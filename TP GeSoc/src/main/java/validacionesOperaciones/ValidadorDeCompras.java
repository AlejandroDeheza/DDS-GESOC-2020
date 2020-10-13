package validacionesOperaciones;

import java.util.List;

import model.EstadoOperacion;
import model.OperacionDeEgreso;
import repositorios.RepositorioCompras;

public class ValidadorDeCompras {

	public void validarComprasPendientes(){
		List<OperacionDeEgreso> comprasPendientes = RepositorioCompras.instance().obtenerOperaciones("estado = 'PENDIENTE'");
		
		comprasPendientes.stream().forEach(compra -> this.validarCompra(compra));
		
		RepositorioCompras.instance().actualizarOperaciones();
	}
	
	public void validarCompra(OperacionDeEgreso compra) {
		if(compra.esValida()) {
			//Aca tenemos que cambiarle el atributo a ACEPTADA.
			compra.setEstado(EstadoOperacion.APROBADA);
			compra.notificarRevisores("La operacion fue validada correctamente");
		}
		else {
			//Aca tenemos que cambiarle el atributo a RECHAZADA.
			compra.setEstado(EstadoOperacion.RECHAZADA);
			
			compra.notificarRevisores("La operacion no es valida");
		}
	}
	
	
}
