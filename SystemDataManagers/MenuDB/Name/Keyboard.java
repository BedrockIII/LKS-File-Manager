package SystemDataManagers.MenuDB.Name;

import java.nio.charset.Charset;
import java.util.ArrayList;

import bFM.Data;
import bFM.Utils;

public class Keyboard implements Data
{
	Charset encoding;
	String[] Characters = new String[80];
	public Keyboard(byte[] lowercase, Charset encoding)
	{
		this.encoding = encoding;
		ArrayList<String> characters = Utils.extractStrings(lowercase);
		if(characters.size() != 80)
		{
			throw new IllegalArgumentException("Wrong Amount of Letters In Keyboard. Expected 80, Has "+ characters.size());
		}
		for(int i = 0; i < Characters.length; i++)
		{
			Characters[i] = characters.get(i);
		}
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		byte[] ret = new byte[0];
		for(String Letter : Characters)
		{
			try {
				ret = Utils.mergeArrays(ret, Utils.mergeArrays(Utils.encodeStringToBytes(Letter, encoding), (byte)0x00));
			} catch (Exception e)
			{
				ret = Utils.mergeArrays(ret, Utils.mergeArrays(Letter.getBytes(Charset.forName("Shift-JIS")), (byte)0x00));
			}
		}
		return ret;
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public String getLetter(int index)
	{
		if(index < 0 || index > Characters.length)
			return "";
		return Characters[index];
	}
	public void setLetter(int index, String letter)
	{
		if(index < 0 || index > Characters.length)
			return;
		Characters[index] = letter;
	}
}
