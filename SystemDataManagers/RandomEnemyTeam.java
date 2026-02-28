package SystemDataManagers;

public class RandomEnemyTeam 
{
	String Name = "Enemy";
	int groupCode = -1;
	public RandomEnemyTeam(String name, int code)
	{
		Name = name;
		groupCode = code;
	}
	public RandomEnemyTeam(String nameLine)
	{
		
		String name = "";
		String lineAfterHeader = nameLine.substring(nameLine.indexOf(">"));
		int startIndex = lineAfterHeader.indexOf('\"');
		int endIndex = lineAfterHeader.lastIndexOf('\"');
		if(startIndex!=-1&&endIndex!=-1)
		{
			name = lineAfterHeader.substring(startIndex+1, endIndex);
		}
		else
		{
			name = lineAfterHeader;
		}
		Name = name;
		groupCode = -1;
	}
	public RandomEnemyTeam() 
	{
		Name = "Enemy";
		groupCode = -1;
	}
	public void addGroup(String groupLine)
	{
		String allowedChars = "1234567890-";
		String integer = "";
		for(int i = groupLine.indexOf(">"); i<groupLine.length();i++)
		{
			if(allowedChars.indexOf(groupLine.charAt(i))!=-1)
			{
				integer+=groupLine.charAt(i);
			}
		}
		groupCode = Integer.parseInt(integer);
	}
	public String toString()
	{
		String ret = "<Monster Team> \"" + Name + "\"\n";
		ret += "<Group Code> " + groupCode + "\n";
		return ret;
	}
	public byte[] getGroupCode() 
	{
		if(groupCode>=0)
		{
			byte[] ret = new byte[2];
			for(int i = 1; i<=2; i++)
			{
				ret[2-i] = (byte) (groupCode%256);
				groupCode/=256;
				
				
			}
			return ret;
		}
		if(groupCode==-1)
			return new byte[]{(byte) 0xff, (byte) 0xff};
		groupCode += 65536;
		return getGroupCode();
	}
	public String getName()
	{
		return Name;
	}
}
