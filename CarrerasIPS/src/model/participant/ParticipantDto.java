package model.participant;

import java.time.LocalDate;
import java.util.Date;

public class ParticipantDto {

	
	public String name;
	public String surname;
	public String dni;
	public String mail;
	public Date birthday;
	public boolean sexMale;	//true es hombre, false es mujer
	
	
	public ParticipantDto() {
		super();
	}
	
	public ParticipantDto(String name, String surname, String dni, String mail, Date birthday, boolean sexMale) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.mail = mail;
		this.birthday = birthday;
		this.sexMale = sexMale;
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


	public String getMail() {
		return mail;
	}


	public Date getBirthday() {
		return birthday;
	}


	public boolean isSexMale() {
		return sexMale;
	}
	
	
	
	
}
