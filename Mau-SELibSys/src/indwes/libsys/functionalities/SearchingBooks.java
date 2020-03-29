package indwes.libsys.functionalities;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class SearchingBooks {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchingBooks window = new SearchingBooks();
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
	public SearchingBooks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 250));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 870, 532);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}