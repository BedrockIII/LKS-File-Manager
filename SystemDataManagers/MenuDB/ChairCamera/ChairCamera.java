package SystemDataManagers.MenuDB.ChairCamera;

import java.nio.ByteBuffer;

import bFM.Data;

public class ChairCamera implements Data
	{
		int num1 = -1;
		int num2 = -1;
		int num3 = -1;
		int num4 = -1;
		int num5 = -1;
		int num6 = -1;
		int num7 = -1;
		int num8 = -1;
		int num9 = -1;
		int num10 = -1;
		int num11 = -1;
		int num12 = -1;
		int num13 = -1;
		public ChairCamera(ByteBuffer data)
		{
			num1 = data.getInt();
			num2 = data.getInt();
			num3 = data.getInt();
			num4 = data.getInt();
			num5 = data.getInt();
			num6 = data.getInt();
			num7 = data.getInt();
			num8 = data.getInt();
			num9 = data.getInt();
			num10 = data.getInt();
			num11 = data.getInt();
			num12 = data.getInt();
			num13 = data.getInt();
		}
		public ChairCamera() 
		{
			// Use Defaults
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
			ByteBuffer ret = ByteBuffer.allocate(52);
			ret.putInt(num1);
			ret.putInt(num2);
			ret.putInt(num3);
			ret.putInt(num4);
			ret.putInt(num5);
			ret.putInt(num6);
			ret.putInt(num7);
			ret.putInt(num8);
			ret.putInt(num9);
			ret.putInt(num10);
			ret.putInt(num11);
			ret.putInt(num12);
			ret.putInt(num1);
			return ret.array();
		}
		public int getSize() 
		{
			return 52;
		}
	}