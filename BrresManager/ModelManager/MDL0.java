package BrresManager.ModelManager;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import BrresManager.BrresHelpers;
import BrresManager.BrresHelpers.ResDicNode;
import BrresManager.BrresHelpers.ResDictionary;
import BrresManager.Pointer;
import bFM.Data;

public class MDL0 implements Data
{
	//Byte Vars
    
    //Real Vars
    ResDicNode parent;
	ByteBuffer data;
    Pointer mdl0Start;
    ResDictionary definitionsDictionary;
    ResDictionary boneDictionary;
    ResDictionary vertexDictionary;
    ResDictionary normalDictionary;
    ResDictionary colorDictionary;
    ResDictionary uvDictionary;
    ResDictionary materialDictionary;
    ResDictionary shaderDictionary;
    ResDictionary objectDictionary;
    ResDictionary textureDictionary;
    
    public MDL0(ByteBuffer data, ResDicNode node, String indent)
    {
    	this.parent = node;
    	this.data = data;
        this.mdl0Start = node.getDataPosition();

        int version = data.getInt(mdl0Start.getPosition() + 8);

        if(version != 11)
            throw new IllegalArgumentException("MDL0 File is Version: " + version + ". Only Version 11 is supported");
        
        //Pointer base
        Pointer definitionsDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 16));
        Pointer boneDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 20));//relOffset(mdl0Start, 0x18);
        Pointer vertexDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 24));
        Pointer normalDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 28));
        Pointer colorDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 32));
        Pointer uvDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 36));
        Pointer furVectorDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 40));
        Pointer furPosDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 44));
        Pointer materialDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 48));//or 52
        Pointer shaderDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 52));
        Pointer objectDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 56));
        Pointer textureDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 60));
        Pointer toResPlttNameToTexPlttInfoDic = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 36));
        Pointer userData = new Pointer(mdl0Start, data.getInt(mdl0Start.getPosition() + 36));
        //Pointer name
        //ResMdlInfoData
        
        
        System.out.println(indent + "Definitions Data");
        definitionsDictionary = BrresHelpers.readDictionary(data, definitionsDic, "\t" + indent, mdl0Start);
        
        System.out.println(indent + "Bone Data");
        boneDictionary = BrresHelpers.readDictionary(data, boneDic, "\t" + indent, mdl0Start);
        
        System.out.println(indent + "Vertex Data");
        vertexDictionary = BrresHelpers.readDictionary(data, vertexDic, "\t" + indent, mdl0Start);
        initializeVertexDic();
        
        System.out.println(indent + "Normal Data");
        normalDictionary = BrresHelpers.readDictionary(data, normalDic, "\t" + indent, mdl0Start);
        initializeNormalDic();
        
        System.out.println(indent + "Color Data");
        colorDictionary = BrresHelpers.readDictionary(data, colorDic, "\t" + indent, mdl0Start);
        initializeColorDic();
        
        System.out.println(indent + "UV Data");
        uvDictionary = BrresHelpers.readDictionary(data, uvDic, "\t" + indent, mdl0Start);
        initializeUVDic();
        
        System.out.println(indent + "Material Data");
        materialDictionary = BrresHelpers.readDictionary(data, materialDic, "\t" + indent, mdl0Start);
        
        System.out.println(indent + "Shader Data");
        shaderDictionary = BrresHelpers.readDictionary(data, shaderDic, "\t" + indent, mdl0Start);
        
        System.out.println(indent + "Object Data");
        objectDictionary = BrresHelpers.readDictionary(data, objectDic, "\t" + indent, mdl0Start);
        
        System.out.println(indent + "Texture Data");
        textureDictionary = BrresHelpers.readDictionary(data, textureDic, "\t" + indent, mdl0Start);
        
    }
	private void initializeVertexDic()
    {
		if(vertexDictionary == null) return;
        ArrayList<ResDicNode> nodes = vertexDictionary.getNodes();
        for(ResDicNode node : nodes)
        {
        	Pointer vertexPos = node.getDataPosition();
        	node.setData(new ModelVector3Data(data, vertexPos, mdl0Start));
        }
        for(ResDicNode node : nodes)
        {
        	//node.getData().toString();
        }
    }
    private void initializeNormalDic()
    {
    	if(normalDictionary == null) return;
        ArrayList<ResDicNode> nodes = normalDictionary.getNodes();
        for(ResDicNode node : nodes)
        {
        	Pointer vertexPos = node.getDataPosition();
        	node.setData(new ModelVector3Data(data, vertexPos, mdl0Start));
        }
        for(ResDicNode node : nodes)
        {
        	//node.getData().toString();
        }
    }
    private void initializeColorDic() 
    {
    	if(colorDictionary == null) return;
    	ArrayList<ResDicNode> nodes = colorDictionary.getNodes();
        for(ResDicNode node : nodes)
        {
        	Pointer vertexPos = node.getDataPosition();
        	node.setData(new ModelVector3Data(data, vertexPos, mdl0Start));
        }
        for(ResDicNode node : nodes)
        {
        	//node.getData().toString();
        }
	}
    private void initializeUVDic() 
    {
    	if(uvDictionary == null) return;
    	ArrayList<ResDicNode> nodes = uvDictionary.getNodes();
        for(ResDicNode node : nodes)
        {
        	Pointer vertexPos = node.getDataPosition();
        	node.setData(new ModelVector2Data(data, vertexPos, mdl0Start));
        }
        for(ResDicNode node : nodes)
        {
        	//node.getData().toString();
        }
	}
    private int relOffset(int mdl0Start, int fieldOffset)
    {
        int rel = data.getInt(mdl0Start + fieldOffset);

        if(rel == 0)
            return 0;

        return mdl0Start + rel;
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
}