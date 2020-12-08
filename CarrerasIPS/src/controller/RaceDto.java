package controller;

import java.util.Date;

public class RaceDto implements Comparable<RaceDto> {
	public String id;
	public String nombre;
	public String tipo;
	public double distancia;
	public double precioInscripcion;
	public Date fechaLimite;
	public Date fechaCarrera;
	public int aforoMax;
	public int aforoActual;
	public boolean inMomentInscription;
	public boolean secuencial;
	public int reserved;
    
	@Override
	public int compareTo(RaceDto o) {
		return this.fechaLimite.compareTo(o.fechaLimite);
	}	
	
	
}
