package model.inscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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

		String idCompeticion = getIdCompeticion(name);
		cal_birthdate = Calendar.getInstance();
		cal_birthdate.setTime(getBirthdate(email));

		j.setNombre(getNombre_justificante(email));
		j.setnombreCompeticion(name);
		j.setCantidad(getCantidad(idCompeticion));
		j.setFecha_inscripcion(date_hoy);
		j.setEstado("PRE-INSCRITO");
		j.setCategoria(categoria(email,name));
		String idCategory = getIdCategory(email,name);
		insert(idCompeticion, email,idCategory);

	}

	

	private void insert(String idCompeticion, String email, String idCategory) {
		DbUtil du = new DbUtil();
		String sql = "INSERT INTO inscription (DNI,IDCompetition,INSCRIPTIONDATE,IDCATEGORY,CATEGORY,INSCRIPTIONSTATE) VALUES (?, ?, ?, ?,?,?)";
		du.executeUpdate(sql, getDni(email), idCompeticion, fecha_hoy, idCategory,j.getCategoria(), j.getEstado());
	}

	// Cambiar a ParticipantModel
	public static String getDni(String e) {
		String sql = "select DNI from participant where email=?";
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

	public static String getIdCompeticion(String name) {
		String sql = "select idcompetition " + "from competition\r\n" + "where name = ?";
		String idCompeticion = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				idCompeticion = rs.getString(1);

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

	public int getCantidad(String idCompeticion) {
		String sql = "select d.fee from inscription_deadline d, inscription i where d.idcompetition=? and i.inscriptiondate>=d.initialdate and i.inscriptiondate<=d.finaldate";
		int cantidad = 0;
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, idCompeticion);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cantidad = rs.getInt("fee");
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
		String sql = "select BIRTHDATE " + "from PARTICIPANT " + "where EMAIL = ?";
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
		String sql = "select sex " + "from PARTICIPANT  " + "where EMAIL = ?";
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
	private String getIdCategory(String email, String name) {
			String sql= "select idcategory from category where name=? and idCompetition=?";
			String idC= getIdCompeticion(name);
			String categoria = categoria(email,name);
			String idCategory="";
			Connection cn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				cn = DbUtil.getConnection();
				pstmt = cn.prepareStatement(sql);
				pstmt.setString(1, categoria);
				pstmt.setString(2, idC);
				
				rs = pstmt.executeQuery();
				while (rs.next()) {
					idCategory = rs.getString(1);
				}

				return idCategory;
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
	public String categoria(String email,String name) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		 LocalDate hoy = LocalDate.now();   
		 LocalDate nacimiento = getBirthdate(email).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		 int edad = (int) ChronoUnit.YEARS.between(nacimiento, hoy); 
		 String categoria="";
		 String sql = "select name from category where idcompetition=? and initial_age<=? and final_age>=? and sex = ?";
		 String sex = getSex(email);
		 String idC= getIdCompeticion(name);
		  Connection cn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				cn = DbUtil.getConnection();
				pstmt = cn.prepareStatement(sql);
				pstmt.setString(1, idC+"");
				pstmt.setInt(2, edad);
				pstmt.setInt(3, edad);
				pstmt.setString(4, sex);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					categoria = rs.getString(1);
				}

				return categoria;
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
//		System.out.print(edad);
//		int años = cal_hoy.get(Calendar.YEAR) - cal_birthdate.get(Calendar.YEAR);
//
//		String categoria = "";
//		if (años >= 18 && años <= 34) {
//			if (getSex(email).equals("HOMBRE")) {
//				categoria = "Senior Masculino";
//			}
//			if (getSex(email).equals("MUJER")) {
//				categoria = "Senior Femenino";
//
//			}
//		}
//
//		if (años >= 35 && años <= 49) {
//			if (getSex(email).equals("HOMBRE")) {
//				categoria = "Veterano A Masculino";
//			}
//
//		}
//
//		if (años >= 35 && años <= 54) {
//			if (getSex(email).equals("MUJER")) {
//				categoria = "Veterano A Femenino";
//			}
//		}
//
//		if (años >= 50) {
//			if (getSex(email).equals("HOMBRE")) {
//				categoria = "Veterano B Masculino";
//			}
//
//		}
//
//		if (años >= 55) {
//			if (getSex(email).equals("MUJER")) {
//				categoria = "Veterano B Femenino";
//			}
//		}
//
//		return categoria;
		

	

	public static void updateEstado(String estado, String dni, String idcompetition) {
		Database db = new Database();
		String sql = "UPDATE inscription SET INSCRIPTIONSTATE = ? WHERE dni = ? AND idcompetition = ? ";
		
		db.executeUpdate(sql, estado, dni, idcompetition);
	}

	public String getNombre_justificante(String email) {
		String sql = "select NAME " + "from PARTICIPANT  " + "where EMAIL = ?";
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

	//	public String getAforo(String id) {
	//		String sql = "select aforo " + "from competition  " + "where IdCompetition = ?";
	//		String aforo = "";
	//		Connection cn = null;
	//		PreparedStatement pstmt = null;
	//		ResultSet rs = null;
	//		try {
	//			cn = DbUtil.getConnection();
	//			pstmt = cn.prepareStatement(sql);
	//			pstmt.setString(1, id);
	//			rs = pstmt.executeQuery();
	//
	//			while (rs.next()) {
	//				aforo = rs.getString("aforo");
	//			}
	//
	//			return aforo;
	//		} catch (SQLException e) {
	//			throw new UnexpectedException(e);
	//		} finally {
	//			try {
	//				rs.close();
	//				pstmt.close();
	//				cn.close();
	//			} catch (SQLException e1) {
	//				// TODO Auto-generated catch block
	//				e1.printStackTrace();
	//			}
	//		}
	//	}

	public Justificante getJustificante() {
		return this.j;
	}

	public boolean existeEmail(String email) {
		String sql = "select email from participant where email=?";
		try {
			if (DbUtil.existRowStringDB(sql, email))
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Este email no está registrado");
		return false;
	}

	public boolean comprobacionDniPorNombre(String e) {
		String sql = "select dni from INSCRIPTION where dni= ?";
		try {
			return DbUtil.existRowStringDB(sql, getDni(e));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;

	}

	public static boolean comprobacionDniPorDni(String e) {
		String sql = "select dni from INSCRIPTION where dni= ?";
		try {
			return DbUtil.existRowStringDB(sql, e);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;

	}



	public static boolean existeYaInscripcion(String e, String name) {
		String sql = "select idcompetition from INSCRIPTION where idcompetition=? and dni ='"+e + "'";
		try {
			if(DbUtil.existRowStringDB(sql, String.valueOf(getIdCompeticion(name)))) {
				return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public static boolean yaPagoOEstaPendiente(String e, String name) {
		String sql = "select idcompetition from INSCRIPTION where idcompetition=? and dni ="+ e + "and estado in ('PAGADO','PENDIENTE')" ;
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

		String sql = "select name,par.dni, idcompetition, INSCRIPTIONDATE,CATEGORY, INSCRIPTIONSTATE"
				+ " from INSCRIPTION as ins, PARTICIPANT as par where par.dni = ins.dni and idcompetition = ?";
		Database db = new Database();
		return db.executeQueryPojo(InscriptionDto.class, sql, id);

	}

	/**
	 * Metodo que hace una consulta sobre todas las carreras a las que se ha inscrito un participante
	 * devuelve una lista de competiciones.
	 */
	public static List<InscriptionDto> getInscribedCompetitions(String dni){
		String sql = "select name, inscriptionstate, inscriptionDate, ins.idcompetition, ins.IDCATEGORY from INSCRIPTION as ins, competition where "
				+ "competition.idcompetition = ins.idcompetition and dni = ?";
		Database db = new Database();
		List<InscriptionDto> result = db.executeQueryPojo(InscriptionDto.class, sql, dni);
		return result;
	}

}