package model;
import java.util.ArrayList;
import java.util.List;

public class EntidadJuridica {
	String razonSocial;
	String nombreFicticio;
	int cuit;
	String direccionPostal;
	int codInscIGJ; //Opcional
	List<EntidadBase> entidades = new ArrayList<>(); //Puede estar vacia
	CategoriaEntidadJuridica categoriaEntidad; //Opcionalmente se reemplazara por una intefaz
	
	public EntidadJuridica(String razonSocial, String nombreFicticio, int cuit, String direccionPostal, int codInscIGJ,
			List<EntidadBase> entidades, CategoriaEntidadJuridica categoriaEntidad) {

		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codInscIGJ = codInscIGJ;
		this.entidades = entidades;
		this.categoriaEntidad = categoriaEntidad;
	}
	
	
	
}

