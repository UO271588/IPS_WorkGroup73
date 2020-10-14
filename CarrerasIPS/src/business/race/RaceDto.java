package business.race;

import java.time.LocalDate;

public class RaceDto implements Comparable<RaceDto> {
	public String id;
	public String nombre;
	public String tipo;
	public int distancia;
	public int precioInscripcion;
	public LocalDate fechaLimite;
	public LocalDate fechaCarrera;
	
	@Override
	public int compareTo(RaceDto o) {
		return this.fechaLimite.compareTo(o.fechaLimite);
	}	
	
	
}
