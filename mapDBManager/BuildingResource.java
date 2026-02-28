package mapDBManager;

import java.util.ArrayList;

public class BuildingResource 
{
	//DAT
	int buildingCode = -1;
	int interactionType = 1;
	int num0 = -1;
	int num02 = -1;
	int width = 0;
	int depth = 0;
	int linkCode1 = -1;
	int linkCode2 = -1;
	//DAT2
	int trainCode = -1;
	int num1 = -1;
	int num2 = -1;
	int speakCode = -1;
	//MDL
	int num3 = -1;
	int num4 = -1;
	String filePath = "Null Path";
	String name = "Null Name";
	//Optional ANM
	ArrayList<String> Animations = new ArrayList<String>();
	//MS
	int mobCode = -1;
	int secondMobCode = -1;//Maybe???
	int mobCodeHP = 1;//NEEDS A SPACE 0x20 AFTER IT WHEN WRITING
	int num5 = -1;
	int num6 = -1;
	public BuildingResource(String DatList)
	{
		String[] datLine = bFM.Utils.toStrArr(DatList);
		//System.out.println(Arrays.toString(datLine));
		if(datLine.length!=8)return;
		
		buildingCode = bFM.Utils.strToInt(datLine[0]);
		
		interactionType = bFM.Utils.strToInt(datLine[1]);
		num0 = bFM.Utils.strToInt(datLine[2]);
		num02 = bFM.Utils.strToInt(datLine[3]);
		width = bFM.Utils.strToInt(datLine[4]);
		depth = bFM.Utils.strToInt(datLine[5]);
		linkCode1 = bFM.Utils.strToInt(datLine[6]);
		linkCode2 = bFM.Utils.strToInt(datLine[7]);
	}
	public void addLine(String list)
	{
		if(list.indexOf("DAT2 ")!=-1)
		{
			addDAT2(list);
		}
		else if(list.indexOf("MDL ")!=-1)
		{
			addMDL(list);
		}
		else if(list.indexOf("ANM")!=-1)
		{
			addANM(list);
		}
		else if(list.indexOf("MS ")!=-1)
		{
			addMS(list);
		}
		
	}
	private void addDAT2(String list) 
	{
		String[] datLine = bFM.Utils.toStrArr(list);
		trainCode = bFM.Utils.strToInt(datLine[1]);
		num1 = bFM.Utils.strToInt(datLine[2]);
		num2 = bFM.Utils.strToInt(datLine[3]);
		speakCode = bFM.Utils.strToInt(datLine[4]);
		//System.out.println(Arrays.toString(datLine));
	}
	private void addMDL(String list) 
	{
		String[] datLine = bFM.Utils.toStrArr(list);
		num3 = bFM.Utils.strToInt(datLine[0]);
		num4 = bFM.Utils.strToInt(datLine[1]);
		filePath = datLine[2];
		name = datLine[3];
		//System.out.println(Arrays.toString(datLine));
	}
	private void addANM(String list) 
	{
		Animations.add(list);
	}
	private void addMS(String list) 
	{
		String[] datLine = bFM.Utils.toStrArr(list);
		mobCode = bFM.Utils.strToInt(datLine[1]);
		secondMobCode = bFM.Utils.strToInt(datLine[2]);
		mobCodeHP = bFM.Utils.strToInt(datLine[3]);
		num5 = bFM.Utils.strToInt(datLine[4]);
		num6 = bFM.Utils.strToInt(datLine[4]);
		//System.out.println(Arrays.toString(datLine));
	}
	public int getCode() 
	{
		return buildingCode;
	}
	public void setTrainingCode(int code) 
	{
		trainCode = code;
	}
	public void setSpeakingCode(int code) 
	{
		speakCode = code;
	}
	public void setInteractionType(int code) 
	{
		interactionType = code;
	}
	public String toString()
	{// + "," + 
		String ret = "DAT " + buildingCode + "," + interactionType + "," + num0 + "," + num02 + "," + width + "," + depth + "," + linkCode1 + "," + linkCode2 + ";\n";
		ret+="DAT2 " + buildingCode + "," + trainCode + "," + num1 + "," + num2 + "," + speakCode + ";\n";
		ret+="MDL " + num3 + "," + num4 + "," + filePath + "," + name + ";\n";
		for(String str : Animations)
			ret += str;
		ret+="MS " + buildingCode + "," + mobCode + "," + secondMobCode + "," + mobCodeHP + " ," + num5 + "," + num6 + ";\n";
		return ret;
	}
}
