package SystemDataManagers;

import java.nio.ByteBuffer;

public class Letter 
{
	String Title = "Error";
	String Message = "Error";
	String CompletionMessage = "I certify that you\r\ncompleted this Quest.";
	int[] ItemCodes = {0,0,0,0};
	int[] ItemCounts = {0,0,0,0};
	//76 Flag Spots
	int letterID = -1;
	int flag1 = 1;
	int flag2 = -1;
	int flag3 = -1;
	int flag4 = -1;
	int flag5 = -1;
	int activationFlag = -1;
	int flag7 = -1;
	int flag8 = -1;
	int flag9 = -1;
	int flag10 = -1;
	int flag11 = -1;
	int flag12 = -1;
	int flag13 = -1;
	int flag14 = -1;
	int flag15 = -1;
	int flag16 = -1;
	int flag17 = -1;
	int flag18 = -1;
	int flag19 = -1;
	int flag20 = -1;
	int flag21 = -1;
	int flag22 = -1;
	int flag23 = -1;
	int flag24 = -1;
	int flag25 = -1;
	int flag26 = -1;
	int flag27 = -1;
	int flag28 = -1;
	int flag29 = -1;
	int flag30 = -1;
	int flag31 = -1;
	int flag32 = -1;
	int flag33 = -1;
	int flag34 = -1;
	int flag35 = -1;
	int flag36 = -1;
	int flag37 = -1;
	int flag38 = -1;
	int flag39 = -1;
	int flag40 = -1;
	int flag41 = -1;
	int flag42 = -1;
	int flag43 = -1;
	int flag44 = -1;
	int flag45 = -1;
	int flag46 = -1;
	int flag47 = -1;
	int flag48 = -1;
	int flag49 = -1;
	int flag50 = -1;
	int flag51 = -1;
	int flag52 = -1;
	int flag53 = -1;
	int flag54 = -1;
	int flag55 = -1;
	int flag56 = -1;
	int flag57 = -1;
	int flag58 = -1;
	int flag59 = -1;
	int flag60 = -1;
	int flag61 = -1;
	int flag62 = -1;
	int flag63 = -1;
	int flag64 = -1;
	int flag65 = -1;
	int flag66 = -1;
	int flag67 = -1;
	int backgroundType = -1;
	int activationQuestFlag = -1;
	int flag70 = -1;
	int flag71 = -1;
	int flag72 = -1;
	int flag73 = -1;
	int flag74 = -1;
	int isQuest = 0;
	
	public Letter(String Title, String Message, String CompletionMessage, int[] ItemCodes, int[] Flags) 
	{
		this.Title = Title;
		this.Message = Message;
		this.CompletionMessage = CompletionMessage;
		this.ItemCodes[0] = ItemCodes[0];
		this.ItemCodes[1] = ItemCodes[1];
		this.ItemCodes[2] = ItemCodes[2];
		this.ItemCodes[3] = ItemCodes[3];
		ItemCounts[0] = ItemCodes[4];
		ItemCounts[1] = ItemCodes[5];
		ItemCounts[2] = ItemCodes[6];
		ItemCounts[3] = ItemCodes[7];
		letterID = Flags[0];
		flag1 = Flags[1];
		flag2 = Flags[2];
		flag3 = Flags[3];
		flag4 = Flags[4];
		flag5 = Flags[5];
		activationFlag = Flags[6];
		flag7 = Flags[7];
		flag8 = Flags[8];
		flag9 = Flags[9];
		flag10 = Flags[10];
		flag11 = Flags[11];
		flag12 = Flags[12];
		flag13 = Flags[13];
		flag14 = Flags[14];
		flag15 = Flags[15];
		flag16 = Flags[16];
		flag17 = Flags[17];
		flag18 = Flags[18];
		flag19 = Flags[19];
		flag20 = Flags[20];
		flag21 = Flags[21];
		flag22 = Flags[22];
		flag23 = Flags[23];
		flag24 = Flags[24];
		flag25 = Flags[25];
		flag26 = Flags[26];
		flag27 = Flags[27];
		flag28 = Flags[28];
		flag29 = Flags[29];
		flag30 = Flags[30];
		flag31 = Flags[31];
		flag32 = Flags[32];
		flag33 = Flags[33];
		flag34 = Flags[34];
		flag35 = Flags[35];
		flag36 = Flags[36];
		flag37 = Flags[37];
		flag38 = Flags[38];
		flag39 = Flags[39];
		flag40 = Flags[40];
		flag41 = Flags[41];
		flag42 = Flags[42];
		flag43 = Flags[43];
		flag44 = Flags[44];
		flag45 = Flags[45];
		flag46 = Flags[46];
		flag47 = Flags[47];
		flag48 = Flags[48];
		flag49 = Flags[49];
		flag50 = Flags[50];
		flag51 = Flags[51];
		flag52 = Flags[52];
		flag53 = Flags[53];
		flag54 = Flags[54];
		flag55 = Flags[55];
		flag56 = Flags[56];
		flag57 = Flags[57];
		flag58 = Flags[58];
		flag59 = Flags[59];
		flag60 = Flags[60];
		flag61 = Flags[61];
		flag62 = Flags[62];
		flag63 = Flags[63];
		flag64 = Flags[64];
		flag65 = Flags[65];
		flag66 = Flags[66];
		flag67 = Flags[67];
		backgroundType = Flags[68];
		activationQuestFlag = Flags[69];
		flag70 = Flags[70];
		flag71 = Flags[71];
		flag72 = Flags[72];
		flag73 = Flags[73];
		flag74 = Flags[74];
		isQuest = Flags[75];
	}
	public Letter(String Title, int index)
	{
		this.Title = Title;
		Message = "Could not find a Message in file";
		CompletionMessage = "";
		letterID = index;
	}
	public byte[] getFlags()
	{
		byte[] ret = bFM.Utils.toByteArr(letterID,2);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag1,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag2,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag3,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag4,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag5,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(activationFlag,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag7,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag8,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag9,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag10,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag11,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag12,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag13,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag14,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag15,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag16,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag17,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag18,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag19,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag20,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag21,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag22,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag23,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag24,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag25,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag26,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag27,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag28,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag29,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag30,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag31,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag32,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag33,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag34,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag35,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag36,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag37,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag38,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag39,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag40,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag41,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag42,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag43,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag44,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag45,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag46,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag47,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag48,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag49,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag50,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag51,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag52,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag53,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag54,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag55,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag56,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag57,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag58,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag59,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag60,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag61,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag62,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag63,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag64,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag65,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag66,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag67,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(backgroundType,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(activationQuestFlag,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag70,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag71,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag72,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag73,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(flag74,2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(isQuest,2));
		return ret;
	}
	public String getTitle()
	{
		return Title;
	}
	public String getMessage()
	{
		return Message;
	}
	public String getCMessage()
	{
		return CompletionMessage;
	}
	public byte[] getItems()
	{
		byte[] ret = bFM.Utils.toByteArr(ItemCodes[0], 4);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(ItemCodes[1], 4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(ItemCodes[2], 4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(ItemCodes[3], 4));
		return ret;
	}
	public String toString()
	{
		String ret = "<<Quest Letter>> \"" + Title + "\"\n";
		ret += "\t<<Message>> \"" + Message + "\"\n";
		if(CompletionMessage.equals("")==false)ret += "\t<<Completion Message>> \"" + CompletionMessage + "\"\n";
		if(ItemCodes[0]!=0)
		{
			ret += "\t<<Item Codes>> (" + ItemCodes[0] + ", " + ItemCounts[0] + ")";
			if(ItemCodes[1]!=0)
			{
				ret += ", (" + ItemCodes[1] + ", " + ItemCounts[1] + ")";
				if(ItemCodes[2]!=0)
				{
					ret += ", (" + ItemCodes[2] + ", " + ItemCounts[2] + ")";
					if(ItemCodes[3]!=0)
					{
						ret += ", (" + ItemCodes[3] + ", " + ItemCounts[3] + ")";
						
					}
				}
			}
			ret += "\n";
		}
		if(letterID!=-1) ret += "\t<<Letter ID Flag>> " + letterID + "\n";
		if(flag1!=1) ret += "\t<<flag1>> " + flag1 + "\n";
		if(flag2!=-1) ret += "\t<<flag2>> " + flag2 + "\n";
		if(flag3!=-1) ret += "\t<<flag3>> " + flag3 + "\n";
		if(flag4!=-1) ret += "\t<<flag4>> " + flag4 + "\n";
		if(flag5!=-1) ret += "\t<<flag5>> " + flag5 + "\n";
		if(activationFlag!=-1) ret += "\t<<Activation Flag>> " + activationFlag + "\n";
		if(flag7!=-1) ret += "\t<<flag7>> " + flag7 + "\n";
		if(flag8!=-1) ret += "\t<<flag8>> " + flag8 + "\n";
		if(flag9!=-1) ret += "\t<<flag9>> " + flag9 + "\n";
		if(flag10!=-1) ret += "\t<<flag10>> " + flag10 + "\n";
		if(flag11!=-1) ret += "\t<<flag11>> " + flag11 + "\n";
		if(flag12!=-1) ret += "\t<<flag12>> " + flag12 + "\n";
		if(flag13!=-1) ret += "\t<<flag13>> " + flag13 + "\n";
		if(flag14!=-1) ret += "\t<<flag14>> " + flag14 + "\n";
		if(flag15!=-1) ret += "\t<<flag15>> " + flag15 + "\n";
		if(flag16!=-1) ret += "\t<<flag16>> " + flag16 + "\n";
		if(flag17!=-1) ret += "\t<<flag17>> " + flag17 + "\n";
		if(flag18!=-1) ret += "\t<<flag18>> " + flag18 + "\n";
		if(flag19!=-1) ret += "\t<<flag19>> " + flag19 + "\n";
		if(flag20!=-1) ret += "\t<<flag20>> " + flag20 + "\n";
		if(flag21!=-1) ret += "\t<<flag21>> " + flag21 + "\n";
		if(flag22!=-1) ret += "\t<<flag22>> " + flag22 + "\n";
		if(flag23!=-1) ret += "\t<<flag23>> " + flag23 + "\n";
		if(flag24!=-1) ret += "\t<<flag24>> " + flag24 + "\n";
		if(flag25!=-1) ret += "\t<<flag25>> " + flag25 + "\n";
		if(flag26!=-1) ret += "\t<<flag26>> " + flag26 + "\n";
		if(flag27!=-1) ret += "\t<<flag27>> " + flag27 + "\n";
		if(flag28!=-1) ret += "\t<<flag28>> " + flag28 + "\n";
		if(flag29!=-1) ret += "\t<<flag29>> " + flag29 + "\n";
		if(flag30!=-1) ret += "\t<<flag30>> " + flag30 + "\n";
		if(flag31!=-1) ret += "\t<<flag31>> " + flag31 + "\n";
		if(flag32!=-1) ret += "\t<<flag32>> " + flag32 + "\n";
		if(flag33!=-1) ret += "\t<<flag33>> " + flag33 + "\n";
		if(flag34!=-1) ret += "\t<<flag34>> " + flag34 + "\n";
		if(flag35!=-1) ret += "\t<<flag35>> " + flag35 + "\n";
		if(flag36!=-1) ret += "\t<<flag36>> " + flag36 + "\n";
		if(flag37!=-1) ret += "\t<<flag37>> " + flag37 + "\n";
		if(flag38!=-1) ret += "\t<<flag38>> " + flag38 + "\n";
		if(flag39!=-1) ret += "\t<<flag39>> " + flag39 + "\n";
		if(flag40!=-1) ret += "\t<<flag40>> " + flag40 + "\n";
		if(flag41!=-1) ret += "\t<<flag41>> " + flag41 + "\n";
		if(flag42!=-1) ret += "\t<<flag42>> " + flag42 + "\n";
		if(flag43!=-1) ret += "\t<<flag43>> " + flag43 + "\n";
		if(flag44!=-1) ret += "\t<<flag44>> " + flag44 + "\n";
		if(flag45!=-1) ret += "\t<<flag45>> " + flag45 + "\n";
		if(flag46!=-1) ret += "\t<<flag46>> " + flag46 + "\n";
		if(flag47!=-1) ret += "\t<<flag47>> " + flag47 + "\n";
		if(flag48!=-1) ret += "\t<<flag48>> " + flag48 + "\n";
		if(flag49!=-1) ret += "\t<<flag49>> " + flag49 + "\n";
		if(flag50!=-1) ret += "\t<<flag50>> " + flag50 + "\n";
		if(flag51!=-1) ret += "\t<<flag51>> " + flag51 + "\n";
		if(flag52!=-1) ret += "\t<<flag52>> " + flag52 + "\n";
		if(flag53!=-1) ret += "\t<<flag53>> " + flag53 + "\n";
		if(flag54!=-1) ret += "\t<<flag54>> " + flag54 + "\n";
		if(flag55!=-1) ret += "\t<<flag55>> " + flag55 + "\n";
		if(flag56!=-1) ret += "\t<<flag56>> " + flag56 + "\n";
		if(flag57!=-1) ret += "\t<<flag57>> " + flag57 + "\n";
		if(flag58!=-1) ret += "\t<<flag58>> " + flag58 + "\n";
		if(flag59!=-1) ret += "\t<<flag59>> " + flag59 + "\n";
		if(flag60!=-1) ret += "\t<<flag60>> " + flag60 + "\n";
		if(flag61!=-1) ret += "\t<<flag61>> " + flag61 + "\n";
		if(flag62!=-1) ret += "\t<<flag62>> " + flag62 + "\n";
		if(flag63!=-1) ret += "\t<<flag63>> " + flag63 + "\n";
		if(flag64!=-1) ret += "\t<<flag64>> " + flag64 + "\n";
		if(flag65!=-1) ret += "\t<<flag65>> " + flag65 + "\n";
		if(flag66!=-1) ret += "\t<<flag66>> " + flag66 + "\n";
		if(flag67!=-1) ret += "\t<<flag67>> " + flag67 + "\n";
		if(backgroundType!=-1) ret += "\t<<Background Type Flag>> " + backgroundType + "\n";
		if(activationQuestFlag!=-1) ret += "\t<<While Active Flag>> " + activationQuestFlag + "\n";
		if(flag70!=-1) ret += "\t<<flag70>> " + flag70 + "\n";
		if(flag71!=-1) ret += "\t<<flag71>> " + flag71 + "\n";
		if(flag72!=-1) ret += "\t<<flag72>> " + flag72 + "\n";
		if(flag73!=-1) ret += "\t<<flag73>> " + flag73 + "\n";
		if(flag74!=-1) ret += "\t<<flag74>> " + flag74 + "\n";
		if(isQuest!=0) ret += "\t<<Is Enabled>>\n";
		return ret;
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<Message>>")!=-1)
		{
			Message = bFM.Utils.formatString(line);
			return;
		}
		else if(line.indexOf("<<Completion Message>>")!=-1)
		{
			CompletionMessage = bFM.Utils.formatString(line);
			return;
		}
		else if(line.indexOf("<<Item Codes>>")!=-1)
		{
			itemLine(line);
			return;
		}
		else if(line.indexOf("<<flag")!=-1||line.indexOf("Flag>>")!=-1)
		{
			if(line.indexOf("<<Letter ID Flag>>")!=-1) letterID=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag1>>")!=-1) flag1=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag2>>")!=-1) flag2=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag3>>")!=-1) flag3=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag4>>")!=-1) flag4=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag5>>")!=-1) flag5=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Activation Flag>>")!=-1) activationFlag=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag7>>")!=-1) flag7=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag8>>")!=-1) flag8=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag9>>")!=-1) flag9=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag10>>")!=-1) flag10=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag11>>")!=-1) flag11=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag12>>")!=-1) flag12=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag13>>")!=-1) flag13=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag14>>")!=-1) flag14=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag15>>")!=-1) flag15=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag16>>")!=-1) flag16=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag17>>")!=-1) flag17=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag18>>")!=-1) flag18=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag19>>")!=-1) flag19=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag20>>")!=-1) flag20=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag21>>")!=-1) flag21=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag22>>")!=-1) flag22=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag23>>")!=-1) flag23=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag24>>")!=-1) flag24=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag25>>")!=-1) flag25=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag26>>")!=-1) flag26=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag27>>")!=-1) flag27=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag28>>")!=-1) flag28=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag29>>")!=-1) flag29=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag30>>")!=-1) flag30=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag31>>")!=-1) flag31=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag32>>")!=-1) flag32=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag33>>")!=-1) flag33=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag34>>")!=-1) flag34=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag35>>")!=-1) flag35=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag36>>")!=-1) flag36=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag37>>")!=-1) flag37=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag38>>")!=-1) flag38=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag39>>")!=-1) flag39=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag40>>")!=-1) flag40=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag41>>")!=-1) flag41=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag42>>")!=-1) flag42=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag43>>")!=-1) flag43=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag44>>")!=-1) flag44=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag45>>")!=-1) flag45=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag46>>")!=-1) flag46=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag47>>")!=-1) flag47=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag48>>")!=-1) flag48=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag49>>")!=-1) flag49=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag50>>")!=-1) flag50=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag51>>")!=-1) flag51=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag52>>")!=-1) flag52=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag53>>")!=-1) flag53=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag54>>")!=-1) flag54=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag55>>")!=-1) flag55=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag56>>")!=-1) flag56=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag57>>")!=-1) flag57=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag58>>")!=-1) flag58=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag59>>")!=-1) flag59=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag60>>")!=-1) flag60=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag61>>")!=-1) flag61=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag62>>")!=-1) flag62=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag63>>")!=-1) flag63=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag64>>")!=-1) flag64=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag65>>")!=-1) flag65=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag66>>")!=-1) flag66=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag67>>")!=-1) flag67=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<Background Type Flag>>")!=-1) backgroundType=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<While Active Flag>>")!=-1) activationQuestFlag=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag70>>")!=-1) flag70=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag71>>")!=-1) flag71=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag72>>")!=-1) flag72=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag73>>")!=-1) flag73=bFM.Utils.formatFlag(line);
			if(line.indexOf("<<flag74>>")!=-1) flag74=bFM.Utils.formatFlag(line);
			return;
		}
		if(line.indexOf("<<Is Enabled>>")!=-1) isQuest=1;
	}
	private void itemLine(String line) 
	{
		String allowedChars = "1234567890-";
		String tempNum = "";
		line = line.substring(line.indexOf('(')+1);
		for(int i = 0; i<line.length(); i++)
		{
			if(line.charAt(i)==')')
			{
				break;
			}
			if(line.charAt(i)==',')
			{
				ItemCodes[0] = Integer.parseInt(tempNum);
				tempNum = "";
				break;
			}
			if(allowedChars.indexOf(line.charAt(i))!=-1)
			{
				tempNum += line.charAt(i);
			}
		}
		line = line.substring(line.indexOf(',')+1);
		for(int i = 0; i<line.length(); i++)
		{
			if(line.charAt(i)==')')
			{
				ItemCounts[0] = Integer.parseInt(tempNum);
				tempNum = "";
				break;
			}
			if(allowedChars.indexOf(line.charAt(i))!=-1)
			{
				tempNum += line.charAt(i);
			}
		}
		if(line.indexOf('(')!=-1&&line.length()>0)
		{
			line = line.substring(line.indexOf('(')+1);
			for(int i = 0; i<line.length(); i++)
			{
				if(line.charAt(i)==',')
				{
					ItemCodes[1] = Integer.parseInt(tempNum);
					tempNum = "";
					break;
				}
				if(allowedChars.indexOf(line.charAt(i))!=-1)
				{
					tempNum += line.charAt(i);
				}
			}
			line = line.substring(line.indexOf(',')+1);
			for(int i = 0; i<line.length(); i++)
			{
				if(line.charAt(i)==')')
				{
					ItemCounts[1] = Integer.parseInt(tempNum);
					tempNum = "";
					break;
				}
				if(allowedChars.indexOf(line.charAt(i))!=-1)
				{
					tempNum += line.charAt(i);
				}
			}
			if(line.indexOf('(')!=-1&&line.length()>0)
			{
				line = line.substring(line.indexOf('(')+1);
				for(int i = 0; i<line.length(); i++)
				{
					if(line.charAt(i)==',')
					{
						ItemCodes[2] = Integer.parseInt(tempNum);
						tempNum = "";
						break;
					}
					if(allowedChars.indexOf(line.charAt(i))!=-1)
					{
						tempNum += line.charAt(i);
					}
				}
				line = line.substring(line.indexOf(',')+1);
				for(int i = 0; i<line.length(); i++)
				{
					if(line.charAt(i)==')')
					{
						ItemCounts[2] = Integer.parseInt(tempNum);
						tempNum = "";
						break;
					}
					if(allowedChars.indexOf(line.charAt(i))!=-1)
					{
						tempNum += line.charAt(i);
					}
				}
				if(line.indexOf('(')!=-1&&line.length()>0)
				{
					line = line.substring(line.indexOf('(')+1);
					for(int i = 0; i<line.length(); i++)
					{
						if(line.charAt(i)==',')
						{
							ItemCodes[3] = Integer.parseInt(tempNum);
							tempNum = "";
							break;
						}
						if(allowedChars.indexOf(line.charAt(i))!=-1)
						{
							tempNum += line.charAt(i);
						}
					}
					line = line.substring(line.indexOf(',')+1);
					for(int i = 0; i<line.length(); i++)
					{
						if(line.charAt(i)==')')
						{
							ItemCounts[3] = Integer.parseInt(tempNum);
							tempNum = "";
							break;
						}
						if(allowedChars.indexOf(line.charAt(i))!=-1)
						{
							tempNum += line.charAt(i);
						}
					}
				}
			}
		}
		
	}
	public byte[] titleBytes() 
	{
		return bFM.Utils.mergeArrays(Title.getBytes(), new byte[1]);
	}
	public byte[] textBytes() 
	{
		return bFM.Utils.mergeArrays(Message.getBytes(), new byte[1]);
	}
	public byte[] completionBytes() 
	{
		return bFM.Utils.mergeArrays(CompletionMessage.getBytes(), new byte[1]);
	}
	public byte[] itemBytes() 
	{
		
		byte[] ret = ByteBuffer.allocate(4).putInt(ItemCodes[0]).array();
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(ItemCodes[1]).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(ItemCodes[2]).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(ItemCodes[3]).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(ItemCounts[0]).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(ItemCounts[1]).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(ItemCounts[2]).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(ItemCounts[3]).array());
		return ret;
	}
}
