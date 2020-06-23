package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import exceptions.*;
import medioDePago.MedioDePago;
import organizacion.Organizacion;
import usuarios.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class OperacionDeEgreso {
	
	private List<Item> items = new ArrayList<>();
	private DocumentoComercial documentoComercial;
	private LocalDateTime fechaOperacion;
	private MedioDePago medio;
	private Organizacion organizacion;
	private Proveedor proveedor;
	private List<Presupuesto> presupuestos = new ArrayList<>();
	private List<Usuario> revisores = new ArrayList<>();
	private final int presupuestosMinimos = 1;
	private static int CantidadInstancias = 0;
	private int IDOperacionDeEgreso;

	public OperacionDeEgreso(List<Item> items, DocumentoComercial documentoComercial, LocalDateTime fechaOperacion, 
			MedioDePago medio, Organizacion organizacion, Proveedor proveedor, List<Presupuesto> presupuestos,
			List<Usuario> revisores) {
		
		this.items = items;
		this.documentoComercial = documentoComercial;
		this.fechaOperacion = fechaOperacion;
		this.medio = medio;
		this.organizacion = organizacion;
		this.proveedor = proveedor;
		this.revisores = revisores;
		CantidadInstancias++;
		this.IDOperacionDeEgreso = CantidadInstancias;
	}
	
	public void setDocumentoComercial(DocumentoComercial codDocumentoComercial) {
		this.documentoComercial = codDocumentoComercial;
	}
	
	public void agregarNuevoPresupuesto(List<Item> items, DocumentoComercial documento, Proveedor proveedorEmisor) {

		if(!this.contieneTodosLos(items)) {
			throw new PresupuestoInvalidoException();
		}
		Presupuesto presupuesto = new Presupuesto(items, documento, this.organizacion, proveedorEmisor);
		this.presupuestos.add(presupuesto);
	}//Así siempre se agregan presupuestos validos. Deberia ser la unica forma de agregar presupuestos.
	//Así respetamos el punto 2 de la entrega 2.
	
	public void altaDeUnRevisor(Usuario revisorNuevo) {
		this.revisores.add(revisorNuevo);
	}

	public BigDecimal valorTotal() {
		return this.items.stream().map(i -> i.getValor()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}
	
	private boolean contieneTodosLos(List<Item> items) {
		return items.stream().allMatch(item -> this.contiene(item));
	}
	
	
	
	

	public boolean tieneLaSuficienteCantidadDePresupuestos() {
		return this.presupuestos.size() >= this.presupuestosMinimos;
	}

	
	
	
	
	private boolean contiene(Item item) {
		return this.items.stream().anyMatch(i -> i.equals(item));
	}
	
	private boolean contieneTodosLosItemsDe(Presupuesto presupuesto) {
		return presupuesto.getItems().stream().allMatch(item -> this.contiene(item));
	}
	
	public boolean estaBasadaEnAlgunPresupuesto() {
		return this.presupuestos.stream().anyMatch(presupuesto -> this.contieneTodosLosItemsDe(presupuesto));
	}
	
	
	

	
	private BigDecimal menorPrecioDePresupuestos() {
		return this.presupuestos.stream().map(presupuesto -> presupuesto.valorTotal()).min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
	}
	
	public boolean seEligioElPresupuestoMasBarato() {
		return this.menorPrecioDePresupuestos().equals(this.valorTotal());
	}

	
	
	
	
	public boolean esValida() {
		return this.estaBasadaEnAlgunPresupuesto() && 
			   this.tieneLaSuficienteCantidadDePresupuestos() && 
			   this.seEligioElPresupuestoMasBarato();
	}
	
	
	
	
	
	public void notificarRevisores(String mensaje) {
		this.revisores.forEach(revisor -> revisor.recibirMensaje(new Mensaje(mensaje + ", " + this.IDOperacionDeEgreso))); 
	}
}
