package SystemDataManagers.MenuDB.KingdomPlanManager;

import java.util.ArrayList;

import bFM.Data;
import bFM.Utils;

public class KingdomPlanArea implements Data
{
	String Name = "";
	String Description = "";
	String Image = "";
	ArrayList<KingdomPlanElement> Elements = new ArrayList<KingdomPlanElement>();
	public KingdomPlanArea(String Name, String Description, String Image)
	{
		this.Name = Name;
		this.Description = Description;
		this.Image = Image;
	}
	public KingdomPlanArea(String NameLine)
	{
		Name = bFM.Utils.formatString(NameLine);
	}
	public KingdomPlanArea() 
	{
		Name = "New Area";
		Image = "SP_00";
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<Area Description>>")!=-1)
		{
			Description = bFM.Utils.formatString(line);
			return;
		}
		if(line.indexOf("<<Area Image>>")!=-1)
		{
			Image = bFM.Utils.formatString(line);
			return;
		}
	}
	public String toString()
	{
		String ret = "<<Area Name>> \"" + Name + "\"\n";
		ret += "\t<<Area Description>> \"" + Description + "\"\n";
		ret += "\t<<Area Image>> \"" + Image + "\"\n";
		for(int i = 0; i < Elements.size(); i++)
		{
			ret += Elements.get(i).toString();
		}
		return ret;
	}
	public String getName()
	{
		return Name;
	}
	public String getDescription()
	{
		return Description;
	}
	public String getImage()
	{
		return Image;
	}
	public void addElement(KingdomPlanElement element)
	{
		Elements.add(element);
	}
	public byte[] getElementNames()
	{
		byte[] Names = null;
		for(int i = 0; i<Elements.size(); i++)
		{
			Names = bFM.Utils.mergeArrays(Names, bFM.Utils.mergeArrays(Utils.encodeStringToBytes(Elements.get(i).getName()), new byte[1]));
		}
		return Names;
	}
	public byte[] getElementDescriptions()
	{
		byte[] Descriptions = null;
		for(int i = 0; i<Elements.size(); i++)
		{
			Descriptions = bFM.Utils.mergeArrays(Descriptions, bFM.Utils.mergeArrays(Utils.encodeStringToBytes(Elements.get(i).getDescription()), new byte[1]));
		}
		return Descriptions;
	}
	public byte[] getElementImages()
	{
		byte[] Images = null;
		for(int i = 0; i<Elements.size(); i++)
		{
			Images = bFM.Utils.mergeArrays(Images, bFM.Utils.mergeArrays(Utils.encodeStringToBytes(Elements.get(i).getImage()), new byte[1]));
		}
		return Images;
	}
	public byte[] getElementFlags()
	{
		byte[] Flags = null;
		for(int i = 0; i<Elements.size(); i++)
		{
			Flags = bFM.Utils.mergeArrays(Flags, Elements.get(i).getFlags(i));
		}
		return Flags;
	}
	public byte[] getElementFlags(int areaIndex)
	{
		byte[] Flags = null;
		for(int i = 0; i<Elements.size(); i++)
		{
			Flags = bFM.Utils.mergeArrays(Flags, Elements.get(i).getFlags(areaIndex));
		}
		return Flags;
	}
	public ArrayList<KingdomPlanElement>getElements() 
	{
		return Elements;
	}
	public void setName(String text) 
	{
		Name = text;
	}
	public void setDescription(String text) 
	{
		Description = text;
	}
	public void setImage(String text) 
	{
		Image = text;
	}
	public void moveDown(int index) 
	{
		KingdomPlanElement Element = (KingdomPlanElement) Elements.get(index);
		Elements.remove(index);
		Elements.add(index+1, Element);
	}
	public void moveUp(int index) 
	{
		KingdomPlanElement Element = (KingdomPlanElement) Elements.get(index);
		Elements.remove(index);
		Elements.add(index-1, Element);
	}
	public void removeElement(KingdomPlanElement element) 
	{
		Elements.remove(element);
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
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
