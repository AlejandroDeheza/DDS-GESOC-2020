package validacionesOperaciones;

import java.util.List;

import model.EstadoOperacion;
import model.OperacionDeEgreso;
import repositorios.RepositorioCompras;

public class ValidadorDeOperaciones implements Runnable {

	public void run() {
		
		System.out.println("Ejecutando validacion de compras...");
		//ValidadorDeOperaciones validador = new ValidadorDeOperaciones();
		validarComprasPendientes();
	    System.out.println("Compras validadas.\n");
	}
	
	public void validarComprasPendientes(){
		List<OperacionDeEgreso> comprasPendientes = RepositorioCompras.instance().obtenerOperaciones("estado = 'PENDIENTE'");
		
		comprasPendientes.stream().forEach(compra -> this.validarCompra(compra));
		
	}
	
	public void validarCompra(OperacionDeEgreso compra) {
		
		if(compra.esValida()) {
			compra.setEstado(EstadoOperacion.APROBADA);
			compra.notificarRevisores("La operacion fue validada correctamente");	
		}
		else {
			compra.setEstado(EstadoOperacion.RECHAZADA);
			compra.notificarRevisores("La operacion no es valida");
		}
		
		RepositorioCompras.instance().actualizarCompra(compra);
		
	}
	
	
}
