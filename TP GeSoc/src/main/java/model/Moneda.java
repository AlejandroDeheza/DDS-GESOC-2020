package model;

import java.math.BigDecimal;

import ubicacion.InfoDeUbicacionYMoneda;

public class Moneda {
	
	InfoDeUbicacionYMoneda api;
	BigDecimal monto;
	BigDecimal ratioAPesos;
	String currency; // Ver como representamos esto

	
	public Moneda(Double monto, String currency) {
		this.monto = new BigDecimal(monto);
		this.currency = currency;
		this.api = new MercadoLibreApi();
				
		System.out.println(api.obtenerRatioAPesos(currency).toString());
		
		this.ratioAPesos = api.obtenerRatioAPesos(currency).getBigDecimal("ratio");
	}

	public BigDecimal valorEnPesos(){
		BigDecimal valorEnPesos = monto.multiply(ratioAPesos);
				
		return valorEnPesos;
	}
}
