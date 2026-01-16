package GUI;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;

import PCKGManager.PCKGManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI implements ActionListener
{
	public enum Actions 
	{
	    OPENFILE,
	    //Pac
	    EXPORTPAC,
	    IMPORT,
	    EXPORTALL,
	    //Generic
	    EXPORT,
	    REPLACE,
	    //FP
	    EXPORTFIXEDPOINTEXTRACTED,
	    IMPORTFIXEDPOINTEXTRACTED,
	    //Collision
	    EXPORTCOLASOBJ,
	    IMPORTCOLASOBJ,
	    //Unused
	    EXTRACTLZ10,
	    EXTRACT
	}
	private static Package pack = null;
	public static JFrame frame = new JFrame();
	public static final int assetHeight = 20;
	public static final int buttonWidth = 150;
	public static final int pacOffset = 50;
	public static final int rowWidth = 1000;
	JPanel fileArea = new JPanel();
	public static final Dimension buttonSize = new Dimension(GUI.buttonWidth,GUI.assetHeight);
	public GUI() 
	{
		frame.setName("LKS File Manager");
		JButton fileButton = new JButton("Open File");
		//fileButton.setActionCommand(Actions.OPENFILE.name());
		//fileButton.setBounds(0,0,100,20);
		//fileButton.addActionListener(this);
		
		frame.add(fileButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setMinimumSize(new Dimension(rowWidth, 400));
		frame.setLayout(new BorderLayout());
		
		 fileArea = new JPanel();
	        fileArea.setBounds(0, assetHeight, rowWidth, 400-assetHeight);
	        fileArea.setPreferredSize(new Dimension(rowWidth, 400-assetHeight));
	        fileArea.setMinimumSize(new Dimension(rowWidth, 400-assetHeight));
	        frame.setContentPane(fileArea);
	        
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(true);
		menuBar.setPreferredSize(new Dimension(rowWidth, assetHeight));
		menuBar.setMinimumSize(new Dimension(rowWidth, assetHeight));
		//menuBar.setMaximumSize(new Dimension(999999, assetHeight));
		JMenu fileMenu = new JMenu("File");
		fileMenu.setPreferredSize(buttonSize);
		fileMenu.setMinimumSize(buttonSize);
		JMenuItem openButton = new JMenuItem("Open File");
		openButton.setActionCommand(Actions.OPENFILE.name());
		openButton.addActionListener(this);
		fileMenu.add(openButton);
        menuBar.add(fileMenu);
        frame.add(menuBar);
        
       
        
        frame.pack();
		
		frame.setVisible(true);
		
	}
	public static void updatePac()
	{
		pack.updatePac();
		frame.pack();
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getActionCommand()== Actions.OPENFILE.name())
		{
			JFileChooser chooseFile = new JFileChooser();
			chooseFile.setFileFilter(new FileNameExtensionFilter("Pac File", "pac", "pcha", "bin", "pac0"));
			int num = chooseFile.showOpenDialog(null);
			if(num==JFileChooser.APPROVE_OPTION&&pack==null)
			{
				try {
					
					PCKGManager pac = new PCKGManager(Files.readAllBytes(chooseFile.getSelectedFile().toPath()), chooseFile.getSelectedFile().getName());
					pack = new Package(pac ,0 ,25, true);
					JPanel holder = new JPanel();
					holder.add(pack);
					holder.setLayout(new GridLayout(1,1,0,0));
					JScrollPane bar = new JScrollPane(holder, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					
					//bar.setCorner(ScrollPaneConstants.LOWER_RIGHT_CORNER,pack);
					//bar.setSize(pack.getSize());
					//frame.add(bar);
					//frame.setVisible(true);
					frame.add(pack);
					
					
				} catch (IOException error) {error.printStackTrace();}
				frame.setSize(rowWidth, pack.getHeight());
				frame.setLayout(null);
				frame.setVisible(true);
			}
			
		}
	}
}
