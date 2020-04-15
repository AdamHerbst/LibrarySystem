package indwes.libsys.functionalities;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import indwes.libsys.main.SqlConnection;
import net.proteanit.sql.DbUtils;

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
	private JTextField textvalue;

	/**
	 * Constructor Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the SBFrameCol.
	 */
	private void initialize() {
		UIFrame = new JFrame();
		UIFrame.getContentPane().setBackground(Color.WHITE);
		UIFrame.setTitle("Librarian Functions");
		UIFrame.setBounds(250, 400, 700, 800);
		UIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		UIFrame.getContentPane().setLayout(null);

		// When button clicked, we will go to the add book window
		// *****************************************************
		// ADD BOOKS BUTTON
		// *****************************************************
		JButton addBookButton = new JButton("Add Book");
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddBook window = new AddBook();
				window.AddFrame.setVisible(true);
				UIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		addBookButton.setBounds(447, 102, 143, 51);
		UIFrame.getContentPane().add(addBookButton);

		// When button clicked, will display all books in database
		// *****************************************************
		// Search/View BOOKS BUTTON
		// *****************************************************

		JButton SearchViewBooksButton = new JButton("Search/View Books");
		textvalue = new JTextField();
		textvalue.setBounds(70, 205, 302, 45);
		UIFrame.getContentPane().add(textvalue);
		textvalue.setColumns(10);
		connection = SqlConnection.dbConnect();
		SearchViewBooksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Actual SQL SELECT statement
				String sql = "SELECT * FROM Books WHERE (book_name LIKE '%" + textvalue.getText()
						+ "%') OR (book_author LIKE '%" + textvalue.getText() + "%');";
				try {
					PreparedStatement statement = connection.prepareStatement(sql);
					ResultSet rs = statement.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		SearchViewBooksButton.setBounds(135, 161, 179, 32);
		UIFrame.getContentPane().add(SearchViewBooksButton);

		// When button clicked goes to account signup/Login
		// *****************************************************
		//  VIEW ACCOUNT BUTTON
		// *****************************************************

		JButton viewAccountButton = new JButton("View Account");
		viewAccountButton.setBounds(12, 29, 146, 61);
		UIFrame.getContentPane().add(viewAccountButton);

		//
		// *****************************************************
		// scrollpane declared. Has mouse listener
		// *****************************************************
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		scrollPane.setBounds(48, 283, 594, 462);
		UIFrame.getContentPane().add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		// When button clicked, book is deleted from GUI
		// *****************************************************
		// DELETE BOOKS BUTTON
		// *****************************************************
		JButton deleteBookBtn = new JButton("Delete A Book");
		deleteBookBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (row != -1) {
					int modelRow = table.convertRowIndexToModel(row);
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					String selected = model.getValueAt(row, 0).toString();
					model.removeRow(modelRow);
					Connection cn = SqlConnection.dbConnect();
					Statement stat = null;
					try {
						stat = cn.createStatement();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String sql = "DELETE FROM Books WHERE book_id ='" + selected + "'";
					try {
						stat.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						cn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		deleteBookBtn.setBounds(447, 29, 146, 61);
		UIFrame.getContentPane().add(deleteBookBtn);
		// When button clicked, we will go to the add book window
		// *****************************************************
		// UPDATE BOOKS BUTTON
		// *****************************************************
		JButton updateBookBtn = new JButton("Update Book");
		updateBookBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		updateBookBtn.setBounds(447, 175, 143, 51);
		UIFrame.getContentPane().add(updateBookBtn);

		//**************************
		// EDIT/UPDATE WITH CELL
		//**************************
		
		
	/*	AbstractAction action = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	if (e.getType() == TableModelEvent.UPDATE)
		    	{
	
		
		int col=table.getSelectedColumn();
		int row=table.getSelectedRow();
		//do the update query on this row
		  
		try {
					
			
			Connection cn = SqlConnection.dbConnect();
		           PreparedStatement ps = cn.prepareStatement("UPDATE Books SET book_id = ?,book_name = ?,book_author = ?, quantity =? WHERE book_id = "+row);
		   
		            ps.setInt(1, (Integer) table.getValueAt(row, 1));
		            ps.setString(2, (String) table.getValueAt(row, 2));
		            ps.setString(3, (String) table.getValueAt(row, 3));
		            ps.setInt(4, (Integer) table.getValueAt(row, 4));
		  
		            ps.executeUpdate(); 
		            ps.close();
		            cn.close();
		  
		        } catch (Exception ex) {
		          ex.printStackTrace();
		        }
		
		    }
		    }
		
		
		
	/*	
		AbstractAction action = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        TableModelListener tcl = (TableModelListener)e.getSource();
		        System.out.println("Row   : " + ((JTable) tcl).getSelectedRow());
		        System.out.println("Column: " + tcl.getColumn());
		        System.out.println("Old   : " + tcl.getOldValue());
		        System.out.println("New   : " + tcl.getNewValue());
		    }
		};
		
	   */

		
	
	}
}
