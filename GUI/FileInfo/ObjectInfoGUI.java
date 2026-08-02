package GUI.FileInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileList.Resources.MOPlacementListGUI.GroupTypesListGUI.GroupsListGUI.GroupListGUI.ObjectListGUI;
import ResourceManagers.MSDBManager.MSDBManager;
import ResourceManagers.MSDBManager.Placement.MobObject;
import bFM.Settings;

@SuppressWarnings("serial")
public class ObjectInfoGUI extends GenericFileInfoGUI 
{
	MSDBManager parent =  null;
	MobObject object = null;
	ObjectListGUI objectListGUI= null;
	protected LabeledInputBox mobLabel = null;
	protected JTextField xOffset; //First 2 Bytes //these first 3 MAY be the offsets for the target points, like where onii men throw pots
	protected JTextField yOffset; //Next 2 Bytes
	protected JTextField zOffset; //Next 2 Bytes
	protected JTextField rotation; //Next 2 Bytes//probably the actual rotation
	protected JTextField num4; //Next 2 Bytes
	protected JTextField mobModNumber; //Next 2 Bytes
	protected JTextField numberOfSubObjects; //Next 2 Bytes
	protected JTextField RadiusOfView; //Next 2 Bytes, normally same as num4
	protected JTextField DegreesOfView; //Next 2 Bytes
	protected JTextField AiCode; //Next 2 Bytes
	protected JTextField deathEffects; //Next 2 Bytes, 1 or 0. 1 = kill rest of group, 0 = not do that
	protected JTextField enemyDrop; //Spawn this mob Code when dead
	protected JTextField itemDrop; 
	public ObjectInfoGUI(MobObject object, MSDBManager parent, ObjectListGUI objectListGUI) 
	{
		this.objectListGUI = objectListGUI;
		this.parent = parent;
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		xOffset = bFM.GUIUtils.createFloatTextField(object.getxOffset(), object::setxOffset);
		yOffset = bFM.GUIUtils.createFloatTextField(object.getyOffset(), object::setyOffset);
		zOffset = bFM.GUIUtils.createFloatTextField(object.getzOffset(), object::setzOffset);
		rotation = bFM.GUIUtils.createFloatTextField(object.getRotation(), object::setRotation);
		num4 = bFM.GUIUtils.createFloatTextField(object.getNum4(), object::setNum4);
		mobModNumber = bFM.GUIUtils.createIntTextField(object.getMobModNumber(), this::setModCode);
		mobLabel =  new LabeledInputBox("Mod Number: ",  mobModNumber);
		setModCode(object.getModCode());
		numberOfSubObjects = bFM.GUIUtils.createIntTextField(object.getNumberOfSubObjects(), object::setNumberOfSubObjects);
		RadiusOfView = bFM.GUIUtils.createFloatTextField(object.getRadiusOfView(), object::setRadiusOfView);
		DegreesOfView = bFM.GUIUtils.createFloatTextField(object.getDegreesOfView(), object::setDegreesOfView);
		AiCode = bFM.GUIUtils.createIntTextField(object.getAiCode(), object::setAiCode);
		deathEffects = bFM.GUIUtils.createIntTextField(object.getDeathEffects(), object::setDeathEffects);
		enemyDrop = bFM.GUIUtils.createIntTextField(object.getEnemyDrop(), object::setEnemyDrop);
		itemDrop = bFM.GUIUtils.createIntTextField(object.getItemDrop(), object::setItemDrop);
	}
	private void setModCode(int code)
	{
		String name = "Mod Code: (" + parent.getModCodeByName(code) + ")";
		mobLabel.replaceText(name);
		object.setMobModNumber(code);
		objectListGUI.update();
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(mobLabel, layout);
		add(new LabeledInputBox("AI Code: ",  AiCode), layout);
		add(new LabeledInputBox("Death Type: ",  deathEffects), layout);
		add(new LabeledInputBox("X Offset: ",  xOffset), layout);
		add(new LabeledInputBox("Y Offset: ",  yOffset), layout);
		add(new LabeledInputBox("Z Offset: ",  zOffset), layout);
		add(new LabeledInputBox("Rotation: ",  rotation), layout);
		add(new LabeledInputBox("Num4: ",  num4), layout);
		add(new LabeledInputBox("Sub Object Count: ",  numberOfSubObjects), layout);
		add(new LabeledInputBox("Radius of Viewcone: ",  RadiusOfView), layout);
		add(new LabeledInputBox("Degrees of Viewcone: ",  DegreesOfView), layout);
		
		add(new LabeledInputBox("Enemy Drop: ",  enemyDrop), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Item Drop: ",  itemDrop), layout);
		
	}
}