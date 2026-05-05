package ResourceManagers.ItemDatabaseManager;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Item 
{
	String ItemName = "";
	int itemCode = -1;
	int num1 = 0;
	int num2 = -1;
	int num3 = -1;
	int num4 = 0;
	int num5 = 0;
	int num6 = 0;
	int num7 = 0;
	int num8 = 0;
	int num9 = 0;
	int num10 = 0;
	String ItemModel = "";//16 bytes
	int num11 = 0;
	int num12 = 0;
	int num13 = 0;
	int num14 = 0;
	int num15 = 0;
	int num16 = 0;
	int num17 = 0;
	int Price = 0;
	int num18 = 0;
	int num19 = 0;
	int num20 = 0;
	int num21 = 0;
	String NewslogName = "";
	String DisplayName = "";
	String ItemDescription = "";
	ArrayList<Placement> placements = new ArrayList<Placement>();
	String SoundEffect1 = "";
	String SoundEffect2 = "";
	String DigType = "";
	int DigSpeed = -1;
	String BuildType = "";
	int BuildSpeed = -1;
	String BreakType = "";
	int BreakSpeed = -1;
	String AttackType = "";
	int AttackSpeed = -1;
	Placement lastPlacement = null;
	protected Item(byte[] data)  
	{
		// Create an Item from an ItemBin Item Array
		//Check to make sure its the right length
		if(data.length!=84) return;
		ByteBuffer itemData = ByteBuffer.wrap(data);
		itemCode = itemData.getInt(0);
		num1 = itemData.getShort(4);
		num2 = itemData.getShort(6);
		num3 = itemData.getShort(8);
		num4 = itemData.getShort(10);
		num5 = itemData.getShort(12);
		num6 = itemData.getShort(14);
		num7 = itemData.getShort(16);
		num8 = itemData.getShort(18);
		num9 = itemData.getShort(20);
		num10 = itemData.getShort(22);
		byte[] modelArray = new byte[16];
		for(int i = 24; i<40; i++)
			modelArray[i-24] = data[i];
		ItemModel = bFM.Utils.formatString(new String(bFM.Utils.removeEmptySpace(modelArray), Charset.forName("Shift-JIS")));
		num11 = itemData.getShort(40);
		num12 = itemData.getShort(42);
		num13 = itemData.getInt(44);
		num14 = itemData.getInt(48);
		num15 = itemData.getInt(52);
		num16 = itemData.getInt(56);
		num17 = itemData.getInt(60);
		Price = itemData.getInt(64);
		num18 = itemData.getInt(68);
		num19 = itemData.getInt(72);
		num20 = itemData.getInt(76);
		num21 = itemData.getInt(80);
	}
	protected void addPlacement(byte[] placementBin)
	{
		//Adds a new Placement for this item
		//Check to make sure it is the right size
		if(placementBin.length!=28) return;
		//Check to make sure its the right code
		if(itemCode!=bFM.Utils.getShort(placementBin, 2)) return;
		placements.add(new Placement(placementBin));
	}
	protected void addHitSoundEffectData(String line)
	{
		//Adds the 2 Hit Sound Effects from the Sub List File
		//Check to make sure its an HITSE line
		if(line.indexOf("HITSE ")==-1) return;
		//Check to make sure it has the correct item Index
		if(line.indexOf(" " + itemCode)==-1) return;
		String[] data = bFM.Utils.toStrArr(line);
		//Check to make sure this is the right length
		if(data.length!=3) return;
		SoundEffect1 = bFM.Utils.formatString(data[1]);
		SoundEffect2 = bFM.Utils.formatString(data[2]);
	}
	protected void addWeaponData(String line)
	{
		//Adds the Weapon Data from the Sub List File
		//Check to make sure its a WEP line
		if(line.indexOf("WEP ")==-1) return;
		//Check to make sure it has the correct item Index
		if(line.indexOf(" " + itemCode)==-1) return;
		String[] data = bFM.Utils.toStrArr(line);
		//Check to make sure this is the right length
		if(data.length!=9) return;
		DigType = bFM.Utils.formatString(data[1]);
		BuildType = bFM.Utils.formatString(data[2]);
		BreakType = bFM.Utils.formatString(data[3]);
		AttackType = bFM.Utils.formatString(data[4]);
		DigSpeed = Integer.parseInt(data[5]);
		BuildSpeed = Integer.parseInt(data[6]);
		BreakSpeed =Integer.parseInt(data[7]);
		AttackSpeed = Integer.parseInt(data[8]);
	}
	protected void addItemList(ArrayList<String> Lines)
	{
		//Adds the data from the Item List
		//Check to make sure it is the minimum length
		if(Lines.size()<4) return;
		//Check to make sure the item code matches
		if(itemCode != Integer.parseInt(Lines.get(0).substring(1))) return;
		ItemName = bFM.Utils.formatString(Lines.get(1));
		NewslogName = bFM.Utils.formatString(Lines.get(2));
		DisplayName = bFM.Utils.formatString(Lines.get(3));
		if(Lines.size()<5) return;
		ItemDescription = bFM.Utils.formatString(Lines.get(4));
		for(int i = 5; i<Lines.size(); i++)
		{
			ItemDescription += "\\r\\n" + Lines.get(i);
		}
	}
	public int getItemCode() 
	{
		// Return the itemCode
		return itemCode;
	}
	public String toString()
	{
		//Return the Item as an easier to edit text file
		String ret = "<<Item>> \"" + ItemName + "\"\n";
		ret += "\t<<Item Code>> " + itemCode + "\n";
		if(num1!=0) ret += "\t<<num1>> " + num1 + "\n";
		if(num2!=-1) ret += "\t<<num2>> " + num2 + "\n";
		if(num3!=-1) ret += "\t<<num3>> " + num3 + "\n";
		if(num4!=0) ret += "\t<<num4>> " + num4 + "\n";
		if(num5!=0) ret += "\t<<num5>> " + num5 + "\n";
		if(num6!=0) ret += "\t<<num6>> " + num6 + "\n";
		if(num7!=0) ret += "\t<<num7>> " + num7 + "\n";
		if(num8!=0) ret += "\t<<num8>> " + num8 + "\n";
		if(num9!=0) ret += "\t<<num9>> " + num9 + "\n";
		if(num10!=0) ret += "\t<<num10>> " + num10 + "\n";
		ret += "\t<<Item Model>> \"" + ItemModel + "\"\n";
		if(num11!=0) ret += "\t<<num11>> " + num11 + "\n";
		if(num12!=0) ret += "\t<<num12>> " + num12 + "\n";
		if(num13!=0) ret += "\t<<num13>> " + num13 + "\n";
		if(num14!=0) ret += "\t<<num14>> " + num14 + "\n";
		if(num15!=0) ret += "\t<<num15>> " + num15 + "\n";
		if(num16!=0) ret += "\t<<num16>> " + num16 + "\n";
		if(num17!=0) ret += "\t<<num17>> " + num17 + "\n";
		if(Price!=0) ret += "\t<<Value>> " + Price + "\n";
		if(num18!=0) ret += "\t<<num18>> " + num18 + "\n";
		if(num19!=0) ret += "\t<<num19>> " + num19 + "\n";
		if(num20!=0) ret += "\t<<num20>> " + num20 + "\n";
		if(num21!=0) ret += "\t<<num21>> " + num21 + "\n";
		if(NewslogName.length()!=0) ret += "\t<<Newslog Name>> \"" + NewslogName + "\"\n";
		if(DisplayName.length()!=0) ret += "\t<<Display Name>> \"" + DisplayName + "\"\n";
		if(ItemDescription.length()!=0) ret += "\t<<Item Description>> \"" + ItemDescription + "\"\n";
		if(SoundEffect1.length()!=0) ret += "\t<<Equipment Sound Effect 1>> \"" + SoundEffect1 + "\"\n";
		if(SoundEffect2.length()!=0) ret += "\t<<Equipment Sound Effect 2>> \"" + SoundEffect2 + "\"\n";
		if(DigType.length()!=0) ret += "\t<<Equipment Dig Type>> \"" + DigType + "\"\n";
		if(DigSpeed!=-1) ret += "\t\t<<Dig Speed>> " + DigSpeed + "\n";
		if(BuildType.length()!=0) ret += "\t<<Equipment Build Type>> \"" + BuildType + "\"\n";
		if(BuildSpeed!=-1) ret += "\t\t<<Build Speed>> " + BuildSpeed + "\n";
		if(BreakType.length()!=0) ret += "\t<<Equipment Break Type>> \"" + BreakType + "\"\n";
		if(BreakSpeed!=-1) ret += "\t\t<<Break Speed>> " + BreakSpeed + "\n";
		if(AttackType.length()!=0) ret += "\t<<Equipment Attack Type>> \"" + AttackType + "\"\n";
		if(AttackSpeed!=-1) ret += "\t\t<<Attack Speed>> " + AttackSpeed + "\n";
		for(Placement p : placements)
		{
			ret += p.toString();
		}
		return ret;
	}
	protected Item(String line) 
	{
		//Initialize an Item using only it's name line from the text
		ItemName = bFM.Utils.formatString(line);
	}
	protected void addItemVariableLine(String line) 
	{
		//Set Variables depending on what the line has
		if(line.indexOf("<<Item Code>>")!=-1) itemCode = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num1>>")!=-1) num1 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num2>>")!=-1) num2 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num3>>")!=-1) num3 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num4>>")!=-1) num4 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num5>>")!=-1) num5 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num6>>")!=-1) num6 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num7>>")!=-1) num7 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num8>>")!=-1) num8 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num9>>")!=-1) num9 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num10>>")!=-1) num10 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Item Model>>")!=-1) ItemModel = bFM.Utils.formatString(line);
		else if(line.indexOf("<<num11>>")!=-1) num11 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num12>>")!=-1) num12 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num13>>")!=-1) num13 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num14>>")!=-1) num14 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num15>>")!=-1) num15 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num16>>")!=-1) num16 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num17>>")!=-1) num17 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Value>>")!=-1) Price = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num18>>")!=-1) num18 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num19>>")!=-1) num19 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num20>>")!=-1) num20 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num21>>")!=-1) num21 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Newslog Name>>")!=-1) NewslogName = bFM.Utils.formatString(line);
		else if(line.indexOf("<<Display Name>>")!=-1) DisplayName = bFM.Utils.formatString(line);
		else if(line.indexOf("<<Item Description>>")!=-1) ItemDescription = bFM.Utils.formatString(line);
		else if(line.indexOf("<<Equipment Sound Effect 1>>")!=-1) SoundEffect1 = bFM.Utils.formatString(line);
		else if(line.indexOf("<<Equipment Sound Effect 2>>")!=-1) SoundEffect2 = bFM.Utils.formatString(line);
		else if(line.indexOf("<<Equipment Dig Type>>")!=-1) DigType = bFM.Utils.formatString(line);
		else if(line.indexOf("<<Dig Speed>>")!=-1) DigSpeed = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Equipment Build Type>>")!=-1) BuildType = bFM.Utils.formatString(line);
		else if(line.indexOf("<<Build Speed>>")!=-1) BuildSpeed = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Equipment Break Type>>")!=-1) BreakType = bFM.Utils.formatString(line);
		else if(line.indexOf("<<Break Speed>>")!=-1) BreakSpeed = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Equipment Attack Type>>")!=-1) AttackType = bFM.Utils.formatString(line);
		else if(line.indexOf("<<Attack Speed>>")!=-1) AttackSpeed = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Location>>")!=-1) 
		{
			lastPlacement = new Placement(line);
			placements.add(lastPlacement);
		}
		else if(line.indexOf("<<Placement Activation Flag>>")!=-1) 
		{
			if(lastPlacement != null)
			{
				lastPlacement.addPlacementLine(line);
			}
		}
		else if(line.indexOf("<<Placement Deactivation Flag>>")!=-1)
		{
			if(lastPlacement != null)
			{
				lastPlacement.addPlacementLine(line);
			}
		}
		else if(line.indexOf("<<Unknown Placement Number>>")!=-1)
		{
			if(lastPlacement != null)
			{
				lastPlacement.addPlacementLine(line);
			}
		}
		else if(line.indexOf("<<Placement Building>>")!=-1)
		{
			if(lastPlacement != null)
			{
				lastPlacement.addPlacementLine(line);
			}
		}
	}
	protected String getSubList() 
	{
		// If the Equipment Data is initialized, return it.
		String ret = "";
		if(SoundEffect1.length()>0||SoundEffect2.length()>0)
		{
			ret += "HITSE " + itemCode + ",\"" + SoundEffect1 + "\",\"" + SoundEffect2 + "\";\r\n";
		}
		
		if(DigType.length()>0||BuildType.length()>0||BreakType.length()>0||AttackType.length()>0||
				DigSpeed!=-1||BuildSpeed!=-1||BreakSpeed!=-1||AttackSpeed!=-1)
		{
			ret += "WEP " + itemCode + ",\"" + DigType + "\",\"" 
		+ BuildType + "\",\"" + BreakType + "\",\"" + AttackType
		+ "\"," + DigSpeed + "," + BuildSpeed + "," + BreakSpeed
		+ "," + AttackSpeed + ";\r\n";
		}
		
		return ret;
	}
	protected String getList() 
	{
		// return Text Data for Items
		String ret = "@" + itemCode + "\r\n";
		ret += ItemName + "\r\n";
		ret += NewslogName + "\r\n";
		ret += DisplayName + "\r\n";
		ret += bFM.Utils.formatStringChars(ItemDescription) + "\r\n";
		return ret;
	}
	protected byte[] getPlaceBytes()
	{
		//returns the placement data for all the appearances of this object
		byte[] ret = new byte[0];
		for(Placement p : placements)
		{
			//ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(itemCode).array());
			ret = bFM.Utils.mergeArrays(ret, p.toBytes(itemCode));
		}
		return ret;
	}
	public byte[] getItemBytes() 
	{
		//returns the item resource data for this object
		//im sorry
		byte[] ret = new byte[0];
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(itemCode).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num1).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num2).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num3).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num4).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num5).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num6).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num7).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num8).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num9).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num10).array());
		ret = bFM.Utils.mergeArrays(ret, ItemModel.substring(0, Math.min(ItemModel.length(), 16)).getBytes());
		ret = bFM.Utils.mergeArrays(ret, new byte[40-ret.length]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num11).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num12).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num13).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num14).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num15).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num16).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num17).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(Price).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num18).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num19).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num20).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num21).array());
		return ret;
	}
}
