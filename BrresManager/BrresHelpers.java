package BrresManager;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import BrresManager.ModelManager.MDL0;
import bFM.Data;

public class BrresHelpers 
{
	public static ResDictionary readDictionary(ByteBuffer data, Pointer dataPos, String indent, Pointer rootPos)
	{
		ResDictionary d = new ResDictionary();
		//System.out.println(indent + "Folder: " + name);
		//System.out.println(indent + "dataPos: " + dataPos);
		//System.out.println(indent + "rootPos: " + rootOffset);
		if(dataPos.isNull()) return null;
		//System.out.println(indent + "Sub-Folder @ 0x" + Integer.toHexString(dataPos));
		if(dataPos.getPosition() + 8 >= data.capacity())
		{
			System.err.println(indent + "Res Dictionary at: " + dataPos + " overflows filesize " + data.capacity());
			return null;
		}
		
		d.size = data.getInt(dataPos.getPosition());
		d.numEntries = data.getInt(dataPos.getPosition() + 4);
		
		if(d.size <= 0 || d.size > 0x10000)
		{
			//System.err.println(indent + "Res Dictionary at: " + dataPos + " has an invalid size: " + d.size);
			return null;
		}
		
		if(d.numEntries < 0 || d.numEntries > 10000)
		{
			System.err.println(indent + "Res Dictionary at: " + dataPos + " has an invalid amount of subEntries: " + d.numEntries);
			return null;
		}
		
		//Read Nodes
		Pointer nodePos = new Pointer(dataPos.getPosition() + 24);
		for(int i = 0; i < d.numEntries; i++)
		{
			Pointer subNode = new Pointer(nodePos, (i * 16));

			ResDicNode node = readNode(data, subNode, dataPos);
			d.nodes.add(node);
			
			System.out.print(indent + "Name: \"" + node.name + "\"");
			System.out.println("\t\t Data Location: " + node.dataPos);
			
			processNodeData(data, node, indent);
		}
		return d;
	}
	public static class ResDictionary implements Data
	{
		//Byte Vars
		private int size;
		private int numEntries;
		//Real Vars
		private ArrayList<ResDicNode> nodes = new ArrayList<ResDicNode>();

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
			int ret = 24;
			for(ResDicNode node : nodes)
			{
				ret += node.getSize();
			}
			return ret;
		}
		public ArrayList<ResDicNode> getNodes() 
		{
			return nodes;
		}
		
	}
	private static ResDicNode readNode(ByteBuffer data, Pointer nodeOffset, Pointer rootOffset)
	{
		ResDicNode n = new ResDicNode();
		n.ref = data.getShort(nodeOffset.getPosition()) & 0xFFFF;
		n.flag = data.getShort(nodeOffset.getPosition() + 2) & 0xFFFF;
		n.left = data.getShort(nodeOffset.getPosition() + 4) & 0xFFFF;
		n.right = data.getShort(nodeOffset.getPosition() + 6) & 0xFFFF;
		n.stringPos = new Pointer(rootOffset, data.getInt(nodeOffset.getPosition() + 8));
		n.dataPos = new Pointer(rootOffset, data.getInt(nodeOffset.getPosition() + 12));
		
		n.name = readCString(data, n.stringPos.getPosition());
		
		return n;
	}
	@SuppressWarnings("unused")
	public static class ResDicNode implements Data
	{
		//Byte Vars
		private int ref;
		private int flag;
		private int left;
		private int right;
		private Pointer stringPos;
		private Pointer dataPos;
		//Real Vars
		private String name;
		private Data data;
		public String getName()
		{
			return name;
		}
		public Pointer getDataPosition()
		{
			return dataPos;
		}
		public void setData(Data data) 
		{
			this.data = data;
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
		@Override
		public void setName(String name) 
		{
			this.name = name;
		}
		@Override
		public int getSize() 
		{
			return 16;
		}
		public Data getData() 
		{
			return data;
		}
	}
	protected static String readString(ByteBuffer data, Pointer root, int length)
	{
		return new String(data.array(), root.getPosition(), length, StandardCharsets.US_ASCII);
	}
	protected static String readCString(ByteBuffer data, int offset)
	{
		int end = offset;

		while(end < data.capacity() && data.get(end) != 0)
			end++;
		
		return new String(data.array(), offset, end - offset, StandardCharsets.UTF_8);
	}
	private static void processNodeData(ByteBuffer data, ResDicNode node, String indent)
	{
		if(node.dataPos.isNull() || node.dataPos.getPosition() >= data.array().length)
		{
			System.err.println(indent + "Res Dictionary at: " + node.dataPos + " overflows filesize " + data.capacity());
			return;
		}
		String tag = BrresHelpers.readString(data, node.dataPos, 4);
		//System.out.println("Tag:" + tag);
		switch(tag)
		{
			case "MDL0":
				System.out.printf( "%s%s (%s)%n", indent, node.name, tag);
				node.setData(new MDL0(data, node, indent + "\t"));
				return;
			case "TEX0":
			case "CHR0":
			case "PAT0":
			case "CLR0":
			case "VIS0":
			case "SRT0":
			case "SCN0":
			case "SHP0":
			case "PLT0":
				System.out.printf( "%s%s (%s)%n", indent, node.name, tag);
				return;
		}
		if(data.getShort(node.dataPos.getPosition()+8)==-1)
		{
			//Likely a Folder
			ResDictionary dic = readDictionary(data, node.dataPos, indent + "\t", node.dataPos);
			if(dic!= null)
			{
				//Almost Definetly a folder
				node.setData(dic);
			}
		}
	}
}
