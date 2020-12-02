package dbAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import business.race.RaceDto;
import util.TimeUtil;
import util.database.Database;

public class CompetitionsAccess {

	public void addRace(RaceDto carrera){
		String SQL = "insert into competition (idcompetition,name,tipo,distance,inscriptionfee,inscriptiondateend,competitiondate,slots) values (?,?,?,?,?,?,?,?,?);";
		PreparedStatement pst = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			pst = c.prepareStatement(SQL);
			pst.setString(1, carrera.id);
			pst.setString(2, carrera.nombre);
			pst.setString(3, carrera.tipo);
			pst.setDouble(5, carrera.precioInscripcion);
			pst.setString(6, TimeUtil.DateToSQL(carrera.fechaLimite));
			pst.setString(7,  TimeUtil.DateToSQL(carrera.fechaLimite));
			pst.setInt(8, carrera.aforoMax);

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
		String SQL = "	update competition set name = ?, tipo = ?, distance = ?, inscriptionfee = ?, inscriptiondateend = ?, competitiondate = ?, slots = ? where idcompetition = ?;";
		PreparedStatement pst = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			pst = c.prepareStatement(SQL);
			pst.setString(1, carrera.nombre);
			pst.setString(2, carrera.tipo);
			pst.setDouble(3, carrera.distancia);
			pst.setDouble(4, carrera.precioInscripcion);
			pst.setString(5, TimeUtil.DateToSQL(carrera.fechaLimite));
			pst.setString(6, TimeUtil.DateToSQL(carrera.fechaLimite));
			pst.setInt(7, carrera.aforoMax);
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
				carrera.tipo = rs.getString("COMPETITION_TYPE");
				carrera.distancia = rs.getDouble("DISTANCE");
				carrera.precioInscripcion = rs.getDouble("INSCRIPTION_FEE");
				carrera.fechaLimite = TimeUtil.isoStringToDate(rs.getString("INSCRIPTION_DATE_END"));
				carrera.fechaCarrera = TimeUtil.isoStringToDate(rs.getString("COMPETITION_DATE"));
				carrera.aforoMax = rs.getInt("SLOTS");
				//carrera.aforoActual = rs.getInt("actualSlots");	//esto ya lo existe
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
	
	
	public static void createRace(RaceDto race) {

		Database db = new Database();
		String sql = "INSERT INTO COMPETITION (IDCOMPETITION,NAME,COMPETITION_TYPE,DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,RESERVED,INMOMENTINSCRIPTION,SECUENCIAL,SLOTS)"
				+ " VALUES (?, ?, ?, ?, ?,?, ?,?,?,?,?);";
		
		db.executeUpdate(sql,race.id, race.nombre, race.tipo, race.distancia, race.precioInscripcion, TimeUtil.dateToIsoString(race.fechaLimite), TimeUtil.dateToIsoString(race.fechaCarrera), race.reserved, race.inMomentInscription,race.secuencial,race.aforoMax);
			
		}
	
	/**
	 * Comprueba si hay espacio en una carrera
	 * @param idCompetition
	 */
	public static boolean checkSlots(String idCompetition) {
		boolean result = false;
		String SQL = "SELECT COUNT(*) < slots "
				+ "FROM competition as c, inscription as i "
				+ "WHERE i.IDCOMPETITION =  c.IDCOMPETITION  and i.IDCOMPETITION = ? "
				+ "GROUP BY i.IDCOMPETITION ";
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection c  = null;
		try {
			c= DriverManager.getConnection("jdbc:sqlite:test.db");

			pst = c.prepareStatement(SQL);
			pst.setString(1, idCompetition);
			rs = pst.executeQuery();
			result = rs.getBoolean(1);
			
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
		return result;
	}

	

		
		
				
	}

	







