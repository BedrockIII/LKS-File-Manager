package SystemDataManagers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;

public class MenuStringManager implements Data
{
	ArrayList<String> Messages = new ArrayList<String>();
	public MenuStringManager(byte[] data)
	{
		Messages = bFM.Utils.extractStrings(data);
	}
	public MenuStringManager(List<String> lines)
	{
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<<String ")!=-1&&lines.get(i).indexOf(">>")!=-1)
			{
				Messages.add(bFM.Utils.formatString(lines.get(i)));
			}
		}
	}
	public byte[] toBytes()
	{
		byte[] messageBytes = new byte[0];
		for(int i = 0; i<Messages.size(); i++)
		{
			messageBytes = bFM.Utils.mergeArrays(messageBytes,bFM.Utils.mergeArrays(Messages.get(i).getBytes(Charset.forName("Ascii")),new byte[1]));
		}
		return messageBytes;
	}
	public String toString()
	{
		String ret = "";
		for(int i = 0; i<Messages.size(); i++)
		{
			ret += "<<String " + (i+1) + ">> \"" + Messages.get(i) + "\"\n";
		}
		return ret;
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		Messages = bFM.Utils.extractStrings(data);
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
