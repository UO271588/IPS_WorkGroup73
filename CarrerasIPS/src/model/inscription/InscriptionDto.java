package model.inscription;

import java.time.LocalDate;

import business.race.RaceDto;

public class InscriptionDto implements Comparable<InscriptionDto>{
	public String idCompetition;
	public String dni;
	public String nombre;	// (opcional) se pone el nombre(del participante) para facilitar la consultad de atletas inscritos en carrera
	public String Category;
	public String InscriptionState;
	public LocalDate inscriptionDate;
	
	
	@Override
	/**
	 * Utilizado para ordenar listasde inscripciones
	 */
	public int compareTo( InscriptionDto ins) {
		if(this.inscriptionDate.compareTo(ins.inscriptionDate) == 0) {
			return this.InscriptionState.compareTo(ins.InscriptionState);
		}
		
		return this.inscriptionDate.compareTo(ins.inscriptionDate);
		
	}
}
