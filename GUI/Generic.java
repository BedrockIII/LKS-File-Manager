package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import GUI.GUI.Actions;
import PCKGManager.PCKGManager;

@SuppressWarnings("serial")
public class Generic extends JPanel implements ActionListener
{
	byte[] data;
	String name = "";
	public Generic(PCKGManager pac, int parentY, int parentX, int displacement)
	{
		data = pac.getFile(displacement);
		name = pac.getName(displacement);
		super.setBounds(40+parentX,GUI.assetHeight+parentY,GUI.rowWidth,GUI.assetHeight);
		super.setLayout(new GridBagLayout());
		super.setMaximumSize(new Dimension(100000,GUI.assetHeight));
		GridBagConstraints constraints = new GridBagConstraints();  
		constraints.weightx = 1.0;
		JPanel spacer = new JPanel();
		spacer.setPreferredSize(new Dimension(parentX, GUI.assetHeight));
		spacer.setMinimumSize(new Dimension(parentX, GUI.assetHeight));
		super.add(spacer);
		JLabel fileName = new JLabel(pac.getName(displacement), SwingConstants.LEFT);
		fileName.setPreferredSize(new Dimension(10000, GUI.assetHeight));
		super.add(fileName, constraints);
		
		JButton export = new JButton("Export File");
		export.setActionCommand(Actions.EXPORT.name());
		export.addActionListener(this);
		export.setPreferredSize(GUI.buttonSize);
		export.setMinimumSize(GUI.buttonSize);
		super.add(export);
		
		JButton replace = new JButton("Replace File");
		replace.setActionCommand(Actions.REPLACE.name());
		replace.setPreferredSize(GUI.buttonSize);
		replace.setMinimumSize(GUI.buttonSize);
		super.add(replace);
		
		//coloring
		if(displacement%2==0)
		{
			super.setBackground(new Color(179, 245, 244)); //blue
		}
		else
		{
			super.setBackground(new Color(191, 191, 191)); //grey
		}
	}
	@Override
	public void actionPerformed(ActionEvent event) 
	{
		
		if(event.getActionCommand()== Actions.EXPORT.name())
		{
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setSelectedFile(new File(name));
			if(chooseFile.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
			{
				try {Files.write(chooseFile.getSelectedFile().toPath(),data);}catch(IOException e){e.printStackTrace();System.out.println("Failed to Export Generic File");}
				System.out.println("Exported Generic File");
			}
		}else if(event.getActionCommand()== Actions.REPLACE.name())
		{
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
				} catch (IOException e) 
				{
					e.printStackTrace();
					System.out.println("Failed to Import Generic File");
				}
				System.out.println("Imported Generic File");
			}
		}
	}

}
