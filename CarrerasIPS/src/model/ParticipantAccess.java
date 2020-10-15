package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.participant.ParticipantDto;
import util.DbUtil;
import util.TimeUtil;

public class ParticipantAccess {
	
	

	
	private static final String SQL_SELECT_DNI = "SELECT * FROM PARTICIPANTE WHERE DNI = ?"; 
	private static final String SQL_SELECT_MAIL = "SELECT * FROM PARTICIPANTE WHERE EMAIL = ?"; 

	public static void addParticipant(ParticipantDto part) throws SQLException {
		
		
	}
	
	public static void showAllParticipants() throws SQLException {
		Connection con = DbUtil.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from PARTICIPANTE");
		while(rs.next()) {
			System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5));
		}
		
		
	}

	/***
	 * Metodo que comprueba si hay alguna instancia del dni introducido almacenado en labase de datos
	 * @param dni
	 * @return
	 * @throws SQLException 
	 */
	public static boolean anyDniParticipant(String dni) throws SQLException {

			return DbUtil.existRowStringDB(SQL_SELECT_DNI, dni);
		
	}
	/**
	 * Metodo que comprueba si hay alguna instancia del mail introducido almacenado en labase de datos
	 * @param mail
	 * @return 
	 * @throws SQLException 
	 */
	public static boolean anyMailParticipant(String mail) throws SQLException {

			return DbUtil.existRowStringDB(SQL_SELECT_MAIL, mail);

		
	}

}
