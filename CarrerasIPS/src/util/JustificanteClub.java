package util;

import model.participant.ParticipantDtoPojo;

public class JustificanteClub {

	public String nombreClub;
	public String aceptados = "";
	public String denegados = "";
	public double precio = 0;
	
	public JustificanteClub(String nombreClub) {
		this.nombreClub = nombreClub;
	}

	public void addAceptado(ParticipantDtoPojo dto, String category, double precio) {
		this.precio += precio;
		aceptados += "    Nombre: " + dto.name + " " + dto.surname;
		aceptados += "\n";
		aceptados += "        Categoria: " + category;
		aceptados += "\n";
		aceptados += "    -------------------------------------------------";
		aceptados += "\n";
	}

	public void addDenegado(ParticipantDtoPojo dto, String string) {
		denegados += "    Nombre: " + dto.name + " motivo: " + string;
		denegados += "\n";
		denegados += "    -------------------------------------------------";
		denegados += "\n";
		
	}

	@Override
	public String toString() {
		return "Justificante de inscripcion:\n"
				+ "Precio Total a pagar: "+ precio 
				+ "\nNombre del Club: " + nombreClub + 
				"\nJUGADORES ACEPTADOS :\n"
				+ aceptados + "JUGADORES DENEGADOS :\n" +
				 denegados;
	}
	
}
