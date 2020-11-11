package model.clasification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DbUtil;

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
			clasificacion.sexo = rs.getString("SEXO");
			clasificacion.posicion = "0";
			clasificacion.tiempoInicio = rs.getString("INITIALTIME");
			clasificacion.tiempoFinal = rs.getString("FINALTIME");
			
			
			clasificacionAbsoluta.add(clasificacion);
		}
		return clasificacionAbsoluta;
	}

}
