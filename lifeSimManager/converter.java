package lifeSimManager;

import java.util.ArrayList;
import java.util.List;

public class converter 
{
	ArrayList<Node> nodes = new ArrayList<Node>();
	public converter(List<String> list)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).indexOf("NODE")!=-1)
			{
				System.out.println("Creating Node from Line: " + i);
				nodes.add(new Node(list.get(i)));
			}
		}
	}
	public ArrayList<String> toOBJ()
	{
		ArrayList<String> lines = new ArrayList<String>();
		System.out.println("Found Nodes: "+ nodes.size());
		lines.add("o npcPathing");
		lines.addAll(getVerts());
		lines.addAll(getFaces());
		return lines;
	}
	private ArrayList<String> getVerts() 
	{
		ArrayList<String> lines = new ArrayList<String>();
		for(int i = 0; i<nodes.size();i++)
		{
			lines.add(nodes.get(i).toOBJV());
		}
		return lines;
	}
	private ArrayList<String> getFaces() 
	{
		ArrayList<String> lines = new ArrayList<String>();
		for(int i = 0; i<nodes.size();i++)
		{
			lines.addAll(nodes.get(i).toOBJF(i));
		}
		return lines;
	}
}
