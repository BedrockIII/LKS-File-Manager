package BrresManager;

import java.nio.ByteBuffer;

public class BrresIndexGroupHeader 
{
	String name;
	int startPos;
	int groupSize; //In Bytes
	int groupAmount;
	public BrresIndexGroupHeader(ByteBuffer data) 
	{
		startPos = data.position();
		int nodeBase = startPos + 8;
		groupSize = data.getInt();
		groupAmount = data.getInt();
		
		BrresIndexGroup[] nodes = new BrresIndexGroup[groupAmount + 1];
		
		for(int i = 1; i <= groupAmount; i++)
		{
			int nodeOffset = nodeBase + (i * 16);
			
			BrresIndexGroup node = new BrresIndexGroup(data);
			nodes[i-1] = node;
			
			int stringPos = nodeOffset + 8 + node.nameOffset;
			node.name = BrresFile.getString(data, stringPos);
			
			int dataPos =nodeOffset + 12 + node.dataOffset;
			
			//processEntry(name, dataPos, "    ");
		}
	}
}
