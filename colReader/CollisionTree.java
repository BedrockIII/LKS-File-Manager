package colReader;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import bFM.Data;

class CollisionTree implements Data
{
	CollisionObject parent = null;
	final public static int maxTreeSize = 32;
	float boundingBoxXMin;
	float boundingBoxZMin;
	float boundingBoxXMax;
	float boundingBoxZMax;
	int treeRefVal1;
	int treeRefVal2;
	int treeRefVal3;
	int treeRefVal4;
	CollisionTree[] forest = new CollisionTree[4];
	//int indexAmount;
	int indexPos;
	ArrayList<CollisionFace> faces = new ArrayList<CollisionFace>();
	byte[] indexData;
	int indexSize;
	ArrayList<CollisionTree> trees = new ArrayList<CollisionTree>();
	public CollisionTree(ByteBuffer data, int position, int refVal) 
	{
		if(refVal < 0) throw new IllegalArgumentException("Tree Depth Cannot be Negative. Depth: " + refVal);
		int startPos = position + refVal * 48;
		data.position(startPos);
		bFM.Utils.DebugPrint("Tree Starting at: " + startPos + " with Depth: " + refVal);
		boundingBoxXMin = data.getFloat(startPos);
		boundingBoxXMax = data.getFloat(startPos + 4);
		boundingBoxZMin = data.getFloat(startPos + 8);
		boundingBoxZMax = data.getFloat(startPos + 12);
		bFM.Utils.DebugPrint("Checking for SubTree1 At: " + data.position());
		treeRefVal1 = data.getInt(startPos + 16);
		bFM.Utils.DebugPrint("Checking for SubTree2 At: " + data.position());
		treeRefVal2 = data.getInt(startPos + 20);
		bFM.Utils.DebugPrint("Checking for SubTree3 At: " + data.position());
		treeRefVal3 = data.getInt(startPos + 24);
		bFM.Utils.DebugPrint("Checking for SubTree4 At: " + data.position());
		treeRefVal4 = data.getInt(startPos + 28);
		if(treeRefVal1!=0)
		{
			forest[0] = new CollisionTree(data, position, treeRefVal1);
		}
		else
		{
			bFM.Utils.DebugPrint("Checking for SubTree1 Not Found");
			forest[0] = null;
		}
		if(treeRefVal2!=0)
		{
			forest[1] = new CollisionTree(data, position, treeRefVal2);
		}
		else
		{
			bFM.Utils.DebugPrint("Checking for SubTree2 Not Found");
			forest[1] = null;
		}
		if(treeRefVal3!=0)
		{
			forest[2] = new CollisionTree(data, position, treeRefVal3);
		}
		else
		{
			bFM.Utils.DebugPrint("Checking for SubTree3 Not Found");
			forest[2] = null;
		}
		if(treeRefVal4!=0)
		{
			forest[3] = new CollisionTree(data, position, treeRefVal4);
		}
		else
		{
			bFM.Utils.DebugPrint("Checking for SubTree4 Not Found");
			forest[3] = null;
		}
		int indexAmount = data.getInt(startPos + 32);
		indexPos = data.getInt(startPos + 36);
		if(indexAmount > 0 && indexPos > 0) makeIndex(data, indexAmount);
	}
	public CollisionTree(CollisionObject collisionObject) 
	{
		parent = collisionObject;
		boundingBoxXMin = Float.MAX_VALUE;
		boundingBoxZMin = -Float.MAX_VALUE;
		boundingBoxXMax = Float.MAX_VALUE;
		boundingBoxZMax = -Float.MAX_VALUE;
		treeRefVal1 = 0;
		treeRefVal2 = 0;
		treeRefVal3 = 0;
		treeRefVal4 = 0;
	}
	public boolean hasFaces()
	{
		return treeRefVal1!=0||treeRefVal2!=0||treeRefVal3!=0||treeRefVal4!=0;
	}
	public void updateTree(int indexAmount, int indexPos)
	{
		
		//this.indexAmount = indexAmount;
		this.indexPos = indexPos;
		if(getMaxIndex()==-1) indexPos = 0;
	}
	public void updateTree(int indexPos)
	{
		this.indexPos = indexPos;
	}
	private void makeIndex(ByteBuffer data, int size) 
	{
		data.position(indexPos);
		for(int i = 0; i<size; i++)
		{
			faces.add(new CollisionFace(data));
		}
	}
	public int getIndexDataSize()
	{
		int ret = faces.size() * 6;
		if(ret % 32 != 0)
		{
			ret = ((ret / 32) + 1) * 32;
		}
		return ret;
	}
 	public int getFaceAmount()
 	{
 		int ret = faces.size();
 		for(CollisionTree tree : forest)
		{
			if(tree != null)
			{
				ret += tree.getFaceAmount();
			}
		}
 		return ret;
 	}
	public byte[] getIndex()
	{
		if(faces!=null && faces.size() > 0)
		{
			byte[] ret = new byte[0];
			for(int i = 0; i<faces.size(); i++)
			{
				ret = bFM.Utils.mergeArrays(ret, faces.get(i).toBytes());
			}	
			int rem = getIndexSize() - ret.length;
			if(rem<0) throw new IllegalArgumentException("Collision Index actual size is larger than the expected size\n");
			indexData =  bFM.Utils.mergeArrays(ret, new byte[rem]);
			return indexData;
		}
		byte[] ret = new byte[0];
		for(CollisionTree tree : forest)
		{
			if(tree != null) ret = bFM.Utils.mergeArrays(ret, tree.getIndex());
		}
		return ret;
	}
	public int getIndexSize()
	{
		if (faces==null || faces.size() <= 0) return 0;
		int ret = faces.size() * 6;
		ret = ret / 32;
		ret = ret + 1;
		ret = ret * 32;
		//Round up to nearest 32
		return ret;
	}
	public int getMaxIndex()
	{
		int ret = 0;
		for(CollisionTree tree : forest)
		{
			if(tree != null)
			{
				int maxIndex = tree.getMaxIndex();
				if(maxIndex > ret)
				{
					ret = maxIndex;
				}
			}
		}
		return ret;
	}
	public String toString(int offset)
	{
		//Return Tree as Wavefront OBJ face data
		String ret = "";
		for(int i = 0; i<faces.size();i++)
		{
			ret = ret + faces.get(i).toString(offset);
		}
		offset += faces.size();
		for(CollisionTree tree : forest)
		{
			if(tree != null) 
			{
				ret += tree.toString(offset);
				offset += tree.faces.size();
			}
		}
		return ret;
	}
	public String toString(String name, int offset)
	{
		String ret = "o " +name + "\n";
		ret += this.toString(offset);
		return ret;
	}
	public void setIndexPos(int indexPos) 
	{
		this.indexPos = indexPos;
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		if(faces.size()==0)indexPos=0;
		ByteBuffer ret = ByteBuffer.allocate(48);
		ret.putFloat(boundingBoxXMin);
		ret.putFloat(boundingBoxXMax);
		ret.putFloat(boundingBoxZMin);
		ret.putFloat(boundingBoxZMax);
		ret.putInt(treeRefVal1);
		ret.putInt(treeRefVal2);
		ret.putInt(treeRefVal3);
		ret.putInt(treeRefVal4);
		ret.putInt(faces.size());
		ret.putInt(indexPos);
		byte[] retArr = ret.array();
		for(CollisionTree tree : forest)
		{
			if(tree != null) retArr = bFM.Utils.mergeArrays(retArr, tree.toBytes());
		}
		return retArr;
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
		int ret = 48;
		for(CollisionTree tree : forest)
		{
			if(tree != null) ret += tree.getSize();
		}
		return ret;
	}
	public CollisionFace addFaceLine(String line, int vertexCount) 
	{
		if(parent == null) 
		{
			throw(new UnsupportedOperationException("This Shouldn't Happen, addFaceLine(String line, int vertexCount) " + getClass()));
		}
		CollisionFace face = new CollisionFace(line, vertexCount);
		faces.add(face);
		boundingBoxXMin = Math.min(boundingBoxXMin, parent.getMinX(face));
		boundingBoxZMin = Math.min(boundingBoxZMin, parent.getMinZ(face));
		boundingBoxXMax = Math.max(boundingBoxXMax, parent.getMaxX(face));
		boundingBoxZMax = Math.max(boundingBoxZMax, parent.getMaxZ(face));
		
		return face;
	}
}