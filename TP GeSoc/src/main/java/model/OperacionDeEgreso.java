package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import exceptions.*;
import organizacion.Organizacion;
import paymentMethods.IDMedioDePago;
import repositorios.RepositorioCompras;
import usuarios.*;
import validacionesOperaciones.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "operaciones_de_egreso")
public class OperacionDeEgreso {
	
	
	//Atributos y persistencia
	//---------------------------------------------------------------------------------------------------
	@Id
	@GeneratedValue
	@Column(name = "id_operacion")
	private Long id;

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "operacion_asociada")
	private List<Item> items = new ArrayList<>();

	/*@OneToOne(cascade = {CascadeType.ALL})
        @JoinColumn(name = "documento_comercial", referencedColumnName = "id_documento_comercial")*/
	@Embedded
	private DocumentoComercial documentoComercial;

	@Column(name = "fecha_operacion")
	private LocalDate fechaOperacion;

	@Enumerated(EnumType.STRING)
	@Column(name = "medio_de_pago")
	private IDMedioDePago medio;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "proveedor")
	private Proveedor proveedor;

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "operacion_asociada")
	private List<Presupuesto> presupuestos = new ArrayList<>();

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "presupuesto_elegido")
	private Presupuesto presupuestoElegido;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(name = "revisor_operacion",
			joinColumns=
            @JoinColumn(name="id_operacion", referencedColumnName="id_operacion"),
            inverseJoinColumns=
            @JoinColumn(name="id_revisor", referencedColumnName="id_usuario")
    )
	private List<Usuario> revisores = new ArrayList<>();

	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "operacion_asociada")
	private List<ValidacionDeOperaciones> validacionesVigentes = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "etiquetas_operaciones",
					 joinColumns=@JoinColumn(name = "id_operacion"))
	private List<EtiquetaOperacion> etiquetas = new ArrayList<>();

	@Column(name = "presupuestos_minimos")
	//Nico | wtf
	private int presupuestosMinimos;

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_operacion")
	private EstadoOperacion estado = EstadoOperacion.PENDIENTE;
	//---------------------------------------------------------------------------------------------------

	//Responsabilidades de la clase
	//---------------------------------------------------------------------------------------------------

	public BigDecimal valorTotal() {
		return this.items.stream().map(item -> item.getMoneda().monto).reduce(BigDecimal.ZERO,BigDecimal::add);
	}

	public boolean contieneItem(Item item) {
		return this.items.stream().anyMatch(i -> i.equals(item));
	}

	private boolean contieneItems(List<Item> items) {
		return items.stream().allMatch(item -> this.contieneItem(item));
	}

	public boolean contieneItemsDelPresupuesto(Presupuesto presupuesto) {
		//return presupuesto.getItems().stream().allMatch(item -> this.contiene(item));
		return contieneItems(presupuesto.getItems());
	}

	public BigDecimal menorPrecioDePresupuestos() {
		return this.presupuestos.stream().map(presupuesto -> presupuesto.valorTotal()).min(Comparator.naturalOrder()).orElse(BigDecimal.ZERO);
	}

	public BigDecimal valorTotalDelPresupuestoElegido() {
		return presupuestoElegido.valorTotal();
	}

	public int cantidadDePresupuestos() {
		return this.presupuestos.size();
	}

	public boolean esValida() {
		return validacionesVigentes.stream().allMatch(validacion -> validacion.operacionValida(this));
	}

	public void notificarRevisores(String asunto, String mensaje) {
		this.revisores.forEach(revisor -> revisor.recibirMensaje(new Mensaje(asunto,mensaje + ", " + this.id,LocalDate.now())));
	}

	//---------------------------------------------------------------------------------------------------

	//Constructores, getters y setters
	//---------------------------------------------------------------------------------------------------

	public OperacionDeEgreso() {}

	public OperacionDeEgreso(List<Item> items,
							 DocumentoComercial documentoComercial,
							 LocalDate fechaOperacion,
							 IDMedioDePago medio,
							 Proveedor proveedor,
							 List<Presupuesto> presupuestos,
							 Presupuesto presupuestoElegido,
							 List<Usuario> revisores,
							 List<ValidacionDeOperaciones> validacionesVigentes,
							 List<EtiquetaOperacion> etiquetas,
							 int presupuestosMinimos,
							 EstadoOperacion estado) {

		this.items = items;
		this.documentoComercial = documentoComercial;
		this.fechaOperacion = fechaOperacion;
		this.medio = medio;
		this.proveedor = proveedor;
		this.presupuestos = presupuestos;
		this.presupuestoElegido = presupuestoElegido;
		this.revisores = revisores;
		this.validacionesVigentes = validacionesVigentes;
		this.etiquetas = etiquetas;
		this.presupuestosMinimos = presupuestosMinimos;
		this.estado = estado;

	}

	public Long getId() {
		return id;
	}
	public String getEstado() {
		return estado.toString();
	}

	public List<EtiquetaOperacion> getEtiquetas(){
		return etiquetas;
	}

	public Presupuesto getPresupuestoElegido() {
		return presupuestoElegido;
	}

	public int getPresupuestosMinimos() {
		return presupuestosMinimos;
	}
	public void setEstado(EstadoOperacion estado) {
		this.estado = estado;
	}

	public Proveedor getProveedor(){
		return proveedor;
	}
	public void setDocumentoComercial(DocumentoComercial codDocumentoComercial) {
		this.documentoComercial = codDocumentoComercial;
	}

	public void agregarEtiqueta(EtiquetaOperacion etiqueta) {
		etiquetas.add(etiqueta);
	}

	public void agregarNuevoPresupuesto(Presupuesto presupuesto) {

		if(!this.contieneItems(presupuesto.getItems())) {
			throw new PresupuestoInvalidoException();
		}

		this.presupuestos.add(presupuesto);
	}
	//Así siempre se agregan presupuestos validos. Deberia ser la unica forma de agregar presupuestos.
	//Así respetamos el punto 2 de la entrega 2.

	public void setPresupuestoElegido(Presupuesto presupuesto) {
		this.presupuestoElegido = presupuesto;
	}

	public void agregarRevisor(Usuario revisorNuevo) {
		this.revisores.add(revisorNuevo);
	}

	public List<Item> getItems() {
		return items;
	}

	public DocumentoComercial getDocumentoComercial() {
		return documentoComercial;
	}

	public LocalDate getFechaOperacion() {
		return fechaOperacion;
	}

	public String getMedio() {
		return medio.toString();
	}

	public List<Presupuesto> getPresupuestos() {
		return presupuestos;
	}

	public List<Usuario> getRevisores() {
		return revisores;
	}

	public List<ValidacionDeOperaciones> getValidaciones() {
		return validacionesVigentes;
	}

	//---------------------------------------------------------------------------------------------------

}
