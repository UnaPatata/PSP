package src;

import java.io.Serializable;

public class Persona implements Serializable{

	private String nombre,materia; 
	private int calificacion;

	public Persona(String nombre, String materia, int calificacion) {
		this.nombre = nombre;
		this.materia = materia;
		this.calificacion = calificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
}
