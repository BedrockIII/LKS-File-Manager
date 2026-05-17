package PCKGManager;

import java.nio.ByteBuffer;

public class OpenedFile 
{
	String name = "null";
	byte[] data = new byte[0];
	protected OpenedFile()
	{
	}
	public OpenedFile(String fileName, byte[] fileData)
	{
		name = fileName;
		data = fileData;
	}
	public OpenedFile(String fileName, ByteBuffer fileData)
	{
		name = fileName;
		data = fileData.array();
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
	public byte[] getData()
	{
		return data;
	}
	public String getName()
	{
		return name;
	}
	public String toString()
	{
		return "File: " + name + " File Size: " + data.length;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
}
