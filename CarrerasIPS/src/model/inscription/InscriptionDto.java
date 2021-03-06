package model.inscription;

import java.sql.Time;
import java.time.LocalDate;

import util.TimeUtil;

import java.util.Date;
public class InscriptionDto implements Comparable<InscriptionDto>{
	public String idCompetition;
	public String dni;
	public String name;	// (opcional) se pone el nombre(del participante) para facilitar la consultad de atletas inscritos en carrera
	public String Category;
	public String idCategory;
	public String dorsal;
	public String club;
	public String anotations;




	

	private String inscriptionState;
	public String inscriptionDate;
	
	
	@Override
	/**
	 * Utilizado para ordenar listasde inscripciones
	 */
	public int compareTo( InscriptionDto ins) {
		if(this.getInscriptionDateAsDate().compareTo(ins.getInscriptionDateAsDate()) == 0) {
			return this.inscriptionState.compareTo(ins.inscriptionState);
		}
		
		return this.getInscriptionDateAsDate().compareTo(ins.getInscriptionDateAsDate());
		
	}
	
	
	
	
	


	public String getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(String idCategory) {
		this.idCategory = idCategory;
	}

	public String getIdCompetition() {
		return idCompetition;
	}


	public void setIdCompetition(String idCompetition) {
		this.idCompetition = idCompetition;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getName() {
		return name;
	}


	public void setName(String nombre) {
		this.name = nombre;
	}
	public String getClub() {
		return club;
	}







	public void setClub(String club) {
		this.club = club;
	}

	public String getCategory() {
		return Category;
	}


	public void setCategory(String category) {
		Category = category;
	}


	public String getInscriptionState() {
		return inscriptionState;
	}


	public void setInscriptionState(String inscriptionState) {
		this.inscriptionState = inscriptionState;
	}
	public String getInscriptionDate() {
		return inscriptionDate;
	}
	public void setInscriptionDate(String inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}
	
	public Date getInscriptionDateAsDate() {
		return TimeUtil.isoStringToDate(inscriptionDate);
	}



}