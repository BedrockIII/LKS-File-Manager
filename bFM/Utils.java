package bFM;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

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
		String allowedChars = "1234567890-";
		String integer = "";
		for(int i = line.indexOf(">>"); i<line.length();i++)
		{
			if(allowedChars.indexOf(line.charAt(i))!=-1)
			{
				integer+=line.charAt(i);
			}
		}
		return Integer.parseInt(integer);
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
		String ret = "";
		String lineAfterHeader = line.substring(line.indexOf(">>"));
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
}
