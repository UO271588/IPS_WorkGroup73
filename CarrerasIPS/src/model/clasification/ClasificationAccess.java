package model.clasification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DbUtil;
import util.database.Database;

public class ClasificationAccess {

	public List<ClasificationDto> findAllByRace(String idCompetition) throws SQLException {
		List<ClasificationDto> clasificacionAbsoluta = new ArrayList<>();
		Connection con = DbUtil.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from CLASIFICATION where IDCOMPETITION = '" + idCompetition+ "'");
		while (rs.next()) {
			ClasificationDto clasificacion= new ClasificationDto();
			clasificacion.dni = rs.getString("DNI");
			clasificacion.idcarrera = idCompetition;
			clasificacion.sexo = rs.getString("SEX");
			clasificacion.posicion = "0";
			clasificacion.tiempoInicio = rs.getString("INITIALTIME");
			clasificacion.tiempoFinal = rs.getString("FINALTIME");
			clasificacion.categoryname = rs.getString("CATEGORYNAME");
			clasificacion.dorsal = rs.getString("DORSAL");
			
			clasificacionAbsoluta.add(clasificacion);
		}
		return clasificacionAbsoluta;
	}
	
	public static boolean isThereAnyClasification(String idCompetition) {
		try {
			return DbUtil.existRowStringDB("select * from CLASIFICATION where IDCOMPETITION = ?", idCompetition);
		} catch (SQLException e) {
			throw new RuntimeException("error en consulta : " + "select * from CLASIFICATION where IDCOMPETITION = '" + idCompetition+ "'");
		}
	}
	
	public List<ClasificationDto> findAllByIdRace(String idCompetition) throws SQLException {
		List<ClasificationDto> clasificacionAbsoluta = new ArrayList<>();
		Connection con = DbUtil.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from CLASIFICATION where IDCOMPETITION = '" + idCompetition+ "'");
		while (rs.next()) {
			ClasificationDto clasificacion= new ClasificationDto();
			clasificacion.dni = rs.getString("DNI");
			clasificacion.idcarrera = idCompetition;
			clasificacion.sexo = rs.getString("SEX");
			clasificacion.posicion = "0";
			clasificacion.tiempoInicio = rs.getString("INITIALTIME");
			clasificacion.tiempoFinal = rs.getString("FINALTIME");
			clasificacion.categoryname = rs.getString("CATEGORYNAME");
			clasificacion.dorsal = rs.getString("DORSAL");
			clasificacion.club = rs.getString("CLUB");
			if(clasificacion.club == null || clasificacion.club.isBlank()) {
				clasificacion.club = "Independiente";
			}
			
			clasificacionAbsoluta.add(clasificacion);
		}
		return clasificacionAbsoluta;
	}

}
