package BrresManager.ModelManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import BrresManager.Pointer;
import bFM.Data;

public class ModelVector3Data implements Data
{
	//Byte Vars
	int size;
	int MDL0Pos;
	Pointer vertexArrayPos;
	Pointer namePos;
	int index;
	int num1;
	int num2;
	int vertexSize; // Short
	int vertexCount; // short
	VEC3 minimumValues;
	VEC3 maximumValues;
	//Real Vars
	ByteBuffer data;
	ArrayList<VEC3> Verticies = new ArrayList<VEC3>();
	protected ModelVector3Data(ByteBuffer data, Pointer vertexPos, Pointer mdl0Pos)
	{
		//Read Byte Vars
		size = data.getInt(vertexPos.getPosition());
		int MDL0Offset = data.getInt(vertexPos.getPosition() + 4);
		if(MDL0Offset != vertexPos.getOffset(mdl0Pos)) throw new IllegalArgumentException("Model Offset for Vector 3 Data is Unexpected Value");
		vertexArrayPos = new Pointer(vertexPos, data.getInt(vertexPos.getPosition() + 8));
		namePos = new Pointer(vertexPos, data.getInt(vertexPos.getPosition() + 12));
		index = data.getInt(vertexPos.getPosition() + 16);
		num1 = data.getInt(vertexPos.getPosition() + 20);
		num2 = data.getInt(vertexPos.getPosition() + 24);
		vertexSize = data.getShort(vertexPos.getPosition() + 28);
		vertexCount = data.getShort(vertexPos.getPosition() + 30);
		minimumValues = new VEC3(data, new Pointer(vertexPos, 32));
		maximumValues = new VEC3(data, new Pointer(vertexPos, 44));
		
		//Get Real Vars
		this.data = data;
		
		for(int i = 0; i < vertexCount; i++)
		{
			Verticies.add(new VEC3(data, new Pointer(vertexArrayPos, i * vertexSize)));
		}
	}
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
	public String toString() 
	{
		String ret = "Verts\n";
		for(int i = 0; i < Verticies.size(); i++)
		{
			ret += Verticies.get(i).toString();
		}
		System.out.println(ret);
		return ret;
	}
}
