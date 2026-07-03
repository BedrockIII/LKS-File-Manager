package SystemDataManagers.MenuDB.KingdomPlanManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.GenericFile;
import bFM.Utils;

public class kingdomPlanManager extends GenericFile
{
	ArrayList<KingdomPlanArea> Areas = new ArrayList<KingdomPlanArea>();
	PCKGManager KingdomPlan = new PCKGManager("");
	public kingdomPlanManager(byte[] data)
	{
		name = "KingdomPlan.bin";
		KingdomPlan = new PCKGManager(data);
		decodeData();
	}
	public void replaceFromBytes(byte[] data)
	{
		name = "KingdomPlan.bin";
		KingdomPlan = new PCKGManager(data);
		decodeData();
	}
	public kingdomPlanManager(List<String> lines)
	{
		name = "KingdomPlan.bin";
		initializeFromLines(lines);
	}
	private void initializeFromLines(List<String> lines) 
	{
		KingdomPlanArea lastArea = null;
		KingdomPlanElement lastElement = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Area Name>>")!=-1)
			{
				lastArea = new KingdomPlanArea(line);
				Areas.add(lastArea);
			}
			else if(line.indexOf("<<Area ")!=-1)
			{
				lastArea.addLine(line);
			}
			else if(line.indexOf("<<Element Name>>")!=-1)
			{
				lastElement = new KingdomPlanElement(line, Areas.size()-1);
				lastArea.addElement(lastElement);
			}
			else if(line.indexOf("<<")!=-1 && line.indexOf(">>")!=-1)
			{
				lastElement.addLine(line);
			}
		}
	}
	public void replaceFromLines(List<String> lines)
	{
		Areas.removeAll(Areas);
		initializeFromLines(lines);
	}
	private void decodeData()
	{
		ArrayList<String> AreaNames = bFM.Utils.extractStringsNoFormatting(KingdomPlan.getFile("ListName"));
		ArrayList<String> AreaDescriptions = bFM.Utils.extractStringsNoFormatting(KingdomPlan.getFile("ListText"));
		ArrayList<String> AreaImages = bFM.Utils.extractStringsNoFormatting(KingdomPlan.getFile("ListImage"));
		ArrayList<String> ElemNames = bFM.Utils.extractStringsNoFormatting(KingdomPlan.getFile("ElemName"));
		ArrayList<String> ElemDescriptions = bFM.Utils.extractStringsNoFormatting(KingdomPlan.getFile("ElemText"));
		ArrayList<String> ElemImages = bFM.Utils.extractStringsNoFormatting(KingdomPlan.getFile("ElemImage"));
		ArrayList<int[]> ElemFlags = extractFlags(KingdomPlan.getFile("Val"));
		int AreaSize = Math.min(AreaNames.size(), AreaDescriptions.size());
		AreaSize = Math.min(AreaSize, AreaImages.size());
		if(AreaNames.size()!=AreaDescriptions.size()||AreaNames.size()!=AreaImages.size())
		{
			bFM.Utils.DebugPrint("Area Data is Incosistantly Sized");
		}
		for(int i = 0; i < AreaSize; i++)
		{
			Areas.add(new KingdomPlanArea(AreaNames.get(i), AreaDescriptions.get(i), AreaImages.get(i)));
		}
		int ElemSize = Math.min(ElemNames.size(), ElemDescriptions.size());
		ElemSize = Math.min(ElemSize, ElemImages.size());
		ElemSize = Math.min(ElemSize, ElemFlags.size());
		if(ElemSize!=ElemNames.size()||ElemSize!=ElemDescriptions.size()||
				ElemSize!=ElemImages.size()||ElemSize!=ElemFlags.size())
		{
			bFM.Utils.DebugPrint("Element Data is Incosistantly Sized");
		}
		
		for(int i = 0; i < ElemSize; i++)
		{
			int AreaIndex = ElemFlags.get(i)[0];
			if(AreaIndex>=0&&AreaIndex<Areas.size())
			{
				Areas.get(AreaIndex).addElement(new KingdomPlanElement(ElemNames.get(i), ElemDescriptions.get(i), ElemImages.get(i), ElemFlags.get(i)));
			}
			else 
			{
				bFM.Utils.DebugPrint("Element has an invalid Area Index");
			}
		}
	}
	private ArrayList<int[]> extractFlags(byte[] data) 
	{
		ArrayList<int[]> Flags = new ArrayList<int[]>();
		for(int i = 0; i<data.length; i+=64)
		{
			byte[] flagData = new byte[64];
			System.arraycopy(data,i,flagData,0,64);
			int[] flags = new int[16];
			for(int j = 0; j < 64; j+=4)
			{
				flags[j/4] = ByteBuffer.wrap(flagData).getInt(j);
			}
			Flags.add(flags);
		}
		return Flags;
	}
	private byte[] flagBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Areas.get(i).getElementFlags());
		}
		return ret;
	}
	private byte[] elemNameBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Areas.get(i).getElementNames());
		}
		return ret;
	}
	private byte[] elemDescriptionBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Areas.get(i).getElementDescriptions());
		}
		return ret;
	}
	private byte[] elemImageBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Areas.get(i).getElementImages());
		}
		return ret;
	}
	private byte[] listImageBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes(Areas.get(i).getImage()), new byte[1]));
		}
		return ret;
	}
	private byte[] listDescriptionBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes(Areas.get(i).getDescription()), new byte[1]));
		}
		return ret;
	}
	private byte[] listNameBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Utils.encodeStringToBytes(Areas.get(i).getName()), new byte[1]));
		}
		return ret;
	}
	public byte[] toBytes() 
	{
		KingdomPlan = new PCKGManager("KingdomPlan.bin");
		KingdomPlan.addFile("ElemName", elemNameBytes());
		KingdomPlan.addFile("ElemText", elemDescriptionBytes());
		KingdomPlan.addFile("ElemImage", elemImageBytes());
		KingdomPlan.addFile("ListName", listNameBytes());
		KingdomPlan.addFile("ListText", listDescriptionBytes());
		KingdomPlan.addFile("ListImage", listImageBytes());
		KingdomPlan.addFile("Val", flagBytes());
		return KingdomPlan.getFile();
	}
	public String toString()
	{
		String ret = "";
		for(int i = 0; i<Areas.size(); i++)
		{
			ret+=Areas.get(i).toString();
		}
		return ret;
	}
	public ArrayList<KingdomPlanArea> getAreas()
	{
		return Areas;
	}
	public void setData(byte[] data)
	{
		KingdomPlan = new PCKGManager(data);
	}
	public String getName()
	{
		return "KingdomPlan.bin";
	}
	public void setName(String name) 
	{
		this.name = name;
	}
}
