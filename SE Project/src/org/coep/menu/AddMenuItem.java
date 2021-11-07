package org.coep.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import org.coep.objects.MenuItem;

public class AddMenuItem extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public static int whichType = 0;
	
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
		if (e.getSource() == save) {
			if(item == null) {
				// save the details in the db
				String name = tName.getText();
				int price = (int) tPrice.getValue();
				int type = cType.getSelectedIndex();
				int isAvailable = cIsAvailable.isSelected() ? 1 : 0;

				boolean isValid = true;

				if (name.trim().isEmpty()) {
					isValid = false;
				}

				if (isValid) {
					String url = "jdbc:sqlite::resource:FastFoodDB.db";

					try {
						Class.forName("org.sqlite.JDBC");
						Connection con = DriverManager.getConnection(url);
						PreparedStatement statement = con.prepareStatement(
								"insert into menu_item(item_name, item_price, item_type, is_available) values(?, ?, ?, ?)");

						statement.setString(1, name);
						statement.setInt(2, price);
						statement.setInt(3, type);
						statement.setInt(4, isAvailable);

						statement.executeUpdate();
						
						frame.refreshMenu(type);
						clear();
						
						JOptionPane.showMessageDialog(this, "Item Saved Successfully!", "",
								JOptionPane.INFORMATION_MESSAGE);
						
						con.close();
					}

					catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Could not save item!", "", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			}
			
			else {
				// edit the existing item
				String name = tName.getText();
				int price = (int) tPrice.getValue();
				int type = cType.getSelectedIndex();
				int isAvailable = cIsAvailable.isSelected() ? 1 : 0;

				boolean isValid = true;

				if (name.trim().isEmpty()) {
					isValid = false;
				}

				if (isValid) {
					String url = "jdbc:sqlite::resource:FastFoodDB.db";

					try {
						Class.forName("org.sqlite.JDBC");
						Connection con = DriverManager.getConnection(url);
						PreparedStatement statement = con.prepareStatement(
								"update menu_item set item_name = ?, item_price = ?, item_type = ?, is_available = ? where item_id = ?");

						statement.setString(1, name);
						statement.setInt(2, price);
						statement.setInt(3, type);
						statement.setInt(4, isAvailable);
						statement.setInt(5, item.getId());
						
						statement.executeUpdate();
						
						frame.refreshMenu(type);
						setVisible(false);
						
						JOptionPane.showMessageDialog(this, "Item Updated Successfully!", "",
								JOptionPane.INFORMATION_MESSAGE);
						
						con.close();
					}

					catch (Exception ex) {
						JOptionPane.showMessageDialog(this, "Could not update item!", "", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			}
		}

		else if (e.getSource() == cancel) {
			this.setVisible(false);
		}
	}
	
	void clear() {
		tName.setText("");
		tPrice.setValue(1);
		cType.setSelectedIndex(0);
		cIsAvailable.setSelected(false);
	}
}