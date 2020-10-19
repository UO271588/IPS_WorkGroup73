package util;

import java.util.Date;

public class Justificante {

	private String nombre;
	private String nombreCompeticion;
	private String categoria;
	private Date fecha_inscripcion;
	private int cantidad;
	private String estado;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getnombreCompeticion() {
		return nombreCompeticion;
	}
	public void setnombreCompeticion(String nombreCompeticion) {
		this.nombreCompeticion = nombreCompeticion;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Date getFecha_inscripcion() {
		return fecha_inscripcion;
	}
	public void setFecha_inscripcion(Date fecha_inscripcion) {
		this.fecha_inscripcion = fecha_inscripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
}
