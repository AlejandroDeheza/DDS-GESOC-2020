package model;

import java.math.BigDecimal;

import javax.persistence.*;


@Entity
@Table(name = "Items")
public class Item {
	@Id
	@GeneratedValue
	private Long id;
	
	@Embedded
	private Moneda moneda;
	
	private String descripcion;
	
	public Item(Moneda moneda, String descripcion) {
		this.moneda = moneda;
		this.descripcion = descripcion;
	}

	public Moneda getMoneda() {
		return this.moneda;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public boolean equals(Item item) {
		return this.descripcion.equals(item.descripcion);
	}
	
}
