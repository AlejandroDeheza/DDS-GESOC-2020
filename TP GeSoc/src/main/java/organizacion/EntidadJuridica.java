package organizacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import model.CategoriaEntidad;


@Entity
@Table(name = "Entidades_juridicas")
public class EntidadJuridica extends Entidad {
	
	private String razonSocial;
	
	private int cuit;
	
	private String direccionPostal;
	
	private int codInscIGJ; //Opcional
	
	@Enumerated(EnumType.STRING)
	private CategoriaEntidadJuridica categoriaEntidadJuridica; //Opcionalmente se reemplazara por una intefaz
//	public boolean tieneAsociadasEntidadesBase = false;
	
	public EntidadJuridica() {
		
	}

	public EntidadJuridica (String nombreFicticio, CategoriaEntidad categoriaEntidad, String razonSocial, int cuit, String direccionPostal, int codInscIGJ,
			CategoriaEntidadJuridica categoriaEntidadJuridica) {
		
		this.nombreFicticio=nombreFicticio;
		this.categoriaEntidad=categoriaEntidad;
		this.razonSocial=razonSocial;
		this.cuit=cuit;
		this.direccionPostal=direccionPostal;
		this.codInscIGJ=codInscIGJ;
		this.categoriaEntidadJuridica=categoriaEntidadJuridica;
	}


	public void asociarEntidadBase(EntidadBase entidadB){
		this.categoriaEntidad.asociarNuevaEntidadBase(entidadB, this);
	}
	
	
}

