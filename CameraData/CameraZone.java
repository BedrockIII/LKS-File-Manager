package CameraData;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

public class CameraZone {

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
		num1 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(0);
		xPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(4);
		yPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(8);
		zPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(12);
		size = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(16);
		num6 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(20);
		num7 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(24);
		cameraFixedXPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(28);
		cameraFixedYPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(32);
		cameraFixedZPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(36);
		cameraFocusXPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(40);
		cameraFocusYPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(44);
		cameraFocusZPos = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(48);
		heightAngle = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(52);
		rotationCenterAngle = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(56);
		num16 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(60);
		cameraDistanceFromFocus = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(64);
		zoom = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(68);
		num19 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(72);
		num20 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(76);
		num21 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(80);
		num22 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(84);
		fadeOutDistance = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(88);
		num24 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(92);
		num25 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(96);
		num26 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(100);
		zoneEnableFlag = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(104);
		num28 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(108);
		num29 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(112);
		num30 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(116);
		zoneDisableFlag = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(120);
		num32 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(124);
		zoneIndex = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(128);
		num34 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(132);
		num35 = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(136);
		rotationRange = ByteBuffer.wrap(data).order(ByteOrder.BIG_ENDIAN).getInt(140);
	}
	public CameraZone(List<String> lines)
	{
		String numChars = "1234567890-.E ";
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<Name>")!=-1)
			{
				name = lines.get(i).substring(lines.get(i).indexOf("<Name>")+6);
				if(name.indexOf(' ')==0) name = name.substring(1);
			}
			else if(lines.get(i).indexOf("<Position>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					int startX = -1;
					int endX=-1;
					int startY = -1;
					int endY=-1;
					int startZ=-1;
					int endZ = lines.get(i).indexOf(')');
					boolean inFloat = false;
					for(int j = lines.get(i).indexOf('('); j<lines.get(i).indexOf(')'); j++)
					{
						if(inFloat&&numChars.indexOf(lines.get(i).charAt(j))==-1)
						{
							if(endX==-1) endX=j;
							else if(endY==-1) endY=j;
							else endZ=j;
							inFloat=false;
						}
						if(!inFloat&&numChars.indexOf(lines.get(i).charAt(j))!=-1)
						{
							if(startX==-1) startX=j; 
							else if(startY==-1) startY=j;
							else startZ=j;
							inFloat=true;
						}
					}
					xPos = (int)(Float.valueOf(lines.get(i).substring(startX, endX))*1000);
					yPos = (int)(Float.valueOf(lines.get(i).substring(startY, endY))*1000);
					if(endZ>startZ) zPos = (int)(Float.valueOf(lines.get(i).substring(startZ, endZ))*1000);
					else zPos = (int)(Float.valueOf(lines.get(i).substring(startZ, lines.get(i).length()-1))*1000);
				}
			}
			else if(lines.get(i).indexOf("<Camera Position>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					int startX = -1;
					int endX=-1;
					int startY = -1;
					int endY=-1;
					int startZ=-1;
					int endZ = lines.get(i).indexOf(')');
					boolean inFloat = false;
					for(int j = lines.get(i).indexOf('('); j<lines.get(i).indexOf(')'); j++)
					{
						if(inFloat&&numChars.indexOf(lines.get(i).charAt(j))==-1)
						{
							if(endX==-1) endX=j;
							else if(endY==-1) endY=j;
							else endZ=j;
							inFloat=false;
						}
						if(!inFloat&&numChars.indexOf(lines.get(i).charAt(j))!=-1)
						{
							if(startX==-1) startX=j; 
							else if(startY==-1) startY=j;
							else startZ=j;
							inFloat=true;
						}
					}
					cameraFixedXPos = (int)(Float.valueOf(lines.get(i).substring(startX, endX))*1000);
					if(cameraFixedXPos==-1000)cameraFixedXPos/=1000;
					cameraFixedYPos = (int)(Float.valueOf(lines.get(i).substring(startY, endY))*1000);
					if(cameraFixedYPos==-1000)cameraFixedYPos/=1000;
					if(endZ>startZ) cameraFixedZPos = (int)(Float.valueOf(lines.get(i).substring(startZ, endZ))*1000);
					else cameraFixedZPos = (int)(Float.valueOf(lines.get(i).substring(startZ, lines.get(i).length()-1))*1000);
					if(cameraFixedZPos==-1000)cameraFixedZPos/=1000;
				}
			}
			else if(lines.get(i).indexOf("<Focus Position>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					int startX = -1;
					int endX=-1;
					int startY = -1;
					int endY=-1;
					int startZ=-1;
					int endZ = lines.get(i).indexOf(')');
					boolean inFloat = false;
					for(int j = lines.get(i).indexOf('('); j<lines.get(i).indexOf(')'); j++)
					{
						if(inFloat&&numChars.indexOf(lines.get(i).charAt(j))==-1)
						{
							if(endX==-1) endX=j;
							else if(endY==-1) endY=j;
							else endZ=j;
							inFloat=false;
						}
						if(!inFloat&&numChars.indexOf(lines.get(i).charAt(j))!=-1)
						{
							if(startX==-1) startX=j; 
							else if(startY==-1) startY=j;
							else startZ=j;
							inFloat=true;
						}
					}
					cameraFocusXPos = (int)(Float.valueOf(lines.get(i).substring(startX, endX))*1000);
					if(cameraFocusXPos==-1000)cameraFocusXPos/=1000;
					cameraFocusYPos = (int)(Float.valueOf(lines.get(i).substring(startY, endY))*1000);
					if(cameraFocusYPos==-1000)cameraFocusYPos/=1000;
					if(endZ>startZ) cameraFocusZPos = (int)(Float.valueOf(lines.get(i).substring(startZ, endZ))*1000);
					else cameraFocusZPos = (int)(Float.valueOf(lines.get(i).substring(startZ, lines.get(i).length()-1))*1000);
					if(cameraFocusZPos==-1000)cameraFocusZPos/=1000;
				}
			}
			else if(lines.get(i).indexOf("<Box Size>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					size = (int)(Float.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')))*1000);
				}
			}
			else if(lines.get(i).indexOf("<Camera Angle>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					heightAngle = (int)(Float.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')))*1000);
				}
			}
			else if(lines.get(i).indexOf("<Rotation Center Angle>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					rotationCenterAngle = (int)(Float.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')))*1000);
				}
			}
			else if(lines.get(i).indexOf("<Rotation Range Angle>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					rotationRange = (int)(Float.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')))/10);
				}
			}
			else if(lines.get(i).indexOf("<Camera Distance>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					cameraDistanceFromFocus = (int)(Float.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')))*1000);
				}
			}
			else if(lines.get(i).indexOf("<Camera Zoom>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					zoom = (int)(Float.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')))*1000);
				}
			}
			else if(lines.get(i).indexOf("<num1>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num1 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num6>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num6 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num7>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num7 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num16>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num16 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num19>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num19 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num20>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num20 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num21>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num21 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num22>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num22 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<Fade Out Radius>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					fadeOutDistance = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num24>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num24 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num25>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num25 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num26>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num26 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<Enable Flag>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					zoneEnableFlag = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num28>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num28 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num29>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num29 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num30>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num30 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<Disable Flag>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					zoneDisableFlag = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<Zone Index>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					zoneIndex = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num34>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num34 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
			else if(lines.get(i).indexOf("<num35>")!=-1)
			{
				if(lines.get(i).indexOf('(')!=-1&&lines.get(i).indexOf(')')!=-1)
				{
					num35 = Integer.valueOf(lines.get(i).substring(lines.get(i).indexOf('(')+1, lines.get(i).indexOf(')')));
				}
			}
		}
	}
	public String toString()
	{
		if(!(753<xPos/1000&&xPos/1000<775))
		{
			if(!(683<zPos/1000&&zPos/1000<709))
			{
				//System.out.println(name);
				//return "";
			}
		}
		String ret = "<Name> " + name + "\n";
		if(xPos!=-1||yPos!=-1||zPos!=-1)
		{
			ret += "\t<Position> (";
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
			ret += "\t<Camera Position> (";
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
			ret += "\t<Focus Position> (";
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
			ret += "\t<Box Size> (";
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
			ret += "\t<Camera Angle> (";
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
			ret += "\t<Rotation Center Angle> (";
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
			ret += "\t<Rotation Range Angle> (";
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
			ret += "\t<Camera Distance> (";
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
			ret += "\t<Camera Zoom> (";
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
			ret += "\t<num1> (" + num1 + ")\n";
		}
		
		if(num6!=65535)
		{
			ret += "\t<num6> (" + num6 + ")\n";
		}
		
		if(num7!=-1)
		{
			ret += "\t<num7> (" + num7 + ")\n";
		}
		
		if(num16!=0)
		{
			ret += "\t<num16> (" + num16 + ")\n";
		}
		if(num19!=-1)
		{
			ret += "\t<num19> (" + num19 + ")\n";
		}
		if(num20!=-1)
		{
			ret += "\t<num20> (" + num20 + ")\n";
		}
		if(num21!=-1)
		{
			ret += "\t<num21> (" + num21 + ")\n";
		}
		if(num22!=-1)
		{
			ret += "\t<num22> (" + num22 + ")\n";
		}
		
		if(fadeOutDistance!=-1)
		{
			ret += "\t<Fade Out Radius> (" + fadeOutDistance + ")\n";
		}
		if(num24!=0)
		{
			ret += "\t<num24> (" + num24 + ")\n";
		}
		if(num25!=0)
		{
			ret += "\t<num25> (" + num25 + ")\n";
		}
		if(num26!=0)
		{
			ret += "\t<num26> (" + num26 + ")\n";
		}
		if(zoneEnableFlag!=0)
		{
			ret += "\t<Enable Flag> (" + zoneEnableFlag + ")\n";
		}
		if(num28!=0)
		{
			ret += "\t<num28> (" + num28 + ")\n";
		}
		if(num29!=0)
		{
			ret += "\t<num29> (" + num29 + ")\n";
		}
		if(num30!=0)
		{
			ret += "\t<num30> (" + num30 + ")\n";
		}
		if(zoneDisableFlag!=0)
		{
			ret += "\t<Disable Flag> (" + zoneDisableFlag + ")\n";
		}
		if(zoneIndex!=49)
		{
			ret += "\t<Zone Index> (" + zoneIndex + ")\n";
		}
		if(num34!=-1)
		{
			ret += "\t<num34> (" + num34 + ")\n";
		}
		if(num35!=-1)
		{
			ret += "\t<num35> (" + num35 + ")\n";
		}
		
		
		return ret;
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
}
