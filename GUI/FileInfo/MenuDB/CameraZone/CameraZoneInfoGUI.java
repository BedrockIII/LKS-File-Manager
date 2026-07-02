package GUI.FileInfo.MenuDB.CameraZone;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import CameraData.CameraZone;
import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import bFM.Settings;

@SuppressWarnings("serial")
public class CameraZoneInfoGUI extends GenericFileInfoGUI
{
	CameraZone file;
	JTextField Name = null;
	JTextField Num1 = null;
	JTextField XPos = null;
	JTextField YPos = null;
	JTextField ZPos = null;
	JTextField Size = null;
	JTextField Num6 = null;
	JTextField Num7 = null;
	JTextField CameraFixedXPos = null;
	JTextField CameraFixedYPos = null;
	JTextField CameraFixedZPos = null;
	JTextField CameraFocusXPos = null;
	JTextField CameraFocusYPos = null;
	JTextField CameraFocusZPos = null;
	JTextField HeightAngle = null;
	JTextField RotationCenterAngle = null;
	JTextField Num16 = null;
	JTextField CameraDistanceFromFocus = null;
	JTextField Zoom = null;
	JTextField Num19 = null;
	JTextField Num20 = null;
	JTextField Num21 = null;
	JTextField Num22 = null;
	JTextField FadeOutDistance = null;
	JTextField Num24 = null;
	JTextField Num25 = null;
	JTextField Num26 = null;
	JTextField ZoneEnableFlag = null;
	JTextField Num28 = null;
	JTextField Num29 = null;
	JTextField Num30 = null;
	JTextField ZoneDisableFlag = null;
	JTextField Num32 = null;
	JTextField ZoneIndex = null;
	JTextField Num34 = null;
	JTextField Num35 = null;
	JTextField RotationRange = null;
	public CameraZoneInfoGUI(CameraZone file) 
	{
		this.file = file;
		makeGUI();
		addGUI();
	}
	private void addGUI() 
	{
		GridBagConstraints layout = Settings.getDefaultConstraints();
		removeAll();
		add(new LabeledInputBox("Internal Name: ", Name), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 1: ", Num1), layout);
		add(new LabeledInputBox("Camera Zone X Position: ", XPos), layout);
		add(new LabeledInputBox("Camera Zone Y Position: ", YPos), layout);
		add(new LabeledInputBox("Camera Zone Z Position: ", ZPos), layout);
		add(new LabeledInputBox("Camera Zone Size: ", Size), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 6: ", Num6), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 7: ", Num7), layout);
		add(new LabeledInputBox("Camera Fixed X Position: ", CameraFixedXPos), layout);
		add(new LabeledInputBox("Camera Fixed Y Position: ", CameraFixedYPos), layout);
		add(new LabeledInputBox("Camera Fixed Z Position: ", CameraFixedZPos), layout);
		add(new LabeledInputBox("Camera Focus X Position: ", CameraFocusXPos), layout);
		add(new LabeledInputBox("Camera Focus Y Position: ", CameraFocusYPos), layout);
		add(new LabeledInputBox("Camera Focus Z Position: ", CameraFocusZPos), layout);
		add(new LabeledInputBox("Camera Height Angle: ", HeightAngle), layout);
		add(new LabeledInputBox("Camera Rotation Center Angle: ", RotationCenterAngle), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 16: ", Num16), layout);
		add(new LabeledInputBox("Camera Distance From Focus: ", CameraDistanceFromFocus), layout);
		add(new LabeledInputBox("Camera Zoom: ", Zoom), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 19: ", Num19), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 20: ", Num20), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 21: ", Num21), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 22: ", Num22), layout);
		add(new LabeledInputBox("Camera Fade Out Distance: ", FadeOutDistance), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 24: ", Num24), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 25: ", Num25), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 26: ", Num26), layout);
		add(new LabeledInputBox("Camera Zone Enable Flag: ", ZoneEnableFlag), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 28: ", Num28), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 29: ", Num29), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 30: ", Num30), layout);
		add(new LabeledInputBox("Camera Zone Disable Flag: ", ZoneDisableFlag), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 32: ", Num32), layout);
		add(new LabeledInputBox("Camera Zone Index: ", ZoneIndex), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 34: ", Num34), layout);
		add(new LabeledInputBox("Camera Zone Unknown Number 35: ", Num35), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Camera Rotation Range: ", RotationRange), layout);
	}
	private void makeGUI()
	{
		Name = bFM.Utils.createNameTextField(file.getName(), file::setName);
		Num1 = bFM.Utils.createIntTextField(file.getNum1(), file::setNum1);
		XPos = bFM.Utils.createFloatTextField(file.getXPos(), file::setXPos);
		YPos = bFM.Utils.createFloatTextField(file.getYPos(), file::setYPos);
		ZPos = bFM.Utils.createFloatTextField(file.getZPos(), file::setZPos);
		Size = bFM.Utils.createFloatTextField(file.getZoneSize(), file::setSize);
		Num6 = bFM.Utils.createIntTextField(file.getNum6(), file::setNum6);
		Num7 = bFM.Utils.createIntTextField(file.getNum7(), file::setNum7);
		CameraFixedXPos = bFM.Utils.createFloatTextField(file.getCameraFixedXPos(), file::setCameraFixedXPos);
		CameraFixedYPos = bFM.Utils.createFloatTextField(file.getCameraFixedYPos(), file::setCameraFixedYPos);
		CameraFixedZPos = bFM.Utils.createFloatTextField(file.getCameraFixedZPos(), file::setCameraFixedZPos);
		CameraFocusXPos = bFM.Utils.createFloatTextField(file.getCameraFocusXPos(), file::setCameraFocusXPos);
		CameraFocusYPos = bFM.Utils.createFloatTextField(file.getCameraFocusYPos(), file::setCameraFocusYPos);
		CameraFocusZPos = bFM.Utils.createFloatTextField(file.getCameraFocusZPos(), file::setCameraFocusZPos);
		HeightAngle = bFM.Utils.createFloatTextField(file.getHeightAngle(), file::setHeightAngle);
		RotationCenterAngle = bFM.Utils.createFloatTextField(file.getRotationCenterAngle(), file::setRotationCenterAngle);
		Num16 = bFM.Utils.createIntTextField(file.getNum16(), file::setNum16);
		CameraDistanceFromFocus = bFM.Utils.createFloatTextField(file.getCameraDistanceFromFocus(), file::setCameraDistanceFromFocus);
		Zoom = bFM.Utils.createFloatTextField(file.getZoom(), file::setZoom);
		Num19 = bFM.Utils.createIntTextField(file.getNum19(), file::setNum19);
		Num20 = bFM.Utils.createIntTextField(file.getNum20(), file::setNum20);
		Num21 = bFM.Utils.createIntTextField(file.getNum21(), file::setNum21);
		Num22 = bFM.Utils.createIntTextField(file.getNum22(), file::setNum22);
		FadeOutDistance = bFM.Utils.createFloatTextField(file.getFadeOutDistance(), file::setFadeOutDistance);
		Num24 = bFM.Utils.createIntTextField(file.getNum24(), file::setNum24);
		Num25 = bFM.Utils.createIntTextField(file.getNum25(), file::setNum25);
		Num26 = bFM.Utils.createIntTextField(file.getNum26(), file::setNum26);
		ZoneEnableFlag = bFM.Utils.createIntTextField(file.getZoneEnableFlag(), file::setZoneEnableFlag);
		Num28 = bFM.Utils.createIntTextField(file.getNum28(), file::setNum28);
		Num29 = bFM.Utils.createIntTextField(file.getNum29(), file::setNum29);
		Num30 = bFM.Utils.createIntTextField(file.getNum30(), file::setNum30);
		ZoneDisableFlag = bFM.Utils.createIntTextField(file.getZoneDisableFlag(), file::setZoneDisableFlag);
		Num32 = bFM.Utils.createIntTextField(file.getNum32(), file::setNum32);
		ZoneIndex = bFM.Utils.createIntTextField(file.getZoneIndex(), file::setZoneIndex);
		Num34 = bFM.Utils.createIntTextField(file.getNum34(), file::setNum34);
		Num35 = bFM.Utils.createIntTextField(file.getNum35(), file::setNum35);
		RotationRange = bFM.Utils.createIntTextField(file.getRotationRange(), file::setRotationRange);
	}
}
