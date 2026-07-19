package SystemDataManagers.MenuDB;

import java.util.ArrayList;
import java.util.List;

import PCKGManager.PCKGManager;
import bFM.Data;
import bFM.OpenedFile;
import bFM.Utils;

public class WonderSpotManager implements OpenedFile
{
	ArrayList<WonderSpot> Spots = new ArrayList<WonderSpot>();
	public WonderSpotManager(byte[] data)
	{
		initializeFromBytes(data);
	}
	public WonderSpotManager(List<String> lines)
	{
		initializeFromLines(lines);
	}
	private void initializeFromBytes(byte[] data)
	{
		PCKGManager pack = new PCKGManager(data);
		List<String> names = Utils.extractStringsNoFormatting(pack.getFile("Name"));
		List<String> texts = Utils.extractStringsNoFormatting(pack.getFile("Text"));
		List<String> images = Utils.extractStringsNoFormatting(pack.getFile("Image"));
		List<String> details = Utils.extractStringsNoFormatting(pack.getFile("Detail"));
		int spotCount = names.size();
		if(spotCount > texts.size() || spotCount > images.size() || spotCount > details.size())
		{
			throw new IllegalArgumentException("Wonder Spot Pack is incorrectly formatted. \n"
					+ "Name Count: " + spotCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Detail Count: " + details.size() + "\n");
		}
		else if(spotCount != texts.size() || spotCount != images.size() || spotCount != details.size())
		{
			System.err.print("Wonder Spot Pack is incorrectly formatted. \n"
					+ "Name Count: " + spotCount + "\n"
					+ "Text Count: " + texts.size() + "\n"
					+ "Image Count: " + images.size() + "\n"
					+ "Detail Count: " + details.size() + "\n");
		}
		for(int i = 0; i < spotCount; i++)
		{
			Spots.add(new WonderSpot(names.get(i), texts.get(i), images.get(i), details.get(i)));
		}
	}
	private void initializeFromLines(List<String> lines)
	{
		WonderSpot lastSpot = null;
		for(String line : lines)
		{
			if(line.indexOf("<<Wonder Spot Name>>") != -1)
			{
				lastSpot = new WonderSpot(line);
				Spots.add(lastSpot);
			}
			else if(lastSpot != null) lastSpot.addLine(line);
		}
	}
	public String toString()
	{
		String ret = "Bedrock's Wonder Spot Intermediate File v1.0\n";
		for(WonderSpot spot : Spots)
		{
			ret += spot.toString();
		}
		return ret;
	}
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		PCKGManager pack = new PCKGManager(getName());
		byte[] nameBin = new byte[0];
		for(WonderSpot spot : Spots)
		{
			nameBin = Utils.mergeArrays(nameBin, Utils.mergeArrays(Utils.encodeStringToBytes(spot.name), (byte)0x00));
		}
		byte[] textBin = new byte[0];
		for(WonderSpot spot : Spots)
		{
			textBin = Utils.mergeArrays(textBin, Utils.mergeArrays(Utils.encodeStringToBytes(spot.text), (byte)0x00));
		}
		byte[] imageBin = new byte[0];
		for(WonderSpot spot : Spots)
		{
			imageBin = Utils.mergeArrays(imageBin, Utils.mergeArrays(Utils.encodeStringToBytes(spot.image), (byte)0x00));
		}
		byte[] detailBin = new byte[0];
		for(WonderSpot spot : Spots)
		{
			detailBin = Utils.mergeArrays(detailBin, Utils.mergeArrays(Utils.encodeStringToBytes(spot.debugText), (byte)0x00));
		}
		pack.addFile("Name", nameBin);
		pack.addFile("Text", textBin);
		pack.addFile("Image", imageBin);
		pack.addFile("Detail", detailBin);
		return pack.toBytes();
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		return "Album.bin";
	}
	public int getSize() 
	{
		return toBytes().length;
	}
	public static class WonderSpot implements Data
	{
		String name = "New Wonderspot";
		String text = "New Wonderspot Description";
		String image = "sp01";
		String debugText = "Detail Text (Unused)";
		public WonderSpot(String name, String text, String image, String debugText)
		{
			this.name = name;
			this.text = text;
			this.image = image;
			this.debugText = debugText;
		}
		public WonderSpot(String line)
		{
			this.name = Utils.formatString(line);
		}
		public WonderSpot() 
		{
			// Use Defaults
		}
		public void addLine(String line)
		{
			if(line.indexOf("<<Description>>") != -1)
			{
				text = Utils.formatString(line);
			}
			else if(line.indexOf("<<Image>>") != -1)
			{
				image = Utils.formatString(line);
			}
			else if(line.indexOf("<<Debug Description>>") != -1)
			{
				debugText = Utils.formatString(line);
			}
		}
		public String toString()
		{
			String ret = "<<Wonder Spot Name>> \"" + Utils.toFormatedString(name) + "\"\n";
			ret += "\t<<Description>> \"" + Utils.toFormatedString(text) + "\"\n";
			ret += "\t<<Image>> \"" + Utils.toFormatedString(image) + "\"\n";
			ret += "\t<<Debug Description>> \"" + Utils.toFormatedString(debugText) + "\"\n";
			return ret;
		}
		public boolean equals(String name) 
		{
			throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
		}
		public void setData(byte[] data) 
		{
			throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
		}
		public byte[] toBytes() 
		{
			throw new UnsupportedOperationException("toBytes() should not be called on type " + this.getClass());
		}
		public void setName(String name) 
		{
			this.name = Utils.formatStringChars(name);
		}
		public String getName() 
		{
			return Utils.toFormatedString(name);
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public void setText(String text) 
		{
			this.text = Utils.formatStringChars(text);
		}
		public String getText() 
		{
			return Utils.toFormatedString(text);
		}
		public void setImage(String image) 
		{
			this.image = Utils.formatStringChars(image);
		}
		public String getImage() 
		{
			return Utils.toFormatedString(image);
		}
		public void setDebugText(String debugText) 
		{
			this.debugText = Utils.formatStringChars(debugText);
		}
		public String getDebugText() 
		{
			return Utils.toFormatedString(debugText);
		}
	}
	public ArrayList<WonderSpot> getSpots() 
	{
		return Spots;
	}
	public void replaceFromData(byte[] data)
	{
		Spots.removeAll(Spots);
		initializeFromBytes(data);
	}
	public void replaceFromBWS(byte[] data)
	{
		List<String> lines = Utils.bytesToStrs(data);
		Spots.removeAll(Spots);
		initializeFromLines(lines);
	}
	public void importFromBWS(byte[] data)
	{
		List<String> lines = Utils.bytesToStrs(data);
		initializeFromLines(lines);
	}
	public byte[] toBWS()
	{
		return Utils.encodeStringToBytes(toString());
	}
}
