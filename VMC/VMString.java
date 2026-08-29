package VMC;

import java.nio.charset.Charset;

public class VMString
{
	String value;
	public VMString(String value)
	{
		this.value = value;
	}
	public int getBlockSize()
	{
		int ret = value.getBytes(Charset.forName("Shift-JIS")).length + 1;// Word + null terminator
		ret = (ret + 3)/4;//Round up to 4
		return ret;
	}
	public String getValue()
	{
		return value;
	}
	public String toString()
	{
		return value;
	}
	public void setValue(String value)
	{
		this.value = value;
	}
}
