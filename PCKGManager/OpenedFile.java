package PCKGManager;

import java.nio.ByteBuffer;
import java.nio.file.Path;

import SystemDataManagers.KingdomPlanManager.kingdomPlanManager;
import colReader.ColReader;

public class OpenedFile 
{
	protected String name = "null";
	byte[] data = new byte[0];
	protected OpenedFile()
	{
	}
	private OpenedFile(String fileName, byte[] fileData)
	{
		name = fileName;
		data = fileData;
	}
	public OpenedFile(String fileName, ByteBuffer fileData)
	{
		name = fileName;
		data = fileData.array();
	}
	public OpenedFile(Path filePath) 
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
	public static OpenedFile makeFile(String name, byte[] file) 
	{
		String fileType = bFM.Utils.getFileType(name, file);
		if(fileType.equals("Fixed Point"))
		{
			//files.add(new FixedPoint(packageFile,files.size(),padding+5,i));
		}
		else if(fileType.equals("Collision"))
		{
			return new ColReader(file,name);
		}
		else if (fileType.equals("Package"))
		{
			return new PCKGManager(file, name);
		}else if (fileType.equals("KingdomPlanDB"))
		{
			return new kingdomPlanManager(file);
		}
		return new OpenedFile(name, file);
	}
}
