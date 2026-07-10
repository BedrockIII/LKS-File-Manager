package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.util.Arrays;

import bFM.Utils;

public class MobMod 
{
	int modCode = -1;
	int summonIDOnDeath = -1; //becomes this UMA when activated
	int health = -1;
	int num3 = 2560;
	int LootTableID = -1;
	int ReferenceSaveDataArrayID = -1;//Name in UMA Book
	int num6 = -1;
	int num7 = -1;
	int num8 = 0;
	/* for Mission Type  Flags
	0x01; Is Impossible
	0x02; Is a Boss
	0x04; 
	0x08; 
	0x10; 
	0x20;
	0x40;
	0x80;
	*/
	int MissionTypeFlags1 = 0;
	int StackLimit = 20;
	int EffectVulnerabilityFlags = 514;
	int ModelResourceID = -1;
	/*
	0x0001;Small Smoke when spawned
	0x0002;
	0x0004;
	0x0008;
	0x0010;
	0x0020;
	0x0040;
	0x0080;
	0x0100;
	0x0200;
	0x0400;
	0x0800;
	0x1000;
	0x2000;
	0x4000;
	0x8000;
	*/
	int BasicAttackID = -1;// 5th bit from left determines if HP Bar Shows
	/*
	0x0001;
	0x0002;
	0x0004;
	0x0008;
	0x0010;
	0x0020;
	0x0040;
	0x0080;
	0x0100;
	0x0200;
	0x0400;
	0x0800;
	0x1000;
	0x2000;
	0x4000;
	0x8000;
	*/
	byte num15a = 0;
	/* for Mission Type  Flags
	0x01; 
	0x02; Movement Type Flag
	0x04; 
	0x08; 
	0x10; 
	0x20;
	0x40;
	0x80;
	*/
	byte MissionTypeFlags2 = 0;
	byte MissionType = 0;
	byte EntranceFlags = 0;
	/*
	0x01; Small Smoke
	0x02; Medium Smoke
	0x04; Large Smoke
	0x08; Ground
	0x10; Scale
	0x20;
	0x40;
	0x80;
	*/
	byte DisapearanceFlags = 0;
	byte num17b = 0;
	byte MapIconType = 0;
	byte LightType = 0;
	byte HPDisplayType = 0;
	byte NPCReactionLimit = 0;
	byte IdleMovementSpeedTableIndex = 0;
	byte IdleRotationSpeedTableIndex = 0;
	byte AttackMovementSpeedTableIndex = 0;
	byte AttackRotationSpeedTableIndex = 0;
	byte num22a = 0;
	byte HPDisplayDuringState = 0;
	byte StateMapIconType = 0;
	byte DiscoveryLogType = 0;
	byte num24a = 0;
	byte num24b = 0;
	byte num25a = 0;
	byte num25b = 0;
	float num26 = 0;
	String name;//32
	public MobMod(byte[] data)
	{
		ByteBuffer data2 = ByteBuffer.wrap(data);
		modCode = bFM.Utils.getShort(data, 0);
		summonIDOnDeath= bFM.Utils.getShort(data, 2);
		health= bFM.Utils.getShort(data, 4);
		num3 = bFM.Utils.getShort(data, 6);
		LootTableID = bFM.Utils.getShort(data, 8);
		ReferenceSaveDataArrayID = bFM.Utils.getShort(data, 10);
		num6 = bFM.Utils.getShort(data, 12);
		num7 = bFM.Utils.getShort(data, 14);
		num8 = bFM.Utils.getShort(data, 16);
		MissionTypeFlags1 = bFM.Utils.getShort(data, 18);
		StackLimit = bFM.Utils.getShort(data, 20);
		EffectVulnerabilityFlags = bFM.Utils.getShort(data, 22);
		ModelResourceID = data2.getInt(24);
		BasicAttackID = bFM.Utils.getShort(data, 28);
		num15a = data[30];
		MissionTypeFlags2 = data[31];
		MissionType = data[32];
		EntranceFlags = data[33];
		DisapearanceFlags = data[34];
		num17b = data[35];
		MapIconType = data[36];
		LightType = data[37];
		HPDisplayType = data[38];
		NPCReactionLimit = data[39];
		IdleMovementSpeedTableIndex = data[40];
		IdleRotationSpeedTableIndex = data[41];
		AttackMovementSpeedTableIndex = data[42];
		AttackRotationSpeedTableIndex = data[43];
		num22a = data[44];
		HPDisplayDuringState = data[45];
		StateMapIconType = data[46];
		DiscoveryLogType = data[47];
		num24a = data[48];
		num24b = data[49];
		num25a = data[50];
		num25b = data[51];
		num26 = data2.getFloat(52);
		name = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 56, 88)));
	}
	public MobMod()
	{
		
	}
	String modLine = null;
	public MobMod(String modLine)
	{
		//Depreciated
		this.modLine = modLine;
		modCode = getInt(0);
		summonIDOnDeath= getInt(1);
		health= getInt(2);
		num3 = getInt(3);
		LootTableID = getInt(4);
		ReferenceSaveDataArrayID = getInt(5);
		num6 = getInt(6);
		num7 = getInt(7);
		num8 = getInt(8);
		MissionTypeFlags1 = getInt(9);
		StackLimit = getInt(10);
		EffectVulnerabilityFlags = getInt(11);
		ModelResourceID = getInt(13);
		BasicAttackID = getInt(14);
		//num15 = getInt(15);
		//num16 = getInt(16);
		//num17 = getInt(17);
		//num18 = getInt(18);//Split into 2
		//num19 = getInt(19);
		//num20 = getInt(20);
		//num21 = getInt(21);
		//num22 = getInt(22);
		//num23 = getInt(23);
		//num24 = getInt(24);
		//num25 = getInt(25);
		int num26a = getInt(26);
		int num26b = getInt(27);
		num26 = ByteBuffer.allocate(4).putShort((short) num26a).putShort((short) num26b).getFloat(0);
		name = bFM.Utils.formatString(this.modLine);
	}
	private int getInt(int index)
	{
		if(modLine==null)return -1;
		int startIndex = 0;
		for(int i = 0; i<modLine.length(); i++)
		{
			if(startIndex==index)
			{
				startIndex = i;
				break;
			}
			if(modLine.charAt(i)==',')
			{
				startIndex++;
			}
		}
		String ret = "";
		String validChars = "1234567890-.";
		for(int i = startIndex; i<modLine.length()&&modLine.charAt(i)!=','; i++)
		{
			if(validChars.indexOf(modLine.charAt(i))!=-1)
			{
				ret+=modLine.charAt(i);
			}
		}
		
		return Integer.parseInt(ret);
	}
	public String toString()
	{
		return "Mob Mod "+modCode +": Error";//+summonIDOnDeath+", "+health+", "+num3 +", "+LootTableID +", "+ReferenceSaveDataArrayID +", "+num6 +", "+num7 +", "+num8 +", "+BossSettingID + ", " +
				//StackLimit +", "+EffectVulnerabilityFlags +", "+0 +", "+ModelResourceID +", "+BasicAttackID +", "+num15 +", "+num16 +", "+num17 +", "+num18 +", "+num19 +", " +
				//num20 +", "+num21 +", "+HPDisplayTypeDuringState +", "+num23 +", "+num24 +", "+num25 +", "+num26 + ", \""+name+"\"\n";
	}
	public String toHP()
	{
		return name+", HP:"+health+ "\n";
	}
	public byte[] toBytes()
	{
		byte[] ret = ByteBuffer.allocate(2).putShort((short) modCode).array();
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) summonIDOnDeath).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) health).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num3).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) LootTableID).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) ReferenceSaveDataArrayID).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num6).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num7).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) num8).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) MissionTypeFlags1).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) StackLimit).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) EffectVulnerabilityFlags).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(ModelResourceID).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(2).putShort((short) BasicAttackID).array());
		ret = bFM.Utils.mergeArrays(ret, num15a);
		ret = bFM.Utils.mergeArrays(ret, MissionTypeFlags2);
		ret = bFM.Utils.mergeArrays(ret, MissionType);
		ret = bFM.Utils.mergeArrays(ret, EntranceFlags);
		ret = bFM.Utils.mergeArrays(ret, DisapearanceFlags);
		ret = bFM.Utils.mergeArrays(ret, num17b);
		ret = bFM.Utils.mergeArrays(ret, MapIconType);
		ret = bFM.Utils.mergeArrays(ret, LightType);
		ret = bFM.Utils.mergeArrays(ret, HPDisplayType);
		ret = bFM.Utils.mergeArrays(ret, NPCReactionLimit);
		ret = bFM.Utils.mergeArrays(ret, IdleMovementSpeedTableIndex);
		ret = bFM.Utils.mergeArrays(ret, IdleRotationSpeedTableIndex);
		ret = bFM.Utils.mergeArrays(ret, AttackMovementSpeedTableIndex);
		ret = bFM.Utils.mergeArrays(ret, AttackRotationSpeedTableIndex);
		ret = bFM.Utils.mergeArrays(ret, num22a);
		ret = bFM.Utils.mergeArrays(ret, HPDisplayDuringState);
		ret = bFM.Utils.mergeArrays(ret, StateMapIconType);
		ret = bFM.Utils.mergeArrays(ret, DiscoveryLogType);
		ret = bFM.Utils.mergeArrays(ret, num24a);
		ret = bFM.Utils.mergeArrays(ret, num24b);
		ret = bFM.Utils.mergeArrays(ret, num25a);
		ret = bFM.Utils.mergeArrays(ret, num25b);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(num26).array());
		ret = bFM.Utils.mergeArrays(ret, Utils.encodeStringToBytes(name));
		byte[] ret1 = new byte[88]; 
		for(int i = 0; i<ret.length&&i<88;i++)
		{
			ret1[i] = ret[i];
		}
		ret = ret1;
		return ret;
	}
	public boolean showsHP()
	{
		//Easy way to tell if its an enemy/obstical or invulnerable
		return (BasicAttackID&16)==0;
	}
	public int getModCode() 
	{
		return modCode;
	}
	public String toBMos()
	{
		String ret = "";
		if(modCode == -1) throw new IllegalArgumentException("Mission Object ID is invalid");
		if(modCode != -1) ret += "<<Mission Object ID>> " + modCode + "\n";
		if(name != null) ret += "\t<<Debug Name>> \"" + name + "\"\n";
		if(summonIDOnDeath != -1) ret += "\t<<Summon ID On Death>> " + summonIDOnDeath + "\n";
		if(health != -1) ret += "\t<<Health>> " + health + "\n";
		if(num3 != 2560) ret += "\t<<Num3>> " + num3 + "\n";
		if(LootTableID != -1) ret += "\t<<Loot Table ID>> " + LootTableID + "\n";
		if(ReferenceSaveDataArrayID != -1) ret += "\t<<Reference Save Data Array ID>> " + ReferenceSaveDataArrayID + "\n";
		if(num6 != -1) ret += "\t<<Num6>> " + num6 + "\n";
		if(num7 != -1) ret += "\t<<Num7>> " + num7 + "\n";
		if(num8 != 0) ret += "\t<<Num8>> " + num8 + "\n";
		if(MissionTypeFlags1 != 0) ret += "\t<<Mission Type Flags 1>> " + MissionTypeFlags1 + "\n";
		if(StackLimit != 20) ret += "\t<<Stack Limit>> " + StackLimit + "\n";
		if(EffectVulnerabilityFlags != 514) ret += "\t<<Effect Vulnerability Flags>> " + EffectVulnerabilityFlags + "\n";
		if(ModelResourceID != -1) ret += "\t<<Model Resource ID>> " + ModelResourceID + "\n";
		if(BasicAttackID != -1) ret += "\t<<Basic Attack ID>> " + BasicAttackID + "\n";
		if(num15a != 0) ret += "\t<<Num 15a>> " + num15a + "\n";
		if(MissionTypeFlags2 != 0) ret += "\t<<Mission Type Flags 2>> " + MissionTypeFlags2 + "\n";
		if(MissionType != 0) ret += "\t<<Mission Type>> " + MissionType + "\n";
		if(EntranceFlags != 0) ret += "\t<<Entrance Flags>> " + EntranceFlags + "\n";
		if(DisapearanceFlags != 0) ret += "\t<<Disapearance Flags>> " + DisapearanceFlags + "\n";
		if(num17b != 0) ret += "\t<<Num 17b>> " + num17b + "\n";
		if(MapIconType != 0) ret += "\t<<Map Icon Type>> " + MapIconType + "\n";
		if(LightType != 0) ret += "\t<<Light Type>> " + LightType + "\n";
		if(HPDisplayType != 0) ret += "\t<<HP Display Type>> " + HPDisplayType + "\n";
		if(NPCReactionLimit != 0) ret += "\t<<NPC Reaction Limit>> " + NPCReactionLimit + "\n";
		if(IdleMovementSpeedTableIndex != 0) ret += "\t<<Idle Movement Speed Table Index>> " + IdleMovementSpeedTableIndex + "\n";
		if(IdleRotationSpeedTableIndex != 0) ret += "\t<<Idle Rotation Speed Table Index>> " + IdleRotationSpeedTableIndex + "\n";
		if(AttackMovementSpeedTableIndex != 0) ret += "\t<<Attack Movement Speed Table Index>> " + AttackMovementSpeedTableIndex + "\n";
		if(AttackRotationSpeedTableIndex != 0) ret += "\t<<Attack Rotation Speed Table Index>> " + AttackRotationSpeedTableIndex + "\n";
		if(num22a != 0) ret += "\t<<Num 22a>> " + num22a + "\n";
		if(HPDisplayDuringState != 0) ret += "\t<<HP Display During State>> " + HPDisplayDuringState + "\n";
		if(StateMapIconType != 0) ret += "\t<<State Map Icon Type>> " + StateMapIconType + "\n";
		if(DiscoveryLogType != 0) ret += "\t<<Discovery Log Type>> " + DiscoveryLogType + "\n";
		if(num24a != 0) ret += "\t<<Num 24a>> " + num24a + "\n";
		if(num24b != 0) ret += "\t<<Num 24b>> " + num24b + "\n";
		if(num25a != 0) ret += "\t<<Num 25a>> " + num25a + "\n";
		if(num25b != 0) ret += "\t<<Num 25b>> " + num25b + "\n";
		if(num26 != 0) ret += "\t<<Num 26>> " + num26 + "\n";
		return ret;
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<Mission Object ID>>")!=-1) modCode = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Debug Name>>")!=-1) name = bFM.Utils.formatString(line);
		else if(line.indexOf("<<Summon ID On Death>>")!=-1) summonIDOnDeath = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Health>>")!=-1) health = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num3>>") != -1) num3 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Loot Table ID>>") != -1) LootTableID = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Reference Save Data Array ID>>") != -1) ReferenceSaveDataArrayID = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num6>>") != -1) num6 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num7>>") != -1) num7 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num8>>") != -1) num8 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Mission Type Flags 1>>") != -1) MissionTypeFlags1 = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Stack Limit>>") != -1) StackLimit = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Effect Vulnerability Flags>>") != -1) EffectVulnerabilityFlags = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Model Resource ID>>") != -1) ModelResourceID = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Basic Attack ID>>") != -1) BasicAttackID = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num 15a>>") != -1) num15a = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Mission Type Flags 2>>") != -1) MissionTypeFlags2 = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Mission Type>>") != -1) MissionType = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Entrance Flags>>") != -1) EntranceFlags = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Disapearance Flags>>") != -1) DisapearanceFlags = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num 17b>>") != -1) num17b = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Map Icon Type>>") != -1) MapIconType = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Light Type>>") != -1) LightType = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<HP Display Type>>") != -1) HPDisplayType = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<NPC Reaction Limit>>") != -1) NPCReactionLimit = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Idle Movement Speed Table Index>>") != -1) IdleMovementSpeedTableIndex = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Idle Rotation Speed Table Index>>") != -1) IdleRotationSpeedTableIndex = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Attack Movement Speed Table Index>>") != -1) AttackMovementSpeedTableIndex = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Attack Rotation Speed Table Index>>") != -1) AttackRotationSpeedTableIndex = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num 22a>>") != -1) num22a = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<HP Display During State>>") != -1) HPDisplayDuringState = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<State Map Icon Type>>") != -1) StateMapIconType = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Discovery Log Type>>") != -1) DiscoveryLogType = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num 24a>>") != -1) num24a = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num 24b>>") != -1) num24b = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num 25a>>") != -1) num25a = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num 25b>>") != -1) num25b = (byte) bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Num 26>>") != -1) num26 = bFM.Utils.formatFloat(line);
	}
}
