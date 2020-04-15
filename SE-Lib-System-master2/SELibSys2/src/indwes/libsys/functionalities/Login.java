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
		frmLogin.setBounds(100, 100, 674, 444);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(169, 170, 90, 15);
		frmLogin.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(169, 248, 70, 15);
		frmLogin.getContentPane().add(passwordLabel);
		
		txtFieldUsername = new JTextField();
		txtFieldUsername.setBounds(299, 162, 192, 31);
		frmLogin.getContentPane().add(txtFieldUsername);
		txtFieldUsername.setColumns(10);
		
		
		JCheckBox checkboxlibrarian = new JCheckBox("Librarian?");
		checkboxlibrarian.setBounds(157, 281, 129, 23);
		frmLogin.getContentPane().add(checkboxlibrarian);
		checkboxlibrarian.setSelected(true);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.setBounds(177, 331, 117, 25);
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					 String username = txtFieldUsername.getText();
					 String password = txtFieldPassword.getText();
			
//						UserView.getUserName(username);
				
					
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
		frmLogin.getContentPane().add(LoginButton);
		

		
		createAccountButton = new JButton("Sign up");
		createAccountButton.setBounds(360, 331, 154, 25);
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateAccount window = new CreateAccount();
				window.frmCreateANew.setVisible(true);
			}
		});
		frmLogin.getContentPane().add(createAccountButton);
		
		txtFieldPassword = new JPasswordField();
		txtFieldPassword.setBounds(299, 240, 192, 31);
		txtFieldPassword.setEchoChar('#');
		frmLogin.getContentPane().add(txtFieldPassword);
		txtFieldPassword.setColumns(10);
		
		JLabel lblWelcome = new JLabel("Welcome to the Library!");
		lblWelcome.setFont(new Font("Serif", Font.BOLD, 30));
		lblWelcome.setBounds(133, 12, 423, 31);
		frmLogin.getContentPane().add(lblWelcome);
		
		JLabel lblSignIn = new JLabel("Please sign in or sign up to browse and check out books.");
		lblSignIn.setBounds(138, 80, 498, 15);
		frmLogin.getContentPane().add(lblSignIn);

		

	}
}
