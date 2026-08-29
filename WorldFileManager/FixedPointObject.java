package WorldFileManager;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import bFM.Data;
import bFM.Utils;

public class FixedPointObject implements Data
{
	private class RotationMatrix
	{
		float xScale, yScale, zScale;
		float xRotation, yRotation, zRotation;
		float xPos, yPos, zPos;
		private RotationMatrix()
		{
			xScale = 1;
			yScale = 1;
			zScale = 1;
			xRotation = 0;
			yRotation = 0;
			zRotation = 0;
			xPos = 0;
			yPos = 0;
			zPos = 0;
		}
		public void initializeFromBytes(ByteBuffer data) 
		{
			int offset = data.position();
			float xSquared = data.getFloat(offset + 0x00) * data.getFloat(offset + 0x00);
			xSquared += data.getFloat(offset + 0x10) * data.getFloat(offset + 0x10);
			xSquared += data.getFloat(offset + 0x20) * data.getFloat(offset + 0x20);
			xScale = (float) Math.sqrt(xSquared);
			float ySquared = data.getFloat(offset + 0x04) * data.getFloat(offset + 0x04);
			ySquared += data.getFloat(offset + 0x14) * data.getFloat(offset + 0x14);
			ySquared += data.getFloat(offset + 0x24) * data.getFloat(offset + 0x24);
			yScale = (float) Math.sqrt(ySquared);
			float zSquared = data.getFloat(offset + 0x08) * data.getFloat(offset + 0x08);
			zSquared += data.getFloat(offset + 0x18) * data.getFloat(offset + 0x18);
			zSquared += data.getFloat(offset + 0x28) * data.getFloat(offset + 0x28);
			zScale = (float) Math.sqrt(zSquared);
			
			xRotation = (float) Math.atan(
					-(data.getFloat(offset + 0x24)/yScale) /
					(data.getFloat(offset + 0x28)/zScale));
			yRotation = (float) Math.asin(data.getFloat(offset + 0x08)/zScale);
			zRotation = (float) Math.atan(
					-(data.getFloat(offset + 0x04) /yScale) /
					(data.getFloat(offset)/yScale));
			
			
			xPos = data.getFloat(offset + 0x30);
			yPos = data.getFloat(offset + 0x34);
			zPos = data.getFloat(offset + 0x38);
		}
		public float getEulerXRotationDegrees()
		{
			if(xRotation==Float.NaN) return 0;
			return (float) (xRotation*180/Math.PI);
		}
		public float getEulerYRotationDegrees()
		{
			if(yRotation==Float.NaN) return 0;
			return (float) (yRotation*180/Math.PI);
		}
		public float getEulerZRotationDegrees()
		{
			if(zRotation==Float.NaN) return 0;
			return (float) (zRotation*180/Math.PI);
		}
		public float getEulerXScale()
		{
			if(xScale==Float.NaN) return 0;
			return xScale;
		}
		public float getEulerYScale()
		{
			if(yScale==Float.NaN) return 0;
			return yScale;
		}
		public float getEulerZScale()
		{
			if(zScale==Float.NaN) return 0;
			return zScale;
		}
		public void setEulerXRotationDegrees(float xRotation)
		{
			this.xRotation = (float) (xRotation/180*Math.PI);
		}
		public void setEulerXRotationRadian(float xRotation)
		{
			this.xRotation = xRotation;
		}
		public void setEulerYRotationRadian(float yRotation)
		{
			this.yRotation = yRotation;
		}
		public void setEulerZRotationRadian(float zRotation)
		{
			this.zRotation = zRotation;
		}
		public void setEulerYRotationDegrees(float yRotation)
		{
			this.yRotation = (float) (yRotation/180*Math.PI);
		}
		public void setEulerZRotationDegrees(float zRotation)
		{
			this.zRotation = (float) (zRotation/180*Math.PI);
		}
		private float[][] setMatrixFromMatricies(float[][] a, float[][] b)
		{
			float[][] ret = new float[4][4];
			ret[0][0] = 1;
			ret[1][1] = 1;
			ret[2][2] = 1;
			ret[3][3] = 1;
			
			ret[0][0] = a[0][0]*b[0][0] + a[0][1]*b[1][0] + a[0][2]*b[2][0];
			ret[0][1] = a[0][0]*b[0][1] + a[0][1]*b[1][1] + a[0][2]*b[2][1];
			ret[0][2] = a[0][0]*b[0][2] + a[0][1]*b[1][2] + a[0][2]*b[2][2];
			
			ret[1][0] = a[1][0]*b[0][0] + a[1][1]*b[1][0] + a[1][2]*b[2][0];
			ret[1][1] = a[1][0]*b[0][1] + a[1][1]*b[1][1] + a[1][2]*b[2][1];
			ret[1][2] = a[1][0]*b[0][2] + a[1][1]*b[1][2] + a[1][2]*b[2][2];
			
			ret[2][0] = a[2][0]*b[0][0] + a[2][1]*b[1][0] + a[2][2]*b[2][0];
			ret[2][1] = a[2][0]*b[0][1] + a[2][1]*b[1][1] + a[2][2]*b[2][1];
			ret[2][2] = a[2][0]*b[0][2] + a[2][1]*b[1][2] + a[2][2]*b[2][2];
			
			return ret;
		}
		public void setEulerXScale(float xScale)
		{
			this.xScale = xScale;
		}
		public void setEulerYScale(float yScale)
		{
			this.yScale = yScale;
		}
		public void setEulerZScale(float zScale)
		{
			this.zScale = zScale;
		}
		public byte[] toArray()
		{
			float[][] scalingMtx = new float[4][4];
			scalingMtx[0][0] = xScale;
			scalingMtx[1][1] = yScale;
			scalingMtx[2][2] = zScale;
			float[][] rotationMtx = new float[4][4];
			rotationMtx[0][0] = (float) (Math.cos(yRotation) * Math.cos(zRotation));
			rotationMtx[0][1] = (float) (-1 * Math.cos(yRotation) * Math.sin(zRotation));
			rotationMtx[0][2] = (float) Math.sin(yRotation);
			rotationMtx[1][0] = (float) (Math.cos(xRotation) * Math.sin(zRotation) + Math.cos(zRotation) * Math.sin(xRotation) * Math.sin(yRotation));
			rotationMtx[1][1] = (float) (Math.cos(xRotation) * Math.cos(zRotation) - Math.sin(xRotation) * Math.sin(yRotation) * Math.sin(zRotation));
			rotationMtx[1][2] = (float) (-Math.cos(yRotation) * Math.sin(xRotation));
			rotationMtx[2][0] = (float) (Math.sin(xRotation) * Math.sin(zRotation) - Math.cos(xRotation) * Math.cos(zRotation) * Math.sin(yRotation));
			rotationMtx[2][1] = (float) (Math.cos(zRotation) * Math.sin(xRotation) + Math.cos(xRotation) * Math.sin(yRotation) * Math.sin(zRotation));
			rotationMtx[2][2] = (float) (Math.cos(xRotation) * Math.cos(yRotation));
			
			Utils.DebugPrint(toString());
			float[][] mtx = setMatrixFromMatricies(rotationMtx, scalingMtx);
			Utils.DebugPrint("Pre Scaling:\n" + toString(rotationMtx));
			mtx[3][0] = xPos;
			mtx[3][1] = yPos;
			mtx[3][2] = zPos;
			Utils.DebugPrint("Post Scaling:\n" + toString(mtx));
			
			
			ByteBuffer ret = ByteBuffer.allocate(0x40);
			ret.putFloat(0x0, mtx[0][0]);
			ret.putFloat(0x4, mtx[0][1]);
			ret.putFloat(0x8, mtx[0][2]);
			ret.putFloat(0xc, mtx[0][3]);
			ret.putFloat(0x10, mtx[1][0]);
			ret.putFloat(0x14, mtx[1][1]);
			ret.putFloat(0x18, mtx[1][2]);
			ret.putFloat(0x1c, mtx[1][3]);
			ret.putFloat(0x20, mtx[2][0]);
			ret.putFloat(0x24, mtx[2][1]);
			ret.putFloat(0x28, mtx[2][2]);
			ret.putFloat(0x2c, mtx[2][3]);
			ret.putFloat(0x30, mtx[3][0]);
			ret.putFloat(0x34, mtx[3][1]);
			ret.putFloat(0x38, mtx[3][2]);
			ret.putFloat(0x3c, mtx[3][3]);
			return ret.array();
		}
		public String toString()
		{
			return String.format(
					"----------\n"
					+ "Scale: [%f, %f, %f]\n"
					+ "Rotation: [%f, %f, %f]\n"
					+ "Position: [%f, %f, %f]",
					xScale, yScale, zScale,
					xRotation, yRotation, zRotation, 
					xPos, yPos, zPos);
					
		}
		public String toString(float[][] mtx)
		{
			return String.format(
					"[%f, %f, %f, %f]\n"
					+ "[%f, %f, %f, %f]\n"
					+ "[%f, %f, %f, %f]\n"
					+ "[%f, %f, %f, %f]", 
					mtx[0][0], mtx[0][1], mtx[0][2], mtx[0][3],
					mtx[1][0], mtx[1][1], mtx[1][2], mtx[1][3],
					mtx[2][0], mtx[2][1], mtx[2][2], mtx[2][3],
					mtx[3][0], mtx[3][1], mtx[3][2], mtx[3][3]);
					
		}
		public void setYPos(float yPos) 
		{
			this.yPos = yPos;
			toArray();//ForDebugPrint
		}
	}
	public String name;
	int index;
	int objectType = -1;
	RotationMatrix rot;
	boolean blenderCoords, randomizeRotation, randomizeScale = false;
	private static final DecimalFormat round = new DecimalFormat("0.00");
	ArrayList<FixedPointObject> referenceObjects = new ArrayList<FixedPointObject>();
	String type = "";
	public FixedPointObject(String name, ArrayList<FixedPointObject> refObj)
	{
		this.name = bFM.Utils.formatString(name);
		referenceObjects = refObj;
		rot = new RotationMatrix();
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
				
				rot = new RotationMatrix();
				data.position(0x60);
				rot.initializeFromBytes(data);
	}
	public FixedPointObject(ArrayList<String> lines, int index, ArrayList<FixedPointObject> refObj,boolean blenderCoords,boolean randomizeRotation,boolean randomizeScale)
	{
		this.blenderCoords = blenderCoords;
		this.randomizeRotation = randomizeRotation;
		this.randomizeScale = randomizeScale;
		referenceObjects = refObj;
		objectType = 0;
		this.index = index;
		if(index == 0) objectType = -1;
		rot = new RotationMatrix();
		
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
			rot.xPos = vals[1];
			rot.yPos = vals[2];
			rot.zPos = vals[3];
		}
	}
	private void addStretchLine(String line)
	{
		float[] vals = getCoords(line);
		if(vals!=null)
		{
			rot.setEulerXScale(vals[1]);
			rot.setEulerYScale(vals[2]);
			rot.setEulerZScale(vals[3]);
		}
	}
	private void addRotationLine(String line)
	{
		float[] vals = getCoords(line);
		if(fpInterpreter.DEGREEMODE)
		{
			rot.setEulerXRotationDegrees((float) (vals[1]));
			rot.setEulerYRotationDegrees((float) (vals[2]));
			rot.setEulerZRotationDegrees((float) (vals[3]));
		}
		else
		{
			rot.setEulerXRotationRadian(vals[1]);
			rot.setEulerYRotationRadian(vals[2]);
			rot.setEulerZRotationRadian(vals[3]);
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
		if(name.length()>0)
		{
			nameLine = "<<Name>> \"" + name + "\"\n";
		}
		if(type.length()>0)
		{
			objectLine = "\t<<Object>> \"" + type + "\"\n";
		}
		if(rot.xPos!=0.0||rot.yPos!=0.0||rot.zPos!=0.0)
		{
			positionLine = "\t\t<<Position>> ";
			positionLine += String.format("(%f,%f,%f)\n", round.format(rot.xPos), round.format(rot.zPos), round.format(rot.zPos));
		}
		if(rot.getEulerXScale()!=1||rot.getEulerYScale()!=1||rot.getEulerZScale()!=1)
		{
			stretchLine = "\t\t<<Scale>> ";
			positionLine += String.format("(%f,%f,%f)\n", round.format(rot.getEulerXScale()), round.format(rot.getEulerYScale()), round.format(rot.getEulerZScale()));
		}
		if(rot.getEulerXRotationDegrees()!=0||rot.getEulerYRotationDegrees()!=0||rot.getEulerZRotationDegrees()!=0)
		{
			rotationLine += String.format("(%f,%f,%f)\n", rot.getEulerXRotationDegrees(), rot.getEulerYRotationDegrees(), rot.getEulerZRotationDegrees());
		}
		return nameLine + objectLine + positionLine + stretchLine + rotationLine;
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
		return rot.xPos;
	}
	public float getYPos() 
	{
		return rot.yPos;
	}
	public float getZPos() 
	{
		return rot.zPos;
	}
	public float getXRot() 
	{
		return rot.getEulerXRotationDegrees();
	}
	public float getYRot() 
	{
		return rot.getEulerYRotationDegrees();
	}
	public float getZRot() 
	{
		return rot.getEulerZRotationDegrees();
	}
	public float getXScale() 
	{
		return rot.getEulerXScale();
	}
	public float getYScale() 
	{
		return rot.getEulerYScale();
	}
	public float getZScale() 
	{
		return rot.getEulerZScale();
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
		ByteBuffer ret = ByteBuffer.allocate(0x60);
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
		
		return Utils.mergeArrays(ret.array(), rot.toArray());
	}
	
	public int getSize() 
	{
		return 160;
	}
	public void setXPos(float xPos) 
	{
		rot.xPos = xPos;
	}
	public void setYPos(float yPos) 
	{
		rot.setYPos(yPos);
	}
	public void setZPos(float zPos) 
	{
		rot.zPos = zPos;
	}
	public void setXRotation(float xRotation) 
	{
		rot.setEulerXRotationDegrees(xRotation);
	}
	public void setYRotation(float yRotation) 
	{
		rot.setEulerYRotationDegrees(yRotation);
	}
	public void setZRotation(float zRotation) 
	{
		rot.setEulerZRotationDegrees(zRotation);
	}
	public void setXScale(float xScl) 
	{
		rot.setEulerXScale(xScl);
	}
	public void setYScale(float yScl) 
	{
		rot.setEulerYScale(yScl);
	}
	public void setZScale(float zScl) 
	{
		rot.setEulerZScale(zScl);
	}
	public int getIndex() 
	{
		return index;
	}
}
