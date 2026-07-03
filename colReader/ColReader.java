package colReader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import bFM.GenericFile;
import bFM.Utils;

public class ColReader extends GenericFile
{
	static final String type = "MDF_COL_WII_100";
	public static boolean optimizeCollision = false;
	static final int startPos = 96;
	int index = 0;
	String colName = "all_field";
	ArrayList<CollisionObject> COLOBJECTS = new ArrayList<CollisionObject>();
	public ColReader() {}
	public ColReader(byte[] file)
	{
		ByteBuffer data = ByteBuffer.wrap(file);
		data.position(32);
		byte chara = data.get();
		name = "";
		colName = "";
		while(chara!=0)
		{
			colName += (char)chara;
			chara = data.get();
		}
		int objects = data.getInt(64);
		makeColObjects(data, objects);
	}
	public ColReader(String name)
	{
		this.name = name;
		COLOBJECTS.add(new CollisionObject("Collision",-1, 0));
		COLOBJECTS.add(new CollisionObject("Ground", 0, 1));
		COLOBJECTS.add(new CollisionObject("Wall", 0, 2));
	}
	public ColReader(byte[] file, String name)
	{
		this.name = name;
		ByteBuffer data = ByteBuffer.wrap(file);
		data.position(32);
		byte chara = data.get();
		name = "";
		colName = "";
		while(chara!=0)
		{
			colName += (char)chara;
			chara = data.get();
		}
		int objects = data.getInt(64);
		makeColObjects(data, objects);
	}
	private void makeColObjects(ByteBuffer data, int objects) 
	{
		for(int i = 96; i<objects*160; i+=160)
		{
			data.position(i);
			bFM.Utils.DebugPrint("File Index: " + i);
			COLOBJECTS.add(new CollisionObject(data));
		}
	}
	public static boolean same(byte[] arr1, byte[] arr2)
	{
		if(arr1.length!=arr2.length) return false;
		for(int i = 0; i<arr1.length; i++)
		{
			if(arr1[i]!=arr2[i]) return false;
		}
		return true;
	}
	public static byte[] longToBytes(long num, int size)
	{
		byte[] ret = new byte[size];
		int place=0;
		for(long i = (long) Math.pow(256, size); i > 1; i/=256)
		{
			ret[place] = (byte)(num*256/i);
			place++;
		}
		
		return ret;
	}
	private void updateColObject(CollisionObject object)
	{
		int otherObjectPos = 0;
		int vertexPos = 0;
		int headerPos = 0;
		int listPos = 0;
		int treePos = 0;
		int indexPos = 0;
		int pos = 96;
		pos +=160*COLOBJECTS.size();
		otherObjectPos = pos;
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			if (COLOBJECTS.get(i).equals(object)) 
			{
				otherObjectPos = pos;
			}
			pos+=160*COLOBJECTS.get(i).amountNormalObjects;
		}
		//bFM.Utils.DebugPrint("Calculated Head Pos: " + pos);
		pos+=32;
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			if (COLOBJECTS.get(i).equals(object)) 
			{
				headerPos = pos;
			}
			pos+=COLOBJECTS.get(i).getHeaderDataSize();
		}
		//bFM.Utils.DebugPrint("Calculated List Pos: " + pos);
		pos+=32;
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			if (COLOBJECTS.get(i).equals(object)) 
			{
				listPos = pos;
			}
			pos+=32*COLOBJECTS.get(i).getListAmount();
		}
		//bFM.Utils.DebugPrint("Calculated Tree Pos: " + pos);
		pos+=32;
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			if (COLOBJECTS.get(i).equals(object)) 
			{
				treePos = pos;
			}
			pos+=COLOBJECTS.get(i).getTreeDataSize();
		}
		//bFM.Utils.DebugPrint("Calculated Index Pos: " + pos);
		pos+=32;
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			if (COLOBJECTS.get(i).equals(object)) 
			{
				indexPos = pos;
			}
			pos+=COLOBJECTS.get(i).getIndexSize();
		}
		//bFM.Utils.DebugPrint("Calculated Vertex Pos: " + pos);
		pos+=32;
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			if (COLOBJECTS.get(i).equals(object)) 
			{
				vertexPos = pos;
			}
			pos+=COLOBJECTS.get(i).getVertexDataSize();
		}
		object.updatePositions(otherObjectPos, headerPos, listPos, treePos, indexPos, vertexPos);
	}
	public byte[] getBytes()
	{
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			updateColObject(COLOBJECTS.get(i));
		}
		byte[] ret = new byte[startPos];
		for(int i = 0; i<type.length(); i++)
		{
			ret[i] = (byte) type.charAt(i);
		}
		for(int i = 0; i<colName.length(); i++)
		{
			ret[i+32] = (byte) colName.charAt(i);
		}
		byte[] num = longToBytes(COLOBJECTS.size(), 4);
		for(int i = 0; i<num.length; i++)
		{
			ret[67-i] = num[num.length-i-1];
		}
		 num = longToBytes(startPos, 4);
		for(int i = 0; i<num.length; i++)
		{
			ret[71-i] = num[num.length-i-1];
		}
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getObjects());
		}
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getOtherObjects());
		}
		//bFM.Utils.DebugPrint("Head Pos: " + ret.length);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes("HEAD"), new byte[28]));
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getHeader());
		}
		//bFM.Utils.DebugPrint("List Pos: " + ret.length);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes("LIST"), new byte[28]));
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getList());
		}
		//bFM.Utils.DebugPrint("Tree Pos: " + ret.length);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes("TREE"), new byte[28]));
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getTree());
		}
		//bFM.Utils.DebugPrint("Index Pos: " + ret.length);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes("INDEX"), new byte[27]));
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getIndex());
		}
		//bFM.Utils.DebugPrint("Vertex Pos: " + ret.length);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes("VERTEX"), new byte[26]));
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getVertex());
		}
		return ret;
	}
	private void importOBJ(List<String> lines)
	{
		int vertexCount = 1;
		int totalVertexCount = 1;
		String nameForChunks = "all_field";
		String colCode = name;
		if(colCode.indexOf(".col")!=-1)
		{
			colCode = colCode.substring(0, colCode.indexOf(".col"));
		}
		if(Main.grid || colName.equals(nameForChunks))
		{
			//A Chunk
			COLOBJECTS.add(new CollisionObject("col" + colCode, -1, COLOBJECTS.size()));
			COLOBJECTS.add(new CollisionObject("Ground_"+colCode, 0, COLOBJECTS.size()));
			COLOBJECTS.add(new CollisionObject("Wall_"+colCode, 0, COLOBJECTS.size()));
		}
		else
		{
			//A Buidling
			COLOBJECTS.add(new CollisionObject(colCode, -1, COLOBJECTS.size()));
			COLOBJECTS.add(new CollisionObject("Ground", 0, COLOBJECTS.size()));
			COLOBJECTS.add(new CollisionObject("Wall", 0, COLOBJECTS.size()));
		}

		CollisionObject lastColObject = null;
		
		
		for(String line : lines)
		{
			if (line.length() < 2)
			{
				//If line is empty, do nothing
			}
			else if(line.charAt(0)=='o')
			{
				//If it is an object line
				line = line.substring(2);
				if(line.indexOf("Ground") != -1)
				{
					lastColObject = new CollisionObject(line, 1, COLOBJECTS.size());
				}
				else if(line.indexOf("Wall") != -1)
				{
					lastColObject = new CollisionObject(line, 2, COLOBJECTS.size());
				}
				else
				{
					if(COLOBJECTS.size()<4)
					{
						//likely a header
						System.err.println("Objects must be named with either \"Ground\" or \"Wall\" in their names while this object was named: " + line);
					}
					else
					{
						throw(new IllegalArgumentException("Objects must be named with either \"Ground\" or \"Wall\" in their names while this object was named: " + line));
					}
				}
				totalVertexCount = vertexCount;
				COLOBJECTS.add(lastColObject);
			}
			else if(line.charAt(0)=='v'&&line.charAt(1)==' ')
			{
				lastColObject.addVertexLine(line);
				vertexCount++;
			}
			if(line.charAt(0)=='f')
			{
				lastColObject.addFaceLine(line, totalVertexCount);
			}
		}
	}
	public void importOBJ(Path file) throws IOException
	{
		List<String> lines = Files.readAllLines(file);
		importOBJ(lines);
	}
	public static void optimizeCollision(boolean bool)
	{
		optimizeCollision = bool;
	}
	public static boolean isCollisionFile(byte[] data)
	{
		String header = "MDF_COL_WII_100";
		if(data.length<header.length())
		{
			return false;
		}
		for(int i = 0; i < header.length(); i++)
		{
			if((char)(data[i])!=header.charAt(i))
			{
				return false;
			}
		}
		return true;
	}
	public String toString()
	{
		String ret = "LKS Collision File \n";
		int vertOffset = 1;
		for(int i = 0; i<COLOBJECTS.size();i++)
		{
			ret = ret + COLOBJECTS.get(i).toOBJ(vertOffset);
			vertOffset+= COLOBJECTS.get(i).getVertexAmount();
			//System.out.println(vertOffset);
		}
		return ret;
	}
	public void setData(byte[] file)
	{
		ByteBuffer data = ByteBuffer.wrap(file);
		int objects = data.getInt(64);
		makeColObjects(data, objects);
	}
	public byte[] toBytes()
	{
		return getBytes();
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public ArrayList<CollisionObject> getObjects()
	{
		return COLOBJECTS;
	}
	public void replaceFromOBJ(byte[] data) 
	{
		List<String> lines = bFM.Utils.bytesToStrs(data);
		COLOBJECTS.removeAll(COLOBJECTS);
		importOBJ(lines);
	}
	public byte[] toOBJ()
	{
		return Utils.encodeStringToBytes(toString());
	}
}
