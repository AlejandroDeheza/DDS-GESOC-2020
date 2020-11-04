package organizacion;
import repositorios.RepositorioEntidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "organizaciones")
public class Organizacion {
	
	@Id
	@GeneratedValue
	@Column(name = "id_organizacion")
	private Long id;
	
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "entidad_organizacion")
	private List<Entidad> entidades = new ArrayList<>();
	
	private String descripcion;

	public Organizacion() {}

	public String getDescripcion(){
		return descripcion;
	}
	public Long getId(){return id;}
	public void agregarEntidad(Entidad entidad){
		entidades.add(entidad);
	}

	/*private List<EntidadJuridica> entidadesJuridicas = new ArrayList<>();
	private List<EntidadBase> entidadesBase = new ArrayList<>();*/
	
	
	public Organizacion(String descripcion,List<EntidadJuridica> entidadesJuridicas, List<EntidadBase> entidadesBase) {
		if(!entidadesJuridicas.isEmpty())
			this.entidades.addAll(entidadesJuridicas);
		if(entidadesBase != null && !entidadesBase.isEmpty())
			this.entidades.addAll(entidadesBase);
		this.descripcion = descripcion;
	}

	/*public List<EntidadJuridica> getEntidadesJuridicas(){ return this.entidadesJuridicas; }

	public List<EntidadBase> getEntidadesBase(){ return this.entidadesBase;	}

	public void setEntidadesBase(List<EntidadBase> entidadesBase) {
		this.entidadesBase = entidadesBase;
	}

	public void setEntidadesJuridicas(List<EntidadJuridica> entidadesJuridicas) { this.entidadesJuridicas = entidadesJuridicas; }*/
}

