package SystemDataManagers;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;

public class CockpitLogManager 
{
	PCKGManager CockpitLog = new PCKGManager("CockpitLog.bin");
	ArrayList<String> Messages = new ArrayList<String>();
	int[] Flags = new int[1];
	public CockpitLogManager(byte[] data)
	{
		CockpitLog = new PCKGManager(data);
		Messages = bFM.Utils.extractStrings(CockpitLog.getFile("CockpitLog"));
		byte[] file = CockpitLog.getFile("Val");
		Flags = new int[file.length/4];
		for(int i = 0; i < file.length/4; i++)
		{
			Flags[i] = ByteBuffer.wrap(file).getInt(i*4);
		}
	}
	public CockpitLogManager(List<String> lines)
	{
		ArrayList<Integer> Flags = new ArrayList<Integer>();
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<<Message ")!=-1&&lines.get(i).indexOf(">>")!=-1)
			{
				Messages.add(bFM.Utils.formatString(lines.get(i)));
				if(i+1!=lines.size()&&lines.get(i+1).indexOf("<<Flag>>")!=-1)
				{
					Flags.add(bFM.Utils.formatFlag(lines.get(i+1)));
				}
				else
				{
					Flags.add(0);
				}
			}
		}
		this.Flags = new int[Flags.size()];
		for(int i = 0; i < Flags.size(); i++)
		{
			this.Flags[i] = Flags.get(i);
		}
	}
	public byte[] toBytes()
	{
		byte[] messageBytes = new byte[0];
		for(int i = 0; i<Messages.size(); i++)
		{
			messageBytes = bFM.Utils.mergeArrays(messageBytes,bFM.Utils.mergeArrays(Messages.get(i).getBytes(),new byte[1]));
		}
		byte[] flagBytes = new byte[0];
		for(int i = 0; i<Flags.length; i++)
		{
			flagBytes = bFM.Utils.mergeArrays(flagBytes,bFM.Utils.toByteArr(Flags[i], 4));
		}
		CockpitLog.addFile("Val", flagBytes);
		CockpitLog.addFile("CockpitLog", messageBytes);
		return CockpitLog.getFile();
	}
	public String toString()
	{
		String ret = "";
		for(int i = 0; i<Messages.size(); i++)
		{
			ret += "<<Message " + (i+1) + ">> \"" + Messages.get(i) + "\"\n";
			if(Flags[i]!=0)
			{
				ret += "\t<<Flag>> " + Flags[i] + "\n";
			}
		}
		return ret;
	}
}
