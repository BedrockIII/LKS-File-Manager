package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import MenuDBGUI.KingdomPlanAreaSelectorGUI;
import PCKGManager.PCKGManager;

@SuppressWarnings("serial")
public class Generic extends JPanel
{
	protected byte[] data;
	protected String name = "";
	GenericFileInfoGUI gui = null;
	protected Generic()
	{
		
	}
	public Generic(PCKGManager pac, int parentY, int parentX, int displacement)
	{
		data = pac.getFile(displacement);
		name = pac.getName(displacement);
		makeGUI(parentX);
	}
	public Generic(String name, byte[] data, int padding)
	{
		this.name = name;
		this.data = data;
		makeGUI(padding);
	}
	public Generic(String name, int padding) 
	{
		this.name = name;
		this.data = new byte[0];
		makeGUI(padding);
	}
	private void makeGUI(int padding) 
	{
		gui = makeInfoGUI(name, data);
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
		setLayout(new GridBagLayout());
		setMaximumSize(new Dimension(100000,GUI.assetHeight));
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
		spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
		add(spacer);
		GridBagConstraints constraints = new GridBagConstraints();  
		constraints.weightx = 1.0;
		JLabel fileName = new JLabel(name, SwingConstants.LEFT);
		fileName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
		add(fileName, constraints);
		
		addActions();
	}
	public void addActions()
	{
		JPopupMenu actions = new JPopupMenu();
		
		JMenuItem export = new JMenuItem("Export File");
		export.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setSelectedFile(new File(name));
			if(chooseFile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					Files.write(chooseFile.getSelectedFile().toPath(),data);
				}
				catch(IOException i)
				{
					System.out.println("Failed to Export Generic File");
					i.printStackTrace();
				}
				System.out.println("Exported Generic File");
			}
		});
		actions.add(export);
		
		JMenuItem replace = new JMenuItem("Replace File");
		replace.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
			//chooseFile.setFileFilter(new FileNameExtensionFilter("Collision File", "col"));
			
			chooseFile.showOpenDialog(null);
			int num = chooseFile.showSaveDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					data = Files.readAllBytes(chooseFile.getSelectedFile().toPath());
				} catch (IOException i) 
				{
					i.printStackTrace();
					System.out.println("Failed to Import Generic File");
				}
				System.out.println("Imported Generic File");
			}
		});
		actions.add(replace);
		
		add(actions);
		
		addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        if (e.isPopupTrigger()) showMenu(e);
		        else if(SwingUtilities.isLeftMouseButton(e))
		        {
		        	GUI.deselectAll();
		        	setBackground(GUI.selectedColor);
		        	GUI.setFileInfo(gui);
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
	protected GenericFileInfoGUI makeInfoGUI(String name, byte[] data)
	{
		String type = bFM.Utils.getFileType(name, data);
		if(type.equals("KingdomPlanDB"))
		{
			return new KingdomPlanAreaSelectorGUI(data);
		}
		else if(type.equals("Package"))
		{
			throw new IllegalArgumentException();
		}
		return new GenericFileInfoGUI(data);
	}
	public int getHeight()
	{
		return GUI.assetHeight;
	}
	public byte[] getBytes() 
	{
		return gui.getBytes();
	}
	public void deselect()
	{
		setBackground(Color.white);
	}
}
