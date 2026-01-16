package mapDBManager;

public class exteriorPlace 
{
	int placeCode = -1;
	int buildingCode = -1;
	int xPos = 9999;
	int yPos = 9999;
	int zPos = 9999;
	int rotation = -1;//0 to 3 representing 90 degree turns
	int activationFlag = -1;
	int num0 = -1;
	int num1 = 2;
	int deactivationFlag = -1;
	int populationCount = 0;
	int textCode = 0;
	int num2;
	public exteriorPlace(String[] line) 
	{
		placeCode = Integer.valueOf(line[0]).intValue();
		buildingCode = Integer.valueOf(line[1]).intValue();
		xPos = Integer.valueOf(line[2]).intValue();
		yPos = Integer.valueOf(line[3]).intValue();
		zPos = Integer.valueOf(line[4]).intValue();
		rotation = Integer.valueOf(line[5]).intValue();
		activationFlag = Integer.valueOf(line[6]).intValue();
		num0 = Integer.valueOf(line[7]).intValue();
		num1 = Integer.valueOf(line[8]).intValue();
		deactivationFlag = Integer.valueOf(line[9]).intValue();
		populationCount = Integer.valueOf(line[10]).intValue();
		textCode = Integer.valueOf(line[11]).intValue();
		num2 = Integer.valueOf(line[12]).intValue();
		if(num0 != -1)
		{
			System.out.println("Num0 strange "+ placeCode);
		}
		if(num1 != 2)
		{
			System.out.println("Num1 strange "+ placeCode);
		}
	}
	public String toString()
	{
		return "DAT " + placeCode + "," + buildingCode + "," + xPos + "," + yPos + "," + zPos + "," 
				+ rotation + "," + activationFlag + "," + num0 + "," + num1 + "," + deactivationFlag 
				+ "," + populationCount + "," + textCode + "," + num2 + ";\r\n";
	}
	 boolean fitsFilter(int xMin, int xMax, int zMin, int zMax, boolean AllOutside) 
	 {
		if(AllOutside)
		{
			if(!(xMin>xPos||xPos>xMax||zMin>zPos||zPos>zMax))
			{
				return false;
			}
		}
		else
		{
			if(!(xMin<xPos&&xPos<xMax))
			{
				return false;
			}
			if(!(zMin<zPos&&zPos<zMax))
			{
				return false;
			}
		}
		return true;
	}
}
