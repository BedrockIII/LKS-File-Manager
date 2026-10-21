package SystemDataManagers.MenuDB.MenuString;

import bFM.Data;
import bFM.Utils;

public class MenuString implements Data
{
	String text = "";
	public MenuString()
	{
		text = "New String";
	}
	public MenuString(String text)
	{
		this.text = text;
	}
	public String getText()
	{
		// TODO Auto-generated method stub
		return text;
	}
	public void setText(String text)
	{
		this.text = text;
	}
	public void setData(byte[] data)
	{
		
	}
	public byte[] toBytes()
	{
		return Utils.encodeStringToBytes(Utils.formatStringChars(text));
	}
	public int getSize()
	{
		return toBytes().length;
	}
}