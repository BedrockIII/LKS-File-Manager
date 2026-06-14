package colReader;

import java.nio.ByteBuffer;
import bFM.Data;

class CollisionFace implements Data
{
	public short v1;
	public short v2;
	public short v3;
	int offset;
	public CollisionFace(short v1, short v2, short v3) 
	{
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	public CollisionFace(ByteBuffer data) 
	{
		v1 = data.getShort();
		v2 = data.getShort();
		v3 = data.getShort();
	}
	public CollisionFace(String line, int vertexOffset) 
	{
		offset = vertexOffset;
		//System.out.println(vertexOffset);
		if(line.indexOf('/')!=-1)
		{
			String A = line.substring(2,line.indexOf('/'));
			line = line.substring(line.indexOf('/'));
			line = line.substring(line.indexOf(' ')+1);
			String B = line.substring(0,line.indexOf('/'));
			line = line.substring(line.indexOf('/'));
			line = line.substring(line.indexOf(' ')+1);
			String C = line.substring(0,line.indexOf('/'));
			v1 = (short)(Integer.parseInt(A)-offset);
			v2 = (short)(Integer.parseInt(B)-offset);
			v3 = (short)(Integer.parseInt(C)-offset);
		}
		else
		{
			line = line.substring(2);
			String A = line.substring(0,line.indexOf(' '));
			line = line.substring(line.indexOf(' ')+1);
			String B = line.substring(0,line.indexOf(' '));
			line = line.substring(line.indexOf(' ')+1);
			String C = "";
			if(line.indexOf('\n')!=-1) C = line.substring(0,line.indexOf('\n'));
			else if(line.indexOf(' ')!=-1) C = line.substring(0,line.indexOf(' '));
			else C = line;
			v1 = (short)(Integer.parseInt(A)-1-offset);
			v2 = (short)(Integer.parseInt(B)-1-offset);
			v3 = (short)(Integer.parseInt(C)-1-offset);
		}
		bFM.Utils.DebugPrint("Created Face: " + v1 + ", "+ v2 + ", "+ v3);
	}
	public String toString(int offset)
	{
		//System.out.println(offset);
		return "f " + (v1+offset) + " " + (v2+offset) + " " + (v3+offset) + "\n";
	}
	public byte[] toBytes()
	{
		//orderIndex
		byte[] ret = ColReader.longToBytes(v1, 2);
		ret = bFM.Utils.mergeArrays(ret, ColReader.longToBytes(v2, 2));
		ret = bFM.Utils.mergeArrays(ret, ColReader.longToBytes(v3, 2));
		return ret;
	}
	public int[] getVerts() 
	{
		return new int[]{(int)v1,(int)v2,(int)v3};
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] bytes) 
	{
		ByteBuffer data = ByteBuffer.wrap(bytes);
		v1 = data.getShort();
		v2 = data.getShort();
		v3 = data.getShort();
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
		return 6;
	}
}
