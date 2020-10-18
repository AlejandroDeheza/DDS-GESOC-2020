import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import comportamientoEntidad.*;
import exceptions.*;
import model.*;
import organizacion.*;
import repositorios.RepositorioCategoriasDeEntidades;

public class TestsCategoriasEntidades {
	
	EntidadBase entidadBase1;
	EntidadJuridica entidadJuridica1;
	CategoriaEntidad categoriaEgresos, categoriaJuridica, categoriaBase, categoriaSinComportamiento;
	
	@Mock
	OperacionDeEgreso operacion1;
	OperacionDeEgreso operacion2;
	OperacionDeEgreso operacion3;
	OperacionDeEgreso operacion4;
	OperacionDeEgreso operacion5;
	
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	public EntidadBase armarEntidadBase() {
		EntidadBase entidadBase = new EntidadBase();
		
		/*
		OperacionDeEgreso operacion1 = crearOperacionDeEgreso(10.0,20.0,30.0);
		operacion1.agregarEtiqueta(new EtiquetaOperacion("A"));
		OperacionDeEgreso operacion2 = crearOperacionDeEgreso(10.0,20.0,30.0);
		operacion2.agregarEtiqueta(new EtiquetaOperacion("B"));
		OperacionDeEgreso operacion3 = crearOperacionDeEgreso(5.0,10.0,20.0);
		operacion3.agregarEtiqueta(new EtiquetaOperacion("A"));
		*/
		//entidadBase.setCategoriaEntidad(new CategoriaEntidad(null,"CATEGORIA_SIN_VALIDACIONES"));
		
		OperacionDeEgreso operacion1 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion2 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion3 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion4 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion5 = Mockito.mock(OperacionDeEgreso.class);
		
		/*Mockito.when(operacion1.valorTotal()).thenReturn(new BigDecimal(60.0));
		Mockito.when(operacion2.valorTotal()).thenReturn(new BigDecimal(60.0));
		Mockito.when(operacion3.valorTotal()).thenReturn(new BigDecimal(35.0));
		Mockito.when(operacion4.valorTotal()).thenReturn(new BigDecimal(300.0));
		Mockito.when(operacion5.valorTotal()).thenReturn(new BigDecimal(3.0)); */
		
		entidadBase.egresos.add(operacion1);
		entidadBase.egresos.add(operacion2);
		entidadBase.egresos.add(operacion3);
		
		
		return entidadBase;
	}
	
	public EntidadJuridica armarEntidadJuridica() {
		EntidadJuridica entidadJuridica = new EntidadJuridica();
		
		OperacionDeEgreso operacion1 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion2 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion3 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion4 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion5 = Mockito.mock(OperacionDeEgreso.class);
				
		entidadJuridica.egresos.add(operacion1);
		entidadJuridica.egresos.add(operacion2);
		entidadJuridica.egresos.add(operacion3);
		
		return entidadJuridica;
	}
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
	public List<Comportamiento> crearListaDeValidacionesEgresos(){
		List<Comportamiento> validaciones = new ArrayList<>();
		validaciones.add(new PoderAgregarEgresos(new BigDecimal(200)));
		return validaciones;
	}
	public List<Comportamiento> crearListaDeValidacionesJuridica(){
		List<Comportamiento> validaciones = new ArrayList<>();
		validaciones.add(new PoderAgregarEntidadesBaseAJuridica());
		return validaciones;
	}
	public List<Comportamiento> crearListaDeValidacionesBase(){
		List<Comportamiento> validaciones = new ArrayList<>();
		validaciones.add(new PoderAsociarEntidadJuridicaABase(entidadJuridica1));
		return validaciones;
	}
	public List<Comportamiento> crearListaDeValidacionesVacia(){
		List<Comportamiento> validaciones = new ArrayList<>();
		return validaciones;
	}
	public CategoriaEntidad crearCategoriaQueReestringeEgresos() {
		return new CategoriaEntidad(crearListaDeValidacionesEgresos(),"categoriaParaEgresos");
	}
	public CategoriaEntidad crearCategoriaQueReestringeAsociarseAUnaJuridica() {
		return new CategoriaEntidad(crearListaDeValidacionesBase(),"categoriaParaBase");
	}
	public CategoriaEntidad crearCategoriaQueImpideAUnaJuridicaAsociarBases() {
		return new CategoriaEntidad(crearListaDeValidacionesJuridica(),"categoriaParaJuridica");
	}
	public CategoriaEntidad crearCategoriaSinComportamiento() {
		return new CategoriaEntidad(new ArrayList<>(),"categoriaSinComportamiento");
	}

	@Before
	public void init() {
		entidadJuridica1 = new EntidadJuridica();
		entidadBase1 = new EntidadBase();
		entidadBase1.setCategoriaEntidad(new CategoriaEntidad(crearListaDeValidacionesVacia(),"Categoria_sin_restricciones"));
		
		categoriaEgresos = crearCategoriaQueReestringeEgresos();
		categoriaJuridica = crearCategoriaQueImpideAUnaJuridicaAsociarBases();
		categoriaBase = crearCategoriaQueReestringeAsociarseAUnaJuridica();
		categoriaSinComportamiento = crearCategoriaSinComportamiento();
		
		/*RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(categoriaEgresos);
		RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(categoriaJuridica);
		RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(categoriaBase);
		RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(categoriaSinComportamiento);*/
	}
	
	@Test(expected = LaCantidadDeEgresosSuperaElMontoMaximoException.class)
	public void noSePuedeAgregarUnEgresoSiSeSuperaElMontoMaximoAcumulado() {
		OperacionDeEgreso operacion4 = Mockito.mock(OperacionDeEgreso.class);
		Mockito.when(operacion4.valorTotal()).thenReturn(new BigDecimal(50.0));
		
		EntidadBase entidadBase = armarEntidadBase();
		entidadBase.egresos.stream().forEach(egreso -> Mockito.when(egreso.valorTotal()).thenReturn(new BigDecimal(100.0)));
		
		entidadBase.setCategoriaEntidad(crearCategoriaQueReestringeEgresos());
		entidadBase.agregarOperacionDeEgreso(operacion4);

	}
	
	@Test(expected = LaEntidadBaseNoPuedeAsociarseALaEntidadJuridicaException.class)
	public void noSePuedeAsociarUnaEntidadBaseAUnaEntidadJuridicaEspecifica() {
		EntidadBase entidadBase = armarEntidadBase();
		entidadBase.setCategoriaEntidad(crearCategoriaQueReestringeAsociarseAUnaJuridica());
		entidadBase.asociarAEntidadJuridica(entidadJuridica1);
	}
	
	@Test(expected = LaEntidadJuridicaNoAdmiteEntidadesBaseException.class)
	public void noSePuedenAsociarEntidadesBaseAUnaDeterminadaEntidadJuridica() {
		
		EntidadJuridica entidadJuridica = armarEntidadJuridica();
		entidadJuridica.setCategoriaEntidad(crearCategoriaQueImpideAUnaJuridicaAsociarBases());
		entidadJuridica.asociarEntidadBase(entidadBase1);
	}
	
	
	//TODO: Nico | Ver con el grupo.
	/*
	@Test(expected = LaCantidadDeEgresosSuperaElMontoMaximoException.class)
	public void elComportamientoDeUnaEntidadVariaAlModificarSuCategoria() {	
		OperacionDeEgreso operacion4 = Mockito.mock(OperacionDeEgreso.class);
		OperacionDeEgreso operacion5 = Mockito.mock(OperacionDeEgreso.class);
		
		Mockito.when(operacion4.valorTotal()).thenReturn(new BigDecimal(300.0));
		Mockito.when(operacion5.valorTotal()).thenReturn(new BigDecimal(3.0));
		
		EntidadBase entidadBase = armarEntidadBase();
		entidadBase.egresos.stream().forEach(egreso -> Mockito.when(egreso.valorTotal()).thenReturn(new BigDecimal(50.0)));
		
		entidadBase.setCategoriaEntidad(categoriaSinComportamiento);
		entidadBase.agregarOperacionDeEgreso(operacion4);
		Assert.assertEquals(entidadBase.gastosTotales(), new BigDecimal(450));
		RepositorioCategoriasDeEntidades.instance().modificarCategoria(crearListaDeValidacionesEgresos(), "categoriasincomportamiento");
		entidadBase.agregarOperacionDeEgreso(operacion5);
	}*/
}	
