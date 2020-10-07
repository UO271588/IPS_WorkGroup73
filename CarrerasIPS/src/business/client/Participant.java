package business.client;

import java.time.LocalDate;
import java.util.Date;

public class Participant {

	
	private String name;
	private String surname;
	private String dni;
	private String mail;
	private Date birthday;
	private boolean sexMale;	//true es hombre, false es mujer
	
	
	public Participant(String name, String surname, String dni, String mail, Date birthday, boolean sexMale) {
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
