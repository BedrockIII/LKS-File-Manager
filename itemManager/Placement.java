package itemManager;

import java.nio.ByteBuffer;

public class Placement 
{
	float x;
	float y;
	float z;
	int activationFlag = -1;
	int deactivationFlag = -1;
	int number = 0;
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
		number = bFM.Utils.getShort(placementBin1, 0);
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
	public String toString()
	{
		String ret = "\t<<Location>> " + x + ", " + y + ", " + z + "\n";
		if(activationFlag!=-1) ret += "\t\t<<Placement Activation Flag>> " + activationFlag + "\n";
		if(deactivationFlag!=-1) ret += "\t\t<<Placement Deactivation Flag>> " + deactivationFlag + "\n";
		if(number!=0) ret += "\t\t<<Unknown Placement Number>> " + number + "\n";
		if(buildingCode!=0) ret += "\t\t<<Placement Building>> " + buildingCode + "\n";
		return ret;
	}
	protected void addPlacementLine(String line) 
	{
		// Set flags from the line
		if(line.indexOf("<<Placement Activation Flag>>")!=-1) activationFlag = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Placement Deactivation Flag>>")!=-1) deactivationFlag = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Unknown Placement Number>>")!=-1) number = bFM.Utils.formatFlag(line);
		else if(line.indexOf("<<Placement Building>>")!=-1) buildingCode = bFM.Utils.formatFlag(line);
	}
	protected byte[] toBytes(int itemCode)
	{
		//returns all of the placement data
		byte[] ret = new byte[0];
		ret = bFM.Utils.mergeArrays(ret, bFM.Utils.toByteArr(number, 2));
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
}
