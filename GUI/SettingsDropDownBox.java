package GUI;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import GUI.PopupWindows.SettingsPopups.PathsWindow;
import bFM.Settings;

@SuppressWarnings("serial")
public class SettingsDropDownBox extends JMenu
{
	public SettingsDropDownBox()
	{
		super("Settings");
		setPreferredSize(Settings.buttonSize);
		setMinimumSize(Settings.buttonSize);
		JMenuItem PathSettings = new JMenuItem("Paths");
		PathSettings.addActionListener(e -> {
			new PathsWindow();
		});
		add(PathSettings);
	}
}
