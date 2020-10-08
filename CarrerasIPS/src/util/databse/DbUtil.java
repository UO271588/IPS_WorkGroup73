package util.databse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	/***
	 * Metodo que comprueba si existe al menos una fila que cumple la consulta seleccionada con el
	 * parametro de tipo string introducido
	 * @param sql
	 * @return
	 * @throws SQLException 
	 */
	public static boolean existRowStringDB(String sql, String param ) throws SQLException {
		Connection con = getConnection();
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, param);
		
		ResultSet rs = pst.executeQuery();
		boolean result =  rs.next();
		rs.close();
		pst.close();
		con.close();
		return result;
	}
}
