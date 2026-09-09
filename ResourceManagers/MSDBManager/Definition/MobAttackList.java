package ResourceManagers.MSDBManager.Definition;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import bFM.Data;
import bFM.Nameable;
import bFM.Utils;

public class MobAttackList implements Data
{
	private ArrayList<MobAttack> Attacks;
	public MobAttackList(byte[] atkInfo, byte[] atkElem, byte[] atkCol)
	{
		Attacks = new ArrayList<MobAttack>();
		initializeFromInfoBytes(atkInfo);
		initializeFromElemBytes(atkElem);
		initializeFromColBytes(atkCol);
	}
	private void initializeFromInfoBytes(byte[] atkInfo)
	{
		if(atkInfo.length<4) return;
		for(int i = 4; i<atkInfo.length; i+=92)
		{
			Attacks.add(new MobAttack(Arrays.copyOfRange(atkInfo, i, i+92)));
		}
	}
	private void initializeFromElemBytes(byte[] atkElem)
	{
		if(atkElem.length<4) return;
		for(int i = 4; i<atkElem.length; i+=36)
		{
			int atkCode = ByteBuffer.wrap(atkElem).order(ByteOrder.BIG_ENDIAN).getShort(i);
			try
			{
				getAttackFromCode(atkCode).addElemData(Arrays.copyOfRange(atkElem, i, i+36));
			}
			catch(NoSuchElementException e)
			{
				System.err.println("Error Finding Defined Attack with code (Element): " + atkCode);
			}
		}
	}
	private void initializeFromColBytes(byte[] atkCol)
	{
		if(atkCol.length<4) return;
		for(int i = 4; i<atkCol.length; i+=36)
		{
			int atkCode = ByteBuffer.wrap(atkCol).order(ByteOrder.BIG_ENDIAN).getShort(i);
			try
			{
				getAttackFromCode(atkCode).addColData(Arrays.copyOfRange(atkCol, i, i+36));
			}
			catch(NoSuchElementException e)
			{
				System.err.println("Error Finding Defined Attack with code (Collision): " + atkCode);
			}
		}
	}
	public MobAttack getAttackFromCode(int atkCode)
	{
		MobAttack ret = null;
		for(MobAttack a : Attacks)
		{
			if(a.attackCode == atkCode)
			{
				if(ret == null) ret = a;
				else throw new IllegalArgumentException("There are >1 Attacks with the same code!!! " + atkCode);
			}
		}
		if(ret == null) throw new NoSuchElementException("There are no Attacks with the same code!!! " + atkCode);
		return ret;
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
		return toBytes().length;
	}
	public byte[] getAttackElement()
	{
		byte[] ret = new byte[]{0,1};
		ret = Utils.mergeArrays(ret, Utils.toByteArr(Attacks.size(), 2));
		for(MobAttack element : Attacks)
			ret = Utils.mergeArrays(ret, element.toElemBytes());
		return ret;
	}
	public byte[] getAttackCol()
	{
		ArrayList<MobAttackCol> AttackCol = new ArrayList<MobAttackCol>();
		for(MobAttack atk : Attacks)
			if(atk.Hitboxes != null)AttackCol.addAll(atk.Hitboxes);
		byte[] ret = new byte[]{0,1};
		ret = Utils.mergeArrays(ret, Utils.toByteArr(AttackCol.size(), 2));
		for(MobAttackCol col : AttackCol)
			ret = Utils.mergeArrays(ret, col.toBytes());
		return ret;
		
	}
	public byte[] getAttackInfo()
	{
		byte[] ret = new byte[]{0,1};
		ret = Utils.mergeArrays(ret, Utils.toByteArr(Attacks.size(), 2));
		for(MobAttack atk : Attacks)
			ret = Utils.mergeArrays(ret, atk.toInfoBytes());
		return ret;
	}
	public class MobAttack implements Nameable, Data
	{
		String AttackName;//16
		String AttackType;//16
		float num0; //4
		float num1; //4
		float num2; //4
		float num3; //4
		float num4;//4
		float num5; //4
		float num6;//4
		float num7;//4
		float num8;//4
		float num9;//4
		float num10;//4
		float num11;//4
		int attackCode;//2
		int num13;//2
		byte num14;
		byte num15;
		byte num16;
		byte num17;
		byte num18;
		byte num19;
		byte num20;
		byte num21;
		//ATK Element Vars
		int Damage;//2
		int atkNum2;//2
		int HitTypes;//2
		int ElementalType;//2
		int atkNum5;//2
		String name;//16 bytes
		float atkNum6;
		byte atkNum7;
		byte atkNum8;
		byte HitEffect; //IE roll or blow away
		byte atkNum10;
		//ATK Cols
		ArrayList<MobAttackCol> Hitboxes = new ArrayList<MobAttackCol>();
		
		private MobAttack(byte[] data)
		{
			ByteBuffer data2 = ByteBuffer.wrap(data);
			AttackName = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 0, 16)));
			AttackType = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 16, 32)));
			num0 = data2.getFloat(32);
			num1 = data2.getFloat(36);
			num2 = data2.getFloat(40);
			num3 = data2.getFloat(44);
			num4 = data2.getFloat(48);
			num5 = data2.getFloat(52);
			num6 = data2.getFloat(56);
			num7 = data2.getFloat(60);
			num8 = data2.getFloat(64);
			num9 = data2.getFloat(68);
			num10 = data2.getFloat(72);
			num11 = data2.getFloat(76);
			attackCode = Utils.getShort(data, 80);
			num13 = Utils.getShort(data, 82);
			num14 = data[84];
			num15 = data[85];
			num16 = data[86];
			num17 = data[87];
			num18 = data[88];
			num19 = data[89];
			num20 = data[90];
			num21 = data[91];
		}
		public void addColData(byte[] data) 
		{
			Hitboxes.add(new MobAttackCol(data));
		}
		private void addElemData(byte[] data)
		{
			if(attackCode != Utils.getShort(data, 0)) throw new IllegalArgumentException("Mob Attack Codes are not even " + attackCode + ", " + Utils.getShort(data, 0));
			Damage = bFM.Utils.getShort(data, 2);
			atkNum2 = bFM.Utils.getShort(data, 4);
			HitTypes = bFM.Utils.getShort(data, 6);
			ElementalType = bFM.Utils.getShort(data, 8);
			atkNum5 = bFM.Utils.getShort(data, 10);
			name = Utils.decodeBytesToString(bFM.Utils.removeEmptySpace(Arrays.copyOfRange(data, 12, 28)));
			atkNum6 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getFloat(28);
			atkNum7 = data[32];
			atkNum8 = data[33];
			HitEffect = data[34];
			atkNum10 = data[35];
		}
		private byte[] toInfoBytes()
		{
			byte[] ret = null;
			try {
				ret = Utils.encodeStringToBytes(AttackName, Charset.forName("Shift-JIS"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] ret2 = new byte[16];
			for(int i = 0; i < ret2.length && i < ret.length; i++)
			{
				ret2[i] = ret[i];
			}
			try {
				ret2 = Utils.mergeArrays(ret2, Utils.encodeStringToBytes(AttackType,Charset.forName("Shift-JIS")));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] finalRet = new byte[32];
			for(int i = 0; i < finalRet.length && i < ret2.length; i++)
			{
				finalRet[i] = ret2[i];
			}
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num0).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num1).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num2).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num3).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num4).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num5).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num6).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num7).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num8).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num9).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num10).array());
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(num11).array());
			finalRet = Utils.mergeArrays(finalRet, Utils.toByteArr(attackCode, 2));
			finalRet = Utils.mergeArrays(finalRet, Utils.toByteArr(num13, 2));
			finalRet = Utils.mergeArrays(finalRet, num14);
			finalRet = Utils.mergeArrays(finalRet, num15);
			finalRet = Utils.mergeArrays(finalRet, num16);
			finalRet = Utils.mergeArrays(finalRet, num17);
			finalRet = Utils.mergeArrays(finalRet, num18);
			finalRet = Utils.mergeArrays(finalRet, num19);
			finalRet = Utils.mergeArrays(finalRet, num20);
			finalRet = Utils.mergeArrays(finalRet, num21);
			return finalRet;
		}
		private byte[] toElemBytes()
		{
			byte[] ret = Utils.toByteArr(attackCode, 2);
			ret = Utils.mergeArrays(ret, Utils.toByteArr(Damage, 2));
			ret = Utils.mergeArrays(ret, Utils.toByteArr(atkNum2, 2));
			ret = Utils.mergeArrays(ret, Utils.toByteArr(HitTypes, 2));
			ret = Utils.mergeArrays(ret, Utils.toByteArr(ElementalType, 2));
			ret = Utils.mergeArrays(ret, Utils.toByteArr(atkNum5, 2));
			ret = Utils.mergeArrays(ret, Utils.encodeStringToBytes(name));
			byte[] finalRet = new byte[28];
			for(int i = 0; i < finalRet.length && i < ret.length; i++)
			{
				finalRet[i] = ret[i];
			}
			finalRet = Utils.mergeArrays(finalRet, ByteBuffer.allocate(4).putFloat(atkNum6).array());
			finalRet = Utils.mergeArrays(finalRet, atkNum7);
			finalRet = Utils.mergeArrays(finalRet, atkNum8);
			finalRet = Utils.mergeArrays(finalRet, HitEffect);
			finalRet = Utils.mergeArrays(finalRet, atkNum10);
			return finalRet;
		}
		public boolean equals(String name)
		{
			return AttackName.equals(name);
		}
		public void setName(String name)
		{
			AttackName = name;
		}
		public String getName()
		{
			return AttackName;
		}
		public String toString()
		{
			String ret = "";
			ret += "Mob Attack Info " +attackCode+": \"" + AttackName + "\", \"" + AttackType + "\", " + num0 + ", " + num1 + ", " +
			num2 + ", " +num3 + ", " +num4 + ", " +num5 + ", " +num6 + ", " +num7 + ", " +num8 + ", " + 
			num9 + ", " +num10 + ", " +num11 + ", " +num13 + ", " +num14 + ", " +
			num15 + ", " +num16 + ", " +num17 + ", " +num18 + ", " +num19 + ", " +num20 + ", " +
			num21 + "\n";
			ret += "Attack Element " + attackCode + ", " + Damage + ", " + atkNum2 + ", " + HitTypes + 
					", " + ElementalType + ", " + atkNum5 + ", \"" + name + "\", " + atkNum6 + ", " + 
					atkNum7 + ", " + atkNum8 + ", " + HitEffect + ", " + atkNum10+"\n";
			for(MobAttackCol c : Hitboxes)
				ret += c.toString();
			return ret;
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
		public ArrayList<MobAttackCol> getCollisions()
		{
			return Hitboxes;
		}
		public String getAttackType() {
			return AttackType;
		}
		public void setSoundEffect(String attackName) {
			name = attackName;
		}
		public String getSoundEffect() {
			return name;
		}
		public void setAttackType(String attackType) {
			AttackType = attackType;
		}
		public float getNum0() {
			return num0;
		}
		public void setNum0(float num0) {
			this.num0 = num0;
		}
		public float getNum1() {
			return num1;
		}
		public void setNum1(float num1) {
			this.num1 = num1;
		}
		public float getNum2() {
			return num2;
		}
		public void setNum2(float num2) {
			this.num2 = num2;
		}
		public float getNum3() {
			return num3;
		}
		public void setNum3(float num3) {
			this.num3 = num3;
		}
		public float getNum4() {
			return num4;
		}
		public void setNum4(float num4) {
			this.num4 = num4;
		}
		public float getNum5() {
			return num5;
		}
		public void setNum5(float num5) {
			this.num5 = num5;
		}
		public float getNum6() {
			return num6;
		}
		public void setNum6(float num6) {
			this.num6 = num6;
		}
		public float getNum7() {
			return num7;
		}
		public void setNum7(float num7) {
			this.num7 = num7;
		}
		public float getNum8() {
			return num8;
		}
		public void setNum8(float num8) {
			this.num8 = num8;
		}
		public float getNum9() {
			return num9;
		}
		public void setNum9(float num9) {
			this.num9 = num9;
		}
		public float getNum10() {
			return num10;
		}
		public void setNum10(float num10) {
			this.num10 = num10;
		}
		public float getNum11() {
			return num11;
		}
		public void setNum11(float num11) {
			this.num11 = num11;
		}
		public int getAttackCode() {
			return attackCode;
		}
		public void setAttackCode(int attackCode) {
			this.attackCode = attackCode;
		}
		public int getNum13() {
			return num13;
		}
		public void setNum13(int num13) {
			this.num13 = num13;
		}
		public byte getNum14() {
			return num14;
		}
		public void setNum14(int num14) {
			this.num14 = (byte) num14;
		}
		public byte getNum15() {
			return num15;
		}
		public void setNum15(int num15) {
			this.num15 = (byte) num15;
		}
		public byte getNum16() {
			return num16;
		}
		public void setNum16(int num16) {
			this.num16 = (byte) num16;
		}
		public byte getNum17() {
			return num17;
		}
		public void setNum17(int num17) {
			this.num17 = (byte) num17;
		}
		public byte getNum18() {
			return num18;
		}
		public void setNum18(int num18) {
			this.num18 = (byte) num18;
		}
		public byte getNum19() {
			return num19;
		}
		public void setNum19(int num19) {
			this.num19 = (byte) num19;
		}
		public byte getNum20() {
			return num20;
		}
		public void setNum20(int num20) {
			this.num20 = (byte) num20;
		}
		public byte getNum21() {
			return num21;
		}
		public void setNum21(int num21) {
			this.num21 = (byte) num21;
		}
		public int getDamage() {
			return Damage;
		}
		public void setDamage(int damage) {
			Damage = damage;
		}
		public int getAtkNum2() {
			return atkNum2;
		}
		public void setAtkNum2(int atkNum2) {
			this.atkNum2 = atkNum2;
		}
		public int getHitTypes() {
			return HitTypes;
		}
		public void setHitTypes(int hitTypes) {
			HitTypes = hitTypes;
		}
		public int getElementalType() {
			return ElementalType;
		}
		public void setElementalType(int elementalType) {
			ElementalType = elementalType;
		}
		public int getAtkNum5() {
			return atkNum5;
		}
		public void setAtkNum5(int atkNum5) {
			this.atkNum5 = atkNum5;
		}
		public float getAtkNum6() {
			return atkNum6;
		}
		public void setAtkNum6(float atkNum6) {
			this.atkNum6 = atkNum6;
		}
		public byte getAtkNum7() {
			return atkNum7;
		}
		public void setAtkNum7(int atkNum7) {
			this.atkNum7 = (byte) atkNum7;
		}
		public byte getAtkNum8() {
			return atkNum8;
		}
		public void setAtkNum8(int atkNum8) {
			this.atkNum8 = (byte) atkNum8;
		}
		public byte getHitEffect() {
			return HitEffect;
		}
		public void setHitEffect(int hitEffect) {
			HitEffect = (byte) hitEffect;
		}
		public byte getAtkNum10() {
			return atkNum10;
		}
		public void setAtkNum10(int atkNum10) {
			this.atkNum10 = (byte) atkNum10;
		}
		public ArrayList<MobAttackCol> getHitboxes() {
			return Hitboxes;
		}
		public void setHitboxes(ArrayList<MobAttackCol> hitboxes) {
			Hitboxes = hitboxes;
		}
	}
	public ArrayList<MobAttack> getAttacks()
	{
		return Attacks;
	}
}
