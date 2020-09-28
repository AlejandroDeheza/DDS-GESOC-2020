package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import comportamientoEntidad.*;
import exceptions.*;
import organizacion.*;

@Entity
@Table(name = "categoria_entidades")
public class CategoriaEntidad {
	
	@Id
	@GeneratedValue
	@Column(name = "id_categoria_entidad")
	private Long id;
	
	@Column(name = "descripcion")
	public String descripcion;
	
	@ManyToMany(cascade =  {CascadeType.ALL})
	@JoinTable(
	        name = "comportamientos_aplicados", 
	        joinColumns = { @JoinColumn(name = "id_categoria") }, 
	        inverseJoinColumns = { @JoinColumn(name = "id_comportamiento") }
	    )
	List<Comportamiento> comportamientos = new ArrayList<>();
	
	public CategoriaEntidad() {}
	public CategoriaEntidad(List<Comportamiento> comportamientos, String texto){
		this.comportamientos = comportamientos;
		this.descripcion = texto.toUpperCase();
	}
	
	public void setComportamientos(List<Comportamiento> comportamientos) {
		this.comportamientos=comportamientos;
	}
	
	public void agregarNuevoEgreso(Entidad entidad, OperacionDeEgreso egreso) {
		System.out.println(comportamientos.size());
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
