package model.inscription;

import java.time.LocalDate;

import business.race.RaceDto;

public class InscriptionDto implements Comparable<InscriptionDto>{
	public String idCompetition;
	public String dni;
	public String nombre;	// (opcional) se pone el nombre(del participante) para facilitar la consultad de atletas inscritos en carrera
	public String Category;
	public String inscriptionState;
	public LocalDate inscriptionDate;
	
	
	@Override
	/**
	 * Utilizado para ordenar listasde inscripciones
	 */
	public int compareTo( InscriptionDto ins) {
		if(this.inscriptionDate.compareTo(ins.inscriptionDate) == 0) {
			return this.inscriptionState.compareTo(ins.inscriptionState);
		}
		
		return this.inscriptionDate.compareTo(ins.inscriptionDate);
		
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


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
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


	public LocalDate getInscriptionDate() {
		return inscriptionDate;
	}


	public void setInscriptionDate(LocalDate inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}
}