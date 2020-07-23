package ClasePrincipal;

import java.util.Scanner;

import model.*;
import repositorios.RepositorioCompras;

public class MainClass {

	public static void main(String[] args) {
	/*	int opc=0;
		Scanner entrada = new Scanner (System.in);
		System.out.println("GeSoc 2020\n");
		System.out.println("Que desea hacer?\n1)Validar compras\nOtro)Finalizar\n");
		opc=entrada.nextInt();
		if(opc==1) {
			  RepositorioCompras.instance().validarComprasPendientes();
			  System.out.println("Compras validadas\n");
		}
	
		System.out.println("Adios :)\n");
		entrada.close();
	} */
	
    System.out.println("Ejecutando validacion de compras...\n");
	RepositorioCompras.instance().validarComprasPendientes();
	System.out.println("Compras validadas.");
	}
}
