package model.participant;

import java.time.LocalDate;
import java.util.Date;

public class ParticipantDto {

	
	public String name;
	public String surname;
	public String dni;
	public String email;


	public Date birthday;
	public boolean sexMale;	//true es hombre, false es mujer
	

	

	public ParticipantDto(String name, String surname, String dni, String mail, Date birthday, boolean sexMale) {
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

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setSexMale(boolean sexMale) {
		this.sexMale = sexMale;
	}

	
	public ParticipantDto() {
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


	public Date getBirthday() {
		return birthday;
	}


	public boolean isSexMale() {
		return sexMale;
	}
	
	
	
	
}
