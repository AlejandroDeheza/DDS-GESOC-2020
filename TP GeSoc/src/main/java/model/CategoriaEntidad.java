package model;

import java.util.ArrayList;
import java.util.List;

public class CategoriaEntidad {
	String texto;
	List<ValidacionEntidad> comportamientos = new ArrayList<>();
	
	
	CategoriaEntidad(List<ValidacionEntidad> comportamientos, String texto){
		this.comportamientos = comportamientos;
		this.texto = texto.toUpperCase();
	}
	void setComportamientos(List<ValidacionEntidad> comportamientos) {
		this.comportamientos=comportamientos;
	}

}
