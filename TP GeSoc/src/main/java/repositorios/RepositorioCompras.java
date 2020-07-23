package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.OperacionDeEgreso;

public final class RepositorioCompras {
	public List<OperacionDeEgreso> comprasPendientes = new ArrayList<>();
	public List<OperacionDeEgreso> comprasValidadas = new ArrayList<>();
	public List<OperacionDeEgreso> comprasAceptadas = new ArrayList<>();
	
	private static final RepositorioCompras INSTANCE = new RepositorioCompras();
	
	public static final RepositorioCompras instance() {
		return INSTANCE;
	}
	
	public void agregarNuevaCompra(OperacionDeEgreso compra) {
		this.comprasPendientes.add(compra);
	}
	
	
	
	public void validarComprasPendientes() {
		this.comprasPendientes.stream().forEach(compra -> compra.validarCompra());
		this.comprasAceptadas.addAll(this.comprasValidadas);
		this.comprasPendientes.removeAll(this.comprasValidadas);
		this.comprasValidadas.clear();
	}

	/*
	private List<OperacionDeEgreso> comprasValidas() {
		return this.comprasPendientes.stream().filter(compra -> compra.esValida()).collect(Collectors.toList());
	}
	
	public void validarComprasPendientes() {
		List<OperacionDeEgreso> comprasValidas = this.comprasValidas();
		this.comprasAceptadas.addAll(comprasValidas);
		this.comprasPendientes.removeAll(comprasValidas);
	}
	*/
	
}
