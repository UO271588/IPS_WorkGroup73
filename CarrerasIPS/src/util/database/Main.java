package util.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import business.race.RaceDto;
import dbAccess.CompetitionsAccess;

public class Main {


	public static void main( String args[] ) {
		
		try {
			createTables();
			addData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Connection c = null;    
//
//		try {
//			Class.forName("org.sqlite.JDBC");
//			c = DriverManager.getConnection("jdbc:sqlite:test.db");//esta linea crea la base de datos en caso de que no exista
//			System.out.println("Opened database successfully");
//
//			createTables(c);
//			addData(c);
//			c.close();
//		} catch ( Exception e ) {
//			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//			System.exit(0);
//		}

	}

	@SuppressWarnings("deprecation")
	private static void addData() throws SQLException {

		Database db = new Database();
		db.loadDatabase();
//		Statement stmt = c.createStatement();
//		stmt = c.createStatement();
//		String sql = "INSERT INTO competition (IDcompetition,NAME,TIPO,DISTANCE,InscriptionFee,InscriptionDateEnd,CompetitionDate) " +
//				"VALUES (0000001, 'Carrera peq nicolas', 'CIUDAD', 20, 10,'2020-11-7', 2020-11-14);"; 
//		stmt.execute(sql);
//		sql = "INSERT INTO competition (IDcompetition,NAME,TIPO,DISTANCE,InscriptionFee,InscriptionDateEnd,CompetitionDate) " +
//				"VALUES (0000002, 'Ruta Picos', 'RUTA', 10, 20,2020-12-1, 2020-12-10);"; 
//		stmt.execute(sql);
//		sql = "INSERT INTO PARTICIPANTE (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE) " +
//				"VALUES ('000000001A', 'Francisco', 'Franco Bahamonte', 'paquito@movimiento.es', 'HOMBRE', 1975-11-20);"; 
//		stmt.execute(sql);
//		sql = "INSERT INTO INSCRIPCION (DNI,IDcompetition,INSCRIPTIONDATE,CATEGORY,INSCRIPTIONSTATE) " +
//				"VALUES ('000000001A', 0000001, 2020-12-1, 'veterano a', 'PENDIENTE');"; 
//		stmt.execute(sql);
		
		
		
		
	
        System.out.println("added Data sucess");
	}

	private static void createTables() throws SQLException {
		Database db = new Database();
		db.createDatabase(true);
		System.out.println("Tables Created Sucessfully");
//        String sql = "CREATE TABLE competition " +
//                       "(IDcompetition  VARCHAR(20) PRIMARY KEY     NOT NULL," +
//                       " NAME           VARCHAR(20)    NOT NULL, " +                      
//                       " TIPO   		VARCHAR(20)  NOT NULL CHECK(TIPO IN ('RUTA','CIUDAD')), " +
//                       " DISTANCE            INT     NOT NULL, " + 
//                       " InscriptionFee       INT     NOT NULL, " + 
//                       " InscriptionDateEnd   DATE    , " + 
//                       " CompetitionDate   DATE     )"; 
//        stmt.executeUpdate(sql);
//        sql = "CREATE TABLE PARTICIPANTE " +
//                "(DNI  VARCHAR(20) PRIMARY KEY     NOT NULL," +
//                " NAME           VARCHAR(20)    NOT NULL, " + 
//                " SURNAME           VARCHAR(20)    NOT NULL, " +
//                " EMAIL        VARCHAR(30)    NOT NULL, " +
//                " SEX            VARCHAR(8)     NOT NULL CHECK(SEX IN ('HOMBRE','MUJER')), " + 
//                " BIRTHDATE       DATE     NOT NULL)"; 
//        stmt.executeUpdate(sql);
//		 sql = "CREATE TABLE INSCRIPCION " +
//                "(DNI  VARCHAR(20)  NOT NULL," +
//                " IDcompetition  VARCHAR(20) NOT NULL," +
//                " INSCRIPTIONDATE DATE NOT NULL," + 
//                " CATEGORY           VARCHAR(20)    NOT NULL, " +
//                " INSCRIPTIONSTATE        VARCHAR(20)    NOT NULL check(INSCRIPTIONSTATE in ('PAGADO','PENDIENTE')), " +
//                " CONSTRAINT fk_DNI FOREIGN  KEY  (dni) REFERENCES  participante(dni),"
//                + "CONSTRAINT fk_idcomp FOREIGN  KEY  (idcompetition) references competition(IDCompetition)"
//                + ")";
//		 stmt.executeUpdate(sql);
//		 stmt.close();
		 
		
	}

}
