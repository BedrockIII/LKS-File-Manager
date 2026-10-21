package GUI.FileInfo.MenuDB.MovieBook;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import GUI.LabeledInputBox;
import GUI.FileInfo.GenericFileInfoGUI;
import SystemDataManagers.MenuDB.Books.MovieBook.MovieBook;
import bFM.Settings;

@SuppressWarnings("serial")
public class MovieInfoGUI extends GenericFileInfoGUI
{
	MovieBook file;
	JTextField Name = null;
	JTextField PreText = null;
	JTextField PostText = null;
	JTextField Description = null;
	JTextField Image = null;
	JTextField Movie = null;
	public MovieInfoGUI(MovieBook file) 
	{
		this.file = file;
		makeGUI();
		addGUI();
	}
	private void addGUI() 
	{
		GridBagConstraints layout = Settings.getDefaultConstraints();
		removeAll();
		add(new LabeledInputBox("Name: ", Name), layout);
		add(new LabeledInputBox("Pre Movie Verde Text: ", PreText), layout);
		add(new LabeledInputBox("Post Movie Verde Text: ", PostText), layout);
		add(new LabeledInputBox("Description: ", Description), layout);
		add(new LabeledInputBox("Image: ", Image), layout);
		layout.weighty = 1.0;
		add(new LabeledInputBox("Movie: ", Movie), layout);
	}
	private void makeGUI()
	{
		Name = bFM.GUIUtils.createNameTextField(file.getName(), file::setName);
		PreText = bFM.GUIUtils.createStringTextField(file.getPreText(), file::setPreText);
		PostText = bFM.GUIUtils.createStringTextField(file.getPostText(), file::setPostText);
		Description = bFM.GUIUtils.createStringTextField(file.getDescription(), file::setDescription);
		Image = bFM.GUIUtils.createStringTextField(file.getImage(), file::setImage);
		Movie = bFM.GUIUtils.createStringTextField(file.getMovie(), file::setMovie);
	}
}
