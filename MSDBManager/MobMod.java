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
	int indexOfNameMaybe;
	int num6;
	int num7;
	int num8;
	int num9;
	int num10;
	int num11;
	int mobCode;
	int num13;
	int Flags;// 5th byte from left determines if HP Bar Shows
	int num15;// 10th byte from left disables AI and makes smoke on death
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
		indexOfNameMaybe = bFM.Utils.getShort(data, 10);
		num6 = bFM.Utils.getShort(data, 12);
		num7 = bFM.Utils.getShort(data, 14);
		num8 = bFM.Utils.getShort(data, 16);
		num9 = bFM.Utils.getShort(data, 18);
		num10 = bFM.Utils.getShort(data, 20);
		num11 = bFM.Utils.getShort(data, 22);
		mobCode = bFM.Utils.getShort(data, 24);
		num13 = bFM.Utils.getShort(data, 26);
		Flags = bFM.Utils.getShort(data, 28);
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
		modCode = getInt(0);
		modReplacementCode= getInt(1);
		health= getInt(2);
		num3 = getInt(3);
		num4 = getInt(4);
		indexOfNameMaybe = getInt(5);
		num6 = getInt(6);
		num7 = getInt(7);
		num8 = getInt(8);
		num9 = getInt(9);
		num10 = getInt(10);
		num11 = getInt(11);
		mobCode = getInt(12);
		num13 = getInt(13);
		Flags = getInt(14);
		num15 = getInt(15);
		num16 = getInt(16);
		num17 = getInt(17);
		num18 = getInt(18);
		num19 = getInt(19);
		num20 = getInt(20);
		num21 = getInt(21);
		num22 = getInt(22);
		num23 = getInt(23);
		num24 = getInt(24);
		num25 = getInt(25);
		num26 = getInt(26);
		num27 = getInt(27);
		name = bFM.Utils.formatString(this.modLine);
	}
	private int getInt(int index)
	{
		if(modLine==null)return -1;
		int startIndex = 0;
		for(int i = 0; i<modLine.length(); i++)
		{
			if(startIndex==index)
			{
				startIndex = i;
				break;
			}
			if(modLine.charAt(i)==',')
			{
				startIndex++;
			}
		}
		String ret = "";
		String validChars = "1234567890-.";
		for(int i = startIndex; i<modLine.length()&&modLine.charAt(i)!=','; i++)
		{
			if(validChars.indexOf(modLine.charAt(i))!=-1)
			{
				ret+=modLine.charAt(i);
			}
		}
		
		return Integer.parseInt(ret);
	}
	public String toString()
	{
		return ""+modCode +", "+modReplacementCode+", "+health+", "+num3 +", "+num4 +", "+indexOfNameMaybe +", "+num6 +", "+num7 +", "+num8 +", "+num9 + ", " +
				num10 +", "+num11 +", "+mobCode +", "+num13 +", "+Flags +", "+num15 +", "+num16 +", "+num17 +", "+num18 +", "+num19 +", " +
				num20 +", "+num21 +", "+num22 +", "+num23 +", "+num24 +", "+num25 +", "+num26 +", "+num27 +", \""+name+"\"\n";
	}
	public String toHP()
	{
		return name+", HP:"+health+ "\n";
	}
	public byte[] toBytes()
	{
		byte[] ret = ByteBuffer.allocate(2).putShort((short) modCode).array();
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) modReplacementCode).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) health).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num3).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num4).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) indexOfNameMaybe).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num6).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num7).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num8).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num9).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num10).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num11).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) mobCode).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num13).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) Flags).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num15).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num16).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num17).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num18).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num19).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num20).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num21).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num22).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num23).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num24).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num25).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num26).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num27).array());
		ret = bFM.Utils.mergeArrays(ret, name.getBytes());
		byte[] ret1 = new byte[88]; 
		for(int i = 0; i<ret.length&&i<88;i++)
		{
			ret1[i] = ret[i];
		}
		ret = ret1;
		return ret;
	}
	public boolean showsHP()
	{
		//Easy way to tell if its an enemy/obstical or invulnerable
		return (Flags&16)==0;
	}
	public int getModCode() 
	{
		return modCode;
	}
}
