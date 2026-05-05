package ResourceManagers.CharacterDatabaseManager;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

class CharacterResourceList
{
	ArrayList<CharacterBody> bodies = null;
	ArrayList<CharacterFace> faces = null;
	protected CharacterResourceList(List<String> lines)
	{
		initialize(lines.toArray(new String[0]));
	}
	protected CharacterResourceList(byte[] file) 
	{
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
	private class CharacterBody
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
	}
	private class CharacterFace
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
	}
}
