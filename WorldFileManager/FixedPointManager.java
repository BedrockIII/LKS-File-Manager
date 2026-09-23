package WorldFileManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bFM.OpenedFile;
import bFM.Utils;

import java.nio.ByteBuffer;

public class FixedPointManager implements OpenedFile
{
	String fpType;
	String name;
	//VFP = Visual FP
	//LFP = Light FP
	//SFP = Sound FP 
	int numObjects;
	int headerSize;
	static boolean DEGREEMODE = true;
	ArrayList<FixedPointObject> objects = new ArrayList<FixedPointObject>();
	ArrayList<String> objectTypes = new ArrayList<String>();
	public FixedPointManager(byte[] data)
	{
		extractObjects(data); 
		name = "NewFP." + fpType.toLowerCase();
	}
	public FixedPointManager(byte[] data, String name)
	{
		extractObjects(data); 
		this.name = name;
	}
	private void extractObjects(byte[] file) 
	{ 
		if(file.length<36) return;
		//Get Type
		byte[] type = Arrays.copyOfRange(file, 32, 36);
		fpType = Utils.decodeBytesToString(type);
		fpType = fpType.replaceAll("\0+$", "");
		//Get Amounts
		numObjects = ByteBuffer.wrap(file).getInt(64);
		headerSize = ByteBuffer.wrap(file).getInt(68);
		int offset = headerSize;
		for(int i = 0; i < numObjects; i++)
		{
			FixedPointObject object = new FixedPointObject(Arrays.copyOfRange(file, offset, offset+160), this);
			objects.add(object);
			offset+=160;
		}
	} 
	protected void registerToParentFromIndex(FixedPointObject child, int parentIndex)
	{
		if(parentIndex== -1)
		{
			child.parent = null;
			return;
		}
		FixedPointObject parent = objects.get(parentIndex);
		parent.registerChild(child);
		
	}
	public FixedPointManager(List<String> lines, String type)
	{
		fpType = type;
		importFromBFP(lines);
	}
	public FixedPointManager(String fpType) 
	{
		this.fpType = fpType;
		name = "New Fixed Point." + fpType;
	}
	public int getAmountOf(String name)
	{
		return getObject(name).children.size();
	}
	public void printVals()
	{
		System.out.println("FP Type: " + fpType);
		for(int i = 0; i<objects.size(); i++)
		{
			String ret = "";
			//if(objects.get(i).getObjectType()!=-1 && objectTypes.get(objects.get(i).getObjectType()).equals("tree00_G"))
			//{
			//if(objects.get(i).getObjectType()!=-1) ret += objects.get(i).toString();
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
	public byte[] toBytes()
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
			ret = bFM.Utils.mergeArrays(ret, objects.get(i).toBytes());
		}
		return ret;
	}
	public String toBFP(int xMin, int xMax, int zMin, int zMax) 
	{
		String ret = "BFP \n";
		for(int i = 0; i<objects.size();i++)
		{
			if(objects.get(i).getXPos()>=xMin&&objects.get(i).getXPos()<=xMax)
			{
				if(objects.get(i).getZPos()>=zMin&&objects.get(i).getZPos()<=zMax)
				{
					ret += objects.get(i).toBFP();
				}
			}
			
		}
		return ret;
	}
	public void setData(byte[] data)
	{
		extractObjects(data);
	}
	public int getSize()
	{
		return 96 + objects.size() * 160;
	}
	public ArrayList<FixedPointObject> getObjects() 
	{
		return objects;
	}
	public static boolean isFixedPointFile(byte[] file) 
	{
		String header = "MDF_FP_WII_100";
		if(file.length<header.length())
		{
			return false;
		}
		for(int i = 0; i < header.length(); i++)
		{
			if((char)(file[i])!=header.charAt(i))
			{
				return false;
			}
		}
		return true;
	}
	public void replaceFromBFP(byte[] data) 
	{
		List<String> lines = bFM.Utils.bytesToStrs(data);
		replaceFromBFP(lines);
	}
	public void replaceFromBFP(List<String> lines) 
	{
		objects.removeAll(objects);
		importFromBFP(lines);
	}
	public void importFromBFP(List<String> lines) 
	{
		FixedPointObject object = null;
		for(String currentLine : lines)
		{
			if(currentLine.indexOf("<<Name>>")!=-1)
			{
				object = new FixedPointObject(currentLine, this);
				objects.add(object);
			}
			else if(currentLine.indexOf("<<Object>>")!=-1||currentLine.indexOf("<<Position>>")!=-1||
					currentLine.indexOf("<<Scale>>")!=-1||currentLine.indexOf("<<Shear>>")!=-1||
					currentLine.indexOf("<<Rotation>>")!=-1)
			{
				object.addLine(currentLine);
			}
			else if(currentLine.length()>1&&currentLine.indexOf("Degree Mode")!=-1)
			{
				DEGREEMODE = true;
			}
			else if(currentLine.length()>1&&currentLine.indexOf("Radian Mode")!=-1)
			{
				DEGREEMODE = false;
			}
			else if(currentLine.length()>1&&currentLine.indexOf("Randomize Rotation")!=-1)
			{
				object.setRandomRotation(true);
			}
			else if(currentLine.length()>1&&currentLine.indexOf("Randomize Scale")!=-1)
			{
				object.setRandomScale(true);
			}
		}	
	}
	public byte[] toBFPBytes()
	{
		return Utils.encodeStringToBytes(toBFP());
	}
	public String getExtension() 
	{
		if(fpType == null) return "*fp";
		return fpType.toLowerCase();
	}
	public boolean equals(String name)
	{
		return this.name.equals(name);
	}
	@Override
	public void setName(String name)
	{
		this.name = name;
	}
	@Override
	public String getName()
	{
		return name;
	}
	public FixedPointObject getObject(String name)
	{
		for(FixedPointObject object : objects)
		{
			if(object.equals(name))
			{
				return object;
			}
		}
		throw new IllegalArgumentException("Fixed Point Object has an undefined parent: " + name);
	}
	public void removePoint(FixedPointObject object)
	{
		object.parent = null;
		objects.remove(object);
		for(FixedPointObject child : object.getChildren())
		{
			removePoint(child);
		}
		object.getChildren().removeAll(object.getChildren());
	}
	public ArrayList<FixedPointObject> clearEmptyNodes()
	{
		ArrayList<FixedPointObject> allObjects = new ArrayList<FixedPointObject>(objects);
		ArrayList<FixedPointObject> removedObjects = new ArrayList<FixedPointObject>();
		for(FixedPointObject object : allObjects)
		{
			if(object.parent != null && object.parent.isParentNode())
			{
				if(object.getChildren().size() == 0)
				{
					removedObjects.add(object);
					removePoint(object);
				}
			}
		}
		return removedObjects;
	}
}
