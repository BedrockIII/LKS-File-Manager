package GUI.PopupWindows;

import java.awt.Color;
import java.awt.Dimension;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import bFM.Settings;

public class ConsoleWindow extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5514789291670059323L;
	private JTextPane  output = new JTextPane()
	{
		public boolean getScrollableTracksViewportWidth()
		{
			return true;
			
		}
	};
	private JScrollPane contentPanel = new JScrollPane(output);
	static SimpleAttributeSet errorFont = new SimpleAttributeSet();
	static SimpleAttributeSet warnFont = new SimpleAttributeSet();
	static SimpleAttributeSet printFont = new SimpleAttributeSet();
	public static OutputStream out;
	public static PrintStream warn;
	
	public static OutputStream err;
	@SuppressWarnings("unused")
	public ConsoleWindow()
	{
		setTheming("Console Window");
		setSize();
		stealPrinting();
	}
	private void setTheming(String name)
	{
		setVisible(true);
		setLocationRelativeTo(GUI.GUI.frame);
		setVisible(true);
		setAlwaysOnTop(true);
		setTitle(name);
		getContentPane().add(contentPanel);
		contentPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		output.setEditable(false);
		
		StyleConstants.setForeground(printFont, Color.BLACK);
		StyleConstants.setForeground(warnFont, Color.YELLOW);
		StyleConstants.setForeground(errorFont, Color.RED);
	}
	private void setSize()
	{
		setResizable(true);
		super.setSize(new Dimension(Settings.consoleWindowWidth,Settings.consoleWindowHeight));
	}
	private void stealPrinting()
	{
		OutputStream out = new OutputStream() {
			public void write(int b)
			{
				SwingUtilities.invokeLater(() -> {
					try {
						output.getStyledDocument().insertString(output.getStyledDocument().getLength(), "" + (char)b, printFont);
					} catch (BadLocationException e)
					{
						//Impossible
					}
				});
			}
		};
		OutputStream warn = new OutputStream() {
			public void write(int b)
			{
				SwingUtilities.invokeLater(() -> {
					try {
						output.getStyledDocument().insertString(output.getStyledDocument().getLength(), "" + (char)b, warnFont);
					} catch (BadLocationException e)
					{
						//Impossible
					}
				});
			}
		};
		ConsoleWindow.warn = new PrintStream(warn, true);
		OutputStream err = new OutputStream() {
			public void write(int b)
			{
				SwingUtilities.invokeLater(() -> {
					try {
						output.getStyledDocument().insertString(output.getStyledDocument().getLength(), "" + (char)b,errorFont);
					} catch (BadLocationException e)
					{
						//Impossible
					}
					GUI.GUI.debugOutput.setVisible(true);
				});
			}
		};
		System.setOut(new PrintStream(out, true));
		System.setErr(new PrintStream(err, true));
		
	}
	public static void warn(String text)
	{
		if(warn != null)
			warn.println(text);
		else System.err.println(text);
	}
}
