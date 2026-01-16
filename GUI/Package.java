package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import GUI.GUI.Actions;
import PCKGManager.PCKGManager;

public class Package extends JPanel implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel headerPanel = new JPanel();
	PCKGManager pac;
	boolean first;
	int parentX;
	int parentY;
	JPanel FilesGUI = new JPanel();
	ArrayList<JComponent> files = new ArrayList<JComponent>();
	public Package(PCKGManager pac, int parentX, int parentY, boolean first)
	{
		this.pac = pac;
		this.parentX = parentX;//+GUI.pacOffset;
		this.parentY = parentY;
		this.first = first;
		
		
		
		this.addButtons();
		this.updatePac();
		if(first)
		{
			JScrollPane scroll = new JScrollPane(FilesGUI);
			scroll.setPreferredSize(new Dimension(GUI.rowWidth, getHeight()));
			//add(FilesGUI);
			
		}
	}
	private ArrayList<JComponent> getFiles()
	{
		return files;
	}
	public void updatePac()
	{
		FilesGUI = new JPanel();
		this.removeAll();
		this.add(headerPanel);
		int height = Package.getHeight(pac);
		files = new ArrayList<JComponent>();
		files.add(headerPanel);
		for(int i =0; i<pac.getFileAmount(); i++)
		{
			if(pac.getFileType(i)=="Fixed Point")
			{
				files.add(new FixedPoint(pac,files.size(),parentX,i));
			}
			else if(pac.getFileType(i)=="Collision")
			{
				files.add(new Collision(pac,files.size(),parentX,i));
			}
			else if (pac.getFileType(i)=="Package")
			{
				ArrayList<JComponent> list = new Package(new PCKGManager(pac.getFile(pac.getName(i)),pac.getName(i)),files.size(),parentX+GUI.pacOffset, false).getFiles();
				for(int j = 0; j<list.size();j++)
				{
					files.add(list.get(j));
				}
			}else
			{
				files.add(new Generic(pac,files.size(),parentX,i));
			}
		}
		for(int j = 0; j<files.size();j++)
		{
			if(first)super.add(files.get(j));
		}
		super.setBounds(parentX, parentY, GUI.rowWidth, height);
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		super.setLayout(layout);
	}
	private void addButtons()
	{
		headerPanel.setLayout(new GridBagLayout());
		headerPanel.setMaximumSize(new Dimension(100000,GUI.assetHeight));
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(parentX, GUI.assetHeight));
		spacer.setMinimumSize(new Dimension(parentX, GUI.assetHeight));
		super.add(spacer);
		GridBagConstraints constraints = new GridBagConstraints();  
		constraints.weightx = 1.0;
		JLabel pacName = new JLabel(pac.getName(), SwingConstants.LEFT);
		pacName.setPreferredSize(new Dimension(1000, GUI.assetHeight));
		headerPanel.add(pacName, constraints);
		
		
		
		JButton export = new JButton("Export File");
		export.setActionCommand(Actions.EXPORTPAC.name());
		export.addActionListener(this);
		export.setPreferredSize(GUI.buttonSize);
		export.setMinimumSize(GUI.buttonSize);
		headerPanel.add(export);
		
		JButton addFile = new JButton("Add New File");
		addFile.setActionCommand(Actions.IMPORT.name());
		addFile.addActionListener(this);
		addFile.setPreferredSize(GUI.buttonSize);
		addFile.setMinimumSize(GUI.buttonSize);
		headerPanel.add(addFile);
		
		JButton exportAll = new JButton("Export All Files");
		exportAll.setActionCommand(Actions.EXPORTALL.name());
		exportAll.addActionListener(this);
		exportAll.setPreferredSize(GUI.buttonSize);
		exportAll.setMinimumSize(GUI.buttonSize);
		headerPanel.add(exportAll);
		//this.add(headerPanel);
		
	}
	private static int getHeight(PCKGManager pac) {
		int ret = GUI.assetHeight;
		for(int i =0; i<pac.getFileAmount(); i++)
		{
			if(PCKGManager.isPAC(pac.getFile(pac.getName(i))))
			{
				ret+=getHeight(new PCKGManager(pac.getFile(i)));
			}
			else 
			{
				ret+= GUI.assetHeight;
			}
		}
		return ret;
	}
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		JFileChooser chooseFile = new JFileChooser();
		if(event.getActionCommand()== Actions.EXPORTPAC.name())
		{
			chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int num = chooseFile.showSaveDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				String output = chooseFile.getSelectedFile().toPath().toString();
				if(output.charAt(output.length()-1)=='\\'||output.charAt(output.length()-1)=='/')
				{
					output = output + pac.getName();
				} else
				{
					output = output + "/" + pac.getName();
				}
				pac.writePac(output);
			}
			System.out.println("Exported Package");
		} else if(event.getActionCommand()== Actions.IMPORT.name())
		{
			
			try 
			{
				chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooseFile.showOpenDialog(null);
				pac.addFile(chooseFile.getSelectedFile().getName().toString(), Files.readAllBytes(chooseFile.getSelectedFile().toPath()));
				GUI.updatePac();
				
				System.out.println("Added File to Package");
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		} else if(event.getActionCommand()== Actions.EXPORTALL.name())
		{
			chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int num = chooseFile.showSaveDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				pac.extractAll(chooseFile.getSelectedFile().toPath().toString());
				System.out.println("Exported all files");
			}
		} else if(event.getActionCommand()== Actions.OPENFILE.name())
		{
			
			try {pac = new PCKGManager(Files.readAllBytes(chooseFile.getSelectedFile().toPath()), chooseFile.getSelectedFile().getName());}
			catch(IOException e) {}
			GUI.updatePac();
		}
	}
}
