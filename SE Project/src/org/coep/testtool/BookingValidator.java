package org.coep.testtool;

import java.awt.Component;
import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.coep.menu.MenuClass;
import org.coep.objects.BookingClass;

public class BookingValidator {
	private BookingClass booking;
	private MenuClass parent;
	private JTable t;
	private JScrollPane sc;

	public BookingValidator(BookingClass booking, MenuClass parent) {
		this.booking = booking;
		this.parent = parent;
	}

	public boolean isValid() {
		boolean isValid = true;
		String message = "";
		createUI();

		// check for customer name
		if (nameTestCase()) {
			message = "Name should not be empty and must contain only alphabates!";
			addRow("Name", booking.getName(), "FAIL", message);
			isValid = false;
		} else {
			addRow("Name", booking.getName(), "PASS", "-");
		}

		// check for mobile no.
		if (mobileTestCase()) {
			message = "Mobile Number should be 10-digit starting with 7, 8 or 9!";
			addRow("Conatct", booking.getMobile(), "FAIL", message);
			isValid = false;
		} else {
			addRow("Contact", booking.getMobile(), "PASS", "-");
		}

		// check for email
		if (emailTestCase()) {
			message = "Email address must be in the proper format!";
			addRow("Email", booking.getEmail(), "FAIL", message);
			isValid = false;
		} else {
			addRow("Email", booking.getEmail(), "PASS", "-");
		}

		if (dateTestCase()) {
			message = "Date must be selected!";
			addRow("Date", booking.getDate(), "FAIL", message);
			isValid = false;
		} else {
			addRow("Date", booking.getDate(), "PASS", "-");
		}
		
		addRow("", "", "", "");
		
		if (isValid)
			addRow("<html>Overall Status: <b>PASS</b></html>", "", "", "");
		else
			addRow("<html>Overall Status: <b>FAIL</b></html>", "", "", "");
		
		UIManager.put("OptionPane.minimumSize", new Dimension(750, 0));
		JOptionPane.showMessageDialog(this.parent, sc, "Booking Test Cases", JOptionPane.PLAIN_MESSAGE, null);
		UIManager.put("OptionPane.minimumSize", null);

		return isValid;
	}

	private void addRow(String field, String data, String status, String message) {
		((DefaultTableModel) t.getModel()).addRow(new Object[] { field, data, status, message });
	}

	private void createUI() {
		DefaultTableModel modelValidation = new DefaultTableModel(
				new Object[] { "Field Name", "Data", "Status", "Message" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		t = new JTable(modelValidation) {
			private static final long serialVersionUID = 1L;

			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component component = super.prepareRenderer(renderer, row, column);
				int rendererWidth = component.getPreferredSize().width;
				TableColumn tableColumn = getColumnModel().getColumn(column);
				tableColumn.setPreferredWidth(
						Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
				return component;
			}
		};

		t.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		sc = new JScrollPane(t, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	private boolean nameTestCase() {
		// validations for name field
		Pattern namePattern = Pattern.compile("^[A-Za-z]+([\\ A-Za-z]+)*");
		Matcher nameMatcher = namePattern.matcher(booking.getName());

		// check for customer name
		return booking.getName().isEmpty() || (!nameMatcher.matches());
	}

	private boolean mobileTestCase() {
		// validations for mobile no. field
		Pattern mobilePattern = Pattern.compile("^[789]\\d{9}$");
		Matcher mobileMatcher = mobilePattern.matcher(booking.getMobile());

		return booking.getMobile().isEmpty() || (!mobileMatcher.matches());
	}

	private boolean emailTestCase() {
		// validations for email field
		Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
		Matcher emailMatcher = emailPattern.matcher(booking.getEmail());

		return booking.getEmail().isEmpty() || (!emailMatcher.matches());
	}

	private boolean dateTestCase() {
		// validations for date field
		return booking.getDate().trim().isEmpty();
	}
}
