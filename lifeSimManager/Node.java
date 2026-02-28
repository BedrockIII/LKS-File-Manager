package lifeSimManager;

import java.util.ArrayList;

public class Node 
{
	double x, y, z = 0;
	ArrayList<Integer> connections = new ArrayList<Integer>();
	public Node()
	{
		x=0;
		y=0;
		z = 0;
		connections = new ArrayList<Integer>();
	}
	public Node(String input)
	{
		x=0;
		y=0;
		z = 0;
		connections = new ArrayList<Integer>();
		if(input.indexOf("NODE")!=-1)
		{
			importNode(input.substring(4));
		}
	}
	private void importNode(String input) 
	{
		final String chars = "1234567890.,-";
		String temp = "";
		for(int i =0; i<input.length(); i++)
		{
			if(chars.indexOf(input.charAt(i))!=-1)
			{
				temp +=input.charAt(i);
			}
		}
		input=temp;
		x = Float.valueOf(input.substring(0, input.indexOf(',')));
		input = input.substring(input.indexOf(',')+1);
		y = Float.valueOf(input.substring(0, input.indexOf(',')));
		input = input.substring(input.indexOf(',')+1);
		z = Float.valueOf(input.substring(0, input.indexOf(',')));
		input = input.substring(input.indexOf(',')+1);
		while(input.length()>0)
		{
			if(input.indexOf(',')!=-1)connections.add(Integer.valueOf(input.substring(0, input.indexOf(','))));
			if(input.indexOf(',')!=-1) input = input.substring(input.indexOf(',')+1);
			else{input = "";}
		}
	}
	public String toOBJV() 
	{
		return "v " + x + " " + y + " " + z;
	}
	public ArrayList<String> toOBJF(int origin) 
	{
		ArrayList<String> rets = new ArrayList<String>();
		for(int i = 0; i < connections.size(); i++)
		{
			String ret = "l  " + origin;
			ret+= " " + (connections.get(i)+1);
			rets.add(ret);
		}
		return rets;
	}
}
