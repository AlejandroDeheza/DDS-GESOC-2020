package model;

import java.math.BigDecimal;

public class Item {
	private BigDecimal valor;
	private String descripcion;
	
	public Item(BigDecimal valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public BigDecimal getValor() {
		return this.valor;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public boolean equals(Item item) {
		return this.valor.equals(item.valor) && this.descripcion.equals(item.descripcion);
	}
}
