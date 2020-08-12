package comportamientoEntidad;

import java.math.BigDecimal;

import exceptions.LaCantidadDeEgresosSuperaElMontoMaximoException;
import model.OperacionDeEgreso;
import organizacion.Entidad;

public class PoderAgregarEgresos extends Comportamiento{
	public BigDecimal montoMaximoEgresos;
	
		
	public PoderAgregarEgresos(BigDecimal montoMaximoEgresos) {
		this.montoMaximoEgresos = montoMaximoEgresos;
	}

	@Override
	public void alAgregarEgreso(Entidad entidad, OperacionDeEgreso nuevoEgreso){
		
		BigDecimal sumaEgresos = entidad.egresos.stream().map(egreso -> egreso.valorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
		
		if(sumaEgresos.add(nuevoEgreso.valorTotal()).compareTo(montoMaximoEgresos) <= 0)
			throw new LaCantidadDeEgresosSuperaElMontoMaximoException();
	}
}
