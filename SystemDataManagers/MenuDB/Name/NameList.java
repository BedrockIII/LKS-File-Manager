package SystemDataManagers.MenuDB.Name;

import java.nio.charset.Charset;
import java.util.ArrayList;

import bFM.Data;
import bFM.Utils;

public class NameList implements Data
{
	Charset encoding;
	ArrayList<String> Names;
	NameList(byte[] data, Charset encoding)
	{
		this.encoding = encoding;
		Names = Utils.extractStringsNoFormatting(data);
		if(Names.size() == 0) return;
		String last = Names.remove(Names.size()-1);
		if(last.equals("/") == false)
		{
			throw new IllegalArgumentException("Name List Doesn't End In Loop Char \n" + last);
		}
	}
	public ArrayList<String> getNames()
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
		for(String Name : Names)
		{
			try
			{
				ret = Utils.mergeArrays(ret, Utils.mergeArrays(Utils.encodeStringToBytes(Name, encoding), (byte)0x00));
			} catch (Exception e)
			{
				ret = Utils.mergeArrays(ret, Utils.mergeArrays(Name.getBytes(Charset.forName("Shift-JIS")), (byte)0x00));
			}
		}
		return Utils.mergeArrays(ret, (byte)'/');
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
