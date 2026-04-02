package PCKGManager;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
public class PCKGManager {
	byte[] file;
	String name = "unknown";
	ArrayList<String> filesNames = new ArrayList<String>();
	ArrayList<byte[]> filesContents = new ArrayList<byte[]>();
	ArrayList<String> pacFilesNames = new ArrayList<String>();
	ArrayList<PCKGManager> pacFilesContents = new ArrayList<PCKGManager>();
	private boolean nlksMode = false;
	public PCKGManager(String name)
	{
		this.name = name;
	}
	public PCKGManager(byte[] data)
	{
		file = data;
		
		extractPAC();
		
	}
	public PCKGManager(byte[] data, boolean nlksFile)
	{
		file = data;
		nlksMode = true;
		extractPAC();
		
	}
	public void setLKSMode(boolean bool)
	{
		nlksMode = bool;
		//ByteBuffer.
	}
	public PCKGManager(byte[] data, String name)
	{
		file = data;
		this.name = name;
		extractPAC();
	}
	private void extractPAC()
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
			
			String letters =  "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890,.;'/`~<>?_:";
			String theNewName = "";
			theName = theName.replaceAll("\0+$", "");
			for(int i = 0; i<theName.length(); i++)
			{
				if(letters.indexOf(theName.charAt(i))!=-1)
				{
					theNewName = theNewName + theName.charAt(i);
				}
			}
			
			filesNames.add(theNewName);
			
			bFM.Utils.DebugPrint("Found File: " + theName + " File Size: " + Math.abs(data.getInt(nextHeaderPos+4)));
			bFM.Utils.DebugPrint("\tHeader Size: " + headerSize);
			filesContents.add(fileContents);
			
			
			
			nextCnt = data.getInt(nextHeaderPos);
			nextHeaderPos += nextCnt;
			}
		}
		
		}
	private void rePAC()
	{//Turns the ArrayLists into a pacFile again
		byte[] data = new byte[32];
			data[0] = 'P';
			data[1] = 'C';
			data[2] = 'K';
			data[3] = 'G';
		for (int i = 0; i < filesContents.size(); i++) 
		{
			data = bFM.Utils.mergeArrays(data, getFileHeader(i));
		}
		file = data;
	}
	private byte[] getFileHeader(int index)
	{
		byte[] headerLine;
		byte headerSize = 0x20;
		byte[] name = {0x00, 0x00, 0x00, 0x00};
		try {
			name = filesNames.get(index).getBytes();
			} 
		catch (Exception error) 
		{
			System.out.println("Unsupported Encoding!!!");
			return new byte[0];
		}
		if((name.length+12)>32) 
			headerSize = (byte)(((name.length+12)/32 + 1) * 32);
		else 
			headerSize = 32;
		
		headerLine = new byte[headerSize];
		int nextFileIndex = 0;
		if(index != filesContents.size()-1)
		{
			nextFileIndex = (filesContents.get(index).length/32+1)*32+headerSize;
		}
		byte[] nextFileIndexArray = bFM.Utils.toByteArr(nextFileIndex, 4);
		headerLine[0] = nextFileIndexArray[0];
		headerLine[1] = nextFileIndexArray[1];
		headerLine[2] = nextFileIndexArray[2];
		headerLine[3] = nextFileIndexArray[3];
		int fileSize = filesContents.get(index).length;
		byte[] fileSizeArray = bFM.Utils.toByteArr(fileSize, 4);
		headerLine[4] = fileSizeArray[0];
		headerLine[5] = fileSizeArray[1];
		headerLine[6] = fileSizeArray[2];
		headerLine[7] = fileSizeArray[3];
		byte[] headerSizeArray = bFM.Utils.toByteArr(headerSize, 4);
		headerLine[8] = headerSizeArray[0];
		headerLine[9] = headerSizeArray[1];
		headerLine[10] = headerSizeArray[2];
		headerLine[11] = headerSizeArray[3];
		for(int i = 12; i<name.length+12&&i<headerSize; i++)
		{
			headerLine[i] = name[i-12];
		}
		byte[] ret = bFM.Utils.mergeArrays(headerLine, filesContents.get(index));
		return  bFM.Utils.mergeArrays(ret, new byte[32-ret.length%32]);
	}
	public byte[] getFile(String name)
	{ 
		for(int i = 0; i < filesNames.size(); i++)
		{
			if(filesNames.get(i).equals(name))
			{
				return filesContents.get(i);
			}
		}
		return new byte[0];
	}
	public void replaceFile(String name, byte[] file)
	{
		for(int i = 0; i < filesNames.size(); i++)
		{
			if(filesNames.get(i).equals(name))
			{
				filesContents.set(i, file);
			}
			
		}
		rePAC();
	}
	public void removeFile(String name)
	{
		for(int i = 0; i < filesNames.size(); i++)
		{
			if(filesNames.get(i).equals(name))
			{
				filesNames.remove(i);
				filesContents.remove(i);
				///if(pacFilesNames.get(i).equals(name))
				//{
					//pacFilesNames.remove(i);
					//pacFilesContents.remove(i);
				//}
			}
			
		}
		rePAC();
	}
	public byte[] getFile()
	{
		rePAC();
		return file;
	}
	public static boolean isPAC(byte[] data)
	{//returns if the header matches a PAC file header
		if(data.length<4) return false;
		return data[0] == 0x50 && data[1] == 0x43 && data[2] == 0x4B && data[3] == 0x47;
	}
	public void extractAll(String dest)
	{
		for(int i = 0; i < filesContents.size(); i++)
		{
			try {
				Files.write(Paths.get(dest + filesNames.get(i)), filesContents.get(i));
			} catch (IOException e) {
				System.out.println("Failed to Create File \"" + filesNames.get(i) + "\"");
				System.out.println(e);
			}
		}
	}
	public String toString()
	{
		return name;
	}
	public void addFile(String name, byte[] bytes) 
	{
		for(int i = 0; i<filesNames.size(); i++)
		{
			if(filesNames.get(i).equals(name))
			{
				filesNames.set(i, name);
				filesContents.set(i, bytes);
				rePAC();
				return;
			}
		}
		filesNames.add(name);
		filesContents.add(bytes);
		rePAC();
	}
	public void writePac(String outputName) 
	{
		rePAC();
		try{Files.write(Paths.get(outputName + name), file);}catch(Exception error) {System.out.println("File Writing Error");}
	}
	public int getFileAmount()
	{
		return filesContents.size();
	}
	public String getName()
	{
		return name;
	}
	public String getName(int index)
	{
		return filesNames.get(index);
	}
	public byte[] getFile(int i) 
	{
		return filesContents.get(i);
	}
}
