package organizacion;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Organizaciones")
public class Organizacion {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	@OneToMany
	@JoinColumn(name = "id_organizacion")
	private List<Entidad> entidades = new ArrayList<>();
	
	private String descripcion;
	public Organizacion() {}
	/*
	private List<EntidadJuridica> entidadesJuridicas = new ArrayList<>();
	private List<EntidadBase> entidadesBase = new ArrayList<>();
	
	
	public Organizacion(List<EntidadJuridica> entidadesJuridicas, List<EntidadBase> entidadesBase) {
		this.entidadesJuridicas = entidadesJuridicas;
		this.entidadesBase = entidadesBase;
	}

	public List<EntidadJuridica> getEntidadesJuridicas(){
		return this.entidadesJuridicas;
	}

	public void setEntidadesJuridicas(List<EntidadJuridica> entidadesJuridicas) {
		this.entidadesJuridicas = entidadesJuridicas;
	}

	public List<EntidadBase> getEntidadesBase() {
		return this.entidadesBase;
	}

	public void setEntidadesBase(List<EntidadBase> entidadesBase) {
		this.entidadesBase = entidadesBase;
	}*/
}
