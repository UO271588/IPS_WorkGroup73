package util;

import model.participant.ParticipantDtoPojo;

public class JustificanteClub {

	public String nombreClub;
	public String aceptados = "";
	public String denegados = "";
	
	public JustificanteClub(String nombreClub) {
		this.nombreClub = nombreClub;
	}

	public void addAceptado(ParticipantDtoPojo dto) {
		aceptados += "Nombre: " + dto.name;
		aceptados += "\n";
	}

	public void addDenegado(ParticipantDtoPojo dto, String string) {
		denegados += "Nombre: " + dto.name + " motivo denegado: " + string;
		denegados += "\n";
		
	}

	@Override
	public String toString() {
		return "Justificante de inscripcion: \n"
				+ " Nombre del Club: " + nombreClub + 
				"\n Jugadores aceptados :\n"
				+ aceptados + "Jugadores denegados :\n" +
				 denegados
				+ "]";
	}
	
}
