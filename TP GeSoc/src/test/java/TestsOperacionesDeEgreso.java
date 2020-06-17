

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import model.*;
import usuarios.TipoUsuario;

public class TestsOperacionesDeEgreso {
	
	List<Item> ListaItems, ListaItems2, ListaItems3;
	Item item1, item2, item3, item4, item5, item6, item7, item8, item9;
	Presupuesto presupuesto1, presupuesto2;
	OperacionDeEgreso operacion1;

	@Before
	public void init() {
	     ListaItems = new ArrayList<>();
	     ListaItems2 = new ArrayList<>();
	     ListaItems3 = new ArrayList<>();
	     
		 item1 = new Item(new BigDecimal(20), "Item A");
		 item2 = new Item(new BigDecimal(30), "Item B");
		 item3 = new Item(new BigDecimal(40), "Item C");
		 item4 = new Item(new BigDecimal(20), "Item A");
		 item5 = new Item(new BigDecimal(30), "Item B");
		 item6 = new Item(new BigDecimal(40), "Item C");
		 item7 = new Item(new BigDecimal(30), "Item A");
		 item8 = new Item(new BigDecimal(30), "Item B");
		 item9 = new Item(new BigDecimal(40), "Item C");
		 
		 ListaItems.add(item1);
		 ListaItems.add(item2);
		 ListaItems.add(item3);
	
		 ListaItems2.add(item5);
		 ListaItems2.add(item4);
		 ListaItems2.add(item6);
		 
		 ListaItems3.add(item9);
		 ListaItems3.add(item7);
		 ListaItems3.add(item8);
		 
		 presupuesto1 = new Presupuesto(ListaItems);
		 presupuesto2 = new Presupuesto(ListaItems3);
		 
		 operacion1 = new OperacionDeEgreso(ListaItems2);
		 operacion1.agregarNuevoPresupuesto(presupuesto1);
		 operacion1.agregarNuevoPresupuesto(presupuesto2);
	}
	
	@Test
	public void elValorTotalDelPresupuestoEsLaSumaDelValorDeSusItems() {
		Assert.assertEquals(presupuesto1.valorTotal(),new BigDecimal(90));
	}
	
	@Test
	public void losItemsDeLaOperacionCoincidenConLosDeAlgunoDeSusPresupuestos() {
		Assert.assertTrue(operacion1.estaBasadaEnAlgunPresupuesto());
	}
	@Test
	public void laOperacionTieneLaCantidadMinimaDePresupuestosQueRequiere() {
		Assert.assertTrue(operacion1.tieneLaSuficienteCantidadDePresupuestos());
	}
	@Test
	public void losItemsCoincidenConLosDelPresupuestoDeMenorValor() {
		Assert.assertTrue(operacion1.seEligioElPresupuestoMasBarato());
	}
	@Test
	public void laOperacionEsValidaSiContemplaTodasLasValidaciones() {
		Assert.assertTrue(operacion1.esValida());
	}
	
	

}
