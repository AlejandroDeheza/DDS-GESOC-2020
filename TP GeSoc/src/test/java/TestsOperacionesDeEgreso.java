import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.*;
import validacionesOperaciones.*;

public class TestsOperacionesDeEgreso {
	
	List<Item> ListaItems, ListaItems2, ListaItems3;
	Item item1, item2, item3, item4, item5, item6, item7, item8, item9;
	Presupuesto presupuesto1;
	OperacionDeEgreso operacion1;

	@Before
	public void init() {
	     ListaItems = new ArrayList<>();
	     ListaItems2 = new ArrayList<>();
	     ListaItems3 = new ArrayList<>();
	     
	     //Items compra
		 item1 = new Item(new BigDecimal(20), "Item A");
		 item2 = new Item(new BigDecimal(30), "Item B");
		 item3 = new Item(new BigDecimal(40), "Item C");
		 //Items presupuesto 1
		 item4 = new Item(new BigDecimal(20), "Item A");
		 item5 = new Item(new BigDecimal(30), "Item B");
		 item6 = new Item(new BigDecimal(40), "Item C");
		 //Items presupuesto 2
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
		 
		 presupuesto1 = new Presupuesto(ListaItems, null, null, null); //Solo deberiamos hacer esto en un test...
		 //En el sistema real no deberia ser posible. Así respetamos el punto 2 de la entrega 2.
		 
		 operacion1 = new OperacionDeEgreso(ListaItems2, null, null, null, null, null, null, null);
		 operacion1.agregarNuevoPresupuesto(ListaItems, null, null);
		 operacion1.agregarNuevoPresupuesto(ListaItems3, null, null);
	}
	
	@Test
	public void elValorTotalDelPresupuestoEsLaSumaDelValorDeSusItems() {
		Assert.assertEquals(presupuesto1.valorTotal(),new BigDecimal(90));
	}
	
	@Test
	public void losItemsDeLaOperacionCoincidenConLosDeAlgunoDeSusPresupuestos() {
		ValidarQueLaOperacionContengaTodosLosItems validacion = new ValidarQueLaOperacionContengaTodosLosItems();
		Assert.assertTrue(validacion.pasoCorrectamente(operacion1));
	}

	@Test
	public void laOperacionTieneLaCantidadMinimaDePresupuestosQueRequiere() {
		ValidarQueSeHayaElegidoElPresupuestoMasBarato validacion = new ValidarQueSeHayaElegidoElPresupuestoMasBarato();
		Assert.assertTrue(validacion.pasoCorrectamente(operacion1));
	}
	@Test
	public void losItemsCoincidenConLosDelPresupuestoDeMenorValor() {
		ValidarQueTengaLaSuficienteCantidadDePresupuestos validacion = new ValidarQueTengaLaSuficienteCantidadDePresupuestos();
		Assert.assertTrue(validacion.pasoCorrectamente(operacion1));
	}
	
	@Test
	public void laOperacionEsValidaSiContemplaTodasLasValidaciones() {
		Assert.assertTrue(operacion1.esValida());
	}
	
	

}
