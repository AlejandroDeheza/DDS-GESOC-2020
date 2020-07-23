package validacionesEntidades;

import java.math.BigDecimal;

import organizacion.Entidad;

public class ValidarNuevosEgresos implements ValidacionEntidad {
	
	public BigDecimal montoMaximoEgresos;
	
	@Override
	public boolean entidadValida(Entidad entidad) {
		BigDecimal sumaEgresos = entidad.egresos.stream().map(egreso -> egreso.valorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
		return sumaEgresos.compareTo(montoMaximoEgresos)<=0;
	}
	
	public ValidarNuevosEgresos(BigDecimal montoMaximoEgresos) {
		this.montoMaximoEgresos = montoMaximoEgresos;
	}


}
