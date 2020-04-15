package indwes.libsys.functionalities;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import indwes.libsys.dao.LibraryDao;
import indwes.libsys.main.SqlConnection;
import net.proteanit.sql.DbUtils;

public class LibrarianView {

	public JFrame UIFrame;
	private JTable table;
	private JTextField bookTitleTxtField;
	private JTextField authorNameTxtField;
	private JTextField quantityTxtField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LibrarianView window = new LibrarianView();
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
	private JTextField textvalue;
	private JTextField bookIDTxtField;

	public LibrarianView() {
		connection = SqlConnection.dbConnect(); // Calling the connection class
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		UIFrame = new JFrame();
		UIFrame.setTitle("Librarian View");
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
		// bookIDTxtField.setText();
		bookIDTxtField.setColumns(10);
		bookIDTxtField.setBounds(146, 6, 135, 19);
		UIFrame.getContentPane().add(bookIDTxtField);
		UIFrame.setVisible(true);

		JButton searchButton = new JButton("Search");
		searchButton.setBounds(361, 29, 86, 35);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				search();
			}
		});
		UIFrame.getContentPane().add(searchButton);

		// *****************************************************
		// ADD BOOKS BUTTON
		// *****************************************************
		JButton addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add();
			}
		});
		addButton.setBounds(491, 29, 86, 35);
		UIFrame.getContentPane().add(addButton);

		// *****************************************************
		// REMOVE BOOKS BUTTON
		// *****************************************************

		JButton removeButton = new JButton("Remove");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remove();
			}
		});
		removeButton.setBounds(614, 29, 97, 35);
		UIFrame.getContentPane().add(removeButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 137, 691, 365);
		UIFrame.getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);

		// *****************************************************
		// UPDATE BOOKS BUTTON
		// *****************************************************

		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		updateButton.setBounds(543, 85, 86, 35);
		UIFrame.getContentPane().add(updateButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearFields();
			}
		});
		clearButton.setBounds(396, 85, 86, 35);
		UIFrame.getContentPane().add(clearButton);

	}

	// *****************************************************
	// ADD BOOKS METHOD
	// *****************************************************

	public void add() {

		try {
			String bookName = bookTitleTxtField.getText();
			String bookAuthor = authorNameTxtField.getText();
			String quantity = quantityTxtField.getText();
			int bookQuantity = Integer.parseInt(quantity);

			int x = LibraryDao.addBook(bookName, bookAuthor, bookQuantity);
			if (x > 0) {
				JOptionPane.showMessageDialog(null, "Book has been successfully added!");
			} else {
				JOptionPane.showMessageDialog(null, "An error has occured, book has not been added.");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		updateTable();
	}

	// *****************************************************
	// REMOVE BOOKS METHOD
	// *****************************************************

	public void remove() {

		String ID = bookIDTxtField.getText();
		int bookID = Integer.parseInt(ID);

		int row = table.getSelectedRow();
		if (row != -1) {
			int modelRow = table.convertRowIndexToModel(row);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			String selected = model.getValueAt(row, 0).toString();
			model.removeRow(modelRow);

			int x = LibraryDao.removeBook(bookID);

			if (x > 0) {
				JOptionPane.showMessageDialog(null, "Book has been successfully deleted!");
			}
		}
		clearFields();
	}

	// *****************************************************
	// SEARCH BOOKS METHOD
	// *****************************************************

	public void search() {

		try {

			table.setModel(new DefaultTableModel(new Object[][] {},
					new String[] { "Book ID", "Book Name", "Book Author", "Quantity" }));

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
		// *****************************************************
		// AUTO FILL IN THE TXT BOXES
		// *****************************************************
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent event) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRow = table.getSelectedRow();
				if (selectedRow > -1) {
					bookIDTxtField.setText(model.getValueAt(selectedRow, 0).toString());
					bookTitleTxtField.setText(model.getValueAt(selectedRow, 1).toString());
					authorNameTxtField.setText(model.getValueAt(selectedRow, 2).toString());
					quantityTxtField.setText(model.getValueAt(selectedRow, 3).toString());

				}
			}

		});

	}

	// *****************************************************
	// UPDATE BOOKS METHOD
	// *****************************************************
	public void update() {

		try {
			String bookIDTemp = bookIDTxtField.getText();
			int bookID = Integer.parseInt(bookIDTemp);
			String bookName = bookTitleTxtField.getText();
			String bookAuthor = authorNameTxtField.getText();
			String quantity = quantityTxtField.getText();
			int bookQuantity = Integer.parseInt(quantity);

			int x = LibraryDao.updateBook(bookID, bookName, bookAuthor, bookQuantity);
			if (x > 0) {
				JOptionPane.showMessageDialog(null, "Book has been successfully updated!");
			} else {
				JOptionPane.showMessageDialog(null, "An error has occured, book has not been updated.");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		updateTable();
	}
	
	// *****************************************************
	// UPDATE TABLE AFTER MODIFYING IT
	// *****************************************************

	private void updateTable() {

		try {
			String sql = "select * from Books";
			PreparedStatement pst = connection.prepareStatement(sql);

			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// *****************************************************
	// CLEAR TEXT FIELDS AFTER REMOVE
	// *****************************************************
	public void clearFields() {
		bookIDTxtField.setText(null);
		bookTitleTxtField.setText(null);
		authorNameTxtField.setText(null);
		quantityTxtField.setText(null);
	}
}
