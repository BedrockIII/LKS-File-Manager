package SystemDataManagers.KingdomPlanManager;

public class KingdomPlanElement 
{
	String Name = "";
	String Description = "";
	String Image = "";
	int AreaCode = -1;
	int Price = -1;
	int ActivationFlag = -1;
	int AltActivationFlag = -1;
	int flag5 = -1;
	int flag6 = -1;
	int flag7 = -1;
	int flag8 = -1;
	int PopulationMinimum = -1;
	int PrereqPurchaseFlag = -1;
	int PurchaseFlag = -1;
	int SpecialVariableType = -1;
	// 11 - BAdge Count _____, change
	// 10 - Add Unit    JobCode, Count
	// 9  - Add Item    count, Code
	// 4  - Add HP      ?????, Change
	// 3  - Add Pot Spots
	// 2  - ??? all normal plans
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
		ActivationFlag = Flags[2];
		AltActivationFlag = Flags[3];
		flag5 = Flags[4];
		flag6 = Flags[5];
		flag7 = Flags[6];
		flag8 = Flags[7];
		PopulationMinimum = Flags[8];
		PrereqPurchaseFlag = Flags[9];
		PurchaseFlag = Flags[10];
		SpecialVariableType = Flags[11];
		SpecialVar1 = Flags[12];
		SpecialVar2 = Flags[13];
		cockpitLogCode = Flags[14];
		flag16 = Flags[15];
	}
	public KingdomPlanElement(String NameLine, int AreaCode)
	{
		this.AreaCode = AreaCode;
		Name = bFM.Utils.formatString(NameLine);
	}public KingdomPlanElement()
	{
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
	public byte[] getFlags()
	{
		byte[] ret = bFM.Utils.toByteArr(AreaCode,4);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(Price,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(ActivationFlag,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(AltActivationFlag,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag5,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag6,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag7,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag8,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(PopulationMinimum,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(PrereqPurchaseFlag,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(PurchaseFlag,4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(SpecialVariableType,4));
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
			if(line.indexOf("<<Activation Flag>>")!=-1) ActivationFlag=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Alternative Activation Flag>>")!=-1) AltActivationFlag=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag5>>")!=-1) flag5=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag6>>")!=-1) flag6=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag7>>")!=-1) flag7=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag8>>")!=-1) flag8=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Population Minimum>>")!=-1) PopulationMinimum=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Previous Purchase Flag>>")!=-1) PrereqPurchaseFlag=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Purchase Flag>>")!=-1) PurchaseFlag=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag12>>")!=-1) SpecialVariableType=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Special Variable Type>>")!=-1) SpecialVariableType=bFM.Utils.formatFlag(line);
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
		if(ActivationFlag!=-1) ret += "\t\t<<Activation Flag>> " + ActivationFlag + "\n";
		if(AltActivationFlag!=-1) ret += "\t\t<<Alternative Activation Flag>> " + AltActivationFlag + "\n";
		if(flag5!=-1) ret += "\t\t<<flag5>> " + flag5 + "\n";
		if(flag6!=-1) ret += "\t\t<<flag6>> " + flag6 + "\n";
		if(flag7!=-1) ret += "\t\t<<flag7>> " + flag7 + "\n";
		if(flag8!=-1) ret += "\t\t<<flag8>> " + flag8 + "\n";
		if(PopulationMinimum!=-1) ret += "\t\t<<Population Minimum>> " + PopulationMinimum + "\n";
		if(PrereqPurchaseFlag!=-1) ret += "\t\t<<Previous Purchase Flag>> " + PrereqPurchaseFlag + "\n";
		if(PurchaseFlag!=-1) ret += "\t\t<<Purchase Flag>> " + PurchaseFlag + "\n";
		if(SpecialVariableType!=-1) ret += "\t\t<<Special Variable Type>> " + SpecialVariableType + "\n";
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
		return ActivationFlag;
	}
	public void setActivationFlag(int num) 
	{
		ActivationFlag = num;
	}
	public int getAltActivationFlag() 
	{
		return AltActivationFlag;
	}
	public void setAltActivationFlag(int num) 
	{
		AltActivationFlag = num;
	}
	public int getFlag5() 
	{
		return flag5;
	}
	public void setFlag5(int num) 
	{
		flag5 = num;
	}
	public int getFlag6() 
	{
		return flag6;
	}
	public int getFlag7() 
	{
		return flag7;
	}
	public int getFlag8() 
	{
		return flag8;
	}
	public int getSpecialVariableType() 
	{
		return SpecialVariableType;
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
		flag6 = num;
	}
	public void setFlag7(int num) 
	{
		flag7 = num;
	}
	public void setFlag8(int num) 
	{
		flag8 = num;
	}
	public void setSpecialVariableType(int num) 
	{
		SpecialVariableType = num;
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
}
