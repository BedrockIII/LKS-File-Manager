package BrresManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class BrresRoot 
{
	int rootOffset;
	final String magicHeader = "root";
	int sectionSize; // In Bytes, 8 + indexGroups size
	BrresIndexGroupHeader header;
	ArrayList<BrresIndexGroup> indexGroups = new ArrayList<BrresIndexGroup>();//List of Index Groups
	protected BrresRoot(ByteBuffer data)
	{
		rootOffset = data.position();
		if(!isValidMagic(data))
		{
			throw new IllegalArgumentException("Invalid .brres Root Magic Header");
		}
		sectionSize = data.getInt();
		header = new BrresIndexGroupHeader(data);
	}
	public boolean isValidMagic(ByteBuffer data)
	{
		if(data.get()!='r')
		{
			return false;
		}
		if(data.get()!='o')
		{
			return false;
		}
		if(data.get()!='o')
		{
			return false;
		}
		if(data.get()!='t')
		{
			return false;
		}
		return true;
	}
}
