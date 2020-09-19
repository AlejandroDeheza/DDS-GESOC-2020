package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import comportamientoEntidad.*;
import exceptions.*;
import organizacion.*;

@Entity
@Table(name = "Categoria_entidades")
public class CategoriaEntidad {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public String texto;
	
	@ManyToMany
	@JoinTable(
	        name = "Comportamiento_aplicado", 
	        joinColumns = { @JoinColumn(name = "id_categoria") }, 
	        inverseJoinColumns = { @JoinColumn(name = "id_comportamiento") }
	    )
	List<Comportamiento> comportamientos = new ArrayList<>();
	
	public CategoriaEntidad(List<Comportamiento> comportamientos, String texto){
		this.comportamientos = comportamientos;
		this.texto = texto.toUpperCase();
	}
	
	public void setComportamientos(List<Comportamiento> comportamientos) {
		this.comportamientos=comportamientos;
	}
	
	public void agregarNuevoEgreso(Entidad entidad, OperacionDeEgreso egreso) {
		comportamientos.forEach(c -> c.alAgregarEgreso(entidad, egreso));
		
		entidad.egresos.add(egreso);
	}
	
	public void asociarAEntidadJuridica(EntidadBase entidadBase, EntidadJuridica entidadJuridica) {
		comportamientos.forEach(c -> c.alAgregarEntidad(entidadBase,entidadJuridica));
		
		entidadBase.entidadJuridica = entidadJuridica;
	}
	
	public void asociarNuevaEntidadBase(EntidadBase entidadBase, EntidadJuridica entidadJuridica){
		comportamientos.forEach(c -> c.alAgregarEntidad(entidadBase,entidadJuridica));
		
		entidadBase.asociarAEntidadJuridica(entidadJuridica);
	}
	
}
