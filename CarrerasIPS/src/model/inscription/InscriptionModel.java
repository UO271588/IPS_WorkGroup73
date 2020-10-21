package model.inscription;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import business.race.RaceDto;
import util.ApplicationException;
import util.DbUtil;
import util.Justificante;
import util.UnexpectedException;
import util.database.Database;

public class InscriptionModel {


	

		Justificante j = new Justificante();
		String fecha_hoy = "2020-14-07";
		Date date_hoy = isoStringToDate(fecha_hoy);


		Calendar cal_hoy = Calendar.getInstance();
		Calendar cal_birthdate;
		String nombreCarrera;


		public InscriptionModel(RaceDto carrera) {
			fecha_hoy = dateToIsoString(date_hoy);

			cal_hoy.setTime(date_hoy);
			this.nombreCarrera=carrera.nombre;

		}




		public InscriptionModel() {

		}




		public void justificante(String email, String name){

			int idCompeticion = getIdCompeticion(name);
			cal_birthdate = Calendar.getInstance();
			cal_birthdate.setTime(getBirthdate(email));

			j.setNombre(getNombre_justificante(email));
			j.setnombreCompeticion(name);
			j.setCantidad(getCantidad(idCompeticion));
			j.setFecha_inscripcion(date_hoy);
			j.setEstado("PRE-INSCRITO");
			j.setCategoria(categoria(email));



			insert(idCompeticion,email);

		}
		private void insert(int idCompeticion, String email) {

			String sql = "INSERT INTO inscripcion (DNI,IDCompetition,INSCRIPTIONDATE,CATEGORY,INSCRIPTIONSTATE) VALUES (?, ?, ?, ?,?)";


			executeUpdate(sql, getDni(email), idCompeticion, fecha_hoy,j.getCategoria(), j.getEstado());

			//		try {
			//		Connection cn=DbUtil.getConnection();
			//		PreparedStatement ps = cn.prepareStatement(sql);
			//		ps.setString(1, getDni(email));
			//		ps.setInt(2, idCompeticion);
			//		ps.setString(3, fecha_hoy);
			//		ps.setString(4, j.getCategoria());
			//		ps.setString(5, j.getEstado());
			//		// System.out.println(getDni(email)+"-"+idCompeticion+"-"+ fecha_hoy+"-"+j.getCategoria()+"-"+j.getEstado());
			//		ps.executeUpdate();
			//		
			//		ps.close();
			//		cn.close();
			//		
			//		}

			//		 catch (SQLException e1) {
			//			e1.printStackTrace();
			//		}

		}
		public void executeUpdate(String sql, Object... params) {
			Connection conn=null;
			try {
				conn=this.getConnection();
				QueryRunner runner=new QueryRunner();
				runner.update(conn, sql, params);
			} catch (SQLException e) {
				throw new UnexpectedException(e);
			} finally {
				DbUtils.closeQuietly(conn);
			}
		}




		public Connection getConnection() throws SQLException {
			return DriverManager.getConnection(getUrl());
		}




		private String getUrl() {
			// TODO Auto-generated method stub
			return "jdbc:sqlite:test.db";
		}




		public String getDni(String e) {
			String sql = "select DNI " + 
					"from participante\r\n" + 
					"where email=?";
			String dni="";
			Connection cn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				cn=DbUtil.getConnection();
				pstmt=cn.prepareStatement(sql);
				pstmt.setString(1, e);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					dni = rs.getString(1);

				}

				return dni;
			}
			catch (SQLException ex) {
				throw new UnexpectedException(ex);
			}
			finally {
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

		public int getIdCompeticion(String name) {
			String sql = "select idcompetition "
					+ "from competition\r\n"
					+ "where name = ?";
			int idCompeticion = 0;
			Connection cn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {

				cn=DbUtil.getConnection();
				pstmt=cn.prepareStatement(sql);
				pstmt.setString(1, name);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					idCompeticion = rs.getInt(1);

				}
				return idCompeticion;
			}
			catch (SQLException e) {
				throw new UnexpectedException(e);
			}
			finally {
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
			String sql = "select InscriptionFee "
					+ "from competition "
					+ "where idcompetition=?";
			int cantidad=0;
			Connection cn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				cn=DbUtil.getConnection();
				pstmt=cn.prepareStatement(sql);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					cantidad = rs.getInt(1);
				}


				return cantidad;
			}
			catch (SQLException e) {
				throw new UnexpectedException(e);
			}
			finally {
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
			String sql = "select BIRTHDATE " + 
					"from PARTICIPANTE " + 
					"where EMAIL = ?";
			String birthdate = "";
			Connection cn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				cn=DbUtil.getConnection();
				pstmt=cn.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					birthdate = rs.getString(1);
				}

				return isoStringToDate(birthdate);
			}
			catch (SQLException e) {
				throw new UnexpectedException(e);
			}
			finally {
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
			String sql = "select sex " + 
					"from PARTICIPANTE  " + 
					"where EMAIL = ?";
			String sex="";
			Connection cn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				cn=DbUtil.getConnection();
				pstmt=cn.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					sex = rs.getString(1);
				}

				return sex;
			}
			catch (SQLException e) {
				throw new UnexpectedException(e);
			}
			finally {
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
			int años = cal_hoy.get(Calendar.YEAR)-cal_birthdate.get(Calendar.YEAR);

			String categoria = "";
			if(años>=18 && años<=34) {
				if(getSex(email).equals("HOMBRE")) {
					categoria = "Senior Masculino";			}
				if(getSex(email).equals("MUJER")) {
					categoria = "Senior Femenino";

				}
			}

			if(años>=35 && años<=49) {
				if(getSex(email).equals("HOMBRE")) {
					categoria = "Veterano A Masculino";			}

			}

			if(años>=35 && años<=54) {
				if(getSex(email).equals("MUJER")) {
					categoria = "Veterano A Femenino";
				}
			}

			if(años>=50) {
				if(getSex(email).equals("HOMBRE")) {
					categoria = "Veterano B Masculino";			}


			}

			if(años>=55) {
				if(getSex(email).equals("MUJER")) {
					categoria = "Veterano B Femenino";
				}
			}

			return categoria;

		}

		public void update(int idCompetition, String dni) {
			//		String sql_inscriptiondate = "update inscripcion set inscriptiondate='?' where idcompetition=? and dni = '?'";
			//		String sql_inscriptionstate = "update inscripcion set inscriptionstate = 'PRE-INSCRITO' where idcompetition=? and dni=?";
			//		String sql_category = "update inscripcion set category = '?' where idcompetition= ? and dni='?'";
			//		DbUtil.executeUpdate(sql_inscriptiondate, dateToIsoString(j.getFecha_inscripcion()),idCompetition, dni);
			//		DbUtil.executeUpdate(sql_inscriptionstate, idCompetition, dni);
			//		DbUtil.executeUpdate(sql_category, j.getCategoria(), idCompetition, dni);
			try (Connection cn=DriverManager.getConnection("jdbc:sqlite:test.db")) { //NOSONAR
				try (Statement stmt = cn.createStatement()) {
					stmt.executeUpdate("update inscripcion\r\n" + 
							"set inscriptiondate ='" + dateToIsoString(j.getFecha_inscripcion()) +
							"' where idcompetition = " + idCompetition+ " and dni = '" + dni + "' ");
					stmt.executeUpdate("update inscripcion\r\n" + 
							"set inscriptionstate = 'PRE-INSCRITO'\r\n" + 
							" where idcompetition ="+ idCompetition+ " and dni = '" + dni + "' ");

					stmt.executeUpdate("update inscripcion\r\n" + 
							"set category = '"+ j.getCategoria()+"'\r\n" + 
							" where idcompetition ="+ idCompetition+ " and dni = '" + dni + "' ");

					stmt.close();

				}
				cn.close();

			} catch (SQLException e) {
				//Ojo, no dejar pasar las excepciones (no limitarse a dejar el codigo autoegenerado por Eclipse haciendo solo printStackTrace)
				throw new UnexpectedException(e); //Es mas habitual usar excepciones propias de la aplicacion
			}
		}



		public String getNombre_justificante(String email) {
			String sql = "select NAME " + 
					"from PARTICIPANTE  " + 
					"where EMAIL = ?";
			String nombre_justificante="";
			Connection cn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				cn=DbUtil.getConnection();
				pstmt=cn.prepareStatement(sql);
				pstmt.setString(1, email);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					nombre_justificante = rs.getString("name");
				}


				return nombre_justificante;
			}
			catch (SQLException e) {
				throw new UnexpectedException(e);
			}
			finally {
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
			String sql = "select aforo " + 
					"from competition  " + 
					"where IdCompetition = ?";
			String aforo="";
			Connection cn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			try {
				cn=DbUtil.getConnection();
				pstmt=cn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				while(rs.next()) {
					aforo = rs.getString("aforo");
				}


				return aforo;
			}
			catch (SQLException e) {
				throw new UnexpectedException(e);
			}
			finally {
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


		/** 
		 * Convierte fecha repesentada como un string iso a fecha java (para conversion de entradas de tipo fecha)
		 */
		public Date isoStringToDate(String isoDateString) {
			try {
				return new SimpleDateFormat("yyyy-MM-dd").parse(isoDateString);
			} catch (ParseException e) {
				throw new ApplicationException("Formato ISO incorrecto para fecha: "+isoDateString);
			}
		}
		/** 
		 * Convierte fecha java a un string formato iso (para display o uso en sql) 
		 */
		public String dateToIsoString(Date javaDate) {
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			return formatter.format(javaDate);
		}


		public Justificante getJustificante() {
			return this.j;
		}



		public boolean existeEmail(String e) {
			String sql = "select email from participante";
			String email="";
			Connection cn=null;
			Statement st=null;
			ResultSet rs=null;
			try {
				cn=DbUtil.getConnection();
				st=cn.createStatement();
				rs = st.executeQuery(sql);

				while(rs.next()) {
					email = rs.getString("email");
					if(email.equals(e))

						return true;
				}

				JOptionPane.showMessageDialog(null, "Este email no está registrado");
				return false;
			}
			catch (SQLException ex) {
				throw new UnexpectedException(ex);
			}
			finally {
				try {
					rs.close();
					st.close();
					cn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		public boolean comprobacionDni(String e) {
			//un mismo no solicatante no se inscriba dos veces a la misma competicion

			String dni = getDni(e);



			String sql1 = "select dni from inscripcion";
			String d = " ";
			Connection cn=null;
			Statement st=null;
			ResultSet rs=null;
			try {
				cn=DbUtil.getConnection();
				st=cn.createStatement();
				rs = st.executeQuery(sql1);

				while(rs.next()) {
					d = rs.getString("dni");
					if(d.equals(dni)) {
						return true;
					}

				}

				return false;

			}
			catch (SQLException ex) {
				throw new UnexpectedException(ex);
			}
			finally {
				try {
					rs.close();
					st.close();
					cn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}




		public boolean existeYaIniscripcion(String e, String n) {

			boolean existe = comprobacionDni(e);
			int idCompeticion = getIdCompeticion(n);
			String sql = "select idcompetition from inscripcion";
			int id =0;
			Connection cn=null;
			Statement st=null;
			ResultSet rs=null;
			try {
				cn=DbUtil.getConnection();
				st=cn.createStatement();
				rs = st.executeQuery(sql);

				while(rs.next()) {
					id = rs.getInt("idCompetition");
					if(id==idCompeticion) {
						if(existe==true) {
							JOptionPane.showMessageDialog(null, "Ya estas insctito en esta competicion");
							return true;
						}
					}

				}
				return false;

			}
			catch (SQLException ex) {
				throw new UnexpectedException(ex);
			}
			finally {
				try {
					rs.close();
					st.close();
					cn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		/**
		 * obtiene una lista de IncriptionDto (con el nombre) con todas las inscripciones para una carrera
		 * @param id
		 * @return
		 */
		public List<InscriptionDto> getInscriptionList(String id){
			

				String sql = "select name,participante.dni, idcompetition, inscriptionDate,category, inscriptionstate"
						+ " from inscripcion, participante where participante.dni = inscripcion.dni and idcompetition = ?";
			Database db = new Database();
			return db.executeQueryPojo(InscriptionDto.class, sql, id);

		}


	}
