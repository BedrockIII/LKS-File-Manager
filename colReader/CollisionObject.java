 package colReader;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.Utils;

public class CollisionObject implements Data
{
	String name;
	ArrayList<CollisionMaterial> normalsList = new ArrayList<CollisionMaterial>();
	CollisionHeader header = new CollisionHeader(-1);
	int value;
	int referenceValue;
	int amountNormalObjects;
	int normalObjectLocation;
	float val1;//All of these can safely be 0
	//skip empty bytes
	float val2;
	//skip empty bytes
	float val3;
	
	int val5;
	int val6;
	float zPos;//east west
	float xPos;//north south
	float yPos;//up down
	float wPos;
	//int vertexAmount;
	int VertexPos;
	//int headerAmount;
	int headerPos;
	
	float boundingBoxMaxX;
	float boundingBoxMaxY;
	float boundingBoxMaxZ;
	float boundingBoxMinX;
	float boundingBoxMinY;
	float boundingBoxMinZ;
	
	
	//LIST VARS
	
	//TREE VARS
	int treePos;
	//
	CollisionTree tree;
	ArrayList<CollisionVertex> VERTEX;
	public CollisionObject(String name, int ref, int value)
	{
		this.name = name;
		initializeFromOBJ(ref, value);
	}
	private void initializeFromOBJ(int ref, int value)
	{
		boundingBoxMinX = Float.MAX_VALUE;
		boundingBoxMinY = Float.MAX_VALUE;
		boundingBoxMinZ = Float.MAX_VALUE;
		boundingBoxMaxX = -Float.MAX_VALUE;
		boundingBoxMaxY = -Float.MAX_VALUE;
		boundingBoxMaxZ = -Float.MAX_VALUE;
		if(ref>0) tree = new CollisionTree(this);
		VERTEX = new ArrayList<CollisionVertex>();
		zPos = 0;//east west
		xPos = 0;//north south
		yPos = 0;//up down
		wPos = 1;
		headerPos = 0;
		val1 = 1;
		val2 = 1;
		val3 = 1;
		val5 = 16777216;
		val6 = 0;
		referenceValue = ref;
		this.value = value;
		if(name.indexOf("Wall")!=0) 
		{
			//normalsList.add(new CollisionMaterial("collision",1));
		}
		else if(name.indexOf("Ground")!=0) 
		{
			//normalsList.add(new CollisionMaterial("collision",1));
		}
		//normalsList.add(new CollisionMaterial("37097291_0",0));
		header = new CollisionHeader(referenceValue);
	}
	public CollisionObject(ByteBuffer data) 
	{
		int StartPos = data.position();
		byte chara = data.get();
		name = "";
		while(chara!=0)
		{
			name += (char)chara;
			chara = data.get();
		}
		bFM.Utils.DebugPrint("Collision Object: " + name);
		value = data.getInt(StartPos + 32);
		referenceValue = data.getInt(StartPos + 36);
		amountNormalObjects = data.getInt(StartPos + 40);
		normalObjectLocation = data.getInt(StartPos + 44);
		val1 = data.getFloat(StartPos + 48);
		val2 = data.getFloat(StartPos + 68);
		val3 = data.getFloat(StartPos + 88);
		zPos = data.getFloat(StartPos + 96);
		yPos = data.getFloat(StartPos + 100);
		xPos = data.getFloat(StartPos + 104);
		wPos = data.getFloat(StartPos + 108);
		int vertexAmount = data.getInt(StartPos + 112);
		VertexPos = data.getInt(StartPos + 116);
		//headerAmount = data.getInt(StartPos + 120);
		headerPos = data.getInt(StartPos + 124);
		val5 = data.getInt(StartPos + 128);
		val6 = data.getInt(StartPos + 132);
		boundingBoxMaxX = data.getFloat(StartPos + 136);
		boundingBoxMaxY = data.getFloat(StartPos + 140);
		boundingBoxMaxZ = data.getFloat(StartPos + 144);
		boundingBoxMinX = data.getFloat(StartPos + 148);
		boundingBoxMinY = data.getFloat(StartPos + 152);
		boundingBoxMinZ = data.getFloat(StartPos + 156);
		if(amountNormalObjects > 0 && normalObjectLocation >= 0) 
		{
			bFM.Utils.DebugPrint("Material Amount: " + amountNormalObjects);
			bFM.Utils.DebugPrint("Material Start Position: " + normalObjectLocation);
			for(int i = normalObjectLocation; i<normalObjectLocation+amountNormalObjects*160; i+=160)
			{
				bFM.Utils.DebugPrint("Material Position: " + i);
				data.position(i);
				normalsList.add(new CollisionMaterial(data));
			}
		}
		bFM.Utils.DebugPrint("Header Position: " + headerPos);
		if(headerPos == 0) header = null;
		else 
		{
			header = new CollisionHeader(data, headerPos);
			bFM.Utils.DebugPrint("List Exists: " + (header != null&&header.getListAmount() > 0));
			if(header != null && header.getListAmount() > 0 && header.getListPos() > 0)
			{
				int listPos = header.getListPos();
				bFM.Utils.DebugPrint("List Position: " + listPos);
				treePos = data.getInt(listPos);
				//bFM.Utils.DebugPrint("Tree Position Position: " + listPos);
				if(treePos > 0)
				{
					bFM.Utils.DebugPrint("Tree Position: " + treePos);
					if(treePos>0)
					{
						tree = new CollisionTree(data, treePos, 0);
					}
				}
			}
		}
		VERTEX = new ArrayList<CollisionVertex>();
		if(vertexAmount > 0 && VertexPos >= 0) 
		{
			bFM.Utils.DebugPrint("Vertex Amount: " + vertexAmount);
			bFM.Utils.DebugPrint("Vertex Start Position: " + VertexPos);
			for(int i = VertexPos; i<VertexPos+vertexAmount*16; i+=16)
			{
				bFM.Utils.DebugPrint("Vertex Position: " + i);
				data.position(i);
				VERTEX.add(new CollisionVertex(data));
			}
		}
	}
	public byte[] getObjects()
	{
		byte[] ret = new byte[32];
		for(int i = 0; i<name.length(); i++)
		{
			ret[i] = (byte) name.charAt(i);
		}
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(value).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(referenceValue).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(amountNormalObjects).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(normalObjectLocation).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val1).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[16]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val2).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[16]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val3).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[4]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(xPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(wPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(VERTEX.size()).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(VertexPos).array());
		int headerAmount = 0; 
		if(header != null) headerAmount++;
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(headerAmount).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(headerPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(val5).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(val6).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMaxX).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMaxY).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMaxZ).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMinX).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMinY).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMinZ).array());
		if(ret.length == 160)
			return ret;
		else
		{
			System.out.print("Col object length is wrong" + ret.length);
			return null;
		}
	}
	public byte[] getHeader()
	{
		if(header==null) return new byte[32];
		return header.toBytes();
	}
	public byte[] getList()
	{
		if(header==null || header.getListAmount() < 0 || VERTEX == null || VERTEX.size() == 0)return new byte[0];
		byte[] ret = new byte[32];
		byte[] num = ColReader.longToBytes(treePos,4);
		for(int i = 1; i<num.length; i++)
		{
			ret[4-i] = num[num.length-i];
		}
		for(int i = 8; i<ret.length; i++)
		{
			ret[i] = (byte)0xAA;
		}
		return ret;
	}
	public byte[] getTree()
	{
		if(tree==null) return new byte[0];
		byte[] ret = new byte[0];
		ret = tree.toBytes();
		return ret;
	}
	public byte[] getIndex()
	{
		if(tree == null) return new byte[0];
		return tree.getIndex();
	}
	public byte[] getVertex()
	{
		if(referenceValue<1) return new byte[0];
		if(VERTEX.size()==0) return new byte[0];
		byte[] ret = VERTEX.get(0).toBytes();
		for(int i = 1; i<VERTEX.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, VERTEX.get(i).toBytes());
		}
		if(ret.length%32!=0)
		{
			ret = bFM.Utils.mergeArrays(ret, new byte[32-ret.length%32]);
		}
		return ret;
	}
	public int getIndexSize()
	{
		if(tree == null) return 0;
		if(referenceValue < 1) return 0;
		return tree.getIndexSize();
	}

 	public byte[] toBytes()
	{
		byte[] ret = new byte[32];
		for(int i = 0; i<name.length(); i++)
		{
			ret[i] = (byte)name.charAt(i);
		}
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(value).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(referenceValue).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(amountNormalObjects).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(normalObjectLocation).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val1).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val2).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val3).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(xPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(wPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(VERTEX.size()).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(VertexPos).array());
		int headerAmount = 0; 
		if(header != null) headerAmount++;
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(headerAmount).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(headerPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(val5).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(val6).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMaxX).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMaxY).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMaxZ).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMinX).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMinY).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxMinZ).array());
		return ret;
	}
 	public String toString()
 	{
 		String ret = "Collision Object: ";
 		ret += name + "\n";
 		return ret;
 	}
	public String toOBJ(int vertexOffset)
	{
		String ret = "o "+ name + "\n";
		if(VERTEX!=null)
		{
			for(int i = 0; i < VERTEX.size();i++)
			{
				ret = ret + VERTEX.get(i).toString();
			}
		}
		if(tree != null) ret = ret + tree.toString(vertexOffset);
		return ret;
	}
	public String getName()
	{
		return name;
	}
	public byte[] getOtherObjects() 
	{
		
		byte[] ret = new byte[0];
		for(int i = 0; i< normalsList.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, normalsList.get(i).toBytes());
		}
		return ret;
	}
	float getMinX(CollisionFace face)
	{
		float ret = Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.min(Math.min(ret, v1.getX()), Math.min(v2.getX(), v3.getX()));
	}
	float getMaxX(CollisionFace face)
	{
		float ret = -Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.max(Math.max(ret, v1.getX()), Math.max(v2.getX(), v3.getX()));
	}
	float getMinY(CollisionFace face)
	{
		float ret = Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.min(Math.min(ret, v1.getY()), Math.min(v2.getY(), v3.getY()));
	}
	float getMaxY(CollisionFace face)
	{
		float ret = -Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.max(Math.max(ret, v1.getY()), Math.max(v2.getY(), v3.getY()));
	}
	float getMinZ(CollisionFace face)
	{
		float ret = Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.min(Math.min(ret, v1.getZ()), Math.min(v2.getZ(), v3.getZ()));
	}
	float getMaxZ(CollisionFace face)
	{
		float ret = -Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.max(Math.max(ret, v1.getZ()), Math.max(v2.getZ(), v3.getZ()));
	}
	public int getHeaderAmount() 
	{
		int headerAmount = 0; 
		if(header != null) headerAmount++;
		return headerAmount;
	}
	public int getListAmount() 
	{
		if(this.header==null) return 0;
		return header.getListAmount();
	}
	public int getTreeAmount() 
	{
		if(this.header==null) return 0;
		return tree.getMaxIndex();
	}
	public int getFaceAmount()
	{
		if(tree==null)return 0;
		int ret = 0;
		ret += tree.getFaceAmount();
		return ret;
	}
	public int getVertexAmount() 
	{
		return VERTEX.size();
	}
	public float getXOffset() 
	{
		return xPos;
	}
	public float getYOffset() 
	{
		return yPos;
	}
	public float getZOffset() 
	{
		return zPos;
	}
	public int getReferenceIndex()
	{
		return referenceValue;
	}
	public void updatePositions(int otherObjectPos, int headerPos, int listPos, int treePos, int indexPos, int vertexPos) 
	{
		//bFM.Utils.DebugPrint("Updating Collision Object: " + name);
		if(normalsList.size()>0) normalObjectLocation = otherObjectPos;
		if(header!=null)this.headerPos = headerPos;
		if(header!=null)header.setListPos(listPos);
		this.treePos = treePos;
		if(tree != null) tree.updateTree(indexPos);
		if(VERTEX!=null&&VERTEX.size()>0)this.VertexPos = vertexPos;
	}
	public int getTreeDataSize() 
	{
		if(tree==null) return 0;
		return tree.getSize();
	}
	public int getVertexDataSize() 
	{
		if(VERTEX==null) return 0;
		int ret = VERTEX.size() * 16;
		if(ret % 32 != 0)
		{
			ret = ((ret / 32) + 1) * 32;
		}
		return ret;
	}
	public int getHeaderDataSize() 
	{
		if (header == null) return 32;
		return header.getSize();
	}
	public void replaceFromOBJ(List<String> lines, int ref, int vertexOffset, int value) 
	{
		tree = new CollisionTree(this);
		VERTEX = new ArrayList<CollisionVertex>();
		for(String line : lines)
		{
			if(line.charAt(0)=='o')
			{
				//If it is an object line
			}
			else if(line.charAt(0)=='v'&&line.charAt(1)==' ')
			{
				addVertexLine(line);
			}
			if(line.charAt(0)=='f')
			{
				addFaceLine(line, vertexOffset);
			}
		}
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public boolean equals(String name) 
	{
		return this.name.equals(name);
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		//Return the size of the Collision Object
		return 160;
	}
	private class CollisionHeader implements Data
	{
		private int amount = -1;
		private int pos = -1;
		public CollisionHeader(ByteBuffer data, int headerPos) 
		{
			if (headerPos == 0)
			{
				amount = 0;
				pos = 0;
			}
			amount = data.getInt(headerPos)/2;
			pos = data.getInt(headerPos + 4);
		}
		public CollisionHeader(int referenceValue) 
		{
			if(referenceValue > 0)
			{
				amount = 1;
				pos = 0;
			}
			else 
			{
				amount = 0;
				pos = 0;
			}
		}
		public boolean equals(String name) 
		{
			throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
		}
		public int getListAmount() 
		{
			return amount;
		}
		public void setListPos(int num) 
		{
			pos = num;
		}
		public int getListPos() 
		{
			return pos;
		}
		public void setData(byte[] data) 
		{
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public byte[] toBytes()
		{
			ByteBuffer ret = ByteBuffer.allocate(32);
			ret.putInt(0, amount * 2);
			ret.putInt(4, pos);
			return ret.array();
		}
		public void setName(String name) 
		{
			throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
		}
		public String getName() 
		{
			throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
		}
		public int getSize() 
		{
			return 32;
		}
	}
	public void setXOffset(float num) 
	{
		xPos = num;
	}
	public void setYOffset(float num) 
	{
		yPos = num;
	}
	public void setZOffset(float num) 
	{
		zPos = num;
	}
	public String toOBJ() 
	{
		String ret = "LKS Collision File \n";
		ret += "o "+ name + "\n";
		if(VERTEX!=null)
		{
			for(int i = 0; i < VERTEX.size();i++)
			{
				ret = ret + VERTEX.get(i).toString();
			}
		}
		ret = ret + tree.toString(1);
		return ret;
	}
	public byte[] toOBJBytes() 
	{
		return Utils.encodeStringToBytes(toOBJ());
	}
	public void addVertexLine(String line) 
	{
		VERTEX.add(new CollisionVertex(line));
	}
	public void addFaceLine(String line, int vertexCount) 
	{
		bFM.Utils.DebugPrint("Adding Face: " + line);
		CollisionFace face = tree.addFaceLine(line, vertexCount);
		boundingBoxMinX = Math.min(boundingBoxMinX, getMinX(face));
		boundingBoxMinY = Math.min(boundingBoxMinY, getMinY(face));
		boundingBoxMinZ = Math.min(boundingBoxMinZ, getMinZ(face));
		boundingBoxMaxX = Math.max(boundingBoxMaxX, getMaxX(face));
		boundingBoxMaxY = Math.max(boundingBoxMaxY, getMaxY(face));
		boundingBoxMaxZ = Math.max(boundingBoxMaxZ, getMaxZ(face));
	}
}
