package GUI;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import bFM.FlagManager;
import bFM.Settings;
import bFM.Utils;
import bFM.FlagManager.Flag;

@SuppressWarnings("serial")
public class BitFlagPanel extends JSplitPane
{
	private static ArrayList<BitFlagInfoPanel> names = new ArrayList<BitFlagInfoPanel>();
	JSplitPane info = new JSplitPane();
	JTextField data;
	private class BitFlagInfoPanel extends JTextField
	{
		private Flag data;
		protected BitFlagInfoPanel(Flag flag)
		{
			super(flag.name().toString());
			data = flag;
			names.add(this);
			getDocument().addDocumentListener(new DocumentListener()
			{
				public void insertUpdate(DocumentEvent e)
				{
					data.setName(getText());
				}
				public void removeUpdate(DocumentEvent e)
				{
					data.setName(getText());
				}
				public void changedUpdate(DocumentEvent e) {}
			});
		}
	}
	public BitFlagPanel(String text, int flag, Consumer<Integer> setterFunction)
	{
		info.setLeftComponent(new JLabel(text));
		updateFlagInfo(flag);
		initializeTextField(flag, setterFunction);
		
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
		info.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
		data.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
		setContinuousLayout(true);
		data.setMinimumSize(new Dimension(Settings.buttonWidth, Settings.assetHeight));
		info.setMinimumSize(new Dimension(Settings.buttonWidth, Settings.assetHeight));
		
		setDividerSize(0);
		setDividerLocation(.5);
		setResizeWeight(.5);
		info.setDividerSize(0);
		info.setDividerLocation(.5);
		info.setResizeWeight(.5);
		
		
		setLeftComponent(info);
		setRightComponent(data);
	}
	private void initializeTextField(int flag, Consumer<Integer> setterFunction)
	{
		data = new JTextField("" + flag);
		
		data.getDocument().addDocumentListener(new DocumentListener()
		{
			public void insertUpdate(DocumentEvent e)
			{
				try
				{
					int flag = Integer.parseInt(data.getText());
					setterFunction.accept(flag);
					updateFlagInfo(flag);
				}
				catch(NumberFormatException i)
				{
					JLabel panel = new JLabel(" Invalid");
					panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
					panel.setMinimumSize(new Dimension(1, Settings.assetHeight));
					info.setRightComponent(panel);
					update();
					setterFunction.accept(-1);
				}
			}
			public void removeUpdate(DocumentEvent e)
			{
				try
				{
					int flag = Integer.parseInt(data.getText());
					setterFunction.accept(flag);
					updateFlagInfo(flag);
				}
				catch(NumberFormatException i)
				{
					JLabel panel = new JLabel(" Invalid");
					panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
					panel.setMinimumSize(new Dimension(1, Settings.assetHeight));
					info.setRightComponent(panel);
					update();
					setterFunction.accept(-1);
				}
			}
			public void changedUpdate(DocumentEvent e) {}
		});
	}
	private void updateFlagInfo(int flag)
	{
		if(flag == -1)
		{
			JLabel panel = new JLabel(" Ignored");
			panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
			panel.setMinimumSize(new Dimension(1, Settings.assetHeight));
			info.setRightComponent(panel);
			update();
			return;
		}
		if(flag < -1)
		{
			JLabel panel = new JLabel(" Invalid");
			panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
			panel.setMinimumSize(new Dimension(1, Settings.assetHeight));
			info.setRightComponent(panel);
			update();
			return;
		}
		//Getting flag
		for(BitFlagInfoPanel panel : names)
		{
			if(panel.data.flag()==flag)
			{
				info.setRightComponent(panel);
				update();
				return;
			}
		}
		//DNE, make new one
		BitFlagInfoPanel panel = new BitFlagInfoPanel(FlagManager.getBitFlag(flag));
		names.add(panel);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Settings.LighterColor, Settings.DarkerColor));
		panel.setMinimumSize(new Dimension(1, Settings.assetHeight));
		info.setRightComponent(panel);
		update();
	}
	public void update()
	{
		SwingUtilities.invokeLater(() -> 
		{
			setResizeWeight(.5);
			setDividerLocation(.5);
			SwingUtilities.invokeLater(() -> 
			{
				info.setResizeWeight(.5);
				info.setDividerLocation(.5);
			});
		});
		revalidate();
		repaint();
		info.revalidate();
		info.repaint();
	}
}
