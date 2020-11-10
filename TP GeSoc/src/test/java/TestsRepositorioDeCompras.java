import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.mockito.Mockito;

import model.*;
import repositorios.RepositorioOperaciones;
import usuarios.Usuario;
import validacionesOperaciones.ValidacionDeOperaciones;
import validacionesOperaciones.ValidarQueLaOperacionContengaTodosLosItems;

public class TestsRepositorioDeCompras {
	
	OperacionDeEgreso operacionValida1;
	OperacionDeEgreso operacionValida2;;
	OperacionDeEgreso operacionInvalida1;
	OperacionDeEgreso operacionInvalida2;
	OperacionDeEgreso operacionInvalida3;
	
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
	
	public List<Item> crearListaDeOtrosTresItems(Double valorA, Double valorB, Double valorC){
		List <Item> ListaItems = new ArrayList<>();
		Item item1 = new Item(crearMoneda(valorA), "Item X");
		Item item2 = new Item(crearMoneda(valorB), "Item Y");
		Item item3 = new Item(crearMoneda(valorC), "Item Z");

		 ListaItems.add(item1);
		 ListaItems.add(item2);
		 ListaItems.add(item3);
	
		return ListaItems;
	}
	
	public OperacionDeEgreso crearOperacionDeEgreso(Double valorA, Double valorB, Double valorC) {
		OperacionDeEgreso operacion;
		
		List<Item> ListaItems = crearListaDeTresItems(valorA,valorB,valorC);
	     
		 Presupuesto presupuesto1 = new Presupuesto(ListaItems, null,  null); //Solo deberiamos hacer esto en un test...
		 //En el sistema real no deberia ser posible. Así respetamos el punto 2 de la entrega 2.

		 //Mocks
		 DocumentoComercial mockDocComercial = Mockito.mock(DocumentoComercial.class);
		 Proveedor mockProveedor = Mockito.mock(Proveedor.class);
		 
		 Usuario mockUsuario = Mockito.mock(Usuario.class);
		 List<Usuario> mockRevisores = new ArrayList<Usuario>();
		 mockRevisores.add(mockUsuario);
		 
		 ValidarQueLaOperacionContengaTodosLosItems validacion = new ValidarQueLaOperacionContengaTodosLosItems();
		 List<ValidacionDeOperaciones> listaValidacionesParaConstructor = new ArrayList<ValidacionDeOperaciones>();
		 listaValidacionesParaConstructor.add(validacion);
		 
		 EtiquetaOperacion etiqueta = new EtiquetaOperacion("a");
		 List<EtiquetaOperacion> listaEtiquetasParaConstructor = new ArrayList<EtiquetaOperacion>();
		 listaEtiquetasParaConstructor.add(etiqueta);
		 
		 operacion = new OperacionDeEgreso(ListaItems,
				 							null,
				 							null,
				 							null,
				 							null,
				 							new ArrayList<Presupuesto>(),
				 							null,
				 							null,
				 							new ArrayList<ValidacionDeOperaciones>(),
				 							new ArrayList<EtiquetaOperacion>(),
				 							1,
				 							EstadoOperacion.PENDIENTE);
		 
		 operacion.agregarNuevoPresupuesto(presupuesto1);
		 operacion.setPresupuestoElegido(presupuesto1);
		 
		 return operacion;
	}
	
	public Presupuesto crearPresupuesto(List<Item> listaDeItems) {
		return new Presupuesto(listaDeItems,null,null);
	}

	@Before
	public void init() {
		
		operacionValida1 = crearOperacionDeEgreso(5.0,10.0,15.0);
		operacionValida1.agregarNuevoPresupuesto(crearPresupuesto(crearListaDeTresItems(5.0,10.0,15.0)));
		
		operacionValida2 = crearOperacionDeEgreso(5.0,10.0,15.0);
		operacionValida2.agregarNuevoPresupuesto(crearPresupuesto(crearListaDeTresItems(5.0,10.0,15.0)));
		operacionValida2.agregarNuevoPresupuesto(crearPresupuesto(crearListaDeTresItems(5.0,10.0,16.0)));
		
		operacionInvalida1 = crearOperacionDeEgreso(5.0,10.0,15.0);
		
		operacionInvalida2 = crearOperacionDeEgreso(5.0,10.0,16.0);
		operacionInvalida2.agregarNuevoPresupuesto(crearPresupuesto(crearListaDeTresItems(5.0,10.0,15.0)));
		operacionInvalida2.agregarNuevoPresupuesto(crearPresupuesto(crearListaDeTresItems(5.0,10.0,16.0)));
		
		operacionInvalida3 = crearOperacionDeEgreso(5.0,10.0,15.0);
		operacionInvalida3.agregarNuevoPresupuesto(crearPresupuesto(crearListaDeTresItems(5.0,10.0,15.0)));
		operacionInvalida3.agregarNuevoPresupuesto(crearPresupuesto(crearListaDeTresItems(5.0,10.0,16.0)));
		
		RepositorioOperaciones.instance().agregarCompra(operacionInvalida1);
		RepositorioOperaciones.instance().agregarCompra(operacionValida1);
		RepositorioOperaciones.instance().agregarCompra(operacionInvalida3);
		RepositorioOperaciones.instance().agregarCompra(operacionInvalida2);
		RepositorioOperaciones.instance().agregarCompra(operacionValida2);
		
		

	}
	//Comento este test ya que cambio el sistema de validacion.
	
//	@Test
//	public void seApruebanLasOperacionesValidas() {
//		RepositorioCompras.instance().validarComprasPendientes();
//		List<OperacionDeEgreso> listaEsperada = new ArrayList<>();
//		listaEsperada.add(operacionValida1);
//		listaEsperada.add(operacionValida2);
//		Assert.assertEquals(RepositorioCompras.instance().obtenerTodasLasOperaciones("estado_operacion = 'ACEPTADA'"),listaEsperada);
//	}
	
//	@Test
//	public void seRechazanLasOperacionesInvalidas() {
//		RepositorioCompras.instance().validarComprasPendientes();
//		List<OperacionDeEgreso> listaEsperada = new ArrayList<>();
//		listaEsperada.add(operacionInvalida1);
//		listaEsperada.add(operacionInvalida2);
//		Assert.assertEquals(RepositorioCompras.instance().obtenerTodasLasOperaciones("estado_operacion = 'RECHAZADA'"),listaEsperada);
//	}

}
