package ResourceManagers.ItemDatabaseManager;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;

import bFM.Data;

public class Item implements Data
{
	//Equip Flags
	final static int childMask = 0x0001;
	final static int adultMask = 0x0002;
	final static int gruntMask = 0x0004;
	final static int veteranMask = 0x0008;
	final static int knightMask = 0x0010;
	final static int hunterMask = 0x0020;
	final static int wizardMask = 0x0040;
	final static int lumberjackMask = 0x0080;
	final static int farmerMask = 0x0100;
	final static int minerMask = 0x0200;
	final static int carpenter1Mask = 0x0400;
	final static int carpenter2Mask = 0x0800;
	final static int carpenter3Mask = 0x1000;
	final static int merchantMask = 0x2000;
	final static int cookMask = 0x4000;
	final static int doctorMask = 0x8000;
	final static int egganMask = 0x10000;
	final static int broadcasterMask = 0x20000;
	final static int mountieMask = 0x40000;
	final static int craftianMask = 0x80000;
	//Immunity Flags
	final static int poisonImmunityMask = 0x0001;
	final static int immunity2Mask = 0x0002;
	final static int immunity4Mask = 0x0004;
	final static int immunity8Mask = 0x0008;
	final static int immunity10Mask = 0x0010;
	final static int immunity20Mask = 0x0020;
	final static int coldImmunityMask = 0x0040;
	final static int fireImmunityMask = 0x0080;
	final static int windImmunityMask = 0x0100;
	final static int immunity200Mask = 0x0200;
	final static int immunity400Mask = 0x0400;
	final static int immunity800Mask = 0x0800;
	final static int immunity1000Mask = 0x1000;
	final static int immunity2000Mask = 0x2000;
	final static int immunity4000Mask = 0x4000;
	final static int immunity8000Mask = 0x8000;
	String ItemName = "";
	int itemCode = -1;
	int num1 = 0;
	int num2 = -1;
	int GourmetBookIndex = -1;
	int SpecialAttackChance = 0;
	int DamageImmunityFlags = 0;
	int WeaponAttackSpeed = 0;
	int num7 = 0;
	int num8 = 0;
	int EquippableFlags = 0;
	String ItemModel = "";//16 bytes
	int num11 = 0;
	int num12 = 0;
	int num13 = 0;
	int num14 = 0;
	int num15 = 0;
	int num16 = 0;
	int num17 = 0;
	int Price = 0;
	int damageMod = 0;
	short hpMod = 0;
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
		GourmetBookIndex = itemData.getShort(8);
		SpecialAttackChance = itemData.getShort(10);
		DamageImmunityFlags = itemData.getShort(12);
		WeaponAttackSpeed = itemData.getShort(14);
		num7 = itemData.getShort(16);
		num8 = itemData.getShort(18);
		EquippableFlags = itemData.getInt(20);
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
		damageMod = itemData.getShort(68);
		hpMod = itemData.getShort(70);
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
		if(GourmetBookIndex!=-1) ret += "\t<<Gourmet Book Index>> " + GourmetBookIndex + "\n";
		if(SpecialAttackChance!=0) ret += "\t<<Special Attack Chance>> " + SpecialAttackChance + "\n";
		if(getDamageImmunityPoison()) ret += "\t<<Is Immune to Poison>>\n";
		if(getDamageImmunity2()) ret += "\t<<Is Immune to Type 2>>\n";
		if(getDamageImmunity4()) ret += "\t<<Is Immune to Type 4>>\n";
		if(getDamageImmunity8()) ret += "\t<<Is Immune to Type 8>>\n";
		if(getDamageImmunity10()) ret += "\t<<Is Immune to Type 10>>\n";
		if(getDamageImmunity20()) ret += "\t<<Is Immune to Type 20>>\n";
		if(getDamageImmunityCold()) ret += "\t<<Is Immune to Cold>>\n";
		if(getDamageImmunityFire()) ret += "\t<<Is Immune to Fire>>\n";
		if(getDamageImmunityWind()) ret += "\t<<Is Immune to Wind>>\n";
		if(getDamageImmunity200()) ret += "\t<<Is Immune to Type 200>>\n";
		if(getDamageImmunity400()) ret += "\t<<Is Immune to Type 400>>\n";
		if(getDamageImmunity800()) ret += "\t<<Is Immune to Type 800>>\n";
		if(getDamageImmunity1000()) ret += "\t<<Is Immune to Type 1000>>\n";
		if(getDamageImmunity2000()) ret += "\t<<Is Immune to Type 2000>>\n";
		if(getDamageImmunity4000()) ret += "\t<<Is Immune to Type 4000>>\n";
		if(getDamageImmunity8000()) ret += "\t<<Is Immune to Type 8000>>\n";
		if(WeaponAttackSpeed!=0) ret += "\t<<Weapon Attack Speed>> " + WeaponAttackSpeed + "\n";
		if(num7!=0) ret += "\t<<num7>> " + num7 + "\n";
		if(num8!=0) ret += "\t<<num8>> " + num8 + "\n";
		if(getIsEquippableJob23()) ret += "\t<<Is Equippable Child>>\n";
		if(getIsEquippableJob24()) ret += "\t<<Is Equippable Adult>>\n";
		if(getIsEquippableJob25()) ret += "\t<<Is Equippable Grunt>>\n";
		if(getIsEquippableJob26()) ret += "\t<<Is Equippable Veteran>>\n";
		if(getIsEquippableJob27()) ret += "\t<<Is Equippable Knight>>\n";
		if(getIsEquippableJob28()) ret += "\t<<Is Equippable Hunter>>\n";
		if(getIsEquippableJob29()) ret += "\t<<Is Equippable Wizard>>\n";
		if(getIsEquippableJob30()) ret += "\t<<Is Equippable Lumberjack>>\n";
		if(getIsEquippableJob31()) ret += "\t<<Is Equippable Farmer>>\n";
		if(getIsEquippableJob32()) ret += "\t<<Is Equippable Miner>>\n";
		if(getIsEquippableJob33()) ret += "\t<<Is Equippable Carpenter 1>>\n";
		if(getIsEquippableJob34()) ret += "\t<<Is Equippable Carpenter 2>>\n";
		if(getIsEquippableJob35()) ret += "\t<<Is Equippable Carpenter 3>>\n";
		if(getIsEquippableJob36()) ret += "\t<<Is Equippable Merchant>>\n";
		if(getIsEquippableJob37()) ret += "\t<<Is Equippable Cook>>\n";
		if(getIsEquippableJob38()) ret += "\t<<Is Equippable Doctor>>\n";
		if(getIsEquippableJob39()) ret += "\t<<Is Equippable Eggan>>\n";
		if(getIsEquippableJob40()) ret += "\t<<Is Equippable Broadcaster>>\n";
		if(getIsEquippableJob41()) ret += "\t<<Is Equippable Mountie>>\n";
		if(getIsEquippableJob42()) ret += "\t<<Is Equippable Craftian>>\n";
		ret += "\t<<Item Model>> \"" + ItemModel + "\"\n";
		if(num11!=0) ret += "\t<<num11>> " + num11 + "\n";
		if(num12!=0) ret += "\t<<num12>> " + num12 + "\n";
		if(num13!=0) ret += "\t<<num13>> " + num13 + "\n";
		if(num14!=0) ret += "\t<<num14>> " + num14 + "\n";
		if(num15!=0) ret += "\t<<num15>> " + num15 + "\n";
		if(num16!=0) ret += "\t<<num16>> " + num16 + "\n";
		if(num17!=0) ret += "\t<<num17>> " + num17 + "\n";
		if(Price!=0) ret += "\t<<Value>> " + Price + "\n";
		if(hpMod!=0) ret += "\t<<num18>> " + damageMod + "\n";
		if(hpMod!=0) ret += "\t<<HP Modifier>> " + hpMod + "\n";
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
	public Item(String line) 
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
		//Legacy
		else if(line.indexOf("<<num3>>")!=-1) GourmetBookIndex = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num4>>")!=-1) SpecialAttackChance = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num5>>")!=-1) DamageImmunityFlags = bFM.Utils.formatFlag(line);
		
		else if(line.indexOf("<<Gourmet Book Index>>")!=-1) GourmetBookIndex = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Special Attack Chance>>")!=-1) SpecialAttackChance = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Is Immune to Poison>>")!=-1) setDamageImmunityPoison(true);
		else if(line.indexOf("<<Is Immune to Type 2>>") != -1) setDamageImmunity2(true);
		else if(line.indexOf("<<Is Immune to Type 4>>") != -1) setDamageImmunity4(true);
		else if(line.indexOf("<<Is Immune to Type 8>>") != -1) setDamageImmunity8(true);
		else if(line.indexOf("<<Is Immune to Type 10>>") != -1) setDamageImmunity10(true);
		else if(line.indexOf("<<Is Immune to Type 20>>") != -1) setDamageImmunity20(true);
		else if(line.indexOf("<<Is Immune to Cold>>") != -1) setDamageImmunityCold(true);
		else if(line.indexOf("<<Is Immune to Fire>>") != -1) setDamageImmunityFire(true);
		else if(line.indexOf("<<Is Immune to Wind>>") != -1) setDamageImmunityWind(true);
		else if(line.indexOf("<<Is Immune to Type 200>>") != -1) setDamageImmunity200(true);
		else if(line.indexOf("<<Is Immune to Type 400>>") != -1) setDamageImmunity400(true);
		else if(line.indexOf("<<Is Immune to Type 800>>") != -1) setDamageImmunity800(true);
		else if(line.indexOf("<<Is Immune to Type 1000>>") != -1) setDamageImmunity1000(true);
		else if(line.indexOf("<<Is Immune to Type 2000>>") != -1) setDamageImmunity2000(true);
		else if(line.indexOf("<<Is Immune to Type 4000>>") != -1) setDamageImmunity4000(true);
		else if(line.indexOf("<<Is Immune to Type 8000>>") != -1) setDamageImmunity8000(true);
		else if(line.indexOf("<<num6>>")!=-1) WeaponAttackSpeed = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Weapon Attack Speed>>")!=-1) WeaponAttackSpeed = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num7>>")!=-1) num7 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num8>>")!=-1) num8 = bFM.Utils.formatFlag(line);
		/* Legacy from Version 1
		else if(line.indexOf("<<num9>>")!=-1) num9 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num10>>")!=-1) num10 = bFM.Utils.formatFlag(line);
		*/
		else if(line.indexOf("<<Is Equippable Child>>")!=-1) setIsEquippableJob23(true);
		else if(line.indexOf("<<Is Equippable Adult>>") != -1) setIsEquippableJob24(true);
		else if(line.indexOf("<<Is Equippable Grunt>>") != -1) setIsEquippableJob25(true);
		else if(line.indexOf("<<Is Equippable Veteran>>") != -1) setIsEquippableJob26(true);
		else if(line.indexOf("<<Is Equippable Knight>>") != -1) setIsEquippableJob27(true);
		else if(line.indexOf("<<Is Equippable Hunter>>") != -1) setIsEquippableJob28(true);
		else if(line.indexOf("<<Is Equippable Wizard>>") != -1) setIsEquippableJob29(true);
		else if(line.indexOf("<<Is Equippable Lumberjack>>") != -1) setIsEquippableJob30(true);
		else if(line.indexOf("<<Is Equippable Farmer>>") != -1) setIsEquippableJob31(true);
		else if(line.indexOf("<<Is Equippable Miner>>") != -1) setIsEquippableJob32(true);
		else if(line.indexOf("<<Is Equippable Carpenter 1>>") != -1) setIsEquippableJob33(true);
		else if(line.indexOf("<<Is Equippable Carpenter 2>>") != -1) setIsEquippableJob34(true);
		else if(line.indexOf("<<Is Equippable Carpenter 3>>") != -1) setIsEquippableJob35(true);
		else if(line.indexOf("<<Is Equippable Merchant>>") != -1) setIsEquippableJob36(true);
		else if(line.indexOf("<<Is Equippable Cook>>") != -1) setIsEquippableJob37(true);
		else if(line.indexOf("<<Is Equippable Doctor>>") != -1) setIsEquippableJob38(true);
		else if(line.indexOf("<<Is Equippable Eggan>>") != -1) setIsEquippableJob39(true);
		else if(line.indexOf("<<Is Equippable Broadcaster>>") != -1) setIsEquippableJob40(true);
		else if(line.indexOf("<<Is Equippable Mountie>>") != -1) setIsEquippableJob41(true);
		else if(line.indexOf("<<Is Equippable Craftian>>") != -1) setIsEquippableJob42(true);
		//
		else if(line.indexOf("<<Item Model>>")!=-1) ItemModel = bFM.Utils.formatString(line);
		else if(line.indexOf("<<num11>>")!=-1) num11 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num12>>")!=-1) num12 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num13>>")!=-1) num13 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num14>>")!=-1) num14 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num15>>")!=-1) num15 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num16>>")!=-1) num16 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num17>>")!=-1) num17 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Value>>")!=-1) Price = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<num18>>")!=-1) damageMod = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<HP Modifier>>")!=-1) hpMod = (short) bFM.Utils.formatFlag(line);
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
		else if(line.indexOf("<<Is Indoors>>")!=-1)
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
		if(hasSoundEffect())
		{
			ret += "HITSE " + itemCode + ",\"" + SoundEffect1 + "\",\"" + SoundEffect2 + "\";\r\n";
		}
		
		if(hasWeaponData())
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
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)GourmetBookIndex).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)SpecialAttackChance).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)DamageImmunityFlags).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)WeaponAttackSpeed).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num7).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short)num8).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(EquippableFlags).array());
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
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) damageMod).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort(hpMod).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num19).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num20).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num21).array());
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
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public void setName(String name) 
	{
		ItemName = name;
	}
	public String getName() 
	{
		return ItemName;
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public boolean hasSoundEffect()
	{
		return SoundEffect1.length()>0||SoundEffect2.length()>0;
	}
	public boolean hasWeaponData()
	{
		return DigType.length()>0||BuildType.length()>0||BreakType.length()>0||AttackType.length()>0||
		DigSpeed!=-1||BuildSpeed!=-1||BreakSpeed!=-1||AttackSpeed!=-1;
	}
	public boolean hasPlacementData()
	{
		return placements.size()>0;
	}
	public ArrayList<Placement> getPlacements()
	{
		return placements;
	}
	public String getItemName()
	{
	    return ItemName;
	}
	public void setItemName(String itemName)
	{
	    ItemName = itemName;
	}
	public void setItemCode(int itemCode)
	{
	    this.itemCode = itemCode;
	}
	public int getNum1()
	{
	    return num1;
	}
	public void setNum1(int num1)
	{
	    this.num1 = num1;
	}
	public int getNum2()
	{
	    return num2;
	}
	public void setNum2(int num2)
	{
	    this.num2 = num2;
	}
	public int getGourmetBookIndex()
	{
	    return GourmetBookIndex;
	}
	public void setGourmetBookIndex(int GourmetBookIndex)
	{
	    this.GourmetBookIndex = GourmetBookIndex;
	}
	public int getSpecialAttackChance()
	{
	    return SpecialAttackChance;
	}
	public void setSpecialAttackChance(int SpecialAttackChance)
	{
	    this.SpecialAttackChance = SpecialAttackChance;
	}
	public boolean getDamageImmunityPoison() 
	{
		return (DamageImmunityFlags & poisonImmunityMask) != 0;
	}
	public void setDamageImmunityPoison(boolean isImmune) 
	{
		if (isImmune)
			DamageImmunityFlags |= poisonImmunityMask;
		else
			DamageImmunityFlags &= ~poisonImmunityMask;
	}
	public boolean getDamageImmunity2()
	{
	    return (DamageImmunityFlags & immunity2Mask) != 0;
	}
	public void setDamageImmunity2(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity2Mask;
	    else
	        DamageImmunityFlags &= ~immunity2Mask;
	}
	public boolean getDamageImmunity4()
	{
	    return (DamageImmunityFlags & immunity4Mask) != 0;
	}
	public void setDamageImmunity4(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity4Mask;
	    else
	        DamageImmunityFlags &= ~immunity4Mask;
	}
	public boolean getDamageImmunity8()
	{
	    return (DamageImmunityFlags & immunity8Mask) != 0;
	}
	public void setDamageImmunity8(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity8Mask;
	    else
	        DamageImmunityFlags &= ~immunity8Mask;
	}
	public boolean getDamageImmunity10()
	{
	    return (DamageImmunityFlags & immunity10Mask) != 0;
	}
	public void setDamageImmunity10(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity10Mask;
	    else
	        DamageImmunityFlags &= ~immunity10Mask;
	}
	public boolean getDamageImmunity20()
	{
	    return (DamageImmunityFlags & immunity20Mask) != 0;
	}
	public void setDamageImmunity20(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity20Mask;
	    else
	        DamageImmunityFlags &= ~immunity20Mask;
	}
	public boolean getDamageImmunityCold()
	{
	    return (DamageImmunityFlags & coldImmunityMask) != 0;
	}
	public void setDamageImmunityCold(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= coldImmunityMask;
	    else
	        DamageImmunityFlags &= ~coldImmunityMask;
	}
	public boolean getDamageImmunityFire()
	{
	    return (DamageImmunityFlags & fireImmunityMask) != 0;
	}
	public void setDamageImmunityFire(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= fireImmunityMask;
	    else
	        DamageImmunityFlags &= ~fireImmunityMask;
	}
	public boolean getDamageImmunityWind()
	{
	    return (DamageImmunityFlags & windImmunityMask) != 0;
	}
	public void setDamageImmunityWind(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= windImmunityMask;
	    else
	        DamageImmunityFlags &= ~windImmunityMask;
	}
	public boolean getDamageImmunity200()
	{
	    return (DamageImmunityFlags & immunity200Mask) != 0;
	}
	public void setDamageImmunity200(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity200Mask;
	    else
	        DamageImmunityFlags &= ~immunity200Mask;
	}
	public boolean getDamageImmunity400()
	{
	    return (DamageImmunityFlags & immunity400Mask) != 0;
	}
	public void setDamageImmunity400(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity400Mask;
	    else
	        DamageImmunityFlags &= ~immunity400Mask;
	}
	public boolean getDamageImmunity800()
	{
	    return (DamageImmunityFlags & immunity800Mask) != 0;
	}
	public void setDamageImmunity800(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity800Mask;
	    else
	        DamageImmunityFlags &= ~immunity800Mask;
	}
	public boolean getDamageImmunity1000()
	{
	    return (DamageImmunityFlags & immunity1000Mask) != 0;
	}
	public void setDamageImmunity1000(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity1000Mask;
	    else
	        DamageImmunityFlags &= ~immunity1000Mask;
	}
	public boolean getDamageImmunity2000()
	{
	    return (DamageImmunityFlags & immunity2000Mask) != 0;
	}
	public void setDamageImmunity2000(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity2000Mask;
	    else
	        DamageImmunityFlags &= ~immunity2000Mask;
	}
	public boolean getDamageImmunity4000()
	{
	    return (DamageImmunityFlags & immunity4000Mask) != 0;
	}
	public void setDamageImmunity4000(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity4000Mask;
	    else
	        DamageImmunityFlags &= ~immunity4000Mask;
	}
	public boolean getDamageImmunity8000()
	{
	    return (DamageImmunityFlags & immunity8000Mask) != 0;
	}
	public void setDamageImmunity8000(boolean isImmune)
	{
	    if (isImmune)
	        DamageImmunityFlags |= immunity8000Mask;
	    else
	        DamageImmunityFlags &= ~immunity8000Mask;
	}
	public int getWeaponAttackSpeed()
	{
	    return WeaponAttackSpeed;
	}
	public void setWeaponAttackSpeed(int num6)
	{
	    this.WeaponAttackSpeed = num6;
	}
	public int getNum7()
	{
	    return num7;
	}
	public void setNum7(int num7)
	{
	    this.num7 = num7;
	}
	public int getNum8()
	{
	    return num8;
	}
	public void setNum8(int num8)
	{
	    this.num8 = num8;
	}
	public boolean getIsEquippableJob23()
	{
	    return (EquippableFlags & childMask) != 0;
	}
	public void setIsEquippableJob23(boolean isEquippable)
	{
		if (isEquippable)
			EquippableFlags |= childMask;
		else
			EquippableFlags &= ~childMask;
	}
	public boolean getIsEquippableJob24()
	{
	    return (EquippableFlags & adultMask) != 0;
	}
	public void setIsEquippableJob24(boolean isEquippable)
	{
		if (isEquippable)
			EquippableFlags |= adultMask;
		else
			EquippableFlags &= ~adultMask;
	}
	public boolean getIsEquippableJob25()
	{
	    return (EquippableFlags & gruntMask) != 0;
	}
	public void setIsEquippableJob25(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= gruntMask;
	    else
	        EquippableFlags &= ~gruntMask;
	}
	public boolean getIsEquippableJob26()
	{
	    return (EquippableFlags & veteranMask) != 0;
	}
	public void setIsEquippableJob26(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= veteranMask;
	    else
	        EquippableFlags &= ~veteranMask;
	}
	public boolean getIsEquippableJob27()
	{
	    return (EquippableFlags & knightMask) != 0;
	}
	public void setIsEquippableJob27(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= knightMask;
	    else
	        EquippableFlags &= ~knightMask;
	}
	public boolean getIsEquippableJob28()
	{
	    return (EquippableFlags & hunterMask) != 0;
	}
	public void setIsEquippableJob28(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= hunterMask;
	    else
	        EquippableFlags &= ~hunterMask;
	}
	public boolean getIsEquippableJob29()
	{
	    return (EquippableFlags & wizardMask) != 0;
	}
	public void setIsEquippableJob29(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= wizardMask;
	    else
	        EquippableFlags &= ~wizardMask;
	}
	public boolean getIsEquippableJob30()
	{
	    return (EquippableFlags & lumberjackMask) != 0;
	}
	public void setIsEquippableJob30(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= lumberjackMask;
	    else
	        EquippableFlags &= ~lumberjackMask;
	}
	public boolean getIsEquippableJob31()
	{
	    return (EquippableFlags & farmerMask) != 0;
	}
	public void setIsEquippableJob31(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= farmerMask;
	    else
	        EquippableFlags &= ~farmerMask;
	}
	public boolean getIsEquippableJob32()
	{
	    return (EquippableFlags & minerMask) != 0;
	}
	public void setIsEquippableJob32(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= minerMask;
	    else
	        EquippableFlags &= ~minerMask;
	}
	public boolean getIsEquippableJob33()
	{
	    return (EquippableFlags & carpenter1Mask) != 0;
	}
	public void setIsEquippableJob33(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= carpenter1Mask;
	    else
	        EquippableFlags &= ~carpenter1Mask;
	}
	public boolean getIsEquippableJob34()
	{
	    return (EquippableFlags & carpenter2Mask) != 0;
	}
	public void setIsEquippableJob34(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= carpenter2Mask;
	    else
	        EquippableFlags &= ~carpenter2Mask;
	}
	public boolean getIsEquippableJob35()
	{
	    return (EquippableFlags & carpenter3Mask) != 0;
	}
	public void setIsEquippableJob35(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= carpenter3Mask;
	    else
	        EquippableFlags &= ~carpenter3Mask;
	}
	public boolean getIsEquippableJob36()
	{
	    return (EquippableFlags & merchantMask) != 0;
	}
	public void setIsEquippableJob36(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= merchantMask;
	    else
	        EquippableFlags &= ~merchantMask;
	}
	public boolean getIsEquippableJob37()
	{
	    return (EquippableFlags & cookMask) != 0;
	}
	public void setIsEquippableJob37(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= cookMask;
	    else
	        EquippableFlags &= ~cookMask;
	}
	public boolean getIsEquippableJob38()
	{
	    return (EquippableFlags & doctorMask) != 0;
	}
	public void setIsEquippableJob38(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= doctorMask;
	    else
	        EquippableFlags &= ~doctorMask;
	}
	public boolean getIsEquippableJob39()
	{
	    return (EquippableFlags & egganMask) != 0;
	}
	public void setIsEquippableJob39(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= egganMask;
	    else
	        EquippableFlags &= ~egganMask;
	}
	public boolean getIsEquippableJob40()
	{
	    return (EquippableFlags & broadcasterMask) != 0;
	}
	public void setIsEquippableJob40(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= broadcasterMask;
	    else
	        EquippableFlags &= ~broadcasterMask;
	}
	public boolean getIsEquippableJob41()
	{
	    return (EquippableFlags & mountieMask) != 0;
	}
	public void setIsEquippableJob41(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= mountieMask;
	    else
	        EquippableFlags &= ~mountieMask;
	}
	public boolean getIsEquippableJob42()
	{
	    return (EquippableFlags & craftianMask) != 0;
	}
	public void setIsEquippableJob42(boolean isEquippable)
	{
	    if (isEquippable)
	        EquippableFlags |= craftianMask;
	    else
	        EquippableFlags &= ~craftianMask;
	}
	public String getItemModel()
	{
	    return ItemModel;
	}
	public void setItemModel(String itemModel)
	{
	    ItemModel = itemModel;
	}
	public int getNum11()
	{
	    return num11;
	}
	public void setNum11(int num11)
	{
	    this.num11 = num11;
	}
	public int getNum12()
	{
	    return num12;
	}
	public void setNum12(int num12)
	{
	    this.num12 = num12;
	}
	public int getNum13()
	{
	    return num13;
	}
	public void setNum13(int num13)
	{
	    this.num13 = num13;
	}
	public int getNum14()
	{
	    return num14;
	}
	public void setNum14(int num14)
	{
	    this.num14 = num14;
	}
	public int getNum15()
	{
	    return num15;
	}
	public void setNum15(int num15)
	{
	    this.num15 = num15;
	}
	public int getNum16()
	{
	    return num16;
	}
	public void setNum16(int num16)
	{
	    this.num16 = num16;
	}
	public int getNum17()
	{
	    return num17;
	}
	public void setNum17(int num17)
	{
	    this.num17 = num17;
	}
	public int getPrice()
	{
	    return Price;
	}
	public void setPrice(int price)
	{
	    Price = price;
	}
	public int getDamageMod()
	{
	    return damageMod;
	}
	public void setDamageMod(int damageMod)
	{
	    this.damageMod = damageMod;
	}
	public int getHPMod()
	{
	    return hpMod;
	}
	public void setHPMod(int hpMod)
	{
	    this.hpMod = (short) hpMod;
	}
	public int getNum19()
	{
	    return num19;
	}
	public void setNum19(int num19)
	{
	    this.num19 = num19;
	}
	public int getNum20()
	{
	    return num20;
	}
	public void setNum20(int num20)
	{
	    this.num20 = num20;
	}
	public int getNum21()
	{
	    return num21;
	}
	public void setNum21(int num21)
	{
	    this.num21 = num21;
	}
	public String getNewslogName()
	{
	    return NewslogName;
	}
	public void setNewslogName(String newslogName)
	{
	    NewslogName = newslogName;
	}
	public String getDisplayName()
	{
	    return DisplayName;
	}
	public void setDisplayName(String displayName)
	{
	    DisplayName = displayName;
	}
	public String getItemDescription()
	{
	    return ItemDescription;
	}
	public void setItemDescription(String itemDescription)
	{
	    ItemDescription = itemDescription;
	}
	public String getDigType()
	{
		return DigType;
	}
	public void setDigType(String Type)
	{
		DigType = Type;
	}
	public String getBuildType()
	{
		return BuildType;
	}
	public void setBuildType(String Type)
	{
		BuildType = Type;
	}
	public String getBreakType()
	{
		return BreakType;
	}
	public void setBreakType(String Type)
	{
		BreakType = Type;
	}
	public String getAttackType()
	{
		return AttackType;
	}
	public void setAttackType(String Type)
	{
		AttackType = Type;
	}
	public int getDigSpeed()
	{
		return DigSpeed;
	}
	public void setDigSpeed(int speed)
	{
		DigSpeed = speed;
	}
	public int getBuildSpeed()
	{
		return BuildSpeed;
	}
	public void setBuildSpeed(int speed)
	{
		BuildSpeed = speed;
	}
	public int getBreakSpeed()
	{
		return BreakSpeed;
	}
	public void setBreakSpeed(int speed)
	{
		BreakSpeed = speed;
	}
	public int getAttackSpeed()
	{
		return AttackSpeed;
	}
	public void setAttackSpeed(int speed)
	{
		AttackSpeed = speed;
	}
	public String getSoundEffect1()
	{
		return SoundEffect1;
	}
	public void setSoundEffect1(String SoundEffect)
	{
		SoundEffect1 = SoundEffect;
	}
	public String getSoundEffect2() 
	{
		return SoundEffect2;
	}
	public void setSoundEffect2(String SoundEffect)
	{
		SoundEffect2 = SoundEffect;
	}
	public void removePlacement(Placement placement) 
	{
		placements.remove(placement);
	}
	public Placement addNewPlacement() 
	{
		Placement ret = new Placement();
		placements.add(ret);
		return ret;
	}
	public void removeWeaponData() 
	{
		DigType = "";
		BuildType = "";
		BreakType = "";
		AttackType = "";
		DigSpeed = -1;
		BuildSpeed = -1; 
		BreakSpeed = -1;
		AttackSpeed = -1;
	}
	public void removeSoundEffect()
	{
		SoundEffect1 = "";
		SoundEffect2 = "";
	}
	public void addWeaponData() 
	{
		DigType = "dmy";
		BuildType = "dmy";
		BreakType = "dmy";
		AttackType = "dmy";
		DigSpeed = 0;
		BuildSpeed = 0; 
		BreakSpeed = 0;
		AttackSpeed = 0;
	}
	public void addSoundEffect()
	{
		SoundEffect1 = "dmy";
		SoundEffect2 = "dmy";
	}
}
