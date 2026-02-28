package GUI;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.GUI.Actions;
import PCKGManager.PCKGManager;

public class HeaderPanel extends JPanel implements ActionListener
{
	PCKGManager pac;
	public HeaderPanel(PCKGManager pac, int parentX, int parentY) 
	{
		super.setLayout(new GridBagLayout());
		super.add(new JLabel(pac.getName()));
		this.pac = pac;
		JButton export = new JButton("Export File");
		export.setActionCommand(Actions.EXPORTPAC.name());
		export.addActionListener(this);
		export.setPreferredSize(new Dimension(GUI.assetHeight,GUI.buttonWidth));
		super.add(export);
		
		JButton addFile = new JButton("Add New File");
		addFile.setActionCommand(Actions.IMPORT.name());
		addFile.addActionListener(this);
		addFile.setPreferredSize(new Dimension(GUI.assetHeight,GUI.buttonWidth));
		super.add(addFile);
		
		JButton exportAll = new JButton("Export All Files");
		exportAll.setActionCommand(Actions.EXPORTALL.name());
		exportAll.addActionListener(this);
		exportAll.setPreferredSize(new Dimension(GUI.assetHeight,GUI.buttonWidth));
		super.add(exportAll);
	}

	@Override
	public void actionPerformed(ActionEvent event) 
	{
		if(event.getActionCommand()== Actions.EXPORTPAC.name())
		{
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int num = chooseFile.showSaveDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				pac.writePac(chooseFile.getSelectedFile().toPath().toString());
			}
			System.out.println("Exported Package");
		} else if(event.getActionCommand()== Actions.IMPORT.name())
		{
			
			try 
			{
				JFileChooser chooseFile = new JFileChooser();
				chooseFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
				chooseFile.showOpenDialog(null);
				int num = pac.getFileAmount();
				pac.addFile(chooseFile.getSelectedFile().getName().toString(), Files.readAllBytes(chooseFile.getSelectedFile().toPath()));
				if(pac.getFileAmount()!=num)
					
				
				
				GUI.frame.setVisible(true);
				System.out.println("Added File to Package");
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		} else if(event.getActionCommand()== Actions.EXPORTALL.name())
		{
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int num = chooseFile.showSaveDialog(null);
			if(num==JFileChooser.APPROVE_OPTION)
			{
				pac.extractAll(chooseFile.getSelectedFile().toPath().toString());
				System.out.println("Exported all files");
			}
		}
	}

}
