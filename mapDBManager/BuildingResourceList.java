package mapDBManager;

import java.util.ArrayList;
import java.util.List;

public class BuildingResourceList 
{
	ArrayList<BuildingResource> Buildings = new ArrayList<BuildingResource>();
	ArrayList<String> Connections = new ArrayList<String>();
	public BuildingResourceList(List<String> lines)
	{
		BuildingResource building = null;
		for(String line : lines)
		{
			if(line.indexOf("MPJP_NUM ")!=-1)
			{
				
			}
			else if(line.indexOf("NUM ")!=-1)
			{
				
			}
			else if(line.indexOf("MPJP ")!=-1)
			{
				Connections.add(line);
			}
			else if(line.indexOf("DAT ")!=-1)
			{
				//System.out.println(Buildings.size());
				building = new BuildingResource(line);
				//System.out.println(building.buildingCode);
				Buildings.add(building);
			}
			else
			{
				building.addLine(line);
			}
		}
	}
	public int size() 
	{
		return 0;
	}
	public BuildingResource getBuilding(int buildingCode)
	{
		for(BuildingResource Building : Buildings)
		{
			if(Building.getCode()==buildingCode)
			{
				return Building;
			}
		}
		return null;
	}
	public String toString()
	{
		String ret = "NUM " + size() + ";\n";
		for(BuildingResource bld : Buildings)
			ret += bld;
		ret += "MPJP_NUM " + Connections.size() +";\n";
		for(String str : Connections)
			ret += str + "\n";
		return ret;
	}
	public byte[] toBytes()
	{
		return toString().getBytes();
	}
}
