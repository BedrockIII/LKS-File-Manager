package CommonFiles.FixedPlacement;

import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.ArrayList;

import bFM.Data;
import bFM.RotationMatrix;
import bFM.Utils;

public class FixedPointObject implements Data
{
	public String name;
	RotationMatrix rot;
	boolean blenderCoords, randomizeRotation, randomizeScale = false;
	private static final DecimalFormat round = new DecimalFormat("0.00");
	FixedPointObject parent;
	FixedPointManager manager;
	ArrayList<FixedPointObject> children = new ArrayList<FixedPointObject>();
	public FixedPointObject(String name, FixedPointManager manager)
	{
		this.name = bFM.Utils.formatString(name);
		this.manager = manager;
		rot = new RotationMatrix();
	}
	public FixedPointObject(byte[] data, FixedPointManager manager)
	{
		this.manager = manager;
		ByteBuffer bytes = ByteBuffer.wrap(data);
		initializeFromBytes(bytes);
	}
	private void initializeFromBytes(ByteBuffer data)
	{
		//byteBuffer.order(ByteOrder.BIG_ENDIAN);
		byte chara = data.get();
		name = "";
		while(chara!=0)
		{
			name += (char)chara;
			chara = data.get();		
		}
		manager.registerToParentFromIndex(this, data.getInt(68));
				
		rot = new RotationMatrix();
		data.position(0x60);
		rot.initializeFromBytes(data);
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
		parent = manager.getObject(Utils.formatString(line));
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
		if(FixedPointManager.DEGREEMODE)
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
		if(parent != null)
		{
			objectLine = "\t<<Object>> \"" + parent.name + "\"\n";
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
		ret.putInt(64, manager.getObjects().indexOf(this));
		if(parent == null || parent == this)
		{
			ret.putInt(68, -1);
		}
		else
		{
			ret.putInt(68, manager.getObjects().indexOf(parent));
		}
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
	public void registerChild(FixedPointObject child)
	{
		if(this==child) throw new IllegalArgumentException("A Child Cannot Be It's Own Parent!!!");
		child.parent = this;
		children.add(child);
	}
	public ArrayList<FixedPointObject> getChildren()
	{
		return children;
	}
	public boolean isParentNode()
	{
		return parent == null || parent == this;
	}
	public FixedPointObject addChild()
	{
		FixedPointObject child = new FixedPointObject("New Fixed Placement", manager);
		manager.objects.add(child);
		registerChild(child);
		return child;
	}
	public FixedPointObject getParent()
	{
		return parent;
	}
}
