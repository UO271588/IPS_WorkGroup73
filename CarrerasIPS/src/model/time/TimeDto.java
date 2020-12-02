package model.time;

public class TimeDto {
	private int dorsal;
	private String tiempoInicio;
	private String tiempoFinal;
	public TimeDto(int dorsal, String tiempoInicio, String tiempoFinal) {
		this.dorsal=dorsal;
		this.tiempoInicio=tiempoInicio;
		this.tiempoFinal=tiempoFinal;
	}
	public int getDorsal() {
		return dorsal;
	}
	public String getTiempoInicio() {
		return tiempoInicio;
	}
	public String getTiempoFinal() {
		return tiempoFinal;
	}


}
