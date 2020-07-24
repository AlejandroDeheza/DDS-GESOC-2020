package model;

import java.math.BigDecimal;

import ubicacion.InfoDeUbicacionYMoneda;

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
	
}
