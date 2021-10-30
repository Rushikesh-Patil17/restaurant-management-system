package org.coep.main;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.coep.menu.MenuClass;

import com.formdev.flatlaf.FlatLightLaf;

class LoginPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JLabel heading1, heading2, heading3, login, loginhead, pass;
	JTextField logint;
	JPasswordField passt;
	JButton submit;
	JPanel p;
	Font f;
	MenuClass m2;

	LoginPage(MenuClass m1) {
		m2 = m1;
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		setLocation(200, 100);
		setLayout(null);
		setTitle("Fast Food Restaurent Management System");
		f = new Font("Liberation Sans", Font.PLAIN, 25);

		URL url = this.getClass().getResource("/pizza.jpg");
		setContentPane(new JLabel(new ImageIcon(url)));

		heading1 = new JLabel("Fast Food ");
		heading2 = new JLabel("Restaurent ");
		heading3 = new JLabel("Management System");
		login = new JLabel("Username");
		pass = new JLabel("Password");
		loginhead = new JLabel("Login");
		p = new JPanel();
		logint = new JTextField(30);

		passt = new JPasswordField();
		submit = new JButton("Submit");

		setMyPosition();
		setMyColor();
		setMyFont();
		addMe();

		submit.addActionListener(this);
		logint.addActionListener(this);
		passt.addActionListener(this);

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

		setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		String x = String.valueOf(passt.getPassword()).trim();
		String x2 = logint.getText().trim();

		if (ae.getSource() == logint) {
			if (logint.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Invalid Username!");
				logint.requestFocus();
			} else {
				passt.requestFocus();
			}
		} else if (ae.getSource() == passt) {
			if (x.equals("")) {
				JOptionPane.showMessageDialog(null, "Invalid Password!");
				passt.requestFocus();
			} else {
				submit.requestFocus();
			}
		} else if (ae.getSource() == submit) {

			if (x2.equals("coep") && x.equals("coep")) {
				JOptionPane.showMessageDialog(null, "Login Successful!");
				m2.setVisible(true);
				this.setVisible(false);

			} else {
				JOptionPane.showMessageDialog(null, "Incorrect Username or Password. Try Again.");
			}
		}
	}

	public void addMe() {
		add(heading2);
		add(heading1);
		add(heading3);
		add(loginhead);
		add(p);
		add(login);
		add(pass);
		add(logint);
		add(passt);
		add(submit);
	}

	public void setMyColor() {
		heading1.setForeground(Color.WHITE);
		heading2.setForeground(Color.WHITE);
		heading3.setForeground(Color.WHITE);
		loginhead.setForeground(Color.WHITE);
		login.setForeground(Color.WHITE);
		pass.setForeground(Color.WHITE);
		submit.setForeground(Color.WHITE);
		logint.setForeground(Color.BLACK);
		p.setBackground(Color.WHITE);
		submit.setBackground(Color.RED);
	}

	public void setMyPosition() {
		heading1.setBounds(750, 10, 300, 40);
		heading2.setBounds(790, 60, 360, 60);
		heading3.setBounds(840, 130, 550, 60);
		loginhead.setBounds(80, 190, 100, 40);
		p.setBounds(80, 240, 450, 8);
		login.setBounds(80, 290, 190, 30);
		pass.setBounds(80, 360, 170, 30);
		logint.setBounds(280, 290, 250, 40);
		passt.setBounds(280, 360, 250, 40);
		submit.setBounds(130, 440, 350, 40);
	}

	public void setMyFont() {
		heading1.setFont(new Font("Liberation Sans", Font.BOLD, 40));
		heading2.setFont(new Font("Liberation Sans", Font.BOLD, 40));
		heading3.setFont(new Font("Liberation Sans", Font.BOLD, 40));

		loginhead.setFont(new Font("Liberation Sans", Font.BOLD, 35));
		login.setFont(new Font("Liberation Sans", Font.BOLD, 35));
		pass.setFont(new Font("Liberation Sans", Font.BOLD, 35));
		submit.setFont(new Font("Liberation Sans", Font.BOLD, 30));
		logint.setFont(f);
		passt.setFont(f);
	}
}

class Main {
	public static void main(String args[]) {
		// set up look and feel
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (Exception ex) {
			System.err.println("Failed to initialize LaF");
		}

		new MenuClass();
		new LoginPage(new MenuClass());
	}
}
