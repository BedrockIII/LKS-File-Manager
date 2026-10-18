package bFM;

import java.util.List;

public interface IntermediateFileFormat
{
	abstract void initializeFromBIT(List<String> lines);
	abstract void importFromBIT(List<String> lines);
	default void replaceFromBIT(byte[] data)
	{
		initializeFromBIT(Utils.bytesToStrs(data));
	}
	default void importFromBIT(byte[] data)
	{
		importFromBIT(Utils.bytesToStrs(data));
	}
	abstract byte[] toBIT();
	//Test1
	abstract String getBITName();
	//Test
}
