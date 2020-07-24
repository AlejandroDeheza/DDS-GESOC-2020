import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import model.*;
import organizacion.*;
import repositorios.RepositorioCategoriasDeEntidades;
import validacionesEntidades.ValidacionEntidad;
import validacionesEntidades.ValidarNuevosEgresos;
import validacionesEntidades.ValidarPoderAgregarEntidadesBaseAJuridica;
import validacionesEntidades.ValidarPoderAsociarEntidadBaseAJuridica;

public class TestsCategoriasEntidades {
	
	EntidadBase entidadBase1;
	EntidadJuridica entidadJuridica1;
	CategoriaEntidad categoriaEgresos, categoriaJuridica, categoriaBase, categoriaSinComportamiento;
	
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
	
	public EntidadJuridica armarEntidadJuridica() {
		EntidadJuridica entidadJuridica = new EntidadJuridica();
		
		OperacionDeEgreso operacion1 = crearOperacionDeEgreso(10.0,20.0,30.0);
		operacion1.agregarEtiqueta(new EtiquetaOperacion("A"));
		OperacionDeEgreso operacion2 = crearOperacionDeEgreso(10.0,20.0,30.0);
		operacion2.agregarEtiqueta(new EtiquetaOperacion("B"));
		OperacionDeEgreso operacion3 = crearOperacionDeEgreso(5.0,10.0,20.0);
		operacion3.agregarEtiqueta(new EtiquetaOperacion("A"));
		
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
	public List<ValidacionEntidad> crearListaDeValidacionesEgresos(){
		List<ValidacionEntidad> validaciones = new ArrayList<>();
		validaciones.add(new ValidarNuevosEgresos(new BigDecimal(200)));
		return validaciones;
	}
	public List<ValidacionEntidad> crearListaDeValidacionesJuridica(){
		List<ValidacionEntidad> validaciones = new ArrayList<>();
		validaciones.add(new ValidarPoderAgregarEntidadesBaseAJuridica());
		return validaciones;
	}
	public List<ValidacionEntidad> crearListaDeValidacionesBase(){
		List<ValidacionEntidad> validaciones = new ArrayList<>();
		validaciones.add(new ValidarPoderAsociarEntidadBaseAJuridica(entidadJuridica1));
		return validaciones;
	}
	public List<ValidacionEntidad> crearListaDeValidacionesVacia(){
		List<ValidacionEntidad> validaciones = new ArrayList<>();
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
	public OperacionDeEgreso crearOperacionDeEgreso(Double valorA, Double valorB, Double valorC) {
		return new OperacionDeEgreso(crearListaDeTresItems(valorA,valorB,valorC));
	}
	
	@Before
	public void init() {
		entidadJuridica1 = new EntidadJuridica();
		entidadJuridica1.ID = 1;
		entidadBase1 = new EntidadBase();
		entidadBase1.setCategoriaEntidad(new CategoriaEntidad(crearListaDeValidacionesVacia(),"Categoria_sin_restricciones"));
		
		categoriaEgresos = crearCategoriaQueReestringeEgresos();
		categoriaJuridica = crearCategoriaQueImpideAUnaJuridicaAsociarBases();
		categoriaBase = crearCategoriaQueReestringeAsociarseAUnaJuridica();
		categoriaSinComportamiento = crearCategoriaSinComportamiento();
		
		RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(categoriaEgresos);
		RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(categoriaJuridica);
		RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(categoriaBase);
		RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(categoriaSinComportamiento);
	}
	
	@Test(expected = LaCantidadDeEgresosSuperaElMontoMaximoException.class)
	public void noSePuedeAgregarUnEgresoSiSeSuperaElMontoMaximoAcumulado() {
		EntidadBase entidadBase = armarEntidadBase();
		entidadBase.setCategoriaEntidad(crearCategoriaQueReestringeEgresos());
		entidadBase.agregarOperacionDeEgreso(crearOperacionDeEgreso(100.0,100.0,100.0));
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
		entidadBase1.asociarAEntidadJuridica(entidadJuridica);
	}
	
	@Test(expected = LaCantidadDeEgresosSuperaElMontoMaximoException.class)
	public void elComportamientoDeUnaEntidadVariaAlModificarSuCategoria() {
		EntidadBase entidadBase = armarEntidadBase();
		entidadBase.setCategoriaEntidad(categoriaSinComportamiento);
		entidadBase.agregarOperacionDeEgreso(crearOperacionDeEgreso(100.0,100.0,100.0));
		Assert.assertEquals(entidadBase.gastosTotales(), new BigDecimal(455));
		RepositorioCategoriasDeEntidades.instance().modificarCategoria(crearListaDeValidacionesEgresos(), "categoriasincomportamiento");
		entidadBase.agregarOperacionDeEgreso(crearOperacionDeEgreso(1.0,1.0,1.0));
	}

}
