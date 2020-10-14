package model.participant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import org.sqlite.JDBC;

import model.ParticipantAccess;
import util.DbUtil;

public class ParticipantModel {
	
	private static final String SQL_SELECT_DNI = "SELECT * FROM PARTICIPANTE WHERE DNI = ?"; 
	private static final String SQL_SELECT_MAIL = "SELECT * FROM PARTICIPANTE WHERE EMAIL = ?"; 


	public static void addParticipant(ParticipantDto part) {
		try {


			ParticipantAccess.addParticipant(part);

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
	 * Comprueba si se puede añadir un nuevo cliente con el mial introducido
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




}
