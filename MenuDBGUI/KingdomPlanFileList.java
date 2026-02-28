package MenuDBGUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import GUI.Generic;
import SystemDataManagers.KingdomPlanManager;

@SuppressWarnings("serial")
public class KingdomPlanFileList extends Generic
{
	KingdomPlanAreaSelectorGUI gui = null;
	public KingdomPlanFileList(byte[] data, int padding)
	{
		super("Kingdom Plan Config", padding);
		//setBackground(color);
		gui = (KingdomPlanAreaSelectorGUI)makeInfoGUI("KingdomPlan.bin",data);
		//System.out.println("Made");
	}
	public void addActions()
	{
		JPopupMenu actions = new JPopupMenu();
		
		JMenuItem export = new JMenuItem("Export File");
		export.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setSelectedFile(new File("Kingdom Plan Config.txt"));
			if(chooseFile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					Files.write(chooseFile.getSelectedFile().toPath(),gui.getKPData());
				}
				catch(IOException i)
				{
					System.out.println("Failed to Export Kingdom Plan Config File");
					i.printStackTrace();
				}
				System.out.println("Exported Kingdom Plan Config File");
			}
		});
		actions.add(export);
		
		JMenuItem replace = new JMenuItem("Replace File");
		replace.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//chooseFile.setFileFilter(new FileNameExtensionFilter("Collision File", "col"));
			
			int num = chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					data = new KingdomPlanManager(Files.readAllLines(chooseFile.getSelectedFile().toPath())).toBytes();
					gui = new KingdomPlanAreaSelectorGUI(data);
				} catch (IOException i) 
				{
					i.printStackTrace();
					System.out.println("Failed to Import Kingdom Plan Config File");
				}
				System.out.println("Imported Kingdom Plan Config File");
			}
		});
		actions.add(replace);
		
		add(actions);
		
		addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        if (e.isPopupTrigger()) showMenu(e);
		        else if(SwingUtilities.isLeftMouseButton(e))
		        {
		        	//System.out.println("hit");
		        	GUI.GUI.setFileInfo(gui);
		        	GUI.GUI.deselectAll();
		        	setBackground(GUI.GUI.selectedColor);
		        }
		    }
		    public void mouseReleased(MouseEvent e) {
		        if (e.isPopupTrigger()) showMenu(e);
		    }
		    private void showMenu(MouseEvent e) {
		    	actions.show(e.getComponent(), e.getX(), e.getY());
		    }
		});
	}
}
