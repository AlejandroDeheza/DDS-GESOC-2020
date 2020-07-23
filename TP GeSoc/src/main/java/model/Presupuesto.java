package model;

import organizacion.Organizacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Presupuesto {
	
	private List<Item> items = new ArrayList<>();
	private DocumentoComercial documentoComercial;
	private Organizacion organizacion;
	private Proveedor proveedor;
	
	public Presupuesto(List<Item> items, DocumentoComercial documento, Organizacion organizacionReceptora, Proveedor proveedorEmisor) {
		this.items = items;
		this.documentoComercial = documento;
		this.organizacion = organizacionReceptora;
		this.proveedor = proveedorEmisor;
	}// en el sistema real, este constructor solo deberia ser usado en el metodo agregarNuevoPresupuesto(...) de clase OperacionDeEgreso.
	//Así respetamos el punto 2 de la entrega 2.
	
	public BigDecimal valorTotal() {
		return this.items.stream().map(i -> i.getValor()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}

	public List<Item> getItems() {
		return this.items;
	}
}
