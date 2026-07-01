package ResourceManagers.ItemDatabaseManager;

import java.nio.ByteBuffer;

public class Placement 
{
	float x;
	float y;
	float z;
	int activationFlag = -1;
	int deactivationFlag = -1;
	int isIndoors = 0;
	int buildingCode = 0;
	protected Placement(byte[] placementBin1) 
	{
		// Create a Placement from the ItemPlace.bin file
		// Check to make sure it is the proper size
		if(placementBin1.length != 28)return;
		ByteBuffer placementBin = ByteBuffer.wrap(placementBin1);
		x = placementBin.getFloat(4);
		y = placementBin.getFloat(8);
		z = placementBin.getFloat(12);
		activationFlag = bFM.Utils.getShort(placementBin1, 16);
		deactivationFlag = bFM.Utils.getShort(placementBin1, 18);
		isIndoors = bFM.Utils.getShort(placementBin1, 0);
		buildingCode = placementBin.getInt(24);
	}
	protected Placement(String line) 
	{
		//set Coords for Placement
		float[] Coords = bFM.Utils.formatCoords(line);
		x = Coords[1];
		y = Coords[2];
		z = Coords[3];
	}
	public Placement() 
	{
		x = 0;
		y = 0;
		z = 0;
	}
	public String toString()
	{
		String ret = "\t<<Location>> " + x + ", " + y + ", " + z + "\n";
		if(activationFlag!=-1) ret += "\t\t<<Placement Activation Flag>> " + activationFlag + "\n";
		if(deactivationFlag!=-1) ret += "\t\t<<Placement Deactivation Flag>> " + deactivationFlag + "\n";
		if(isIndoors!=0) ret += "\t\t<<Is Indoors>\n";
		if(buildingCode!=0) ret += "\t\t<<Placement Building>> " + buildingCode + "\n";
		return ret;
	}
	protected void addPlacementLine(String line) 
	{
		// Set flags from the line
		if(line.indexOf("<<Placement Activation Flag>>")!=-1) activationFlag = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Placement Deactivation Flag>>")!=-1) deactivationFlag = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Is Indoors>>")!=-1) isIndoors = 1;
		else if(line.indexOf("<<Placement Building>>")!=-1) buildingCode = bFM.Utils.formatFlag(line);
	}
	protected byte[] toBytes(int itemCode)
	{
		//returns all of the placement data
		byte[] ret = new byte[0];
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(isIndoors, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(itemCode, 2));
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(x).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(y).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(z).array());
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(activationFlag, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(deactivationFlag, 2));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(0, 4));
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(buildingCode, 4));
		return ret;
	}
	public float getX()
	{
	    return x;
	}
	public void setX(float x)
	{
	    this.x = x;
	}
	public float getY()
	{
	    return y;
	}
	public void setY(float y)
	{
	    this.y = y;
	}
	public float getZ()
	{
	    return z;
	}
	public void setZ(float z)
	{
	    this.z = z;
	}
	public int getActivationFlag()
	{
	    return activationFlag;
	}
	public void setActivationFlag(int activationFlag)
	{
	    this.activationFlag = activationFlag;
	}
	public int getDeactivationFlag()
	{
	    return deactivationFlag;
	}
	public void setDeactivationFlag(int deactivationFlag)
	{
	    this.deactivationFlag = deactivationFlag;
	}
	public boolean getIsIndoors()
	{
	    return isIndoors != 0;
	}
	public void setIsIndoors(boolean isIndoors)
	{
		if(isIndoors) this.isIndoors = 1;
		else this.isIndoors = 0;
	}

	public int getBuildingCode()
	{
	    return buildingCode;
	}
	public void setBuildingCode(int buildingCode)
	{
	    this.buildingCode = buildingCode;
	}
}
