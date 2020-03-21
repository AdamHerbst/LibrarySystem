package indwes.libsys.functionalities;

import java.awt.EventQueue;
import java.sql.Connection;

import indwes.libsys.main.SqlConnection;

public class SearchBooks {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// Create object of addingBooks
					AddBook window = new AddBook();
					window.AddFrame.setVisible(true); // Set frame visible true
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;

	public SearchBooks() {
		initialize();
		connection = SqlConnection.dbConnect(); // Calling the connection class (database)
	}

	public void initialize() {

		/*
		 * String textvalue = textbox.getText(); String bID; String bName = ""; String
		 * aName = ""; String quantity;
		 * 
		 * String sql = "SELECT * FROM Books WHERE (book_name LIKE '%" + textvalue +
		 * "%') OR (book_author LIKE '%" + textvalue + "%');"; PreparedStatement ps =
		 * con.prepareStatement(sql); ResultSet rs = ps.executeQuery(); int i = 0; while
		 * (rs.next()) { bID = rs.getString("book_id"); bName =
		 * rs.getString("book_name"); aName = rs.getString("book_author"); quantity =
		 * rs.getString("quantity"); model.addRow(new Object[] { bID, bName, aName,
		 * quantity }); i++; } if (i < 1) { JOptionPane.showMessageDialog(null,
		 * "No Record Found", "Error", JOptionPane.ERROR_MESSAGE); } if (i == 1) {
		 * System.out.println(i + " Record Found"); } else { System.out.println(i +
		 * " Records Found"); } } catch (Exception ex) {
		 * JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
		 * JOptionPane.ERROR_MESSAGE); } }
		 */
	}
}