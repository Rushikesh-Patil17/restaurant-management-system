package org.coep.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.coep.objects.MenuItem;

public class Menu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JButton vegm, nonvegm, dessertm, drinksm;
	JLabel menuhead;
	JButton addMenu, deleteMenu;
	JTable tbl;
	JScrollPane scr;
	Font f;
	JFrame parent;

	static int toBeDeleted = -1;
	static int currentType = 0;

	public Menu(JFrame parent) {
		this.setLayout(null);
		this.parent = parent;

		menuhead = new JLabel("MENU");
		vegm = new JButton("Vegetarian");
		nonvegm = new JButton("Non-Vegetarian");
		dessertm = new JButton("Dessert");
		drinksm = new JButton("Drinks");
		addMenu = new JButton("Add");
		deleteMenu = new JButton("Delete");

		tbl = new JTable();
		scr = new JScrollPane(tbl, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		vegm.addActionListener(this);
		nonvegm.addActionListener(this);
		dessertm.addActionListener(this);
		drinksm.addActionListener(this);
		addMenu.addActionListener(this);
		deleteMenu.addActionListener(this);

		tbl.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (tbl.getSelectedRow() != -1) {
					// enable deleteMenu button
					deleteMenu.setEnabled(true);
					toBeDeleted = Integer.parseInt(tbl.getValueAt(tbl.getSelectedRow(), 0).toString());
				}
			}
		});

		tbl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (me.getClickCount() == 2) {
					int row = tbl.getSelectedRow(); // select a row
					// create object to pass
					int id = (int) tbl.getValueAt(row, 0);
					String name = tbl.getValueAt(row, 1).toString();
					int price = (int) tbl.getValueAt(row, 2);
					int type = currentType;
					boolean isAvailable = (boolean) tbl.getValueAt(row, 3);

					MenuItem item = new MenuItem(id, name, price, type, isAvailable);
					new AddMenuItem(Menu.this, item);
				}
			}
		});

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		f = new Font("Liberation Sans", Font.PLAIN, 35);

		setMyColor();
		addMe();
		setMyPosition();
		setMyFont();
	}

	public void actionPerformed(ActionEvent ae) {
		// Callback for <Menu> -> <Veg Menu>
		if (ae.getSource() == vegm) {
			refreshMenu(0);
		}

		// Callback for <Menu> -> <NonVeg Menu>
		else if (ae.getSource() == nonvegm) {
			refreshMenu(1);
		}

		// Callback for <Menu> -> <Dessert Menu>
		else if (ae.getSource() == dessertm) {
			refreshMenu(2);
		}

		// Callback for <Menu> -> <Drinks Menu>
		else if (ae.getSource() == drinksm) {
			refreshMenu(3);
		}

		if (ae.getSource() == addMenu) {
			new AddMenuItem(this, null);
		}

		if (ae.getSource() == deleteMenu) {
			if (toBeDeleted != -1) {
				// delete the selected row
				String url = "jdbc:sqlite::resource:FastFoodDB.db";

				try {
					Class.forName("org.sqlite.JDBC");
					Connection con = DriverManager.getConnection(url);
					PreparedStatement statement = con.prepareStatement("DELETE FROM menu_item WHERE item_id = ?");

					statement.setInt(1, toBeDeleted);
					statement.executeUpdate();

					refreshMenu(currentType);

					JOptionPane.showMessageDialog(parent, "Item Deleted Successfully!", "",
							JOptionPane.INFORMATION_MESSAGE);

					con.close();
				}

				catch (Exception ex) {
					JOptionPane.showMessageDialog(parent, "Could not delete item!", "", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		}
	}

	public void setColor() {
		this.setBackground(new Color(50, 210, 200));
		menuhead.setForeground(Color.BLACK);
		menuhead.setBackground(new Color(20, 100, 120));
		vegm.setBackground(new Color(255, 0, 0));
		nonvegm.setBackground(new Color(255, 0, 0));
		dessertm.setBackground(new Color(255, 0, 0));
		drinksm.setBackground(new Color(255, 0, 0));
		vegm.setForeground(Color.WHITE);
		nonvegm.setForeground(Color.WHITE);
		dessertm.setForeground(Color.WHITE);
		drinksm.setForeground(Color.WHITE);
	}

	public void refreshMenu(int type) {
		// disable delete button
		deleteMenu.setEnabled(false);
		currentType = type;

		String url = "jdbc:sqlite::resource:FastFoodDB.db";
		String x = "SELECT * FROM menu_item WHERE item_type = " + type;

		String t = "";

		switch (type) {
		case 0:
			t = "Veg";
			break;
		case 1:
			t = "Non-Veg";
			break;
		case 2:
			t = "Dessert";
			break;
		case 3:
			t = "Drink";
			break;
		}

		DefaultTableModel model = new DefaultTableModel(
				new Object[] { t + " ID", t + " Name", t + " Price", "Availability" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				switch (columnIndex) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return Boolean.class;
				}

				return String.class;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tbl.setModel(model);

		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();
			Boolean b;

			ResultSet rs = st.executeQuery(x);

			while (rs.next()) {
				b = (rs.getInt(5) == 1) ? Boolean.valueOf(true) : Boolean.valueOf(false);
				model.addRow(new Object[] { rs.getInt(1), rs.getString(2), rs.getInt(3), b });
			}

			con.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		scr.setBounds(500, 110, 450, 450);
		this.add(scr);
	}

	public void setMyColor() {
		this.setBackground(new Color(50, 210, 200));
		menuhead.setForeground(Color.BLACK);
		menuhead.setBackground(new Color(20, 100, 120));

		vegm.setBackground(new Color(255, 0, 0));
		nonvegm.setBackground(new Color(255, 0, 0));
		dessertm.setBackground(new Color(255, 0, 0));
		drinksm.setBackground(new Color(255, 0, 0));

		vegm.setForeground(Color.WHITE);
		nonvegm.setForeground(Color.WHITE);
		dessertm.setForeground(Color.WHITE);
		drinksm.setForeground(Color.WHITE);
	}

	public void addMe() {
		this.add(menuhead);
		this.add(vegm);
		this.add(nonvegm);
		this.add(dessertm);
		this.add(drinksm);
		this.add(addMenu);
		this.add(deleteMenu);
		deleteMenu.setEnabled(false);
	}

	public void setMyPosition() {
		this.setBounds(300, 50, 1300, 650);
		menuhead.setBounds(50, 20, 200, 40);
		vegm.setBounds(10, 110, 350, 70);
		nonvegm.setBounds(10, 240, 350, 70);
		dessertm.setBounds(10, 360, 350, 70);
		drinksm.setBounds(10, 490, 350, 70);
		addMenu.setBounds(500, 60, 100, 40);
		deleteMenu.setBounds(650, 60, 100, 40);
	}

	public void setMyFont() {
		menuhead.setFont(new Font("Liberation Sans", Font.PLAIN, 40));
		vegm.setFont(f);
		nonvegm.setFont(f);
		dessertm.setFont(f);
		drinksm.setFont(f);
		addMenu.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		deleteMenu.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
	}
}