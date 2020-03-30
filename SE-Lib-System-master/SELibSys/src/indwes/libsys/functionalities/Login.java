package indwes.libsys.functionalities;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import java.awt.EventQueue;

import indwes.libsys.dao.LibraryDao;
import indwes.libsys.main.SqlConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Scanner;
import java.sql.*;

public class Login {

	JFrame frmLogin;
	private JButton createAccountButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection connection = null;
	private JTextField txtFieldUsername;
	private JPasswordField txtFieldPassword;
	
	
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = SqlConnection.dbConnect(); // Calling the connection class
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Library Management System");
		frmLogin.setBounds(100, 100, 516, 312);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(80, 65, 90, 15);
		frmLogin.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(70, 142, 70, 15);
		frmLogin.getContentPane().add(passwordLabel);
		
		txtFieldUsername = new JTextField();
		txtFieldUsername.setBounds(212, 42, 192, 31);
		frmLogin.getContentPane().add(txtFieldUsername);
		txtFieldUsername.setColumns(10);
		
		
		JCheckBox checkboxlibrarian = new JCheckBox("Librarian?");
		checkboxlibrarian.setBounds(70, 161, 129, 23);
		frmLogin.getContentPane().add(checkboxlibrarian);
		checkboxlibrarian.setSelected(true);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					 String username = txtFieldUsername.getText();
					 String password = txtFieldPassword.getText();
					 
					 // Functionality is included in the LibraryDao class
					boolean x = LibraryDao.login(username, password);
					if (x == true && checkboxlibrarian.isSelected()) {
						 JOptionPane.showMessageDialog(null, "Username and password are correct!");
						 LibrarianView window = new LibrarianView();
						window.UIFrame.setVisible(true);

					 }
					else if(x == true && !checkboxlibrarian.isSelected()) {
						 JOptionPane.showMessageDialog(null, "Username and password are correct!");
						 UserView window = new UserView();
						window.UIFrame.setVisible(true);

					 }
					 else {
						 JOptionPane.showMessageDialog(null, "Username and password are not correct! Please try again.");
					}
				}
				
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
				
			}});
		LoginButton.setBounds(90, 211, 117, 25);
		frmLogin.getContentPane().add(LoginButton);
		

		
		createAccountButton = new JButton("Sign up");
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateAccount window = new CreateAccount();
				window.frmCreateANew.setVisible(true);
			}
		});
		createAccountButton.setBounds(273, 211, 154, 25);
		frmLogin.getContentPane().add(createAccountButton);
		
		txtFieldPassword = new JPasswordField();
		txtFieldPassword.setEchoChar('#');
		txtFieldPassword.setBounds(212, 120, 192, 31);
		frmLogin.getContentPane().add(txtFieldPassword);
		txtFieldPassword.setColumns(10);

		

	}
}

