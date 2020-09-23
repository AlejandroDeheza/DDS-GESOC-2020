package model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import ubicacion.InfoDeUbicacionYMoneda;

@Embeddable
public class Moneda {

	BigDecimal monto;
	String currency; 

	public Moneda(Double monto, String currency) {
		this.monto = new BigDecimal(monto);
		this.currency = currency;
	}

	public BigDecimal monto() {
		return monto;
	}
	
	public String currency() {
		return currency;
	}
	public Moneda() {}
	
	public BigDecimal valorEnPesos(InfoDeUbicacionYMoneda apiMoneda){
			
		System.out.println(apiMoneda.obtenerRatioAPesos(currency).toString());
		
		BigDecimal ratio = apiMoneda.obtenerRatioAPesos(currency).getBigDecimal("ratio");
		
		return monto.multiply(ratio);
	}
	
}
