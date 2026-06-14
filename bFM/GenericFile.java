package bFM;

import java.nio.ByteBuffer;
import java.nio.file.Path;

public class GenericFile implements OpenedFile
{
	protected String name = "null";
	byte[] data = new byte[0];
	
	protected GenericFile()
	{
	}
	public GenericFile(String fileName, byte[] fileData)
	{
		name = fileName;
		data = fileData;
	}
	public GenericFile(String fileName, ByteBuffer fileData)
	{
		name = fileName;
		data = fileData.array();
	}
	public GenericFile(Path filePath) 
	{
		// TODO Auto-generated constructor stub
	}
	public boolean equals(String name)
	{
		//returns if this has the same name, not whether or not the contents are the same.
		return this.name.equals(name);
	}
	public void setData(byte[] data)
	{
		this.data = data;
	}
	public byte[] toBytes()
	{
		return data;
	}
	public String getName()
	{
		return name;
	}
	public String toString()
	{
		return "File: " + name + " File Size: " + data.length + "\n";
	}
	public int getSize()
	{
		return data.length;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
}
