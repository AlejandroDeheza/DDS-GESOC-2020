package organizacion;

import model.EtiquetaOperacion;
import model.OperacionDeEgreso;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "entidades")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entidad {
	@Id
	@GeneratedValue
	@Column(name = "id_entidad")
	public Long id;

	/*
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "entidad_organizacion", referencedColumnName = "id_organizacion")
	public Organizacion organizacion;*/

	@Column(name = "nombre_ficticio")
	public String nombreFicticio;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinColumn(name = "entidad")
	public List<OperacionDeEgreso> egresos = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY, cascade =  {CascadeType.ALL})
	@JoinColumn(name = "categoria_entidad", referencedColumnName = "id_categoria_entidad")
	public CategoriaEntidad categoriaEntidad;
	
	public void agregarOperacionDeEgreso(OperacionDeEgreso egreso) {
		categoriaEntidad.agregarNuevoEgreso(this, egreso);
	}
	
	public List<OperacionDeEgreso> egresosConEtiqueta(EtiquetaOperacion etiqueta) {
		return egresos.stream().filter(egreso -> egreso.getEtiquetas().stream().anyMatch(eti -> eti.texto.equals(etiqueta.texto))).collect(Collectors.toList());
	}
	
	public BigDecimal gastosDeEtiqueta(EtiquetaOperacion etiqueta) {
		return this.egresosConEtiqueta(etiqueta).stream().map(egreso -> egreso.valorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}
	
	public BigDecimal gastosTotales() {
		return this.egresos.stream().map(egreso -> egreso.valorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}
	
	public void setCategoriaEntidad(CategoriaEntidad categoriaEntidad) {
		this.categoriaEntidad=categoriaEntidad;
	}

	public String getNombreFicticio(){
		return nombreFicticio;
	}

	public Long getId(){
		return id;
	}
	public CategoriaEntidad getCategoriaEntidad(){
		return categoriaEntidad;
	}

	public void setNombreFicticio(String nombreFicticio) {
		this.nombreFicticio = nombreFicticio;
	}

	/*public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}*/

	public void setEgresos(List<OperacionDeEgreso> egresos) {
		this.egresos = egresos;
	}
}
