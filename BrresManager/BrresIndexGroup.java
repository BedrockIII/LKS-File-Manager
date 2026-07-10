package BrresManager;

import java.nio.ByteBuffer;

public class BrresIndexGroup 
{
	String name;
	int entryID;
	int padding;
	int leftIndex;
	int rightIndex;
	int nameOffset;
	int dataOffset;
	public BrresIndexGroup(ByteBuffer data) 
	{
		entryID = data.getShort() & 0xFFFF;
		padding = data.getShort() & 0xFFFF;
		leftIndex = data.getShort() & 0xFFFF;
		rightIndex = data.getShort() & 0xFFFF;
		nameOffset = data.getInt();
		dataOffset = data.getInt();
	}
	public String toString()
	{
		return "Node: " + name + "\n";
	}
}
