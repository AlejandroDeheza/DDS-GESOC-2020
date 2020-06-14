package model;

public class Item {
	private int valor;
	private String descripcion;
	
	public Item(int valor, String descripcion,OperacionDeEgreso operacionAsociada ) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public int getValor() {
		return this.valor;
	}

	public String getDescripcion() {
		return this.descripcion;
	}



}
