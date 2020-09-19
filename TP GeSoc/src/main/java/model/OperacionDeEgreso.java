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
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "Operaciones_De_Egreso")
public class OperacionDeEgreso {
	
	@Id
	@GeneratedValue
	private Long id;
	
//	@OneToOne
//	private DocumentoComercial documentoComercial;
	
	@Transient
	private MedioDePago medio;
	//No sabemos q hacer con esto
	
	@OneToOne
	private Presupuesto presupuestoElegido;


	private LocalDateTime fechaOperacion;
	
	@OneToMany
	@JoinColumn(name = "operacion_asociada")
	public List<Presupuesto> presupuestos = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
	        name = "Revisor", 
	        joinColumns = { @JoinColumn(name = "id_operacion_de_egreso") }, 
	        inverseJoinColumns = { @JoinColumn(name = "id_usuario") }
	    )
	private List<Usuario> revisores = new ArrayList<>();
	
	@Transient
	private List<ValidadorDeOperaciones> validacionesVigentes = new ArrayList<>();
	
	@ElementCollection
	public List<EtiquetaOperacion> etiquetas = new ArrayList<>();
	
	public final int presupuestosMinimos = 1;
		
	public Presupuesto getPresupuestoElegido() {
		return presupuestoElegido;
	}
	
	public void setPresupuestoElegido(Presupuesto presupuestoElegido) {
		this.presupuestoElegido = presupuestoElegido;
	}
	
	public OperacionDeEgreso() {

		
		validacionesVigentes.add(new ValidarQueLaOperacionContengaTodosLosItems());
		validacionesVigentes.add(new ValidarQueSeHayaElegidoElPresupuestoMasBarato());
		validacionesVigentes.add(new ValidarQueTengaLaSuficienteCantidadDePresupuestos());
	}
	
	public OperacionDeEgreso(LocalDateTime fechaOperacion, MedioDePago medio, List<Presupuesto> presupuestos,
			List<Usuario> revisores) {

		this.fechaOperacion = fechaOperacion;
		this.medio = medio;

		this.revisores = revisores;

		
		validacionesVigentes.add(new ValidarQueLaOperacionContengaTodosLosItems());
		validacionesVigentes.add(new ValidarQueSeHayaElegidoElPresupuestoMasBarato());
		validacionesVigentes.add(new ValidarQueTengaLaSuficienteCantidadDePresupuestos());
	}

	public void agregarEtiqueta(EtiquetaOperacion etiqueta) {
		etiquetas.add(etiqueta);
	}
	
	public void agregarNuevoPresupuesto(List<Item> items, DocumentoComercial documento, Proveedor proveedorEmisor) {
//
//		if(!this.contieneTodosLos(items)) {
//			throw new PresupuestoInvalidoException();
//		}
		
		Presupuesto presupuesto = new Presupuesto(items, documento, proveedorEmisor);
		this.presupuestos.add(presupuesto);
	}
	//Así siempre se agregan presupuestos validos. Deberia ser la unica forma de agregar presupuestos.
	//Así respetamos el punto 2 de la entrega 2.
	
	public void altaDeUnRevisor(Usuario revisorNuevo) {
		this.revisores.add(revisorNuevo);
	}

	public BigDecimal valorTotal() {
		return this.presupuestoElegido.valorTotal();
//		return this.items.stream().map(item -> item.getMoneda().monto).reduce(BigDecimal.ZERO,BigDecimal::add);

	}
	
//	private boolean contieneTodosLos(List<Item> items) {
//
//		return items.stream().allMatch(item -> this.contiene(item));
//	}
	
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
		this.revisores.forEach(revisor -> revisor.recibirMensaje(new Mensaje(mensaje + ", " + this.id))); 
	}
}
