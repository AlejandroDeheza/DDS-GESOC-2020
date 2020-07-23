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
	
	public List<Item> crearListaDeTresItems(BigDecimal valorA, BigDecimal valorB, BigDecimal valorC){
		List <Item> ListaItems = new ArrayList<>();
		Item item1 = new Item(valorA, "Item A");
		Item item2 = new Item(valorB, "Item B");
		Item item3 = new Item(valorC, "Item C");

		 ListaItems.add(item1);
		 ListaItems.add(item2);
		 ListaItems.add(item3);
	
		return ListaItems;
	}
	
	public List<Item> crearListaDeOtrosTresItems(BigDecimal valorA, BigDecimal valorB, BigDecimal valorC){
		List <Item> ListaItems = new ArrayList<>();
		Item item1 = new Item(valorA, "Item X");
		Item item2 = new Item(valorB, "Item Y");
		Item item3 = new Item(valorC, "Item Z");

		 ListaItems.add(item1);
		 ListaItems.add(item2);
		 ListaItems.add(item3);
	
		return ListaItems;
	}
	
	public OperacionDeEgreso crearOperacionDeEgreso(BigDecimal valorA, BigDecimal valorB, BigDecimal valorC) {
		return new OperacionDeEgreso(crearListaDeTresItems(valorA,valorB,valorC));
	}
	
	public Presupuesto crearPresupuesto(List<Item> listaDeItems) {
		return new Presupuesto(listaDeItems,null,null,null);
	}

	@Before
	public void init() {
		
		operacionValida1 = new OperacionDeEgreso(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(15)));
		operacionValida1.presupuestos.add(crearPresupuesto(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(15))));
		
		operacionValida2 = new OperacionDeEgreso(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(15)));
		operacionValida2.presupuestos.add(crearPresupuesto(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(15))));
		operacionValida2.presupuestos.add(crearPresupuesto(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(16))));
		
		operacionInvalida1 = new OperacionDeEgreso(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(15)));
		
		operacionInvalida2 = new OperacionDeEgreso(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(16)));
		operacionInvalida2.presupuestos.add(crearPresupuesto(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(15))));
		operacionInvalida2.presupuestos.add(crearPresupuesto(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(16))));
		
		operacionInvalida3 = new OperacionDeEgreso(crearListaDeOtrosTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(16)));
		operacionInvalida3.presupuestos.add(crearPresupuesto(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(15))));
		operacionInvalida3.presupuestos.add(crearPresupuesto(crearListaDeTresItems(new BigDecimal(5),new BigDecimal(10), new BigDecimal(16))));
		
		RepositorioCompras.instance().comprasPendientes.add(operacionInvalida1);
		RepositorioCompras.instance().comprasPendientes.add(operacionValida1);
		RepositorioCompras.instance().comprasPendientes.add(operacionInvalida3);
		RepositorioCompras.instance().comprasPendientes.add(operacionInvalida2);
		RepositorioCompras.instance().comprasPendientes.add(operacionValida2);

	}
	
	@Test
	public void ejecutarValidacionDeOperaciones() {
		RepositorioCompras.instance().validarComprasPendientes();
		Assert.assertEquals(RepositorioCompras.instance().comprasAceptadas.size(),2);
	}

}
