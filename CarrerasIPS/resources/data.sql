--Datos para carga inicial de la base de datos

	
	
INSERT INTO competition (IDcompetition,NAME,TIPO,DISTANCE,InscriptionFee,InscriptionDateEnd,CompetitionDate,slots)
				VALUES (0000001, 'Carrera peq nicolas', 'CIUDAD', 20, 10,'2020-11-7', 2020-11-14,30); 

INSERT INTO competition (IDcompetition,NAME,TIPO,DISTANCE,InscriptionFee,InscriptionDateEnd,CompetitionDate,slots) 
				VALUES (0000002, 'Ruta Picos', 'RUTA', 10, 20,2020-12-1, 2020-12-10,30);

INSERT INTO PARTICIPANTE (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('000000001A', 'Francisco', 'Franco Bahamonte', 'paquito@movimiento.es', 'HOMBRE', 1975-11-20);

INSERT INTO INSCRIPCION (DNI,IDcompetition,INSCRIPTIONDATE,CATEGORY,INSCRIPTIONSTATE)
				VALUES ('000000001A', 0000001, 2020-12-1, 'veterano a', 'PAGADO'); 
insert into competition(IDcompetition, name, tipo, distance, inscriptionFee, inscriptiondateend, competitiondate,slots) values
		('3', 'Carrera Prueba 2', 'CIUDAD', 15, 7, '2019-10-7', '2019-10-10',20);