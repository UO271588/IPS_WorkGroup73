package business.race;

import java.util.Date;

public class RaceDto implements Comparable<RaceDto> {
	public String id;
	public String nombre;
	public String tipo;
	public Double distancia;
	public Double precioInscripcion;
	public Date fechaLimite;
	public Date fechaCarrera;
	public int aforoMax;
	public int aforoActual;
	
	@Override
	public int compareTo(RaceDto o) {
		return this.fechaLimite.compareTo(o.fechaLimite);
	}	
	
	
}
