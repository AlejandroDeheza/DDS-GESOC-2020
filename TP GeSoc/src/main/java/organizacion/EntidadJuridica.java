package organizacion;

import javax.persistence.*;

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

	public String getRazonSocial(){
		return razonSocial;
	}

	public int getCuit() {
		return cuit;
	}

	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}

	public int getCodInscIGJ() {
		return codInscIGJ;
	}

	public CategoriaEntidadJuridica getCategoriaEntidadJuridica() {
		return categoriaEntidadJuridica;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public void setCuit(int cuit) {
		this.cuit = cuit;
	}

	public void setDireccionPostal(DireccionPostal direccionPostal) {
		this.direccionPostal = direccionPostal;
	}

	public void setCodInscIGJ(int codInscIGJ) {
		this.codInscIGJ = codInscIGJ;
	}

	public void setCategoriaEntidadJuridica(CategoriaEntidadJuridica categoriaEntidadJuridica) {
		this.categoriaEntidadJuridica = categoriaEntidadJuridica;
	}
}

