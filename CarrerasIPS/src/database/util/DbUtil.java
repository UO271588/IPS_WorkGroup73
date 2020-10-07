package database.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final  String URL = "jdbc:sqlite:test.db";
	
	
	public static Connection getConnection() throws SQLException{
		
		try {
	         Class.forName(DRIVER);
	         Connection c = DriverManager.getConnection(URL);//esta linea crea la base de datos en caso de que no exista
	         System.out.println("Opened database successfully");
	         
	         return c;
	      } catch ( ClassNotFoundException e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
		return null;
	}
}
