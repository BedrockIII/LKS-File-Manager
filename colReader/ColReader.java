package colReader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.OpenedFile;

public class ColReader extends OpenedFile
{
	static final String type = "MDF_COL_WII_100";
	public static boolean optimizeCollision = true;
	static final int startPos = 96;
	int index = 0;
	ArrayList<colObject> COLOBJECTS = new ArrayList<colObject>();
	int objects;
	public ColReader()
	{
		
	}
	public ColReader(byte[] file)
	{
		ByteBuffer data = ByteBuffer.wrap(file);
		data.position(32);
		byte chara = data.get();
		name = "";
		while(chara!=0)
		{
			name += (char)chara;
			chara = data.get();
		}
		objects = data.getInt(64);
		makeColObjects(data);
	}
	public ColReader(String name)
	{
		boolean grid = true;
		if(Main.grid&&grid )
		{
			this.name = "all_field";
			COLOBJECTS.add(new colObject("col"+name,-1, 0));
			COLOBJECTS.add(new colObject("Ground_"+name, 0, 1));
			COLOBJECTS.add(new colObject("Wall_"+name, 0, 2));
		}
		else
		{
			this.name = name;
			COLOBJECTS.add(new colObject("Collision",-1, 0));
			COLOBJECTS.add(new colObject("Ground", 0, 1));
			COLOBJECTS.add(new colObject("Wall", 0, 2));
		}
		
	}
	public ColReader(byte[] file, String name) 
	{
		this.name = name;
		ByteBuffer data = ByteBuffer.wrap(file);
		objects = data.getInt(64);
		makeColObjects(data);
	}
	private void makeColObjects(ByteBuffer data) 
	{
		for(int i = 96; i<objects*160; i+=160)
		{
			data.position(i);
			COLOBJECTS.add(new colObject(data));
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
	private void updateColObject(colObject object)
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
		pos+=32;
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			if (COLOBJECTS.get(i).equals(object)) 
			{
				headerPos = pos;
			}
			pos+=COLOBJECTS.get(i).getHeaderDataSize();
		}
		pos+=32;
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			if (COLOBJECTS.get(i).equals(object)) 
			{
				listPos = pos;
			}
			pos+=32*COLOBJECTS.get(i).getListAmount();
		}
		pos+=32;
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			if (COLOBJECTS.get(i).equals(object)) 
			{
				treePos = pos;
			}
			pos+=COLOBJECTS.get(i).getTreeDataSize();
		}
		pos+=32;
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			if (COLOBJECTS.get(i).equals(object)) 
			{
				indexPos = pos;
			}
			pos+=COLOBJECTS.get(i).getIndexSize();
		}
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
		for(int i = 0; i<name.length(); i++)
		{
			ret[i+32] = (byte) name.charAt(i);
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
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(new String("HEAD").getBytes(), new byte[28]));
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getHeader());
		}
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(new String("LIST").getBytes(), new byte[28]));
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getList());
		}
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(new String("TREE").getBytes(), new byte[28]));
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getTree());
		}
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(new String("INDEX").getBytes(), new byte[27]));
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getIndex());
		}
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(new String("VERTEX").getBytes(), new byte[26]));
		for(int i = 0; i<COLOBJECTS.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, COLOBJECTS.get(i).getVertex());
			if(ret.length%32!=0)
			{
				ret = bFM.Utils.mergeArrays(ret, new byte[32-ret.length%32]);
			}
		}
		return ret;
	}
	public void importOBJ(Path file, String name) throws IOException
	{
		COLOBJECTS.add(new colObject(name, 0, COLOBJECTS.size()));
		if(Main.grid)
		{
			COLOBJECTS.add(new colObject("Ground_"+name, 0, COLOBJECTS.size()));
			COLOBJECTS.add(new colObject("Wall_"+name, 0, COLOBJECTS.size()));
		}
		else
		{
			COLOBJECTS.add(new colObject("Ground", 0, COLOBJECTS.size()));
			COLOBJECTS.add(new colObject("Wall", 0, COLOBJECTS.size()));
		}
		List<String> lines = Files.readAllLines(file);
		ArrayList<String> objectLines = new ArrayList<String>();
		int oCount = 0;
		int vertexCount = 0;
		int totalVertexCount = 0;
		
		for(int i =0; i<lines.size(); i++)
		{
			if(lines.get(i).length()>1&&lines.get(i).charAt(0)=='o')
			{
				oCount += 1;
				if(oCount<2)objectLines.add(lines.get(i));
				else
				{
					if(lines.get(i).indexOf("Ground")!=-1)
					{
						COLOBJECTS.add(new colObject(objectLines, 1, totalVertexCount, COLOBJECTS.size()));
					}
					else if(lines.get(i).indexOf("Wall")!=-1)
					{
						COLOBJECTS.add(new colObject(objectLines, 2, totalVertexCount, COLOBJECTS.size()));
					}
					else if(lines.get(i).indexOf("G")!=-1)
					{
						COLOBJECTS.add(new colObject(objectLines, 1, totalVertexCount, COLOBJECTS.size()));
					}
					else if(lines.get(i).indexOf("W")!=-1)
					{
						COLOBJECTS.add(new colObject(objectLines, 2, totalVertexCount, COLOBJECTS.size()));
					}
					else if(vertexCount==0)
					{
						//COLOBJECTS.add(new colObject(name, 1));
					}
					else
					{
						COLOBJECTS.add(new colObject(objectLines, 0, totalVertexCount, COLOBJECTS.size()));
					}
					totalVertexCount=vertexCount;
					
					objectLines.removeAll(objectLines);
					oCount = 1;
					objectLines.add(lines.get(i));
				}
			}
			if(lines.get(i).length()>2&&lines.get(i).charAt(0)=='v'&&lines.get(i).charAt(1)==' ')
			{
				vertexCount++;
				//System.out.println(vertexCount);
				objectLines.add(lines.get(i));
			}
			if(lines.get(i).length()>2&&lines.get(i).charAt(0)=='v'&&lines.get(i).charAt(1)=='n')
			{
				//objectLines.add(lines.get(i));
				//normals are not normal
			}
			if(lines.get(i).length()>1&&lines.get(i).charAt(0)=='f')
			{
				objectLines.add(lines.get(i));
			}
		}
		
	}
	public void importOBJ(Path file) throws IOException
	{
		int refVal = COLOBJECTS.size();//before adding it because it needs to be -1 otherwise
		List<String> lines = Files.readAllLines(file);
		ArrayList<String> objectLines = new ArrayList<String>();
		int oCount = 0;
		int vertexCount = 0;
		int totalVertexCount = 0;
		
		for(int i =0; i<lines.size(); i++)
		{
			if(lines.get(i).length()>1&&lines.get(i).charAt(0)=='o')
			{
				oCount += 1;
				if(oCount<2)objectLines.add(lines.get(i));
				else
				{
					if(objectLines.get(0).indexOf("Ground")!=-1)
					{
						COLOBJECTS.add(new colObject(objectLines, 1, totalVertexCount,COLOBJECTS.size()));
					}
					else if(objectLines.get(0).indexOf("Wall")!=-1)
					{
						COLOBJECTS.add(new colObject(objectLines, 2, totalVertexCount,COLOBJECTS.size()));
					}
					else if(vertexCount==0)
					{
						COLOBJECTS.add(new colObject(objectLines, 1, totalVertexCount,COLOBJECTS.size()));
					}
					else
					{
						COLOBJECTS.add(new colObject(objectLines, refVal, totalVertexCount,COLOBJECTS.size()));
					}
					totalVertexCount=vertexCount;
					objectLines.removeAll(objectLines);
					oCount = 1;
					objectLines.add(lines.get(i));
				}
			}
			if(lines.get(i).length()>2&&lines.get(i).charAt(0)=='v'&&lines.get(i).charAt(1)==' ')
			{
				vertexCount++;
				objectLines.add(lines.get(i));
			}
			if(lines.get(i).length()>1&&lines.get(i).charAt(0)=='f')
			{
				objectLines.add(lines.get(i));
			}
		}
		if(objectLines.get(0).indexOf("Ground")!=-1)
		{
			COLOBJECTS.add(new colObject(objectLines, 1, totalVertexCount,COLOBJECTS.size()));
		}
		else if(objectLines.get(0).indexOf("Wall")!=-1)
		{
			COLOBJECTS.add(new colObject(objectLines, 2, totalVertexCount,COLOBJECTS.size()));
		}
		else if(vertexCount==0)
		{
			COLOBJECTS.add(new colObject(objectLines, 1, totalVertexCount,COLOBJECTS.size()));
		}
		else
		{
			COLOBJECTS.add(new colObject(objectLines, refVal, totalVertexCount,COLOBJECTS.size()));
		}
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
			ret = ret + COLOBJECTS.get(i).toString(vertOffset);
			vertOffset+= COLOBJECTS.get(i).getVertexAmount();
			//System.out.println(vertOffset);
		}
		return ret;
	}
	public void setData(byte[] file)
	{
		ByteBuffer data = ByteBuffer.wrap(file);
		objects = data.getInt(64);
		makeColObjects(data);
	}
	public byte[] getData()
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
	public ArrayList<colObject> getObjects()
	{
		return COLOBJECTS;
	}
}
