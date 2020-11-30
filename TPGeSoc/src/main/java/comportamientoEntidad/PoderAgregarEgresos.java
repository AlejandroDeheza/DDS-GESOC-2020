package comportamientoEntidad;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import exceptions.*;
import model.OperacionDeEgreso;
import organizacion.Entidad;

@Entity
@DiscriminatorValue("LIMITAR_EGRESOS")
public class PoderAgregarEgresos extends Comportamiento{
	@Column(name = "monto_maximo_egresos")
	public BigDecimal montoMaximoEgresos;
	
 
	public PoderAgregarEgresos(BigDecimal montoMaximoEgresos) {
		this.montoMaximoEgresos = montoMaximoEgresos;
	}

	@Override
	public void alAgregarEgreso(Entidad entidad, OperacionDeEgreso nuevoEgreso){
		
		BigDecimal sumaEgresos = entidad.egresos.stream().map(egreso -> egreso.valorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
		if(sumaEgresos.add(nuevoEgreso.valorTotal()).compareTo(montoMaximoEgresos) >= 0)
			throw new LaCantidadDeEgresosSuperaElMontoMaximoException();
	}
	
	public PoderAgregarEgresos() {
	}
}
