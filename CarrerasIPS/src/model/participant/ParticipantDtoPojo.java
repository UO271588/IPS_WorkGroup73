package model.participant;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

import util.TimeUtil;

public class ParticipantDtoPojo {

	
	public String name;
	public String surname;
	public String dni;
	public String email;


	public String birthday;
	public boolean sexMale;	//true es hombre, false es mujer
	

	

	public ParticipantDtoPojo(String name, String surname, String dni, String mail, String birthday, boolean sexMale) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.email = mail;
		this.birthday = birthday;
		this.sexMale = sexMale;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setEmail(String mail) {
		this.email = mail;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public void setSexMale(boolean sexMale) {
		this.sexMale = sexMale;
	}

	
	public ParticipantDtoPojo() {
		super();
	}
	


	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}


	public String getDni() {
		return dni;
	}


	public String getEmail() {
		return email;
	}


	public String getBirthday() {
		return birthday;
	}
	
	public Date getBirthdayAsDate() {
		return TimeUtil.isoStringToDate(birthday);
	}
	public void setBirthdayAsDate(Date date) {
		this.birthday = TimeUtil.dateToIsoString(date);
	}


	public boolean isSexMale() {
		return sexMale;
	}
	
	
	
	
}
