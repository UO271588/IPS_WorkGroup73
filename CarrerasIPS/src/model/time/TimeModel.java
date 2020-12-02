package model.time;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DbUtil;
import util.UnexpectedException;

public class TimeModel {
	
	
	public TimeModel(String raceId) {
		
	}
	public void insert(String idRace,int dorsal,String initialTime,String finalTime) {
		DbUtil du = new DbUtil();
		String sql = "INSERT INTO CLASIFICATION (IDCOMPETITION,DNI,CATEGORYNAME,DORSAL,INITIALTIME,FINALTIME,SEX) VALUES (?,?, ?, ?, ?,?,?)";
		du.executeUpdate(sql, idRace,findDni(idRace, dorsal),findNameCategory(idRace, dorsal),dorsal,initialTime,finalTime,findSex(idRace, dorsal));
	}
	
	public String findNameCategory(String idRace,int dorsal) {
		String sql = "select category from inscription where dorsal = ? and idcompetition=?";
		String nameCategory = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setInt(1, dorsal);
			pstmt.setString(2, idRace);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				nameCategory = rs.getString(1);
			}
			return nameCategory;
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
	
	public String findSex(String idRace,int dorsal) {
		String sql = "select sex from participant where dni = ?";
		String dni = findDni(idRace, dorsal);
		String sex = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, dni);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sex = rs.getString(1);
			}
			return sex;
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
	
	public String findDni(String idRace, int dorsal) {
		String sql = "select dni from inscription where dorsal = ? and idcompetition=?";
		String dni = "";
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setInt(1, dorsal);
			pstmt.setString(2, idRace);
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
	public boolean existDorsal(int dorsal,String id) {
		String sql = "select dorsal from inscription where dorsal = ? and idcompetition=?";
		int d = -1;
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setInt(1, dorsal);
			pstmt.setString(2, id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				d = rs.getInt(1);
			}
			if(d==-1) {
				return false;
			}
			return true;
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
	
	public boolean existeYaClasificacion(String raceId, int dorsal) {
		String sql = "select dorsal from clasification where dorsal = ? and idcompetition=?";
		int d = -1;
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			cn = DbUtil.getConnection();
			pstmt = cn.prepareStatement(sql);
			pstmt.setInt(1, dorsal);
			pstmt.setString(2, raceId);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				d = rs.getInt(1);
			}
			if(d==-1) {
				return false;
			}
			return true;
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

}
