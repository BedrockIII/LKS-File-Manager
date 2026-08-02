package ResourceManagers.MSDBManager.Definition;

import bFM.Data;

public class MissionObjectManager implements Data
{
	MobAiList AI;
	MobResAsn Resources;
	MobModList Definitions;
	MobAttackColList AttackCol;
	MobAttackElemList AttackElem;
	MobAttackInfoList AttackInfo;
	MobDamageColList DamageCol;
	MobPresetTableList PresetTable;
	public MissionObjectManager(byte[] ai, byte[] res, byte[] mod, byte[] AttackCol, byte[] AttackElem, byte[] AttackInfo, byte[] DamageCol, byte[] PresetTable)
	{
		this.AI = new MobAiList(ai);
		this.Resources = new MobResAsn(res);
		this.Definitions = new MobModList(mod);
		this.AttackCol = new MobAttackColList(AttackCol);
		this.AttackElem = new MobAttackElemList(AttackElem);
		this.AttackInfo = new MobAttackInfoList(AttackInfo);
		this.DamageCol = new MobDamageColList(DamageCol);
		this.PresetTable = new MobPresetTableList(PresetTable);
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
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public byte[] getAI() 
	{
		return AI.toBytes();
	}
	public byte[] getResource() 
	{
		return Resources.toBytes();
	}
	public byte[] getDefinition()
	{
		return Definitions.toBytes();
	}
	public byte[] getAttackElement()
	{
		return AttackElem.toBytes();
	}
	public byte[] getAttackCol() 
	{
		return AttackCol.toBytes();
	}
	public byte[] getAttackInfo() 
	{
		return AttackInfo.toBytes();
	}
	public byte[] getDamageCol()
	{
		return DamageCol.toBytes();
	}
	public byte[] getTable()
	{
		return PresetTable.toBytes();
	}
	public String getModCodeByName(int code) 
	{
		return Definitions.getModCodeByName(code);
	}
}
