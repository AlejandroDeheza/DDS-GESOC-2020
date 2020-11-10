package ClasePrincipal;

import java.util.concurrent.*;

import validacionesOperaciones.ValidadorDeOperaciones;

public class MainClass{
	
	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		Runnable tareaAEjecutar = new ValidadorDeOperaciones();
		int delayInicial = 5;
		int delayPeriodico = 30;
		scheduler.scheduleAtFixedRate(tareaAEjecutar, delayInicial, delayPeriodico,TimeUnit.SECONDS);
	}
}
