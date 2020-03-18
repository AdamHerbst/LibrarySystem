package indwes.libsys.functionalities;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import indwes.libsys.dao.LibraryDAO;
import indwes.libsys.main.SqlConnection;

public class Login {

	private JFrame LFrame;
	private JButton createAccountButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.LFrame.setVisible(true);
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
	 * Initialize the contents of the LFrame.
	 */
	private void initialize() {
		LFrame = new JFrame();
		LFrame.setBounds(100, 100, 450, 300);
		LFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LFrame.getContentPane().setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(40, 68, 90, 15);
		LFrame.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(40, 143, 70, 15);
		LFrame.getContentPane().add(passwordLabel);
		
		txtFieldUsername = new JTextField();
		txtFieldUsername.setBounds(173, 60, 192, 31);
		LFrame.getContentPane().add(txtFieldUsername);
		txtFieldUsername.setColumns(10);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					 String username = txtFieldUsername.getText();
					 String password = txtFieldPassword.getText();
					 
					 // FUnctionality is included in the LibraryDao class
					int x = LibraryDAO.login(username, password);
					if (x == 0) {
						 JOptionPane.showMessageDialog(null, "Username and password are correct!");
						 UserInterface window = new UserInterface();
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
		LoginButton.setBounds(139, 204, 117, 25);
		LFrame.getContentPane().add(LoginButton);
		
		createAccountButton = new JButton("Create Account");
		createAccountButton.setBounds(284, 204, 154, 25);
		LFrame.getContentPane().add(createAccountButton);
		
		txtFieldPassword = new JPasswordField();
		txtFieldPassword.setEchoChar('#');
		txtFieldPassword.setBounds(173, 135, 192, 31);
		LFrame.getContentPane().add(txtFieldPassword);
		txtFieldPassword.setColumns(10);
	}
}

