package WorldFileManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.ByteBuffer;

public class fpInterpreter 
{
	byte[] file;
	String fpType;
	//VFP = Visual FP
	//LFP = Light FP
	//SFP = Sound FP 
	int numObjects;
	int headerSize;
	static boolean DEGREEMODE = false;
	ArrayList<fpObject> objects = new ArrayList<fpObject>();
	ArrayList<String> objectTypes = new ArrayList<String>();
	public fpInterpreter(byte[] data)
	{
		file = data;
		extractObjects(); 
	}
	private void extractObjects() 
	{ 
		//Get Type
		byte[] type = Arrays.copyOfRange(file, 32, 36);
		try {fpType = new String(type, "ISO-8859-1");} catch (Exception error) {System.out.println("Failed to Extract due to being an unsupported encoding");}
		fpType = fpType.replaceAll("\0+$", "");
		//Get Amounts
		numObjects = ByteBuffer.wrap(file).getInt(64);
		headerSize = ByteBuffer.wrap(file).getInt(68);
		int offset = headerSize;
		//Create Object arrays
		byte[] objectArr = new byte[160];
		for(int i = 0; i < numObjects; i++)
		{
			objectArr = Arrays.copyOfRange(file, offset, offset+160);
			offset+=160;
			objects.add(new fpObject(objectArr));
		}
		for(int i = 0; i < objects.size(); i++)
		{
			objectTypes.add(objects.get(i).getName());
			if(objects.get(i).getObjectType()!=-1&&objectTypes.get(i)!=null)objects.get(i).setType(objectTypes.get(objects.get(i).getObjectType()));
		}
	} 
	public fpInterpreter(List<String> lines, String type)
	{
		fpType = type;
		fpObject object = null;
		for(int i =1; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<<Name>>")!=-1)
			{
				object = new fpObject(lines.get(i), objects);
				objects.add(object);
			}
			else if(lines.get(i).indexOf("<<Object>>")!=-1||lines.get(i).indexOf("<<Position>>")!=-1||
					lines.get(i).indexOf("<<Scale>>")!=-1||lines.get(i).indexOf("<<Shear>>")!=-1||
					lines.get(i).indexOf("<<Rotation>>")!=-1)
			{
				object.addLine(lines.get(i));
			}
			else if(lines.get(i).length()>1&&lines.get(i).indexOf("Degree Mode")!=-1)
			{
				DEGREEMODE = true;
			}
			else if(lines.get(i).length()>1&&lines.get(i).indexOf("Radian Mode")!=-1)
			{
				DEGREEMODE = false;
			}
			else if(lines.get(i).length()>1&&lines.get(i).indexOf("Randomize Rotation")!=-1)
			{
				object.setRandomRotation(true);
			}
			else if(lines.get(i).length()>1&&lines.get(i).indexOf("Randomize Scale")!=-1)
			{
				object.setRandomScale(true);
			}
		}	
	}
	public int getAmountOf(String object)
	{
		int ret = 0;
		for(int i = 0; i<objects.size(); i++)
		{
			object = "tree00_G";
			if(objects.get(i).getObjectType()!=-1 && objectTypes.get(objects.get(i).getObjectType()).equals(object))
			{
				ret++;
			}
		}
		return ret;
	}
	public void printVals()
	{
		System.out.println("FP Type: " + fpType);
		for(int i = 0; i<objects.size(); i++)
		{
			String ret = "";
			//if(objects.get(i).getObjectType()!=-1 && objectTypes.get(objects.get(i).getObjectType()).equals("tree00_G"))
			//{
			if(objects.get(i).getObjectType()!=-1) ret += objects.get(i).toString();
			//if(objects.get(i).getObjectType()!=0)if(objects.get(i).getObjectType()!=-1)System.out.println("Object Type: " + objectTypes.get(objects.get(i).getObjectType()));
			if(ret.length()>0)System.out.println(ret);
			//input.nextLine();
			//}
		}
	}
	public String toBFP()
	{
		String ret = "BFP \n";
		for(int i = 0; i<objects.size();i++)
		{
			ret += objects.get(i).toBFP();
		}
		return ret;
	}
	public byte[] getBytes()
	{
		String Fp = "MDF_FP_WII_100";
		byte[] ret = new byte[32];
		for(int i = 0;i<32&&i<Fp.length(); i++)
		{
			ret[i] = (byte) Fp.charAt(i);
		}
		byte[] nameArr = new byte[32];
		for(int i = 0;i<32&&i<fpType.length(); i++)
		{
			nameArr[i] = (byte) fpType.charAt(i);
		}
		ret = bFM.Utils.mergeArrays(ret, nameArr);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(objects.size()).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(96).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[24]);
		for(int i = 0; i<objects.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, objects.get(i).getBytes());
		}
		return ret;
	}
	public String toBFP(int xMin, int xMax, int zMin, int zMax) 
	{
		String ret = "BFP \n";
		for(int i = 0; i<objects.size();i++)
		{
			if(objects.get(i).xPos>=xMin&&objects.get(i).xPos<=xMax)
			{
				if(objects.get(i).zPos>=zMin&&objects.get(i).zPos<=zMax)
				{
					ret += objects.get(i).toBFP();
				}
			}
			
		}
		return ret;
	}
}
