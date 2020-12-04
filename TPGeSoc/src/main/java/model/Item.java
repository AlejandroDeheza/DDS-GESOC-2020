package model;

import javax.persistence.*;


@Entity
@Table(name = "items")
public class Item {
	@Id
	@GeneratedValue
	@Column(name = "id_item")
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
	public Item() {
		
	}
}
