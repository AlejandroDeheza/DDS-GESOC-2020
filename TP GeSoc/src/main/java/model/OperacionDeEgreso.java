package model;

import java.time.LocalDateTime;
import exceptions.*;
import usuarios.*;

import java.util.ArrayList;
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
	public final int presupuestosMinimos=2;
	
	public void setCodDocumentoComercial(DocumentoComercial codDocumentoComercial) {
		this.documentoComercial = codDocumentoComercial;
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

	public Double valorTotal() {
		return this.items.stream().mapToDouble(a -> a.getValor()).sum();
    }
	
	public OptionalDouble menorPrecioDePresupuestos() {
		return presupuestos.stream().mapToDouble(presupuesto -> presupuesto.valorTotal()).min();
	}
	public void validarConsistenciaConPresupuesto() {
		if(!presupuestos.stream().anyMatch(presupuesto -> presupuesto.getItems()==items))
			throw new operacionInconsistenteConPresupuestosException();
	}
	private void validarCantidadDePresupuestos() {
		if(presupuestos.size()<presupuestosMinimos)
			throw new presupuestosInsuficientesException();
	}
	private void validarHaberElegidoElPresupuestoMasBarato() {
		if(!menorPrecioDePresupuestos().equals(this.valorTotal()))
			throw new presupuestoMasBaratoNoElegidoException();
	}
	
	public void enviarMensajeARevisores(Mensaje mensaje) {
		revisores.forEach(revisor -> revisor.recibirMensaje(mensaje));
	}
	//Usamos excepciones para eventualmente agregar comportamiento
	public boolean esValida() {
		try {
		validarCantidadDePresupuestos();
		validarConsistenciaConPresupuesto();
		validarHaberElegidoElPresupuestoMasBarato();
		return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
