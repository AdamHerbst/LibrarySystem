package indwes.libsys.functionalities;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class FrontScreenApp {

	public JFrame frmLibrarianFunctions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontScreenApp window = new FrontScreenApp();
					window.frmLibrarianFunctions.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrontScreenApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibrarianFunctions = new JFrame();
		frmLibrarianFunctions.setTitle("Librarian Functions");
		frmLibrarianFunctions.setBounds(100, 100, 450, 339);
		frmLibrarianFunctions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibrarianFunctions.getContentPane().setLayout(null);

		// When button clicked, we will go to the add book window
		JButton addBookButton = new JButton("Add Books");
		addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddingBooks window = new AddingBooks();
				window.frmAddNewBook.setVisible(true);
				frmLibrarianFunctions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		addBookButton.setBounds(40, 41, 117, 50);
		frmLibrarianFunctions.getContentPane().add(addBookButton);

		 JButton SearchViewBooksButton = new JButton("Search/View Books");

		    SearchViewBooksButton.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
		            SearchingBooks window = new SearchingBooks();
		            window.createUI();
		            window.frame.setVisible(true);
		 
		            frmLibrarianFunctions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        }
		    });
		    SearchViewBooksButton.setBounds(239, 130, 187, 61);
		    frmLibrarianFunctions.getContentPane().add(SearchViewBooksButton);

		JButton viewAccountButton = new JButton("View Account");
		viewAccountButton.setBounds(45, 130, 146, 61);
		frmLibrarianFunctions.getContentPane().add(viewAccountButton);
	}
}