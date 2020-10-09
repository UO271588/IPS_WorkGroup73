package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.race.RaceDto;

public class CompetitionsAccess {
	
	public List<RaceDto> findAllRaces(){
		List<RaceDto> carreras = new ArrayList<RaceDto>();
		String SQL = "SELECT * FROM competition";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
			
			pst = c.prepareStatement(SQL);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				RaceDto carrera = new RaceDto();
				carrera.nombre = rs.getString("NAME");
				carrera.tipo = rs.getString("TIPO");
				carrera.distancia = rs.getDouble("DISTANCE");
				carrera.precioInscripcion = rs.getDouble("INSCRIPTIONFEE");
				carrera.fechaLimite = rs.getDate("INSCRIPTIONDATEEND").toLocalDate();
				carrera.fechaCarrera = rs.getDate("COMPETITIONDATE").toLocalDate();
				carreras.add(carrera);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return carreras;
	}
	
	
}
