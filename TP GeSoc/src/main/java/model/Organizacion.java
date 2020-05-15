package model;
import java.util.ArrayList;
import java.util.List;

public class Organizacion {
	List<EntidadJuridica> entidadesJuridicas = new ArrayList<>();
	List<EntidadBase> entidadesBase = new ArrayList<>();
	
	public Organizacion(List<EntidadJuridica> entidadesJuridicas, List<EntidadBase> entidadesBase) {
		this.entidadesJuridicas = entidadesJuridicas;
		this.entidadesBase = entidadesBase;
	}

	public List<EntidadJuridica> getEntidadesJuridicas(){
		return entidadesJuridicas;
	}

	public void setEntidadesJuridicas(List<EntidadJuridica> entidadesJuridicas) {
		this.entidadesJuridicas = entidadesJuridicas;
	}

	public List<EntidadBase> getEntidadesBase() {
		return entidadesBase;
	}

	public void setEntidadesBase(List<EntidadBase> entidadesBase) {
		this.entidadesBase = entidadesBase;
	}

}
