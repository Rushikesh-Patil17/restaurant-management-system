package org.coep.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class Menu extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	JButton vegm, nonvegm, dessertm, drinksm;
	JLabel menuhead;
	JButton addMenu, deleteMenu;
	JTable tbl;
	JScrollPane scr;
	Font f;
	JFrame parent;
	
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

		tbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		f = new Font("Liberation Sans", Font.PLAIN, 35);
		
		DefaultTableModel model = new DefaultTableModel(
				new Object[] { "ID", "Name", "Price", "Availability" }, 0) {
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

		setMyColor();
		addMe();
		setMyPosition();
		setMyFont();
	}
	
	public void actionPerformed(ActionEvent ae) {
		// TODO: Handle callbacks
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
		this.add(scr);
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
		scr.setBounds(500, 110, 450, 450);
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
