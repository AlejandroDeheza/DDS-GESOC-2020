package organizacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import model.CategoriaEntidad;
import ubicacion.DireccionPostal;


@Entity
@Table(name = "entidades_juridicas")
public class EntidadJuridica extends Entidad {
	
	@Column(name = "razon_social")
	private String razonSocial;
	
	private int cuit;
	
	@Embedded
	private DireccionPostal direccionPostal;
	
	@Column(name = "cod_inscripcion_IGJ")
	private int codInscIGJ; //Opcional
	
	@Enumerated(EnumType.STRING)
	@Column(name = "categoria_juridica")
	private CategoriaEntidadJuridica categoriaEntidadJuridica; //Opcionalmente se reemplazara por una intefaz
//	public boolean tieneAsociadasEntidadesBase = false;
	
	public EntidadJuridica() {
		
	}

	public EntidadJuridica (String nombreFicticio, CategoriaEntidad categoriaEntidad, String razonSocial, int cuit, DireccionPostal direccionPostal, int codInscIGJ,
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

