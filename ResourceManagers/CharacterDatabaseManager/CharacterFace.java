package ResourceManagers.CharacterDatabaseManager;

import bFM.Data;

public class CharacterFace implements Data
{
	int characterCode = -1;
	int jobCode = -1;
	int num1 = -1;
	int genderCode = -1;
	int num2 = -1;
	String model = "";
	String modelType = "";
	int referenceCharacterCode = -1;
	int num3 = -1;
	int num4 = -1;
	int num5 = -1;
	String name = "";
	public CharacterFace() 
	{
		name = "New Face";
	}
	CharacterFace(String line) 
	{
		String[] data = bFM.Utils.toStrArr(line);
		characterCode = bFM.Utils.strToInt(data[0]);
		jobCode = bFM.Utils.strToInt(data[1]);
		num1 = bFM.Utils.strToInt(data[2]);
		genderCode = bFM.Utils.strToInt(data[3]);
		num2 = bFM.Utils.strToInt(data[4]);
		model = bFM.Utils.formatString(data[5]);
		modelType = bFM.Utils.formatString(data[6]);
		referenceCharacterCode = bFM.Utils.strToInt(data[7]);
		num3 = bFM.Utils.strToInt(data[8]);
		num4 = bFM.Utils.strToInt(data[9]);
		num5 = bFM.Utils.strToInt(data[10]);
	}
	CharacterFace(String line, boolean fillerForMoreData) 
	{
		String[] data = bFM.Utils.toStrArr(line);
		characterCode = bFM.Utils.strToInt(data[0]);
		jobCode = bFM.Utils.strToInt(data[1]);
		num1 = bFM.Utils.strToInt(data[2]);
		genderCode = bFM.Utils.strToInt(data[3]);
		num2 = bFM.Utils.strToInt(data[4]);
		model = bFM.Utils.formatString(data[5]);
		modelType = bFM.Utils.formatString(data[6]);
		referenceCharacterCode = bFM.Utils.strToInt(data[7]);
		num3 = bFM.Utils.strToInt(data[8]);
		num4 = bFM.Utils.strToInt(data[9]);
		num5 = bFM.Utils.strToInt(data[10]);
		name = bFM.Utils.formatString(data[11]);
	}
	void addVData(String line)
	{
		String[] data = bFM.Utils.toStrArr(line);
		if(characterCode != bFM.Utils.strToInt(data[0]))
		{
			throw new IllegalArgumentException("Character Body V Data Code does not match last Character Body \n" + line + "\n");
		}
		name = bFM.Utils.formatString(line);
	}
	public String toString()
	{
		String ret = "";
		ret += "FC_DAT " + characterCode + "," + jobCode + "," + num1 + "," + genderCode + "," + num2 + ",\"" + model + "\",\"" + modelType + "\"," + 
		referenceCharacterCode + "," + num3 + "," + num4 + "," + num5 + ";\r\n";
		ret += "FC_DATV " + characterCode + ",\"" + name + "\";\r\n";
		return ret;
	}
	String toCSV()
	{
		String ret = "";
		ret += "Face Data " + characterCode + "," + jobCode + "," + num1 + "," + genderCode + "," + num2 + ",\"" + model + "\",\"" + modelType + "\"," + 
		referenceCharacterCode + "," + num3 + "," + num4 + "," + num5 + ",\"" + name + "\"";
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

	public void setModelType(String modelType) 
	{
		this.modelType = modelType;
	}
	public String getModelType() 
	{
		return modelType;
	}

	public void setReferenceCharacterCode(int referenceCharacterCode) 
	{
		this.referenceCharacterCode = referenceCharacterCode;
	}
	public int getReferenceCharacterCode() 
	{
		return referenceCharacterCode;
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
}