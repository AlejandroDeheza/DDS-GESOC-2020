package model;

import organizacion.Organizacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "Presupuestos")
public class Presupuesto {
	@Id
	@GeneratedValue
	Long id;
	
	@OneToMany
	@JoinColumn(name = "Presupesto_asociado")
	private List<Item> items = new ArrayList<>();
	@OneToOne
	private DocumentoComercial documentoComercial;
	@ManyToOne
	private Proveedor proveedor;
	
	public Presupuesto(List<Item> items, DocumentoComercial documento,  Proveedor proveedorEmisor) {
		this.items = items;
		this.documentoComercial = documento;
		this.proveedor = proveedorEmisor;
	}// en el sistema real, este constructor solo deberia ser usado en el metodo agregarNuevoPresupuesto(...) de clase OperacionDeEgreso.
	//Así respetamos el punto 2 de la entrega 2.
	
	public BigDecimal valorTotal() {
		return this.items.stream().map(i -> i.getMoneda().monto).reduce(BigDecimal.ZERO,BigDecimal::add);
	}

	public List<Item> getItems() {
		return this.items;
	}
	public Presupuesto() {}
}
