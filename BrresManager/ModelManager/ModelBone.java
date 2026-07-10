package BrresManager.ModelManager;

import BrresManager.Pointer;
import bFM.Data;

public class ModelBone implements Data
{
	//Byte Vars
	int size;
    Pointer mdl0Pos;

    Pointer namePos;
    int index;
    int matrixType;//Always 34, 0x22
    int flags;
    int bbmode;
    int referenceNodeIndex;

    VEC3 scale;
    VEC3 rotation;
    VEC3 position;
    VEC3 minPos;
    VEC3 maxPos;

    Pointer toParentNode;    // Offset to the parent ResNodeData from the start of the structure. 0 when omitted.
    Pointer toChildNode;     // Offset to the child ResNodeData from the start of the structure. 0 when omitted.
    Pointer toNextSibling;   // Offset to the next sibling's ResNodeData from the start of the structure. 0 when omitted.
    Pointer toPrevSibling;   // Offset to the previous sibling's ResNodeData from the start of the structure. 0 when omitted.
    Pointer toResUserData;   // When <user_data_array> is present, this is an offset to user data from the start of the structure. (0 when not present.)

    MTX34 modelMtx;       // Modeling matrix (at time of bind pose)
    MTX34 invModelMtx;    // Inverse of the modelMtx matrix (used for skinning)
	//Real Vars
    
    
    
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
