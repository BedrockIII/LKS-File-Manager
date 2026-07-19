package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

import bFM.Utils;

public class MobRes 
{
	String location = ""; //First 32 Bytes
	String name = ""; //Next 32 Bytes
	int code = -1; //Next 2 Bytes
	int aiCode = -1; //Next 2 Bytes
	int memSize = 0; //Next 2 Bytes
	int num4 = 0; //Next 2 Bytes
	int num5 = -1; //Next 2 Bytes
	int num6 = 0; //Next 2 Bytes
	float soundSizeOrVolume1 = 0; //Next 4 Bytes
	float soundSizeOrVolume2 = 0; //Next 4 Bytes
	float num7 = 0;
	String soundType = ""; //Next 16 Bytes
	public MobRes(byte[] data) 
	{
		location = Utils.decodeBytesToString(Utils.removeEmptySpace(Arrays.copyOfRange(data, 0, 32)));
		name = Utils.decodeBytesToString(Utils.removeEmptySpace(Arrays.copyOfRange(data, 32, 64)));
		code = Utils.getShort(data, 64);
		aiCode = Utils.getShort(data, 66);
		memSize = Utils.getShort(data, 68);
		num4 = Utils.getShort(data, 70);
		num5 = Utils.getShort(data, 72);
		num6 = Utils.getShort(data, 74);
		soundSizeOrVolume1 = ByteBuffer.wrap(data).getFloat(76);
		soundSizeOrVolume2 = ByteBuffer.wrap(data).getFloat(80);
		num7 = ByteBuffer.wrap(data).getFloat(84);
		soundType = Utils.decodeBytesToString(Utils.removeEmptySpace(Arrays.copyOfRange(data, 88, 104)));
	}
	public byte[] toBytes()
	{
		byte[] ret = Utils.encodeStringToBytes(location);
		byte[] ret1 = new byte[32]; 
		for(int i = 0; i<ret.length&&i<32;i++)
		{
			ret1[i] = ret[i];
		}
		ret = ret1;
		try 
		{
			ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes(name, Charset.forName("Shift-JIS")));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		ret1 = new byte[64]; 
		for(int i = 0; i<ret.length&&i<64;i++)
		{
			ret1[i] = ret[i];
		}
		ret = ret1;
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) code).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) aiCode).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) memSize).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num4).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num5).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num6).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(soundSizeOrVolume1).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(soundSizeOrVolume2).array());
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num7).array());
		ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes(soundType));
		ret1 = new byte[104]; 
		for(int i = 0; i<ret.length&&i<104;i++)
		{
			ret1[i] = ret[i];
		}
		ret = ret1;
		return ret;
	}
	public MobRes() 
	{
	}
	public String toCSV()
	{
		return "Monster Resource " + code +": \"" +location + "\", \"" +name + "\", " +aiCode + ", " +memSize + ", " +num4 + ", " +num5 + ", " +num6 + ", " +soundSizeOrVolume1+ ", " +soundSizeOrVolume2+ ", " + num7 + ", \"" +soundType+"\"\n";
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<Model Resource ID>>") != -1) code = Utils.formatFlag(line);
		if(line.indexOf("<<Debug Name>>") != -1) name = Utils.formatString(line);
		if(line.indexOf("<<File Path>>") != -1) location = Utils.formatString(line);
		if(line.indexOf("<<Res Num 1>>") != -1) aiCode = Utils.formatFlag(line);
		if(line.indexOf("<<Memory>>") != -1) memSize = Utils.formatFlag(line);
		if(line.indexOf("<<Res Num 3>>") != -1) num4 = Utils.formatFlag(line);
		if(line.indexOf("<<Res Num 4>>") != -1) num5 = Utils.formatFlag(line);
		if(line.indexOf("<<Res Num 5>>") != -1) num6 = Utils.formatFlag(line);
		if(line.indexOf("<<Res Num 6>>") != -1) soundSizeOrVolume1 = Utils.formatFloat(line);
		if(line.indexOf("<<Res Num 7>>") != -1) soundSizeOrVolume2 = Utils.formatFloat(line);
		if(line.indexOf("<<Res Num 8>>") != -1) num7 = Utils.formatFloat(line);
		if(line.indexOf("<<Sound Effect>>") != -1) soundType = Utils.formatString(line);
		//if(line.indexOf("<<>>") != -1)  = Utils.formatFlag(line);
	}
	public String toBMos()
	{
		String ret = "";
		if(code == -1) throw new IllegalArgumentException("Model Resource ID is invalid");
		if(code != -1) ret += "<<Model Resource ID>> " + code + "\n";
		if(name.equals("") == false) ret += "\t<<Debug Name>> \"" + name + "\"\n";
		if(location.equals("") == false) ret += "\t<<File Path>> \"" + location + "\"\n";
		if(aiCode != -1) ret += "\t<<Res Num 1>> " + aiCode + "\n";
		if(memSize != 0) ret += "\t<<Memory>> " + memSize + "\n";
		if(num4 != 0) ret += "\t<<Res Num 3>> " + num4 + "\n";
		if(num5 != -1) ret += "\t<<Res Num 4>> " + num5 + "\n";
		if(num6 != 0) ret += "\t<<Res Num 5>> " + num6 + "\n";
		if(soundSizeOrVolume1 != 0) ret += "\t<<Res Num 6>> " + soundSizeOrVolume1 + "\n";
		if(soundSizeOrVolume2 != 0) ret += "\t<<Res Num 7>> " + soundSizeOrVolume2 + "\n";
		if(num7 != 0) ret += "\t<<Res Num 8>> " + num7 + "\n";
		if(soundType.equals("") == false) ret += "\t<<Sound Effect>> \"" + soundType + "\"\n";
		return ret;
		//if( != -1) ret += "<<\t>> " +  + "\n";
	}
}
