import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import model.*;
import organizacion.*;
import repositorios.RepositorioCompras;

public class TestsRepositorioDeCompras {
	
	OperacionDeEgreso operacionValida1;
	OperacionDeEgreso operacionValida2;;
	OperacionDeEgreso operacionInvalida1;
	OperacionDeEgreso operacionInvalida2;
	OperacionDeEgreso operacionInvalida3;
	
	public Moneda crearMoneda(Double monto) {
		return new Moneda(monto,"ARS");
	}
	
	public List<Item> crearListaDeTresItems(Double valorA, Double valorB, Double valorC){
		List <Item> ListaItems = new ArrayList<>();
		Item item1 = new Item(crearMoneda(valorA), "Item A");
		Item item2 = new Item(crearMoneda(valorB), "Item B");
		Item item3 = new Item(crearMoneda(valorC), "Item C");

		 ListaItems.add(item1);
		 ListaItems.add(item2);
		 ListaItems.add(item3);
	
		return ListaItems;
	}
	
	public List<Item> crearListaDeOtrosTresItems(Double valorA, Double valorB, Double valorC){
		List <Item> ListaItems = new ArrayList<>();
		Item item1 = new Item(crearMoneda(valorA), "Item X");
		Item item2 = new Item(crearMoneda(valorB), "Item Y");
		Item item3 = new Item(crearMoneda(valorC), "Item Z");

		 ListaItems.add(item1);
		 ListaItems.add(item2);
		 ListaItems.add(item3);
	
		return ListaItems;
	}
	
	public OperacionDeEgreso crearOperacionDeEgreso(Double valorA, Double valorB, Double valorC) {
		return new OperacionDeEgreso(crearListaDeTresItems(valorA,valorB,valorC));
	}
	
	public Presupuesto crearPresupuesto(List<Item> listaDeItems) {
		return new Presupuesto(listaDeItems,null,null);
	}

	@Before
	public void init() {
		
		operacionValida1 = crearOperacionDeEgreso(5.0,10.0,15.0);
		operacionValida1.presupuestos.add(crearPresupuesto(crearListaDeTresItems(5.0,10.0,15.0)));
		
		operacionValida2 = crearOperacionDeEgreso(5.0,10.0,15.0);
		operacionValida2.presupuestos.add(crearPresupuesto(crearListaDeTresItems(5.0,10.0,15.0)));
		operacionValida2.presupuestos.add(crearPresupuesto(crearListaDeTresItems(5.0,10.0,16.0)));
		
		operacionInvalida1 = crearOperacionDeEgreso(5.0,10.0,15.0);
		
		operacionInvalida2 = crearOperacionDeEgreso(5.0,10.0,16.0);
		operacionInvalida2.presupuestos.add(crearPresupuesto(crearListaDeTresItems(5.0,10.0,15.0)));
		operacionInvalida2.presupuestos.add(crearPresupuesto(crearListaDeTresItems(5.0,10.0,16.0)));
		
		operacionInvalida3 = new OperacionDeEgreso(crearListaDeOtrosTresItems(5.0,10.0,15.0));
		operacionInvalida3.presupuestos.add(crearPresupuesto(crearListaDeTresItems(5.0,10.0,15.0)));
		operacionInvalida3.presupuestos.add(crearPresupuesto(crearListaDeTresItems(5.0,10.0,16.0)));
		
		RepositorioCompras.instance().comprasPendientes.add(operacionInvalida1);
		RepositorioCompras.instance().comprasPendientes.add(operacionValida1);
		RepositorioCompras.instance().comprasPendientes.add(operacionInvalida3);
		RepositorioCompras.instance().comprasPendientes.add(operacionInvalida2);
		RepositorioCompras.instance().comprasPendientes.add(operacionValida2);

	}
	//Comento este test ya que cambio el sistema de validacion.
	
//	@Test
//	public void ejecutarValidacionDeOperaciones() {
//		RepositorioCompras.instance().validarComprasPendientes();
//		List<OperacionDeEgreso> listaEsperada = new ArrayList<>();
//		listaEsperada.add(operacionValida1);
//		listaEsperada.add(operacionValida2);
//		Assert.assertEquals(RepositorioCompras.instance().comprasAceptadas,listaEsperada);
//	}

}
