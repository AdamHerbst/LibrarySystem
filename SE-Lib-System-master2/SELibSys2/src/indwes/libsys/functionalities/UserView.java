package indwes.libsys.functionalities;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import indwes.libsys.main.SqlConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class UserView {

	JFrame UIFrame;
	private JTextField bookTitleTxtField;
	private JTextField authorNameTxtField;
	private JTextField quantityTxtField;
	private JTextField bookIDTxtField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView window = new UserView();
					window.UIFrame.setVisible(true);
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
	public UserView() {
		initialize();
		connection = SqlConnection.dbConnect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		UIFrame = new JFrame();
		UIFrame.setTitle("User View");
		UIFrame.setBounds(100, 100, 788, 561);
		UIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIFrame.getContentPane().setLayout(null);
		
		JLabel bookTitleLabel = new JLabel("Book Title:");
		bookTitleLabel.setBounds(37, 39, 86, 15);
		UIFrame.getContentPane().add(bookTitleLabel);
		
		JLabel authorNameLabel = new JLabel("Author Name:");
		authorNameLabel.setBounds(37, 70, 103, 15);
		UIFrame.getContentPane().add(authorNameLabel);
		
		JLabel quantityLabel = new JLabel("Quantity:");
		quantityLabel.setBounds(37, 95, 86, 15);
		UIFrame.getContentPane().add(quantityLabel);
		
		bookTitleTxtField = new JTextField();
		bookTitleTxtField.setBounds(146, 37, 135, 19);
		UIFrame.getContentPane().add(bookTitleTxtField);
		bookTitleTxtField.setColumns(10);
		
		authorNameTxtField = new JTextField();
		authorNameTxtField.setBounds(146, 68, 135, 19);
		UIFrame.getContentPane().add(authorNameTxtField);
		authorNameTxtField.setColumns(10);
		
		quantityTxtField = new JTextField();
		quantityTxtField.setBounds(146, 93, 135, 19);
		quantityTxtField.setColumns(10);
		UIFrame.getContentPane().add(quantityTxtField);
		

		JLabel bookIDLabel = new JLabel("Book ID:");
		bookIDLabel.setBounds(37, 12, 86, 15);
		UIFrame.getContentPane().add(bookIDLabel);
		
		bookIDTxtField = new JTextField();
		//bookIDTxtField.setText();
		bookIDTxtField.setColumns(10);
		bookIDTxtField.setBounds(146, 10, 135, 19);
		UIFrame.getContentPane().add(bookIDTxtField);
		UIFrame.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 137, 691, 365);
		UIFrame.getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton checkoutButton = new JButton("Check Out");
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		checkoutButton.setBounds(313, 25, 106, 42);
		UIFrame.getContentPane().add(checkoutButton);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search();
			}
		});
		searchButton.setBounds(323, 81, 99, 42);
		UIFrame.getContentPane().add(searchButton);
		
		JButton returnButton = new JButton("Return");
		returnButton.setBounds(661, 25, 103, 42);
		UIFrame.getContentPane().add(returnButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearFields();
			}
		});
		clearButton.setBounds(443, 25, 99, 42);
		UIFrame.getContentPane().add(clearButton);

		// *****************************************************
		// MY BOOKS
		// *****************************************************
				

		JButton myBooksButton = new JButton("My Books");
		myBooksButton.setBounds(554, 25, 103, 42);
		UIFrame.getContentPane().add(myBooksButton);
	
	}
	
	
	public void search() {
		try {

			table.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "Book ID", "Book Name", "Book Author", "Quantity" }));

			String[] columnNames = { "Book ID", "Book Name", "Book Author", "Quantity" };

			UIFrame.getContentPane().add(bookTitleTxtField);
			bookTitleTxtField.setColumns(10);

			UIFrame.getContentPane().add(authorNameTxtField);
			authorNameTxtField.setColumns(10);

			String bookName = bookTitleTxtField.getText();
			String bookAuthor = authorNameTxtField.getText();

			// Actual SQL SELECT statement
			String sql = "SELECT * FROM Books WHERE (book_name LIKE '%" + bookName + "%') OR (book_author LIKE '%"
					+ bookAuthor + "%');";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
	}
	
	public void returnBook() {
		
		
		
	}
	
	
	// *****************************************************
	// CLEAR TEXT FIELDS
	// *****************************************************
	public void clearFields() {
		bookIDTxtField.setText(null);
		bookTitleTxtField.setText(null);
		authorNameTxtField.setText(null);
		quantityTxtField.setText(null);
	}
}











































