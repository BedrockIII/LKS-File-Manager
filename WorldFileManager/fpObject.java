package WorldFileManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class fpObject 
{
	public String name;
	int index;
	int objectType = -1;
	float xUnknownTransformation;
	float yUnknownTransformation;
	float zUnknownTransformation;
	float wUnknownTransformation;
	float xStretch = 1;
	float yStretch = 1;
	float zStretch = 1;
	float wStretch = 1;
	float xAxisRotation;
	float yAxisRotation;
	float zAxisRotation;
	float wAxisRotation;
	float xPos;
	float yPos;
	float zPos;
	float wPos;
	boolean blenderCoords, randomizeRotation, randomizeScale = false;
	ArrayList<fpObject> referenceObjects = new ArrayList<fpObject>();
	String type = "";
	public fpObject(String name, int index, int objectType, 
			float xUnknownTransformation, float yUnknownTransformation, float zUnknownTransformation, float wUnknownTransformation, 
			float xStretch, float yStretch, float zStretch, float wStretch, 
			float xAxisRotation, float yAxisRotation, float zAxisRotation, float wAxisRotation, 
			float xPos, float yPos, float zPos, float wPos)
	{
		this.name = name;
		this.index = index;
		this.objectType = objectType;
		this.xUnknownTransformation = xUnknownTransformation;
		this.yUnknownTransformation = yUnknownTransformation;
		this.zUnknownTransformation = zUnknownTransformation;
		this.wUnknownTransformation = wUnknownTransformation;
		this.xStretch = xStretch;
		this.yStretch = yStretch;
		this.zStretch = zStretch;
		this.wStretch = wStretch;
		this.xAxisRotation = xAxisRotation;
		this.yAxisRotation = yAxisRotation;
		this.zAxisRotation = zAxisRotation;
		this.wAxisRotation = wAxisRotation;
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.wPos = wPos;
	}
	public fpObject(String name, ArrayList<fpObject> refObj)
	{
		this.name = bFM.Utils.formatString(name);
		referenceObjects = refObj;
		this.index = refObj.size();
	}
	public fpObject(byte[] data)
	{
		//byteBuffer.order(ByteOrder.BIG_ENDIAN);
		int integer = 32;
		for(int i = 0; i<32; i++)
		{
			if(data[i]==0)
			{
				integer = i;
				break;
			}
		}
		byte[] nameArr = Arrays.copyOfRange(data, 0, integer);
		try{name = new String(nameArr, "ISO-8859-1");}catch(Exception error){System.out.println("Failed to find object name due to being an unsupported encoding");}
		index = ByteBuffer.wrap(data).getInt(64);
		objectType = ByteBuffer.wrap(data).getInt(68);
		
		if(0!=ByteBuffer.wrap(data).getFloat(84)) System.out.println(name + " 84 " + ByteBuffer.wrap(data).getFloat(84));
		if(0!=ByteBuffer.wrap(data).getFloat(92)) System.out.println(name + " 92 " + ByteBuffer.wrap(data).getFloat(92));
		if(0!=ByteBuffer.wrap(data).getFloat(108)) System.out.println(name + " 108 " + ByteBuffer.wrap(data).getFloat(108));
		if(0!=ByteBuffer.wrap(data).getFloat(112)) System.out.println(name + " 112 " + ByteBuffer.wrap(data).getFloat(112));
		if(0!=ByteBuffer.wrap(data).getFloat(124)) System.out.println(name + " 124 " + ByteBuffer.wrap(data).getFloat(124));
		if(0!=ByteBuffer.wrap(data).getFloat(132)) System.out.println(name + " 132 " + ByteBuffer.wrap(data).getFloat(132));
		//if(0!=ByteBuffer.wrap(data).getFloat(156)) System.out.println(name + " 156 " + ByteBuffer.wrap(data).getFloat(156));
		
		xStretch = ByteBuffer.wrap(data).getFloat(96);//Stretch Z
		yStretch = ByteBuffer.wrap(data).getFloat(116);//Stretch Y
		zStretch = ByteBuffer.wrap(data).getFloat(136);
		
		zAxisRotation = ByteBuffer.wrap(data).getFloat(100); 
		yAxisRotation = ByteBuffer.wrap(data).getFloat(104);//Rotate Y
		xAxisRotation = ByteBuffer.wrap(data).getFloat(120);// Rotate x

		xPos = ByteBuffer.wrap(data).getFloat(144);
		yPos = ByteBuffer.wrap(data).getFloat(148);
		zPos = ByteBuffer.wrap(data).getFloat(152);
	}
	public fpObject(ArrayList<String> lines, int index, ArrayList<fpObject> refObj,boolean blenderCoords,boolean randomizeRotation,boolean randomizeScale)
	{
		this.blenderCoords = blenderCoords;
		this.randomizeRotation = randomizeRotation;
		this.randomizeScale = randomizeScale;
		referenceObjects = refObj;
		wPos = 1;
		zStretch = 1;
		yStretch = 1;
		xStretch = 1;
		objectType = 0;
		this.index = index;
		if(index == 0) objectType = -1;
		
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<<Name>>")!=-1)
			{
				name = bFM.Utils.formatString(lines.get(i));
			}
			else if(lines.get(i).indexOf("<<Object>>")!=-1)
			{
				addObjectLine(lines.get(i));
			}
			else if(lines.get(i).indexOf("<<Position>>")!=-1)
			{
				addPositionLine(lines.get(i));
			}
			else if(lines.get(i).indexOf("<<Stretch>>")!=-1)
			{
				addStretchLine(lines.get(i));
			}
			else if(lines.get(i).indexOf("<<Rotation>>")!=-1)
			{
				addRotationLine(lines.get(i));
			}
			else if(lines.get(i).indexOf("<<Shear>>")!=-1)
			{
				addShearLine(lines.get(i));
			}
		}
	}

	public String getName()
	{
		return name;
	}
	public void addLine(String line)
	{
		if(line.indexOf("<<Object>>")!=-1)
		{
			addObjectLine(line);
		}
		else if(line.indexOf("<<Position>>")!=-1)
		{
			addPositionLine(line);
		}
		else if(line.indexOf("<<Scale>>")!=-1)
		{
			addStretchLine(line);
		}
		else if(line.indexOf("<<Rotation>>")!=-1)
		{
			addRotationLine(line);
		}
		else if(line.indexOf("<<Shear>>")!=-1)
		{
			addShearLine(line);
		}
	}
	private void addObjectLine(String line)
	{
		String objectName =  bFM.Utils.formatString(line);
		for(int i = 0; i < referenceObjects.size(); i++)
		{
			if(objectName.equals(referenceObjects.get(i).name))
			{
				objectType = i;
				break;
			}
		}
	}
	private void addPositionLine(String line)
	{
		float[] vals = getCoords(line);
		if(vals!=null)
		{
			wPos = vals[0];
			xPos = vals[1];
			yPos = vals[2];
			zPos = vals[3];
		}
	}
	private void addStretchLine(String line)
	{
		float[] vals = getCoords(line);
		if(vals!=null)
		{
			wStretch = vals[0];
			xStretch = vals[1];
			yStretch = vals[2];
			zStretch = vals[3];
		}
	}
	private void addRotationLine(String line)
	{
		float[] vals = getCoords(line);
		if(vals!=null)
		{
			wAxisRotation = vals[0];
			xAxisRotation = vals[1];
			yAxisRotation = vals[2];
			zAxisRotation = vals[3];
		}
		if(fpInterpreter.DEGREEMODE)
		{
			wAxisRotation = (float) (wAxisRotation*Math.PI/180);
			xAxisRotation = (float) (xAxisRotation*Math.PI/180);
			yAxisRotation = (float) (yAxisRotation*Math.PI/180);
			zAxisRotation = (float) (zAxisRotation*Math.PI/180);
		}
	}
	private void addShearLine(String line)
	{
		float[] vals = getCoords(line);
		if(vals!=null)
		{
			wUnknownTransformation = vals[0];
			xUnknownTransformation = vals[1];
			yUnknownTransformation = vals[2];
			zUnknownTransformation = vals[3];
		}
	}
	private float[] getCoords(String line)
	{
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
		if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
		{
			int startX = -1;
			int endX=-1;
			int startY = -1;
			int endY=-1;
			int startZ=-1;
			int endZ = line.indexOf(')');
			boolean inFloat = false;
			for(int j = line.indexOf('('); j<line.indexOf(')'); j++)
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
		else
		{
			bFM.Utils.DebugPrint("Failed to find Coordinates for .fp file on line: " + line);
		}
		return null;
	}
	public int getObjectType()
	{
		return objectType;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public byte[] getBytes()
	{
		byte[] ret = new byte[64];
		if(name==null)
		{
			name = "Unknown Name";
		}
		for(int i = 0;i<64&&i<name.length(); i++)
		{
			ret[i] = (byte) name.charAt(i);
		}
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(index).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(objectType).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[24]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zStretch).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zAxisRotation).array()); 
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yAxisRotation).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[8]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yStretch).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(xAxisRotation).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[12]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zStretch).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[4]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(xPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(wPos).array());
		return ret;
	}
 	public String toBFP()
	{
		String nameLine = "";
		String objectLine = "";
		String positionLine = "";
		String stretchLine = "";
		String rotationLine = "";
		String shearLine = "";
		if(name.length()>0)
		{
			nameLine = "<<Name>> \"" + name + "\"\n";
		}
		if(type.length()>0)
		{
			objectLine = "\t<<Object>> \"" + type + "\"\n";
		}
		if(xPos!=0.0||yPos!=0.0||zPos!=0.0||wPos!=0.0)
		{
			positionLine = "\t\t<<Position>> ";
			if(wPos!=0.0) positionLine += "{" + wPos +"} ";
			if(xPos!=0.0||yPos!=0.0||zPos!=0.0) positionLine += "(" + xPos + ", " + yPos + ", " + zPos + ")";
			positionLine+="\n";
		}
		if(xStretch!=1||yStretch!=1||zStretch!=1||(wStretch!=0&&wStretch!=1))
		{
			stretchLine = "\t\t<<Scale>> ";
			if(wStretch!=0&&wStretch!=1) stretchLine += "{" + wStretch +"} ";
			if(xStretch!=1||yStretch!=1||zStretch!=1) stretchLine += "(" + xStretch + ", " + yStretch + ", " + zStretch + ")";
			stretchLine += "\n";
		}
		if(xAxisRotation!=0||yAxisRotation!=0||zAxisRotation!=0||wAxisRotation!=0)
		{
			rotationLine = "\t\t<<Rotation>> ";
			if(wAxisRotation!=0) rotationLine += "{" + wAxisRotation +"} ";
			if(xAxisRotation!=0||yAxisRotation!=0||zAxisRotation!=0) 
			{
				if(fpInterpreter.DEGREEMODE) rotationLine += "(" + xAxisRotation/Math.PI*180 + ", " + yAxisRotation/Math.PI*180 + ", " + zAxisRotation/Math.PI*180 + ")";
				else rotationLine += "(" + xAxisRotation + ", " + yAxisRotation + ", " + zAxisRotation + ")";
			}
			rotationLine += "\n";
		}
		if(xUnknownTransformation!=0||yUnknownTransformation!=0||zUnknownTransformation!=0||wUnknownTransformation!=0)
		{
			shearLine = "\t\t<<Shear>> ";
			if(wUnknownTransformation!=0) shearLine += "{" + wUnknownTransformation +"} ";
			if(xUnknownTransformation!=0||yUnknownTransformation!=0||zUnknownTransformation!=0) shearLine += "(" + xUnknownTransformation + ", " + yUnknownTransformation + ", " + zUnknownTransformation + ")";
			shearLine += "\n";
		}
		return nameLine + objectLine + positionLine + stretchLine + rotationLine + shearLine;
	}
	public String toString()
	{
		if((xPos==yPos)&&(0==yPos)) return "";
		System.out.print("Object:" );
		System.out.println(name);
		String ret = "";
		if(wStretch!=1)
			System.out.println(wStretch*.01);
		//ret += "??? Values:" + xUnknownTransformation + "," + yUnknownTransformation + "," + zUnknownTransformation + "," + wUnknownTransformation + ";\n";
		//ret += "Stretch Values:" + xStretch + "," + yStretch + "," + zStretch + "," + wStretch + ";\n";
		//ret += "Rotation Values:\n" ;
		//ret +=xAxisRotation + "\n";
		
		//ret +=zAxisRotation + "\n";
		//ret +=wAxisRotation + ";\n";
		//ret += "PositionData:\n" ;
		ret += + (int)xPos*.01 + "\n"+ (int)zPos*-.01 + "\n"+ (int)(10*yPos)*.001 + "\n";
		//+ wPos + ";";
		ret +=yAxisRotation + "r";//+"\n";
		
		
		
		//ret = "v  " + xPos + " " + yPos + " " + zPos;
		return ret;
	}
	public float[] getPosData() 
	{
		float[] ret = {xPos, yPos, zPos, wPos};
		return ret;
	}
	public void setRandomScale(boolean b) 
	{
		randomizeScale = b;
	}
	public void setRandomRotation(boolean b) 
	{
		randomizeRotation = b;
	}
	public void setBlenderCoords(boolean b) 
	{
		blenderCoords = b;
	} 
}
