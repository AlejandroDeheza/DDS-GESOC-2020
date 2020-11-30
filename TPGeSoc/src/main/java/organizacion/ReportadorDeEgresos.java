package organizacion;

import java.math.BigDecimal;

import model.EtiquetaOperacion;

public class ReportadorDeEgresos{
	public BigDecimal gastosDeEtiqueta(Entidad entidad,EtiquetaOperacion etiqueta) {
		return entidad.egresosConEtiqueta(etiqueta).stream().map(egreso -> egreso.valorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}
	
	public BigDecimal gastosTotales(Entidad entidad){
		return entidad.egresos.stream().map(egreso -> egreso.valorTotal()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}
}
