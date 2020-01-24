import java.sql.*;


public class CreateDatabase {

public static void main( String args[] )

{

Connection c = null;

try {

Class.forName("org.sqlite.JDBC");

c = DriverManager.getConnection("jdbc:sqlite:LibraryDB2.db");

}

catch ( Exception e ) {

System.err.println( e.getClass().getName() + ": " + e.getMessage() );

System.exit(0);

}

System.out.println("database successfully created");

}

}