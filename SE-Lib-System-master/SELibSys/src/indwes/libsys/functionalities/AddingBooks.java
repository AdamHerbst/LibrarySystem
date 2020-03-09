package indwes.libsys.functionalities;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.TextField;
import java.awt.Button;

import indwes.libsys.dao.LibraryDao;
import indwes.libsys.main.*;
import java.util.Scanner;
import java.sql.*;
import java.awt.SystemColor;
import java.awt.Window.Type;
import javax.swing.JToolBar;

import javax.swing.JFrame;

public class AddingBooks {
	// This window needs to be open accessible to other classes
		public JFrame frmAddNewBook;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						
						// Create object of addingBooks
						AddingBooks window = new AddingBooks();
						window.frmAddNewBook.setVisible(true); // Set frame visible true
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the application.
		 */
		
		// Adding the connection.
		Connection connection = null;
		
		public AddingBooks() {
			initialize();
			connection = SqlConnection.dbConnect(); // Calling the connection class (database)
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			
			frmAddNewBook = new JFrame("Add New Book");
			frmAddNewBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmAddNewBook.setForeground(Color.BLACK);
			frmAddNewBook.getContentPane().setBackground(new Color(227, 228, 230));
			frmAddNewBook.getContentPane().setForeground(new Color(192, 192, 192));
			frmAddNewBook.setBounds(100, 100, 450, 339);
			frmAddNewBook.getContentPane().setLayout(null);
			
			JLabel bookInfoLabel = new JLabel("Book Information");
			bookInfoLabel.setFont(new Font("Lato Light", Font.BOLD, 37));
			bookInfoLabel.setForeground(new Color(255, 140, 0));
			bookInfoLabel.setBackground(new Color(32, 178, 170));
			bookInfoLabel.setBounds(104, 12, 334, 34);
			frmAddNewBook.getContentPane().add(bookInfoLabel);
			
			
			JLabel nameOfBookLabel = new JLabel("Name:");
			nameOfBookLabel.setFont(new Font("Dialog", Font.BOLD, 20));
			nameOfBookLabel.setForeground(new Color(0, 0, 0));
			nameOfBookLabel.setBackground(new Color(211, 211, 211));
			nameOfBookLabel.setBounds(12, 123, 91, 34);
			frmAddNewBook.getContentPane().add(nameOfBookLabel);
			
			JLabel authorOfBookLabel = new JLabel("Author:");
			authorOfBookLabel.setFont(new Font("Dialog", Font.BOLD, 20));
			authorOfBookLabel.setForeground(new Color(0, 0, 0));
			authorOfBookLabel.setBackground(new Color(211, 211, 211));
			authorOfBookLabel.setBounds(12, 169, 102, 34);
			frmAddNewBook.getContentPane().add(authorOfBookLabel);
			
			JLabel quantityofBookLabel = new JLabel("Quantity:");
			quantityofBookLabel.setFont(new Font("Dialog", Font.BOLD, 20));
			quantityofBookLabel.setForeground(new Color(0, 0, 0));
			quantityofBookLabel.setBackground(new Color(211, 211, 211));
			quantityofBookLabel.setBounds(12, 215, 114, 34);
			frmAddNewBook.getContentPane().add(quantityofBookLabel);
			
			JLabel IDofBookLabel = new JLabel("ID:");
			IDofBookLabel.setFont(new Font("Dialog", Font.BOLD, 20));
			IDofBookLabel.setForeground(new Color(0, 0, 0));
			IDofBookLabel.setBackground(new Color(211, 211, 211));
			IDofBookLabel.setBounds(12, 77, 91, 34);
			frmAddNewBook.getContentPane().add(IDofBookLabel);
			
			
			TextField txtFieldforID = new TextField();
			txtFieldforID.setFont(new Font("Georgia", Font.PLAIN, 24));
			txtFieldforID.setBounds(134, 77, 289, 34);
			frmAddNewBook.getContentPane().add(txtFieldforID);
			
			TextField txtFieldforName = new TextField();
			txtFieldforName.setFont(new Font("Georgia", Font.PLAIN, 24));
			txtFieldforName.setBounds(134, 123, 289, 34);
			frmAddNewBook.getContentPane().add(txtFieldforName);
			
			TextField txtFieldforAuthor = new TextField();
			txtFieldforAuthor.setFont(new Font("Georgia", Font.PLAIN, 24));
			txtFieldforAuthor.setBounds(134, 163, 289, 34);
			frmAddNewBook.getContentPane().add(txtFieldforAuthor);
			
			TextField txtFieldforQuantity = new TextField();
			txtFieldforQuantity.setFont(new Font("Georgia", Font.PLAIN, 24));
			txtFieldforQuantity.setBounds(134, 209, 289, 34);
			frmAddNewBook.getContentPane().add(txtFieldforQuantity);
			
			Button AddNewBookButton = new Button("Add New Book");
			AddNewBookButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			
					try {
						String bookid = txtFieldforID.getText();
						int bookID = Integer.parseInt(bookid);
						 String bookName = txtFieldforName.getText();
						 String bookAuthor = txtFieldforAuthor.getText();
						 String quantity = txtFieldforQuantity.getText();
						 int bookQuantity = Integer.parseInt(quantity);
						 
						 int x = LibraryDao.addBook(bookID, bookName, bookAuthor, bookQuantity);
						 if (x > 0) {
							 JOptionPane.showMessageDialog(null, "Book has been successfully added!");
						 }
						 else {
							 JOptionPane.showMessageDialog(null, "An error has occured, book has not been added.");
						}
					}
					catch (Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
					
				}
			});
			AddNewBookButton.setBounds(134, 265, 189, 34);
			frmAddNewBook.getContentPane().add(AddNewBookButton);
			AddNewBookButton.setForeground(Color.WHITE);
			AddNewBookButton.setFont(new Font("Georgia", Font.PLAIN, 24));
			AddNewBookButton.setBackground(SystemColor.desktop);
			
			JButton backButton = new JButton("<");
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					FrontScreenApp window = new FrontScreenApp();
					window.frmLibrarianFunctions.setVisible(true);
				}
			});
			backButton.setFont(new Font("Georgia", Font.BOLD, 20));
			backButton.setBounds(12, 12, 53, 34);
			frmAddNewBook.getContentPane().add(backButton);
			
			
			
		}
	}