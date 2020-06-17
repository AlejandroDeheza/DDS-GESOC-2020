package model;

import exceptions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Presupuesto {
	
	private List<Item> items = new ArrayList<>();
	
	public BigDecimal valorTotal() {
		return this.items.stream().map(i -> i.getValor()).reduce(BigDecimal.ZERO,BigDecimal::add);
	}

	public List<Item> getItems() {
		return items;
	}

	public Presupuesto(List<Item> items) {
		this.items = items;
	};
    
}
