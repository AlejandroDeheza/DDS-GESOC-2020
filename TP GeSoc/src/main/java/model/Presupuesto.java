package model;

import exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class Presupuesto {
	
	private List<Item> items = new ArrayList<>();
	
	public float valorTotal() {
		return (float) this.items.stream().mapToDouble(a -> a.getValor()).sum();
    }

	public List<Item> getItems() {
		return items;
	}

	public Presupuesto(List<Item> items) {
		this.items = items;
	};
    
}
