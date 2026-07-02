package CameraData;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import PCKGManager.PCKGManager;
import bFM.OpenedFile;
import bFM.Utils;

public class CameraZoneList implements OpenedFile
{
	ArrayList<CameraZone> Zone = new ArrayList<CameraZone>();
	public CameraZoneList(byte[] data, byte[] names)
	{
		initializeFromBytes(data, names);
	}
	private void initializeFromBytes(byte[] data, byte[] names)
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
		initializeFromLines(lines);
	}
	private void initializeFromLines(List<String> lines)
	{
		CameraZone lastZone = null;;
		for(String line : lines)
		{
			if(line.length()>1&&line.indexOf("<<Name>>")!=-1)
			{
				lastZone = new CameraZone(line);
				Zone.add(lastZone);
			}
			else
			{
				if(lastZone != null) lastZone.addLine(line);
			}
		}
	}
	public CameraZoneList(byte[] file) 
	{
		PCKGManager pac = new PCKGManager(file);
		initializeFromBytes(pac.getFile("List"), pac.getFile("Name"));
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
		String ret = "Bedrock's Camera Zone File v 2.0\n";
		for(int i = 0; i<Zone.size(); i++)
		{
			ret = ret + Zone.get(i).toString();
		}
		return ret;
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
		return toPAC();
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "CameraData.bin";
	}
	public int getSize() 
	{
		return toPAC().length;
	}
	public ArrayList<CameraZone> getZones() 
	{
		return Zone;
	}
	public void replaceFromData(byte[] data) 
	{
		PCKGManager pac = new PCKGManager(data);
		Zone.removeAll(Zone);
		initializeFromBytes(pac.getFile("List"), pac.getFile("Name"));
	}
	public void replaceFromBCZ(byte[] data) 
	{
		List<String> lines = Utils.bytesToStrs(data);
		Zone.removeAll(Zone);
		initializeFromLines(lines);
	}
	public void importFromBCZ(byte[] data) 
	{
		List<String> lines = Utils.bytesToStrs(data);
		initializeFromLines(lines);
	}
	public byte[] toBCZ()
	{
		return toString().getBytes(Charset.forName("Shift-JIS"));
	}
}
