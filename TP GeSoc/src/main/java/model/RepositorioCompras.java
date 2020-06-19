package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import usuarios.*;

public final class RepositorioCompras {
	private List<OperacionDeEgreso> comprasPendientes = new ArrayList<>();
	private List<OperacionDeEgreso> comprasAceptadas = new ArrayList<>();
	
	private static final RepositorioCompras INSTANCE = new RepositorioCompras();
	
	public static final RepositorioCompras instance() {
		return INSTANCE;
	}
	
	public void agregarNuevaCompra(OperacionDeEgreso compra) {
		comprasPendientes.add(compra);
	}
	
	public void validarComprasPendientes() {
		List<OperacionDeEgreso> comprasValidas = comprasPendientes.stream().filter(compra -> compra.esValida()).collect(Collectors.toList());
		comprasAceptadas.addAll(comprasValidas);
		comprasValidas.forEach(compra -> compra.compraValidada());
		comprasPendientes.removeAll(comprasValidas);
	}
}
