package indwes.libsys.functionalities;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

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
		addBookButton.setBounds(40, 41, 117, 50);
		UIFrame.getContentPane().add(addBookButton);

		JButton SearchViewBooksButton = new JButton("Search/View Books");
//       JFrame window3 = new JFrame();

		SearchViewBooksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchBooks window = new SearchBooks();
				window.createUI();
				window.SBFrameCol.setVisible(true);

				UIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		SearchViewBooksButton.setBounds(239, 130, 187, 61);
		UIFrame.getContentPane().add(SearchViewBooksButton);

		JButton viewAccountButton = new JButton("View Account");
		viewAccountButton.setBounds(45, 130, 146, 61);
		UIFrame.getContentPane().add(viewAccountButton);
		
		table = new JTable();
		SearchBooks display = new SearchBooks();
		display.createUI();
		table.setBounds(137, 283, 441, 399);
		UIFrame.getContentPane().add(table);
	}
}