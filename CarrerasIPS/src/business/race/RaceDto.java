package business.race;

import java.util.Date;

public class RaceDto implements Comparable<RaceDto> {
	public String id;
	public String nombre;
	public String tipo;
	public int distancia;
	public int precioInscripcion;
	public Date fechaLimite;
	public Date fechaCarrera;
	
	@Override
	public int compareTo(RaceDto o) {
		return this.fechaLimite.compareTo(o.fechaLimite);
	}	
	
	
}
