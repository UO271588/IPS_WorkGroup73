package business.race;

import java.time.LocalDate;

public class RaceDto implements Comparable<RaceDto> {
	public String nombre;
	public String tipo;
	public double distancia;
	public double precioInscripcion;
	public LocalDate fechaLimite;
	public LocalDate fechaCarrera;
	
	@Override
	public int compareTo(RaceDto o) {
		return this.fechaLimite.compareTo(o.fechaLimite);
	}	
	
	
}
