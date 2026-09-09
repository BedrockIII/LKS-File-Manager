package GUI.FileInfo.MissionDataBase;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

import GUI.CollapseablePanel;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import ResourceManagers.MSDBManager.Definition.MobAttackList.MobAttack;
import bFM.GUIUtils;
import bFM.Settings;

@SuppressWarnings("serial")
public class MobAttackInfoGUI extends GenericFileInfoGUI 
{
	MobAttack object = null;
	CollapseablePanel PlacementInfo;
	JTextField name;
	JTextField type;
	JTextField attackCode;
	JTextField num0;
	JTextField num1;
	JTextField num2;
	JTextField num3;
	JTextField num4;
	JTextField num5;
	JTextField num6;
	JTextField num7;
	JTextField num8;
	JTextField num9;
	JTextField num10;
	JTextField num11;
	JTextField num13;
	JTextField num14;
	JTextField num15;
	JTextField num16;
	JTextField num17;
	JTextField num18;
	JTextField num19;
	JTextField num20;
	JTextField num21;
	//Attack ELement
	JTextField soundEffect;
	JTextField damage;
	JTextField atknum2;
	JTextField hitType;//Change to Dropdown
	JTextField ElementalType;//Change to Dropdown
	JTextField atknum5;
	JTextField atknum6;
	JTextField atknum7;
	JTextField atknum8;
	JTextField HitEffect;//Change to checkboxes
	JTextField atknum10;
	public MobAttackInfoGUI(MobAttack data) 
	{
		object = data;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		name = GUIUtils.createNameTextField(object.getName(), object::setName);
		type = GUIUtils.createStringTextField(object.getAttackType(), object::setAttackType);
		soundEffect = GUIUtils.createStringTextField(object.getSoundEffect(), object::setSoundEffect);
		attackCode = GUIUtils.createIntTextField(object.getAttackCode(), object::setAttackCode);
		num1 = GUIUtils.createFloatTextField(object.getNum1(), object::setNum1);
		num2 = GUIUtils.createFloatTextField(object.getNum2(), object::setNum2);
		num3 = GUIUtils.createFloatTextField(object.getNum3(), object::setNum3);
		num4 = GUIUtils.createFloatTextField(object.getNum4(), object::setNum4);
		num5 = GUIUtils.createFloatTextField(object.getNum5(), object::setNum5);
		num6 = GUIUtils.createFloatTextField(object.getNum6(), object::setNum6);
		num7 = GUIUtils.createFloatTextField(object.getNum7(), object::setNum7);
		num8 = GUIUtils.createFloatTextField(object.getNum8(), object::setNum8);
		num9 = GUIUtils.createFloatTextField(object.getNum9(), object::setNum9);
		num10 = GUIUtils.createFloatTextField(object.getNum10(), object::setNum10);
		num11 = GUIUtils.createFloatTextField(object.getNum11(), object::setNum11);
		num13 = GUIUtils.createIntTextField(object.getNum13(), object::setNum13);
		num14 = GUIUtils.createIntTextField(object.getNum14(), object::setNum14);
		num15 = GUIUtils.createIntTextField(object.getNum15(), object::setNum15);
		num16 = GUIUtils.createIntTextField(object.getNum16(), object::setNum16);
		num17 = GUIUtils.createIntTextField(object.getNum17(), object::setNum17);
		num18 = GUIUtils.createIntTextField(object.getNum18(), object::setNum18);
		num19 = GUIUtils.createIntTextField(object.getNum19(), object::setNum19);
		num20 = GUIUtils.createIntTextField(object.getNum20(), object::setNum20);
		num21 = GUIUtils.createIntTextField(object.getNum21(), object::setNum21);
		damage = GUIUtils.createIntTextField(object.getDamage(), object::setDamage);
		atknum2 = GUIUtils.createIntTextField(object.getAtkNum2(), object::setAtkNum2);
		hitType = GUIUtils.createIntTextField(object.getHitTypes(), object::setHitTypes);
		ElementalType = GUIUtils.createIntTextField(object.getElementalType(), object::setElementalType);
		atknum5 = GUIUtils.createIntTextField(object.getAtkNum5(), object::setAtkNum5);
		atknum6 = GUIUtils.createFloatTextField(object.getAtkNum6(), object::setAtkNum6);
		atknum7 = GUIUtils.createIntTextField(object.getAtkNum7(), object::setAtkNum7);
		atknum8 = GUIUtils.createIntTextField(object.getAtkNum8(), object::setAtkNum8);
		HitEffect = GUIUtils.createIntTextField(object.getHitEffect(), object::setHitEffect);
		atknum10 = GUIUtils.createIntTextField(object.getAtkNum10(), object::setAtkNum10);
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = Settings.getDefaultConstraints();
		add(new LabeledInputBox("Name: ",  name), layout);
		add(new LabeledInputBox("Type: ",  type), layout);
		add(new LabeledInputBox("Sound Effect: ",  soundEffect), layout);
		add(new LabeledInputBox("Attack Code: ",  attackCode), layout);
		add(new LabeledInputBox("Damage: ",  damage), layout);
		add(new LabeledInputBox("Number 1: ",  num1), layout);
		add(new LabeledInputBox("Number 2: ",  num2), layout);
		add(new LabeledInputBox("Number 3: ",  num3), layout);
		add(new LabeledInputBox("Number 4: ",  num4), layout);
		add(new LabeledInputBox("Number 5: ",  num5), layout);
		add(new LabeledInputBox("Number 6: ",  num6), layout);
		add(new LabeledInputBox("Number 7: ",  num7), layout);
		add(new LabeledInputBox("Number 8: ",  num8), layout);
		add(new LabeledInputBox("Number 9: ",  num9), layout);
		add(new LabeledInputBox("Number 10: ",  num10), layout);
		add(new LabeledInputBox("Number 11: ",  num11), layout);
		add(new LabeledInputBox("Number 13: ",  num13), layout);
		add(new LabeledInputBox("Number 14: ",  num14), layout);
		add(new LabeledInputBox("Number 15: ",  num15), layout);
		add(new LabeledInputBox("Number 16: ",  num16), layout);
		add(new LabeledInputBox("Number 17: ",  num17), layout);
		add(new LabeledInputBox("Number 18: ",  num18), layout);
		add(new LabeledInputBox("Number 19: ",  num19), layout);
		add(new LabeledInputBox("Number 20: ",  num20), layout);
		add(new LabeledInputBox("Number 21: ",  num21), layout);
		add(new LabeledInputBox("Element Number 2: ",  atknum2), layout);
		add(new LabeledInputBox("Hit Type: ",  hitType), layout);
		add(new LabeledInputBox("Elemental Type: ",  ElementalType), layout);
		add(new LabeledInputBox("Element Number 5: ",  atknum5), layout);
		add(new LabeledInputBox("Element Number 6: ",  atknum6), layout);
		add(new LabeledInputBox("Element Number 7: ",  atknum7), layout);
		add(new LabeledInputBox("Element Number 8: ",  atknum8), layout);
		add(new LabeledInputBox("Hit Effect: ",  HitEffect), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Element Number 10: ",  atknum10), layout);
	}
}