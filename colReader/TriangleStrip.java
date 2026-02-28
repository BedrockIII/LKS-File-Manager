package colReader;

import java.util.ArrayList;
import java.util.Arrays;

public class TriangleStrip 
{
	//Copied from
	//https://github.com/gruco0002/triangle_strip/blob/main/triangle_strip.py
	public static int[] findStrip(face[] faces)
	{
		boolean hitLimit = false;
		int[][] triangles = new int[faces.length][3];
		for(int i = 0; i<faces.length; i++)
		{
			triangles[i] = faces[i].getVerts();
		}
		int usage = 1;
		int[] ret = null;
		while(ret==null&&!hitLimit)
		{
			ArrayList<Integer> tempUsedTriangles = new ArrayList<Integer>();
			for(int i = 0; i<triangles.length; i++)
			{
				tempUsedTriangles.add(0);
			}
			ArrayList<Integer> tempTriangleStrip = new ArrayList<Integer>();
			int[] tempUsedTris = new int[tempUsedTriangles.size()];
			for(int i = 0; i<tempUsedTriangles.size(); i++)
			{
				tempUsedTris[i] = tempUsedTriangles.get(i);
			}
			int[] tempTriStrip = new int[tempTriangleStrip.size()];
			for(int i = 0; i<tempTriangleStrip.size(); i++)
			{
				tempTriStrip[i] = tempTriangleStrip.get(i);
			}
			ret = findStripInternal(triangles, tempTriangleStrip, tempUsedTris, usage);
			usage+= 1;
			if(usage>2)//limite usage
			{
				hitLimit=true;
			}
		}
		return ret;
	}
	private static int[] findStripInternal(int[][] triangles, ArrayList<Integer> currentStrip, int[] usedTriangles, int maxTriangleUsage)
	{
		int[] ret = null;
		boolean unusedTrianglesExist = false;
		for(int i = 0; i<triangles.length; i++)
		{
			if(usedTriangles[i] == 0)
			{
				unusedTrianglesExist = true;
				break;
			}
		}
		if(!unusedTrianglesExist)
		{
			return listToArr(currentStrip);
		}
		if(currentStrip.size() == 0)
		{
			for(int i = 0; i<triangles.length; i++)
			{
				int[] triangle = triangles[i];
				usedTriangles[i]=1;
				currentStrip.add(triangle[0]);
				currentStrip.add(triangle[1]);
				currentStrip.add(triangle[2]);
				ret = findStripInternal(triangles, currentStrip, usedTriangles, maxTriangleUsage);
				if(ret!=null)
				{
					return ret;
				}
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.add(triangle[0]);
				currentStrip.add(triangle[2]);
				currentStrip.add(triangle[1]);
				ret = findStripInternal(triangles, currentStrip, usedTriangles, maxTriangleUsage);
				if(ret!=null)
				{
					return ret;
				}
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.add(triangle[1]);
				currentStrip.add(triangle[0]);
				currentStrip.add(triangle[2]);
				ret = findStripInternal(triangles, currentStrip, usedTriangles, maxTriangleUsage);
				if(ret!=null)
				{
					return ret;
				}
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.add(triangle[1]);
				currentStrip.add(triangle[2]);
				currentStrip.add(triangle[0]);
				ret = findStripInternal(triangles, currentStrip, usedTriangles, maxTriangleUsage);
				if(ret!=null)
				{
					return ret;
				}
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.add(triangle[2]);
				currentStrip.add(triangle[0]);
				currentStrip.add(triangle[1]);
				ret = findStripInternal(triangles, currentStrip, usedTriangles, maxTriangleUsage);
				if(ret!=null)
				{
					return ret;
				}
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.add(triangle[2]);
				currentStrip.add(triangle[1]);
				currentStrip.add(triangle[0]);
				ret = findStripInternal(triangles, currentStrip, usedTriangles, maxTriangleUsage);
				if(ret!=null)
				{
					return ret;
				}
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				currentStrip.remove(currentStrip.size()-1);
				usedTriangles[i] = 0;
			}
		} else
		{
			for(int i = 0; i<triangles.length; i++)
			{
				int[] triangle = triangles[i];
				if(usedTriangles[i]<maxTriangleUsage)
				{
					boolean sharesSide = false;
					int sharedVertCount = 0;
					int unusedVert = -1;
					for(int j = 0; j<3; j++)
					{
						if(currentStrip.get(currentStrip.size()-1)==triangle[j]) sharedVertCount++;
						else if(currentStrip.get(currentStrip.size()-2)==triangle[j]) sharedVertCount++;
						else unusedVert = triangle[j];
					}
					sharesSide = sharedVertCount==2;
					if(sharesSide)
					{
						usedTriangles[i] = usedTriangles[i]+1;
						currentStrip.add(unusedVert);
						System.out.println(Arrays.toString(currentStrip.toArray()));
						//System.out.print(".");
						ret = findStripInternal(triangles, currentStrip, usedTriangles, maxTriangleUsage);
						if(ret!=null)
						{
							return ret;
						}
						currentStrip.remove(currentStrip.size()-1);
						usedTriangles[i] = usedTriangles[i]-1;
					}
				}
			}
		}
		return null;
	}
	private static int[] listToArr(ArrayList<Integer> list)
	{
		int[] ret = new int[list.size()];
		for(int i = 0; i<list.size(); i++)
		{
			ret[i] = list.get(i);
		}
		return ret;
	}
	public static ArrayList<ArrayList<face>> findGroups(face[] faces) 
	{
		if(faces.length==0)return new ArrayList<ArrayList<face>>();
		//System.out.println(faces.length);
		ArrayList<ArrayList<face>> groups = new ArrayList<ArrayList<face>>();
		ArrayList<face> groupFaces = new ArrayList<face>();
		boolean ungroupedFaces = true;
		while(ungroupedFaces)
		{
			for(int i =0; i< faces.length; i++)
			{
				if(faces[i]!=null)
				{
					groupFaces.add(faces[i]);
					faces[i]=null;
					break;
				}
			}
			for(int j =0; j< groupFaces.size(); j++)
			{
				for(int i =0; i< faces.length; i++)
				{
					if(faces[i]!=null&&groupFaces.get(j).sharesSide(faces[i])) 
					{
						if(faces[i]!=null)groupFaces.add(faces[i]);
						//System.out.print(faces[i]);
						faces[i]=null;
					}
				}
				//System.out.println(Arrays.toString(faces));
				
			}
			ungroupedFaces=false;
			for(int i =0; i< faces.length&&!ungroupedFaces; i++)
			{
				if(faces[i]!=null) ungroupedFaces=true;
				//if(ungroupedFaces) System.out.print(faces[i]);
			}
			//if(groupFaces.size()!=0)//System.out.println(Arrays.toString(groupFaces.toArray()));
			if(groupFaces.size()!=0)groups.add(new ArrayList<face>(groupFaces));
			groupFaces.removeAll(groupFaces);
			//System.out.println(ungroupedFaces);
		}
		return groups;
	}
}
