package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bFM.Utils;

public class MobResAsn 
{
	short startNum1 = 0;//first 2 bytes,always 0001
	short startNum2 = 0;//next 2 bytes amount of things in list
	ArrayList<MobRes> Res = new ArrayList<MobRes>();
	public static final String BmosVersion = "Bedrock's Mission Resource Definition File Version: 1.0";
	public MobResAsn(byte[] data)
	{
		if(data.length<4) return;
		startNum1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort();
		startNum2 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getShort(2);
		for(int i = 4; i<data.length-99; i+=104)
		{
			Res.add(new MobRes(Arrays.copyOfRange(data, i, i+104)));
		}
	}
	public MobResAsn(List<String> lines)
	{
		MobRes lastRes = null;
		for(String line : lines)
		{
			if(line.indexOf("Bedrock's Mission Resource Definition File Version:") != -1 && line.indexOf(BmosVersion)==-1)
			{
				System.err.println("Warning: Wrong File Version");
			}
			else if(line.indexOf("<<Model Resource ID>>") != -1)
			{
				lastRes = new MobRes();
				lastRes.addLine(line);
				Res.add(lastRes);
			}
			else if(lastRes != null)
			{
				lastRes.addLine(line);
			}
		}
	}
	public String toCSV()
	{
		String ret = "Num " + startNum2 + "\n";
		for(int i = 0; i<Res.size(); i++)
		{
			ret = ret + Res.get(i).toCSV();
		}
		return ret;
	}
	public String toBMos()
	{
		String ret = BmosVersion + "\n";
		for(MobRes res : Res)
		{
			ret += res.toBMos();
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = ByteBuffer.allocate(2).putShort((short)1).array();
		ret = Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)Res.size()).array());
		for(MobRes res : Res)
		{
			ret = Utils.mergeArrays(ret, res.toBytes());
		}
		return ret;
	}
}
