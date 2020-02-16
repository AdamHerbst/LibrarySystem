import static org.junit.jupiter.api.Assertions.*;

import java.sql.*;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
class addBookTest {

	@Test
	void test() {	
		String name = "";
		String author;
		int quantity = 0;
		int id;
		try 
		{
		
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:LibraryDB2.db");
		
		Statement st = conn.createStatement();
		
		st.executeUpdate("INSERT INTO Books (book_name,book_author,quantity)" + "VALUES ('The Hunger Games', 'Suzanne Collins', 10)");
		

		
		String query = "SELECT * FROM Books WHERE book_name='The Hunger Games'";
		ResultSet rs = st.executeQuery(query);
		
		while (rs.next()) {
		id = rs.getInt("book_id");
		name = rs.getString("book_name");
		quantity = rs.getInt("quantity");
		author = rs.getString("book_author");
		System.out.println(id + "\t " + name + " \t " + author + "\t " + quantity);
		}
		
		// Assert test code
		String expected = "The Hunger Games";
		String actual = name;
		assertEquals(expected, actual);
		
		rs.close();
	}catch (Exception e) {
		System.err.println(e.getClass().getName() + ": " + e.getMessage());
		System.exit(0);
	}

}


}
