package bFM;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils 
{
	public final static int JAPANESE_LANGUAGE = 0;
	public final static int ENGLISH_LANGUAGE = 1;
	public final static int FRENCH_LANGUAGE = 2;
	public final static int ITALIAN_LANGUAGE = 3;
	public final static int GERMAN_LANGUAGE = 4;
	public final static int SPANISH_LANGUAGE = 5;
	public static boolean debugOutput = false;
	public static boolean autoEditSubPackFile = true;
	public static void DebugPrint(String message)
	{
		if(debugOutput)
		{
			System.out.println(message);
		}
	}
	public static void DebugPrintF(String message, String...strings)
	{
		if(debugOutput)
		{
			System.out.printf(message + '\n', (Object[])strings);
		}
	}
	public static byte[] mergeArrays(byte[] main, byte[] add)
	{
		if(add==null) return main;
		if(main==null) return add;
		byte[] ret = new byte[main.length+add.length];
		for(int i = 0; i < main.length; i++)
		{
			ret[i] = main[i];
		}
		for(int i = 0; i < add.length; i++)
		{
			ret[i+main.length] = add[i];
		}
		return ret;
	}
	public static byte[] toByteArr(int input, int arrLength) 
	{
		int oldInput = input;
		if(input>=0)
		{
			byte[] ret = new byte[arrLength];
			for(int i = 1; i<=arrLength; i++)
			{
				ret[arrLength-i] = (byte) (input%256);
				input/=256;
			}
			return ret;
		}
		else if(input==-1)
		{
			byte[] ret = new byte[arrLength];
			for(int i = 0; i<arrLength; i++)
			{
				ret[i] = (byte) 0xff;
			}
			return ret;
		}
		return toByteArr(65536+oldInput, arrLength);
	}
	public static byte[] longToBytes(long num, int size)
	{
		byte[] ret = new byte[size];
		int place=0;
		for(long i = (long) Math.pow(256, size); i > 1; i/=256)
		{
			ret[place] = (byte)(num*256/i);
			place++;
		}
		
		return ret;
	}
	public static byte[] removeEmptySpace(byte[] data)
	{
		int num = data.length;
		for(int i = 0; i<data.length; i++)
		{
			if(data[i]==0x00)
			{
				num=i;
				break;
			}
		}
		
		byte[] newData = new byte[num];
		for(int i = 0; i<num; i++)
		{
			newData[i] = data[i];
		}
		return newData;
	}
	public static int getShort(byte[] data, int index)
	{
		if(data==null)
		{
			return -1;
		}
		if(data.length<index+2)
		{
			return -1;
		}
		int ret = (int)data[index];
		if(ret<0)ret+=256;
		ret*=256;
		int ret2 =(int)data[index+1];
		if(ret2<0)ret2+=256;
		ret+=ret2;
		if(ret==65535) return -1;
		return (ret);
	}
	public static int formatFlag(String line) 
	{
		//Return whatever number is after a ">>", which is used to denote the variable names 
		//Rounds down at a decimal
		String allowedChars = "1234567890-";
		String integer = "";
		for(int i = line.indexOf(">>"); i<line.length();i++)
		{
			if(allowedChars.indexOf(line.charAt(i))!=-1)
			{
				integer+=line.charAt(i);
			}
			if(line.charAt(i)=='.')
			{
				break;
			}
		}
		return Integer.parseInt(integer);
	}
	public static float formatFloat(String line) 
	{
		//Return whatever number is after a ">>", which is used to denote the variable names 
		//Rounds down at a decimal
		String allowedChars = "1234567890-.E";
		String floatingPoint = "";
		for(int i = line.indexOf(">>"); i<line.length();i++)
		{
			if(allowedChars.indexOf(line.charAt(i))!=-1)
			{
				floatingPoint+=line.charAt(i);
			}
		}
		return Float.parseFloat(floatingPoint);
	}
	public static float[] formatCoords(String line, boolean blenderCoords) 
	{
		//Return whichever 3 floats are after a ">>", which is used to denote the variable names 
		String numChars = "1234567890-.E ";
		float xVal, yVal, zVal, wVal = (float) -1.0;
		if(line.indexOf('{')!=-1&&line.indexOf('}')!=-1)
		{
			wVal = Float.valueOf(line.substring(line.indexOf('{')+1, line.indexOf('}')));
		}
		else
		{
			wVal = 1;
		}
		int startX = -1;
		int endX=-1;
		int startY = -1;
		int endY=-1;
		int startZ=-1;
		int endZ;
		int LoopStart;
		int LoopEnd;
		if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
		{
			endZ = line.indexOf(')');
			LoopStart = line.indexOf('(');
		}
		else
		{
			endZ = line.length();
			LoopStart = line.indexOf('>');
		}
		LoopEnd = endZ;
			
			boolean inFloat = false;
			for(int j = LoopStart; j<LoopEnd; j++)
			{
				if(inFloat&&numChars.indexOf(line.charAt(j))==-1)
				{
					if(endX==-1) endX=j;
					else if(endY==-1) endY=j;
					else endZ=j;
					inFloat=false;
				}
				if(!inFloat&&numChars.indexOf(line.charAt(j))!=-1)
				{
					if(startX==-1) startX=j; 
					else if(startY==-1) startY=j;
					else startZ=j;
					inFloat=true;
				}
			}
			xVal = Float.valueOf(line.substring(startX, endX));
			yVal = Float.valueOf(line.substring(startY, endY));
			if(endZ>startZ) zVal = Float.valueOf(line.substring(startZ, endZ));
			else zVal = Float.valueOf(line.substring(startZ, line.length()-1));
			if(blenderCoords)
			{
				xVal *= 100.0;
				yVal *= 100.0;
				zVal *= -100.0;
			}
			float[] ret = {wVal, xVal, yVal, zVal};
			return ret;
	}
	public static String[] toStrArr(String line) 
	{
		ArrayList<String> ret = new ArrayList<String>();
		while(line.length()>0)
		{
			int endex = line.indexOf(',');
			if(endex == -1) endex = line.indexOf(';');
			if(endex == -1) endex = line.length();
			String value = line.substring(0, endex);
			ret.add(value);
			if(endex!=line.length())
			{
				line = line.substring(endex+1);
			}
			else
			{
				break;
			}
		}
		String[] returnArr = new String[ret.size()];
		for(int i = 0; i <ret.size(); i++)
		{
			returnArr[i] = ret.get(i);
		}
		return  returnArr;
	}
	public static ArrayList<String> extractStrings(byte[] data)
	{
		return extractStrings(data,0);
	}
	public static ArrayList<String> extractStringsNoFormatting(byte[] data)
	{
		return extractStringsNoFormatting(data,0);
	}
	public static ArrayList<String> extractStrings(byte[] data, int startPos)
	{
		ArrayList<String> Strings = new ArrayList<String>();
		byte[] temp = new byte[1024];
		int k = 0;
		for(int i = startPos; i<data.length; i++)
		{
			if(data[i]==0x00)
			{
				Strings.add(decodeBytesToString(temp));
				temp = new byte[temp.length];
				k=0;
			}
			else if(data[i]==0x0d)
			{
				temp[k] = (byte)'\\';
				k++;
				temp[k] = (byte)'r';
				k++;
			}
			else if(data[i]==0x0a)
			{
				temp[k] = (byte)'\\';
				k++;
				temp[k] = (byte)'n';
				k++;
			}
			else if(data[i]==0x09)
			{
				temp[k] = (byte)'\\';
				k++;
				temp[k] = (byte)'t';
				k++;
			}
			else 
			{
				temp[k] = data[i];
				k++;
			}
		}
		return Strings;
	}
	public static ArrayList<String> extractStringsNoFormatting(byte[] data, int startPos)
	{
		ArrayList<String> Strings = new ArrayList<String>();
		byte[] temp = new byte[1024];
		int k = 0;
		for(int i = startPos; i<data.length; i++)
		{
			if(data[i]==0x00)
			{
				Strings.add(decodeBytesToString(temp));
				temp = new byte[temp.length];
				k=0;
			}
			else 
			{
				temp[k] = data[i];
				k++;
			}
		}
		return Strings;
	}
	public static String formatString(String line) 
	{
		//Return whatever is between the two outermost quotation marks after a ">>", which is used to denote the variable names 
		String ret = "";
		String lineAfterHeader = line.substring(Math.max(line.indexOf(">>"), 0));
		int startIndex = lineAfterHeader.indexOf('\"');
		int endIndex = lineAfterHeader.lastIndexOf('\"');
		if(startIndex!=-1&&endIndex!=-1)
		{
			try
			{
				ret = lineAfterHeader.substring(startIndex+1, endIndex);
			}
			catch(StringIndexOutOfBoundsException e)
			{
				System.out.println(line);
				e.printStackTrace();
				return line;
			}
		}
		else
		{
			ret = lineAfterHeader;
		}
		return formatStringChars(ret);
	}
	public static String formatStringChars(String ret)
	{
		String finalLine = "";
		for(int i = 0; i<ret.length(); i++)
		{
			if(ret.charAt(i)=='\\'&&i+1==ret.length())
			{
				finalLine += "\\";
			}
			else if(ret.charAt(i)=='\\'&&ret.charAt(i+1)=='n')
			{
				finalLine += "\n";
				i++;
			}
			else if(ret.charAt(i)=='\\'&&ret.charAt(i+1)=='r')
			{
				finalLine += "\r";
				i++;
			} else if(ret.charAt(i)=='\\'&&ret.charAt(i+1)=='t')
			{
				finalLine += "\t";
				i++;
			}
			else if(ret.charAt(i)=='\\'&&ret.charAt(i+1)=='\\')
			{
				finalLine += "\\";
				i++;
			}
			else
			{
				finalLine += ret.charAt(i);
			}
		}
		return finalLine;
	}
	public static String toFormatedString(String ret)
	{
		String finalLine = "";
		for(int i = 0; i<ret.length(); i++)
		{
			if(ret.charAt(i)=='\n')
			{
				finalLine += "\\n";
			}
			else if(ret.charAt(i)=='\r')
			{
				finalLine += "\\r";
			} else if(ret.charAt(i)=='\t')
			{
				finalLine += "\\t";
			}
			else if(ret.charAt(i)=='\\')
			{
				finalLine += "\\\\";
			}
			else
			{
				finalLine += ret.charAt(i);
			}
		}
		return finalLine;
	}
	public static int strToInt(String str)
	{
		String allowedChars = "1234567890-";
		String integer = "";
		for(int i = 0; i<str.length();i++)
		{
			if(allowedChars.indexOf(str.charAt(i))!=-1)
			{
				integer+=str.charAt(i);
			}
		}
		try
		{
			return Integer.parseInt(integer);
		}
		catch (NumberFormatException w)
		{
			
		}
		return 0;
	}
	public static List<String> bytesToStrs(byte[] data)
	{
		List<String> Strings = new ArrayList<String>();
		byte[] temp = new byte[512];
		int k = 0;
		for(int i = 0; i<data.length; i++)
		{
			if(data[i]==0x0a||data[i]==0x0d||data[i]==0x00)
			{
				String line = decodeBytesToString(temp);
				int ending = line.indexOf(0x00);
				if(ending == -1) ending = line.length();
				line = line.substring(0, ending);
				if(line.length()>0)Strings.add(line);
				
				temp = new byte[temp.length];
				k=0;
			
			}
			else
			{
				temp[k] = data[i];
				k++;
			}
		}
		return Strings;
	}
	public static String getFileType(String name, byte[] file) 
	{
		
		if(PCKGManager.PCKGManager.isPAC(file))
		{
			if(name.equals("KingdomPlan.bin")||name.equals("Kingdom Plan Config"))
			{
				return "KingdomPlanDB";
			}
			else if(name.indexOf("chrDB") != -1||name.equals("Character Data Base"))
			{
				return "CharacterDB";
			}
			else if(name.indexOf("itemDB") != -1||name.equals("Item Data Base"))
			{
				if(name.indexOf("itemDB") != -1 && name.length() > 8)
				{
					System.out.println("Setting Language to " + name.charAt(8));
					Settings.LanguageCode = Integer.parseInt("" + name.charAt(8));
				}
				return "ItemDB";
			}
			else if(name.equals("CameraData.bin")||name.equals("Camera Zone Config"))
			{
				return "CameraZoneDB";
			}
			else if(name.equals("Album.bin"))
			{
				return "WonderSpotDB";
			}
			//Check if special TODO
			//else return "Package"
			return "Package";
		}
		else if(colReader.ColReader.isCollisionFile(file))
		{
			return "Collision";
		}
		else if(WorldFileManager.fpInterpreter.isFixedPointFile(file))
		{
			return "Fixed Point";
		}
		return "Todo";
	}
	public static float[] formatCoords(String line) 
	{
		// wrapper for the other format Coords
		return formatCoords(line, false);
	}
	public static boolean testDifferences(byte[] file1, byte[] file2) 
	{
		int count = 0;
		int firstDifference = -1;
		boolean ret = true;
		for(int i = 0; i<Math.min(file1.length, file2.length); i++)
		{
			if(file1[i]!=file2[i])
			{
				if(firstDifference==-1)
				{
					firstDifference = i;
				}
				count++;
				System.out.println("Difference at: " + i + ". File 1 is: " + file1[i] + ". File 2 is: " + file2[i] + ".");
			}
		}
		System.out.println("File 1 size: " + file1.length);
		System.out.println("File 2 size: " + file2.length);
		if(file1.length!=file2.length) ret = false;
		System.out.println("Total Differences: " + count);
		System.out.println("Percent Difference: " + ((double)count/((file1.length+file2.length+0.0)/2.0))*100);
		if(firstDifference!=-1)
		{
			System.out.println("First Difference at: " + firstDifference + ". File 1 is: " + file1[firstDifference] + ". File 2 is: " + file2[firstDifference] + ".");
			ret = false;
		}
		else
		{
			System.out.println("No Differences!!!");
		}
		return ret;
	}
	public static byte[] readFile(String name, String path)
	{
		byte[] ret = null;
		if(ClassLoader.getSystemResourceAsStream(name)!=null)
		{
			try 
			{
				ret = ClassLoader.getSystemResourceAsStream(name).readAllBytes();
			} 
			catch (IOException error) 
			{
				System.out.println("Failed to read internal file: " + name);
			}
		}
		else
		{
			try 
			{
				ret = Files.readAllBytes(Paths.get(path + name));
			} 
			catch (IOException error) 
			{
				System.out.println("Failed to read file at: " + Paths.get(path + name));
			}
		}
		
		return ret;
	}
	public static int byteToInt(byte b)
	{
		int ret = Byte.toUnsignedInt(b);
		if(ret == 255) return -1;
		return ret;
	}
	public static byte[] mergeArrays(byte[] ret, byte num13a) 
	{
		byte[] temp = {num13a};
		return mergeArrays(ret, temp);
	}
	public static void setDebugOutput(boolean b) 
	{
		debugOutput = b;
	}
	public static float strToFloat(String str) 
	{
		String allowedChars = "1234567890-.";
		String integer = "";
		for(int i = 0; i<str.length();i++)
		{
			if(allowedChars.indexOf(str.charAt(i))!=-1)
			{
				integer+=str.charAt(i);
			}
		}
		try
		{
			return Float.parseFloat(integer);
		}
		catch (NumberFormatException w)
		{
			
		}
		return 0;
	}
	public static int getShort(ByteBuffer data)
	{
		int ret = (0xFFFF & data.getShort());
		if(ret == 65535) return -1;
		return ret;
	}
	public static String getFileType(OpenedFile file) 
	{
		return getFileType(file.getName(), file.toBytes());
	}
	public static boolean isGenericPAC(byte[] fileContents, String name) 
	{
		return getFileType(name, fileContents).equals("Package");
	}
	public static String getAsSetting(String settingName, boolean value) 
	{
		String ret = "<<" + settingName + ">> " + value + "\n"; 
		return ret;
	}
	public static boolean getSettingValue(String line) 
	{
		line = line.substring(line.indexOf(">>") + 1);
		return line.toLowerCase().indexOf("true")!=-1;
	}
	public static int getSettingValueInt(String line) 
	{
		return formatFlag(line);
	}
	public static String getSettingValueString(String line) 
	{
		String ret = "";
		String lineAfterHeader = line.substring(Math.max(line.indexOf(">>"), 0));
		int startIndex = lineAfterHeader.indexOf('\"');
		int endIndex = lineAfterHeader.lastIndexOf('\"');
		if(startIndex!=-1&&endIndex!=-1)
		{
			ret = lineAfterHeader.substring(startIndex+1, endIndex);
		}
		else
		{
			ret = lineAfterHeader;
		}
		return ret;
	}
	public static String getAsSetting(String settingName, int value) 
	{
		String ret = "<<" + settingName + ">> " + value + "\n"; 
		return ret;
	}
	public static String getAsSetting(String settingName, String value) 
	{
		String ret = "<<" + settingName + ">> \"" + value + "\"\n"; 
		return ret;
	}
	public static int byteArrIndex(byte[] arr, byte is)
	{
		for(int i = 0; i<arr.length; i++)
		if(arr[i]==is) return i;
		return -1;
	}
	public static byte[] encodeStringToBytes(String text)
	{
		byte[] ret = null;
		try
		{
			switch(Settings.LanguageCode)
			{
			case JAPANESE_LANGUAGE:
				ret = encodeStringToBytes(text, Charset.forName("Shift-JIS"));
				break;
			case ENGLISH_LANGUAGE:
				try 
				{
					ret = encodeStringToBytes(text, StandardCharsets.UTF_8);
				}
				catch (Exception e) 
				{
					//Must be Japanese Text that was untranslated
					ret = encodeStringToBytes(text, Charset.forName("Shift-JIS"));
				}
				break;
			case FRENCH_LANGUAGE:
				try 
				{
					ret = encodeStringToBytes(text, StandardCharsets.UTF_8);
				}
				catch (Exception e) 
				{
					//Must be Japanese Text that was untranslated
					ret = encodeStringToBytes(text, Charset.forName("Shift-JIS"));
				}
				break;
			case ITALIAN_LANGUAGE:
				try 
				{
					ret = encodeStringToBytes(text, StandardCharsets.UTF_8);
				}
				catch (Exception e) 
				{
					//Must be Japanese Text that was untranslated
					ret = encodeStringToBytes(text, Charset.forName("Shift-JIS"));
				}
				break;
			case GERMAN_LANGUAGE:
				try 
				{
					ret = encodeStringToBytes(text, StandardCharsets.UTF_8);
				}
				catch (Exception e) 
				{
					//Must be Japanese Text that was untranslated
					ret = encodeStringToBytes(text, Charset.forName("Shift-JIS"));
				}
				break;
			case SPANISH_LANGUAGE:
				try 
				{
					ret = encodeStringToBytes(text, StandardCharsets.UTF_8);
				}
				catch (Exception e) 
				{
					//Must be Japanese Text that was untranslated
					ret = encodeStringToBytes(text, Charset.forName("Shift-JIS"));
				}
				break;
			}
		}
		catch (Exception e) 
		{
			//Panic. The Text is unknown
			e.printStackTrace();
			System.err.println("Language Encoding could not be determined, defaulting to Shift-JIS with Special Characters Removed");
			ret = text.getBytes(Charset.forName("Shift-JIS"));
		}
		return ret;
	}
	public static byte[] encodeStringToBytes(String text, Charset charset) throws Exception
	{
		CharsetEncoder encoder = charset.newEncoder();
		encoder.onMalformedInput(CodingErrorAction.REPORT);
		encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
		
		ByteBuffer data = encoder.encode(CharBuffer.wrap(text));
		
		byte[] ret = new byte[data.remaining()];
		data.get(ret);
		return ret;
	}
	public static String decodeBytesToString(byte[] data)
	{
		String ret = "";
		try 
		{
			switch(Settings.LanguageCode)
			{
			case JAPANESE_LANGUAGE:
				ret = decodeBytesToString(data, Charset.forName("Shift-JIS"));
				break;
			case ENGLISH_LANGUAGE:
				try 
				{
					ret = decodeBytesToString(data, StandardCharsets.UTF_8);
				}
				catch (Exception e) 
				{
					//Must be Japanese Text that was untranslated
					ret = decodeBytesToString(data, Charset.forName("Shift-JIS"));
				}
				break;
			case FRENCH_LANGUAGE:
				try 
				{
					ret = decodeBytesToString(data, StandardCharsets.UTF_8);
				}
				catch (Exception e) 
				{
					//Must be Japanese Text that was untranslated
					ret = decodeBytesToString(data, Charset.forName("Shift-JIS"));
				}
				break;
			case ITALIAN_LANGUAGE:
				try 
				{
					ret = decodeBytesToString(data, StandardCharsets.UTF_8);
				}
				catch (Exception e) 
				{
					//Must be Japanese Text that was untranslated
					ret = decodeBytesToString(data, Charset.forName("Shift-JIS"));
				}
				break;
			case GERMAN_LANGUAGE:
				try 
				{
					ret = decodeBytesToString(data, StandardCharsets.UTF_8);
				}
				catch (Exception e) 
				{
					//Must be Japanese Text that was untranslated
					ret = decodeBytesToString(data, Charset.forName("Shift-JIS"));
				}
				break;
			case SPANISH_LANGUAGE:
				try 
				{
					ret = decodeBytesToString(data, StandardCharsets.UTF_8);
				}
				catch (Exception e) 
				{
					//Must be Japanese Text that was untranslated
					ret = decodeBytesToString(data, Charset.forName("Shift-JIS"));
				}
				break;
			}
		}
		catch (Exception e) 
		{
			//Panic. The Text is unknown
			System.err.println("Language Encoding could not be determined, defaulting to Shift-JIS with Special Characters Removed");
			ret = new String(data, Charset.forName("Shift-JIS"));
		}
		return ret;
	}
	public static String decodeBytesToString(byte[] data, Charset charset) throws Exception
	{
		CharsetDecoder decoder = charset.newDecoder();
		decoder.onMalformedInput(CodingErrorAction.REPORT);
		decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
		
		int len = 0;
		while (len < data.length && data[len] != 0)
		    len++;

		return decoder.decode(ByteBuffer.wrap(data, 0, len)).toString();
	}
	public static String getLanguage(int languageCode)
	{
		//Return the name of the language based off number
		switch(languageCode)
		{
		case JAPANESE_LANGUAGE: return "Japanese";
		case ENGLISH_LANGUAGE: return "English";
		case FRENCH_LANGUAGE: return "French";
		case ITALIAN_LANGUAGE: return "Italian";
		case GERMAN_LANGUAGE: return "German";
		case SPANISH_LANGUAGE: return "Spanish";
		}
		throw new IllegalArgumentException("Language code: " + languageCode + " is undefined");
	}
	public static int getLanguageCodeByName(String name)
	{
		if(name.indexOf("Japanese") != -1) return JAPANESE_LANGUAGE;
		if(name.indexOf("English") != -1) return ENGLISH_LANGUAGE;
		if(name.indexOf("French") != -1) return FRENCH_LANGUAGE;
		if(name.indexOf("Italian") != -1) return ITALIAN_LANGUAGE;
		if(name.indexOf("German") != -1) return GERMAN_LANGUAGE;
		if(name.indexOf("Spanish") != -1) return SPANISH_LANGUAGE;
		return -1;
	}
}
