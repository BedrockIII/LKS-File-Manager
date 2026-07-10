package BrresManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BrresTester 
{
	static BrresFile file = null;
	public static void main(String[] args) 
	{
		try 
		{
			file = new BrresFile(Files.readAllBytes(Paths.get("D:\\LKS Mod\\1112\\wg1112.brres")));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		testNodeReader();
		testMDL0Reader();
	}
	private static void testMDL0Reader() 
	{
		// TODO Auto-generated method stub
		
	}
	public static void testNodeReader()
	{
		file.printContents();
	}
}
