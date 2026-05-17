package DebugModeManager.Event;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventViewer 
{
	ArrayList<EventEntry> Events = new ArrayList<EventEntry>();
	public EventViewer(byte[] data)
	{
		for(int i = 16; i < data.length; i += 224)
		{
			Events.add(new EventEntry(Arrays.copyOfRange(data, i, i+224)));
		}
	}
	public EventViewer(List<String> lines)
	{
		for(String line : lines)
		{
			Events.add(new EventEntry(line));
		}
	}
	public byte[] toBytes()
	{
		 byte[] ret = bFM.Utils.toByteArr(Events.size(), 4);
		 ret = bFM.Utils.mergeArrays(ret, new byte[12]);
		 for(EventEntry Event : Events)
		 {
			 ret = bFM.Utils.mergeArrays(ret, Event.toBytes());
		 }
		 return ret;
	}
	public String toString()
	{
		String ret = "Debug Event List";
		for(EventEntry Event : Events)
		{
			ret += "\n" + Event.toString();
		}
		return ret;
	}
	private class EventEntry
	{
		private String FileName = "";
		private String EventName = "";
		private int EventCode = -1;
		private int Chapter = -1;
		private int Index = -1;
		private EventEntry(byte[] data)
		{
			//224 bytes
			ByteBuffer EventData = ByteBuffer.wrap(data);
			byte[] StringArr = new byte[64];
			EventData.get(0, StringArr, 0, 64);
			FileName = new String(bFM.Utils.removeEmptySpace(StringArr), Charset.forName("Shift-JIS"));
			StringArr = new byte[64];
			EventData.get(64, StringArr, 0, 64);
			EventName = new String(bFM.Utils.removeEmptySpace(StringArr), Charset.forName("Shift-JIS"));
			EventCode = EventData.getInt(132);
			Chapter = EventData.getShort(136);
			Index = EventData.getShort(138);
		}
		private EventEntry(String line)
		{
			String[] data = line.split(",");
			if(data.length!=5) return;
			//224 bytes
			FileName = data[0].strip();
			EventName = data[1].strip();
			EventCode = bFM.Utils.strToInt(data[2]);
			Chapter = bFM.Utils.strToInt(data[3]);
			Index = bFM.Utils.strToInt(data[4]);
		}
		private byte[] toBytes()
		{
			ByteBuffer ret = ByteBuffer.allocate(224);
			byte[] StringArr = FileName.getBytes(Charset.forName("Shift-JIS"));
			ret.put(0, StringArr);
			StringArr = EventName.getBytes(Charset.forName("Shift-JIS"));
			ret.put(64, StringArr);
			ret.putInt(132, EventCode);
			ret.putShort(136, (short)Chapter);
			ret.putShort(138, (short)Index);
			return ret.array();
		}
		public String toString()
		{
			return FileName + "," + EventName + "," + EventCode + "," + Chapter + "," + Index;
		}
	}
}
