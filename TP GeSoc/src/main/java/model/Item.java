package model;
public class Item {
	int valor;
	String descripcion;
	
	public Item(int valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public int getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
