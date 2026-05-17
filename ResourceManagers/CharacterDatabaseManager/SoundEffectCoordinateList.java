package ResourceManagers.CharacterDatabaseManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

class SoundEffectCoordinateList 
{
	ArrayList<SoundEffectCoordinate> list = new ArrayList<SoundEffectCoordinate>();
	protected SoundEffectCoordinateList(byte[] data)
	{
		ByteBuffer bytes = ByteBuffer.wrap(data);
		for(int i = 4; i < data.length; i+=8)
		{
			list.add(new SoundEffectCoordinate(bytes.slice(i, 8)));
			//System.out.println(list.get(list.size()-1));
			//list.get(list.size()-1).printUniques();
		}
	}
	protected SoundEffectCoordinateList(List<String> lines) 
	{
		for(String line : lines)
		{
			if(line.indexOf("Sound Effect Coordinate")!=-1)
			{
				list.add(new SoundEffectCoordinate(line));
			}
		}
	}
	public String toString()
	{
		String ret = "";
		for(SoundEffectCoordinate sec : list)
		{
			if(ret.length()>0) ret += '\n';
			ret += sec.toString();
		}
		return ret;
	}
	protected byte[] toBytes()
	{
		byte[] ret = bFM.Utils.longToBytes(list.size(), 4);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.longToBytes(32, 2));
		ret = bFM.Utils.mergeArrays(ret, new byte[10]);
		for(SoundEffectCoordinate sec : list)
		{
			ret = bFM.Utils.mergeArrays(ret, sec.toBytes());
			ret = bFM.Utils.mergeArrays(ret, new byte[16]);
		}
		return ret;
	}
	
	
	private class SoundEffectCoordinate 
	{
		//8 bytes
	}

}
