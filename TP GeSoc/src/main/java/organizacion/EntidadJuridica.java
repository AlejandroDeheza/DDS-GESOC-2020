package organizacion;

import java.util.ArrayList;
import java.util.List;

import model.CategoriaEntidad;
import model.OperacionDeEgreso;
import validacionesOperaciones.ValidadorDeOperaciones;

public class EntidadJuridica {
	private String razonSocial;
	private String nombreFicticio;
	private int cuit;
	private String direccionPostal;
	private int codInscIGJ; //Opcional
	private List<OperacionDeEgreso> egresos = new ArrayList<>();
	private CategoriaEntidadJuridica categoriaEntidadJuridica; //Opcionalmente se reemplazara por una intefaz
	public CategoriaEntidad categoriaEntidad;
	
	/*
	public EntidadJuridica(String razonSocial, String nombreFicticio, int cuit, String direccionPostal, int codInscIGJ,
			 CategoriaEntidadJuridica categoriaEntidadJuridica, CategoriaEntidad categoriaEntidad) {

		this.razonSocial = razonSocial;
		this.nombreFicticio = nombreFicticio;
		this.cuit = cuit;
		this.direccionPostal = direccionPostal;
		this.codInscIGJ = codInscIGJ;
		this.categoriaEntidadJuridica = categoriaEntidadJuridica;
		this.categoriaEntidad = categoriaEntidad;
	}*/
	
	
	
}

