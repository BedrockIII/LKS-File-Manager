package CameraData;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import PCKGManager.PCKGManager;

public class CameraZoneList 
{
	ArrayList<CameraZone> Zone = new ArrayList<CameraZone>();
	public CameraZoneList(byte[] data, byte[] names)
	{
		ArrayList<String> Names = new ArrayList<String>();
		int startPos = 0;
		for(int i = 0; names!=null&&i<names.length; i++)
		{
			if(names[i]==0x00)
			{
				try 
				{
					Names.add(new String(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(names, startPos, i)), "SHIFT-JIS"));
				} catch (UnsupportedEncodingException e) 
				{
					e.printStackTrace();
				}
				startPos = i+1;
			}
		}
		for(int i = 0; data!=null&&i<data.length; i+=144)
		{
			Zone.add(new CameraZone(Arrays.copyOfRange(data, i, i+144), Names.get(i/144)));
		}
	}
	public CameraZoneList(List<String> lines)
	{
		int zoneLinesSize = -1;
		ArrayList<String> zoneLines = new ArrayList<String>();
		for(int i =1; i<lines.size(); i++)
		{
			if(lines.get(i).length()>1&&lines.get(i).indexOf("<Name>")!=-1)
			{
				zoneLinesSize += 1;
				if(zoneLinesSize<0)zoneLines.add(lines.get(i));
				else if(zoneLines.size()>0)
				{
					Zone.add(new CameraZone(new ArrayList<String>(zoneLines)));
					zoneLines = new ArrayList<String>();
					zoneLinesSize -= 1;
				}
			}
			zoneLines.add(lines.get(i));
		}
		Zone.add(new CameraZone(new ArrayList<String>(zoneLines)));
	}
	public byte[] toPAC()
	{
		PCKGManager CameraData = new PCKGManager("CameraData.bin");
		CameraData.addFile("List", listToBytes());
		CameraData.addFile("Name", nameToBytes());
		return CameraData.getFile();
	}
	private byte[] listToBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i<Zone.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Zone.get(i).getBytes());
		}
		return ret;
	}
	private byte[] nameToBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i<Zone.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Zone.get(i).getNameBytes());
		}
		return ret;
	}
	public String toString()
	{
		String ret = "Bedrock's Camera Zone File\n";
		for(int i = 0; i<Zone.size(); i++)
		{
			ret = ret + Zone.get(i).toString();
		}
		return ret;
	}
}
