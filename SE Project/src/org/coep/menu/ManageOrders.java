package org.coep.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
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

		tblOrders.setModel(ordersModel);
		tblOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
		this.add(scrOrders);
		deleteOrder.setEnabled(false);
	}

	public void setMyPosition() {
		this.setBounds(300, 50, 1300, 650);
		deleteOrder.setBounds(200, 60, 100, 30);
		scrOrders.setBounds(200, 110, 700, 450);
	}

	public void setMyFont() {
		deleteOrder.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
	}

	public void actionPerformed(ActionEvent ae) {
	
	}
}
