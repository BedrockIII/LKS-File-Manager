package SystemDataManagers;

import java.util.ArrayList;

public class KingdomPlanArea 
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
			Names = bFM.Utils.mergeArrays(Names, bFM.Utils.mergeArrays(Elements.get(i).getName().getBytes(), new byte[1]));
		}
		return Names;
	}//bFM.Utils.mergeArrays(, )
	public byte[] getElementDescriptions()
	{
		byte[] Descriptions = null;
		for(int i = 0; i<Elements.size(); i++)
		{
			Descriptions = bFM.Utils.mergeArrays(Descriptions, bFM.Utils.mergeArrays(Elements.get(i).getDescription().getBytes(), new byte[1]));
		}
		return Descriptions;
	}
	public byte[] getElementImages()
	{
		byte[] Images = null;
		for(int i = 0; i<Elements.size(); i++)
		{
			Images = bFM.Utils.mergeArrays(Images, bFM.Utils.mergeArrays(Elements.get(i).getImage().getBytes(), new byte[1]));
		}
		return Images;
	}
	public byte[] getElementFlags()
	{
		byte[] Flags = null;
		for(int i = 0; i<Elements.size(); i++)
		{
			Flags = bFM.Utils.mergeArrays(Flags, Elements.get(i).getFlags());
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
}
