package org.coep.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class MenuClass extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel leftPanel, upPanel, sepPanel;
	Menu menuPanel;
	Order orderPanel;
	Booking bookPanel;
	ManageOrders viewOrdersPanel;
	JLabel icon, fast, res;
	Font font;
	JButton menu, book, order, logout, manageOrders;
	static int amt = 0;
	
	public MenuClass() {
		setLocation(200, 200);
		setLayout(null);
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setTitle("Fast Food Restaurent Management System");
		font = new Font("Liberation Sans", Font.PLAIN, 35);
		leftPanel = new JPanel();
		leftPanel.setLayout(null);
		upPanel = new JPanel();
		upPanel.setLayout(null);
		menuPanel = new Menu(this);
		orderPanel = new Order(this);
		viewOrdersPanel = new ManageOrders(this, orderPanel);
		bookPanel = new Booking(this);
		sepPanel = new JPanel();

		URL url = this.getClass().getResource("/reicon.jpg");
		icon = new JLabel(new ImageIcon(url));

		menu = new JButton("Menu");
		logout = new JButton("Logout");
		manageOrders = new JButton("Manage Orders");
		book = new JButton("Booking");
		order = new JButton("Order");

		fast = new JLabel("Fast Food");
		res = new JLabel("Restaurent");

		menu.addActionListener(this);
		book.addActionListener(this);
		order.addActionListener(this);
		logout.addActionListener(this);
		manageOrders.addActionListener(this);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit?",
						JOptionPane.YES_NO_OPTION);

				if (resp == JOptionPane.YES_OPTION) {
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					System.exit(0);
				} else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}
			}
		});

		setMyColor();
		addMe();
		setMyPosition();
		setMyFont();

		viewOrdersPanel.setVisible(false);
		setVisible(false);
		menuPanel.setVisible(true);
		menuPanel.refreshMenu(0);
	}

	public void setMyColor() {
		leftPanel.setBackground(new Color(20, 100, 120));
		upPanel.setBackground(new Color(255, 0, 0));
		orderPanel.setBackground(new Color(50, 210, 200));
		viewOrdersPanel.setBackground(new Color(50, 210, 200));
		bookPanel.setBackground(new Color(50, 210, 200));
		sepPanel.setBackground(Color.WHITE);
		fast.setForeground(Color.WHITE);
		res.setForeground(Color.WHITE);
		menu.setForeground(Color.WHITE);
		order.setForeground(Color.WHITE);
		logout.setForeground(Color.WHITE);
		manageOrders.setForeground(Color.WHITE);
		book.setForeground(Color.WHITE);
		menu.setBackground(new Color(20, 100, 120));
		order.setBackground(new Color(20, 100, 120));
		logout.setBackground(new Color(20, 100, 120));
		manageOrders.setBackground(new Color(20, 100, 120));
		book.setBackground(new Color(20, 100, 120));
		menu.setForeground(Color.ORANGE);
	}

	public void addMe() {
		add(leftPanel);
		add(upPanel);
		add(menuPanel);
		leftPanel.add(sepPanel);
		leftPanel.add(icon);
		leftPanel.add(fast);
		leftPanel.add(res);
		leftPanel.add(menu);
		leftPanel.add(order);
		leftPanel.add(book);
		leftPanel.add(logout);
		leftPanel.add(manageOrders);

		add(orderPanel);
		add(viewOrdersPanel);
		add(bookPanel);
		orderPanel.setVisible(false);
		bookPanel.setVisible(false);
	}

	public void setMyPosition() {
		leftPanel.setBounds(1, 1, 300, 700);
		upPanel.setBounds(300, 1, 1300, 50);
		orderPanel.setBounds(300, 50, 1300, 650);
		viewOrdersPanel.setBounds(300, 50, 1300, 650);
		bookPanel.setBounds(300, 50, 1300, 650);
		sepPanel.setBounds(1, 95, 300, 10);
		icon.setBounds(1, 1, 90, 90);
		fast.setBounds(100, 5, 200, 30);
		res.setBounds(100, 55, 200, 30);
		menu.setBounds(60, 150, 150, 50);
		order.setBounds(60, 250, 150, 50);
		manageOrders.setBounds(60, 350, 220, 50);
		book.setBounds(60, 450, 170, 50);
		logout.setBounds(60, 550, 170, 50);
	}

	public void setMyFont() {
		fast.setFont(new Font("Liberation Sans", Font.PLAIN, 35));
		res.setFont(new Font("Liberation Sans", Font.PLAIN, 35));
		menu.setFont(font);
		logout.setFont(font);
		order.setFont(font);
		manageOrders.setFont(new Font("Liberation Sans", Font.BOLD, 24));
		book.setFont(font);

	}

	public void actionPerformed(ActionEvent ae) {
		// side-panel callback for <Menu>
		if (ae.getSource() == menu) {
			menuPanel.setVisible(true);
			bookPanel.setVisible(false);
			orderPanel.setVisible(false);
			viewOrdersPanel.setVisible(false);
			selectMenu(menu);
		}

		// side-panel callback for <Order>
		else if (ae.getSource() == order) {
			menuPanel.setVisible(false);
			bookPanel.setVisible(false);
			orderPanel.setVisible(true);
			viewOrdersPanel.setVisible(false);
			selectMenu(order);
		}

		// side-panel callback for <Logout>
		else if (ae.getSource() == logout) {
			int resp = JOptionPane.showConfirmDialog(null, "Are you sure you want to logout?", "Exit?",
					JOptionPane.YES_NO_OPTION);

			if (resp == JOptionPane.YES_OPTION) {
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				System.exit(0);
			} else {
				setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			}
		}

		// side-panel callback for <Book>
		else if (ae.getSource() == book) {
			menuPanel.setVisible(false);
			bookPanel.setVisible(true);
			orderPanel.setVisible(false);
			viewOrdersPanel.setVisible(false);
			selectMenu(book);
			((DefaultTableModel) bookPanel.tblBooking.getModel()).setRowCount(0);
			bookPanel.addBookings();
		}
		
		else if (ae.getSource() == manageOrders) {
			menuPanel.setVisible(false);
			bookPanel.setVisible(false);
			orderPanel.setVisible(false);
			viewOrdersPanel.setVisible(true);
			viewOrdersPanel.refreshOrders();
			selectMenu(manageOrders);
		}
	}

	public void selectMenu(JButton b) {
		menu.setForeground(Color.WHITE);
		order.setForeground(Color.WHITE);
		book.setForeground(Color.WHITE);
		manageOrders.setForeground(Color.WHITE);
		b.setForeground(Color.ORANGE);
	}
}