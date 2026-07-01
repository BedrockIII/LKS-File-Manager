package GUI.FileInfo;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import GUI.LabeledInputBox;
import ResourceManagers.CharacterDatabaseManager.CharacterDataBaseManager;

@SuppressWarnings("serial")
public class CharacterDataBaseInfoGUI extends GenericFileInfoGUI 
{
	CharacterDataBaseManager object;
	LabeledInputBox FaceCount;
	LabeledInputBox BodyCount;
	LabeledInputBox JoinCount;
	LabeledInputBox IndexCount;
	LabeledInputBox CoordinateCount;
	LabeledInputBox JobCount;
	LabeledInputBox AnimationCount;
	LabeledInputBox PatternCount;
	public CharacterDataBaseInfoGUI(CharacterDataBaseManager object) 
	{
		this.object = object;
		makeGUI();
		addGUI();
	}
	private void makeGUI()
	{
		BodyCount = new LabeledInputBox("Body Count", new JLabel("" + object.getCharacterResource().getAmountOfBodies()));
		FaceCount = new LabeledInputBox("Face Count", new JLabel("" + object.getCharacterResource().getAmountOfFaces()));
		JoinCount = new LabeledInputBox("Join Count", new JLabel("" + object.getCharacterJoin().getAmountOfJoins()));
		IndexCount = new LabeledInputBox("Index Count", new JLabel("" + object.getCharacterIndex().getAmountOfIndicies()));
		CoordinateCount = new LabeledInputBox("Coordinate Count", new JLabel("" + object.getCharacterSoundEffect().getAmountOfCoordinates()));
		AnimationCount = new LabeledInputBox("Animations Count", new JLabel("" + object.getTextureAnimations().getAnimations().getAnimations().size()));
		PatternCount = new LabeledInputBox("Patterns Count", new JLabel("" + object.getTextureAnimations().getPatterns().getPatterns().size()));
		JobCount = new LabeledInputBox("Job Count", new JLabel("" + object.getJobChangePriceList().getAmountOfJobs()));
	}
	private void addGUI()
	{
		removeAll();
		setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weighty = 0;
		layout.weightx = 1.0;
		add(BodyCount, layout);
		add(FaceCount, layout);
		add(JoinCount, layout);
		add(IndexCount, layout);
		add(CoordinateCount, layout);
		add(AnimationCount, layout);
		add(PatternCount, layout);
		
		layout.weighty = 1.0;
		add(JobCount, layout);
	}
	public void update()
	{
		BodyCount.replaceComponent(new JLabel("" + object.getCharacterResource().getAmountOfBodies()));
		FaceCount.replaceComponent(new JLabel("" + object.getCharacterResource().getAmountOfFaces()));
		JoinCount.replaceComponent(new JLabel("" + object.getCharacterJoin().getAmountOfJoins()));
		IndexCount.replaceComponent(new JLabel("" + object.getCharacterIndex().getAmountOfIndicies()));
		CoordinateCount.replaceComponent(new JLabel("" + object.getCharacterSoundEffect().getAmountOfCoordinates()));
		AnimationCount.replaceComponent(new JLabel("" + object.getTextureAnimations().getAnimations().getAnimations().size()));
		PatternCount.replaceComponent(new JLabel("" + object.getTextureAnimations().getPatterns().getPatterns().size()));
		JobCount.replaceComponent(new JLabel("" + object.getJobChangePriceList().getAmountOfJobs()));
		addGUI();
		for(Component c : this.getComponents())
		{
			if(c instanceof LabeledInputBox)
			{
				((LabeledInputBox) c).update();
			}
		}
	}
}
