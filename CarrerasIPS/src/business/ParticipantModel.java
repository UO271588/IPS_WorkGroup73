package business;

import java.sql.Connection;
import java.sql.SQLException;

import org.sqlite.JDBC;

import business.client.Participant;
import database.util.DbUtil;
import dbAccess.ParticipantAccess;

public class ParticipantModel {

	public static void addParticipant(Participant part) {
		try {
			
			
			ParticipantAccess.addParticipant(part);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	
	
	
}
