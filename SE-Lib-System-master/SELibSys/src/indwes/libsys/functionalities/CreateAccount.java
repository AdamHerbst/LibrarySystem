package indwes.libsys.functionalities;

import java.awt.EventQueue;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;

import indwes.libsys.dao.LibraryDao;
import indwes.libsys.main.SqlConnection;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Choice;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Button;

public class CreateAccount {

	JFrame frmCreateANew;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount window = new CreateAccount();
					window.frmCreateANew.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	Connection connection = null;
	public CreateAccount() {
		initialize();
		connection = SqlConnection.dbConnect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateANew = new JFrame();
		frmCreateANew.setTitle("Library Management System");
		frmCreateANew.setBounds(100, 100, 450, 300);
		frmCreateANew.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Liberation Serif", Font.BOLD, 16));
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Liberation Serif", Font.BOLD, 16));
		
		JTextField txtFieldUsername = new JTextField();
		txtFieldUsername.setColumns(10);
		
		
		JPasswordField txtFieldPassword;
		txtFieldPassword = new JPasswordField();
		txtFieldPassword.setEchoChar('#');
		txtFieldPassword.setColumns(10);
		
		JCheckBox chckbxLibrarian = new JCheckBox("Librarian?");
		chckbxLibrarian.setFont(new Font("Liberation Serif", Font.BOLD, 16));
		chckbxLibrarian.setSelected(true);
		
		
		JButton LoginButton = new JButton("Register");
		LoginButton.setFont(new Font("Liberation Serif", Font.BOLD, 16));
		
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 String username = txtFieldUsername.getText();
				 String password = String.copyValueOf(txtFieldPassword.getPassword());
		
				 if(username.contentEquals("")) {
					 JOptionPane.showMessageDialog(null, "Please enter a username!");
				 }
				 else if (password.equals("")) {
					 JOptionPane.showMessageDialog(null, "Please enter a password!");
				 }
				 else if(LibraryDao.checkUsername(username)) {
					 JOptionPane.showMessageDialog(null, "This username already exists");
				 }
				
				 else {
				
				 
				try {
	
					 // Functionality is included in the LibraryDao class 
					int x = LibraryDao.createAccount(username, password);
					int y = LibraryDao.createUserAccount(username, password);
					
					
					// Opens up the librarian view for the new added user
					if (x > 0 && chckbxLibrarian.isSelected()) {
						 JOptionPane.showMessageDialog(null, "New user added!");
						 LibrarianView window = new LibrarianView();
						 window.UIFrame.setVisible(true);
					 }
					
					// Opens up the user view for the new added user
					else if (y > 0 && !chckbxLibrarian.isSelected()) {
						 JOptionPane.showMessageDialog(null, "New user added!");
						 	UserView window = new UserView();
							window.UIFrame.setVisible(true);
					 }
					 else {
						 JOptionPane.showMessageDialog(null, "Something went wrong :(");
					}
				}
				
				catch(Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				 }
				
			}});
		
		JButton button = new JButton("<");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login window = new Login();
				window.frmLogin.setVisible(true);
			}
		});
		
		JLabel lblCreateAnAccount = new JLabel("Create an Account");
		GroupLayout groupLayout = new GroupLayout(frmCreateANew.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addGap(63)
							.addComponent(txtFieldPassword, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addComponent(chckbxLibrarian, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(173)
							.addComponent(LoginButton, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(button))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(40)
							.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(43)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCreateAnAccount)
								.addComponent(txtFieldUsername, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(85, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button)
							.addGap(35))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCreateAnAccount)
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtFieldUsername, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtFieldPassword, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addComponent(chckbxLibrarian, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(LoginButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
		);
		frmCreateANew.getContentPane().setLayout(groupLayout);
		
		

		
	}
}
