package GUI.PopupWindows;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import bFM.Settings;

public class ConsoleWindow extends JFrame
{
	private JTextArea output = new JTextArea();
	private JScrollPane contentPanel = new JScrollPane(output);
	 
	@SuppressWarnings("unused")
	public ConsoleWindow()
	{
		setTheming("Console Window");
		SwingUtilities.invokeLater(() -> {
			addGUI();
			setSize();
			stealPrinting();
		});
	}
	private void setTheming(String name)
	{
		setVisible(true);
		setLocationRelativeTo(GUI.GUI.frame);
		setVisible(true);
		setAlwaysOnTop(true);
		setTitle(name);
		getContentPane().add(contentPanel);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	private void setSize()
	{
		setResizable(true);
		pack();
	}
	protected void addGUI()
	{
		GridBagConstraints layout = Settings.getDefaultConstraints();
		
	}
	private void stealPrinting()
	{
		OutputStream out = new OutputStream() {
			public void write(int b)
			{
				SwingUtilities.invokeLater(() -> {
					output.append(String.valueOf(b));
					output.setCaretPosition(output.getDocument().getLength());
				});
			}
		};
		OutputStream err = new OutputStream() {
			public void write(int b)
			{
				SwingUtilities.invokeLater(() -> {
					output.append(String.valueOf(b));
					output.setCaretPosition(output.getDocument().getLength());
					GUI.GUI.debugOutput.setVisible(true);
				});
			}
		};
		System.setOut(new PrintStream(out, true));
		//System.setErr(new PrintStream(err, true));
	}
}
