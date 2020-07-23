package ClasePrincipal;

import java.util.Scanner;
import java.util.concurrent.*;

import model.*;
import repositorios.RepositorioCompras;

public class MainClass implements Runnable{
	
	public void run() {
		
		System.out.println("Ejecutando validacion de compras...");
		RepositorioCompras.instance().validarComprasPendientes();
	    System.out.println("Compras validadas.\n");
	}

	public static void main(String[] args) {

		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		Runnable tareaAEjecutar = new MainClass();
		int delayInicial = 5;
		int delayPeriodico = 5;
		scheduler.scheduleAtFixedRate(tareaAEjecutar, delayInicial, delayPeriodico,TimeUnit.SECONDS);
		
	}
}
