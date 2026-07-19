package ResourceManagers.CharacterDatabaseManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.GenericFile;
import bFM.Utils;

public class indBinList extends GenericFile
{
	ArrayList<ind> list = new ArrayList<ind>();
	protected indBinList(byte[] data)
	{
		name = "Index List";
		ByteBuffer bytes = ByteBuffer.wrap(data);
		for(int i = 16; i < data.length; i+=196)
		{
			list.add(new ind(bytes.slice(i, 180)));
			//System.out.println(list.get(list.size()-1));
			//list.get(list.size()-1).printUniques();
		}
	}
	protected indBinList(List<String> lines) 
	{
		name = "Index List";
		for(String line : lines)
		{
			if(line.indexOf("Index")!=-1)
			{
				list.add(new ind(line));
			}
		}
	}
	protected indBinList() 
	{
		name = "Index List";
	}
	public String toString()
	{
		String ret = "";
		for(ind index : list)
		{
			if(ret.length()>0) ret += '\n';
			ret += index.toString();
		}
		return ret;
	}
	public byte[] toBytes()
	{
		byte[] ret = bFM.Utils.longToBytes(list.size(), 4);
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.longToBytes(32, 2));
		ret = bFM.Utils.mergeArrays(ret, new byte[10]);
		for(ind index : list)
		{
			ret = bFM.Utils.mergeArrays(ret, index.toBytes());
			ret = bFM.Utils.mergeArrays(ret, new byte[16]);
		}
		return ret;
	}
	public String getNameByCode(int jobCode)
	{
		for(ind index : list)
		{
			if(index.jobCode == jobCode)
			{
				return index.getName();
			}
		}
		System.err.println("The Job Code " + jobCode + " has no defined index");
		return "Not A Job";
	}
	public class ind implements Data
	{
		int jobCode = -1;
		int num8 = -1;
		int handheldItemCode = -1;
		int helmetItemCode = -1;
		@SuppressWarnings("unused")
		int num11 = -1;
		@SuppressWarnings("unused")
		int num12 = -1;
		String word1 = ""; //16 bytes
		String word2 = ""; //16 bytes
		String name = ""; //32 bytes
		int num13a = -1;
		int num13b = -1;
		int num14a = -1;
		int num14b = -1;
		int num15a = -1;
		int num15b = -1;
		int num16a = 1;
		int num16b = 2;
		int num17a = -1;
		int num17b = 5;
		int num18a = 1;
		int num18b = 1;
		int num19a = 2;
		int num19b = 2;
		int num20a = 2;
		int num20b = -1;
		int num21a = 10;
		int num21b = -1;
		int num22a = 1;
		int num22b = 1;
		int num23a = -1;
		int num23b = -1;
		int num24a = 12;
		int num24b = 12;
		int num25a = 12;
		int num25b = 12;
		int num26a = 12;
		int num26b = 12;
		int num27a = 12;
		int num27b = 12;
		int num28a = 12;
		int num28b = 12;
		int num29a = 12;
		int num29b = 12;
		int num30a = 12;
		int num30b = 12;
		int num31a = 12;
		int num31b = 12;
		int num32a = 12;
		int num32b = 0;
		int num33a = 0;
		int num33b = 0;
		int jobMinHP = -1;
		int num34b = -1;
		int num35a = -1;
		int num35b = -1;
		int num36a = -1;
		int num36b = -1;
		int num37a = -1;
		int num37b = -1;
		int num38a = -1;
		int num38b = -1;
		int num39a = -1;
		int attackCharges = -1;
		int num40a = -1;
		int num40b = -1;
		String word4 = ""; //16 bytes
		String word5 = ""; //32 bytes
		private ind(ByteBuffer data)
		{
			//num1 = data.getShort();
			//num2 = data.getShort();
			//num3 = data.getShort();
			//num4 = data.getShort();
			//num5 = data.getShort();
			//num6 = data.getShort();
			jobCode = data.getShort(0);
			num8 = data.getShort(2);
			handheldItemCode = data.getShort(4);
			helmetItemCode = data.getShort(6);
			num11 = data.getShort(8);
			num12 = data.getShort(10);
			byte[] tempStringData = new byte[32];
			data.get(12, tempStringData, 0, 16);
			data.position(12+16);
			word1 = (Utils.decodeBytesToString(tempStringData)).replace((char)0x00, ' ').stripTrailing(); //16 bytes
			data.get(data.position(), tempStringData, 0, 16);
			data.position(data.position()+16);
			word2 = (Utils.decodeBytesToString(tempStringData)).replace((char)0x00, ' ').stripTrailing(); //16 bytes
			data.get(data.position(), tempStringData, 0, 32);
			data.position(data.position()+32);
			name = (Utils.decodeBytesToString(tempStringData)).replace((char)0x00, ' ').stripTrailing(); //32 bytes
			num13a = bFM.Utils.byteToInt(data.get());
			num13b = bFM.Utils.byteToInt(data.get());
			num14a = bFM.Utils.byteToInt(data.get());
			num14b = bFM.Utils.byteToInt(data.get());
			num15a = bFM.Utils.byteToInt(data.get());
			num15b = bFM.Utils.byteToInt(data.get());
			num16a = bFM.Utils.byteToInt(data.get());
			num16b = bFM.Utils.byteToInt(data.get());
			num17a = bFM.Utils.byteToInt(data.get());
			num17b = bFM.Utils.byteToInt(data.get());
			num18a = bFM.Utils.byteToInt(data.get());
			num18b = bFM.Utils.byteToInt(data.get());
			num19a = bFM.Utils.byteToInt(data.get());
			num19b = bFM.Utils.byteToInt(data.get());
			num20a = bFM.Utils.byteToInt(data.get());
			num20b = bFM.Utils.byteToInt(data.get());
			num21a = bFM.Utils.byteToInt(data.get());
			num21b = bFM.Utils.byteToInt(data.get());
			num22a = bFM.Utils.byteToInt(data.get());
			num22b = bFM.Utils.byteToInt(data.get());
			num23a = bFM.Utils.byteToInt(data.get());
			num23b = bFM.Utils.byteToInt(data.get());
			num24a = bFM.Utils.byteToInt(data.get());
			num24b = bFM.Utils.byteToInt(data.get());
			num25a = bFM.Utils.byteToInt(data.get());
			num25b = bFM.Utils.byteToInt(data.get());
			num26a = bFM.Utils.byteToInt(data.get());
			num26b = bFM.Utils.byteToInt(data.get());
			num27a = bFM.Utils.byteToInt(data.get());
			num27b = bFM.Utils.byteToInt(data.get());
			num28a = bFM.Utils.byteToInt(data.get());
			num28b = bFM.Utils.byteToInt(data.get());
			num29a = bFM.Utils.byteToInt(data.get());
			num29b = bFM.Utils.byteToInt(data.get());
			num30a = bFM.Utils.byteToInt(data.get());
			num30b = bFM.Utils.byteToInt(data.get());
			num31a = bFM.Utils.byteToInt(data.get());
			num31b = bFM.Utils.byteToInt(data.get());
			num32a = bFM.Utils.byteToInt(data.get());
			num32b = bFM.Utils.byteToInt(data.get());
			num33a = bFM.Utils.byteToInt(data.get());
			num33b = bFM.Utils.byteToInt(data.get());
			jobMinHP = bFM.Utils.byteToInt(data.get());
			num34b = bFM.Utils.byteToInt(data.get());
			num35a = bFM.Utils.byteToInt(data.get());
			num35b = bFM.Utils.byteToInt(data.get());
			num36a = bFM.Utils.byteToInt(data.get());
			num36b = bFM.Utils.byteToInt(data.get());
			num37a = bFM.Utils.byteToInt(data.get());
			num37b = bFM.Utils.byteToInt(data.get());
			num38a = bFM.Utils.byteToInt(data.get());
			num38b = bFM.Utils.byteToInt(data.get());
			num39a = bFM.Utils.byteToInt(data.get());
			attackCharges = bFM.Utils.byteToInt(data.get());
			num40a = bFM.Utils.byteToInt(data.get());
			num40b = bFM.Utils.byteToInt(data.get());
			tempStringData = new byte[32];
			data.get(data.position(), tempStringData, 0, 16);
			data.position(data.position()+16);
			word4 = (Utils.decodeBytesToString(tempStringData)).replace((char)0x00, ' ').stripTrailing(); //16 bytes
			data.get(data.position(), tempStringData, 0, 32);
			data.position(data.position()+32);
			word5 = (Utils.decodeBytesToString(tempStringData)).replace((char)0x00, ' ').stripTrailing(); //32 bytes
			//num41 = data.getShort();
			//num42 = data.getShort();
		}
		private ind(String line)
		{
			arrayIndex = 0;
			String[] data = bFM.Utils.toStrArr(line);
			jobCode = bFM.Utils.strToInt(readNextValue(data));
			num8 = bFM.Utils.strToInt(readNextValue(data));
			handheldItemCode = bFM.Utils.strToInt(readNextValue(data));
			helmetItemCode = bFM.Utils.strToInt(readNextValue(data));
			//num11 = bFM.Utils.strToInt(readNextValue(data));
			//num12 = bFM.Utils.strToInt(readNextValue(data));
			word1 = bFM.Utils.formatString(readNextValue(data));
			word2 = bFM.Utils.formatString(readNextValue(data));
			name = bFM.Utils.formatString(readNextValue(data));
			num13a = bFM.Utils.strToInt(readNextValue(data));
			num13b = bFM.Utils.strToInt(readNextValue(data));
			num14a = bFM.Utils.strToInt(readNextValue(data));
			num14b = bFM.Utils.strToInt(readNextValue(data));
			num15a = bFM.Utils.strToInt(readNextValue(data));
			num15b = bFM.Utils.strToInt(readNextValue(data));
			num16a = bFM.Utils.strToInt(readNextValue(data));
			num16b = bFM.Utils.strToInt(readNextValue(data));
			num17a = bFM.Utils.strToInt(readNextValue(data));
			num17b = bFM.Utils.strToInt(readNextValue(data));
			num18a = bFM.Utils.strToInt(readNextValue(data));
			num18b = bFM.Utils.strToInt(readNextValue(data));
			num19a = bFM.Utils.strToInt(readNextValue(data));
			num19b = bFM.Utils.strToInt(readNextValue(data));
			num20a = bFM.Utils.strToInt(readNextValue(data));
			num20b = bFM.Utils.strToInt(readNextValue(data));
			num21a = bFM.Utils.strToInt(readNextValue(data));
			num21b = bFM.Utils.strToInt(readNextValue(data));
			num22a = bFM.Utils.strToInt(readNextValue(data));
			num22b = bFM.Utils.strToInt(readNextValue(data));
			num23a = bFM.Utils.strToInt(readNextValue(data));
			num23b = bFM.Utils.strToInt(readNextValue(data));
			num24a = bFM.Utils.strToInt(readNextValue(data));
			num24b = bFM.Utils.strToInt(readNextValue(data));
			num25a = bFM.Utils.strToInt(readNextValue(data));
			num25b = bFM.Utils.strToInt(readNextValue(data));
			num26a = bFM.Utils.strToInt(readNextValue(data));
			num26b = bFM.Utils.strToInt(readNextValue(data));
			num27a = bFM.Utils.strToInt(readNextValue(data));
			num27b = bFM.Utils.strToInt(readNextValue(data));
			num28a = bFM.Utils.strToInt(readNextValue(data));
			num28b = bFM.Utils.strToInt(readNextValue(data));
			num29a = bFM.Utils.strToInt(readNextValue(data));
			num29b = bFM.Utils.strToInt(readNextValue(data));
			num30a = bFM.Utils.strToInt(readNextValue(data));
			num30b = bFM.Utils.strToInt(readNextValue(data));
			num31a = bFM.Utils.strToInt(readNextValue(data));
			num31b = bFM.Utils.strToInt(readNextValue(data));
			num32a = bFM.Utils.strToInt(readNextValue(data));
			num32b = bFM.Utils.strToInt(readNextValue(data));
			num33a = bFM.Utils.strToInt(readNextValue(data));
			num33b = bFM.Utils.strToInt(readNextValue(data));
			jobMinHP = bFM.Utils.strToInt(readNextValue(data));
			num34b = bFM.Utils.strToInt(readNextValue(data));
			num35a = bFM.Utils.strToInt(readNextValue(data));
			num35b = bFM.Utils.strToInt(readNextValue(data));
			num36a = bFM.Utils.strToInt(readNextValue(data));
			num36b = bFM.Utils.strToInt(readNextValue(data));
			num37a = bFM.Utils.strToInt(readNextValue(data));
			num37b = bFM.Utils.strToInt(readNextValue(data));
			num38a = bFM.Utils.strToInt(readNextValue(data));
			num38b = bFM.Utils.strToInt(readNextValue(data));
			num39a = bFM.Utils.strToInt(readNextValue(data));
			attackCharges = bFM.Utils.strToInt(readNextValue(data));
			num40a = bFM.Utils.strToInt(readNextValue(data));
			num40b = bFM.Utils.strToInt(readNextValue(data));
			word4 = bFM.Utils.formatString(readNextValue(data));
			word5 = bFM.Utils.formatString(readNextValue(data));
		}
		public ind(int jobCode2, String name2) 
		{
			jobCode = jobCode2;
			name = name2;
		}
		public byte[] toBytes()
		{
			byte[] ret = null;
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(jobCode, 2));
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(num8, 2));
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(handheldItemCode, 2));
			ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(helmetItemCode, 2));
			ret = bFM.Utils.mergeArrays(ret, new byte[4]);
			byte[] string1 = new byte[16];
			byte[] string2 = Utils.encodeStringToBytes(word1);
			for(int i = 0; i<16&&i<string2.length; i++)
			{
				string1[i] = string2[i];
			}
			ret = bFM.Utils.mergeArrays(ret, string1);
			string1 = new byte[16];
			string2 = Utils.encodeStringToBytes(word2);
			for(int i = 0; i<16&&i<string2.length; i++)
			{
				string1[i] = string2[i];
			}
			ret = bFM.Utils.mergeArrays(ret, string1);
			string1 = new byte[32];
			string2 = Utils.encodeStringToBytes(name);
			for(int i = 0; i<32&&i<string2.length; i++)
			{
				string1[i] = string2[i];
			}
			ret = bFM.Utils.mergeArrays(ret, string1);
			ret = bFM.Utils.mergeArrays(ret, (byte)num13a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num13b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num14a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num14b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num15a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num15b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num16a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num16b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num17a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num17b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num18a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num18b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num19a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num19b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num20a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num20b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num21a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num21b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num22a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num22b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num23a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num23b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num24a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num24b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num25a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num25b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num26a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num26b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num27a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num27b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num28a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num28b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num29a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num29b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num30a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num30b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num31a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num31b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num32a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num32b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num33a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num33b);
			ret = bFM.Utils.mergeArrays(ret, (byte)jobMinHP);
			ret = bFM.Utils.mergeArrays(ret, (byte)num34b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num35a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num35b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num36a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num36b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num37a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num37b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num38a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num38b);
			ret = bFM.Utils.mergeArrays(ret, (byte)num39a);
			ret = bFM.Utils.mergeArrays(ret, (byte)attackCharges);
			ret = bFM.Utils.mergeArrays(ret, (byte)num40a);
			ret = bFM.Utils.mergeArrays(ret, (byte)num40b);
			string1 = new byte[16];
			string2 = Utils.encodeStringToBytes(word4);
			for(int i = 0; i<16&&i<string2.length; i++)
			{
				string1[i] = string2[i];
			}
			ret = bFM.Utils.mergeArrays(ret, string1);
			string1 = new byte[32];
			string2 = Utils.encodeStringToBytes(word5);
			for(int i = 0; i<16&&i<string2.length; i++)
			{
				string1[i] = string2[i];
			}
			ret = bFM.Utils.mergeArrays(ret, string1);
			return ret;
		}
		int arrayIndex = 0;
		private String readNextValue(String[] data)
		{
			if(data.length<=arrayIndex) return "";
			String ret = data[arrayIndex];
			arrayIndex++;
			return ret;
		}
		@SuppressWarnings("unused")
		private void printUniques() 
		{
			//if(num1!=0) System.out.print(", num1: " + num1); //only diff for first one
			//if(num2!=0) System.out.print(num2);const
			//if(num3!=0) System.out.print(num3);const
			//if(num4!=0) System.out.print(num4);const
			//if(num5!=0) System.out.print(num5);const
			//if(num6!=0) System.out.print(num6);const
			//if(num7!=0) System.out.print(", num7: " + num7);code
			//if(num8!=0) System.out.print(", num8: " + num8);maybe unit type
			//if(num9!=0) System.out.print(", num9: " + num9);non const
			//if(num10!=0) System.out.print(", num10: " + num10);non const
			//if(num11!=0) System.out.print(", num11: " + num11);const
			//if(num12!=0) System.out.print(", num12: " + num12);const
			//if(num13!=0) System.out.print(", num13: " + num13);nonconst
			//if(num14!=0) System.out.print(", num14: " + num14);nonconst
			//if(num15!=0) System.out.print(", num15: " + num15);nonconst
			//if(num16!=258) System.out.print(", num16: " + num16);const
			//if(num17!=0) System.out.print(", num17: " + num17);nonconst
			//if(num18!=257) System.out.print(", num18: " + num18);const
			//if(num19!=514) System.out.print(", num19: " + num19);const
			//if(num20!=513) System.out.print(", num20: " + num20);nonconst
			//if(num21!=2561) System.out.print(", num21: " + num21);nonconst
			//if(num22!=257) System.out.print(", num22: " + num22);const
			//if(num23!=0) System.out.print(", num23: " + num23); nonconst
			//if(num24!=3084) System.out.print(", num24: " + num24);//const
			//if(num25!=3084) System.out.print(", num25: " + num25);//const
			//if(num26!=3084) System.out.print(", num26: " + num26);//const
			//if(num27!=3084) System.out.print(", num27: " + num27);//const
			//if(num28!=3084) System.out.print(", num28: " + num28);//const
			//if(num29!=3084) System.out.print(", num29: " + num29);const
			//if(num30!=3084) System.out.print(", num30: " + num30);const
			//if(num31!=3084) System.out.print(", num31: " + num31);const
			//if(num32!=3072) System.out.print(", num32: " + num32);const
			//if(num33!=0) System.out.print(", num33: " + num33);const
			//if(num34!=0) System.out.print(", num34: " + num34);nonconst
			//if(num35!=257) System.out.print(", num35: " + num35);const
			//if(num36!=257) System.out.print(", num36: " + num36);const
			//if(num37!=25600) System.out.print(", num37: " + num37);const
			//if(num38!=0) System.out.print(", num38: " + num38);const
			//if(num39!=0) System.out.print(", num39: " + num39);const
			//if(num40!=0) System.out.print(", num40: " + num40);//nonconst
			//if(num41!=0) System.out.print(", num41: " + num41);//const
			//if(num42!=0) System.out.print(", num42: " + num42);//const
			
			
			//if(num13a!=0) System.out.print(", num13a: " + num13a);
			//if(num13b!=0) System.out.print(", num13b: " + num13b);
			//if(num14a!=0) System.out.print(", num14a: " + num14a);
			//if(num14b!=0) System.out.print(", num14b: " + num14b);
			//if(num15a!=0) System.out.print(", num15a: " + num15a);
			//if(num15b!=0) System.out.print(", num15b: " + num15b);
			if(num16a!=1) System.out.print(", num16a: " + num16a);
			if(num16b!=2) System.out.print(", num16b: " + num16b);
			//if(num17a!=0) System.out.print(", num17a: " + num17a);
			if(num17b!=5) System.out.print(", num17b: " + num17b);
			if(num18a!=1) System.out.print(", num18a: " + num18a);
			if(num18b!=1) System.out.print(", num18b: " + num18b);
			if(num19a!=2) System.out.print(", num19a: " + num19a);
			if(num19b!=2) System.out.print(", num19b: " + num19b);
			if(num20a!=2) System.out.print(", num20a: " + num20a);
			//if(num20b!=0) System.out.print(", num20b: " + num20b);
			if(num21a!=10) System.out.print(", num21a: " + num21a);
			//if(num21b!=0) System.out.print(", num21b: " + num21b);
			if(num22a!=1) System.out.print(", num22a: " + num22a);
			if(num22b!=1) System.out.print(", num22b: " + num22b);
			//if(num23a!=0) System.out.print(", num23a: " + num23a);
			//if(num23b!=0) System.out.print(", num23b: " + num23b);
			if(num24a!=12) System.out.print(", num24a: " + num24a);
			if(num24b!=12) System.out.print(", num24b: " + num24b);
			if(num25a!=12) System.out.print(", num25a: " + num25a);
			if(num25b!=12) System.out.print(", num25b: " + num25b);
			if(num26a!=12) System.out.print(", num26a: " + num26a);
			if(num26b!=12) System.out.print(", num26b: " + num26b);
			if(num27a!=12) System.out.print(", num27a: " + num27a);
			if(num27b!=12) System.out.print(", num27b: " + num27b);
			if(num28a!=12) System.out.print(", num28a: " + num28a);
			if(num28b!=12) System.out.print(", num28b: " + num28b);
			if(num29a!=12) System.out.print(", num29a: " + num29a);
			if(num29b!=12) System.out.print(", num29b: " + num29b);
			if(num30a!=12) System.out.print(", num30a: " + num30a);
			if(num30b!=12) System.out.print(", num30b: " + num30b);
			if(num31a!=12) System.out.print(", num31a: " + num31a);
			if(num31b!=12) System.out.print(", num31b: " + num31b);
			if(num32a!=12) System.out.print(", num32a: " + num32a);
			if(num32b!=0) System.out.print(", num32b: " + num32b);
			if(num33a!=0) System.out.print(", num33a: " + num33a);
			if(num33b!=0) System.out.print(", num33b: " + num33b);
			//if(num34a!=0) System.out.print(", num34a: " + num34a);
			//if(num34b!=0) System.out.print(", num34b: " + num34b);
			//if(num35a!=0) System.out.print(", num35a: " + num35a);
			if(num35b!=1) System.out.print(", num35b: " + num35b);
			if(num36a!=1) System.out.print(", num36a: " + num36a);
			if(num36b!=1) System.out.print(", num36b: " + num36b);
			//if(num37a!=0) System.out.print(", num37a: " + num37a);
			if(num37b!=0) System.out.print(", num37b: " + num37b);
			//if(num38a!=0) System.out.print(", num38a: " + num38a);
			//if(num38b!=0) System.out.print(", num38b: " + num38b);
			//if(num39a!=0) System.out.print(", num39a: " + num39a);
			//if(num39b!=0) System.out.print(", num39b: " + num39b);
			if(num40a!=0) System.out.print(", num40a: " + num40a);
			//if(num40b!=0) System.out.print(", num40b: " + num40b);
			System.out.println();
		}
		public String toString()
		{
			String ret = "Index " + jobCode + ", " + num8 + ", " + handheldItemCode + ", " + helmetItemCode + ", \"" + word1 + "\", \"" + word2 + "\", \"" + 
					name + "\", "+ num13a + ", " + num13b + ", " + num14a + ", " + num14b + ", " + num15a + ", " + num15b + ", " + 
					num16a + ", " + num16b + ", " + num17a + ", " + num17b + ", " + num18a + ", " + num18b + ", " + num19a + ", " + 
					num19b + ", " + num20a + ", " + num20b + ", " + num21a + ", " + num21b + ", " + num22a + ", " + num22b + ", " + 
					num23a + ", " + num23b + ", " + num24a + ", " + num24b + ", " + num25a + ", " + num25b + ", " + num26a + ", " + 
					num26b + ", " + num27a + ", " + num27b + ", " + num28a + ", " + num28b + ", " + num29a + ", " + num29b + ", " + 
					num30a + ", " + num30b + ", " + num31a + ", " + num31b + ", " + num32a + ", " + num32b + ", " + num33a + ", " + 
					num33b + ", " + jobMinHP + ", " + num34b + ", " + num35a + ", " + num35b + ", " + num36a + ", " + num36b + ", " + 
					num37a + ", " + num37b + ", " + num38a + ", " + num38b + ", " + num39a + ", " + attackCharges + ", " + num40a + ", " + 
					num40b + ", \"" + word4 + "\", \"" + word5 +  "\"";
			//String ret = "Ind Data: " + num1 + ", " + num2 + ", " + num3 + ", " + num4 + ", " + num5 + ", " +  num6 + ", " + 
					//num7 + ", " + num8 + ", " + num9 + ", " + num10 + ", " + num11 + ", " + num12 + ", " + word1 + ", " + 
					//word2 + ", " + word3 + ", " + num13 + ", " + num14 + ", " + num15 + ", " + num16 + ", " + num17 + ", " + 
					//num18 + ", " + num19 + ", " + num20 + ", " + num21 + ", " + num22 + ", " + num23 + ", " + num24 + ", " + 
					//num25 + ", " + num26 + ", " + num27 + ", " + num28 + ", " + num29 + ", " + num30 + ", " + num31 + ", " + 
					//num32 + ", " + num33 + ", " + num34 + ", " + num35 + ", " + num36 + ", " + num37 + ", " + num38 + ", " + 
					//num39 + ", " + num40 + ", " + word4 + ", " + word5 + ", " + num41 + ", " + num42 + ";";
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
		public void setName(String name) 
		{
			this.name = name;
		}
		public String getName() 
		{
			return name;
		}
		public int getSize() 
		{
			throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
		}
		public int getJobCode() 
		{
			return jobCode;
		}
		public void setJobCode(int num) 
		{
			jobCode = num;
		}
		
		public int getNum8() 
		{
			return num8;
		}
		public void setNum8(int num) 
		{
			num8 = num;
		}

		public int getHandheldItemCode() 
		{
			return handheldItemCode;
		}
		public void setHandheldItemCode(int num) 
		{
			handheldItemCode = num;
		}

		public int getHelmetItemCode() 
		{
			return helmetItemCode;
		}
		public void setHelmetItemCode(int num) 
		{
			helmetItemCode = num;
		}

		public int getNum11() 
		{
			return num11;
		}
		public void setNum11(int num) 
		{
			num11 = num;
		}

		public int getNum12() 
		{
			return num12;
		}
		public void setNum12(int num) 
		{
			num12 = num;
		}

		public String getWord1() 
		{
			return word1;
		}
		public void setWord1(String str) 
		{
			word1 = str;
		}

		public String getWord2() 
		{
			return word2;
		}
		public void setWord2(String str) 
		{
			word2 = str;
		}

		public int getNum13a() 
		{
			return num13a;
		}
		public void setNum13a(int num) 
		{
			num13a = num;
		}

		public int getNum13b() 
		{
			return num13b;
		}
		public void setNum13b(int num) 
		{
			num13b = num;
		}

		public int getNum14a() 
		{
			return num14a;
		}
		public void setNum14a(int num) 
		{
			num14a = num;
		}

		public int getNum14b() 
		{
			return num14b;
		}
		public void setNum14b(int num) 
		{
			num14b = num;
		}

		public int getNum15a() 
		{
			return num15a;
		}
		public void setNum15a(int num) 
		{
			num15a = num;
		}

		public int getNum15b() 
		{
			return num15b;
		}
		public void setNum15b(int num) 
		{
			num15b = num;
		}

		public int getNum16a() 
		{
			return num16a;
		}
		public void setNum16a(int num) 
		{
			num16a = num;
		}

		public int getNum16b() 
		{
			return num16b;
		}
		public void setNum16b(int num) 
		{
			num16b = num;
		}

		public int getNum17a() 
		{
			return num17a;
		}
		public void setNum17a(int num) 
		{
			num17a = num;
		}

		public int getNum17b() 
		{
			return num17b;
		}
		public void setNum17b(int num) 
		{
			num17b = num;
		}

		public int getNum18a() 
		{
			return num18a;
		}
		public void setNum18a(int num) 
		{
			num18a = num;
		}

		public int getNum18b() 
		{
			return num18b;
		}
		public void setNum18b(int num) 
		{
			num18b = num;
		}

		public int getNum19a() 
		{
			return num19a;
		}
		public void setNum19a(int num) 
		{
			num19a = num;
		}

		public int getNum19b() 
		{
			return num19b;
		}
		public void setNum19b(int num) 
		{
			num19b = num;
		}

		public int getNum20a() 
		{
			return num20a;
		}
		public void setNum20a(int num) 
		{
			num20a = num;
		}

		public int getNum20b() 
		{
			return num20b;
		}
		public void setNum20b(int num) 
		{
			num20b = num;
		}

		public int getNum21a() 
		{
			return num21a;
		}
		public void setNum21a(int num) 
		{
			num21a = num;
		}

		public int getNum21b() 
		{
			return num21b;
		}
		public void setNum21b(int num) 
		{
			num21b = num;
		}

		public int getNum22a() 
		{
			return num22a;
		}
		public void setNum22a(int num) 
		{
			num22a = num;
		}

		public int getNum22b() 
		{
			return num22b;
		}
		public void setNum22b(int num) 
		{
			num22b = num;
		}

		public int getNum23a() 
		{
			return num23a;
		}
		public void setNum23a(int num) 
		{
			num23a = num;
		}

		public int getNum23b() 
		{
			return num23b;
		}
		public void setNum23b(int num) 
		{
			num23b = num;
		}

		public int getNum24a() 
		{
			return num24a;
		}
		public void setNum24a(int num) 
		{
			num24a = num;
		}

		public int getNum24b() 
		{
			return num24b;
		}
		public void setNum24b(int num) 
		{
			num24b = num;
		}

		public int getNum25a() 
		{
			return num25a;
		}
		public void setNum25a(int num) 
		{
			num25a = num;
		}

		public int getNum25b() 
		{
			return num25b;
		}
		public void setNum25b(int num) 
		{
			num25b = num;
		}

		public int getNum26a() 
		{
			return num26a;
		}
		public void setNum26a(int num) 
		{
			num26a = num;
		}

		public int getNum26b() 
		{
			return num26b;
		}
		public void setNum26b(int num) 
		{
			num26b = num;
		}

		public int getNum27a() 
		{
			return num27a;
		}
		public void setNum27a(int num) 
		{
			num27a = num;
		}

		public int getNum27b() 
		{
			return num27b;
		}
		public void setNum27b(int num) 
		{
			num27b = num;
		}

		public int getNum28a() 
		{
			return num28a;
		}
		public void setNum28a(int num) 
		{
			num28a = num;
		}

		public int getNum28b() 
		{
			return num28b;
		}
		public void setNum28b(int num) 
		{
			num28b = num;
		}

		public int getNum29a() 
		{
			return num29a;
		}
		public void setNum29a(int num) 
		{
			num29a = num;
		}

		public int getNum29b() 
		{
			return num29b;
		}
		public void setNum29b(int num) 
		{
			num29b = num;
		}

		public int getNum30a() 
		{
			return num30a;
		}
		public void setNum30a(int num) 
		{
			num30a = num;
		}

		public int getNum30b() 
		{
			return num30b;
		}
		public void setNum30b(int num) 
		{
			num30b = num;
		}

		public int getNum31a() 
		{
			return num31a;
		}
		public void setNum31a(int num) 
		{
			num31a = num;
		}

		public int getNum31b() 
		{
			return num31b;
		}
		public void setNum31b(int num) 
		{
			num31b = num;
		}

		public int getNum32a() 
		{
			return num32a;
		}
		public void setNum32a(int num) 
		{
			num32a = num;
		}

		public int getNum32b() 
		{
			return num32b;
		}
		public void setNum32b(int num) 
		{
			num32b = num;
		}

		public int getNum33a() 
		{
			return num33a;
		}
		public void setNum33a(int num) 
		{
			num33a = num;
		}

		public int getNum33b() 
		{
			return num33b;
		}
		public void setNum33b(int num) 
		{
			num33b = num;
		}

		public int getJobMinHP() 
		{
			return jobMinHP;
		}
		public void setJobMinHP(int num) 
		{
			jobMinHP = num;
		}

		public int getNum34b() 
		{
			return num34b;
		}
		public void setNum34b(int num) 
		{
			num34b = num;
		}

		public int getNum35a() 
		{
			return num35a;
		}
		public void setNum35a(int num) 
		{
			num35a = num;
		}

		public int getNum35b() 
		{
			return num35b;
		}
		public void setNum35b(int num) 
		{
			num35b = num;
		}

		public int getNum36a() 
		{
			return num36a;
		}
		public void setNum36a(int num) 
		{
			num36a = num;
		}

		public int getNum36b() 
		{
			return num36b;
		}
		public void setNum36b(int num) 
		{
			num36b = num;
		}

		public int getNum37a() 
		{
			return num37a;
		}
		public void setNum37a(int num) 
		{
			num37a = num;
		}

		public int getNum37b() 
		{
			return num37b;
		}
		public void setNum37b(int num) 
		{
			num37b = num;
		}

		public int getNum38a() 
		{
			return num38a;
		}
		public void setNum38a(int num) 
		{
			num38a = num;
		}

		public int getNum38b() 
		{
			return num38b;
		}
		public void setNum38b(int num) 
		{
			num38b = num;
		}

		public int getNum39a() 
		{
			return num39a;
		}
		public void setNum39a(int num) 
		{
			num39a = num;
		}

		public int getAttackCharges() 
		{
			return attackCharges;
		}
		public void setAttackCharges(int num) 
		{
			attackCharges = num;
		}

		public int getNum40a() 
		{
			return num40a;
		}
		public void setNum40a(int num) 
		{
			num40a = num;
		}

		public int getNum40b() 
		{
			return num40b;
		}
		public void setNum40b(int num) 
		{
			num40b = num;
		}

		public String getWord4() 
		{
			return word4;
		}
		public void setWord4(String str) 
		{
			word4 = str;
		}

		public String getWord5() 
		{
			return word5;
		}
		public void setWord5(String str) 
		{
			word5 = str;
		}
	}
	public ArrayList<ind> getIndicies() 
	{
		return list;
	}
	public int getAmountOfIndicies() 
	{
		return list.size();
	}
	public ind getByCode(int jobCode) 
	{
		for(ind index : list)
		{
			if(index.jobCode == jobCode)
			{
				return index;
			}
		}
		throw new IllegalArgumentException("The Job Code " + jobCode + " has no defined index");
	}
	public void addJob(int jobCode, String name) 
	{
		list.add(new ind(jobCode, name));
	}
	public ind getLastObject() 
	{
		return list.get(list.size() - 1);
	}
	public void removeIndex(ind file) 
	{
		int code = file.getJobCode();
		for(int i = list.size()-1; i >= 0; i--)
		{
			if(list.get(i).getJobCode() == code)
			{
				list.remove(i);
			}
		}
	}
}
