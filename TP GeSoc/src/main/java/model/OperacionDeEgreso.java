package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import exceptions.*;
import medioDePago.MedioDePago;
import organizacion.Organizacion;
import repositorios.RepositorioCompras;
import usuarios.*;
import validacionesOperaciones.*;

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
	public List<Presupuesto> presupuestos = new ArrayList<>();
	private List<Usuario> revisores = new ArrayList<>();
	private List<ValidadorDeOperaciones> validacionesVigentes = new ArrayList<>();
	public List<EtiquetaOperacion> etiquetas = new ArrayList<>();
	public final int presupuestosMinimos = 1;
	private static int CantidadInstancias = 0;
	public int IDOperacionDeEgreso;

	
	public OperacionDeEgreso(List<Item> items) {
		this.items = items;
		
		validacionesVigentes.add(new ValidarQueLaOperacionContengaTodosLosItems());
		validacionesVigentes.add(new ValidarQueSeHayaElegidoElPresupuestoMasBarato());
		validacionesVigentes.add(new ValidarQueTengaLaSuficienteCantidadDePresupuestos());
	}
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
		
		validacionesVigentes.add(new ValidarQueLaOperacionContengaTodosLosItems());
		validacionesVigentes.add(new ValidarQueSeHayaElegidoElPresupuestoMasBarato());
		validacionesVigentes.add(new ValidarQueTengaLaSuficienteCantidadDePresupuestos());
	}
	
	public void setDocumentoComercial(DocumentoComercial codDocumentoComercial) {
		this.documentoComercial = codDocumentoComercial;
	}
	
	public void agregarEtiqueta(EtiquetaOperacion etiqueta) {
		etiquetas.add(etiqueta);
	}
	
	public boolean contiene(Item item) {
		return this.items.stream().anyMatch(i -> i.equals(item));
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
	
	public boolean esValida() {
		return validacionesVigentes.stream().allMatch(validacion -> validacion.pasoCorrectamente(this));
	}
	
	public void validarCompra() {
		if(this.esValida()) {
			RepositorioCompras.instance().comprasValidadas.add(this);
			notificarRevisores("La operacion fue validada correctamente");
		}
		else {
			notificarRevisores("La operación no es valida");
		}
	}
	
	
	public void notificarRevisores(String mensaje) {
		this.revisores.forEach(revisor -> revisor.recibirMensaje(new Mensaje(mensaje + ", " + this.IDOperacionDeEgreso))); 
	}
}
