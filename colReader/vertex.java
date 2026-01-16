package colReader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class vertex {
	float x;
	float y;
	float z;
	public vertex(float x, float y, float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public vertex(String line) 
	{
		line = line.substring(2);
		String A = line.substring(0, line.indexOf(' '));
		line = line.substring(line.indexOf(' ')+1);
		String B = line.substring(0, line.indexOf(' '));
		line = line.substring(line.indexOf(' ')+1);
		String C = line;
		x = (float)(Float.parseFloat(A)*1);
		y = (float)(Float.parseFloat(B)*1);
		z = (float)(Float.parseFloat(C)*1);
		if(y>-.001&&y<.001)y=0;
	}
	private void roundY()
	{
		y = ((float)((int)(y*100+.5)))/100;
	}
	public vertex(byte[] bytes)
	{
		
		byte[] xArr = {bytes[0],bytes[1],bytes[2],bytes[3]};
		x = ByteBuffer.wrap(xArr).order(ByteOrder.BIG_ENDIAN).getFloat();
		byte[] yArr = {bytes[4],bytes[5],bytes[6],bytes[7]};
		y = ByteBuffer.wrap(yArr).order(ByteOrder.BIG_ENDIAN).getFloat();
		byte[] zArr = {bytes[8],bytes[9],bytes[10],bytes[11]};
		z = ByteBuffer.wrap(zArr).order(ByteOrder.BIG_ENDIAN).getFloat();
	}
	public String toString()
	{
		return "v " + x + " " + y + " " + z + " \n";
	}
	public byte[] getBytes()
	{
		roundY();
		return bFM.Utils.mergeArrays(bFM.Utils.mergeArrays(ByteBuffer.allocate(4).putFloat(x).array(), ByteBuffer.allocate(4).putFloat(y).array()), bFM.Utils.mergeArrays(ByteBuffer.allocate(4).putFloat(z).array(), new byte[4]));
	}
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return y;
	}
	public float getZ()
	{
		return z;
	}
	public boolean equals(vertex v)
	{
		if(x == v.getX())
		{
			if(y == v.getY())
			{
				if(z == v.getZ())
				{
					System.out.print(this);
					return true;
				}
			}
		}
		return false;
	}
}