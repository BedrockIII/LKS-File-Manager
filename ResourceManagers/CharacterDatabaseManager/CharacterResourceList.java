package ResourceManagers.CharacterDatabaseManager;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import bFM.Data;
import bFM.GenericFile;

public class CharacterResourceList extends GenericFile
{
	ArrayList<CharacterBody> bodies = null;
	ArrayList<CharacterFace> faces = null;
	protected CharacterResourceList(List<String> lines)
	{
		name = "Character Resource List";
		initialize(lines.toArray(new String[0]));
	}
	protected CharacterResourceList(byte[] file) 
	{
		name = "Character Resource List";
		String data = new String(file, Charset.forName("Shift-JIS"));
		String[] lines = data.split(";");
		initialize(lines);
	}
	private void initialize(String[] lines)
	{
		//TODO
		bodies = new ArrayList<CharacterBody>();
		faces = new ArrayList<CharacterFace>();
		CharacterBody lastBody = null;
		CharacterFace lastFace = null;
		for(String line : lines)
		{
			if(line.indexOf("BD_DAT ")!=-1)
			{
				lastBody = new CharacterBody(line);
				bodies.add(lastBody);
			}
			else if(line.indexOf("BD_DATV ")!=-1)
			{
				lastBody.addVData(line);
			}
			else if(line.indexOf("FC_DAT ")!=-1)
			{
				lastFace = new CharacterFace(line);
				faces.add(lastFace);
			}
			else if(line.indexOf("FC_DATV ")!=-1)
			{
				lastFace.addVData(line);
			}
			else if(line.indexOf("Body Data ")!=-1)
			{
				lastBody = new CharacterBody(line, true);
				bodies.add(lastBody);
			}
			else if(line.indexOf("Face Data ")!=-1)
			{
				lastFace = new CharacterFace(line, true);
				faces.add(lastFace);
			}
		}
	}
	public String toString()
	{
		String ret = "";
		ret += "BD_NUM " + bodies.size() + ";\r\n";
		for(CharacterBody body : bodies)
		{
			ret += body.toString() + "\r\n";
		}
		ret += "FC_NUM " + faces.size() + ";\r\n";
		for(CharacterFace face : faces)
		{
			ret += face.toString();
		}
		return ret;
	}
	public String toCSV()
	{
		String ret = "";
		for(CharacterBody body : bodies)
		{
			ret += body.toCSV() + "\n";
		}
		for(CharacterFace face : faces)
		{
			ret += face.toCSV() + "\n";
		}
		return ret;
	}
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
		private CharacterBody(String line) 
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
		private CharacterBody(String line, boolean fillerForMoreData) 
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
		public CharacterBody(String name, int jobCode) 
		{
			this.name = name;
			this.jobCode = jobCode;
		}
		private void addVData(String line)
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
			ret += "BD_DATV " + characterCode + ",\"" + name + "\";";
			return ret;
		}
		private String toCSV()
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
		private CharacterFace(String line) 
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
		private CharacterFace(String line, boolean fillerForMoreData) 
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
		public CharacterFace(String name, int jobCode) 
		{
			this.name = name;
			this.jobCode = jobCode;
		}
		private void addVData(String line)
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
		private String toCSV()
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
	public ArrayList<CharacterBody> getBodies() 
	{
		return bodies;
	}
	public ArrayList<CharacterFace> getFaces() 
	{
		return faces;
	}
	public void removeBody(CharacterBody file)
	{
		int code = file.getCharacterCode();
		for(int i = bodies.size()-1; i >= 0; i--)
		{
			if(bodies.get(i).getCharacterCode() == code)
			{
				bodies.remove(i);
			}
		}
	}
	public void removeFace(CharacterFace file)
	{
		int code = file.getCharacterCode();
		for(int i = faces.size()-1; i >= 0; i--)
		{
			if(faces.get(i).getCharacterCode() == code)
			{
				faces.remove(i);
			}
		}
	}
	public void addFace(String face, int jobCode) 
	{
		faces.add(new CharacterFace(face, jobCode));
	}
	public void addBody(String body, int jobCode) 
	{
		bodies.add(new CharacterBody(body, jobCode));
	}
	public CharacterFace getLastFace() 
	{
		return faces.get(faces.size() - 1);
	}
	public CharacterBody getLastBody() 
	{
		return bodies.get(bodies.size() - 1);
	}
	public int getAmountOfBodies() 
	{
		return bodies.size();
	}
	public int getAmountOfFaces() 
	{
		return faces.size();
	}
}
