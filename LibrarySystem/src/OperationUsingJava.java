import java.util.Scanner;

import java.sql.*;

public class OperationUsingJava

{

public static void main( String args[] )

{

String flag="Y";

do{

System.out.println("Select DML Operation For Books Table...");

System.out.println("1. Insert");

System.out.println("2. Update");

System.out.println("3. Delete");

System.out.println("4. Select");

System.out.println("5. Exit");

Scanner reader = new Scanner(System.in);

System.out.println("Enter a choice: ");

int n = reader.nextInt();

Connection c = null;

Statement stmt = null;

try {

Class.forName("org.sqlite.JDBC");

c = DriverManager.getConnection("jdbc:sqlite:LibraryDB.db");

c.setAutoCommit(false);

stmt = c.createStatement();

String name="",sql="";

float price=0.0f;

int quantity=0;

int id;

Scanner scanName;

switch(n){

 

case 1:

scanName=new Scanner(System.in);

System.out.println("Enter Book Name:");

name=scanName.nextLine();

System.out.println("Enter Book Price:");

price=scanName.nextFloat();

System.out.println("Enter Book Quantity:");

quantity=scanName.nextInt();

sql = "INSERT INTO Books (book_name,price,quantity) " +

"VALUES ('" +name+ "'," +

price + "," + quantity + ")";

stmt.executeUpdate(sql);

System.out.println("Inserted Successfully!!!");

break;

 

case 2:

System.out.println("Enter Book id:");

scanName=new Scanner(System.in);

id=scanName.nextInt();

System.out.println("Enter Book Name:");

scanName=new Scanner(System.in);

name=scanName.nextLine();

System.out.println("Enter Book Price:");

price=scanName.nextFloat();

System.out.println("Enter Book Quantity:");

quantity=scanName.nextInt();

 

sql = "UPDATE Books SET book_name = '"+ name + "',price=" + price +",quantity=" + quantity +

" WHERE p_id=" +id ;

 

stmt.executeUpdate(sql);

System.out.println("Updated Successfully!!!");

break;

 

case 3:

System.out.println("Enter Book id:");

scanName=new Scanner(System.in);

id=scanName.nextInt();

sql="DELETE FROM Books WHERE p_id=" + id+";";

stmt.executeUpdate(sql);

System.out.println("Deleted Successfully!!!");

break;

 

case 4:

ResultSet rs = stmt.executeQuery("SELECT * FROM Books;");

System.out.println("ID\t Book Name\t Price\t Qty ");

while ( rs.next() ) {

id = rs.getInt("p_id");

name = rs.getString("book_name");

quantity = rs.getInt("quantity");

price = rs.getFloat("price");

System.out.println(id+"\t "+name+" \t "+price+"\t "+quantity);

}

rs.close();

break;

 

case 5:

System.exit(0);

break;

 

default:

System.out.println("Oops!!! Wrong Choice...");

break;

}

stmt.close();

c.commit();

c.close();

}

catch ( Exception e )

{

System.err.println( e.getClass().getName() + ": " + e.getMessage() );

System.exit(0);

}

 

System.out.println("Continue Y OR N?");

reader=new Scanner(System.in);

flag=reader.nextLine();

 

}while(flag.equalsIgnoreCase("Y"));

System.exit(0);

}

}