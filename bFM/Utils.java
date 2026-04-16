package bFM;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Utils 
{
	final static boolean debugOutput = true;
	public static void DebugPrint(String message)
	{
		if(debugOutput)
		{
			System.out.println(message);
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
	public static ArrayList<String> extractStrings(byte[] data, int startPos)
	{
		ArrayList<String> Strings = new ArrayList<String>();
		String temp = "";
		for(int i = startPos; i<data.length; i++)
		{
			if(data[i]==0x00)
			{
				try {
					Strings.add(new String(temp.getBytes(), "SHIFT-JIS"));
				} catch (UnsupportedEncodingException e) 
				{
					e.printStackTrace();
				}
				temp = "";
			}
			else if(data[i]==0x0d)
			{
				temp+="\\r";
			}
			else if(data[i]==0x0a)
			{
				temp+="\\n";
			}
			else if(data[i]==0x09)
			{
				temp+="\\t";
			}
			else 
			{
				temp+=(char)data[i];
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
			ret = lineAfterHeader.substring(startIndex+1, endIndex);
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
			if(ret.charAt(i)=='\\'&&ret.charAt(i+1)=='n')
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
		byte[] temp = new byte[256];
		int k = 0;
		for(int i = 0; i<data.length; i++)
		{
			if(data[i]==0x0a||data[i]==0x0d)
			{
				try {
					String line = new String(temp, "SHIFT-JIS");
					line = line.substring(0, line.indexOf(0x00));
					if(line.length()>0)Strings.add(line);
				} catch (UnsupportedEncodingException e) 
				{
					e.printStackTrace();
				}
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
		if(name.equals("KingdomPlan.bin"))
		{
			return "KingdomPlanDB";
		}
		if(PCKGManager.PCKGManager.isPAC(file))
		{
			//Check if special TODO
			//else return "Package"
			return "Package";
		}
		return "Todo";
	}
	public static float[] formatCoords(String line) 
	{
		// wrapper for the other format Coords
		return formatCoords(line, false);
	}
	public static void testDifferences(byte[] file1, byte[] file2) 
	{
		int count = 0;
		int firstDifference = -1;
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
		System.out.println("Total Differences: " + count);
		System.out.println("Percent Difference: " + ((double)count/((file1.length+file2.length+0.0)/2.0))*100);
		if(firstDifference!=-1)
		{
			System.out.println("First Difference at: " + firstDifference + ". File 1 is: " + file1[firstDifference] + ". File 2 is: " + file2[firstDifference] + ".");
		}
	}
	public static byte[] readFile(String name, String path)
	{
		byte[] ret = null;
		try 
		{
			ret = ClassLoader.getSystemResourceAsStream(name).readAllBytes();
		}
		catch (NullPointerException e)
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
		catch (IOException error) 
		{
			System.out.println("Failed to read internal file: " + name);
		}
		return ret;
	}
}
