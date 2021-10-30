package org.coep.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class Order extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	JButton veg, nonveg, dessert, drinks, bill, add, deleteItem, clearAll;
	JLabel name, totalBill, contact, dish, quantity;
	JTextField tm, tb, tc;
	JComboBox<String> orderCombo;
	JSpinner quantitySpinner;
	JScrollPane scrBill;
	JTable billTable;
	
	public Order() {
		this.setLayout(null);
		
		deleteItem = new JButton("Delete");
		clearAll = new JButton("Clear All");
		
		veg = new JButton("Vegetarian");
		nonveg = new JButton("Non-Vegetarian");
		dessert = new JButton("Dessert");
		drinks = new JButton("Drinks");

		bill = new JButton("Bill");
		add = new JButton("Add");

		name = new JLabel("Name");
		contact = new JLabel("Contact");
		totalBill = new JLabel("Bill");

		tm = new JTextField(30);
		tc = new JTextField(30);
		tb = new JTextField(30);

		dish = new JLabel("Select Dish: ");
		quantity = new JLabel("Select Quantity: ");

		orderCombo = new JComboBox<String>();
		quantitySpinner = new JSpinner();
		
		billTable = new JTable();
		scrBill = new JScrollPane(billTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		veg.addActionListener(this);
		nonveg.addActionListener(this);
		dessert.addActionListener(this);
		drinks.addActionListener(this);

		add.addActionListener(this);
		bill.addActionListener(this);
		orderCombo.addActionListener(this);
		orderCombo.addActionListener(this);
		deleteItem.addActionListener(this);
		clearAll.addActionListener(this);

		DefaultTableModel model = new DefaultTableModel(
				new Object[] { "Item ID", "Item Name", "Item Quantity", "Item Price", "Item Total" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		billTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		billTable.setModel(model);

		setMyColor();
		addMe();
		setMyPosition();
		setMyFont();
	}

	public void setMyColor() {
		this.setBackground(new Color(50, 210, 200));

		veg.setBackground(new Color(255, 0, 0));
		nonveg.setBackground(new Color(255, 0, 0));
		dessert.setBackground(new Color(255, 0, 0));
		drinks.setBackground(new Color(255, 0, 0));

		veg.setBackground(new Color(255, 0, 0));
		nonveg.setBackground(new Color(255, 0, 0));
		drinks.setBackground(new Color(255, 0, 0));
		dessert.setBackground(new Color(255, 0, 0));
		bill.setBackground(new Color(255, 0, 0));
		add.setBackground(new Color(255, 0, 0));

		name.setBackground(new Color(50, 210, 200));
		totalBill.setBackground(new Color(50, 210, 200));
		contact.setBackground(new Color(50, 210, 200));

		veg.setForeground(Color.WHITE);
		nonveg.setForeground(Color.WHITE);
		dessert.setForeground(Color.WHITE);
		drinks.setForeground(Color.WHITE);
		add.setForeground(Color.WHITE);
		bill.setForeground(Color.WHITE);
		dish.setForeground(Color.BLACK);
		quantity.setForeground(Color.BLACK);

		orderCombo.setBackground(Color.WHITE);
		quantitySpinner.setBackground(Color.WHITE);
	}

	public void addMe() {		
		this.add(veg);
		this.add(nonveg);
		this.add(dessert);
		this.add(drinks);
		this.add(add);
		this.add(bill);
		this.setVisible(false);
		this.setVisible(false);
		this.add(name);
		this.add(totalBill);
		this.add(contact);
		this.add(tm);
		this.add(tb);
		this.add(tc);
		this.add(orderCombo);
		this.add(quantitySpinner);
		this.add(dish);
		this.add(quantity);
		this.add(deleteItem);
		this.add(clearAll);
		this.add(scrBill);

		tb.setEnabled(false);
		quantitySpinner.setEnabled(false);
		orderCombo.setEnabled(false);
		deleteItem.setVisible(false);
		clearAll.setVisible(false);
		deleteItem.setEnabled(false);
	}

	public void setMyPosition() {
		this.setBounds(300, 50, 1300, 650);

		name.setBounds(20, 20, 80, 30);
		tm.setBounds(130, 20, 300, 30);
		contact.setBounds(20, 60, 150, 30);
		tc.setBounds(130, 60, 250, 30);
		totalBill.setBounds(20, 100, 80, 30);
		tb.setBounds(130, 100, 150, 30);

		dish.setBounds(20, 160, 260, 30);
		orderCombo.setBounds(20, 200, 480, 40);
		quantity.setBounds(20, 280, 285, 30);
		quantitySpinner.setBounds(20, 320, 100, 40);
		deleteItem.setBounds(510, 100, 100, 30);
		clearAll.setBounds(630, 100, 130, 30);

		veg.setBounds(20, 380, 230, 40);
		nonveg.setBounds(270, 380, 230, 40);
		dessert.setBounds(20, 450, 230, 40);
		drinks.setBounds(270, 450, 230, 40);
		bill.setBounds(20, 510, 230, 40);
		add.setBounds(270, 510, 230, 40);
		scrBill.setBounds(510, 150, 500, 400);
	}

	public void setMyFont() {
		deleteItem.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		clearAll.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		name.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		totalBill.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		contact.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		tm.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		tb.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		tc.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		dish.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		quantity.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		orderCombo.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		quantitySpinner.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		veg.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		dessert.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		drinks.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		nonveg.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		add.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		bill.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
	}

	public void actionPerformed(ActionEvent ae) {
	
	}
}
