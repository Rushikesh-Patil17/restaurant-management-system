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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ManageOrders extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	JTable tblOrders;
	JScrollPane scrOrders;
	JButton deleteOrder;
	static Order order;
	static MenuClass parent;

	public ManageOrders(MenuClass parent, Order order) {
		ManageOrders.order = order;
		ManageOrders.parent = parent;
		this.setLayout(null);

		deleteOrder = new JButton("Delete");
		tblOrders = new JTable();
		scrOrders = new JScrollPane(tblOrders, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		DefaultTableModel ordersModel = new DefaultTableModel(
				new Object[] { "Order ID", "Order Name", "Order Date", "Order Mobile", "Order Bill" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		tblOrders.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (tblOrders.getSelectedRow() != -1) {
					// enable deleteItem button
					deleteOrder.setEnabled(true);
				} else {
					// otherwise disable deleteItem button
					deleteOrder.setEnabled(false);
				}
			}
		});

		tblOrders.setModel(ordersModel);
		tblOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tblOrders.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				final Order order = ManageOrders.order;
				if (me.getClickCount() == 2) {
					order.isEditing = true;
					// clear the add order screen
					ManageOrders.order.tm.setText("");
					ManageOrders.order.tc.setText("");
					ManageOrders.order.tb.setText("");
					Order.amount = 0;
					ManageOrders.order.map.clear();

					// make the add screen visible
					ManageOrders.parent.selectMenu(ManageOrders.parent.order);

					// ManageOrders.parent.setVisible(false);
					ManageOrders.parent.bookPanel.setVisible(false);
					ManageOrders.parent.orderPanel.setVisible(true);

					ManageOrders.this.setVisible(false);

					// read data from database and populate on screen

					String url = "jdbc:sqlite::resource:FastFoodDB.db";
					String x = "SELECT * FROM food_order where _id = "
							+ tblOrders.getValueAt(tblOrders.getSelectedRow(), 0);

					try {
						Class.forName("org.sqlite.JDBC");
						Connection con = DriverManager.getConnection(url);
						Statement st = con.createStatement();

						ResultSet rs = st.executeQuery(x);

						if (rs.next()) {
							Order.toBeUpdated = rs.getInt("_id");
							// add data on screen
							order.tm.setText(rs.getString("order_name"));
							order.tc.setText(rs.getString("order_mobile"));
							order.tb.setText(Integer.toString(rs.getInt("order_bill")));
							order.map = Order.read(rs.getBytes("items"));
							order.refreshBill();
						}

						con.close();
					}

					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});

		setMyColor();
		addMe();
		setMyPosition();
		setMyFont();

		deleteOrder.addActionListener(this);
	}

	public void setMyColor() {
		this.setBackground(new Color(50, 210, 200));

	}

	public void addMe() {
		this.add(deleteOrder);
		deleteOrder.setEnabled(false);
	}

	public void setMyPosition() {
		this.setBounds(300, 50, 1300, 650);
		deleteOrder.setBounds(200, 60, 100, 30);
	}

	public void setMyFont() {
		deleteOrder.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == deleteOrder) {
			String url = "jdbc:sqlite::resource:FastFoodDB.db";

			try {
				int selected = (int) tblOrders.getValueAt(tblOrders.getSelectedRow(), 0);

				// don't update and delete simultaneously
				if (Order.toBeUpdated == selected) {
					order.clearOrderEditing();
				}

				Class.forName("org.sqlite.JDBC");
				Connection con = DriverManager.getConnection(url);
				PreparedStatement statement = con.prepareStatement("delete from food_order where _id = ?");

				statement.setInt(1, selected);
				statement.executeUpdate();
				refreshOrders();

				JOptionPane.showMessageDialog(parent, "Order Deleted Successfully!", "", JOptionPane.INFORMATION_MESSAGE);

				con.close();
			}

			catch (Exception ex) {
				JOptionPane.showMessageDialog(parent, "Could not delete order!", "", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}

	public void refreshOrders() {
		((DefaultTableModel) tblOrders.getModel()).setRowCount(0);

		String url = "jdbc:sqlite::resource:FastFoodDB.db";
		String x = "SELECT * FROM food_order";

		try {
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection(url);
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(x);

			while (rs.next()) {
				((DefaultTableModel) tblOrders.getModel()).addRow(
						new Object[] { rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5) });
			}

			con.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		scrOrders.setBounds(200, 110, 700, 450);
		this.add(scrOrders);
	}
}
