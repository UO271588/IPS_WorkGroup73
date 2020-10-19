--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
drop table competition;
drop table PARTICIPANTE;
drop table INSCRIPCION;

CREATE TABLE competition (IDcompetition  VARCHAR(20) PRIMARY KEY     NOT NULL,
                        NAME           VARCHAR(20)    NOT NULL,                      
                        TIPO   		VARCHAR(20)  NOT NULL CHECK(TIPO IN ('RUTA','CIUDAD')), 
                        DISTANCE            INT     NOT NULL,
                        InscriptionFee       INT     NOT NULL, 
                        InscriptionDateEnd   DATE    , 
                        CompetitionDate   DATE     ,
                        slots INT); 

CREATE TABLE PARTICIPANTE (DNI  VARCHAR(20) PRIMARY KEY     NOT NULL,
                NAME           VARCHAR(20)    NOT NULL,
                SURNAME           VARCHAR(20)    NOT NULL,
                EMAIL        VARCHAR(30)    NOT NULL,
                SEX            VARCHAR(8)     NOT NULL CHECK(SEX IN ('HOMBRE','MUJER')),
                BIRTHDATE       DATE     NOT NULL); 

CREATE TABLE INSCRIPCION  (DNI  VARCHAR(20)  NOT NULL,
                IDcompetition  VARCHAR(20) NOT NULL,
                INSCRIPTIONDATE DATE NOT NULL,
                CATEGORY           VARCHAR(20)    NOT NULL, 
                INSCRIPTIONSTATE        VARCHAR(20)    NOT NULL check(INSCRIPTIONSTATE in ('PAGADO','PENDIENTE','PRE-INSCRITO')), 
                PRIMARY KEY (DNI,IDcompetition),
                CONSTRAINT fk_DNI FOREIGN  KEY  (dni) REFERENCES  participante(dni),
                CONSTRAINT fk_idcomp FOREIGN  KEY  (idcompetition) references competition(IDCompetition));