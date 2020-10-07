package dbAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import business.client.Participant;
import database.util.DbUtil;

public class ParticipantAccess {
	
	
	private static final String SQL = "INSERT INTO PARTICIPANTE (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE) " +
    		"VALUES (?, ?, ?, ?, ?, 1975-11-20);"; 

	public static void addParticipant(Participant part) throws SQLException {
		Connection con = DbUtil.getConnection();
		PreparedStatement pst = con.prepareStatement(SQL);
		pst.setString(1, part.getDni());
		pst.setString(2, part.getName());
		pst.setString(3, part.getSurname());
		pst.setString(4, part.getMail());
		
		String sex;
		if(part.isSexMale()) {
			sex = "HOMBRE";
		}
		else {
			sex = "MUJER";
		}
		
		pst.setString(5, sex);
		//pst.setString(5, "1975-11-20");
		pst.executeUpdate();
		pst.close();
		con.close();
		
	}
	
	public static void showAllParticipants() throws SQLException {
		Connection con = DbUtil.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from PARTICIPANTE");
		while(rs.next()) {
			System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5));
		}
		
		
	}

}
