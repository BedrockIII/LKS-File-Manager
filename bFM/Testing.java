package bFM;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Testing
{
	private static final Path TestOutputPath = Paths.get("D:\\LKS Mod\\Testing");
	private enum Verbosity
	{
		Verbose,
		Info,
		Errors,
		None;
		private static boolean isValid(Verbosity Criterion)
		{
			if(VerboseOutputLevel == Verbose)
			{
				return true;
			}
			if(VerboseOutputLevel == Info)
			{
				return Criterion != Verbose;
			}
			if(VerboseOutputLevel == Errors)
			{
				return Criterion == Errors;
			}
			return false;
		}
	}
	private final static Verbosity VerboseOutputLevel = Verbosity.Verbose;
	@SuppressWarnings("unused")
	private static void Assert()
	{
		if(Verbosity.isValid(Verbosity.Errors))
		{
			throw new AssertionError();
		}
	}
	@SuppressWarnings("unused")
	public static void Assert(String assertion)
	{
		if(Verbosity.isValid(Verbosity.Errors))
		{
			throw new AssertionError(assertion + "\n");
		}
	}
	@SuppressWarnings("unused")
	private static void Inform()
	{
		if(Verbosity.isValid(Verbosity.Info))
		{
			System.out.println();
		}
	}
	@SuppressWarnings("unused")
	private static void Inform(String message)
	{
		if(Verbosity.isValid(Verbosity.Info))
		{
			System.out.println(message);
		}
	}
	@SuppressWarnings("unused")
	private static void Print()
	{
		if(Verbosity.isValid(Verbosity.Verbose))
		{
			System.out.println();
		}
	}
	@SuppressWarnings("unused")
	private static void Print(String message)
	{
		if(Verbosity.isValid(Verbosity.Verbose))
		{
			System.out.println(message);
		}
	}
	public static boolean TestSize(byte[] src, byte[] out) 
	{
		Inform("File 1 size: " + src.length);
		Inform("File 2 size: " + out.length);
		if(src.length != out.length)
			Assert(String.format("Output Length Does Not Equal Soruce Length.\n\tsrc: %d\n\tout: %d", src.length, out.length));
		return true;
		
	}
	public static int TestRawDifferences(byte[] src, byte[] out) 
	{
		int totalDifferences = 0;
		int firstDifference = -1;
		for(int i = 0; i<Math.min(src.length, out.length); i++)
		{
			if(src[i]!=out[i])
			{
				if(firstDifference==-1)
				{
					firstDifference = i;
				}
				totalDifferences++;
				Print("Difference at: " + i + ". Source is: " + src[i] + ". Output is: " + out[i] + ".");
			}
		}
		totalDifferences += Math.abs(src.length-out.length);
		
		if(firstDifference!=-1)
		{
			Inform("First Difference at: " + firstDifference + ". File 1 is: " + src[firstDifference] + ". File 2 is: " + out[firstDifference] + ".");
			Inform("Total Differences: " + totalDifferences);
		}
		else
		{
			Print("No Differences!!!");
		}
		
		return totalDifferences;
	}
	public static boolean TestPercentDifference(byte[] src, byte[] out, double threshold) 
	{
		int totalDifferences = 0;
		double percentDifference = -100;
		totalDifferences = TestRawDifferences(src, out);
		percentDifference = ((double)totalDifferences/src.length)*100;
		
		
		try
		{
			TestSize(src, out);
		}
		catch(AssertionError e)
		{
			Inform(e.getMessage());
		}
		Inform("Percent Difference: " + percentDifference);
		if(totalDifferences == 0) return true;
		return percentDifference < threshold;
	}
	public static boolean TestPercentDifference(byte[] src, byte[] out) 
	{
		return TestPercentDifference(src, out, 0);
	}
	
	
	
	
	
	public static void saveFiles(byte[] src, byte[] out, String name)
	{
		String namePrefix = name.substring(0, name.indexOf('.'));
		String nameSuffix = name.substring(name.indexOf('.'));
		String srcName = namePrefix + "_Source" + nameSuffix;
		String outName = namePrefix + "_Output" + nameSuffix;
		
		try
		{
			Files.write(TestOutputPath.resolve(srcName), src);
			Files.write(TestOutputPath.resolve(outName), out);
		} catch (IOException e)
		{
			e.printStackTrace();
			Assert("Failed to write files");
		}
	}
}
