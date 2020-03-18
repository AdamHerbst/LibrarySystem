package indwes.libsys.functionalities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class SearchBooks implements ActionListener {
	JFrame SBFrameCol, SBFrameDisplay;
	JTextField textbox;
	JLabel label;
	JButton button;
	JPanel panel;
	static JTable table;

	String driverName = "org.sqlite.JDBC";
	String url = "jdbc:sqlite:LibraryDB2.db";
	// String userName = "root";
	// String password = "root";

	String[] columnNames = { "Book ID", "Book Name", "Book Author", "Quantity" };

	public void createUI() {
		SBFrameCol = new JFrame("Database Search Result");
		SBFrameCol.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SBFrameCol.setLayout(null);
		textbox = new JTextField();
		textbox.setBounds(400, 130, 250, 30);
		label = new JLabel("Enter A Book Name or Author Name");
		label.setBounds(100, 130, 300, 30);
		button = new JButton("search");
		button.setBounds(220, 200, 150, 20);
		button.addActionListener(this);

		SBFrameCol.add(textbox);
		SBFrameCol.add(label);
		SBFrameCol.add(button);
		SBFrameCol.setVisible(true);
		SBFrameCol.setSize(700, 400);
	}

	public void actionPerformed(ActionEvent ae) {
		button = (JButton) ae.getSource();
		System.out.println("Showing Table Data.......");
		showTableData();
	}

	public void showTableData() {

		SBFrameDisplay = new JFrame("Database Search Result");
		SBFrameDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SBFrameDisplay.setLayout(new BorderLayout());

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);

		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		String textvalue = textbox.getText();
		String bID;
		String bName = "";
		String aName = "";
		String quantity;
		try {
			Class.forName(driverName);
			Connection con = DriverManager.getConnection(url);
			String sql = "SELECT * FROM Books WHERE (book_name LIKE '%" + textvalue + "%') OR (book_author LIKE '%"
					+ textvalue + "%');";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				bID = rs.getString("book_id");
				bName = rs.getString("book_name");
				aName = rs.getString("book_author");
				quantity = rs.getString("quantity");
				model.addRow(new Object[] { bID, bName, aName, quantity });
				i++;
			}
			if (i < 1) {
				JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			if (i == 1) {
				System.out.println(i + " Record Found");
			} else {
				System.out.println(i + " Records Found");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		SBFrameDisplay.add(scroll);
		SBFrameDisplay.setVisible(true);
		SBFrameDisplay.setSize(400, 300);
	}

	public static void main(String args[]) {
		SearchBooks sr = new SearchBooks();
		sr.createUI();
	}
}