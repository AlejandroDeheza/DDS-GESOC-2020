package validacionesOperaciones;

import model.OperacionDeEgreso;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("presupuesto_mas_barato")
@DiscriminatorValue("PRES_MAS_BARATO")
public class ValidarQueSeHayaElegidoElPresupuestoMasBarato extends ValidacionDeOperaciones{
	
	public boolean operacionValida(OperacionDeEgreso operacion) {
		return (operacion.getPresupuestoElegido()!=null &&
				operacion.menorPrecioDePresupuestos().equals(operacion.valorTotalDelPresupuestoElegido()));
	}
	public ValidarQueSeHayaElegidoElPresupuestoMasBarato(){
		this.descripcion = "Validar que se haya elegido el presupuesto más barato de los disponibles";
	}

	public String getDescripcion() {
		return descripcion;
	}

}
