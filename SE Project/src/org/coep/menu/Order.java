package org.coep.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.coep.objects.OrderClass;
import org.coep.testtool.OrderValidator;

public class Order extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	JButton veg, nonveg, dessert, drinks, bill, add, deleteItem, clearAll;
	JLabel name, totalBill, contact, dish, quantity;
	JTextField tm, tb, tc;
	JComboBox<String> orderCombo;
	JSpinner quantitySpinner;
	JScrollPane scrBill;
	JTable billTable;
	static int amount = 0;
	boolean isEditing = false;
	static int toBeUpdated = -1;
	MenuClass parent;
	
	// map to store menu items id and quantity
	LinkedHashMap<Integer, Integer[]> map = new LinkedHashMap<Integer, Integer[]>();

	
	public Order(MenuClass parent) {
		this.setLayout(null);
		this.parent = parent;
		
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

		billTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (billTable.getSelectedRow() != -1 && billTable.getSelectedRow() != (billTable.getRowCount() - 1)
						&& billTable.getSelectedRow() != (billTable.getRowCount() - 2)) {
					// enable deleteItem button
					deleteItem.setEnabled(true);
				} else {
					// otherwise disable deleteItem button
					deleteItem.setEnabled(false);
				}
			}
		});

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
	
	public void handleMenu(int type) {
		orderCombo.setEnabled(true);

		String url = "jdbc:sqlite::resource:FastFoodDB.db";
		String x = "SELECT * FROM menu_item WHERE item_type = " + type;

		orderCombo.removeAllItems();

		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(x);

			while (rs.next()) {
				if (rs.getInt(5) == 0)
					continue;
				orderCombo.addItem(rs.getString(2));
			}
			con.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		// Callback for <Order> -> <Veg Menu>
		if (ae.getSource() == veg)
			handleMenu(0);

		// Callback for <Order> -> <NonVeg Menu>
		else if (ae.getSource() == nonveg)
			handleMenu(1);

		// Callback for <Order> -> <Dessert Menu>
		else if (ae.getSource() == dessert)
			handleMenu(2);
		
		// Callback for <Order> -> <Drinks Menu>
		else if (ae.getSource() == drinks)
			handleMenu(3);

		// Callback for <Order> -> <Add>
		else if (ae.getSource() == add) {
			String y = quantitySpinner.getValue().toString();
			int q = Integer.parseInt(y);
			String n = (String) orderCombo.getSelectedItem();

			String url = "jdbc:sqlite::resource:FastFoodDB.db";
			String x = "SELECT * FROM menu_item WHERE item_name = '" + n + "'";

			try {
				Class.forName("org.sqlite.JDBC");
				Connection con = DriverManager.getConnection(url);
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(x);

				if (rs.next()) {
					int id = rs.getInt("item_id");
					int price = rs.getInt("item_price");

					// add the entry in hash map
					map.put(id, new Integer[] { q, price });

					refreshBill();
					tb.setText(String.valueOf(amount));
					deleteItem.setVisible(true);
					clearAll.setVisible(true);
				}
				con.close();
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		}

		// Callback for <Order> -> <Bill>
		else if (ae.getSource() == bill) {
			String name = tm.getText().trim();
			String mobile = tc.getText().trim();

			OrderClass order = new OrderClass(name, mobile, billTable.getRowCount());
			boolean isValid = new OrderValidator(order, this.parent).isValid();
			
			
			if (isValid) {
				tb.setText(String.valueOf(amount));
				String url = "jdbc:sqlite::resource:FastFoodDB.db";
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				try {
					Class.forName("org.sqlite.JDBC");
					Connection con = DriverManager.getConnection(url);

					String cmd = "INSERT INTO food_order(order_name, order_date, order_mobile, order_bill, items) values(?, ?, ?, ?, ?)";
					String cmd1 = "UPDATE food_order SET order_name = ?, order_mobile = ?, order_bill = ?, items = ? WHERE _id = ?";

					PreparedStatement statement = con.prepareStatement(cmd);
					byte[] buffer = makebyte(map);

					PreparedStatement statement1 = con.prepareStatement(cmd1);

					if (isEditing) {
						statement1.setString(1, tm.getText());
						statement1.setString(2, tc.getText());
						statement1.setInt(3, amount);
						statement1.setBytes(4, buffer);
						statement1.setInt(5, toBeUpdated);

						statement1.execute();
						// clear map
						map.clear();

						JOptionPane.showMessageDialog(null, "Order Updated Successfully!!");
						isEditing = false;
						con.close();
					}

					else {
						statement.setString(1, tm.getText());
						statement.setString(2, String.valueOf(formatter.format(new Date())));
						statement.setString(3, tc.getText());
						statement.setInt(4, amount);
						statement.setBytes(5, buffer);

						statement.execute();

						// clear map
						map.clear();

						JOptionPane.showMessageDialog(null, "Order Saved Successfully!!");
						con.close();
					}
				}

				catch (Exception e) {
					e.printStackTrace();
				}

				tm.setText("");
				tc.setText("");
				tb.setText("");
				amount = 0;
				DefaultTableModel dtm = (DefaultTableModel) billTable.getModel();
				dtm.setRowCount(0);
			}
		}

		else if (ae.getSource() == orderCombo) {
			quantitySpinner.setEnabled(true);
			SpinnerModel value = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
			quantitySpinner.setModel(value);
		}
		
		if (ae.getSource() == deleteItem) {
			if (billTable.getRowCount() != 0) {
				((DefaultTableModel) billTable.getModel()).removeRow(billTable.getRowCount() - 1);
				((DefaultTableModel) billTable.getModel()).removeRow(billTable.getRowCount() - 1);
			}

			// delete the ordered items in the billing table
			map.remove(billTable.getValueAt(billTable.getSelectedRow(), 0));
			((DefaultTableModel) billTable.getModel()).removeRow(billTable.getSelectedRow());

			if (billTable.getRowCount() == 0) {
				// if no items are present empty the table
				DefaultTableModel dtm = (DefaultTableModel) billTable.getModel();
				dtm.setRowCount(0);
				amount = 0;
				tb.setText("");
			}

			else {
				calculateTotal();

				// add a separator row and grand total row
				((DefaultTableModel) billTable.getModel()).addRow(new Object[] { "", "", "", "", "" });
				((DefaultTableModel) billTable.getModel()).addRow(new Object[] { "Grand Total", "", "", "", amount });

				// change grand total value
				billTable.setValueAt(amount, billTable.getRowCount() - 1, 4);
			}
		}

		if (ae.getSource() == clearAll)
			clearOrderEditing();
	}

	public void refreshBill() {
		// remove all rows
		((DefaultTableModel) billTable.getModel()).setRowCount(0);

		// populate using map
		Set set = map.entrySet();
		Iterator iterator = set.iterator();

		String url = "jdbc:sqlite::resource:FastFoodDB.db";

		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection(url);
			String sql = "SELECT * FROM menu_item WHERE item_id = ?";

			PreparedStatement statement = con.prepareStatement(sql);

			while (iterator.hasNext()) {
				Map.Entry me = (Map.Entry) iterator.next();
				statement.setInt(1, (int) me.getKey());

				ResultSet rs = statement.executeQuery();

				((DefaultTableModel) billTable.getModel())
						.addRow(new Object[] { rs.getInt(1), rs.getString(2), ((Integer[]) me.getValue())[0],
								rs.getInt(3), ((Integer[]) me.getValue())[0] * ((Integer[]) me.getValue())[1] });
			}

			calculateTotal();

			// add a separator row and grand total row
			((DefaultTableModel) billTable.getModel()).addRow(new Object[] { "", "", "", "", "" });
			((DefaultTableModel) billTable.getModel()).addRow(new Object[] { "Grand Total", "", "", "", amount });

			con.close();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

		scrBill.setBounds(510, 150, 500, 400);
		this.add(scrBill);
	}
	
	public void calculateTotal() {
		amount = 0;
		// traverse the table and save sum to amt
		if (billTable.getRowCount() != 0) {
			for (int i = 0; i < billTable.getRowCount(); i++) {
				amount = amount + (int) billTable.getValueAt(i, 4);
			}

			tb.setText(Integer.toString(amount));
		}
	}

	// serialize the map
	public static byte[] makebyte(LinkedHashMap<Integer, Integer[]> modeldata) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(modeldata);
			byte[] employeeAsBytes = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(employeeAsBytes);
			return employeeAsBytes;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	// deserialize the map
	public static LinkedHashMap<Integer, Integer[]> read(byte[] data) {
		try {
			ByteArrayInputStream baip = new ByteArrayInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(baip);
			LinkedHashMap<Integer, Integer[]> dataobj = (LinkedHashMap<Integer, Integer[]>) ois.readObject();
			return dataobj;

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	void clearOrderEditing() {
		map.clear();
		tm.setText("");
		tc.setText("");
		tb.setText("");
		amount = 0;
		DefaultTableModel dtm = (DefaultTableModel) billTable.getModel();
		dtm.setRowCount(0);
		isEditing = false;
	}
}
