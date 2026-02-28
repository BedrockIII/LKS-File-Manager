package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import MenuDBGUI.KingdomPlanFileList;
import PCKGManager.PCKGManager;

public class Package extends Generic
{
	/**
	 * 
	 */
	int padding = 0;
	private static final long serialVersionUID = 1L;
	JPanel headerPanel = new JPanel();
	PCKGManager packageFile;
	JPanel FilesGUI = new JPanel();
	ArrayList<JComponent> files = new ArrayList<JComponent>();
	GridBagConstraints layout = new GridBagConstraints();
	JPopupMenu actions = new JPopupMenu();
	Package parent = null;
	GenericFileInfoGUI info = null;
	boolean isExtended = true;
	private Package(PCKGManager packageFile, int padding, Package parent)
	{
		this.packageFile = packageFile;
		this.parent = parent;
		this.padding = padding;
		setLayout(new GridBagLayout());
		layout = new GridBagConstraints();
	    layout.gridwidth = GridBagConstraints.REMAINDER;
	    
	    
	    //System.out.println("Package depth: " + ((padding/5)+1));
		createPackageHeader();
		initializeGUIS();
		update();
		
	}
	public Package(PCKGManager packageFile)
	{
		this.packageFile = packageFile;
		padding = 0;
		setLayout(new GridBagLayout());
		layout = new GridBagConstraints();
	    layout.gridwidth = GridBagConstraints.REMAINDER;
	    layout.anchor = GridBagConstraints.FIRST_LINE_START;
	   
	    
		createPackageHeader();
		initializeGUIS();
		GUI.update();
	}
	public Package() 
	{
		packageFile = new PCKGManager("New Package");
	}
	public void update()
	{
		if(isExtended) updateFull();
		else updateHeader();
		
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		setMinimumSize(new Dimension(GUI.rowWidth, getHeight()));
		//System.out.println("Package: " + packageFile.getName());
		//System.out.println("File Count: " + packageFile.getFileAmount());
		//System.out.println("Package depth: " + ((padding/5)+1));
		//System.out.println("Height: " + getHeight());
		//GUI.update();
	}
	private void initializeGUIS()
	{
		try
		{
			info = makeInfoGUI(packageFile.getName(), packageFile.getFile());
		}
		catch(IllegalArgumentException e)
		{
			info = new PackageInfoGUI(packageFile);
		}
		files = new ArrayList<JComponent>();
		files.add(headerPanel);
		
		for(int i =0; i<packageFile.getFileAmount(); i++)
		{
			byte[] data = packageFile.getFile(i);
			String name = packageFile.getName(i);
			createGUI(name, data);
		}
	}
	private void updateFull() 
	{
		FilesGUI = new JPanel();
		removeAll();
		
		for(int j = 0; j<files.size();j++)
		{
			add(files.get(j), layout);
		}
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		setSize(new Dimension(GUI.rowWidth, getHeight()));
		//System.out.println(new Dimension(GUI.rowWidth, getHeight()));
	}
	private void updateHeader() 
	{
		removeAll();
		add(headerPanel);
		setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
		setSize(new Dimension(GUI.rowWidth, getHeight()));
	}
	public int getHeight()
	{
		int increment = GUI.assetHeight;
		if(isExtended == false) return increment+increment;
		int ret = 0;
		for(int i = 0; i < files.size(); i++)
		{
			if(files.get(i) instanceof Package)
			{
				ret += ((Package)files.get(i)).getHeight();
			}
			else ret += increment;
		}
		return ret;
	}
	private void createPackageHeader()
	{
		headerPanel.setPreferredSize(new Dimension(GUI.rowWidth, GUI.assetHeight));
		//headerPanel.setMinimumSize(new Dimension(GUI.rowWidth, GUI.assetHeight));
		headerPanel.setLayout(new GridBagLayout());
		headerPanel.setMaximumSize(new Dimension(100000,GUI.assetHeight));
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(padding, GUI.assetHeight));
		spacer.setMinimumSize(new Dimension(padding, GUI.assetHeight));
		GridBagConstraints constraints = new GridBagConstraints();  
		constraints.weightx = 0.0;
		headerPanel.add(spacer); 
		constraints.weightx = 1.0;
		JLabel pacName = new JLabel(packageFile.getName(), SwingConstants.LEFT);
		pacName.setPreferredSize(new Dimension(GUI.rowWidth-padding, GUI.assetHeight));
		headerPanel.add(pacName, constraints);
		
		
		
		
		
		addExportButton();
		addFileButton();
		addExportAllButton();
		
		addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) 
		    {
		        if (e.isPopupTrigger()) showMenu(e);
		        else if(SwingUtilities.isLeftMouseButton(e))
		        {
		        	GUI.deselectAll();
		        	headerPanel.setBackground(GUI.selectedColor);
		        	GUI.setFileInfo(info);
		        	//isExtended = !isExtended;
		        	//if(parent!=null)parent.updateChildPack(packageFile);
		        	//broken for now
		        	//GUI.update();
		        	//TODO fix
		        }
		    }
		    public void mouseReleased(MouseEvent e) {
		        if (e.isPopupTrigger()) showMenu(e);
		    }
		    private void showMenu(MouseEvent e) {
		    	actions.show(e.getComponent(), e.getX(), e.getY());
		    }
		});
		add(actions);
	}
	private void addExportButton()
	{
		JMenuItem export = new JMenuItem("Export File");
		export.addActionListener(e -> {
			updatePacContents();
			JFileChooser chooseFile = new JFileChooser();
			//chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int num = chooseFile.showSaveDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				
				String output = chooseFile.getSelectedFile().toPath().toString();
				packageFile.writePac(output);
			}
			System.out.println("Exported Package");
		});
		actions.add(export);
	}
	private void addFileButton()
	{
		JMenuItem addFile = new JMenuItem("Add New File");
		addFile.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			try 
			{
				chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooseFile.showOpenDialog(null);
				byte[] data = Files.readAllBytes(chooseFile.getSelectedFile().toPath());
				String name = chooseFile.getSelectedFile().getName().toString();
				packageFile.addFile(name, data);
				createGUI(name, data);
				
				System.out.println("Added File to Package");
				if(parent!=null)
				{
					parent.updateChildPack(packageFile);
					System.out.println("Told Parent about new Package");
				}
				GUI.update();
			} catch (IOException i) 
			{
				i.printStackTrace();
			}
		});
		actions.add(addFile);
	}
	private void createGUI(String name, byte[] data) 
	{
		int padding = this.padding + GUI.indentSize;
		String fileType = bFM.Utils.getFileType(name, data);
		if(fileType.equals("Fixed Point"))
		{
			//files.add(new FixedPoint(packageFile,files.size(),padding+5,i));
		}
		else if(fileType.equals("Collision"))
		{
			//files.add(new Collision(packageFile,files.size(),padding+5,i));
		}
		else if (fileType.equals("Package"))
		{
			Package packagePanel = new Package(new PCKGManager(data,name), padding, this);
			files.add(packagePanel);
		}else if (fileType.equals("KingdomPlanDB"))
		{
			files.add(new KingdomPlanFileList(data, padding));
		}
		else
		{
			files.add(new Generic(name, data, padding+5));
		}
	}
	private void updateChildPack(PCKGManager kidPack) 
	{
		packageFile.addFile(kidPack.getName(), kidPack.getFile());
		if(parent!=null)parent.updateChildPack(packageFile);
	}
	private void addExportAllButton()
	{
		JMenuItem exportAll = new JMenuItem("Export All Files");
		exportAll.addActionListener(e -> {
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int num = chooseFile.showSaveDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				updatePacContents();
				packageFile.extractAll(chooseFile.getSelectedFile().toPath().toString()+ "/");
				System.out.println("Exported all files");
			}
		});
		actions.add(exportAll);
	}
	private void updatePacContents() 
	{
		for(JComponent c : files)
		{
			if(c instanceof KingdomPlanFileList)
			{
				packageFile.addFile("KingdomPlan.bin", ((KingdomPlanFileList)(c)).getBytes());
			}
		}
		
		
		((PackageInfoGUI)info).updateGUI();
	}
	public void deselect()
	{
		headerPanel.setBackground(getBackground());
	}
	public void deselectAll() 
	{
		deselect();
		for(JComponent comp : files)
		{
			if(comp instanceof Package)
			{
				((Package)comp).deselectAll();
			}
			else comp.setBackground(getBackground());
		}
	}
}
