package organizacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizaciones")
public class Organizacion {
	
	@Id
	@GeneratedValue
	@Column(name = "id_organizacion")
	private Long id;
	
	
	@OneToMany(cascade = {CascadeType.PERSIST})
	@JoinColumn(name = "entidad_organizacion")
	private List<Entidad> entidades = new ArrayList<>();
	
	private String nombre;
	private String descripcion;

	public Organizacion() {}

	public String getNombre(){
		return nombre;
	}
	public String getDescripcion() {return descripcion;}
	public Long getId(){return id;}
	public void agregarEntidad(Entidad entidad){
		entidades.add(entidad);
	}

	/*private List<EntidadJuridica> entidadesJuridicas = new ArrayList<>();
	private List<EntidadBase> entidadesBase = new ArrayList<>();*/


	public Organizacion(String nombre, String descripcion, List<Entidad> entidades) {
		this.entidades = entidades;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Organizacion(String descripcion, List<EntidadJuridica> entidadesJuridicas, List<EntidadBase> entidadesBase) {
		if(!entidadesJuridicas.isEmpty())
			this.entidades.addAll(entidadesJuridicas);
		if(entidadesBase != null && !entidadesBase.isEmpty())
			this.entidades.addAll(entidadesBase);
		this.nombre = descripcion;
	}

	/*public List<EntidadJuridica> getEntidadesJuridicas(){ return this.entidadesJuridicas; }

	public List<EntidadBase> getEntidadesBase(){ return this.entidadesBase;	}

	public void setEntidadesBase(List<EntidadBase> entidadesBase) {
		this.entidadesBase = entidadesBase;
	}

	public void setEntidadesJuridicas(List<EntidadJuridica> entidadesJuridicas) { this.entidadesJuridicas = entidadesJuridicas; }*/
}

