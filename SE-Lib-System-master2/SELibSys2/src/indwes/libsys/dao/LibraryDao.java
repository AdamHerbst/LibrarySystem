package indwes.libsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import indwes.libsys.functionalities.UserView;
import indwes.libsys.main.SqlConnection;

public class LibraryDao {

	Connection connection = null;

	public static boolean view(int bookID, String bookName, String bookAuthor, int bookQuantity) {
		boolean status = false;

		try {
			Connection conn = SqlConnection.dbConnect();
			PreparedStatement preparedstm = conn.prepareStatement("SELECT * FROM Books");

			preparedstm.setInt(1, bookID);
			preparedstm.setString(2, bookName);
			preparedstm.setString(3, bookAuthor);
			preparedstm.setInt(4, bookQuantity);

			ResultSet rs = preparedstm.executeQuery();
			status = rs.next();
			conn.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int addBook(String bookName, String bookAuthor, int bookQuantity) {
		int status = 0;

		try {
			Connection conn = SqlConnection.dbConnect();
			PreparedStatement preparedstm = conn
					.prepareStatement("INSERT INTO Books (book_name, book_author,quantity) VALUES (?, ?, ?)");

			preparedstm.setString(1, bookName);
			preparedstm.setString(2, bookAuthor);
			preparedstm.setInt(3, bookQuantity);
			status = preparedstm.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int updateBook(int bookID, String bookName, String bookAuthor, int bookQuantity) {
		int status = 0;

		try {
			Connection conn = SqlConnection.dbConnect();
			String sql = "UPDATE Books SET book_name=?, book_author=?,quantity=? WHERE book_id=?";
			// PreparedStatement preparedstm = conn.prepareStatement("UPDATE Books SET
			// book_id = ?, book_name= ?, book_author = ?,quantity = ? WHERE book_id = ?");
			PreparedStatement preparedstm = conn.prepareStatement(sql);
			preparedstm.setString(1, bookName);
			preparedstm.setString(2, bookAuthor);
			preparedstm.setInt(3, bookQuantity);
			preparedstm.setInt(4, bookID);
			status = preparedstm.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int removeBook(int bookID) {
		int status = 0;

		try {
			Connection conn = SqlConnection.dbConnect();
			PreparedStatement preparedstm = conn.prepareStatement("DELETE FROM Books WHERE book_id = ?");

			preparedstm.setInt(1, bookID);
			status = preparedstm.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static int searchBook(String bookName, String bookAuthor) {
		int status = 0;

		try {
			Connection conn = SqlConnection.dbConnect();
			PreparedStatement preparedstm = conn.prepareStatement("SELECT * FROM Books WHERE book_name like '%'"
					+ bookName + "'%' OR book_author like '%'" + bookAuthor + "'%'");

			preparedstm.setString(1, bookName);
			preparedstm.setString(2, bookAuthor);
			status = preparedstm.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static boolean login(String username, String password) {
		boolean status = false;

		try {
			Connection conn = SqlConnection.dbConnect();
			PreparedStatement preparedstm = conn
					.prepareStatement("SELECT * FROM Users WHERE username=? AND password=? ");

			preparedstm.setString(1, username);
			preparedstm.setString(2, password);

			ResultSet rs = preparedstm.executeQuery();
			status = rs.next();
			conn.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}

		return status;

	}

	public static int createAccount(String username, String password) {
		int status = 0;

		try {
			Connection conn = SqlConnection.dbConnect();
			PreparedStatement preparedstm = conn.prepareStatement("INSERT INTO Users(username, password) VALUES (?,?)");

			preparedstm.setString(1, username);
			preparedstm.setString(2, password);

			status = preparedstm.executeUpdate();
			conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	public static boolean checkUsername(String username) {

		PreparedStatement preparedstm;
		ResultSet resultset;
		boolean check = false;

		String query = "SELECT * FROM Users WHERE username =?";

		try {
			Connection conn = SqlConnection.dbConnect();
			preparedstm = conn.prepareStatement(query);
			preparedstm.setString(1, username);

			resultset = preparedstm.executeQuery();

			if (resultset.next()) {
				check = true;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return check;
	}

}
