package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class RepositorioCompras {
	private List<OperacionDeEgreso> comprasPendientes = new ArrayList<>();
	private List<OperacionDeEgreso> comprasAceptadas = new ArrayList<>();
	
	private static final RepositorioCompras INSTANCE = new RepositorioCompras();
	
	public static final RepositorioCompras instance() {
		return INSTANCE;
	}
	
	public void agregarNuevaCompra(OperacionDeEgreso compra) {
		this.comprasPendientes.add(compra);
	}
	
	private List<OperacionDeEgreso> comprasValidas() {
		return this.comprasPendientes.stream().filter(compra -> compra.esValida()).collect(Collectors.toList());
	}
	
	public void validarComprasPendientes() {
		List<OperacionDeEgreso> comprasValidas = this.comprasValidas();
		this.comprasAceptadas.addAll(comprasValidas);
		comprasValidas.forEach(compra -> compra.notificarRevisores("La compra es valida"));
		this.comprasPendientes.removeAll(comprasValidas);
		this.comprasPendientes.forEach(compra -> compra.notificarRevisores("La compra NO es valida"));
	}
}
