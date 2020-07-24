import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.*;
import organizacion.*;

public class TestsEtiquetas {
	
	EntidadBase entidad;
	
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
	
	public OperacionDeEgreso crearOperacionDeEgreso(Double valorA, Double valorB, Double valorC) {
		return new OperacionDeEgreso(crearListaDeTresItems(valorA,valorB,valorC));
	}
	
	public EntidadBase armarEntidadBase() {
		EntidadBase entidadBase = new EntidadBase();
		
		OperacionDeEgreso operacion1 = crearOperacionDeEgreso(10.0,20.0,30.0);
		operacion1.agregarEtiqueta(new EtiquetaOperacion("A"));
		OperacionDeEgreso operacion2 = crearOperacionDeEgreso(10.0,20.0,30.0);
		operacion2.agregarEtiqueta(new EtiquetaOperacion("B"));
		OperacionDeEgreso operacion3 = crearOperacionDeEgreso(5.0,10.0,20.0);
		operacion3.agregarEtiqueta(new EtiquetaOperacion("A"));
		
		//entidadBase.setCategoriaEntidad(new CategoriaEntidad(null,"CATEGORIA_SIN_VALIDACIONES"));
		
		entidadBase.egresos.add(operacion1);
		entidadBase.egresos.add(operacion2);
		entidadBase.egresos.add(operacion3);
		
		return entidadBase;
		
	}
	
	@Before
	public void init() {
		
	entidad = armarEntidadBase();
	
	}
	
	@Test
	public void seCalculaElGastoGeneradoPorLosEgresosDeUnaEntidadQueTienenCiertaEtiqueta() {
		Assert.assertEquals(entidad.gastosDeEtiqueta(new EtiquetaOperacion("a")),new BigDecimal(95));
	}

}
