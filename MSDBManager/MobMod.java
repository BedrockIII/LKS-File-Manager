package MSDBManager;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class MobMod 
{
	int modCode;
	int modReplacementCode; //becomes this UMA when activated
	int health;
	int num3;
	int num4;
	int num5;
	int num6;
	int num7;
	int num8;
	int num9;
	int num10;
	int num11;
	int mobCode;
	int num13;
	int num14;
	int num15;
	int num16;
	int num17;
	int num18;
	int num19;
	int num20;
	int num21;
	int num22;
	int num23;
	int num24;
	int num25;
	int num26;
	int num27;
	String name;//32
	public MobMod(byte[] data)
	{
		modCode = bFM.Utils.getShort(data, 0);
		modReplacementCode= bFM.Utils.getShort(data, 2);
		health= bFM.Utils.getShort(data, 4);
		num3 = bFM.Utils.getShort(data, 6);
		num4 = bFM.Utils.getShort(data, 8);
		num5 = bFM.Utils.getShort(data, 10);
		num6 = bFM.Utils.getShort(data, 12);
		num7 = bFM.Utils.getShort(data, 14);
		num8 = bFM.Utils.getShort(data, 16);
		num9 = bFM.Utils.getShort(data, 18);
		num10 = bFM.Utils.getShort(data, 20);
		num11 = bFM.Utils.getShort(data, 22);
		mobCode = bFM.Utils.getShort(data, 24);
		num13 = bFM.Utils.getShort(data, 26);
		num14 = bFM.Utils.getShort(data, 28);
		num15 = bFM.Utils.getShort(data, 30);
		num16 = bFM.Utils.getShort(data, 32);
		num17 = bFM.Utils.getShort(data, 34);
		num18 = bFM.Utils.getShort(data, 36);
		num19 = bFM.Utils.getShort(data, 38);
		num20 = bFM.Utils.getShort(data, 40);
		num21 = bFM.Utils.getShort(data, 42);
		num22 = bFM.Utils.getShort(data, 44);
		num23 = bFM.Utils.getShort(data, 46);
		num24 = bFM.Utils.getShort(data, 48);
		num25 = bFM.Utils.getShort(data, 50);
		num26 = bFM.Utils.getShort(data, 52);
		num27 = bFM.Utils.getShort(data, 54);
		try {
			name = new String(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 56, 88)), "SHIFT-JIS");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String modLine = null;
	public MobMod(String modLine)
	{
		this.modLine = modLine;
		modCode = getNextInt();
		modReplacementCode= getNextInt();
		health= getNextInt();
		num3 = getNextInt();
		num4 = getNextInt();
		num5 = getNextInt();
		num6 = getNextInt();
		num7 = getNextInt();
		num8 = getNextInt();
		num9 = getNextInt();
		num10 = getNextInt();
		num11 = getNextInt();
		mobCode = getNextInt();
		num13 = getNextInt();
		num14 = getNextInt();
		num15 = getNextInt();
		num16 = getNextInt();
		num17 = getNextInt();
		num18 = getNextInt();
		num19 = getNextInt();
		num20 = getNextInt();
		num21 = getNextInt();
		num22 = getNextInt();
		num23 = getNextInt();
		num24 = getNextInt();
		num25 = getNextInt();
		num26 = getNextInt();
		num27 = getNextInt();
		name = bFM.Utils.formatString(this.modLine);
	}
	private int getNextInt()
	{
		if(modLine==null)return -1;
		int startIndex = 0;
		String ret = "";
		String validChars = "1234567890-.";
		for(int i = startIndex; i<modLine.length()&&modLine.charAt(i)!=-1; i++)
		{
			if(validChars.indexOf(modLine.charAt(i))!=-1)
			{
				ret+=modLine.charAt(i);
			}
		}
		startIndex = modLine.indexOf(',');
		modLine = modLine.substring(startIndex+1);
		
		
		return Integer.parseInt(ret);
	}
	public String toString()
	{
		return ""+modCode +", "+modReplacementCode+", "+health+", "+num3 +", "+num4 +", "+num5 +", "+num6 +", "+num7 +", "+num8 +", "+num9 + ", " +
				num10 +", "+num11 +", "+mobCode +", "+num13 +", "+num14 +", "+num15 +", "+num16 +", "+num17 +", "+num18 +", "+num19 +", " +
				num20 +", "+num21 +", "+num22 +", "+num23 +", "+num24 +", "+num25 +", "+num26 +", "+num27 +", \""+name+"\"\n";
	}
	public String toHP()
	{
		return name+", HP:"+health+ "\n";
	}
	public byte[] toBytes()
	{
		byte[] ret = ByteBuffer.allocate(2).putInt(modCode).array();
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(modReplacementCode).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(health).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num3).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num4).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num5).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num6).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num7).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num8).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num9).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num10).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num11).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(mobCode).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num13).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num14).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num15).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num16).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num17).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num18).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num19).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num20).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num21).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num22).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num23).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num24).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num25).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num26).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putInt(num27).array());
		ret = bFM.Utils.mergeArrays(ret, name.getBytes());
		byte[] ret1 = new byte[88]; 
		for(int i = 0; i<ret.length;i++)
		{
			ret1[i] = ret[i];
		}
		ret = ret1;
		return ret;
	}
}
