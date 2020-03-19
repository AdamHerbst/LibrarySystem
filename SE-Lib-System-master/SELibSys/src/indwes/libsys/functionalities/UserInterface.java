package indwes.libsys.functionalities;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import indwes.libsys.main.SqlConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;

public class UserInterface {

	public JFrame UIFrame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.UIFrame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	// String driverName = "org.sqlite.JDBC";
	// String url = "jdbc:sqlite:LibraryDB2.db";

	// Connection dbConnection;
	private JTextField textvalue;

	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the SBFrameCol.
	 */
	private void initialize() {
		UIFrame = new JFrame();
		UIFrame.setTitle("Librarian Functions");
		UIFrame.setBounds(100, 100, 700, 800);
		UIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIFrame.getContentPane().setLayout(null);

		// When button clicked, we will go to the add book window
		JButton addBookButton = new JButton("Add Books");
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddBook window = new AddBook();
				window.AddFrame.setVisible(true);
				UIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		addBookButton.setBounds(48, 39, 143, 51);
		UIFrame.getContentPane().add(addBookButton);

		JButton SearchViewBooksButton = new JButton("Search/View Books");
// 
		
		textvalue = new JTextField();
		textvalue.setBounds(243, 201, 302, 45);
		UIFrame.add(textvalue);
		textvalue.setColumns(10);
		
	//	String textSQL = textvalue.getText();
		
		connection = SqlConnection.dbConnect();
		SearchViewBooksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//String sql = "SELECT * FROM Books";
				String sql = "SELECT * FROM Books WHERE (book_name LIKE '%" + textvalue.getText()+ "%') OR (book_author LIKE '%" + textvalue.getText()+ "%');";
				try {

					PreparedStatement statement = connection.prepareStatement(sql);
					ResultSet rs = statement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

//					int i = 0;
//					while (rs.next()) {
//						String bID = rs.getString("book_id");
//						String bName = rs.getString("book_name");
//						String aName = rs.getString("book_author");
//						String quantity = rs.getString("quantity");
//						//rs.addRow(new Object[] { bID, bName, aName, quantity });
//						i++;
//					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		SearchViewBooksButton.setBounds(286, 92, 187, 61);
		UIFrame.getContentPane().add(SearchViewBooksButton);

		JButton viewAccountButton = new JButton("View Account");
		viewAccountButton.setBounds(45, 130, 146, 61);
		UIFrame.getContentPane().add(viewAccountButton);

		// dbConnection = SqlConnection.dbConnect();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 283, 594, 462);
		UIFrame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);


	}
}