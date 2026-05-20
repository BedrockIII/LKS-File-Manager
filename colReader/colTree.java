package colReader;

import java.nio.ByteBuffer;
import java.util.ArrayList;

class colTree 
{
	final public static int maxTreeSize = 32;
	float boundingBoxXMin;
	float boundingBoxZMin;
	float boundingBoxXMax;
	float boundingBoxZMax;
	int treeRefVal1;
	int treeRefVal2;
	int treeRefVal3;
	int treeRefVal4;
	int indexAmount;
	int indexPos;
	face[] faces = new face[0];
	byte[] indexData;
	int indexSize;
	ArrayList<colTree> trees = new ArrayList<colTree>();
	public colTree(float x1, float z1, float x2, float z2, int t1, int t2, int t3, int t4)
	{
		boundingBoxXMin = x1;
		boundingBoxZMin = z1;
		boundingBoxXMax = x2;
		boundingBoxZMax = z2;
		treeRefVal1 = t1;
		treeRefVal2 = t2;
		treeRefVal3 = t3;
		treeRefVal4 = t4;
		indexAmount = 0;
		indexPos = 0;
	}
	public colTree(int offset, ArrayList<face> faces, ArrayList<vertex> VERTEX)
	{
		System.out.println(faces.size());
		System.out.println(VERTEX.size());
		float x1 = Float.MAX_VALUE;
		for(int i = 0; i<VERTEX.size();i++)
		{
			//if(i>=VERTEX.size()) x1 = 32;
			if(x1>VERTEX.get(i).getX()) x1 = VERTEX.get(i).getX();
		}
		//.faces.(x1 == Float.MAX_VALUE) x1 = 
		float x2 = Float.MIN_VALUE;
		for(int i = 0; i<VERTEX.size();i++)
		{
			//if(i>=VERTEX.size()) x2 = (float) -32.0;
			if(x2<VERTEX.get(i).getX()) x2 = VERTEX.get(i).getX();
		}
		float z1 = Float.MAX_VALUE;
		for(int i = 0; i<VERTEX.size();i++)
		{
			//if(i>=VERTEX.size()) z1 = 32;
			if(z1>VERTEX.get(i).getZ()) z1 = VERTEX.get(i).getZ();
		}
		float z2 = Float.MIN_VALUE;
		for(int i = 0; i<VERTEX.size();i++)
		{
			//if(i>=VERTEX.size()) z2 = (float) -32.0;
			if(z2<VERTEX.get(i).getZ()) z2 = VERTEX.get(i).getZ();
		}
		boundingBoxXMin = x1;
		boundingBoxXMax = x2;
		boundingBoxZMin = z1;
		boundingBoxZMax = z2;
		treeRefVal1 = 0;
		treeRefVal2 = 0;
		treeRefVal3 = 0;
		treeRefVal4 = 0;
		indexAmount = faces.size();
		indexPos = 0;
		this.faces=new face[faces.size()];
		for(int i = 0; i<faces.size();i++)
		{
			this.faces[i] = faces.get(i);
		}
	}
	public colTree(ByteBuffer data) 
	{
		boundingBoxXMin = data.getFloat();
		boundingBoxXMax = data.getFloat();
		boundingBoxZMin = data.getFloat();
		boundingBoxZMax = data.getFloat();
		treeRefVal1 = data.getInt();
		treeRefVal2 = data.getInt();
		treeRefVal3 = data.getInt();
		treeRefVal4 = data.getInt();
		indexAmount = data.getInt();
		indexPos = data.getInt();
		if(indexAmount > 0 && indexPos > 0) makeIndex(data);
	}
	public byte[] getBytes()
	{
		if(indexAmount==0)indexPos=0;
		byte[] ret = ByteBuffer.allocate(4).putFloat(boundingBoxXMin).array();
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxXMax).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxZMin).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(boundingBoxZMax).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(treeRefVal1).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(treeRefVal2).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(treeRefVal3).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(treeRefVal4).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(indexAmount).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(indexPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		return ret;
	}
	public boolean hasFaces()
	{
		return treeRefVal1!=0||treeRefVal2!=0||treeRefVal3!=0||treeRefVal4!=0;
	}
	public void updateTree(int indexAmount, int indexPos)
	{
		
		this.indexAmount = indexAmount;
		this.indexPos = indexPos;
		if(getMaxIndex()==-1) indexPos = 0;
	}
	public void updateTree(int indexPos)
	{
		this.indexPos = indexPos;
	}
	private void makeIndex(ByteBuffer data) 
	{
		faces = new face[indexAmount];
		data.position(indexPos);
		for(int i = 0; i<indexAmount; i++)
		{
			faces[i] = new face(data);
		}
	}
	public int getIndexDataSize()
	{
		int ret = indexAmount * 6;
		if(ret % 32 != 0)
		{
			ret = ((ret / 32) + 1) * 32;
		}
		return ret;
	}
	public int getIndexSize()
	{
		return indexSize;
	}
 	public int getFaceAmount()
 	{
 		return faces.length;
 	}
	public byte[] getIndex()
	{
		if(faces!=null)
		{
			byte[] ret = new byte[0];
			for(int i = 0; i<faces.length; i++)
			{
				ret = bFM.Utils.mergeArrays(ret, faces[i].toBytes());
			}	
			int rem = ret.length%32;
			if(rem!=0) rem = 32-rem;
			indexData =  bFM.Utils.mergeArrays(ret, new byte[rem]);
			indexSize = indexData.length;
		}
		return indexData;
	}
	public int getMaxIndex()
	{
		int ret = -1;
		if(ret<treeRefVal1) ret = treeRefVal1;
		if(ret<treeRefVal2) ret = treeRefVal2;
		if(ret<treeRefVal3) ret = treeRefVal3;
		if(ret<treeRefVal4) ret = treeRefVal4;
		return ret;
	}
	public String toString(int offset)
	{
		String ret = "";
		//System.out.println(faces.length);
		for(int i = 0; i<faces.length;i++)
		ret = ret + faces[i].toString(offset);
		return ret;
	}
	public String toString(String name, int offset)
	{
		String ret = "o " +name + "\n";
		for(int i = 0; i<faces.length;i++)
		ret = ret + faces[i].toString();
		return ret;
	}
	public void setIndexPos(int indexPos) 
	{
		this.indexPos = indexPos;
	}
}