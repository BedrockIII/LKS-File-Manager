package bFM;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class RotationMatrix
{
	float xScale, yScale, zScale;
	float xRotation, yRotation, zRotation;
	float xPos, yPos, zPos;
	public RotationMatrix()
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
		/*
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
		*/
		
		float xSquared = data.getFloat(offset + 0x00) * data.getFloat(offset + 0x00);
		xSquared += data.getFloat(offset + 0x04) * data.getFloat(offset + 0x04);
		xSquared += data.getFloat(offset + 0x08) * data.getFloat(offset + 0x08);
		xScale = (float) Math.sqrt(xSquared);
		float ySquared = data.getFloat(offset + 0x10) * data.getFloat(offset + 0x10);
		ySquared += data.getFloat(offset + 0x14) * data.getFloat(offset + 0x14);
		ySquared += data.getFloat(offset + 0x18) * data.getFloat(offset + 0x18);
		yScale = (float) Math.sqrt(ySquared);
		float zSquared = data.getFloat(offset + 0x20) * data.getFloat(offset + 0x20);
		zSquared += data.getFloat(offset + 0x24) * data.getFloat(offset + 0x24);
		zSquared += data.getFloat(offset + 0x28) * data.getFloat(offset + 0x28);
		zScale = (float) Math.sqrt(zSquared);
		
		xRotation = (float) Math.atan(
				-(data.getFloat(offset + 0x18)/yScale) /
				(data.getFloat(offset + 0x28)/zScale));
		yRotation = (float) Math.asin(data.getFloat(offset + 0x20)/zScale);
		zRotation = (float) Math.atan(
				-(data.getFloat(offset + 0x10) /yScale) /
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
	private float[][] applyScaling(float[][] a)
	{
		float[][] ret = new float[4][4];
		ret[0][0] = 1;
		ret[1][1] = 1;
		ret[2][2] = 1;
		ret[3][3] = 1;
		
		ret[0][0] = a[0][0]*xScale;
		ret[0][1] = a[0][1]*xScale;
		ret[0][2] = a[0][2]*xScale;
		
		ret[1][0] = a[1][0]*yScale;
		ret[1][1] = a[1][1]*yScale;
		ret[1][2] = a[1][2]*yScale;
		
		ret[2][0] = a[2][0]*zScale;
		ret[2][1] = a[2][1]*zScale;
		ret[2][2] = a[2][2]*zScale;
		
		return ret;
	}
	public void setEulerXScale(float xScale)
	{
		this.xScale = xScale;
		toArray();
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
		float[][] rotationMtx = new float[4][4];
		rotationMtx[0][0] = (float) (Math.cos(yRotation) * Math.cos(zRotation));
		rotationMtx[1][0] = (float) (-1 * Math.cos(yRotation) * Math.sin(zRotation));
		rotationMtx[2][0] = (float) Math.sin(yRotation);
		rotationMtx[0][1] = (float) (Math.cos(xRotation) * Math.sin(zRotation) + Math.cos(zRotation) * Math.sin(xRotation) * Math.sin(yRotation));
		rotationMtx[1][1] = (float) (Math.cos(xRotation) * Math.cos(zRotation) - Math.sin(xRotation) * Math.sin(yRotation) * Math.sin(zRotation));
		rotationMtx[2][1] = (float) (-Math.cos(yRotation) * Math.sin(xRotation));
		rotationMtx[0][2] = (float) (Math.sin(xRotation) * Math.sin(zRotation) - Math.cos(xRotation) * Math.cos(zRotation) * Math.sin(yRotation));
		rotationMtx[1][2] = (float) (Math.cos(zRotation) * Math.sin(xRotation) + Math.cos(xRotation) * Math.sin(yRotation) * Math.sin(zRotation));
		rotationMtx[2][2] = (float) (Math.cos(xRotation) * Math.cos(yRotation));
		
		//Rotate to fix my mistakes
		float[][] fixedMtx = new float[4][4];
		fixedMtx[0][0] = rotationMtx[0][0];
		fixedMtx[1][0] = rotationMtx[0][1];
		fixedMtx[2][0] = rotationMtx[0][2];
		fixedMtx[0][1] = rotationMtx[1][0];
		fixedMtx[1][1] = rotationMtx[1][1];
		fixedMtx[2][1] = rotationMtx[1][2];
		fixedMtx[0][2] = rotationMtx[2][0];
		fixedMtx[1][2] = rotationMtx[2][1];
		fixedMtx[2][2] = rotationMtx[2][2];
				
		Utils.DebugPrint(toString());
		float[][] mtx = applyScaling(fixedMtx);
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
		System.out.println(Arrays.toString(mtx[0]) + "\n" + 
		Arrays.toString(mtx[1]) + "\n" + 
		Arrays.toString(mtx[2]) + "\n" + 
		Arrays.toString(mtx[3]));
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