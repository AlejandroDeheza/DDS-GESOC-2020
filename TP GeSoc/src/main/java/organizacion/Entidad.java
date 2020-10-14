package organizacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import model.*;

@Entity
@Table(name = "entidades")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entidad {
	@Id
	@GeneratedValue
	@Column(name = "id_entidad")
	public Long id;
	
	@Column(name = "nombre_ficticio")
	public String nombreFicticio;
	
	@OneToMany
	@JoinColumn(name = "entidad")
	public List<OperacionDeEgreso> egresos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "categoria_entidad", referencedColumnName = "id_categoria_entidad")
	public CategoriaEntidad categoriaEntidad;
	
	public void agregarOperacionDeEgreso(OperacionDeEgreso egreso) {
		categoriaEntidad.agregarNuevoEgreso(this, egreso);
	}
	
	public List<OperacionDeEgreso> egresosConEtiqueta(EtiquetaOperacion etiqueta) {
		return egresos.stream().filter(egreso -> egreso.getEtiquetas().stream().anyMatch(eti -> eti.texto.equals(etiqueta.texto))).collect(Collectors.toList());
	}
	
	/*
	public BigDecimal gastosDeEtiqueta(EtiquetaOperacion etiqueta) {
		return this.egresosConEtiqueta(etiqueta).stream().map(egreso -> egreso.valorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}
	
	public BigDecimal gastosTotales() {
		return this.egresos.stream().map(egreso -> egreso.valorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}*/
	
	public void setCategoriaEntidad(CategoriaEntidad categoriaEntidad) {
		this.categoriaEntidad=categoriaEntidad;
	}
	
	
}
