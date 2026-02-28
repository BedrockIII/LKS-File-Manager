 package colReader;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class colObject 
{
	String name = "";
	ArrayList<otherObject> normalsList = new ArrayList<otherObject>();
	int value;
	int referenceValue;
	int amountNormalObjects;
	int normalObjectLocation;
	float val1;//All of these can safely be 0
	//skip empty bytes
	float val2;
	//skip empty bytes
	float val3;
	
	int val5;
	int val6;
	float zPos;//east west
	float xPos;//north south
	float yPos;//up down
	float wPos;
	int vertexAmount;
	int vertexPos;
	int headerAmount;
	int headerPos;
	
	float float136;
	float float140;
	float float144;
	float float148;
	float float152;
	float float156;
	
	
	//LIST VARS
	int listAmount;
	int listPos;
	//TREE VARS
	int treePos;
	//
	colTree[] forest;
	face[] faces;
	ArrayList<vertex> VERTEX;
	public colObject(byte[] colData, byte[] data) 
	{
		name = new String(Arrays.copyOfRange(colData, 0, byteArrIndex(colData,(byte)0)));
		value = ByteBuffer.wrap(colData).getInt(32);
		System.out.println(name);
		referenceValue = ByteBuffer.wrap(colData).getInt(36);
		amountNormalObjects = ByteBuffer.wrap(colData).getInt(40);
		normalObjectLocation = ByteBuffer.wrap(colData).getInt(44);
		val1 = ByteBuffer.wrap(colData).getFloat(48);
		val2 = ByteBuffer.wrap(colData).getFloat(68);
		val3 = ByteBuffer.wrap(colData).getFloat(88);
		zPos = ByteBuffer.wrap(colData).getFloat(96);
		yPos = ByteBuffer.wrap(colData).getFloat(100);
		xPos = ByteBuffer.wrap(colData).getFloat(104);
		wPos = ByteBuffer.wrap(colData).getFloat(108);
		vertexAmount = ByteBuffer.wrap(colData).getInt(112);
		vertexPos = ByteBuffer.wrap(colData).getInt(116);
		headerAmount = ByteBuffer.wrap(colData).getInt(120);
		headerPos = ByteBuffer.wrap(colData).getInt(124);
		val5 = ByteBuffer.wrap(colData).getInt(128);
		val6 = ByteBuffer.wrap(colData).getInt(132);
		float136 = ByteBuffer.wrap(colData).getFloat(136);
		float140 = ByteBuffer.wrap(colData).getFloat(140);
		float144 = ByteBuffer.wrap(colData).getFloat(144);
		float148 = ByteBuffer.wrap(colData).getFloat(148);
		float152 = ByteBuffer.wrap(colData).getFloat(152);
		float156 = ByteBuffer.wrap(colData).getFloat(156);
		if(ColReader.optimizeCollision==false)
		{
			if(headerAmount == 0 && headerPos == 0)
			{
				listAmount = 0;
				listPos = 0;
			}
			else
			{
				listAmount = ByteBuffer.wrap(data).getInt(headerPos)/2;
				listPos = ByteBuffer.wrap(data).getInt(headerPos+4);
			}
			
		}
		else
		{
			amountNormalObjects = 0;
			normalObjectLocation = 0;
			if(referenceValue==0||referenceValue==-1)
			{
				headerAmount = 0;
				headerPos = 0;
				VERTEX = new ArrayList<vertex>();
				vertexAmount =0;
				vertexPos=0;
				
			}
			if(headerAmount == 0 && headerPos == 0)
			{
				listAmount = 0;
				listPos = 0;
			}
			else
			{
				listAmount = ByteBuffer.wrap(data).getInt(headerPos);
				listPos = ByteBuffer.wrap(data).getInt(headerPos+4);
			}
		}
		for(int i = 0; i < amountNormalObjects; i++)
		{
			normalsList.add(new otherObject(Arrays.copyOfRange(data, i*160+normalObjectLocation, i*160+normalObjectLocation+160)));
		}
		VERTEX = new ArrayList<vertex>();
		if(vertexAmount != 0 && vertexPos != 0) 
		{
			for(int i = vertexPos; i<vertexPos+vertexAmount*16; i+=16)
			{
				VERTEX.add(new vertex(Arrays.copyOfRange(data, i, i+12)));
			}
		}
		if(listAmount != 0 && listPos != 0)
		{
			treePos = ByteBuffer.wrap(data).getInt(listPos);
		}
		if(treePos>0)
		{
			ArrayList<colTree> trees = new ArrayList<colTree>();
			trees.add(new colTree(Arrays.copyOfRange(data, treePos, treePos+48), VERTEX, data));
			int tempTreePos = treePos+48;
			int maxTree = trees.get(0).getMaxIndex();
			for(int i = 1; i<=maxTree; i++)
			{
				trees.add(new colTree(Arrays.copyOfRange(data, tempTreePos, tempTreePos+48), VERTEX, data));
				if(maxTree<trees.get(i).getMaxIndex()) maxTree = trees.get(i).getMaxIndex();
				tempTreePos+=48;
			}
			System.out.println("max tree Index = "+ maxTree);
			forest = new colTree[trees.size()];
			for(int i =0; i<trees.size();i++)
			{
				forest[i] = trees.get(i);
			}
		}
	}
	public colObject(String name, int ref, int value)
	{
		//this.name = name;
		zPos = 0;//east west
		xPos = 0;//north south
		yPos = 0;//up down
		wPos = 1;
		this.name = name;
		
		
		normalObjectLocation = 0;
		this.value = value;
		//int vertexAmount;
		//int vertexPos;
		
		headerPos = 0;
		val1 = 1;
		val2 = 1;
		val3 = 1;
		val5 = 16777216;
		val6 = 0;
		referenceValue = ref;
		vertexAmount = 0;
		if(ColReader.optimizeCollision==true)
		{
			headerAmount=0;
			amountNormalObjects = 0;
		}
		else 
		{
			amountNormalObjects = 1;
			headerAmount = 1;
			normalsList.add(new otherObject(name+" normals",0));
		}
		listAmount = 0;
		listPos = 0;
		float136 = ByteBuffer.wrap(new byte[] {(byte) 0xff, (byte) 0x7f, (byte) 0xff, (byte) 0xff}).getFloat();
		float140 = ByteBuffer.wrap(new byte[] {(byte) 0xff, (byte) 0x7f, (byte) 0xff, (byte) 0xff}).getFloat();
		float144 = ByteBuffer.wrap(new byte[] {(byte) 0xff, (byte) 0x7f, (byte) 0xff, (byte) 0xff}).getFloat();
		float148 = ByteBuffer.wrap(new byte[] {(byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff}).getFloat();
		float152 = ByteBuffer.wrap(new byte[] {(byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff}).getFloat();
		float156 = ByteBuffer.wrap(new byte[] {(byte) 0x7f, (byte) 0x7f, (byte) 0xff, (byte) 0xff}).getFloat();
	}
	public colObject(ArrayList<String> lines, int ref, int vertexOffset, int value) 
	{
		ArrayList<vertex> verticies = new ArrayList<vertex>();
		ArrayList<face> faces = new ArrayList<face>();
		zPos = 0;//east west
		xPos = 0;//north south
		yPos = 0;//up down
		wPos = 1;
		headerPos = 0;
		val1 = 1;
		val2 = 1;
		val3 = 1;
		val5 = 16777216;
		val6 = 0;
		referenceValue = ref;
		this.value = value;
		for(int i = 0;i<lines.size();i++)
		{
			if(lines.get(i).charAt(0)=='o'&&lines.get(i).charAt(1)==' ')
			{
				name = lines.get(i).substring(2);
			}
			if(lines.get(i).charAt(0)=='v'&&lines.get(i).charAt(1)==' ')
			{
				verticies.add(new vertex(lines.get(i)));
			}
			if(lines.get(i).charAt(0)=='f'&&lines.get(i).charAt(1)==' ')
			{
				System.out.println(lines.get(i));
				faces.add(new face(lines.get(i), vertexOffset, verticies));
			}
		}
		if(ColReader.optimizeCollision)
		{
			if(ref==0||ref==-1)
			{
				headerAmount=0;
				listAmount = 0;
			}
			else
			{
				headerAmount=1;
				listAmount = 1;
			}
		}else
		{
			
			if(name.indexOf("Wall")!=0) 
			{
				normalsList.add(new otherObject("collision",1));
			}
			else if(name.indexOf("Ground")!=0) 
			{
				normalsList.add(new otherObject("collision",1));
			}
			normalsList.add(new otherObject("37097291_0",0));
			
			
		}
		
		VERTEX = new ArrayList<vertex>(verticies);
		if(ref!=0&&ref!=-1)makeNewTree(vertexOffset,  faces);
		vertexAmount = VERTEX.size();
		headerAmount = 1;
		amountNormalObjects = normalsList.size();
		float136 = getMaxX();
		float140 = getMaxY();
		float144 = getMaxZ();
		float148 = getMinX();
		float152 = getMinY();
		float156 = getMinZ();
	}
	public byte[] getObjects()
	{
		byte[] ret = new byte[32];
		for(int i = 0; i<name.length(); i++)
		{
			ret[i] = (byte) name.charAt(i);
		}
		if(ColReader.optimizeCollision)
		{
			//val1 = 0;
			//val2 = 0;
			//val3 = 0;
			zPos = 0;
			yPos = 0;
			xPos = 0;
			//wPos = 0;
		}
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(value).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(referenceValue).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(amountNormalObjects).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(normalObjectLocation).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val1).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[16]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val2).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[16]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val3).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[4]);
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(xPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(wPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(vertexAmount).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(vertexPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(headerAmount).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(headerPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(val5).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(val6).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float136).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float140).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float144).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float148).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float152).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float156).array());
		if(ret.length == 160)
			return ret;
		else
		{
			System.out.print("Col object length is wrong" + ret.length);
			return null;
		}
	}
	public byte[] getHeader()
	{
		if(ColReader.optimizeCollision&&headerAmount==0)return new byte[0];
		if(listAmount!=0)listAmount=1;
		byte[] ret = bFM.Utils.mergeArrays(ByteBuffer.allocate(4).putInt(listAmount*2).array(), ByteBuffer.allocate(4).putInt(listPos).array());
		ret = bFM.Utils.mergeArrays(ret, new byte[24]);
		return ret ;
	}
	public byte[] getList()
	{
		if(listAmount==0)return new byte[0];
		byte[] ret = new byte[32];
		byte[] num = ColReader.longToBytes(treePos,4);
		for(int i = 1; i<num.length; i++)
		{
			ret[4-i] = num[num.length-i];
		}
		for(int i = 8; i<ret.length; i++)
		{
			ret[i] = (byte)0xAA;
		}
		return ret;
	}
	public byte[] getTree()
	{
		if(forest==null) return new byte[0];
		byte[] ret = new byte[0];
		for(int i = 0; i<forest.length; i++)
		{
			ret = bFM.Utils.mergeArrays(ret, forest[i].getBytes());
		}
		return ret;
	}
	public byte[] getIndex()
	{
		byte[] ret = new byte[0];
		if(forest==null)
		{
			return ret;
		}
		for(int i = 0; i<forest.length; i++)
		{
			 ret = bFM.Utils.mergeArrays(ret, forest[i].getIndex());
		}
		return ret;
	}
	public byte[] getVertex()
	{
		
		if(vertexAmount==0) return new byte[0];
		byte[] ret = VERTEX.get(0).getBytes();
		for(int i = 1; i<VERTEX.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, VERTEX.get(i).getBytes());
		}
		int buff = 16-ret.length%16;
		if(buff==16)buff=0;
		return  bFM.Utils.mergeArrays(ret, ColReader.longToBytes(0, buff));
	}
	public int getIndexSize()
	{
		int ret = 0;
		if(forest==null)return ret;
		for(int i = 0; i<forest.length; i++)
		{
			ret += forest[i].getIndexSize();
		}
		return ret;
	}
	private void makeNewTree(int offset, ArrayList<face> faces)
	{
		forest = new colTree[1];
		forest[0] = new colTree(offset,faces,VERTEX);
	}
	public int byteArrIndex(byte[] arr, byte is)
	{
		for(int i = 0; i<arr.length; i++)
		if(arr[i]==is) return i;
		return -1;
	}
	public int arrIndex(byte[] a, char c)
	{
		for(int i = 0; i<a.length; i++)
		{
			if(a[i]==(byte)i) return i;
		}
		return -1;
	}
 	public byte[] toBytes()
	{
		byte[] ret = new byte[32];
		for(int i = 0; i<name.length(); i++)
		{
			ret[i] = (byte)name.charAt(i);
		}
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(value).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(referenceValue).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(amountNormalObjects).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(normalObjectLocation).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val1).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val2).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(val3).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(0).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(zPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(yPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(xPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(wPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(vertexAmount).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(vertexPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(headerAmount).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(headerPos).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(val5).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(val6).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float136).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float140).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float144).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float148).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float152).array());
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putFloat(float156).array());
		return ret;
	}
	public String toString(int vertexOffset)
	{
		String ret = "o "+ name + "\n";
		if(VERTEX!=null)
		{
			for(int i = 0; i < VERTEX.size();i++)
			{
				ret = ret + VERTEX.get(i).toString();
			}
		}
		if(forest!=null)
		{
			for(int i = 0; i < forest.length; i++)
			{
				//ret = ret + forest[i].toString(name + " " +i, vertexOffset);
				ret = ret + forest[i].toString(vertexOffset);
			}
		}
		return ret;
	}
	public int vertexAmount()
	{
		return vertexAmount;
	}
	public String getName()
	{
		return name;
	}
	public byte[] getOtherObjects() 
	{
		
		byte[] ret = new byte[0];
		for(int i = 0; i< normalsList.size(); i++)
		{
			ret = bFM.Utils.mergeArrays(ret, normalsList.get(i).getObjects());
		}
		return ret;
	}
	private float getMinX()
	{
		float ret = Float.MAX_VALUE;
		for(int i = 0; i<VERTEX.size();i++)
		{
			if(ret>VERTEX.get(i).getX()) ret = VERTEX.get(i).getX();
		}
		return ret;
	}
	private float getMaxX()
	{
		float ret = Float.MIN_VALUE;
		for(int i = 0; i<VERTEX.size();i++)
		{
			if(ret<VERTEX.get(i).getX()) ret = VERTEX.get(i).getX();
		}
		return ret;
	}
	private float getMinY()
	{
		float ret = Float.MAX_VALUE;
		for(int i = 0; i<VERTEX.size();i++)
		{
			if(ret>VERTEX.get(i).getY()) ret = VERTEX.get(i).getY();
		}
		return ret;
	}
	private float getMaxY()
	{
		float ret = Float.MIN_VALUE;
		for(int i = 0; i<VERTEX.size();i++)
		{
			if(ret<VERTEX.get(i).getY()) ret = VERTEX.get(i).getY();
		}
		return ret;
	}
	private float getMinZ()
	{
		float ret = Float.MAX_VALUE;
		for(int i = 0; i<VERTEX.size();i++)
		{
			if(ret>VERTEX.get(i).getZ()) ret = VERTEX.get(i).getZ();
		}
		return ret;
	}
	private float getMaxZ()
	{
		float ret = Float.MIN_VALUE;
		for(int i = 0; i<VERTEX.size();i++)
		{
			if(ret<VERTEX.get(i).getZ()) ret = VERTEX.get(i).getZ();
		}
		return ret;
	}
	public int getHeaderAmount() 
	{
		return headerAmount;
	}
	public int getListAmount() 
	{
		return listAmount;
	}
	public int getTreeAmount() 
	{
		if(forest==null)return 0;
		return forest.length;
	}
	public int getVertexAmount() 
	{
		return vertexAmount;
	}
	public void updatePositions(int otherObjectPos, int headerPos, int listPos, int treePos, int indexPos, int vertexPos) 
	{
		if(ColReader.optimizeCollision==false)
		{
			normalObjectLocation = otherObjectPos;
			this.headerPos = headerPos;
			this.listPos = listPos;
			this.treePos = treePos;
			if(forest!=null)forest[0].updateTree(indexPos);
			this.vertexPos = vertexPos;
		}
		else
		{
			if(normalsList.size()>0) normalObjectLocation = otherObjectPos;
			if(headerAmount>0)this.headerPos = headerPos;
			this.listPos = listPos;
			this.treePos = treePos;
			if(forest!=null)forest[0].updateTree(indexPos);
			if(VERTEX!=null&&VERTEX.size()>0)this.vertexPos = vertexPos;
		}
	}
}
