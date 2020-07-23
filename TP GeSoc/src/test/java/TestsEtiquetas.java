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
	
	public OperacionDeEgreso crearOperacionDeEgreso(BigDecimal valorA, BigDecimal valorB, BigDecimal valorC) {
		return new OperacionDeEgreso(crearListaDeTresItems(valorA,valorB,valorC),null,null,null,null,null,null,null);
	}
	
	public EntidadBase armarEntidadBase() {
		EntidadBase entidadBase = new EntidadBase();
		
		OperacionDeEgreso operacion1 = crearOperacionDeEgreso(new BigDecimal(10),new BigDecimal(20),new BigDecimal(30));
		operacion1.agregarEtiqueta(new EtiquetaOperacion("A"));
		OperacionDeEgreso operacion2 = crearOperacionDeEgreso(new BigDecimal(10),new BigDecimal(20),new BigDecimal(30));
		operacion2.agregarEtiqueta(new EtiquetaOperacion("B"));
		OperacionDeEgreso operacion3 = crearOperacionDeEgreso(new BigDecimal(5),new BigDecimal(10),new BigDecimal(20));
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
	
	//TODO - Agregar tests para el repo de etiquetas
}
