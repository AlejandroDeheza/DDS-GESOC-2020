import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.spy;

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
	
	/*public OperacionDeEgreso crearOperacionDeEgreso(Double valorA, Double valorB, Double valorC) {
		return new OperacionDeEgreso(crearListaDeTresItems(valorA,valorB,valorC));
	}*/
	
	public EntidadBase armarEntidadBase() {
		EntidadBase entidadBase = spy(EntidadBase.class);
		
		/*OperacionDeEgreso operacion1 = crearOperacionDeEgreso(10.0,20.0,30.0);
		operacion1.agregarEtiqueta(new EtiquetaOperacion("A"));
		OperacionDeEgreso operacion2 = crearOperacionDeEgreso(10.0,20.0,30.0);
		operacion2.agregarEtiqueta(new EtiquetaOperacion("B"));
		OperacionDeEgreso operacion3 = crearOperacionDeEgreso(5.0,10.0,20.0);
		operacion3.agregarEtiqueta(new EtiquetaOperacion("A"));*/
		
		OperacionDeEgreso operacion1 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion2 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion3 = Mockito.mock(OperacionDeEgreso.class);
		
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
		EtiquetaOperacion etiquetaPrueba = new EtiquetaOperacion("a");
		
		entidad.egresos.stream().forEach(egreso -> Mockito.when(egreso.valorTotal()).thenReturn(new BigDecimal(30.0)));
		Mockito.doReturn(entidad.egresos).when(entidad).egresosConEtiqueta(etiquetaPrueba);
		
		Assert.assertEquals(new BigDecimal(90),entidad.gastosDeEtiqueta(etiquetaPrueba));
	}

}
