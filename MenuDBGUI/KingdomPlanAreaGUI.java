package MenuDBGUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import GUI.LabeledInputBox;
import SystemDataManagers.KingdomPlanArea;
import SystemDataManagers.KingdomPlanElement;

@SuppressWarnings("serial")
public class KingdomPlanAreaGUI extends JPanel
{
	KingdomPlanAreaSelectorGUI parent;
	ArrayList<KingdomPlanElementGUI> Elements = new ArrayList<KingdomPlanElementGUI>();
	JComboBox<KingdomPlanElementGUI> ElementList = new JComboBox<KingdomPlanElementGUI>();
	GridBagConstraints layout = new GridBagConstraints();
	JTextField Name = null;
	JTextField Description = null;
	JTextField Image = null;
	KingdomPlanArea area = null;
	public KingdomPlanAreaGUI(KingdomPlanArea area, KingdomPlanAreaSelectorGUI parent) 
	{
		//setBorder(BorderFactory.createLineBorder(Color.black));
		this.parent = parent;
		setLayout(new GridBagLayout());
		layout.anchor = GridBagConstraints.NORTHWEST;
		layout.fill = GridBagConstraints.HORIZONTAL;
		layout.weightx = 1.0;
		layout.weighty = 1.0;
		layout.gridwidth = GridBagConstraints.REMAINDER;
		this.area = area;
		
		makeGUI();
		
		
		
		addGUI();
	}
	private void makeGUI() 
	{
		Name = new JTextField(area.getName());
		Name.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				updateName();
			}
			public void removeUpdate(DocumentEvent e) 
			{
				updateName();
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		Description = new JTextField(area.getDescription());
		Description.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				area.setDescription(bFM.Utils.formatStringChars(Description.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				area.setDescription(bFM.Utils.formatStringChars(Description.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		Image = new JTextField(area.getImage());
		Image.getDocument().addDocumentListener(new DocumentListener() 
		{
			public void insertUpdate(DocumentEvent e) 
			{
				area.setImage(bFM.Utils.formatStringChars(Image.getText()));
			}
			public void removeUpdate(DocumentEvent e) 
			{
				area.setImage(bFM.Utils.formatStringChars(Image.getText()));
			}
			public void changedUpdate(DocumentEvent e) {}
		});
		
		JPopupMenu actions = new JPopupMenu();
		
		JMenuItem up = new JMenuItem("Move Up");
		up.addActionListener(e -> {
			int index = ElementList.getSelectedIndex();
			if(index == 0) return;
			KingdomPlanElementGUI Element = (KingdomPlanElementGUI) ElementList.getSelectedItem();
			ElementList.removeItemAt(index);
			ElementList.insertItemAt(Element, index-1);
			ElementList.setSelectedItem(Element);
			area.moveUp(index);
		});
		actions.add(up);
		
		JMenuItem down = new JMenuItem("Move Down");
		down.addActionListener(e -> {
			int index = ElementList.getSelectedIndex();
			if(index == ElementList.getItemCount()-1) return;
			KingdomPlanElementGUI Element = (KingdomPlanElementGUI) ElementList.getSelectedItem();
			ElementList.removeItemAt(index);
			ElementList.insertItemAt(Element, index+1);
			ElementList.setSelectedItem(Element);
			area.moveDown(index);
		});
		actions.add(down);
		
		JMenuItem newElem = new JMenuItem("Add New Element");
		newElem.addActionListener(e -> {
			KingdomPlanElement Element = new KingdomPlanElement();
			area.addElement(Element);
			KingdomPlanElementGUI ElementGUI = new KingdomPlanElementGUI(Element, this);
			ElementList.addItem(ElementGUI);
			ElementList.setSelectedItem(ElementGUI);
		});
		actions.add(newElem);
		
		JMenuItem removeElem = new JMenuItem("Remove Selected Element");
		removeElem.addActionListener(e -> {
			KingdomPlanElement Element = new KingdomPlanElement();
			int index = ElementList.getSelectedIndex();
			area.removeElement(Element);
			ElementList.removeItemAt(index);
			ElementList.setSelectedItem(Math.max(0, index-1));
		});
		actions.add(removeElem);
		
		ArrayList<KingdomPlanElement> elements = new ArrayList<KingdomPlanElement>(area.getElements());
		ElementList = new JComboBox<KingdomPlanElementGUI>();
		Elements = new ArrayList<KingdomPlanElementGUI>();
		
		for(int i = 0; i < elements.size(); i++)
		{
			Elements.add(new KingdomPlanElementGUI(elements.get(i), this));
			Elements.get(i).addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		        if(SwingUtilities.isRightMouseButton(e))
		        {
		        	showMenu(e);
		        }
		    }
		    public void mouseReleased(MouseEvent e)
		    {
		    }
		    private void showMenu(MouseEvent e) {
		    	actions.show(e.getComponent(), e.getX(), e.getY());
		    }
		});
			ElementList.addItem(Elements.get(i));
		}
		ElementList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				  if (e.getStateChange() == ItemEvent.SELECTED) 
				  {
					  addGUI();
					  GUI.GUI.update();
			      }
			}
			
		});
		//ElementList.setPreferredSize(new Dimension((int)(GUI.GUI.assetHeight), 100));
	}
	private void addGUI()
	{
		removeAll();
		add(new LabeledInputBox("Area Name: ", Name, 1.5), layout);
		add(new LabeledInputBox("Area Description: ", Description, 1.5), layout);
		add(new LabeledInputBox("Area Image Name: ", Image, 1.5), layout);
		add(ElementList, layout);
		if(ElementList.getSelectedItem() != null) add((KingdomPlanElementGUI) ElementList.getSelectedItem(), layout );
		else add(new JLabel("No Kingdom Plan Elements Found"), layout);
	}
	public String toString()
	{
		if(area==null) return "Unknown Name";
		return area.getName();
	}
	//Drop down of all elements and an element panel
	public void updateList() 
	{
		ElementList.repaint();
	}
	protected void updateName()
	{
		if(Name!=null) 
		{
			
			setName(bFM.Utils.formatStringChars(Name.getText()));
			area.setName(bFM.Utils.formatStringChars(Name.getText()));
			parent.updateList();
		}
		else 
		{
			System.out.println("Null Name");
		}
	}
	public void update() 
	{
		setPreferredSize(new Dimension(GUI.GUI.getRightSize().width-10, getHeight()));
		for(KingdomPlanElementGUI Element : Elements)
		{
			Element.update();
		}
	}
}
