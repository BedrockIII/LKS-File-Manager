package itemManager;

import java.nio.ByteBuffer;

public class Placement 
{
	float x;
	float y;
	float z;
	int activationFlag;
	int deactivationFlag;
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
	}
	public String toString()
	{
		String ret = "\t<<Location>> " + x + ", " + y + ", " + z + "\n";
		if(activationFlag!=-1) ret += "\t\t<<Placement Activation Flag>> " + activationFlag + "\n";
		if(deactivationFlag!=-1) ret += "\t\t<<Placement Deactivation Flag>> " + deactivationFlag + "\n";
		return ret;
	}
}
