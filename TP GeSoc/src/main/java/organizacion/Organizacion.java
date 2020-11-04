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
	
	
	@OneToMany
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
	
	
	public Organizacion(List<EntidadJuridica> entidadesJuridicas, List<EntidadBase> entidadesBase) {
		this.entidades.addAll(entidadesJuridicas);
		this.entidades.addAll(entidadesBase);
	}

	/*public List<EntidadJuridica> getEntidadesJuridicas(){ return this.entidadesJuridicas; }

	public List<EntidadBase> getEntidadesBase(){ return this.entidadesBase;	}

	public void setEntidadesBase(List<EntidadBase> entidadesBase) {
		this.entidadesBase = entidadesBase;
	}

	public void setEntidadesJuridicas(List<EntidadJuridica> entidadesJuridicas) { this.entidadesJuridicas = entidadesJuridicas; }*/
}

