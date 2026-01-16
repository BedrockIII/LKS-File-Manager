package WorldFileManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class fpObject 
{
	public String name;
	int index;
	int objectType;
	float xUnknownTransformation;
	float yUnknownTransformation;
	float zUnknownTransformation;
	float wUnknownTransformation;
	float xStretch;
	float yStretch;
	float zStretch;
	float wStretch;
	float xAxisRotation;
	float yAxisRotation;
	float zAxisRotation;
	float wAxisRotation;
	float xPos;
	float yPos;
	float zPos;
	float wPos;
	String type = "";
	public fpObject(String name, int index, int objectType, float xUnknownTransformation, float yUnknownTransformation, float zUnknownTransformation, float wUnknownTransformation, float xStretch, float yStretch, float zStretch, float wStretch, float xAxisRotation, float yAxisRotation, float zAxisRotation, float wAxisRotation, float xPos, float yPos, float zPos, float wPos)
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
		zAxisRotation = ByteBuffer.wrap(data).getFloat(88); //Rotate Z
		//yUnknownTransformation = ByteBuffer.wrap(data).getFloat(88);
		//zUnknownTransformation = ByteBuffer.wrap(data).getFloat(92);
		zStretch = ByteBuffer.wrap(data).getFloat(96); //Stretch Z
		yAxisRotation = ByteBuffer.wrap(data).getFloat(104);//Rotate Y
		//yAxisRotation = ByteBuffer.wrap(data).getFloat(108);
		//zAxisRotation = ByteBuffer.wrap(data).getFloat(112);
		yStretch = ByteBuffer.wrap(data).getFloat(116);//Stretch Y
		xAxisRotation = ByteBuffer.wrap(data).getFloat(128); // Rotate x
		//yStretch = ByteBuffer.wrap(data).getFloat(128);
		//zStretch = ByteBuffer.wrap(data).getFloat(132);
		xStretch = ByteBuffer.wrap(data).getFloat(136);//Stretch X
		xPos = ByteBuffer.wrap(data).getFloat(144);
		yPos = ByteBuffer.wrap(data).getFloat(148);
		zPos = ByteBuffer.wrap(data).getFloat(152);
		wPos = ByteBuffer.wrap(data).getFloat(156);
	}
	public fpObject(ArrayList<String> lines, int index, ArrayList<fpObject> refObj,boolean blenderCoords,boolean randomizeRotation,boolean randomizeScale)
	{
		wPos = 1;
		zStretch = 1;
		yStretch = 1;
		xStretch = 1;
		objectType = 0;
		this.index = index;
		if(index == 0) objectType = -1;
		String numChars = "1234567890-.E ";
		for(int i = 0; i<lines.size(); i++)
		{
			if(lines.get(i).indexOf("<Name>")!=-1)
			{
				name = lines.get(i).substring(lines.get(i).indexOf("<Name>")+6);
				if(name.indexOf(' ')==0) name = name.substring(1);
			}
			else if(lines.get(i).indexOf("<Object> ")!=-1)
			{
				String refName = lines.get(i).substring(lines.get(i).indexOf("<Object> ")+9);
				for(int j = 0; j < refObj.size(); j++)
				{
					if(refName.equals(refObj.get(j).name))
					{
						objectType = j;
						break;
					}
				}
			}
			else if(lines.get(i).indexOf("<Position> ")!=-1)
			{
				if(lines.get(i).indexOf('{')!=-1&&lines.get(i).indexOf('}')!=-1)
				{
					wPos = Float.valueOf(lines.get(i).substring(lines.get(i).indexOf('{')+1, lines.get(i).indexOf('}')));
				}
				else
				{
					wPos = 1;
				}
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
					xPos = Float.valueOf(lines.get(i).substring(startX, endX));
					yPos = Float.valueOf(lines.get(i).substring(startY, endY));
					if(endZ>startZ) zPos = Float.valueOf(lines.get(i).substring(startZ, endZ));
					else zPos = Float.valueOf(lines.get(i).substring(startZ, lines.get(i).length()-1));
					if(blenderCoords)
					{
						xPos *= 100.0;
						yPos *= 100.0;
						zPos *= -100.0;
					}
				}
			}
			else if(lines.get(i).indexOf("<Stretch> ")!=-1)
			{
				if(lines.get(i).indexOf('{')!=-1&&lines.get(i).indexOf('}')!=-1)
				{
					wStretch = Float.valueOf(lines.get(i).substring(lines.get(i).indexOf('{')+1, lines.get(i).indexOf('}')));
				}
				else
				{
					wStretch = 0;
				}
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
					xStretch = Float.valueOf(lines.get(i).substring(startX, endX));
					yStretch = Float.valueOf(lines.get(i).substring(startY, endY));
					zStretch = Float.valueOf(lines.get(i).substring(startZ, endZ));
					if(randomizeScale)
					{
						float scale = (float)((Math.sqrt(Math.random())*.05)+.95);//get a random number with an upwards bias between 1-.95 for scale
						if(Math.random()>=.5)scale*=-1;
						if(xStretch==1&&yStretch==1&&zStretch==1)
						{
							xStretch = scale;
							zStretch = scale;
						}
					}
				}
			}
			else if(lines.get(i).indexOf("<Rotation> ")!=-1)
			{
				if(lines.get(i).indexOf('{')!=-1&&lines.get(i).indexOf('}')!=-1)
				{
					wAxisRotation = Float.valueOf(lines.get(i).substring(lines.get(i).indexOf('{')+1, lines.get(i).indexOf('}')));
				}
				else
				{
					wAxisRotation = 0;
				}
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
					xAxisRotation = Float.valueOf(lines.get(i).substring(startX, endX));
					yAxisRotation = Float.valueOf(lines.get(i).substring(startY, endY));
					zAxisRotation = Float.valueOf(lines.get(i).substring(startZ, endZ));
				}
				if(randomizeRotation)
				{
					float scale = (float)(1-Math.sqrt(Math.random()));//get a random number with a small bias
					if(xAxisRotation==0&&yAxisRotation==0&&zAxisRotation==0)
					{
						xAxisRotation = scale;
						yAxisRotation = -1*scale;
					}
				}
			}
			else if(lines.get(i).indexOf("<Shear> ")!=-1)
			{
				if(lines.get(i).indexOf('{')!=-1&&lines.get(i).indexOf('}')!=-1)
				{
					wUnknownTransformation = Float.valueOf(lines.get(i).substring(lines.get(i).indexOf('{')+1, lines.get(i).indexOf('}')));
				}
				else
				{
					wUnknownTransformation = 0;
				}
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
					xUnknownTransformation = Float.valueOf(lines.get(i).substring(startX, endX));
					yUnknownTransformation = Float.valueOf(lines.get(i).substring(startY, endY));
					zUnknownTransformation = Float.valueOf(lines.get(i).substring(startZ, endZ));
				}
			}
		}
	}
	public String getName()
	{
		return name;
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
		ret = bFM.Utils.mergeArrays(ret, new byte[16]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zAxisRotation).array()); 
		ret = bFM.Utils.mergeArrays(ret, new byte[4]);
		//ret = bedrockFileManagers.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yUnknownTransformation).array());
		//ret = bedrockFileManagers.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zUnknownTransformation).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zStretch).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[4]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yAxisRotation).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[8]);
		//ret = bedrockFileManagers.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yAxisRotation).array());
		//ret = bedrockFileManagers.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zAxisRotation).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yStretch).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[8]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(xAxisRotation).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[4]);
		//ret = bedrockFileManagers.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yStretch).array());
		//ret = bedrockFileManagers.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zStretch).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(xStretch).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[4]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(xPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zPos).array());
		//ret = bedrockFileManagers.Utils.mergeArrays(ret, new byte[4]);
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
			nameLine = "<Name> " + name + "\n";
		}
		if(type.length()>0)
		{
			objectLine = "\t<Object> " + type + "\n";
		}
		if(xPos!=0.0||yPos!=0.0||zPos!=0.0||wPos!=0.0)
		{
			positionLine = "\t\t<Position> ";
			if(wPos!=0.0) positionLine += "{" + wPos +"} ";
			if(xPos!=0.0||yPos!=0.0||zPos!=0.0) positionLine += "(" + xPos + ", " + yPos + ", " + zPos + ")";
			positionLine+="\n";
		}
		if(xStretch!=1||yStretch!=1||zStretch!=1||wStretch!=0)
		{
			stretchLine = "\t\t<Stretch> ";
			if(wStretch!=0) stretchLine += "{" + wStretch +"} ";
			if(xStretch!=1||yStretch!=1||zStretch!=1) stretchLine += "(" + xStretch + ", " + yStretch + ", " + zStretch + ")";
			stretchLine += "\n";
		}
		if(xAxisRotation!=0||yAxisRotation!=0||zAxisRotation!=0||wAxisRotation!=0)
		{
			rotationLine = "\t\t<Rotation> ";
			if(wAxisRotation!=0) rotationLine += "{" + wAxisRotation +"} ";
			if(xAxisRotation!=0||yAxisRotation!=0||zAxisRotation!=0) rotationLine += "(" + xAxisRotation + ", " + yAxisRotation + ", " + zAxisRotation + ")";
			rotationLine += "\n";
		}
		if(xUnknownTransformation!=0||yUnknownTransformation!=0||zUnknownTransformation!=0||wUnknownTransformation!=0)
		{
			shearLine = "\t\t<Shear> ";
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
}
