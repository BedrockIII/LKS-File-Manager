package BrresManager;

public class Pointer 
{
	private int bytePosition = -1;
	public Pointer(int bytePosition)
	{
		this.bytePosition = bytePosition;
	}
	public Pointer(Pointer root, int offset)
	{
		bytePosition = root.getPosition() + offset;
	}
	public int getOffset(Pointer toHere)
	{
		return toHere.getPosition() - bytePosition;
	}
	public int getPosition() 
	{
		return bytePosition;
	}
	public void setPosition(int newPosition)
	{
		bytePosition = newPosition;
	}
	public boolean isNull() 
	{
		return bytePosition < 1;
	}
	public String toString()
	{
		return "0x" + Integer.toHexString(bytePosition);
	}
}
