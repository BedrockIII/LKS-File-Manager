package SystemDataManagers.MenuDB.KingdomPlanManager;

import bFM.Data;

public class KingdomPlanElement implements Data
{
	String Name = "";
	String Description = "";
	String Image = "";
	int AreaCode = -1;
	int Price = -1;
	int OR_ActivationFlag1 = -1;//Any of these 4 is combined with latter
	int OR_ActivationFlag2 = -1;
	int OR_ActivationFlag3 = -1;
	int OR_ActivationFlag4 = -1;
	int AND_ActivationFlag1 = -1;//Both this and the other must be set and true
	int AND_ActivationFlag2 = -1;
	int PopulationMinimum = -1;
	int PrereqPurchaseFlag = -1;//Must be true always
	int PurchaseFlag = -1;
	int ProgranType = -1; //CDPMenuDevelopment::ProgramTypeCheck
	// 11 - Badge Count _____, change
	// 10 - Add Unit    JobCode, Count
	// 9  - Add Item    count, Code
	// 4  - Add HP      ?????, Change
	// 3  - Add Pot Spots
	// 2  - ??? all normal plans
	// 0 NOP
	// 1 Kingdom Plan -> Flower [--]
	// 2 Kingdom Plan -> Development addValue[SpecialVar2], cmpValue[SpecialVar1]
	// 3 Kingdom Plan -> Reinforcement Pot [SpecialVar2]
	// 4 Kingdom Plan -> HP +[SpecialVar2]"
	// 5 Kingdom Plan -> AT +[SpecialVar2]"
	// 6 Kingdom Plan -> Job Change [SpecialVar1] -> [SpecialVar2]"
	// 7 Kingdom Plan -> Job Change Man [SpecialVar1] -> [SpecialVar2]"
	// 8 Kingdom Plan -> Job Change (Female) [SpecialVar1] -> [SpecialVar2]"
	// 9 Kingdom Plan -> Item Obtained [SpecialVar2]\n"
	// 10 removed NOP
	// 11 Kingdom Plan -> Badges Gained [SpecialVar2]"
	int SpecialVar1 = -1;
	int SpecialVar2 = -1;
	int cockpitLogCode = -1;
	int flag16 = -1;
	public KingdomPlanElement(String Name, String Description, String Image, int[] Flags)
	{
		this.Name = Name;
		this.Description = Description;
		this.Image = Image;
		AreaCode = Flags[0];
		Price = Flags[1];
		OR_ActivationFlag1 = Flags[2];
		OR_ActivationFlag2 = Flags[3];
		OR_ActivationFlag3 = Flags[4];
		OR_ActivationFlag4 = Flags[5];
		AND_ActivationFlag1 = Flags[6];
		AND_ActivationFlag2 = Flags[7];
		PopulationMinimum = Flags[8];
		PrereqPurchaseFlag = Flags[9];
		PurchaseFlag = Flags[10];
		ProgranType = Flags[11];
		SpecialVar1 = Flags[12];
		SpecialVar2 = Flags[13];
		cockpitLogCode = Flags[14];
		flag16 = Flags[15];
	}
	public KingdomPlanElement(String NameLine, int AreaCode)
	{
		this.AreaCode = AreaCode;
		Name = bFM.Utils.formatString(NameLine);
	}
	public KingdomPlanElement()
	{
		Name = "New Element";
		Description = "";
		Image = "SP_00";
		AreaCode = -1;
		Price = -1;
		OR_ActivationFlag1 = -1;
		OR_ActivationFlag2 = -1;
		OR_ActivationFlag3 = -1;
		OR_ActivationFlag4 = -1;
		AND_ActivationFlag1 = -1;
		AND_ActivationFlag2 = -1;
		PopulationMinimum = -1;
		PrereqPurchaseFlag = -1;
		PurchaseFlag = -1;
		ProgranType = 2;
		SpecialVar1 = -1;
		SpecialVar2 = -1;
		cockpitLogCode = -1;
		flag16 = -1;
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
	public byte[] getFlags(int areaIndex)
	{
		AreaCode = areaIndex;
		byte[] ret = bFM.Utils.toByteArr(AreaCode,4);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(Price,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(OR_ActivationFlag1,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(OR_ActivationFlag2,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(OR_ActivationFlag3,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(OR_ActivationFlag4,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(AND_ActivationFlag1,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(AND_ActivationFlag2,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(PopulationMinimum,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(PrereqPurchaseFlag,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(PurchaseFlag,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(ProgranType,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(SpecialVar1,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(SpecialVar2,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(cockpitLogCode,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag16,4));
		return ret;
	}
	public int getAreaIndex()
	{
		return AreaCode;
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<Element Description>>")!=-1)
		{
			Description = bFM.Utils.formatString(line);
			return;
		}
		if(line.indexOf("<<Element Image>>")!=-1)
		{
			Image = bFM.Utils.formatString(line);
			return;
		}
		else if(line.indexOf("<<")!=-1&&line.indexOf(">>")!=-1)
		{
			if(line.indexOf("<<Price>>")!=-1) Price=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Activation Flag>>")!=-1) OR_ActivationFlag1=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Alternative Activation Flag>>")!=-1) OR_ActivationFlag2=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag5>>")!=-1) OR_ActivationFlag3=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag6>>")!=-1) OR_ActivationFlag4=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag7>>")!=-1) AND_ActivationFlag1=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag8>>")!=-1) AND_ActivationFlag2=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Population Minimum>>")!=-1) PopulationMinimum=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Previous Purchase Flag>>")!=-1) PrereqPurchaseFlag=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Purchase Flag>>")!=-1) PurchaseFlag=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag12>>")!=-1) ProgranType=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Special Variable Type>>")!=-1) ProgranType=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag13>>")!=-1) SpecialVar1=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Special Variable 1>>")!=-1) SpecialVar1=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag14>>")!=-1) SpecialVar2=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Special Variable 2>>")!=-1) SpecialVar2=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Cockpit Log Code>>")!=-1) cockpitLogCode=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag16>>")!=-1) flag16=bFM.Utils.formatFlag(line);
			return;
		}
	}
	public String toString()
	{
		String ret = "\t<<Element Name>> \"" + Name + "\"\n";
		ret += "\t\t<<Element Description>> \"" + Description + "\"\n";
		ret += "\t\t<<Element Image>> \"" + Image + "\"\n";
		if(Price!=-1) ret += "\t\t<<Price>> " + Price + "\n";
		if(OR_ActivationFlag1!=-1) ret += "\t\t<<Activation Flag>> " + OR_ActivationFlag1 + "\n";
		if(OR_ActivationFlag2!=-1) ret += "\t\t<<Alternative Activation Flag>> " + OR_ActivationFlag2 + "\n";
		if(OR_ActivationFlag3!=-1) ret += "\t\t<<flag5>> " + OR_ActivationFlag3 + "\n";
		if(OR_ActivationFlag4!=-1) ret += "\t\t<<flag6>> " + OR_ActivationFlag4 + "\n";
		if(AND_ActivationFlag1!=-1) ret += "\t\t<<flag7>> " + AND_ActivationFlag1 + "\n";
		if(AND_ActivationFlag2!=-1) ret += "\t\t<<flag8>> " + AND_ActivationFlag2 + "\n";
		if(PopulationMinimum!=-1) ret += "\t\t<<Population Minimum>> " + PopulationMinimum + "\n";
		if(PrereqPurchaseFlag!=-1) ret += "\t\t<<Previous Purchase Flag>> " + PrereqPurchaseFlag + "\n";
		if(PurchaseFlag!=-1) ret += "\t\t<<Purchase Flag>> " + PurchaseFlag + "\n";
		if(ProgranType!=-1) ret += "\t\t<<Special Variable Type>> " + ProgranType + "\n";
		if(SpecialVar1!=-1) ret += "\t\t<<Special Variable 1>> " + SpecialVar1 + "\n";
		if(SpecialVar2!=-1) ret += "\t\t<<Special Variable 2>> " + SpecialVar2 + "\n";
		if(cockpitLogCode!=-1) ret += "\t\t<<Cockpit Log Code>> " + cockpitLogCode + "\n";
		if(flag16!=-1) ret += "\t\t<<flag16>> " + flag16 + "\n";
		return ret;
	}
	public void setName(String name)
	{
		Name = name;
	}
	public void setDescription(String text) 
	{
		Description = text;
	}
	public void setImage(String text) 
	{
		Image = text;
	}
	public int getPrice() 
	{
		return Price;
	}
	public void setPrice(int num) 
	{
		Price = num;
	}
	public int getActivationFlag() 
	{
		return OR_ActivationFlag1;
	}
	public void setActivationFlag(int num) 
	{
		OR_ActivationFlag1 = num;
	}
	public int getAltActivationFlag() 
	{
		return OR_ActivationFlag2;
	}
	public void setAltActivationFlag(int num) 
	{
		OR_ActivationFlag2 = num;
	}
	public int getFlag5() 
	{
		return OR_ActivationFlag3;
	}
	public void setFlag5(int num) 
	{
		OR_ActivationFlag3 = num;
	}
	public int getFlag6() 
	{
		return OR_ActivationFlag4;
	}
	public int getFlag7() 
	{
		return AND_ActivationFlag1;
	}
	public int getFlag8() 
	{
		return AND_ActivationFlag2;
	}
	public int getSpecialVariableType() 
	{
		return ProgranType;
	}
	public int getSpecialVar1() 
	{
		return SpecialVar1;
	}
	public int getSpecialVar2() 
	{
		return SpecialVar2;
	}
	public int getFlag16() 
	{
		return flag16;
	}
	public int getPopulationMinimum() 
	{
		return PopulationMinimum;
	}
	public int getPrereqPurchaseFlag() 
	{
		return PrereqPurchaseFlag;
	}
	public int getPurchaseFlag() 
	{
		return PurchaseFlag;
	}
	public int getCockpitLogCode() 
	{
		return cockpitLogCode;
	}
	public void setFlag6(int num) 
	{
		OR_ActivationFlag4 = num;
	}
	public void setFlag7(int num) 
	{
		AND_ActivationFlag1 = num;
	}
	public void setFlag8(int num) 
	{
		AND_ActivationFlag2 = num;
	}
	public void setSpecialVariableType(int num) 
	{
		ProgranType = num;
	}
	public void setSpecialVar1(int num) 
	{
		SpecialVar1 = num;
	}
	public void setSpecialVar2(int num) 
	{
		SpecialVar2 = num;
	}
	public void setFlag16(int num) 
	{
		flag16 = num;
	}
	public void setPopulationMinimum(int num) 
	{
		PopulationMinimum = num;
	}
	public void setPrereqPurchaseFlag(int num) 
	{
		PrereqPurchaseFlag = num;
	}
	public void setPurchaseFlag(int num) 
	{
		PurchaseFlag = num;
	}
	public void setCockpitLogCode(int num) 
	{
		cockpitLogCode = num;
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
