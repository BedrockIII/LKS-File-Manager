 package CommonFiles.Collision;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import CommonFiles.Collision.ColReader.CollisionSize;
import bFM.Data;
import bFM.RotationMatrix;
import bFM.Utils;

public class CollisionObject implements Data
{
	protected static enum CollisionOffsets
	{
		INDEX_POS(32),
		PARENT_INDEX_POS(36),
		COL_ATTRIBUTE_COUNT_POS(40),
		COL_ATTRIBUTE_POINTER_POS(44),
		TRANSFORMATION_MATRIX_POS(48),
		VERTEX_COUNT_POS(112),
		VERTEX_POINTER_POS(116),
		HEADER_COUNT_POS(120),
		HEADER_POINTER_POS(124),
		VAL5_COUNT_POS(128),
		VAL6_POINTER_POS(132),
		X_MAX_POS(136),
		Y_MAX_POS(140),
		Z_MAX_POS(144),
		X_MIN_POS(148),
		Y_MIN_POS(152),
		Z_MIN_POS(156);
		protected final int ofs;
		private CollisionOffsets(int ofs)
		{
			this.ofs = ofs;
		}
	}
	
	ColReader file;
	
	String name;
	CollisionObject parent;
	ArrayList<CollisionMaterial> Attributes = new ArrayList<CollisionMaterial>();
	CollisionHeader Header = new CollisionHeader(-1);
	ArrayList<CollisionVertex> Verticies;
	RotationMatrix position;
	int val5;
	int val6;
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
	public CollisionObject(ColReader file, ByteBuffer data, int offset) 
	{
		this.file = file;
		
		data.position(offset);
		byte chara = data.get();
		name = "";
		while(chara!=0)
		{
			name += (char)chara;
			chara = data.get();
		}
		ColReader.DebugPrint("Collision Object: " + name);
		
		parent = file.getObjectFromIndex(data.getInt(offset + CollisionOffsets.PARENT_INDEX_POS.ofs));
		
		int AttributeCount = data.getInt(offset + CollisionOffsets.COL_ATTRIBUTE_COUNT_POS.ofs);
		int AttributePointer = data.getInt(offset + CollisionOffsets.COL_ATTRIBUTE_POINTER_POS.ofs);
		if(AttributeCount > 0)
		{
			ColReader.DebugPrint("Attribute Count: " + AttributeCount);
			ColReader.DebugPrint("Attribute Offset: " + AttributePointer);
			for(int i = 0; i < AttributeCount; i++)
			{
				ColReader.DebugPrint("Attribute Position: " + i);
				CollisionMaterial Attribute = new CollisionMaterial(file, data, AttributePointer + i * CollisionSize.COL_ATTRIBUTE.size);
				Attributes.add(Attribute);
				file.register(Attribute);
			}
		}
		
		data.position(offset + CollisionOffsets.TRANSFORMATION_MATRIX_POS.ofs);
		position = new RotationMatrix();
		position.initializeFromBytes(data);
		
		int VertexCount = data.getInt(offset + CollisionOffsets.VERTEX_COUNT_POS.ofs);
		int VertexPointer = data.getInt(offset + CollisionOffsets.VERTEX_POINTER_POS.ofs);
		if(VertexCount > 0)
		{
			ColReader.DebugPrint("Vertex Count: " + VertexCount);
			ColReader.DebugPrint("Vertex Offset: " + VertexPointer);
			for(int i = 0; i < VertexCount; i++)
			{
				ColReader.DebugPrint("Vertex Position: " + i);
				CollisionVertex Vertex = new CollisionVertex(file, data, VertexPointer + i * CollisionSize.COL_VERTEX.size);
				Verticies.add(Vertex);
				file.register(Vertex);
			}
		}
		
		Header = new CollisionHeader(file, data, data.getInt(offset + CollisionOffsets.HEADER_POINTER_POS.ofs));
		
		val5 = data.getInt(offset + CollisionOffsets.VAL5_COUNT_POS.ofs);
		val6 = data.getInt(offset + CollisionOffsets.VAL5_COUNT_POS.ofs);
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
	float getMinX(CollisionIndex face)
	{
		float ret = Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.min(Math.min(ret, v1.getX()), Math.min(v2.getX(), v3.getX()));
	}
	float getMaxX(CollisionIndex face)
	{
		float ret = -Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.max(Math.max(ret, v1.getX()), Math.max(v2.getX(), v3.getX()));
	}
	float getMinY(CollisionIndex face)
	{
		float ret = Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.min(Math.min(ret, v1.getY()), Math.min(v2.getY(), v3.getY()));
	}
	float getMaxY(CollisionIndex face)
	{
		float ret = -Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.max(Math.max(ret, v1.getY()), Math.max(v2.getY(), v3.getY()));
	}
	float getMinZ(CollisionIndex face)
	{
		float ret = Float.MAX_VALUE;
		CollisionVertex v1 = VERTEX.get(face.v1);
		CollisionVertex v2 = VERTEX.get(face.v2);
		CollisionVertex v3 = VERTEX.get(face.v3);
		return Math.min(Math.min(ret, v1.getZ()), Math.min(v2.getZ(), v3.getZ()));
	}
	float getMaxZ(CollisionIndex face)
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
		CollisionIndex face = tree.addFaceLine(line, vertexCount);
		boundingBoxMinX = Math.min(boundingBoxMinX, getMinX(face));
		boundingBoxMinY = Math.min(boundingBoxMinY, getMinY(face));
		boundingBoxMinZ = Math.min(boundingBoxMinZ, getMinZ(face));
		boundingBoxMaxX = Math.max(boundingBoxMaxX, getMaxX(face));
		boundingBoxMaxY = Math.max(boundingBoxMaxY, getMaxY(face));
		boundingBoxMaxZ = Math.max(boundingBoxMaxZ, getMaxZ(face));
	}
}
