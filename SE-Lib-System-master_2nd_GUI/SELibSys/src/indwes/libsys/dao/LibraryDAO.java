package indwes.libsys.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;

import indwes.libsys.main.SqlConnection;

public class LibraryDAO {
	
Connection connection = null;
	
	
	public static int addBook(int bookID, String bookName, String bookAuthor, int bookQuantity) {
		int status = 0;
		
		try {
		Connection conn = SqlConnection.dbConnect();
		 PreparedStatement preparedstm = conn.prepareStatement("INSERT INTO Books (book_id, book_name, book_author,quantity) VALUES (?, ?, ?, ?)");
		 
		 preparedstm.setInt(1,bookID);
		 preparedstm.setString(2, bookName);
		 preparedstm.setString(3, bookAuthor);
		 preparedstm.setInt(4, bookQuantity);
		 status = preparedstm.executeUpdate();
		 conn.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return status;	
	}


	public static int login(String username, String password) {
		int status = 0;
		
		try {
		Connection conn = SqlConnection.dbConnect();
		PreparedStatement preparedstm = conn.prepareStatement("SELECT * FROM LibraryCardInfo WHERE username=? AND password=? ");
		 preparedstm.setString(1, username);
		 preparedstm.setString(2, password);
		 status = preparedstm.executeUpdate();
		 conn.close();
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
		
		return status;	
		
	}
	
	public void showTableData(String bookQuery) {
		
	}
}
