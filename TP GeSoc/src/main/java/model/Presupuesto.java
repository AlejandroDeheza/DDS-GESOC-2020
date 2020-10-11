package model;

import organizacion.Organizacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "presupuestos")
public class Presupuesto {
	@Id
	@GeneratedValue
	@Column(name = "id_presupuesto")
	Long id;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "presupesto_asociado")
	private List<Item> items = new ArrayList<>();
	
	/*@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "documento_comercial", referencedColumnName = "id_documento_comercial")*/
	@Embedded
	private DocumentoComercial documentoComercial;
	
	@ManyToOne
	@JoinColumn(name = "proveedor")
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
