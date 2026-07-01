package GUI;

import javax.swing.JMenuBar;

import bFM.Settings;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar
{
	public MenuBar()
	{
		setBorderPainted(true);
		FileDropDownBox fileMenu = new FileDropDownBox();
		fileMenu.setMinimumSize(Settings.buttonSize);
		add(fileMenu);
		ToolDropDownBox toolMenu = new ToolDropDownBox();
		toolMenu.setMinimumSize(Settings.buttonSize);
		add(toolMenu);
		SettingsDropDownBox settingsMenu = new SettingsDropDownBox();
		settingsMenu.setMinimumSize(Settings.buttonSize);
		add(settingsMenu);
	}
}
