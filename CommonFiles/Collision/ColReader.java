package CommonFiles.Collision;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import bFM.OpenedFile;
import bFM.Utils;

public class ColReader implements OpenedFile
{
	protected static final boolean EnableCollisionDebugPrinting = true;
	static void DebugPrint(String str)
	{
		if(EnableCollisionDebugPrinting)
			Utils.DebugPrint(str);
	}
	protected static enum CollisionSize
	{
		COL_FILE_HEADER(96),
		COL_OBJECT(160),
		COL_ATTRIBUTE(160),
		COL_HEADER(32),
		COL_LIST(32),
		COL_TREE(48),
		COL_INDEX(6),
		COL_VERTEX(16);
		protected final int size;
		private CollisionSize(int size)
		{
			this.size = size;
		}
	}
	private String name;
	static final String type = "MDF_COL_WII_100";
	int index = 0;
	String colName = "all_field";
	ArrayList<CollisionObject> CollisionObjects = new ArrayList<CollisionObject>();
	ArrayList<CollisionMaterial> CollisionAttributes = new ArrayList<CollisionMaterial>();
	ArrayList<CollisionHeader> CollisionHeaders = new ArrayList<CollisionHeader>();
	ArrayList<CollisionList> CollisionLists = new ArrayList<CollisionList>();
	ArrayList<CollisionTree> CollisionTrees = new ArrayList<CollisionTree>();
	ArrayList<CollisionIndex> CollisionIndicies = new ArrayList<CollisionIndex>();
	ArrayList<CollisionVertex> CollisionVerticies = new ArrayList<CollisionVertex>();
	public ColReader()
	{
		name = "New Collision.col";
	}
	public ColReader(byte[] file)
	{
		name = "";
		initializeFromBytes(file);
	}
	public ColReader(String name)
	{
		this.name = name;
		CollisionObjects.add(new CollisionObject("Collision",-1, 0));
		CollisionObjects.add(new CollisionObject("Ground", 0, 1));
		CollisionObjects.add(new CollisionObject("Wall", 0, 2));
	}
	public ColReader(byte[] file, String name)
	{
		this.name = name;
		initializeFromBytes(file);
	}
	
	
	
	private void initializeFromBytes(byte[] file)
	{
		if(!isCollisionFile(file))
		{
			throw new IllegalArgumentException("Collision File Header is Wrong");
		}
		
		ByteBuffer data = ByteBuffer.wrap(file);
		data.position(32);
		byte chara = data.get();
		
		colName = "";
		while(chara!=0)
		{
			colName += (char)chara;
			chara = data.get();
		}
		int ObjectCount = data.getInt(64);
		for(int i = 0; i<ObjectCount; i++)
		{
			int offset = CollisionSize.COL_FILE_HEADER.size + CollisionSize.COL_OBJECT.size * i;
			DebugPrint("File Index: " + i);
			try
			{
				CollisionObjects.add(new CollisionObject(this, offset, data));
			}
			catch(IllegalArgumentException e)
			{
				System.err.println("Failed to Parse Collision Object at Index: " + i);
				e.printStackTrace();
			}
		}
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
		pos +=160*CollisionObjects.size();
		otherObjectPos = pos;
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			if (CollisionObjects.get(i).equals(object)) 
			{
				otherObjectPos = pos;
			}
			pos+=160*CollisionObjects.get(i).amountNormalObjects;
		}
		//bFM.Utils.DebugPrint("Calculated Head Pos: " + pos);
		pos+=32;
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			if (CollisionObjects.get(i).equals(object)) 
			{
				headerPos = pos;
			}
			pos+=CollisionObjects.get(i).getHeaderDataSize();
		}
		//bFM.Utils.DebugPrint("Calculated List Pos: " + pos);
		pos+=32;
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			if (CollisionObjects.get(i).equals(object)) 
			{
				listPos = pos;
			}
			pos+=32*CollisionObjects.get(i).getListAmount();
		}
		//bFM.Utils.DebugPrint("Calculated Tree Pos: " + pos);
		pos+=32;
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			if (CollisionObjects.get(i).equals(object)) 
			{
				treePos = pos;
			}
			pos+=CollisionObjects.get(i).getTreeDataSize();
		}
		//bFM.Utils.DebugPrint("Calculated Index Pos: " + pos);
		pos+=32;
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			if (CollisionObjects.get(i).equals(object)) 
			{
				indexPos = pos;
			}
			pos+=CollisionObjects.get(i).getIndexSize();
		}
		//bFM.Utils.DebugPrint("Calculated Vertex Pos: " + pos);
		pos+=32;
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			if (CollisionObjects.get(i).equals(object)) 
			{
				vertexPos = pos;
			}
			pos+=CollisionObjects.get(i).getVertexDataSize();
		}
		object.updatePositions(otherObjectPos, headerPos, listPos, treePos, indexPos, vertexPos);
	}
	public byte[] getBytes()
	{
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			updateColObject(CollisionObjects.get(i));
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
		byte[] num = longToBytes(CollisionObjects.size(), 4);
		for(int i = 0; i<num.length; i++)
		{
			ret[67-i] = num[num.length-i-1];
		}
		 num = longToBytes(startPos, 4);
		for(int i = 0; i<num.length; i++)
		{
			ret[71-i] = num[num.length-i-1];
		}
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, CollisionObjects.get(i).getObjects());
		}
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, CollisionObjects.get(i).getOtherObjects());
		}
		//bFM.Utils.DebugPrint("Head Pos: " + ret.length);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes("HEAD"), new byte[28]));
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, CollisionObjects.get(i).getHeader());
		}
		//bFM.Utils.DebugPrint("List Pos: " + ret.length);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes("LIST"), new byte[28]));
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, CollisionObjects.get(i).getList());
		}
		//bFM.Utils.DebugPrint("Tree Pos: " + ret.length);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes("TREE"), new byte[28]));
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, CollisionObjects.get(i).getTree());
		}
		//bFM.Utils.DebugPrint("Index Pos: " + ret.length);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes("INDEX"), new byte[27]));
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, CollisionObjects.get(i).getIndex());
		}
		//bFM.Utils.DebugPrint("Vertex Pos: " + ret.length);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes("VERTEX"), new byte[26]));
		for(int i = 0; i<CollisionObjects.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, CollisionObjects.get(i).getVertex());
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
			CollisionObjects.add(new CollisionObject("col" + colCode, -1, CollisionObjects.size()));
			CollisionObjects.add(new CollisionObject("Ground_"+colCode, 0, CollisionObjects.size()));
			CollisionObjects.add(new CollisionObject("Wall_"+colCode, 0, CollisionObjects.size()));
		}
		else
		{
			//A Buidling
			CollisionObjects.add(new CollisionObject(colCode, -1, CollisionObjects.size()));
			CollisionObjects.add(new CollisionObject("Ground", 0, CollisionObjects.size()));
			CollisionObjects.add(new CollisionObject("Wall", 0, CollisionObjects.size()));
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
					lastColObject = new CollisionObject(line, 1, CollisionObjects.size());
				}
				else if(line.indexOf("Wall") != -1)
				{
					lastColObject = new CollisionObject(line, 2, CollisionObjects.size());
				}
				else
				{
					if(CollisionObjects.size()<4)
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
				CollisionObjects.add(lastColObject);
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
	public String toString()
	{
		String ret = "LKS Collision File \n";
		int vertOffset = 1;
		for(int i = 0; i<CollisionObjects.size();i++)
		{
			ret = ret + CollisionObjects.get(i).toOBJ(vertOffset);
			vertOffset+= CollisionObjects.get(i).getVertexAmount();
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
		return CollisionObjects;
	}
	public void replaceFromOBJ(byte[] data) 
	{
		List<String> lines = bFM.Utils.bytesToStrs(data);
		CollisionObjects.removeAll(CollisionObjects);
		importOBJ(lines);
	}
	public byte[] toOBJ()
	{
		return Utils.encodeStringToBytes(toString());
	}
	public int getSize()
	{
		return toBytes().length;
	}
	public boolean equals(String name)
	{
		return name.equals(this.name);
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
	protected CollisionObject getObjectFromIndex(int index)
	{
		if(index >= CollisionObjects.size())
		{
			throw new IllegalArgumentException(String.format("Collision Object Index (%d), is Greater than Amount of Objects Defined (%d)", index, CollisionObjects.size()));
		}
		if(index == -1) return null;
		
		return CollisionObjects.get(index);
	}
	protected void register(CollisionObject object)
	{
		CollisionObjects.add(object);
	}
	protected void register(CollisionMaterial attribute)
	{
		CollisionAttributes.add(attribute);
	}
	protected void register(CollisionVertex vertex)
	{
		CollisionVerticies.add(vertex);
	}
}
