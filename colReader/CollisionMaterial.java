package colReader;

import java.nio.ByteBuffer;
import bFM.Data;

class CollisionMaterial implements Data
{
	//With the Debug Mode I have discovered the Use of these, they are for materials
	String name;
	float num1;
	float num2;
	float num3;
	float num4;
	float num5;
	float num6;
	float num7;
	float num8;
	float num9;
	float num10;
	float num11;
	public CollisionMaterial(ByteBuffer data)
	{
		int StartPos = data.position();
		byte chara = data.get();
		name = "";
		while(chara!=0)
		{
			name += (char)chara;
			chara = data.get();
		}
		num1 = data.getFloat(StartPos + 76);
		num2 = data.getFloat(StartPos + 80);
		num3 = data.getFloat(StartPos + 84);
		num4 = data.getFloat(StartPos + 88);
		num5 = data.getFloat(StartPos + 92);
		num6 = data.getFloat(StartPos + 96);
		num7 = data.getFloat(StartPos + 100);
		num8 = data.getFloat(StartPos + 104);
		num9 = data.getFloat(StartPos + 108);
		num10 = data.getFloat(StartPos + 124);
		num11 = data.getFloat(StartPos + 128);
	}
	public CollisionMaterial(String name, int preset)
	{
		this.name = name;
		if(preset==0) 
		{
			this.name = "DMY_1229149135_0";
			num1 = 0;
			num2 = 0;
			num3 = 0;
			num4 = 0;
			num5 = 0;
			num6 = 0;
			num7 = 0;
			num8 = 0;
			num9 = 0;
			num10 = 0;
			num11 = 0;
		} else if(preset==1)
		{
			this.name = "lambert1";
			num1 = 1;
			num2 = (float)0.5;
			num3 = (float)0.5;
			num4 = (float)0.5;
			num5 = 1;
			num6 = 1;
			num7 = 1;
			num8 = 1;
			num9 = 1;
			num10 = 1;
			num11 = 1;
		} else if(preset==2)
		{
			this.name = "col_m";
			num1 = 1;
			num2 = 1;
			num3 = (float)0.708;
			num4 = (float)0.84227127;
			num5 = 1;
			num6 = 1;
			num7 = 1;
			num8 = 1;
			num9 = 1;
			num10 = 1;
			num11 = 1;
		}
		
	}
	public CollisionMaterial()
	{
		name = "object";
		num1 = 1;
		num2 = 1;
		num3 = 1;
		num4 = 1;
		num5 = 1;
		num6 = 1;
		num7 = 1;
		num8 = 1;
		num9 = 1;
		num10 = 1;
		num11 = 1;
	}
	public boolean equals(String name) 
	{
		return this.name.equals(name);
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(160);
		for(int i = 0; i < name.length() && i < 76; i++)
		{
			ret.put((byte) name.charAt(i));
		}
		ret.position(76);
		ret.putFloat(num1);
		ret.putFloat(num2);
		ret.putFloat(num3);
		ret.putFloat(num4);
		ret.putFloat(num5);
		ret.putFloat(num6);
		ret.putFloat(num7);
		ret.putFloat(num8);
		ret.putFloat(num9);
		ret.position(112);
		ret.putFloat(num10);
		ret.putFloat(num11);
		return ret.array();
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getName() 
	{
		return name;
	}
	public int getSize() 
	{
		return 160;
	}

}
