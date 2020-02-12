package edu.indwes.main;
import java.util.Scanner;
import java.sql.*;

public class Main {

	static void printMenu() {
		System.out.println("Select a library operation: ");
		System.out.println("1. Add a Book");
		System.out.println("2. Update a Book");
		System.out.println("3. Delete a book");
		System.out.println("4. Print out all books");
		System.out.println("5. Search Books");
		System.out.println("6. Exit");
	}

	public static void main(String args[]) {

		String flag = "Y";
		do {
			printMenu();
			char choice;
			Scanner reader = new Scanner(System.in);
			System.out.println("Enter a choice: ");
			choice = reader.nextLine().charAt(0);
		//	int n = reader.nextInt();
			Connection c = null;
			Statement stmt = null;

			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection("jdbc:sqlite:LibraryDB2.db");
				c.setAutoCommit(false);
				stmt = c.createStatement();
				String name = "", sql = "";
				String author;
				int quantity = 0;
				int id;

				Scanner scanName;

				switch (choice) {

				case '1':

					scanName = new Scanner(System.in);
					System.out.println("Enter Book Name:");
					name = scanName.nextLine();
					System.out.println("Enter Book Author:");
					author = scanName.nextLine();
					System.out.println("Enter Book Quantity:");
					quantity = scanName.nextInt();
					sql = "INSERT INTO Books (book_name,book_author,quantity) " + "VALUES ('" + name + "','" + author
							+ "'," + quantity + ")";
					stmt.executeUpdate(sql);
					System.out.println("Inserted Successfully!!!");
					break;

				case '2':

					System.out.println("Enter Book id:");
					scanName = new Scanner(System.in);
					id = scanName.nextInt();
					System.out.println("Enter Book Name:");
					scanName = new Scanner(System.in);
					name = scanName.nextLine();
					System.out.println("Enter Book Author (First name)");
					author = scanName.nextLine();
					System.out.println("Enter Book Quantity:");
					quantity = scanName.nextInt();
					sql = "UPDATE Books SET book_name = '" + name + "',author=" + author + ",quantity=" + quantity
							+ " WHERE book_id=" + id;
					stmt.executeUpdate(sql);
					System.out.println("Updated Successfully!!!");
					break;

				case '3':

					System.out.println("Enter Book id:");
					scanName = new Scanner(System.in);
					id = scanName.nextInt();
					sql = "DELETE FROM Books WHERE book_id=" + id + ";";
					stmt.executeUpdate(sql);
					System.out.println("Deleted Successfully!!!");
					break;
				case '4':

					ResultSet rs = stmt.executeQuery("SELECT * FROM Books;");
					System.out.println("ID\t Book Name\t Author\t Qty ");
					while (rs.next()) {
						id = rs.getInt("book_id");
						name = rs.getString("book_name");
						quantity = rs.getInt("quantity");
						author = rs.getString("book_author");
						System.out.println(id + "\t " + name + " \t " + author + "\t " + quantity);
					}
					rs.close();
					break;
				case '5':

					scanName = new Scanner(System.in);
					System.out.println("Enter Book Name:");
					name = scanName.nextLine();
					ResultSet rs2 = stmt.executeQuery("SELECT * FROM Books WHERE book_name LIKE '%" + name + "%';");
					System.out.println("ID\t Book Name\t Author\t Qty ");
					while (rs2.next()) {
					id = rs2.getInt("book_id");
					name = rs2.getString("book_name");
					quantity = rs2.getInt("quantity");
					author = rs2.getString("book_author");
					System.out.println(id + "\t " + name + " \t " + author + "\t " + quantity);
					}
					rs2.close();
					break;
				case '6':

					System.exit(0);
					break;
				default:

					System.out.println("Wrong Choice. Please enter a number between 1 and 6...");
					break;
				}
				stmt.close();
				c.commit();
				c.close();
			}

			catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			System.out.println("Continue Y OR N?");
			reader = new Scanner(System.in);
			flag = reader.nextLine();
		} while (flag.equalsIgnoreCase("Y"));

		System.exit(0);
	}
	 
}