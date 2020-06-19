package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import exceptions.*;
import usuarios.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class OperacionDeEgreso {
	private DocumentoComercial documentoComercial;
	private LocalDateTime fechaOperacion;
	private MedioDePago medio;
	private List<Item> items = new ArrayList<>();
	private Organizacion organizacion;
	private Proveedor proveedor;
	private List<Presupuesto> presupuestos = new ArrayList<>();
	private List<Usuario> revisores = new ArrayList<>();
	public final int presupuestosMinimos=1;
	
	public void setDocumentoComercial(DocumentoComercial codDocumentoComercial) {
		this.documentoComercial = codDocumentoComercial;
	}

	//*******USADO PARA TESTING*******//
	public OperacionDeEgreso(List<Item> items) {
		this.items=items;
	}
	
	public OperacionDeEgreso(DocumentoComercial documentoComercial, LocalDateTime fechaOperacion, MedioDePago medio,
			List<Item> items, Organizacion organizacion, Proveedor proveedor, List<Presupuesto> presupuestos,
			List<Usuario> revisores) {
		
		this.documentoComercial = documentoComercial;
		this.fechaOperacion = fechaOperacion;
		this.medio = medio;
		this.items = items;
		this.organizacion = organizacion;
		this.proveedor = proveedor;
		this.revisores = revisores;
	}
	
	public void agregarNuevoPresupuesto(Presupuesto presupuesto) {
		presupuestos.add(presupuesto);
	}

	public BigDecimal valorTotal() {
		return this.items.stream().map(i -> i.getValor()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}
	
	public BigDecimal menorPrecioDePresupuestos() {
		return presupuestos.stream().map(presupuesto -> presupuesto.valorTotal()).min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
	}
	
	/*
	public void validarConsistenciaConPresupuesto() {
		if(!presupuestos.stream().anyMatch(presupuesto -> presupuesto.getItems().containsAll(items)))
			throw new operacionInconsistenteConPresupuestosException();
	}
	public void validarCantidadDePresupuestos() {
		if(presupuestos.size()<presupuestosMinimos)
			throw new presupuestosInsuficientesException();
	}
	public void validarHaberElegidoElPresupuestoMasBarato() {
		if(!menorPrecioDePresupuestos().equals(this.valorTotal()))
			throw new presupuestoMasBaratoNoElegidoException();
	}*/
	
	public boolean estaBasadaEnAlgunPresupuesto() {
		return presupuestos.stream().anyMatch(presupuesto -> presupuesto.getItems().stream().allMatch(item -> items.stream().anyMatch(i -> i.equals(item))));
	}
	public boolean tieneLaSuficienteCantidadDePresupuestos() {
		return presupuestos.size()>=presupuestosMinimos;
	}
	public boolean seEligioElPresupuestoMasBarato() {
		return menorPrecioDePresupuestos().equals(this.valorTotal());
	}
	public void enviarMensajeARevisores(Mensaje mensaje) {
		revisores.forEach(revisor -> revisor.recibirMensaje(mensaje));
	}
	
	public boolean esValida() {
		return estaBasadaEnAlgunPresupuesto() && tieneLaSuficienteCantidadDePresupuestos() && seEligioElPresupuestoMasBarato();
	}
	
	public void altaDeUnRevisor(Usuario revisorNuevo) {
		revisores.add(revisorNuevo);
	}

}
