package mapDBManager;

import java.util.ArrayList;

public class BuildingResource 
{
	//DAT
	int buildingCode = -1;
	int interactionType = 1;
	int num0 = -1;
	int width = 0;
	int depth = 0;
	int linkCode1 = -1;
	int linkCode2 = -1;
	//DAT2
	int trainCode = -1;
	int num1 = -1;
	int num2 = -1;
	int speakCode = -1;
	//MDL
	int num3 = -1;
	int num4 = -1;
	String filePath = "Null Path";
	String name = "Null Name";
	//Optional ANM
	ArrayList<String> Animations = new ArrayList<String>();
	//MS
	int mobCode = -1;
	int secondMobCode = -1;//Maybe???
	int mobCodeHP = 1;//NEEDS A SPACE 0x20 AFTER IT WHEN WRITING
	int num5 = -1;
	int num6 = -1;
}
