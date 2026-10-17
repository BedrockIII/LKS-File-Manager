package SystemDataManagers.MenuDB.Name;

import java.nio.charset.Charset;
import java.util.ArrayList;

import bFM.Data;
import bFM.Utils;

public class NameList implements Data
{
	Charset encoding;
	ArrayList<GeneratedName> Names = new ArrayList<GeneratedName>();
	NameList(byte[] data, Charset encoding)
	{
		this.encoding = encoding;
		ArrayList<String> Names = Utils.extractStringsNoFormatting(data);
		if(Names.size() == 0) return;
		String last = Names.remove(Names.size()-1);
		if(last.equals("/") == false)
		{
			throw new IllegalArgumentException("Name List Doesn't End In Loop Char \n" + last);
		}
		for(String name : Names)
			this.Names.add(new GeneratedName(name));
	}
	public ArrayList<GeneratedName> getNames()
	{
		return Names;
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		byte[] ret = new byte[0];
		for(GeneratedName Name : Names)
		{
			try
			{
				ret = Utils.mergeArrays(ret, Utils.mergeArrays(Utils.encodeStringToBytes(Name.getName(), encoding), (byte)0x00));
			} catch (Exception e)
			{
				ret = Utils.mergeArrays(ret, Utils.mergeArrays(Name.getName().getBytes(Charset.forName("Shift-JIS")), (byte)0x00));
			}
		}
		return Utils.mergeArrays(ret, (byte)'/');
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
