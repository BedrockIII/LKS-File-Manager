package WorldFileManager;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import bFM.Data;

public class FixedPointObject implements Data
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
	private static final DecimalFormat round = new DecimalFormat("0.00");
	ArrayList<FixedPointObject> referenceObjects = new ArrayList<FixedPointObject>();
	String type = "";
	public FixedPointObject(String name, int index, int objectType, 
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
	public FixedPointObject(String name, ArrayList<FixedPointObject> refObj)
	{
		this.name = bFM.Utils.formatString(name);
		referenceObjects = refObj;
		this.index = refObj.size();
	}
	public FixedPointObject(byte[] data)
	{
		ByteBuffer bytes = ByteBuffer.wrap(data);
		initializeFromBytes(bytes);
	}
	private void initializeFromBytes(ByteBuffer data)
	{
		//byteBuffer.order(ByteOrder.BIG_ENDIAN);
		byte chara = data.get();
		name = "";
		//System.out.println("aab");
		while(chara!=0)
		{
			name += (char)chara;
			//System.out.println((char)chara);
			chara = data.get();
		}
				index = data.getInt(64);
				objectType = data.getInt(68);
				
				if(0!=data.getFloat(84)) System.out.println(name + " 84 " + data.getFloat(84));
				if(0!=data.getFloat(92)) System.out.println(name + " 92 " + data.getFloat(92));
				if(0!=data.getFloat(108)) System.out.println(name + " 108 " + data.getFloat(108));
				if(0!=data.getFloat(112)) System.out.println(name + " 112 " + data.getFloat(112));
				if(0!=data.getFloat(124)) System.out.println(name + " 124 " + data.getFloat(124));
				if(0!=data.getFloat(132)) System.out.println(name + " 132 " + data.getFloat(132));
				//if(0!=data.getFloat(156)) System.out.println(name + " 156 " + data.getFloat(156));
				
				xStretch = data.getFloat(96);//Stretch Z
				yStretch = data.getFloat(116);//Stretch Y
				zStretch = data.getFloat(136);
				
				zAxisRotation = data.getFloat(100); 
				yAxisRotation = data.getFloat(104);//Rotate Y
				xAxisRotation = data.getFloat(120);// Rotate x

				xPos = data.getFloat(144);
				yPos = data.getFloat(148);
				zPos = data.getFloat(152);
	}
	public FixedPointObject(ArrayList<String> lines, int index, ArrayList<FixedPointObject> refObj,boolean blenderCoords,boolean randomizeRotation,boolean randomizeScale)
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
		return bFM.Utils.formatCoords(line, blenderCoords);
	}
	public int getObjectType()
	{
		return objectType;
	}
	public void setType(String type)
	{
		this.type = type;
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
			if(wPos!=0.0) positionLine += "{" + round.format(wPos) +"} ";
			if(xPos!=0.0||yPos!=0.0||zPos!=0.0) positionLine += "(" + round.format(xPos) + ", " + round.format(yPos) + ", " + round.format(zPos) + ")";
			positionLine+="\n";
		}
		if(xStretch!=1||yStretch!=1||zStretch!=1||(wStretch!=0&&wStretch!=1))
		{
			stretchLine = "\t\t<<Scale>> ";
			if(wStretch!=0&&wStretch!=1) stretchLine += "{" + round.format(wStretch) +"} ";
			if(xStretch!=1||yStretch!=1||zStretch!=1) stretchLine += "(" + round.format(xStretch) + ", " + round.format(yStretch) + ", " + round.format(zStretch) + ")";
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
	public int getReferenceIndex() 
	{
		return objectType;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public float getXPos() 
	{
		return xPos;
	}
	public float getYPos() 
	{
		return yPos;
	}
	public float getZPos() 
	{
		return zPos;
	}
	public float getXRot() 
	{
		return xAxisRotation;
	}
	public float getYRot() 
	{
		return yAxisRotation;
	}
	public float getZRot() 
	{
		return zAxisRotation;
	}
	public float getXScale() 
	{
		return xStretch;
	}
	public float getYScale() 
	{
		return yStretch;
	}
	public float getZScale() 
	{
		return zStretch;
	}
	public boolean equals(String name) 
	{
		return this.name.equals(name);
	}
	public void setData(byte[] data) 
	{
		ByteBuffer bytes = ByteBuffer.wrap(data);
		initializeFromBytes(bytes);
	}
	
	public byte[] toBytes() 
	{
		ByteBuffer ret = ByteBuffer.allocate(160);
		if(name==null)
		{
			name = "Unknown Name";
		}
		for(int i = 0; i < name.length() && i < 64; i++)
		{
			ret.put((byte) name.charAt(i));
		}
		ret.putInt(64, index);
		ret.putInt(68, objectType);
		
		ret.putFloat(96, xStretch);
		ret.putFloat(116, yStretch);
		ret.putFloat(136, zStretch);
		
		ret.putFloat(120, xAxisRotation);
		ret.putFloat(104, yAxisRotation);
		ret.putFloat(100, zAxisRotation);
		
		ret.putFloat(144, xPos);
		ret.putFloat(148, yPos);
		ret.putFloat(152, zPos);
		ret.putFloat(156, wPos);
		
		return ret.array();
	}
	
	public int getSize() 
	{
		return 160;
	}
	public void setXPos(float num) 
	{
		this.xPos = num;
	}
	public void setYPos(float num) 
	{
		this.yPos = num;
	}
	public void setZPos(float num) 
	{
		this.zPos = num;
	}
	public void setXRotation(float num) 
	{
		this.xAxisRotation = num;
	}
	public void setYRotation(float num) 
	{
		this.yAxisRotation = num;
	}
	public void setZRotation(float num) 
	{
		this.zAxisRotation = num;
	}
	public void setXScale(float num) 
	{
		this.xStretch = num;
	}
	public void setYScale(float num) 
	{
		this.yStretch = num;
	}
	public void setZScale(float num) 
	{
		this.zStretch = num;
	}
}
