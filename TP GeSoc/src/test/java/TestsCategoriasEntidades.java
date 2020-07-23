import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import model.*;
import organizacion.*;
import validacionesEntidades.ValidacionEntidad;
import validacionesEntidades.ValidarNuevosEgresos;
import validacionesEntidades.ValidarPoderAgregarEntidadesBaseAJuridica;
import validacionesEntidades.ValidarPoderAsociarEntidadBaseAJuridica;

public class TestsCategoriasEntidades {
	
	EntidadBase entidadBase1;
	EntidadJuridica entidadJuridica1;
	
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
	
	public EntidadJuridica armarEntidadJuridica() {
		EntidadJuridica entidadJuridica = new EntidadJuridica();
		
		OperacionDeEgreso operacion1 = crearOperacionDeEgreso(new BigDecimal(10),new BigDecimal(20),new BigDecimal(30));
		operacion1.agregarEtiqueta(new EtiquetaOperacion("A"));
		OperacionDeEgreso operacion2 = crearOperacionDeEgreso(new BigDecimal(10),new BigDecimal(20),new BigDecimal(30));
		operacion2.agregarEtiqueta(new EtiquetaOperacion("B"));
		OperacionDeEgreso operacion3 = crearOperacionDeEgreso(new BigDecimal(5),new BigDecimal(10),new BigDecimal(20));
		operacion3.agregarEtiqueta(new EtiquetaOperacion("A"));
		
		entidadJuridica.egresos.add(operacion1);
		entidadJuridica.egresos.add(operacion2);
		entidadJuridica.egresos.add(operacion3);
		
		return entidadJuridica;
	}
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
		return new CategoriaEntidad(crearListaDeValidacionesEgresos(),"categoria1");
	}
	public CategoriaEntidad crearCategoriaQueReestringeAsociarseAUnaJuridica() {
		return new CategoriaEntidad(crearListaDeValidacionesBase(),"categoria2");
	}
	public CategoriaEntidad crearCategoriaQueImpideAUnaJuridicaAsociarBases() {
		return new CategoriaEntidad(crearListaDeValidacionesJuridica(),"categoria3");
	}
	public OperacionDeEgreso crearOperacionDeEgreso(BigDecimal valorA, BigDecimal valorB, BigDecimal valorC) {
		return new OperacionDeEgreso(crearListaDeTresItems(valorA,valorB,valorC),null,null,null,null,null,null,null);
	}
	
	@Before
	public void init() {
		entidadJuridica1 = new EntidadJuridica();
		entidadJuridica1.ID = 1;
		entidadBase1 = new EntidadBase();
		entidadBase1.setCategoriaEntidad(new CategoriaEntidad(crearListaDeValidacionesVacia(),"Categoria_sin_restricciones"));
		
	}
	
	@Test(expected = LaCantidadDeEgresosSuperaElMontoMaximoException.class)
	public void noSePuedeAgregarUnEgresoSiSeSuperaElMontoMaximoAcumulado() {
		EntidadBase entidadBase = armarEntidadBase();
		entidadBase.setCategoriaEntidad(crearCategoriaQueReestringeEgresos());
		entidadBase.agregarOperacionDeEgreso(crearOperacionDeEgreso(new BigDecimal(100),new BigDecimal(100),new BigDecimal(100)));
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
	
	//TODO - Agregar test para el repo de categorias

}
