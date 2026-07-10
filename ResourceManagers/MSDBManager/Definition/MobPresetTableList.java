package ResourceManagers.MSDBManager.Definition;

import java.util.ArrayList;
import java.util.Arrays;

public class MobPresetTableList 
{
	ArrayList<MobPresetTable> PresetTable = new ArrayList<MobPresetTable>();
	public MobPresetTableList(byte[] data)
	{
		for(int i = 0; i<data.length; i+=24)
		{
			PresetTable.add(new MobPresetTable(Arrays.copyOfRange(data, i, i+24)));
		}
	}
	public String toString()
	{
		String ret = "Num " + PresetTable.size() + "\n";
		for(int i = 0; i<PresetTable.size(); i++)
		{
			ret = ret + PresetTable.get(i).toString();
		}
		return ret;
	}

}
