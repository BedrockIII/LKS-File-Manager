package ResourceManagers.CharacterDatabaseManager;

import bFM.Data;

public class CharacterBody implements Data
{
	int characterCode = -1;
	int jobCode = -1;
	int num1 = -1;
	int genderCode = -1;
	int num2 = -1;
	int num3 = -1;
	String model = "";
	int num4 = -1;
	int num5 = -1;
	int num6 = -1;
	float num7 = -1;
	String name = "";
	CharacterBody(String line) 
	{
		String[] data = bFM.Utils.toStrArr(line);
		characterCode = bFM.Utils.strToInt(data[0]);
		jobCode = bFM.Utils.strToInt(data[1]);
		num1 = bFM.Utils.strToInt(data[2]);
		genderCode = bFM.Utils.strToInt(data[3]);
		num2 = bFM.Utils.strToInt(data[4]);
		num3 = bFM.Utils.strToInt(data[5]);
		model = bFM.Utils.formatString(data[6]);
		num4 = bFM.Utils.strToInt(data[7]);
		num5 = bFM.Utils.strToInt(data[8]);
		num6 = bFM.Utils.strToInt(data[9]);
		num7 = Float.parseFloat(data[10]);
	}
	CharacterBody(String line, boolean fillerForMoreData) 
	{
		String[] data = bFM.Utils.toStrArr(line);
		characterCode = bFM.Utils.strToInt(data[0]);
		jobCode = bFM.Utils.strToInt(data[1]);
		num1 = bFM.Utils.strToInt(data[2]);
		genderCode = bFM.Utils.strToInt(data[3]);
		num2 = bFM.Utils.strToInt(data[4]);
		num3 = bFM.Utils.strToInt(data[5]);
		model = bFM.Utils.formatString(data[6]);
		num4 = bFM.Utils.strToInt(data[7]);
		num5 = bFM.Utils.strToInt(data[8]);
		num6 = bFM.Utils.strToInt(data[9]);
		num7 = Float.parseFloat(data[10]);
		name = bFM.Utils.formatString(data[11]);
	}
	public CharacterBody() 
	{
		name = "New Body";
	}
	void addVData(String line)
	{
		String[] data = bFM.Utils.toStrArr(line);
		if(characterCode != bFM.Utils.strToInt(data[0]))
		{
			throw new IllegalArgumentException("Character Face V Data Code does not match last Character Face \n" + line + "\n");
		}
		name = bFM.Utils.formatString(line);
	}
	public String toString()
	{
		String ret = "";
		ret += "BD_DAT " + characterCode + "," + jobCode + "," + num1 + "," + genderCode + "," + num2 + "," + num3 + ",\"" + model + "\"," + 
		num4 + "," + num5 + "," + num6 + "," + String.format("%.2f", num7) + " ;\r\n";
		ret += "BD_DATV " + characterCode + ",\"" + name + "\";\r\n";
		return ret;
	}
	String toCSV()
	{
		String ret = "";
		ret += "Body Data " + characterCode + "," + jobCode + "," + num1 + "," + genderCode + "," + num2 + "," + num3 + "," + model + "," + 
		num4 + "," + num5 + "," + num6 + "," + num7 + "," + name;
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
		throw new UnsupportedOperationException("getData() should not be called on type " + this.getClass());
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
	public void setCharacterCode(int characterCode) 
	{
		this.characterCode = characterCode;
	}
	public int getCharacterCode() 
	{
		return characterCode;
	}

	public void setJobCode(int jobCode) 
	{
		this.jobCode = jobCode;
	}
	public int getJobCode() 
	{
		return jobCode;
	}

	public void setNum1(int num1) 
	{
		this.num1 = num1;
	}
	public int getNum1() 
	{
		return num1;
	}

	public void setGenderCode(int genderCode) 
	{
		this.genderCode = genderCode;
	}
	public int getGenderCode() 
	{
		return genderCode;
	}

	public void setNum2(int num2) 
	{
		this.num2 = num2;
	}
	public int getNum2() 
	{
		return num2;
	}

	public void setModel(String model) 
	{
		this.model = model;
	}
	public String getModel() 
	{
		return model;
	}

	public void setNum3(int num3) 
	{
		this.num3 = num3;
	}
	public int getNum3() 
	{
		return num3;
	}

	public void setNum4(int num4) 
	{
		this.num4 = num4;
	}
	public int getNum4() 
	{
		return num4;
	}

	public void setNum5(int num5) 
	{
		this.num5 = num5;
	}
	public int getNum5() 
	{
		return num5;
	}
	
	public void setNum6(int num6) 
	{
		this.num6 = num6;
	}
	public int getNum6() 
	{
		return num6;
	}
	
	public void setNum7(float num7) 
	{
		this.num7 = num7;
	}
	public float getNum7() 
	{
		return num7;
	}
}