package BrresManager.ModelManager;

import BrresManager.Pointer;
import bFM.Data;

public class ModelObject implements Data
{
	int size;
    Pointer mdl0Start;
    int matrixIndex; // -1 if null
    byte[] cache;     // Cache of the first 32 bytes of PrePrimDL

    ResTagDLData  tagPrePrimDL;        // Offset to PrePrimDL, and so on
    ResTagDLData  tagPrimDL;           // Offset to PrimDL, and so on

    // Record the presence or absence of vcd as a bit pattern.
    // The layout uses 0 for the upper 11 bits, followed by GX_VA_PNMTXIDX through GX_VA_TEX7, in order, from GXAttr.
    // 
    int           flags1;

    int           flag;
    Pointer     name;                // Offset to the shape name character string from the start of the structure
    int           id;                  // Shape ID

    int vertexAmount;// Number of vertices
    int faceAmount;// Number of polygons

    short              idVtxPosition;       // Contains the id of the ResVtxPosData to use
    short idVtxNormal;         // Contains the id of the ResVtxNrmData to used (-1 if it doesn't exist)
    short[] idVtxColor;       // Contains the id of the ResVtxClrData to use (-1 if it doesn't exist) //2
    short[] idVtxTexCoord;    // Contains the id of the ResVtxTexCoordData to use (-1 if it doesn't exist) //8
    short idVtxFurVec;         // Contains the id of the ResVtxFurVecData to use (-1 if it doesn't exist)
    short idVtxFurPos;         // Contains the id of the ResVtxFurPosData to use (-1 if it doesn't exist)
    Pointer toMtxSetUsed;        // Offset from the start of the structure to ResMtxSetUsed
	public boolean equals(String name) 
	{
		throw new UnsupportedOperationException("equals() should not be called on type " + this.getClass());
	}
	public void setData(byte[] data) 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public byte[] toBytes() 
	{
		throw new UnsupportedOperationException("setData(byte[] data) should not be called on type " + this.getClass());
	}
	public void setName(String name) 
	{
		throw new UnsupportedOperationException("setName(String name) should not be called on type " + this.getClass());
	}
	public String getName() 
	{
		throw new UnsupportedOperationException("getName() should not be called on type " + this.getClass());
	}
	public int getSize() 
	{
		throw new UnsupportedOperationException("getSize() should not be called on type " + this.getClass());
	}
}
