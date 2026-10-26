package ResourceManagers.CharacterDatabaseManager;

import java.util.ArrayList;
import java.util.List;

import bFM.OpenedFile;
import bFM.Utils;

public class CharacterResourceList implements OpenedFile
{
	ArrayList<CharacterBody> bodies = null;
	ArrayList<CharacterFace> faces = null;
	protected CharacterResourceList(List<String> lines)
	{
		initialize(lines.toArray(new String[0]));
	}
	protected CharacterResourceList(byte[] file) 
	{
		String data = Utils.decodeBytesToString(file);
		String[] lines = data.split(";");
		initialize(lines);
	}
	protected CharacterResourceList()
	{
		bodies = new ArrayList<CharacterBody>();
		faces = new ArrayList<CharacterFace>();
	}
	private void initialize(String[] lines)
	{
		bodies = new ArrayList<CharacterBody>();
		faces = new ArrayList<CharacterFace>();
		CharacterBody lastBody = null;
		CharacterFace lastFace = null;
		for(String line : lines)
		{
			if(line.indexOf("BD_DAT ")!=-1)
			{
				lastBody = new CharacterBody(line);
				bodies.add(lastBody);
			}
			else if(line.indexOf("BD_DATV ")!=-1)
			{
				lastBody.addVData(line);
			}
			else if(line.indexOf("FC_DAT ")!=-1)
			{
				lastFace = new CharacterFace(line);
				faces.add(lastFace);
			}
			else if(line.indexOf("FC_DATV ")!=-1)
			{
				lastFace.addVData(line);
			}
			else if(line.indexOf("Body Data ")!=-1)
			{
				lastBody = new CharacterBody(line, true);
				bodies.add(lastBody);
			}
			else if(line.indexOf("Face Data ")!=-1)
			{
				lastFace = new CharacterFace(line, true);
				faces.add(lastFace);
			}
		}
	}
	public String toString()
	{
		String ret = "";
		ret += "BD_NUM " + bodies.size() + ";\r\n";
		for(CharacterBody body : bodies)
		{
			ret += body.toString();
		}
		ret += "FC_NUM " + faces.size() + ";\r\n";
		for(CharacterFace face : faces)
		{
			ret += face.toString();
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = null;
		ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes("BD_NUM " + bodies.size() + ";\r\n"));
		for(CharacterBody body : bodies)
		{
			ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes(body.toString()));
		}
		ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes("FC_NUM " + faces.size() + ";\r\n"));
		for(CharacterFace face : faces)
		{
			ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes(face.toString()));
		}
		return ret;
	}
	public String toCSV()
	{
		String ret = "";
		for(CharacterBody body : bodies)
		{
			ret += body.toCSV() + "\n";
		}
		for(CharacterFace face : faces)
		{
			ret += face.toCSV() + "\n";
		}
		return ret;
	}
	public ArrayList<CharacterBody> getBodies() 
	{
		return bodies;
	}
	public ArrayList<CharacterFace> getFaces() 
	{
		return faces;
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
