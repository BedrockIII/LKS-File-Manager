package bFM;

import java.util.List;

public interface IntermediateFileFormat
{
	abstract void initializeFromBIT(List<String> lines);
	abstract void importFromBIT(List<String> lines);
	abstract String toBIT(List<String> lines);
}
