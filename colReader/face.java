package colReader;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class face 
{
	public short v1;
	public short v2;
	public short v3;
	ArrayList<vertex> vertexs;
	int offset;
	public face(short v1, short v2, short v3) 
	{
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	public face(byte[] arr, ArrayList<vertex> vertexs) 
	{
		this.vertexs = vertexs;
		if(arr.length!=6) 
		{
			System.out.println("Error making face from byte array: Array wrong length!");
			return;
		}
		v1 = (short) (ByteBuffer.wrap(arr).getShort(0));
		v2 = (short) (ByteBuffer.wrap(arr).getShort(2));
		v3 = (short) (ByteBuffer.wrap(arr).getShort(4));
	}
	public face(String line, int vertexOffset, ArrayList<vertex> vertexs) 
	{
		this.vertexs = vertexs;
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
			v1 = (short)(Integer.parseInt(A)-1-offset);
			v2 = (short)(Integer.parseInt(B)-1-offset);
			v3 = (short)(Integer.parseInt(C)-1-offset);
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
	}
	public String toString(int offset)
	{
		//System.out.println(offset);
		return "f " + (v1+offset) + " " + (v2+offset) + " " + (v3+offset) + "\n";
	}
	public face updateV(ArrayList<vertex> v)
	{
		vertexs=v;
		return this;
	}
	public byte[] toBytes()
	{
		//orderIndex
		byte[] ret = ColReader.longToBytes(v1, 2);
		ret = bFM.Utils.mergeArrays(ret, ColReader.longToBytes(v2, 2));
		ret = bFM.Utils.mergeArrays(ret, ColReader.longToBytes(v3, 2));
		return ret;
	}
	public boolean sharesSide(face other)
	{
		int sharedVerts = 0;
		if(vertexs.get(v1)==vertexs.get(other.v1)||vertexs.get(v1)==vertexs.get(other.v2)||vertexs.get(v1)==vertexs.get(other.v3)) sharedVerts++;
		if(vertexs.get(v2)==vertexs.get(other.v1)||vertexs.get(v2)==vertexs.get(other.v2)||vertexs.get(v2)==vertexs.get(other.v3)) sharedVerts++;
		if(vertexs.get(v3)==vertexs.get(other.v1)||vertexs.get(v3)==vertexs.get(other.v2)||vertexs.get(v3)==vertexs.get(other.v3)) sharedVerts++;
		if(sharedVerts!=0)System.out.println("Shared verts: "+sharedVerts);
		return sharedVerts==2;
	}
	public int[] getVerts() 
	{
		return new int[]{(int)v1,(int)v2,(int)v3};
	}
}
