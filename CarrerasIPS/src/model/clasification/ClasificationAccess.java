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
			System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getString(4) + rs.getString(5));
		}
		return clasificacionAbsoluta;
	}

}
