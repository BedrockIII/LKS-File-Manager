package CameraData;

import java.nio.ByteBuffer;
import bFM.Data;
import bFM.Utils;

public class CameraZone implements Data
{

	String name = "";
	int num1 = 65535;
	int xPos = -1;//integer x 1000
	int yPos = -1;
	int zPos = -1;
	int size = -1;
	int num6 = 65535;//ALWAYS 0 or 65535
	int num7 = -1;//ALWAYS -1 Except 2 cases where it is 30
	int cameraFixedXPos = -1;
	int cameraFixedYPos = -1;
	int cameraFixedZPos = -1;
	int cameraFocusXPos = -1;
	int cameraFocusYPos = -1;
	int cameraFocusZPos = -1;
	int heightAngle = -1;//angle between camera, corb, and ground
	int rotationCenterAngle = -1;
	int num16 = 0;						//0 for almost All
	int cameraDistanceFromFocus = -1;
	int zoom = -1;
	int num19 = -1;
	int num20 = -1;
	int num21 = -1;
	int num22 = -1;
	int fadeOutDistance = -1;
	int num24 = 0;//ALWAYS 0
	int num25 = 0;//ALWAYS 0
	int num26 = 0;//ALWAYS 0
	int zoneEnableFlag = 0;
	int num28 = 0;//ALWAYS 0
	int num29 = 0;//ALWAYS 0
	int num30 = 0;//ALWAYS 0
	int zoneDisableFlag = 0;
	int num32 = 0;//ALWAYS 0
	int zoneIndex = 49;
	int num34 = -1; //ALWAYS -1 but once
	int num35 = -1; //ALWAYS -1 but once
	int rotationRange = 0;//10th of the range of camera rotation
	
	public CameraZone(byte[] data, String name) 
	{
		this.name = name;
		initializeFromBytes(ByteBuffer.wrap(data));
	}
	private void initializeFromBytes(ByteBuffer data)
	{
		num1 = data.getInt(0);
		xPos = data.getInt(4);
		yPos = data.getInt(8);
		zPos = data.getInt(12);
		size = data.getInt(16);
		num6 = data.getInt(20);
		num7 = data.getInt(24);
		cameraFixedXPos = data.getInt(28);
		cameraFixedYPos = data.getInt(32);
		cameraFixedZPos = data.getInt(36);
		cameraFocusXPos = data.getInt(40);
		cameraFocusYPos = data.getInt(44);
		cameraFocusZPos = data.getInt(48);
		heightAngle = data.getInt(52);
		rotationCenterAngle = data.getInt(56);
		num16 = data.getInt(60);
		cameraDistanceFromFocus = data.getInt(64);
		zoom = data.getInt(68);
		num19 = data.getInt(72);
		num20 = data.getInt(76);
		num21 = data.getInt(80);
		num22 = data.getInt(84);
		fadeOutDistance = data.getInt(88);
		num24 = data.getInt(92);
		num25 = data.getInt(96);
		num26 = data.getInt(100);
		zoneEnableFlag = data.getInt(104);
		num28 = data.getInt(108);
		num29 = data.getInt(112);
		num30 = data.getInt(116);
		zoneDisableFlag = data.getInt(120);
		num32 = data.getInt(124);
		zoneIndex = data.getInt(128);
		num34 = data.getInt(132);
		num35 = data.getInt(136);
		rotationRange = data.getInt(140);
	}
	public CameraZone(String line)
	{
		if(line.indexOf("<<Name>>")!=-1)
		{
			name = Utils.formatString(line);
		}
	}
	public CameraZone() 
	{
		// Use Default Values
		name = "New Zone";
	}
	public void addLine(String line)
	{
		String numChars = "1234567890-.E ";
		if(line.indexOf("<<Position>>")!=-1)
		{
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
				xPos = (int)(Float.valueOf(line.substring(startX, endX))*1000);
				yPos = (int)(Float.valueOf(line.substring(startY, endY))*1000);
				if(endZ>startZ) zPos = (int)(Float.valueOf(line.substring(startZ, endZ))*1000);
				else zPos = (int)(Float.valueOf(line.substring(startZ, line.length()-1))*1000);
			}
		}
		else if(line.indexOf("<<Camera Position>>")!=-1)
		{
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
				cameraFixedXPos = (int)(Float.valueOf(line.substring(startX, endX))*1000);
				if(cameraFixedXPos==-1000)cameraFixedXPos/=1000;
				cameraFixedYPos = (int)(Float.valueOf(line.substring(startY, endY))*1000);
				if(cameraFixedYPos==-1000)cameraFixedYPos/=1000;
				if(endZ>startZ) cameraFixedZPos = (int)(Float.valueOf(line.substring(startZ, endZ))*1000);
				else cameraFixedZPos = (int)(Float.valueOf(line.substring(startZ, line.length()-1))*1000);
				if(cameraFixedZPos==-1000)cameraFixedZPos/=1000;
			}
		}
		else if(line.indexOf("<<Focus Position>>")!=-1)
		{
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
				cameraFocusXPos = (int)(Float.valueOf(line.substring(startX, endX))*1000);
				if(cameraFocusXPos==-1000)cameraFocusXPos/=1000;
				cameraFocusYPos = (int)(Float.valueOf(line.substring(startY, endY))*1000);
				if(cameraFocusYPos==-1000)cameraFocusYPos/=1000;
				if(endZ>startZ) cameraFocusZPos = (int)(Float.valueOf(line.substring(startZ, endZ))*1000);
				else cameraFocusZPos = (int)(Float.valueOf(line.substring(startZ, line.length()-1))*1000);
				if(cameraFocusZPos==-1000)cameraFocusZPos/=1000;
			}
		}
		else if(line.indexOf("<<Box Size>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				size = (int)(Float.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')))*1000);
			}
		}
		else if(line.indexOf("<<Camera Angle>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				heightAngle = (int)(Float.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')))*1000);
			}
		}
		else if(line.indexOf("<<Rotation Center Angle>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				rotationCenterAngle = (int)(Float.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')))*1000);
			}
		}
		else if(line.indexOf("<<Rotation Range Angle>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				rotationRange = (int)(Float.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')))/10);
			}
		}
		else if(line.indexOf("<<Camera Distance>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				cameraDistanceFromFocus = (int)(Float.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')))*1000);
			}
		}
		else if(line.indexOf("<<Camera Zoom>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				zoom = (int)(Float.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')))*1000);
			}
		}
		else if(line.indexOf("<<num1>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num1 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num6>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num6 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num7>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num7 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num16>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num16 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num19>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num19 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num20>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num20 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num21>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num21 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num22>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num22 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<Fade Out Radius>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				fadeOutDistance = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num24>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num24 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num25>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num25 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num26>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num26 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<Enable Flag>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				zoneEnableFlag = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num28>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num28 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num29>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num29 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num30>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num30 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<Disable Flag>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				zoneDisableFlag = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<Zone Index>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				zoneIndex = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num34>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num34 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
		else if(line.indexOf("<<num35>>")!=-1)
		{
			if(line.indexOf('(')!=-1&&line.indexOf(')')!=-1)
			{
				num35 = Integer.valueOf(line.substring(line.indexOf('(')+1, line.indexOf(')')));
			}
		}
	}
	public String toString()
	{
		String ret = "<<Name>> \"" + name + "\"\n";
		if(xPos!=-1||yPos!=-1||zPos!=-1)
		{
			ret += "\t<<Position>> (";
			if(xPos==-1)
			{
				ret += xPos + ", ";
			}
			else
			{
				ret += xPos/1000.0 + ", ";
			}
			if(yPos==-1)
			{
				ret += yPos + ", ";
			}
			else
			{
				ret += yPos/1000.0 + ", ";
			}
			if(zPos==-1)
			{
				ret += zPos;
			}
			else
			{
				ret += zPos/1000.0;
			}
			ret += ")\n";
		}
		
		if(cameraFixedXPos!=-1||cameraFixedYPos!=-1||cameraFixedZPos!=-1)
		{
			ret += "\t<<Camera Position>> (";
			if(cameraFixedXPos==-1)
			{
				ret += cameraFixedXPos + ", ";
			}
			else
			{
				ret += cameraFixedXPos/1000.0 + ", ";
			}
			if(cameraFixedYPos==-1)
			{
				ret += cameraFixedYPos + ", ";
			}
			else
			{
				ret += cameraFixedYPos/1000.0 + ", ";
			}
			if(cameraFixedZPos==-1)
			{
				ret += cameraFixedZPos;
			}
			else
			{
				ret += cameraFixedZPos/1000.0;
			}
			ret += ")\n";
		}
		
		if(cameraFocusXPos!=-1||cameraFocusYPos!=-1||cameraFocusZPos!=-1)
		{
			ret += "\t<<Focus Position>> (";
			if(cameraFocusXPos==-1)
			{
				ret += cameraFocusXPos + ", ";
			}
			else
			{
				ret += cameraFocusXPos/1000.0 + ", ";
			}
			if(cameraFocusYPos==-1)
			{
				ret += cameraFocusYPos + ", ";
			}
			else
			{
				ret += cameraFocusYPos/1000.0 + ", ";
			}
			if(cameraFocusZPos==-1)
			{
				ret += cameraFocusZPos;
			}
			else
			{
				ret += cameraFocusZPos/1000.0;
			}
			ret += ")\n";
		}
		
		if(size!=-1)
		{
			ret += "\t<<Box Size>> (";
			if(size==-1)
			{
				ret += size;
			}
			else
			{
				ret += size/1000.0;
			}
			ret += ")\n";
		}
		if(heightAngle!=-1)
		{
			ret += "\t<<Camera Angle>> (";
			if(heightAngle==-1)
			{
				ret += heightAngle;
			}
			else
			{
				ret += heightAngle/1000.0;
			}
			ret += ")\n";
		}
		if(rotationCenterAngle!=-1)
		{
			ret += "\t<<Rotation Center Angle>> (";
			if(rotationCenterAngle==-1)
			{
				ret += rotationCenterAngle;
			}
			else
			{
				ret += rotationCenterAngle/1000.0;
			}
			ret += ")\n";
		}
		
		if(rotationRange!=-1)
		{
			ret += "\t<<Rotation Range Angle>> (";
			if(rotationRange==-1)
			{
				ret += rotationRange;
			}
			else
			{
				ret += rotationRange*10;
			}
			ret += ")\n";
		}
		
		if(cameraDistanceFromFocus!=-1)
		{
			ret += "\t<<Camera Distance>> (";
			if(cameraDistanceFromFocus==-1)
			{
				ret += cameraDistanceFromFocus;
			}
			else
			{
				ret += cameraDistanceFromFocus/1000.0;
			}
			ret += ")\n";
		}
		
		if(zoom!=-1)
		{
			ret += "\t<<Camera Zoom>> (";
			if(zoom==-1)
			{
				ret += zoom;
			}
			else
			{
				ret += zoom/1000.0;
			}
			ret += ")\n";
		}
		
		if(num1!=65535)
		{
			ret += "\t<<num1>> (" + num1 + ")\n";
		}
		
		if(num6!=65535)
		{
			ret += "\t<<num6>> (" + num6 + ")\n";
		}
		
		if(num7!=-1)
		{
			ret += "\t<<num7>> (" + num7 + ")\n";
		}
		
		if(num16!=0)
		{
			ret += "\t<<num16>> (" + num16 + ")\n";
		}
		if(num19!=-1)
		{
			ret += "\t<<num19>> (" + num19 + ")\n";
		}
		if(num20!=-1)
		{
			ret += "\t<<num20>> (" + num20 + ")\n";
		}
		if(num21!=-1)
		{
			ret += "\t<<num21>> (" + num21 + ")\n";
		}
		if(num22!=-1)
		{
			ret += "\t<<num22>> (" + num22 + ")\n";
		}
		
		if(fadeOutDistance!=-1)
		{
			ret += "\t<<Fade Out Radius>> (" + fadeOutDistance + ")\n";
		}
		if(num24!=0)
		{
			ret += "\t<<num24>> (" + num24 + ")\n";
		}
		if(num25!=0)
		{
			ret += "\t<<num25>> (" + num25 + ")\n";
		}
		if(num26!=0)
		{
			ret += "\t<<num26>> (" + num26 + ")\n";
		}
		if(zoneEnableFlag!=0)
		{
			ret += "\t<<Enable Flag>> (" + zoneEnableFlag + ")\n";
		}
		if(num28!=0)
		{
			ret += "\t<<num28>> (" + num28 + ")\n";
		}
		if(num29!=0)
		{
			ret += "\t<<num29>> (" + num29 + ")\n";
		}
		if(num30!=0)
		{
			ret += "\t<<num30>> (" + num30 + ")\n";
		}
		if(zoneDisableFlag!=0)
		{
			ret += "\t<<Disable Flag>> (" + zoneDisableFlag + ")\n";
		}
		if(zoneIndex!=49)
		{
			ret += "\t<<Zone Index>> (" + zoneIndex + ")\n";
		}
		if(num34!=-1)
		{
			ret += "\t<<num34>> (" + num34 + ")\n";
		}
		if(num35!=-1)
		{
			ret += "\t<<num35>> (" + num35 + ")\n";
		}
		
		
		return ret;
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
	public byte[] getBytes()
	{
		byte[] ret = new byte[0];
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num1).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(xPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(yPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(zPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(size).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num6).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num7).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(cameraFixedXPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(cameraFixedYPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(cameraFixedZPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(cameraFocusXPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(cameraFocusYPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(cameraFocusZPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(heightAngle).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(rotationCenterAngle).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num16).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(cameraDistanceFromFocus).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(zoom).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num19).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num20).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num21).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num22).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(fadeOutDistance).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num24).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num25).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num26).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(zoneEnableFlag).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num28).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num29).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num30).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(zoneDisableFlag).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num32).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(zoneIndex).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num34).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(num35).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(rotationRange).array());
		return ret;
		
	}
	public byte[] getNameBytes()
	{
		byte[] ret = name.getBytes();
		return bFM.Utils.mergeArrays(ret, new byte[1]);
	}
	public boolean equals(String name)
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data)
	{
		initializeFromBytes(ByteBuffer.wrap(data));
	}
	public byte[] toBytes()
	{
		return getBytes();
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	public int getNum1()
	{
	    return num1;
	}
	public void setNum1(int num1)
	{
	    this.num1 = num1;
	}
	public float getXPos()
	{
		if(xPos == -1) return -1;
	    return (float) (xPos/1000.0);
	}
	public void setXPos(float xPos)
	{
	    this.xPos = (int)(xPos*1000);
	    if(xPos == -1) this.xPos = -1;
	}
	public float getYPos()
	{
		if(yPos == -1) return -1;
		return (float) (yPos/1000.0);
	}
	public void setYPos(float yPos)
	{
		 this.yPos = (int)(yPos*1000);
		 if(yPos == -1) this.yPos = -1;
	}
	public float getZPos()
	{
		if(zPos == -1) return -1;
	    return (float) (zPos/1000.0);
	}
	public void setZPos(float zPos)
	{
	    this.zPos = (int)(zPos*1000);
	    if(zPos == -1) this.zPos = -1;
	}
	public float getZoneSize()
	{
	    return (float) (size/1000.0);
	}
	public void setSize(float size)
	{
	    this.size = (int)(size*1000);
	}
	public int getNum6()
	{
	    return num6;
	}
	public void setNum6(int num6)
	{
	    this.num6 = num6;
	}
	public int getNum7()
	{
	    return num7;
	}
	public void setNum7(int num7)
	{
	    this.num7 = num7;
	}
	public float getCameraFixedXPos()
	{
		if(cameraFixedXPos == -1) return -1;
	    return (float) (cameraFixedXPos/1000.0);
	}
	public void setCameraFixedXPos(float cameraFixedXPos)
	{
	    this.cameraFixedXPos = (int)(cameraFixedXPos*1000);
	    if(cameraFixedXPos == -1) this.cameraFixedXPos = -1;
	}
	public float getCameraFixedYPos()
	{
		if(cameraFixedYPos == -1) return -1;
	    return (float) (cameraFixedYPos/1000.0);
	}
	public void setCameraFixedYPos(float cameraFixedYPos)
	{
	    this.cameraFixedYPos = (int)(cameraFixedYPos*1000);
	    if(cameraFixedYPos == -1) this.cameraFixedYPos = -1;
	}
	public float getCameraFixedZPos()
	{
		if(cameraFixedZPos == -1) return -1;
	    return (float) (cameraFixedZPos/1000.0);
	}
	public void setCameraFixedZPos(float cameraFixedZPos)
	{
	    this.cameraFixedZPos = (int)(cameraFixedZPos*1000);
	    if(cameraFixedZPos == -1) this.cameraFixedZPos = -1;
	}
	public float getCameraFocusXPos()
	{
		if(cameraFocusXPos == -1) return -1;
	    return (float) (cameraFocusXPos/1000.0);
	}
	public void setCameraFocusXPos(float cameraFocusXPos)
	{
	    this.cameraFocusXPos = (int)(cameraFocusXPos*1000);
	    if(cameraFocusXPos == -1) this.cameraFocusXPos = -1;
	}
	public float getCameraFocusYPos()
	{
		if(cameraFocusYPos == -1) return -1;
	    return (float) (cameraFocusYPos/1000.0);
	}
	public void setCameraFocusYPos(float cameraFocusYPos)
	{
	    this.cameraFocusYPos = (int)(cameraFocusYPos*1000);
	    if(cameraFocusYPos == -1) this.cameraFocusYPos = -1;
	}
	public float getCameraFocusZPos()
	{
		if(cameraFocusZPos == -1) return -1;
	    return (float) (cameraFocusZPos/1000.0);
	}
	public void setCameraFocusZPos(float cameraFocusZPos)
	{
	    this.cameraFocusZPos = (int)(cameraFocusZPos*1000);
	    if(cameraFocusZPos == -1) this.cameraFocusZPos = -1;
	}
	public float getHeightAngle()
	{
		if(heightAngle == -1) return -1;
	    return (float) (heightAngle/1000.0);
	}
	public void setHeightAngle(float heightAngle)
	{
	    this.heightAngle = (int)(heightAngle*1000);
	    if(heightAngle == -1) this.heightAngle = -1;
	}
	public float getRotationCenterAngle()
	{
		if(rotationCenterAngle == -1) return -1;
	    return (float) (rotationCenterAngle/1000.0);
	}
	public void setRotationCenterAngle(float rotationCenterAngle)
	{
	    this.rotationCenterAngle = (int)(rotationCenterAngle*1000);
	}
	public int getNum16()
	{
	    return num16;
	}
	public void setNum16(int num16)
	{
	    this.num16 = num16;
	}
	public float getCameraDistanceFromFocus()
	{
		if(cameraDistanceFromFocus == -1) return -1;
	    return (float) (cameraDistanceFromFocus/1000.0);
	}
	public void setCameraDistanceFromFocus(float cameraDistanceFromFocus)
	{
	    this.cameraDistanceFromFocus = (int)(cameraDistanceFromFocus*1000);
	    if(cameraDistanceFromFocus == -1) this.cameraDistanceFromFocus = -1;
	}
	public float getZoom()
	{
		if(zoom == -1) return -1;
	    return (float) (zoom/1000.0);
	}
	public void setZoom(float zoom)
	{
	    this.zoom = (int)(zoom*1000);
	    if(zoom == -1) this.zoom = -1;
	}
	public int getNum19()
	{
	    return num19;
	}
	public void setNum19(int num19)
	{
	    this.num19 = num19;
	}
	public int getNum20()
	{
	    return num20;
	}
	public void setNum20(int num20)
	{
	    this.num20 = num20;
	}
	public int getNum21()
	{
	    return num21;
	}
	public void setNum21(int num21)
	{
	    this.num21 = num21;
	}
	public int getNum22()
	{
	    return num22;
	}
	public void setNum22(int num22)
	{
	    this.num22 = num22;
	}
	public float getFadeOutDistance()
	{
		if(fadeOutDistance == -1) return -1;
	    return (float) (fadeOutDistance/1000.0);
	}
	public void setFadeOutDistance(float fadeOutDistance)
	{
	    this.fadeOutDistance = (int)(fadeOutDistance*1000);
	    if(fadeOutDistance == -1) this.fadeOutDistance = -1;
	}
	public int getNum24()
	{
	    return num24;
	}
	public void setNum24(int num24)
	{
	    this.num24 = num24;
	}
	public int getNum25()
	{
	    return num25;
	}
	public void setNum25(int num25)
	{
	    this.num25 = num25;
	}
	public int getNum26()
	{
	    return num26;
	}
	public void setNum26(int num26)
	{
	    this.num26 = num26;
	}
	public int getZoneEnableFlag()
	{
	    return zoneEnableFlag;
	}
	public void setZoneEnableFlag(int zoneEnableFlag)
	{
	    this.zoneEnableFlag = zoneEnableFlag;
	}
	public int getNum28()
	{
	    return num28;
	}
	public void setNum28(int num28)
	{
	    this.num28 = num28;
	}
	public int getNum29()
	{
	    return num29;
	}
	public void setNum29(int num29)
	{
	    this.num29 = num29;
	}
	public int getNum30()
	{
	    return num30;
	}
	public void setNum30(int num30)
	{
	    this.num30 = num30;
	}
	public int getZoneDisableFlag()
	{
	    return zoneDisableFlag;
	}
	public void setZoneDisableFlag(int zoneDisableFlag)
	{
	    this.zoneDisableFlag = zoneDisableFlag;
	}
	public int getNum32()
	{
	    return num32;
	}
	public void setNum32(int num32)
	{
	    this.num32 = num32;
	}
	public int getZoneIndex()
	{
	    return zoneIndex;
	}
	public void setZoneIndex(int zoneIndex)
	{
	    this.zoneIndex = zoneIndex;
	}
	public int getNum34()
	{
	    return num34;
	}
	public void setNum34(int num34)
	{
	    this.num34 = num34;
	}
	public int getNum35()
	{
	    return num35;
	}
	public void setNum35(int num35)
	{
	    this.num35 = num35;
	}
	public int getRotationRange()
	{
	    return rotationRange;
	}
	public void setRotationRange(int rotationRange)
	{
	    this.rotationRange = rotationRange;
	}
}
