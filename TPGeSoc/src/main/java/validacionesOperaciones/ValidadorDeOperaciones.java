package validacionesOperaciones;

import java.util.List;

import model.EstadoOperacion;
import model.OperacionDeEgreso;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import repositorios.RepositorioOperaciones;

public class ValidadorDeOperaciones implements Runnable, WithGlobalEntityManager, TransactionalOps {

	public void run() {
		
		System.out.println("Ejecutando validacion de compras...");
		//ValidadorDeOperaciones validador = new ValidadorDeOperaciones();
		validarComprasPendientes();
	    System.out.println("Compras validadas.\n");
	}
	
	public void validarComprasPendientes(){
		List<OperacionDeEgreso> comprasPendientes = RepositorioOperaciones.instance().obtenerOperaciones("estado = 'PENDIENTE'");
		
		comprasPendientes.stream().forEach(compra -> this.validarCompra(compra));
		
	}
	
	public void validarCompra(OperacionDeEgreso compra) {
		
		if(compra.esValida()) {
			compra.setEstado(EstadoOperacion.APROBADA);
			compra.notificarRevisores("Validacion compra Nº"+compra.getId(),"La operacion fue validada correctamente");
		}
		else {
			compra.setEstado(EstadoOperacion.RECHAZADA);
			compra.notificarRevisores("Validacion compra Nº"+compra.getId(),"La operacion no es valida");
		}
		withTransaction(() ->{
			RepositorioOperaciones.instance().actualizarCompra(compra);
		});
	}
	
	
}
