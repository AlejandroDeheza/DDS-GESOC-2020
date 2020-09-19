package organizacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import model.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entidad {
	@Id
	@GeneratedValue
	public Long id;
	
	public String nombreFicticio;
	
	@OneToMany
	@JoinColumn(name = "Entidad")
	public List<OperacionDeEgreso> egresos = new ArrayList<>();
	
	@ManyToOne
	public CategoriaEntidad categoriaEntidad;
	
	public void agregarOperacionDeEgreso(OperacionDeEgreso egreso) {
		categoriaEntidad.agregarNuevoEgreso(this, egreso);
	}
	
	public List<OperacionDeEgreso> egresosConEtiqueta(EtiquetaOperacion etiqueta) {
		return egresos.stream().filter(egreso -> egreso.etiquetas.stream().anyMatch(eti -> eti.texto.equals(etiqueta.texto))).collect(Collectors.toList());
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
}
