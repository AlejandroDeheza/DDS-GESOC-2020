package validacionesOperaciones;

import java.util.List;

import model.EstadoOperacion;
import model.OperacionDeEgreso;
import repositorios.RepositorioCompras;

public class ValidadorDeOperaciones {
	
	void ejecutar() {
		//Pedirle las operaciones al repo
		List<OperacionDeEgreso> comprasPendientes = RepositorioCompras.instance().obtenerOperaciones("estado = 'PENDIENTE'");
		//Validarlas
		comprasPendientes.stream().forEach( (c) -> {
			if (c.esValida())
			 c.setEstado(EstadoOperacion.APROBADA);
		}
		);
		//TODO - Actualizarlas en el repo, ¿como hacemos?
		 
	}

}
