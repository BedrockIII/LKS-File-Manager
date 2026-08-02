package ResourceManagers.MapDatabaseManager;

public class exteriorPlace 
{
	//AreaCodes
	public final static int GENERAL_AC = 0;
	public final static int CASTLE_TOWN_AC = 1;
	public final static int GRASSLAND_TOWN_AC = 2;
	public final static int FARMLAND_AC = 3;
	public final static int STONE_CITY_AC = 4;
	public final static int GOURMET_TOWN_AC = 5;
	public final static int SOLDIER_TOWN_AC = 6;
	public final static int ROYAL_CITY_AC = 7;
	public final static int GLAMOUR_TOWN_AC = 8;
	public final static int MINER_TOWN_AC = 9;
	public final static int MAGIC_LAND_AC = 10;
	//Construction Level
	public final static int STARTS_UNBUILT = 1;
	public final static int STARTS_BUILT = 2;
	//Rotation
	public final static int ROTATED_0_DEGREES = 0;
	public final static int ROTATED_90_DEGREES = 1;
	public final static int ROTATED_180_DEGREES = 2;
	public final static int ROTATED_270_DEGREES = 3;
	//Variables
	int placeCode = -1;
	int buildingCode = -1;
	int xPos = 9999;
	int yPos = 9999;
	int zPos = 9999;
	int rotation = -1;//0 to 3 representing 90 degree turns
	int activationFlag = -1;
	int constructedFlag = -1;
	int constructionLevel = 2;
	int deactivationFlag = -1;
	int areaCode = 0;
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
		constructedFlag = Integer.valueOf(line[7]).intValue();
		constructionLevel = Integer.valueOf(line[8]).intValue();
		deactivationFlag = Integer.valueOf(line[9]).intValue();
		areaCode = Integer.valueOf(line[10]).intValue();
		textCode = Integer.valueOf(line[11]).intValue();
		num2 = Integer.valueOf(line[12]).intValue();
	}
	public String toString()
	{
		return "DAT " + placeCode + "," + buildingCode + "," + xPos + "," + yPos + "," + zPos + "," 
				+ rotation + "," + activationFlag + "," + constructedFlag + "," + constructionLevel + "," + deactivationFlag 
				+ "," + areaCode + "," + textCode + "," + num2 + ";\r\n";
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
	public int getPlaceCode() {
		return placeCode;
	}
	public void setPlaceCode(int placeCode) {
		this.placeCode = placeCode;
	}
	public int getBuildingCode() {
		return buildingCode;
	}
	public void setBuildingCode(int buildingCode) {
		this.buildingCode = buildingCode;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getzPos() {
		return zPos;
	}
	public void setzPos(int zPos) {
		this.zPos = zPos;
	}
	public int getRotation() {
		return rotation;
	}
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	public int getActivationFlag() {
		return activationFlag;
	}
	public void setActivationFlag(int activationFlag) {
		this.activationFlag = activationFlag;
	}
	public int getConstructedFlag() {
		return constructedFlag;
	}
	public void setConstructedFlag(int constructedFlag) {
		this.constructedFlag = constructedFlag;
	}
	public int getConstructionLevel() {
		return constructionLevel;
	}
	public void setConstructionLevel(int constructionLevel) {
		this.constructionLevel = constructionLevel;
	}
	public int getDeactivationFlag() {
		return deactivationFlag;
	}
	public void setDeactivationFlag(int deactivationFlag) {
		this.deactivationFlag = deactivationFlag;
	}
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public int getTextCode() {
		return textCode;
	}
	public void setTextCode(int textCode) {
		this.textCode = textCode;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
}
