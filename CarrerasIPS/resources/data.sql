--Datos para carga inicial de la base de datos

	
INSERT INTO COMPETITION(IDCOMPETITION,NAME,COMPETITION_TYPE, DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,RESERVED,INMOMENTINSCRIPTION,SECUENCIAL,SLOTS)
				VALUES ('5', 'Prueba Plazos Inscripcion', 'ASFALTO', 12, 23, '2022-01-01', '2021-02-02',7,false,false,20);
INSERT INTO COMPETITION (IDCOMPETITION,NAME,COMPETITION_TYPE,DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,RESERVED,INMOMENTINSCRIPTION,SECUENCIAL,SLOTS)
				VALUES ('1', 'Carrera peq nicolas', 'ASFALTO', 20, 10,'2020-11-07', '2020-11-14',3,false,false,30);
INSERT INTO COMPETITION (IDCOMPETITION,NAME,COMPETITION_TYPE,DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,RESERVED,INMOMENTINSCRIPTION,SECUENCIAL,SLOTS) 
				VALUES ('2', 'Ruta Picos', 'MONTANA', 10, 20,'2020-12-01', '2020-12-10',2,false,true,30);
INSERT INTO COMPETITION(IDCOMPETITION,NAME,COMPETITION_TYPE, DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,RESERVED,INMOMENTINSCRIPTION,SECUENCIAL,SLOTS)
				VALUES ('3', 'Maraton trama gurtel', 'MONTANA', 24, 7, '2020-12-12', '2020-12-25',1,true,false,20);
INSERT INTO COMPETITION(IDCOMPETITION,NAME,COMPETITION_TYPE, DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,RESERVED,INMOMENTINSCRIPTION,SECUENCIAL,SLOTS)
				VALUES ('4', 'Carrera Cursos Formacion', 'ASFALTO', 12, 23, '2021-01-01', '2021-02-02',5,true,true,20);
INSERT INTO COMPETITION(IDCOMPETITION,NAME,COMPETITION_TYPE, DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,RESERVED,INMOMENTINSCRIPTION,SECUENCIAL,SLOTS)
				VALUES ('6', 'Carrera Prueba Sprint 3', 'ASFALTO', 12, 23, '2020-12-02', '2020-12-04',5,true,true,20);
INSERT INTO COMPETITION(IDCOMPETITION,NAME,COMPETITION_TYPE, DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,RESERVED,INMOMENTINSCRIPTION,SECUENCIAL,SLOTS)
				VALUES ('7', 'Carrera Prueba2 Sprint 3', 'ASFALTO', 12, 23, '2020-12-02', '2020-12-04',5,true,false,20);
INSERT INTO COMPETITION(IDCOMPETITION,NAME,COMPETITION_TYPE, DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,RESERVED,INMOMENTINSCRIPTION,SECUENCIAL,SLOTS)
				VALUES ('8', 'Carrera Prueba3 Sprint 3', 'ASFALTO', 12, 23, '2020-12-02', '2020-12-04',5,false,true,20);
INSERT INTO COMPETITION(IDCOMPETITION,NAME,COMPETITION_TYPE, DISTANCE,INSCRIPTION_FEE,INSCRIPTION_DATE_END,COMPETITION_DATE,RESERVED,INMOMENTINSCRIPTION,SECUENCIAL,SLOTS)
				VALUES ('9', 'Carrera Prueba4 Sprint 3', 'ASFALTO', 12, 23, '2020-12-02', '2020-12-04',5,false,false,20);				
	
	
INSERT INTO CATEGORY (IDCATEGORY, IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('1','1','HOMBRE','SENIOR MASCULINO',18,1000);
INSERT INTO CATEGORY (IDCATEGORY,IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('2','1','MUJER','SENIOR FEMENINO',18,1000);
				
INSERT INTO CATEGORY (IDCATEGORY, IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('3','2','HOMBRE','SENIOR MASCULINO',18,1000);
INSERT INTO CATEGORY (IDCATEGORY,IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('4','2','MUJER','SENIOR FEMENINO',18,1000);
				
INSERT INTO CATEGORY (IDCATEGORY, IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('5','3','HOMBRE','SENIOR MASCULINO',18,1000);
INSERT INTO CATEGORY (IDCATEGORY,IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('6','3','MUJER','SENIOR FEMENINO',18,1000);
				
INSERT INTO CATEGORY (IDCATEGORY, IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('7','4','HOMBRE','SENIOR MASCULINO',18,1000);
INSERT INTO CATEGORY (IDCATEGORY,IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('8','4','MUJER','SENIOR FEMENINO',18,1000);
				
INSERT INTO CATEGORY (IDCATEGORY, IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('9','5','HOMBRE','SENIOR MASCULINO',18,1000);
INSERT INTO CATEGORY (IDCATEGORY,IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('10','5','MUJER','SENIOR FEMENINO',18,1000);

INSERT INTO CATEGORY (IDCATEGORY, IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('11','6','HOMBRE','SENIOR MASCULINO',18,1000);
INSERT INTO CATEGORY (IDCATEGORY,IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('12','6','MUJER','SENIOR FEMENINO',18,1000);
				
INSERT INTO CATEGORY (IDCATEGORY, IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('13','7','HOMBRE','SENIOR MASCULINO',18,1000);
INSERT INTO CATEGORY (IDCATEGORY,IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('14','7','MUJER','SENIOR FEMENINO',18,1000);
				
INSERT INTO CATEGORY (IDCATEGORY, IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('15','8','HOMBRE','SENIOR MASCULINO',18,1000);
INSERT INTO CATEGORY (IDCATEGORY,IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('16','8','MUJER','SENIOR FEMENINO',18,1000);
				
INSERT INTO CATEGORY (IDCATEGORY, IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('17','9','HOMBRE','SENIOR MASCULINO',18,1000);
INSERT INTO CATEGORY (IDCATEGORY,IDCOMPETITION, SEX,NAME,INITIAL_AGE,FINAL_AGE)
				VALUES ('18','9','MUJER','SENIOR FEMENINO',18,1000);



INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('000000001A', 'Francisco', 'Franco Bahamonte', 'paquito@movimiento.es', 'HOMBRE', '1975-11-20');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('123123d', 'Jabiertzo', 'echearria', 'javi@gmail.chi', 'HOMBRE', '1992-03-01');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('2352352352F', 'Benito', 'Mussolini', 'benit@fascio.ita', 'HOMBRE', '1939-10-08');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('8568456F', 'Margaret', 'Thacher', 'marga@goverment.eng', 'MUJER', '2002-04-04');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('9837598G', 'Maria Teresa', 'De calcula', 'sormaria@iglesia.com', 'MUJER', '1985-10-12');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('2352352D', 'Santiago', 'Abascal', 'santi@vox.es', 'HOMBRE', '1989-01-02');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('73472383B', 'Rosa', 'Diez', 'palpatine@emperador.gal', 'MUJER', '1994-02-25');
INSERT INTO PARTICIPANT (DNI,NAME,SURNAME,EMAIL,SEX,BIRTHDATE)
				VALUES ('155E', 'Jordi', 'Pujol i Soley', 'puyol3@cat.es', 'HOMBRE', '1982-06-12');	
			

INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY, DORSAL,INSCRIPTIONSTATE)
				VALUES ('2352352D', 1, '1997-12-01', '1','SENIOR MASCULINO',36, 'PENDIENTE'); 
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('73472383B', 1, '2021-12-01', '2','SENIOR FEMENINO', 54,'INSCRITO'); 
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('2352352352F', 1, '2021-12-04', '1','SENIOR MASCULINO', 22,'PENDIENTE'); 
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('123123d', 1, '2020-12-01', '2','SENIOR FEMENINO',21, 'INSCRITO'); 
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('2352352352F', 2, '2021-12-01', '1','SENIOR MASCULINO',23, 'INSCRITO');
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('8568456F', 1, '2020-12-04', '2','SENIOR FEMENINO',10, 'PENDIENTE');
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('9837598G', 1, '2022-06-07', '1', 'SENIOR MASCULINO',1,'INSCRITO');
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('9837598G', 3, '2020-12-12', '2','SENIOR FEMENINO',6, 'PENDIENTE');
				
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('155E', 1, '2020-11-05', '2','SENIOR FEMENINO', 4,'PENDIENTE');
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('155E', 2, '2020-10-12', '2','SENIOR FEMENINO', 1,'INSCRITO');
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('155E', 3, '2020-11-12', '2','SENIOR FEMENINO',4, 'INSCRITO');
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('2352352D', 3, '2020-11-12', '2','SENIOR MASCULINO', 5,'INSCRITO');
INSERT INTO INSCRIPTION (DNI,IDCOMPETITION,INSCRIPTIONDATE,IDCATEGORY, CATEGORY,DORSAL,INSCRIPTIONSTATE)
				VALUES ('155E', 4, '2020-11-25', '2','SENIOR FEMENINO',3, 'PENDIENTE');
				
INSERT INTO INSCRIPTION_DEADLINE(IDDEADLINE,IDCOMPETITION,INITIALDATE,FINALDATE,FEE)
                                VALUES ('1','1','2020-11-5','2020-11-07', 10);
INSERT INTO INSCRIPTION_DEADLINE(IDDEADLINE,IDCOMPETITION,INITIALDATE,FINALDATE,FEE)
                                VALUES ('2','2','2020-11-5','2020-12-01', 5);
INSERT INTO INSCRIPTION_DEADLINE(IDDEADLINE,IDCOMPETITION,INITIALDATE,FINALDATE,FEE)
                                VALUES ('3','3','2020-11-5','2020-12-12', 7);
INSERT INTO INSCRIPTION_DEADLINE(IDDEADLINE,IDCOMPETITION,INITIALDATE,FINALDATE,FEE)
                                VALUES ('4','4','2020-11-5','2021-01-01', 4);
INSERT INTO INSCRIPTION_DEADLINE(IDDEADLINE,IDCOMPETITION,INITIALDATE,FINALDATE,FEE)
				VALUES ('6','5','2020-11-5','2020-11-07', 4);
INSERT INTO INSCRIPTION_DEADLINE(IDDEADLINE,IDCOMPETITION,INITIALDATE,FINALDATE,FEE)
				VALUES ('7','5','2020-11-8','2022-01-01', 666);	
				
INSERT INTO CLASIFICATION (IDCOMPETITION,DNI,CATEGORYNAME,DORSAL,INITIALTIME,FINALTIME,SEX) VALUES (4,'9837598G','Veterano A Fem', 3,'15:03:20','20:10:14','MUJER');
INSERT INTO CLASIFICATION (IDCOMPETITION,DNI,CATEGORYNAME,DORSAL,INITIALTIME,FINALTIME,SEX) VALUES (3,'155E','Veterano B Mas',2,'00:00:00','00:00:00','HOMBRE');
INSERT INTO CLASIFICATION (IDCOMPETITION,DNI,CATEGORYNAME,DORSAL,INITIALTIME,FINALTIME,SEX) VALUES (4,'123123d','Veterano A Mas',30,'00:00:00','01:02:01','HOMBRE');
INSERT INTO CLASIFICATION (IDCOMPETITION,DNI,CATEGORYNAME,DORSAL,INITIALTIME,FINALTIME,SEX) VALUES (4,'155E','Veterano B Mas',4,'--:--:--','00:00:00','HOMBRE');
INSERT INTO CLASIFICATION (IDCOMPETITION,DNI,CATEGORYNAME,DORSAL,INITIALTIME,FINALTIME,SEX) VALUES (4,'8568456F','Veterano B Fem',5,'00:00:00','--:--:--','MUJER');
INSERT INTO CLASIFICATION (IDCOMPETITION,DNI,CATEGORYNAME,DORSAL,INITIALTIME,FINALTIME,SEX) VALUES (4,'2352352D','Senior Mas',6,'00:00:10','02:00:01','HOMBRE');
INSERT INTO CLASIFICATION (IDCOMPETITION,DNI,CATEGORYNAME,DORSAL,INITIALTIME,FINALTIME,SEX) VALUES (4,'73472383B','Senior Fem',7,'00:00:00','01:10:23','MUJER');

