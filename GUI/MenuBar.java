package GUI;

import java.awt.Dimension;

import javax.swing.JMenuBar;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar
{
	public MenuBar()
	{
		setBorderPainted(true);
		setPreferredSize(new Dimension(GUI.rowWidth, GUI.assetHeight));
		setMinimumSize(new Dimension(GUI.rowWidth, GUI.assetHeight));
		FileDropDownBox fileMenu = new FileDropDownBox();
		add(fileMenu);
	}
}
