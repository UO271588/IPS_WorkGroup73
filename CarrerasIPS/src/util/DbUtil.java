package util;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
	public static boolean existRowStringDB(String sql, String param) throws SQLException {
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
	
	/**
	 * Ejecuta una sentencia sql de actualizacion con los parametros especificados;
	 * Utiliza apache commons-dbutils para manejar todos los aspectos de jdbc
	 */
	public void executeUpdate(String sql, Object... params) {
		Connection conn=null;
		try {
			conn=getConnection();
			QueryRunner runner=new QueryRunner();
			runner.update(conn, sql, params);
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
	
	
	
	
	/**
	 * Ejecuta una query sql con los parametros especificados mapeando el resultet en una lista de arrays de objetos;
	 * Utiliza apache commons-dbutils para relizar el mapeo y el manejo del resto de aspectos de jdbc
	 */
	public List<Object[]> executeQueryArray(String sql, Object... params) {
		Connection conn=null;
		try {
			conn=getConnection();
			//Como no hay una clase especificada para realizar el mapeo, utiliza el ArrayListHandler
			ArrayListHandler beanListHandler=new ArrayListHandler();
			QueryRunner runner=new QueryRunner();
			return runner.query(conn, sql, beanListHandler, params);
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
	
	/**
	 * Ejecuta una query sql con los parametros especificados mapeando el resultet en una lista de objetos 
	 * de la clase indicada en pojoClass;
	 * Utiliza apache commons-dbutils para realizar el mapeo y el manejo del resto de aspectos de jdbc
	 */
	public <T> List<T> executeQueryPojo(Class<T> pojoClass, String sql, Object... params) {
		Connection conn=null;
		try {
			conn=getConnection();
			BeanListHandler<T> beanListHandler=new BeanListHandler<>(pojoClass);
			QueryRunner runner=new QueryRunner();
			return runner.query(conn, sql, beanListHandler, params);
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}
	
	
}
