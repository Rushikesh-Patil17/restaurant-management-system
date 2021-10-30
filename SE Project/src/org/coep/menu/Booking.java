package org.coep.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

public class Booking extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	JScrollPane scrBooking;
	JButton bookBook, bookDelete, bookCancel;
	JLabel bookName, bookEmail, bookPhone, bookPeople, bookDate;
	JTextField tname, temail, tphone;
	JSpinner noOfPeople;
	JTable tblBooking;
	JButton deleteBooking;
	DatePicker datePicker;
	MenuClass parent;
	
	public Booking(MenuClass parent) {
		this.parent = parent;
		this.setLayout(null);
		setLayout(null);		
		deleteBooking = new JButton("Delete");

		bookName = new JLabel("Name");
		bookEmail = new JLabel("Email");
		bookPhone = new JLabel("Contact");
		bookPeople = new JLabel("No. of People");
		bookDate = new JLabel("Date");
		tname = new JTextField(30);
		temail = new JTextField(30);
		tphone = new JTextField(30);

		bookBook = new JButton("Book");
		bookDelete = new JButton("Delete");
		bookCancel = new JButton("Cancel");

		noOfPeople = new JSpinner();
		bookBook.addActionListener(this);
		bookCancel.addActionListener(this);
		bookDelete.addActionListener(this);
		deleteBooking.addActionListener(this);

		tblBooking = new JTable();
		
		DefaultTableModel modelBooking = new DefaultTableModel(new Object[] { "Booking ID", "Booker Name",
				"Booking Date", "Booking Mobile", "Booking Email", "No. of People" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		scrBooking = new JScrollPane(tblBooking, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		SpinnerModel v = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
		noOfPeople.setModel(v);

		// setup date picker settings
		DatePickerSettings datePickerSettings = new DatePickerSettings();

		datePicker = new DatePicker(datePickerSettings);
		datePicker.setBounds(0, 0, 200, 30);
		datePickerSettings.setDateRangeLimits(LocalDate.now(), LocalDate.now().plusDays(365));
		datePickerSettings.setAllowKeyboardEditing(false);
		datePickerSettings.setFormatForDatesCommonEra("dd/MM/yyyy, EEE");
			
		tblBooking.setModel(modelBooking);
		
		setMyColor();
		addMe();
		setMyPosition();
		setMyFont();
	}

	public void setMyColor() {
		this.setBackground(new Color(50, 210, 200));
		bookName.setForeground(Color.BLACK);
		bookEmail.setForeground(Color.BLACK);
		bookPhone.setForeground(Color.BLACK);
		bookPeople.setForeground(Color.BLACK);
		bookDate.setForeground(Color.BLACK);
		bookBook.setForeground(Color.WHITE);
		bookCancel.setForeground(Color.WHITE);
		bookDelete.setForeground(Color.WHITE);
		bookBook.setBackground(new Color(255, 0, 0));
		bookCancel.setBackground(new Color(255, 0, 0));
		bookDelete.setBackground(new Color(255, 0, 0));
	}

	public void addMe() {		
		this.add(bookName);
		this.add(bookEmail);
		this.add(bookPhone);
		this.add(bookPeople);
		this.add(bookDate);
		this.add(bookBook);
		this.add(bookCancel);
		this.add(tname);
		this.add(temail);
		this.add(tphone);
		this.add(noOfPeople);
		this.add(datePicker);
		this.add(deleteBooking);
		this.add(scrBooking);
		deleteBooking.setEnabled(false);
	}

	public void setMyPosition() {
		this.setBounds(300, 50, 1300, 650);
		deleteBooking.setBounds(420, 15, 100, 30);
		bookName.setBounds(20, 50, 230, 30);
		bookEmail.setBounds(20, 150, 200, 30);
		bookPhone.setBounds(20, 250, 200, 30);
		bookPeople.setBounds(20, 350, 200, 30);
		bookDate.setBounds(20, 450, 250, 30);
		bookBook.setBounds(20, 520, 100, 40);
		bookCancel.setBounds(150, 520, 100, 40);
		bookDelete.setBounds(280, 520, 120, 40);
		tname.setBounds(150, 50, 250, 40);
		temail.setBounds(150, 150, 250, 40);
		tphone.setBounds(150, 250, 250, 40);
		noOfPeople.setBounds(150, 350, 100, 40);
		datePicker.setBounds(150, 450, 250, 30);
		scrBooking.setBounds(420, 50, 580, 500);
	}

	public void setMyFont() {
		deleteBooking.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		bookName.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		bookEmail.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		bookPhone.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		bookPeople.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		bookDate.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		bookBook.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		bookCancel.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		bookDelete.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		tname.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		temail.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		tphone.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		noOfPeople.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
		datePicker.setFont(new Font("Liberation Sans", Font.PLAIN, 20));
	}

	public void actionPerformed(ActionEvent ae) {
		// TODO: Handle callbacks
	}
}
