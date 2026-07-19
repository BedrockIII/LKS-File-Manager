package LZ10Convertor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;

import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class LZ_PCKGManager extends PCKGManager
{
	public LZ_PCKGManager(String name)
	{
		this.name = name;
	}
	public LZ_PCKGManager(byte[] data)
	{
		name = "NewPackage_LZ.bin";
		extractPAC(data);
	}
	public LZ_PCKGManager(byte[] data, String name)
	{
		this.name = name;
		extractPAC(data);
	}
	public LZ_PCKGManager(Path filePath) 
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
	protected void extractPAC(byte[] data)
	{
		//Override to Decompress File
		byte[] extractedData = LZ10Decompressor.decompress(data);
		super.extractPAC(extractedData);
	}
	protected void addPackedFile(String fileName, byte[] fileContents)
	{
		//Override to Decompress File and change file extensions
		//
		if(fileName.indexOf("_LZ.bin") != -1)
		{
			fileName = fileName.substring(0, fileName.indexOf("_LZ.bin")) + ".txt";
		}
		byte[] extractedData = LZ10Decompressor.decompress(fileContents);
		files.add(OpenedFile.makeFile(fileName, extractedData));
	}
	protected byte[] getFileWithHeader(OpenedFile file, boolean isLast)
	{
		String fileName = file.getName();
		if(fileName.indexOf(".txt") != -1)
		{
			fileName = fileName.substring(0, file.getName().indexOf(".txt")) + "_LZ.bin";
		}
		byte[] compressedData = LZ10Compressor.compress(file.toBytes());
		int headerSize = HeaderSizeWithoutName + fileName.length(); //Get the size of the header
		if(headerSize % 32 != 0) headerSize = (headerSize / 32 + 1) * 32; //round the header to be the correct length
		
		int nextFileOffset = compressedData.length + headerSize;
		if(nextFileOffset % 32 != 0 && AlignedFiles) nextFileOffset = (nextFileOffset / 32 + 1) * 32; //round the offset to be the correct length if needed
		
		ByteBuffer ret = ByteBuffer.allocate(nextFileOffset);
		
		if(isLast)ret.putInt(nextFileOffset);
		else ret.putInt(0);
		ret.putInt(compressedData.length);
		ret.putInt(headerSize);
		ret.put(Utils.encodeStringToBytes(fileName));
		ret.position(headerSize);
		ret.put(compressedData);
		while(ret.remaining()>0)
		{
			ret.put((byte)0xff);
		}
		return ret.array();
	}
	public byte[] getFile()
	{
		byte[] compressedData = LZ10Compressor.compress(super.getFile());
		return compressedData;
	}
	public byte[] getUncompressedFile()
	{
		return super.getFile();
	}
	public void addFile(String fileName, byte[] bytes) 
	{
		if(fileName.indexOf("_LZ.bin") != -1)
		{
			fileName = fileName.substring(0, fileName.indexOf("_LZ.bin")) + ".txt";
		}
		super.addFile(fileName, bytes);
	}
}
