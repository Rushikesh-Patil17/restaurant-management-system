package org.coep.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.coep.objects.MenuItem;

public class AddMenuItem extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel name, price, type;
	JTextField tName;
	JSpinner tPrice;
	JComboBox<String> cType;
	JCheckBox cIsAvailable;
	JButton save, cancel;
	Menu frame;
	MenuItem item;
	
	public AddMenuItem(Menu class1, MenuItem item) {
		setSize(350, 350);
		setLocation(550, 200);
		setLayout(null);
		
		this.frame = class1;
		this.item = item;
		
		name = new JLabel("Name");
		price = new JLabel("Price");
		type = new JLabel("Category");

		tName = new JTextField(30);

		SpinnerModel model = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		tPrice = new JSpinner(model);

		cType = new JComboBox<String>(new String[] { "Veg", "Non-veg", "Dessert", "Drink" });
		cIsAvailable = new JCheckBox("Availability");

		save = new JButton("Save");
		cancel = new JButton("Close");

		add(name);
		name.setBounds(20, 20, 40, 10);

		add(tName);
		tName.setBounds(100, 15, 200, 25);

		add(price);
		price.setBounds(20, 70, 50, 20);

		add(tPrice);
		tPrice.setBounds(100, 65, 70, 25);

		add(type);
		type.setBounds(20, 120, 70, 20);

		add(cType);
		cType.setBounds(100, 115, 100, 25);

		add(cIsAvailable);
		cIsAvailable.setBounds(20, 170, 100, 20);

		add(save);
		save.setBounds(20, 220, 70, 30);

		add(cancel);
		cancel.setBounds(120, 220, 80, 30);

		save.addActionListener(this);
		cancel.addActionListener(this);
		
		if(this.item != null) {
			setTitle("Edit Menu Item");
			setup();
		}
		
		else
			setTitle("Add New Menu Item");
		
		setModal(true);
		setResizable(false);
		setVisible(true);
	}
	
	public void setup() {
		tName.setText(item.getName());
		tPrice.setValue(item.getPrice());
		cType.setSelectedIndex(item.getType());
		cIsAvailable.setSelected(item.isAvailable());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: Handle callbacks
	}
	
	void clear() {
		tName.setText("");
		tPrice.setValue(1);
		cType.setSelectedIndex(0);
		cIsAvailable.setSelected(false);
	}
}
