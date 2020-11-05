package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import business.race.RaceDto;
import util.TimeUtil;
import util.database.Database;

public class CompetitionsAccess {

	public void addRace(RaceDto carrera){
		String SQL = "insert into competition (idcompetition,name,tipo,distance,inscriptionfee,inscriptiondateend,competitiondate,slots,actualSlots) values (?,?,?,?,?,?,?,?,?);";
		PreparedStatement pst = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			pst = c.prepareStatement(SQL);
			pst.setString(1, carrera.id);
			pst.setString(2, carrera.nombre);
			pst.setString(3, carrera.tipo);
			pst.setInt(4, carrera.distancia);
			pst.setInt(5, carrera.precioInscripcion);
			pst.setString(6, TimeUtil.DateToSQL(carrera.fechaLimite));
			pst.setString(7,  TimeUtil.DateToSQL(carrera.fechaLimite));
			pst.setInt(8, carrera.aforoMax);
			pst.setInt(9, carrera.aforoActual);

			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void updateRace(RaceDto carrera){
		String SQL = "	update competition set name = ?, tipo = ?, distance = ?, inscriptionfee = ?, inscriptiondateend = ?, competitiondate = ? slots = ?,actualSlots = ?where idcompetition = ?;";
		PreparedStatement pst = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			pst = c.prepareStatement(SQL);
			pst.setString(1, carrera.nombre);
			pst.setString(2, carrera.tipo);
			pst.setInt(3, carrera.distancia);
			pst.setInt(4, carrera.precioInscripcion);
			pst.setString(5, TimeUtil.DateToSQL(carrera.fechaLimite));
			pst.setString(6, TimeUtil.DateToSQL(carrera.fechaLimite));
			pst.setInt(7, carrera.aforoMax);
			pst.setInt(8, carrera.aforoActual);
			pst.setString(9, carrera.id);

			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pst.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<RaceDto> findAllRaces(){
		List<RaceDto> carreras = new ArrayList<RaceDto>();
		String SQL = "SELECT * FROM competition";
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection c  = null;
		try {
			c= DriverManager.getConnection("jdbc:sqlite:test.db");

			pst = c.prepareStatement(SQL);

			rs = pst.executeQuery();

			while(rs.next()) {
				RaceDto carrera = new RaceDto();
				carrera.id = rs.getString("IDCOMPETITION");
				carrera.nombre = rs.getString("NAME");
				carrera.tipo = rs.getString("TIPO");
				carrera.distancia = rs.getInt("DISTANCE");
				carrera.precioInscripcion = rs.getInt("INSCRIPTIONFEE");
				carrera.fechaLimite = TimeUtil.isoStringToDate(rs.getString("InscriptionDateEnd"));
				carrera.fechaCarrera = TimeUtil.isoStringToDate(rs.getString("CompetitionDate"));
				carrera.aforoMax = rs.getInt("slots");
				carrera.aforoActual = rs.getInt("actualSlots");
				carreras.add(carrera);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pst.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return carreras;
	}

	







}
