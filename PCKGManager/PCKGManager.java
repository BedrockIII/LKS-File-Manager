package PCKGManager;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import bFM.GenericFile;
import bFM.OpenedFile;
public class PCKGManager extends GenericFile
{
	final static private int HeaderSizeWithoutName = 12;
	private static boolean AlignedFiles = true; // Whether or not files have extra bytes appended onto them so they are exactly a multiple of 32
	private static boolean LZMode = false;
	private static boolean nlksMode = false;
	//String name = "Unknown Name";
	ArrayList<OpenedFile> files = new ArrayList<OpenedFile>();
	public PCKGManager(String name)
	{
		this.name = name;
	}
	public PCKGManager(byte[] data)
	{
		name = "NewPackage.pac";
		extractPAC(data);
	}
	public static void setNLKSMode(boolean bool)
	{
		nlksMode = bool;
	}
	public static void setLzCompressionMode(boolean bool)
	{
		LZMode = bool;
	}
	public static void setAlignmentMode(boolean bool)
	{
		AlignedFiles = bool;
	}
	public PCKGManager(byte[] data, String name)
	{
		this.name = name;
		extractPAC(data);
	}
	public PCKGManager(Path filePath) 
	{
		try 
		{
			name = filePath.getFileName().toString();
			extractPAC(Files.readAllBytes(filePath));
		} catch (IOException e) 
		{
			System.out.println("Failed to read file from given path");
			e.printStackTrace();
		}
	}
	private void extractPAC(byte[] file)
	{//turns the pacFile into ArrayLists
		/*PCKG File layout
		 * First 32 Bytes: PCKG Header, PCKG and then 28 0x00 bytes
		 * 
		 * PCKG Block Layout:
		 * First Four Bytes: Size of File Block
		 * Second Four Bytes: Size of File in File Block
		 * Third Four Bytes: Size of File Block Header (default 0x20)
		 * Byte 13+: name
		 * 
		 * */
		byte[] fileName; //Name of current file
		byte[] fileContents = {0x01,0x01}; //Contents of current file
		int nextHeaderPos = 32;
		int nextCnt = 1;
		String theName = "";
		int headerSize = 32;
		if(file.length<64)return;
		ByteBuffer data = ByteBuffer.wrap(file);
		if(nlksMode)
		{
			data.order(ByteOrder.LITTLE_ENDIAN);
		}
		if(isPAC(file))
		{
		while (nextCnt != 0) 
		{
			bFM.Utils.DebugPrint("\tNextHeaderPos: " + nextHeaderPos);
			headerSize = data.getInt(nextHeaderPos+8);
			int FileStartPosition = nextHeaderPos+headerSize;
			int FileEndPosition = FileStartPosition + data.getInt(nextHeaderPos+4);
			
			
			fileContents = Arrays.copyOfRange(file, FileStartPosition, FileEndPosition);	
			
			fileName = Arrays.copyOfRange(file, nextHeaderPos+12, nextHeaderPos+headerSize);
			try {theName = new String(fileName);} catch (Exception error) {System.out.println("Failed to Extract due to being an unsupported encoding");break;}
			
			String letters =  "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890,.;'/`~<>?_: ";
			String theNewName = "";
			theName = theName.replaceAll("\0+$", "");
			for(int i = 0; i<theName.length(); i++)
			{
				if(letters.indexOf(theName.charAt(i))!=-1)
				{
					theNewName = theNewName + theName.charAt(i);
				}
			}
			
			bFM.Utils.DebugPrint("Found File: " + theName + " File Size: " + Math.abs(data.getInt(nextHeaderPos+4)));
			bFM.Utils.DebugPrint("\tHeader Size: " + headerSize);
			if(!bFM.Utils.autoEditSubPackFile) files.add(new GenericFile(name, fileContents));
			else files.add(OpenedFile.makeFile(theNewName, fileContents));
			
			
			nextCnt = data.getInt(nextHeaderPos);
			nextHeaderPos += nextCnt;
			}
		}
	}
	public byte[] getFile(String name)
	{ 
		for(OpenedFile file : files)
		{
			if(file.equals(name))
			{
				return file.toBytes();
			}
		}
		return new byte[0];
	}
	public boolean hasFile(String name)
	{
		for(OpenedFile file : files)
		{
			if(file.equals(name))
			{
				return true;
			}
		}
		return false;
	}
	public void replaceFile(String name, byte[] file)
	{
		for(OpenedFile f : files)
		{
			if(f.equals(name))
			{
				f.setData(file);
			}
		}
	}
	public void removeFile(String name)
	{
		for(OpenedFile file : files)
		{
			if(file.equals(name))
			{
				files.remove(file);
			}
			
		}
	}
	public byte[] getFile()
	{
		byte[] data = new byte[32];
		data[0] = 'P';
		data[1] = 'C';
		data[2] = 'K';
		data[3] = 'G';
		for (int i = 0; i < files.size(); i++) 
		{
			data = bFM.Utils.mergeArrays(data, getFileWithHeader(files.get(i), files.size()-1!=i));
		} 
		return data;
	}
	public int getSize()
	{
		int ret = 32;
		for (int i = 0; i < files.size(); i++) 
		{
			ret += getFileSizeWithHeader(files.get(i));
		} 
		return ret;
	}
	private int getFileSizeWithHeader(OpenedFile file) 
	{
		int ret = HeaderSizeWithoutName + file.getName().length(); //Get the size of the header
		if(ret % 32 != 0) ret = (ret / 32 + 1) * 32;
		ret += file.getSize();
		if(AlignedFiles && ret % 32 != 0) ret = (ret / 32 + 1) * 32;
		return ret;
	}
	public byte[] toBytes()
	{
		//Override
		return getFile();
	}
	public static boolean isPAC(byte[] data)
	{//returns if the header matches a PAC file header
		if(data.length<4) return false;
		return data[0] == 0x50 && data[1] == 0x43 && data[2] == 0x4B && data[3] == 0x47;
	}
	public void extractAll(String dest)
	{
		for(OpenedFile file : files)
		{
			try 
			{
				Files.write(Paths.get(dest + file.getName()), file.toBytes());
			} catch (IOException e) 
			{
				System.out.println("Failed to Create File \"" + file.getName() + "\"");
				e.printStackTrace();
			}
		}
	}
	public String toString()
	{
		String ret = "Package File \"" + name + "\"\n";
		for(OpenedFile file : files)
		{
			ret += file.toString();
		}
		return ret;
	}
	public void addFile(String name, byte[] bytes) 
	{
		for(OpenedFile file : files)
		{
			if(file.equals(name))
			{
				bFM.Utils.DebugPrint("Found Packed File: " + name + " File Type: " + file.getClass());
				file.setData(bytes);
				return;
			}
		}
		if(!bFM.Utils.autoEditSubPackFile) files.add(new GenericFile(name, bytes));
		else if(isPAC(bytes))files.add(new PCKGManager(bytes, name));
		else files.add(OpenedFile.makeFile(name, bytes));
	}
	public void writePac(String outputName) 
	{
		try
		{
			Files.write(Paths.get(outputName + name), getFile());
		}
		catch(Exception error) 
		{
			System.out.println("File Writing Error");
		}
	}
	public int getFileAmount()
	{
		return files.size();
	}
	public String getName(int i)
	{
		return files.get(i).getName();
	}
	public byte[] getFile(int i) 
	{
		return files.get(i).toBytes();
	}
	private byte[] getFileWithHeader(OpenedFile file, boolean isLast)
	{
		int headerSize = HeaderSizeWithoutName + file.getName().length(); //Get the size of the header
		if(headerSize % 32 != 0) headerSize = (headerSize / 32 + 1) * 32; //round the header to be the correct length
		
		int nextFileOffset = file.toBytes().length + headerSize;
		if(nextFileOffset % 32 != 0 && AlignedFiles) nextFileOffset = (nextFileOffset / 32 + 1) * 32; //round the offset to be the correct length if needed
		
		ByteBuffer ret = ByteBuffer.allocate(nextFileOffset);
		
		if(isLast)ret.putInt(nextFileOffset);
		else ret.putInt(0);
		ret.putInt(file.toBytes().length);
		ret.putInt(headerSize);
		ret.put(file.getName().getBytes(Charset.forName("Shift-JIS")));
		ret.position(headerSize);
		ret.put(file.toBytes());
		if(LZMode)
		{
			while(ret.remaining()>0)
			{
				ret.put((byte)0xff);
			}
		}
		return ret.array();
	}
	public int indexOf(String FileName) 
	{
		for(int i = 0; i < files.size(); i++)
		{
			if(FileName.equals(files.get(i).getName()))
			{
				return i;
			}
		}
		return -1;
	}
	public OpenedFile getPackedFile(int i) 
	{
		return files.get(i);
	}
	public int getAbsoluteFileAmount() 
	{
		//Gets amount of files for the purposes of the GUI, SubPacks are counted with their contents
		int ret = 1;
		for(OpenedFile file : files)
		{
			if(file instanceof PCKGManager)
			{
				ret += ((PCKGManager)file).getAbsoluteFileAmount();
			}
			else
			{
				ret += 1;
			}
		}
		return ret;
	}
	public ArrayList<OpenedFile> getFiles() 
	{
		return files;
	}
	public void setData(byte[] data)
	{
		files = new ArrayList<OpenedFile>();
		extractPAC(data);
	}
}
