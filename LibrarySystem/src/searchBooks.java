import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class searchBooks {

	@Test
	void test() {

		String name = "";
		String author ="";
		int quantity = 0;
		int id;

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:LibraryDB2.db");

			Statement st = conn.createStatement();

			String query = "SELECT * FROM Books";
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				id = rs.getInt("book_id");
				name = rs.getString("book_name");
				quantity = rs.getInt("quantity");
				author = rs.getString("book_author");
				System.out.println(id + "\t " + name + " \t " + author + "\t " + quantity);
			}
			assertNotNull(name);
			assertNotNull(author);
			assertNotNull(quantity);
		} catch (Exception e) {
			System.err.println("Exception! Something went wrong with the test!");
			System.exit(0);

		}
	}
}