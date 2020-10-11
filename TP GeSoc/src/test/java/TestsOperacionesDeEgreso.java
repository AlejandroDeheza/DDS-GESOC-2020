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

	@Before
	public void init() {
	     ListaItems = crearListaDeTresItems(20.0,30.0,40.0);
	     ListaItems2 = crearListaDeTresItems(20.0,30.0,40.0);
	     ListaItems3 = crearListaDeTresItems(30.0,30.0,40.0);
	     
		 presupuesto1 = new Presupuesto(ListaItems, null,  null); //Solo deberiamos hacer esto en un test...
		 //En el sistema real no deberia ser posible. Así respetamos el punto 2 de la entrega 2.
		 
		 operacion1 = new OperacionDeEgreso(ListaItems2);
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
		Assert.assertTrue(validacion.pasaLaValidacion(operacion1));
	}

	@Test
	public void laOperacionTieneLaCantidadMinimaDePresupuestosQueRequiere() {
		ValidarQueSeHayaElegidoElPresupuestoMasBarato validacion = new ValidarQueSeHayaElegidoElPresupuestoMasBarato();
		Assert.assertTrue(validacion.pasaLaValidacion(operacion1));
	}
	@Test
	public void losItemsCoincidenConLosDelPresupuestoDeMenorValor() {
		ValidarQueTengaLaSuficienteCantidadDePresupuestos validacion = new ValidarQueTengaLaSuficienteCantidadDePresupuestos();
		Assert.assertTrue(validacion.pasaLaValidacion(operacion1));
	}
	
	@Test
	public void laOperacionEsValidaSiContemplaTodasLasValidaciones() {
		Assert.assertTrue(operacion1.esValida());
	}
}
