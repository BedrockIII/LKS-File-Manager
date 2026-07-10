package BrresManager;

import java.nio.ByteBuffer;

public class BrresHeader 
{
	final String magicHeader = "bres";
	short byteOrderMapping;
	short padding;
	int fileSize;
	Pointer rootOffset;
	short sectionCount;
	protected BrresHeader(ByteBuffer data)
	{
		if(!isValidMagic(data))
		{
			throw new IllegalArgumentException("Invalid .brres Magic Header");
		}
		byteOrderMapping = data.getShort();
		padding = data.getShort();
		fileSize = data.getInt();
		if(fileSize != data.capacity())
		{
			throw new IllegalArgumentException("Invalid .brres Header, Recorded File Size does not match Actual Buffer Size");
		}
		rootOffset = new Pointer(data.getShort());
		sectionCount = data.getShort();
	}
	public boolean isValidMagic(ByteBuffer data)
	{
		if(data.get()!='b')
		{
			return false;
		}
		if(data.get()!='r')
		{
			return false;
		}
		if(data.get()!='e')
		{
			return false;
		}
		if(data.get()!='s')
		{
			return false;
		}
		return true;
	}
}
