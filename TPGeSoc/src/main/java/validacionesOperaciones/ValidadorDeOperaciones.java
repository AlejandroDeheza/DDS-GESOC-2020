package validacionesOperaciones;

import model.EstadoOperacion;
import model.OperacionDeEgreso;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import repositorios.RepositorioOperaciones;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ValidadorDeOperaciones implements Runnable, WithGlobalEntityManager, TransactionalOps {

	public void run() {
		
		System.out.println("Ejecutando validacion de compras...");
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
	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		Runnable tareaAEjecutar = new ValidadorDeOperaciones();
		int delayInicial = (24-LocalDateTime.now().getHour())*3600 - LocalDateTime.now().getMinute()*60 - LocalDateTime.MAX.getSecond();
		int delayPeriodico = 86400;
		System.out.println("Iniciando validador de operaciones");
		System.out.println("La validación se ejecutará dentro de " + delayInicial + " segundos");
		scheduler.scheduleAtFixedRate(tareaAEjecutar, delayInicial, delayPeriodico, TimeUnit.SECONDS);
	}
	
}
