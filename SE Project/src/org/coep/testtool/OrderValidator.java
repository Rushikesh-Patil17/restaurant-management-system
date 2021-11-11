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
import org.coep.objects.OrderClass;

public class OrderValidator {
	private OrderClass order;
	private MenuClass parent;
	private JTable t;
	private JScrollPane sc;

	public OrderValidator(OrderClass order, MenuClass parent) {
		this.order = order;
		this.parent = parent;
	}

	public boolean isValid() {
		boolean isValid = true;
		String message = "";
		createUI();

		// check for customer name
		if (nameTestCase()) {
			message = "Name should not be empty and must contain only alphabates!";
			addRow("Name", order.getName(), "FAIL", message);
			isValid = false;
		} else {
			addRow("Name", order.getName(), "PASS", "-");
		}

		// check for mobile no.
		if (mobileTestCase()) {
			message = "Mobile Number should be 10-digit starting with 7, 8 or 9!";
			addRow("Conatct", order.getMobile(), "FAIL", message);
			isValid = false;
		} else {
			addRow("Contact", order.getMobile(), "PASS", "-");
		}

		// check for itemCount
		if (itemCountTestCase()) {
			message = "Select at least one item!";
			addRow("itemCount", Integer.toString(order.getItemCount()), "FAIL", message);
			isValid = false;
		} else {
			addRow("itemCount", Integer.toString(order.getItemCount()), "PASS", "-");
		}

		addRow("", "", "", "");
		
		if (isValid)
			addRow("<html>Overall Status: <b>PASS</b></html>", "", "", "");
		else
			addRow("<html>Overall Status: <b>FAIL</b></html>", "", "", "");
		
		UIManager.put("OptionPane.minimumSize", new Dimension(750, 300));
		JOptionPane.showMessageDialog(this.parent, sc, "Order Test Cases", JOptionPane.PLAIN_MESSAGE, null);
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
		Matcher nameMatcher = namePattern.matcher(order.getName());

		// check for customer name
		return order.getName().isEmpty() || (!nameMatcher.matches());
	}

	private boolean mobileTestCase() {
		// validations for mobile no. field
		Pattern mobilePattern = Pattern.compile("^[789]\\d{9}$");
		Matcher mobileMatcher = mobilePattern.matcher(order.getMobile());

		return order.getMobile().isEmpty() || (!mobileMatcher.matches());
	}

	private boolean itemCountTestCase() {
		// validations for itemCount
		return order.getItemCount() == 0;
	}
}
