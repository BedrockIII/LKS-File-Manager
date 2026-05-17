package GUI.FileList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import GUI.GUI;
import GUI.FileInfo.GenericFileInfoGUI;
import GUI.FileInfo.MenuDB.KingdomPlan.KingdomPlanAreaSelectorGUI;
import PCKGManager.OpenedFile;
import PCKGManager.PCKGManager;

@SuppressWarnings("serial")
public class Generic extends JPanel
{
	protected OpenedFile file = null;
	protected GenericFileInfoGUI gui = null;
	JLabel fileName = new JLabel();
	protected Generic()
	{
		
	}
	public Generic(PCKGManager pac, int parentX, int index)
	{
		file = pac.getPackedFile(index);
		initializeGUI(parentX);
	}
	public Generic(String name, byte[] data, int padding)
	{
		file = new OpenedFile(name, data);
		initializeGUI(padding);
	}
	public Generic(OpenedFile file, int padding)
	{
		this.file = file;
		initializeGUI(padding);
	}
	public Generic(String name, int padding) 
	{
		file = new OpenedFile(name, new byte[0]);
		initializeGUI(padding);
	}
	private void initializeGUI(int padding) 
	{
		//setBorder(BorderFactory.createLineBorder(Color.GREEN));
		GridBagConstraints constraints = new GridBagConstraints();  
		constraints.weightx = 0.0;
		constraints.anchor = GridBagConstraints.NORTHWEST;
		gui = makeInfoGUI(file);
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		//setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
		setLayout(new GridBagLayout());
		//setMaximumSize(new Dimension(100000,GUI.assetHeight));
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
		spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
		add(spacer, constraints);
		constraints.weightx = 1.0;
		fileName = new JLabel(file.getName(), SwingConstants.LEFT);
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
			chooseFile.setSelectedFile(new File(file.getName()));
			if(chooseFile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					Files.write(chooseFile.getSelectedFile().toPath(),file.getData());
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
			
			int num =chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				try 
				{
					file.setData(Files.readAllBytes(chooseFile.getSelectedFile().toPath()));
					//System.out.println(data.length);
					gui.updateGUI(file.getData());
					file.setName(chooseFile.getSelectedFile().toPath().getFileName().toString());
					fileName.setText(file.getName());
					GUI.update();
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
	protected GenericFileInfoGUI makeInfoGUI(OpenedFile file)
	{
		String type = bFM.Utils.getFileType(file.getName(), file.getData());
		if(type.equals("KingdomPlanDB"))
		{
			return new KingdomPlanAreaSelectorGUI(file);
		}
		else if(type.equals("Package"))
		{
			throw new IllegalArgumentException();
		}
		return new GenericFileInfoGUI(file);
	}
	public int getHeight()
	{
		return GUI.assetHeight;
	}
	public byte[] getBytes() 
	{
		System.out.println(file.getName());
		if(gui==null) return new byte[0];
		return gui.getBytes();
	}
	public void deselect()
	{
		setBackground(Color.white);
	}
}
