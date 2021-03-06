package model.participant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.sqlite.JDBC;

import model.ParticipantAccess;
import util.DbUtil;
import util.TimeUtil;
import util.database.Database;

public class ParticipantModel {
	private static final String SQL_INSERT_PARTICIPANT = "INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE) " +
    		"VALUES (?, ?, ?, ?, ?, ?);"; 
	private static final String SQL_SELECT_DNI = "SELECT * FROM PARTICIPANT WHERE DNI = ?"; 
	private static final String SQL_SELECT_MAIL = "SELECT * FROM PARTICIPANT WHERE EMAIL = ?"; 

	Database db = new Database();

	public static void addParticipant(ParticipantDto part) {
		try {


			Connection con = DbUtil.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_INSERT_PARTICIPANT);
			pst.setString(1, part.getDni());
			pst.setString(2, part.getName());
			pst.setString(3, part.getSurname());
			pst.setString(4, part.getEmail());
			
			
			String sex;
			if(part.isSexMale()) {
				sex = "HOMBRE";
			}
			else {
				sex = "MUJER";
			}
			
			pst.setString(5, sex);
			pst.setString(6, TimeUtil.dateToIsoString(part.getBirthday()));
			//pst.setString(5, "1975-11-20");
			pst.executeUpdate();
			pst.close();
			con.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Comprueba si el dni introducido existe en la base de datos
	 * @param dni
	 * @return
	 */
	public static boolean checkDni(String dni) {
		try {

			return DbUtil.existRowStringDB(SQL_SELECT_DNI, dni);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Comprueba si se puede a�adir un nuevo cliente con el mial introducido
	 * @param mail
	 * @return
	 */
	public static boolean checkMail(String mail) {
		try {
			return !DbUtil.existRowStringDB(SQL_SELECT_MAIL, mail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * Devuelve un Dto con la infomacion basica del participante,
	 * se debe de comprobar antes si existe un usuario con el dni introducido
	 * @param dni
	 * @return
	 */
	public static ParticipantDto getBasicData(String dni) {
		Database db = new Database();
		String sql = "select name, surname, email from participant where dni = ?";
		
		return db.executeQueryPojo(ParticipantDto.class, sql, dni).get(0);
		
	}
	
	/**
	 * Devuelve un Dto con la infomacion basica del participante,
	 * se debe de comprobar antes si existe un usuario con el mail introducido
	 * 
	 * 
	 * @param dni
	 * @return
	 */
	public static ParticipantDtoPojo getByEmail(String mail) {
		Database db = new Database();
		String sql = "select * from participant where email = ?";
		
		return db.executeQueryPojo(ParticipantDtoPojo.class, sql, mail).get(0);
		
	}



}
