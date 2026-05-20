 package colReader;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class colObject 
{
	String name = "";
	ArrayList<CollisionMaterial> normalsList = new ArrayList<CollisionMaterial>();
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
	int VertexPos;
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
			normalsList.add(new CollisionMaterial(name+" normals",0));
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
		addFromOBJ(lines, ref, vertexOffset, value);
	}
	private void addFromOBJ(List<String> lines, int ref, int vertexOffset, int value)
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
				normalsList.add(new CollisionMaterial("collision",1));
			}
			else if(name.indexOf("Ground")!=0) 
			{
				normalsList.add(new CollisionMaterial("collision",1));
			}
			normalsList.add(new CollisionMaterial("37097291_0",0));
			
			
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
	public colObject(ByteBuffer data) 
	{
		int StartPos = data.position();
		byte chara = data.get();
		name = "";
		//System.out.println("aab");
		while(chara!=0)
		{
			name += (char)chara;
			//System.out.println((char)chara);
			chara = data.get();
		}
		//System.out.println("aaa");
		value = data.getInt(StartPos + 32);
		//System.out.println(name);
		referenceValue = data.getInt(StartPos + 36);
		amountNormalObjects = data.getInt(StartPos + 40);
		normalObjectLocation = data.getInt(StartPos + 44);
		val1 = data.getFloat(StartPos + 48);
		val2 = data.getFloat(StartPos + 68);
		val3 = data.getFloat(StartPos + 88);
		zPos = data.getFloat(StartPos + 96);
		yPos = data.getFloat(StartPos + 100);
		xPos = data.getFloat(StartPos + 104);
		wPos = data.getFloat(StartPos + 108);
		vertexAmount = data.getInt(StartPos + 112);
		VertexPos = data.getInt(StartPos + 116);
		headerAmount = data.getInt(StartPos + 120);
		headerPos = data.getInt(StartPos + 124);
		val5 = data.getInt(StartPos + 128);
		val6 = data.getInt(StartPos + 132);
		float136 = data.getFloat(StartPos + 136);
		float140 = data.getFloat(StartPos + 140);
		float144 = data.getFloat(StartPos + 144);
		float148 = data.getFloat(StartPos + 148);
		float152 = data.getFloat(StartPos + 152);
		float156 = data.getFloat(StartPos + 156);
		if(ColReader.optimizeCollision==false)
		{
			if(headerAmount == 0 && headerPos == 0)
			{
				listAmount = 0;
				listPos = 0;
			}
			else
			{
				listAmount = data.getInt(headerPos)/2;
				listPos = data.getInt(headerPos+4);
			}
			
		}
		else if(ColReader.optimizeCollision == true)
		{
			amountNormalObjects = 0;
			normalObjectLocation = 0;
			if(referenceValue==0||referenceValue==-1) // Erase Entries for Wall Ground and Generic
			{
				headerAmount = 0;
				headerPos = 0;
				VERTEX = new ArrayList<vertex>();
				vertexAmount =0;
				VertexPos=0;
				
			}
			if(headerAmount == 0 && headerPos == 0)
			{
				listAmount = 0;
				listPos = 0;
			}
			else
			{
				listAmount = data.getInt(headerPos);
				listPos = data.getInt(headerPos+4);
			}
		}
		for(int i = 0; i < amountNormalObjects; i++)
		{
			data.position(i*160+normalObjectLocation);
			normalsList.add(new CollisionMaterial(data));
		}
		VERTEX = new ArrayList<vertex>();
		if(vertexAmount != 0 && VertexPos != 0) 
		{
			for(int i = VertexPos; i<VertexPos+vertexAmount*16; i+=16)
			{
				data.position(i);
				VERTEX.add(new vertex(data));
			}
		}
		if(listAmount != 0 && listPos != 0)
		{
			treePos = data.getInt(listPos);
		}
		if(treePos>0)
		{
			ArrayList<colTree> trees = new ArrayList<colTree>();
			data.position(treePos);
			trees.add(new colTree(data));
			int maxTree = trees.get(0).getMaxIndex();
			for(int i = 1; i<=maxTree; i++)
			{
				data.position(treePos + i * 48);
				trees.add(new colTree(data));
				if(maxTree<trees.get(i).getMaxIndex()) maxTree = trees.get(i).getMaxIndex();
			}
			System.out.println("max tree Index = "+ maxTree);
			forest = new colTree[trees.size()];
			for(int i =0; i<trees.size();i++)
			{
				forest[i] = trees.get(i);
			}
		}
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
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(VertexPos).array());
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
		if(listAmount == 0) listPos = 0;
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
		if(forest==null||listAmount == 0)
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
			ret += forest[i].getIndexDataSize();
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
		ret = bFM.Utils.mergeArrays(ret, ByteBuffer.allocate(4).putInt(VertexPos).array());
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
	public int getFaceAmount()
	{
		if(forest==null)return 0;
		int ret = 0;
		for(colTree tree : forest)
		{
			ret += tree.getFaceAmount();
		}
		return ret;
	}
	public int getVertexAmount() 
	{
		return vertexAmount;
	}
	public float getXOffset() 
	{
		return xPos;
	}
	public float getYOffset() 
	{
		return yPos;
	}
	public float getZOffset() 
	{
		return zPos;
	}
	public int getReferenceIndex()
	{
		return referenceValue;
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
			this.VertexPos = vertexPos;
		}
		else
		{
			if(normalsList.size()>0) normalObjectLocation = otherObjectPos;
			if(headerAmount>0)this.headerPos = headerPos;
			this.listPos = listPos;
			this.treePos = treePos;
			if(forest!=null)forest[0].updateTree(indexPos);
			if(VERTEX!=null&&VERTEX.size()>0)this.VertexPos = vertexPos;
		}
	}
	public int getTreeDataSize() 
	{
		if(forest==null) return 0;
		int ret = forest.length * 48;
		if(ret % 32 != 0)
		{
			//ret = ((ret / 32) + 1) * 32;
		}
		return ret;
	}
	public int getVertexDataSize() 
	{
		if(VERTEX==null) return 0;
		int ret = VERTEX.size() * 16;
		if(ret % 32 != 0)
		{
			ret = ((ret / 32) + 1) * 32;
		}
		return ret;
	}
	public int getHeaderDataSize() 
	{
		if(headerAmount>2 || ColReader.optimizeCollision) return headerAmount * 32;
		return 32;
	}
	public void replaceFromOBJ(List<String> lines, int ref, int vertexOffset, int value) 
	{
		addFromOBJ(lines, ref, vertexOffset, value);
	}
	public void setName(String name)
	{
		this.name = name;
	}
}
