package model.inscription;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import business.race.RaceDto;
import util.DbUtil;
import util.Justificante;
import util.TimeUtil;
import util.UnexpectedException;
import util.database.Database;

public class InscriptionModel {

	Justificante j = new Justificante();
	String fecha_hoy;
	Date date_hoy = new Date();
	Calendar cal_hoy = Calendar.getInstance();
	Calendar cal_birthdate;
	String nombreCarrera;

	public InscriptionModel(RaceDto carrera) {
		fecha_hoy = TimeUtil.dateToIsoString(date_hoy);
		cal_hoy.setTime(date_hoy);
		this.nombreCarrera = carrera.nombre;

	}

	public InscriptionModel() {
	}

	public void justificante(String email, String name) {

		int idCompeticion = getIdCompeticion(name);
		cal_birthdate = Calendar.getInstance();
		cal_birthdate.setTime(getBirthdate(email));

		j.setNombre(getNombre_justificante(email));
		j.setnombreCompeticion(name);
		j.setCantidad(getCantidad(idCompeticion));
		j.setFecha_inscripcion(date_hoy);
		j.setEstado("PRE-INSCRITO");
		j.setCategoria(categoria(email));

		insert(idCompeticion, email);

	}

	private void insert(int idCompeticion, String email) {
		DbUtil du = new DbUtil();
		String sql = "INSERT INTO inscripcion (DNI,IDCompetition,INSCRIPTIONDATE,CATEGORY,INSCRIPTIONSTATE) VALUES (?, ?, ?, ?,?)";
		du.executeUpdate(sql, getDni(email), idCompeticion, fecha_hoy, j.getCategoria(), j.getEstado());
	}

	// Cambiar a ParticipantModel
	public static String getDni(String e) {
		String sql = "select DNI from participante where email=?";
		String dni = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, e);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dni = rs.getString(1);
			}
			return dni;
		} catch (SQLException ex) {
			throw new UnexpectedException(ex);
		} finally {
			try {
				rs.close();
				pstmt.close();
				cn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}

	public static int getIdCompeticion(String name) {
		String sql = "select idcompetition " + "from competition\r\n" + "where name = ?";
		int idCompeticion = 0;
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				idCompeticion = rs.getInt(1);

			}
			return idCompeticion;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				cn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public int getCantidad(int id) {
		String sql = "select InscriptionFee " + "from competition " + "where idcompetition=?";
		int cantidad = 0;
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cantidad = rs.getInt(1);
			}

			return cantidad;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				cn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public Date getBirthdate(String email) {
		String sql = "select BIRTHDATE " + "from PARTICIPANTE " + "where EMAIL = ?";
		String birthdate = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				birthdate = rs.getString(1);
			}

			return TimeUtil.isoStringToDate(birthdate);
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				cn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public String getSex(String email) {
		String sql = "select sex " + "from PARTICIPANTE  " + "where EMAIL = ?";
		String sex = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sex = rs.getString(1);
			}

			return sex;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				cn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public String categoria(String email) {
		int a�os = cal_hoy.get(Calendar.YEAR) - cal_birthdate.get(Calendar.YEAR);

		String categoria = "";
		if (a�os >= 18 && a�os <= 34) {
			if (getSex(email).equals("HOMBRE")) {
				categoria = "Senior Masculino";
			}
			if (getSex(email).equals("MUJER")) {
				categoria = "Senior Femenino";

			}
		}

		if (a�os >= 35 && a�os <= 49) {
			if (getSex(email).equals("HOMBRE")) {
				categoria = "Veterano A Masculino";
			}

		}

		if (a�os >= 35 && a�os <= 54) {
			if (getSex(email).equals("MUJER")) {
				categoria = "Veterano A Femenino";
			}
		}

		if (a�os >= 50) {
			if (getSex(email).equals("HOMBRE")) {
				categoria = "Veterano B Masculino";
			}

		}

		if (a�os >= 55) {
			if (getSex(email).equals("MUJER")) {
				categoria = "Veterano B Femenino";
			}
		}

		return categoria;

	}

	public static void updateEstado(String estado, String dni, String idcompetition) {
		Database db = new Database();
		String sql = "UPDATE inscripcion SET INSCRIPTIONSTATE = ? WHERE dni = ? AND idcompetition = ? ";
		System.out.println(estado + dni + idcompetition);
		db.executeUpdate(sql, estado, dni, idcompetition);
	}

	public String getNombre_justificante(String email) {
		String sql = "select NAME " + "from PARTICIPANTE  " + "where EMAIL = ?";
		String nombre_justificante = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				nombre_justificante = rs.getString("name");
			}

			return nombre_justificante;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				cn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public String getAforo(String id) {
		String sql = "select aforo " + "from competition  " + "where IdCompetition = ?";
		String aforo = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				aforo = rs.getString("aforo");
			}

			return aforo;
		} catch (SQLException e) {
			throw new UnexpectedException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				cn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public Justificante getJustificante() {
		return this.j;
	}

	public boolean existeEmail(String email) {
		String sql = "select email from participante where email=?";
		try {
			if (DbUtil.existRowStringDB(sql, email))
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Este email no est� registrado");
		return false;
	}

	public boolean comprobacionDniPorNombre(String e) {
		String sql = "select dni from inscripcion where dni= ?";
		try {
			return DbUtil.existRowStringDB(sql, getDni(e));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;

	}
	
	public static boolean comprobacionDniPorDni(String e) {
		String sql = "select dni from inscripcion where dni= ?";
		try {
			return DbUtil.existRowStringDB(sql, e);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;

	}

	

	public static boolean existeYaInscripcion(String e, String name) {
			String sql = "select idcompetition from inscripcion where idcompetition=? and dni ="+e;
			try {
				if(DbUtil.existRowStringDB(sql, String.valueOf(getIdCompeticion(name)))) {
					return true;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}

	/**
	 * obtiene una lista de IncriptionDto (con el nombre) con todas las
	 * inscripciones para una carrera
	 * 
	 * @param id
	 * @return
	 */
	public List<InscriptionDto> getInscriptionList(String id) {

		String sql = "select name,participante.dni, idcompetition, inscriptionDate,category, inscriptionstate"
				+ " from inscripcion, participante where participante.dni = inscripcion.dni and idcompetition = ?";
		Database db = new Database();
		return db.executeQueryPojo(InscriptionDto.class, sql, id);

	}

}