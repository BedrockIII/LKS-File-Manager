package BrresManager;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import BrresManager.BrresHelpers.ResDictionary;

public class BrresFile 
{
	BrresHeader header;
	//Stuff
	//ArrayList<MDL0> Models = new ArrayList<MDL0>();
	ResDictionary files;
	private final ByteBuffer data;
	public BrresFile()
	{
		this.data = null;
		throw new IllegalArgumentException("Empty Brres Files arent Supported Yet");
	}
	public BrresFile(byte[] file)
	{
		data = ByteBuffer.wrap(file);
		//ByteBuffer data = ByteBuffer.wrap(file);
		//initializeFromBytes(data);
	}
	public BrresFile(ByteBuffer data)
	{
		this.data = data;
		//initializeFromBytes(data);
	}
	private void initializeFromBytes(ByteBuffer data)
	{
		header = new BrresHeader(data);
	}
	public void getNodes() 
	{
		// TODO Auto-generated method stub
		
	}
	public static String getString(ByteBuffer data, int pos) 
	{
		int ogPos = data.position();
		String ret = "";
		
		int end = pos;

		while(end < data.capacity() && data.get(end) != 0)
		{
			end++;
		}
		
		ret =  new String(data.array(), pos, end - pos, Charset.forName("Shift-JIS"));
		
		data.position(ogPos);
		return ret;
	}
	public void printContents()
	{
			verifyHeader();

			Pointer root = new Pointer(0x10);
			
			System.out.println("ROOT @ " + root.toString());

			readRoot(root);
	}
	private void verifyHeader()
	{
		String magic = BrresHelpers.readString(data, new Pointer(0), 4);
		
		if(!magic.equals("bres"))
			throw new RuntimeException("Not a BRRES file");
	}
	
	
	private void readRoot(Pointer root)
	{
		String magic = BrresHelpers.readString(data, root, 4);

		if(!magic.equals("root"))
			throw new RuntimeException("Expected \'root\', found: " + magic);

		Pointer dictionary = new Pointer(root, 8);

		files = BrresHelpers.readDictionary(data, dictionary, "\t", dictionary);
	}
}
