package model;

public class EntidadJuridica {
	String razonSocial;
	String nombreFicticio;
	int cuit;
	String direccionPostal;
	int codInscIGJ; //Opcional
	CategoriaEntidadJuridica categoriaEntidad; //Opcionalmente se reemplazara por una intefaz
	
	public EntidadJuridica(String razonSocial, String nombreFicticio, int cuit, String direccionPostal, int codInscIGJ,
			 CategoriaEntidadJuridica categoriaEntidad) {

		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codInscIGJ = codInscIGJ;
		this.categoriaEntidad = categoriaEntidad;
	}
	
	
	
}

