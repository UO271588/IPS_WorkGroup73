--Datos para carga inicial de la base de datos

	
	
INSERT INTO COMPETITION (IDCOMPETITION,NAME,COMPETITION_TYPE,DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,SLOTS)
				VALUES ('1', 'Carrera peq nicolas', 'ASFALTO', 20, 10,'2020-11-7', '2020-11-14',30);
INSERT INTO COMPETITION (IDCOMPETITION,NAME,COMPETITION_TYPE,DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,SLOTS) 
				VALUES ('2', 'Ruta Picos', 'MONTANA', 10, 20,'2020-12-1', '2020-12-10',30);
INSERT INTO COMPETITION(IDCOMPETITION,NAME,COMPETITION_TYPE, DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,SLOTS)
				VALUES ('3', 'Carrera Prueba 2', 'ASFALTO', 15, 7, '2019-10-7', '2019-10-10',20);
	
	
INSERT INTO CATEGORY (IDCATEGORY,SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('1','HOMBRE','SENIOR MASCULINO',18,50);
INSERT INTO CATEGORY (IDCATEGORY,SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('2','MUJER','SENIOR FEMENINO',18,50);


INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('000000001A', 'Francisco', 'Franco Bahamonte', 'paquito@movimiento.es', 'HOMBRE', '1975-11-20');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('123123d', 'Jabiertzo', 'echearria', 'javi@gmail.chi', 'HOMBRE', '1992-3-1');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('2352352352F', 'Benito', 'Mussolini', 'benit@fascio.ita', 'HOMBRE', '1939-10-8');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('8568456F', 'Margaret', 'Thacher', 'marga@goverment.eng', 'MUJER', '2002-4-4');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('9837598G', 'Maria Teresa', 'De calcula', 'sormaria@iglesia.com', 'MUJER', '1985-10-12');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('2352352D', 'Santiago', 'Abascal', 'santi@vox.es', 'HOMBRE', '1989-1-2');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('73472383B', 'Rosa', 'Diez', 'palpatine@emperador.gal', 'MUJER', '1994-2-25');
				

INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY, INSCRIPTIONSTATE)
				VALUES ('2352352D', 1, '1997-12-1', '1','SENIOR MASCULINO', 'PENDIENTE'); 
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,INSCRIPTIONSTATE)
				VALUES ('73472383B', 1, '2021-12-1', '2','SENIOR FEMENINO', 'PAGADO'); 
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,INSCRIPTIONSTATE)
				VALUES ('2352352352F', 1, '2021-12-4', '1','SENIOR MASCULINO', 'PENDIENTE'); 
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,INSCRIPTIONSTATE)
				VALUES ('123123d', 1, '2020-12-1', '2','SENIOR FEMENINO', 'PAGADO'); 
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,INSCRIPTIONSTATE)
				VALUES ('2352352352F', 2, '2021-12-1', '1','SENIOR MASCULINO', 'PAGADO');
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,INSCRIPTIONSTATE)
				VALUES ('8568456F', 1, '2020-12-4', '2','SENIOR FEMENINO', 'PENDIENTE');
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,INSCRIPTIONSTATE)
				VALUES ('9837598G', 1, '2022-6-7', '1', 'SENIOR MASCULINO','PAGADO');
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,INSCRIPTIONSTATE)
				VALUES ('9837598G', 3, '2020-12-12', '2','SENIOR FEMENINO', 'PENDIENTE');