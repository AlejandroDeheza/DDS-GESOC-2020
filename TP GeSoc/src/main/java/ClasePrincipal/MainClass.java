package ClasePrincipal;

import java.util.Scanner;

import model.*;

public class MainClass {

	public static void main(String[] args) {
		int opc=0;
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
	}
}
