package CommonFiles.Collision;

public class CollisionList {

	
	
	
	int listPos = header.getListPos();
	bFM.Utils.DebugPrint("List Position: " + listPos);
	treePos = data.getInt(listPos);
	//bFM.Utils.DebugPrint("Tree Position Position: " + listPos);
	if(treePos > 0)
	{
		bFM.Utils.DebugPrint("Tree Position: " + treePos);
		if(treePos>0)
		{
			tree = new CollisionTree(data, treePos, 0);
		}
	}
}
