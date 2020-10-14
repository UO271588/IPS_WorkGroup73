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
		 Connection c = null;    
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         c = DriverManager.getConnection("jdbc:sqlite:test.db");//esta linea crea la base de datos en caso de que no exista
	         System.out.println("Opened database successfully");
	         
	         createTables(c);
	         addData(c);
	         c.close();
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	     
	   }

	@SuppressWarnings("deprecation")
	private static void addData(Connection c) throws SQLException {
		CompetitionsAccess ca = new CompetitionsAccess();
		RaceDto carrera = new RaceDto();
		carrera.id = "2";
		carrera.nombre = "Carrera Prueba 2";
		carrera.tipo = "CIUDAD";
		carrera.distancia = 15;
		carrera.precioInscripcion = 7;
		carrera.fechaLimite = new java.sql.Date(new Date(119,10,7).getTime()).toLocalDate();
		carrera.fechaCarrera = new java.sql.Date(new Date(119,10,10).getTime()).toLocalDate();
		ca.addRace(carrera);
		
		
	
        System.out.println("added Data sucess");
	}

	private static void createTables(Connection c) throws SQLException {
		Statement stmt = c.createStatement();
        String sql = "CREATE TABLE competition " +
                       "(IDcompetition  VARCHAR(20) PRIMARY KEY     NOT NULL," +
                       " NAME           VARCHAR(20)    NOT NULL, " +                      
                       " TIPO   		VARCHAR(20)  NOT NULL CHECK(TIPO IN ('RUTA','CIUDAD')), " +
                       " DISTANCE            INT     NOT NULL, " + 
                       " InscriptionFee       INT     NOT NULL, " + 
                       " InscriptionDateEnd   DATE    , " + 
                       " CompetitionDate   DATE     )"; 
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE PARTICIPANTE " +
                "(DNI  VARCHAR(20) PRIMARY KEY     NOT NULL," +
                " NAME           VARCHAR(20)    NOT NULL, " + 
                " SURNAME           VARCHAR(20)    NOT NULL, " +
                " EMAIL        VARCHAR(30)    NOT NULL, " +
                " SEX            VARCHAR(8)     NOT NULL CHECK(SEX IN ('HOMBRE','MUJER')), " + 
                " BIRTHDATE       DATE     NOT NULL)"; 
        stmt.executeUpdate(sql);
		 sql = "CREATE TABLE INSCRIPCION " +
                "(DNI  VARCHAR(20)  NOT NULL," +
                " IDcompetition  VARCHAR(20) NOT NULL," +
                " INSCRIPTIONDATE DATE NOT NULL," + 
                " CATEGORY           VARCHAR(20)    NOT NULL, " +
                " INSCRIPTIONSTATE        VARCHAR(20)    NOT NULL check(INSCRIPTIONSTATE in ('PAGADO','PENDIENTE')), " +
                " CONSTRAINT fk_DNI FOREIGN  KEY  (dni) REFERENCES  participante(dni),"
                + "CONSTRAINT fk_idcomp FOREIGN  KEY  (idcompetition) references competition(IDCompetition)"
                + ")";
		 stmt.executeUpdate(sql);
		 stmt.close();
		 
		
	}

}
