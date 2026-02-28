package SystemDataManagers;

import PCKGManager.PCKGManager;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

public class NormalMailManager 
{
	PCKGManager questData;
	ArrayList<Letter> Letters = new ArrayList<Letter>();
	public NormalMailManager(byte[] data)
	{
		questData = new PCKGManager(data);
		decodeData();
	}
	public NormalMailManager(List<String> lines)
	{
		boolean inString = false;
		boolean inTitle = false;
		String line = "";
		for(int i = 0; i<lines.size(); i++)
		{
			if(inString == true)
			{
				//if end
				if((lines.get(i+1).indexOf("<<")!=-1)&&lines.get(i+1).indexOf(">>")!=-1)
				{
					if(inTitle)
					{
						line += lines.get(i).substring(0,lines.get(i).lastIndexOf("\""));
						Letters.add(new Letter(line, lines.size()));
						inTitle = false;
					}
					else
					{
						line += lines.get(i);
						
						Letters.get(Letters.size()-1).addLine(line);
					}
					
					
					inString = false;
					line = "";
				}
				else
				{
					line += lines.get(i) + "\n";
				}
			}
			else if(lines.get(i).indexOf("<<Quest Letter>>")!=-1)
			{
				
				if((lines.get(i+1).indexOf("<<")==-1)&&lines.get(i+1).indexOf(">>")==-1)
				{
					line = lines.get(i).substring(lines.get(i).indexOf("\"")) + "\n";
					inTitle = true;
					inString = true;
				}
				else
				{
					line = lines.get(i).substring(lines.get(i).indexOf("\"")+1 , lines.get(i).lastIndexOf("\""));
					inTitle = false;
					inString = false;
					Letters.add(new Letter(line, Letters.size()));
				}
			}
			else if(lines.get(i).indexOf("<<Message>>")!=-1||lines.get(i).indexOf("<<Completion Message>>")!=-1)
			{
				line = lines.get(i)+"\n";
				inString = true;
			}
			else if(lines.get(i).indexOf("<<")!=-1&&lines.get(i).indexOf(">>")!=-1)
			{
				Letters.get(Letters.size()-1).addLine(lines.get(i));
			}
		}
	}
	private void decodeData()
	{
		ArrayList<String> CompletionMessages = bFM.Utils.extractStrings(questData.getFile("QuestEndMes"));//Verde's Congrats
		ArrayList<int[]> ItemCodes = getItemCodes(questData.getFile("QuestGetItem"));//Completion Reward
		ArrayList<int[]> Flags = getFlags(questData.getFile("QuestInfo"));//Flags
		ArrayList<String> Messages = bFM.Utils.extractStrings(questData.getFile("QuestText"));//Obvi
		ArrayList<String> Titles = bFM.Utils.extractStrings(questData.getFile("QuestTitle"));//Obvi
		
		for(int i = 0; i<Titles.size(); i++)
		{
			Letters.add(new Letter(Titles.get(i), Messages.get(i), CompletionMessages.get(i), ItemCodes.get(i), Flags.get(i)));
		}
	}
	private ArrayList<int[]> getFlags(byte[] data) 
	{
		ArrayList<int[]> Flags = new ArrayList<int[]>();
		for(int i = 4; i<data.length; i+=152)
		{
			byte[] flagData = new byte[152];
			System.arraycopy(data,i,flagData,0,152);
			int[] flags = new int[76];
			for(int j = 0; j < 152; j+=2)
			{
				flags[j/2] = bFM.Utils.getShort(flagData, j);
			}
			Flags.add(flags);
		}
		return Flags;
	}
	private ArrayList<int[]> getItemCodes(byte[] data) 
	{
		ArrayList<int[]> Codes = new ArrayList<int[]>();
		int[] items = {0,0,0,0,0,0,0,0};
		for(int i = 0; i<data.length; i+=32)
		{
			items[0] = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(i);
			items[1] = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(i+4);
			items[2] = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(i+8);
			items[3] = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(i+12);
			items[4] = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(i+16);
			items[5] = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(i+20);
			items[6] = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(i+24);
			items[7] = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(i+28);
			int[] ret = {items[0], items[1], items[2], items[3], items[4], items[5], items[6], items[7]};
			Codes.add(ret);
			items[0] = 0;
			items[1] = 0;
			items[2] = 0;
			items[3] = 0;
			items[4] = 0;
			items[5] = 0;
			items[6] = 0;
			items[7] = 0;
		}
		return Codes;
	}
	public String toString()
	{
		String ret = "";
		for(int i = 0; i<Letters.size(); i++)
		{
			ret+=Letters.get(i);
		}
		return ret;
	}
	public byte[] toBytes() 
	{
		questData = new PCKGManager("normalquestdata.bin");
		questData.addFile("QuestTitle", titleBytes());
		questData.addFile("QuestText", textBytes());
		questData.addFile("QuestEndMes", completionBytes());
		questData.addFile("QuestGetItem", itemBytes());
		questData.addFile("QuestInfo", flagBytes());
		return questData.getFile();
	}
	private byte[] flagBytes() 
	{
		byte[] ret = "QDB1".getBytes();
		for(int i = 0; i < Letters.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Letters.get(i).getFlags());
		}
		return ret;
	}
	private byte[] itemBytes() 
	{
		byte[] ret = null;
		for(int i = 0; i < Letters.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Letters.get(i).itemBytes());
		}
		return ret;
	}
	private byte[] completionBytes() 
	{
		byte[] ret = null;
		for(int i = 0; i < Letters.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Letters.get(i).completionBytes());
		}
		return ret;
	}
	private byte[] textBytes() 
	{
		byte[] ret = null;
		for(int i = 0; i < Letters.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Letters.get(i).textBytes());
		}
		return ret;
	}
	private byte[] titleBytes() 
	{
		byte[] ret = null;
		for(int i = 0; i < Letters.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, Letters.get(i).titleBytes());
		}
		return ret;
	}
}
