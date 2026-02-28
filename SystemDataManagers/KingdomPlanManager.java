package SystemDataManagers;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;

public class KingdomPlanManager 
{
	ArrayList<KingdomPlanArea> Areas = new ArrayList<KingdomPlanArea>();
	PCKGManager KingdomPlan = new PCKGManager("");
	public KingdomPlanManager(byte[] data)
	{
		KingdomPlan = new PCKGManager(data);
		decodeData();
	}
	public KingdomPlanManager(List<String> lines)
	{
		boolean inAreaString = false;
		boolean inElemString = false;
		boolean inTitle = false;
		String line = "";
		KingdomPlanElement Element = null;
		for(int i = 0; i<lines.size(); i++)
		{
			if(inAreaString == true)
			{
				//if end
				if(lines.get(i+1).indexOf("<<")!=-1&&lines.get(i+1).indexOf(">>")!=-1)
				{
					if(inTitle)
					{
						line += lines.get(i);
						Areas.add(new KingdomPlanArea(line));
						inTitle = false;
					}
					else
					{
						line += lines.get(i);
						
						Areas.get(Areas.size()-1).addLine(line);
					}
					
					
					inAreaString = false;
					line = "";
				}
				else
				{
					line += lines.get(i) + "\n";
				}
			}
			else if(lines.get(i).indexOf("<<Area Name>>")!=-1)
			{
				
				if((lines.get(i+1).indexOf("<<")==-1)&&lines.get(i+1).indexOf(">>")==-1)//if next line is another object
				{
					line = lines.get(i);
					inTitle = true;
					inAreaString = true;
				}
				else
				{
					inTitle = false;
					inAreaString = false;
					Areas.add(new KingdomPlanArea(lines.get(i)));
				}
			}
			else if(lines.get(i).indexOf("<<Area Description>>")!=-1||lines.get(i).indexOf("<<Area Image>>")!=-1)
			{
				if(i+1!=lines.size()&&(lines.get(i+1).indexOf("<<")!=-1)&&lines.get(i+1).indexOf(">>")!=-1)//if next line isny another object
				{
					Areas.get(Areas.size()-1).addLine(lines.get(i));
				}
				else
				{
					line = lines.get(i)+"\n";
					inAreaString = true;
				}
			}
			else if(inElemString == true)
			{
				//if end
				if((lines.get(i+1).indexOf("<<")!=-1)&&lines.get(i+1).indexOf(">>")!=-1)
				{
					if(inTitle)
					{
						line += lines.get(i);
						Element = new KingdomPlanElement(lines.get(i), Areas.size()-1);
						inTitle = false;
					}
					else
					{
						line += lines.get(i);
						
						Element.addLine(line);
					}
					
					inElemString = false;
					
					line = "";
				}
				else
				{
					line += lines.get(i) + "\n";
				}
			}
			else if(lines.get(i).indexOf("<<Element Name>>")!=-1)
			{
				if(Element!=null)
				{
					Areas.get(Areas.size()-1).addElement(Element);
				}
				if((lines.get(i+1).indexOf("<<")==-1)&&lines.get(i+1).indexOf(">>")==-1)//if next line is another object
				{
					line = lines.get(i);
					inTitle = true;
					inElemString = true;
				}
				else
				{
					inTitle = false;
					inElemString = false;
					Element = new KingdomPlanElement(lines.get(i), Areas.size()-1);
				}
			}
			else if(lines.get(i).indexOf("<<Element Description>>")!=-1||lines.get(i).indexOf("<<Element Image>>")!=-1)
			{
				if((lines.get(i+1).indexOf("<<")!=-1)&&lines.get(i+1).indexOf(">>")!=-1)//if next line is another object
				{
					Element.addLine(lines.get(i));
				}
				else
				{
					line = lines.get(i)+"\n";
					inElemString = true;
				}
				
			}
			else if(lines.get(i).indexOf("<<")!=-1&&lines.get(i).indexOf(">>")!=-1)
			{
				if(Element!=null)
				{
					Element.addLine(lines.get(i));
				}
			}
		}
	}
	private void decodeData()
	{
		ArrayList<String> AreaNames = bFM.Utils.extractStrings(KingdomPlan.getFile("ListName"));
		ArrayList<String> AreaDescriptions = bFM.Utils.extractStrings(KingdomPlan.getFile("ListText"));
		ArrayList<String> AreaImages = bFM.Utils.extractStrings(KingdomPlan.getFile("ListImage"));
		ArrayList<String> ElemNames = bFM.Utils.extractStrings(KingdomPlan.getFile("ElemName"));
		ArrayList<String> ElemDescriptions = bFM.Utils.extractStrings(KingdomPlan.getFile("ElemText"));
		ArrayList<String> ElemImages = bFM.Utils.extractStrings(KingdomPlan.getFile("ElemImage"));
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
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Areas.get(i).getImage().getBytes(), new byte[1]));
		}
		return ret;
	}
	private byte[] listDescriptionBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Areas.get(i).getDescription().getBytes(), new byte[1]));
		}
		return ret;
	}
	private byte[] listNameBytes() 
	{
		byte[] ret = new byte[0];
		for(int i = 0; i < Areas.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.mergeArrays(Areas.get(i).getName().getBytes(), new byte[1]));
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
}
