package ResourceManagers.MSDBManager.Definition;

import java.awt.Component;

import bFM.Data;

public class MissionObjectDefinitionManager implements Data
{
	MobAiList AI;
	MobResAsn Resources;
	MobModList Definitions;
	MobAttackList Attacks;
	MobDamageColList DamageCol;
	MobPresetTableList PresetTable;
	public MissionObjectDefinitionManager(byte[] ai, byte[] res, byte[] mod, byte[] AttackCol, byte[] AttackElem, byte[] AttackInfo, byte[] DamageCol, byte[] PresetTable)
	{
		this.AI = new MobAiList(ai);
		this.Resources = new MobResAsn(res);
		this.Definitions = new MobModList(mod);
		this.Attacks = new MobAttackList(AttackInfo, AttackElem, AttackCol);
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
		return Attacks.getAttackElement();
	}
	public byte[] getAttackCol() 
	{
		return Attacks.getAttackCol();
	}
	public byte[] getAttackInfo() 
	{
		return Attacks.getAttackInfo();
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
	public MobAttackList getAttacks()
	{
		return Attacks;
	}
	public MobResAsn getResources()
	{
		return Resources;
	}
	public MobModList getDefinitions()
	{
		return Definitions;
	}
	public MobAiList getAis()
	{
		return AI;
	}
	public MobPresetTableList getPresetTable()
	{
		return PresetTable;
	}
	public MobDamageColList getDamageCols()
	{
		return DamageCol;
	}
}
