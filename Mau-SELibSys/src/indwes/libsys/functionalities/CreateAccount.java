package indwes.libsys.functionalities;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import indwes.libsys.main.SqlConnection;

public class CreateAccount {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount window = new CreateAccount();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Liberation Serif", Font.BOLD, 16));
		usernameLabel.setBounds(40, 68, 90, 15);
		frame.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Liberation Serif", Font.BOLD, 16));
		passwordLabel.setBounds(40, 143, 70, 15);
		frame.getContentPane().add(passwordLabel);
		
		JTextField txtFieldUsername = new JTextField();
		txtFieldUsername.setBounds(173, 60, 192, 31);
		frame.getContentPane().add(txtFieldUsername);
		txtFieldUsername.setColumns(10);
		
		
		JPasswordField txtFieldPassword;
		txtFieldPassword = new JPasswordField();
		txtFieldPassword.setEchoChar('#');
		txtFieldPassword.setBounds(173, 135, 192, 31);
		frame.getContentPane().add(txtFieldPassword);
		txtFieldPassword.setColumns(10);
		
		JCheckBox chckbxLibrarian = new JCheckBox("Librarian?");
		chckbxLibrarian.setFont(new Font("Liberation Serif", Font.BOLD, 16));
		chckbxLibrarian.setBounds(38, 180, 129, 23);
		frame.getContentPane().add(chckbxLibrarian);
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
					if (x > 0 && chckbxLibrarian.isSelected()) {
						 JOptionPane.showMessageDialog(null, "New user added!");
						 LibrarianView window = new LibrarianView();
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
		LoginButton.setBounds(173, 210, 117, 25);
		frame.getContentPane().add(LoginButton);
		
		

		
	}
}
