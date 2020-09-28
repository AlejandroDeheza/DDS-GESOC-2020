package db;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import comportamientoEntidad.Comportamiento;
import model.CategoriaEntidad;
import model.EstadoOperacion;
import model.EtiquetaOperacion;
import model.Item;
import model.Moneda;
import model.OperacionDeEgreso;
import model.Presupuesto;
import repositorios.RepositorioCategoriasDeEntidades;
import repositorios.RepositorioCompras;
import usuarios.Mensaje;
import usuarios.TipoUsuario;
import usuarios.Usuario;


public class PersistenceTests extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
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
	     
		 presupuesto1 = new Presupuesto(ListaItems, null,  null); 
		 
		 operacion1 = new OperacionDeEgreso(ListaItems2);
		 operacion1.agregarNuevoPresupuesto(ListaItems, null, null);
		 operacion1.agregarNuevoPresupuesto(ListaItems3, null, null);
	}
	
	/*@Test
	public void contextUp() {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		
		
		Usuario usuario = new Usuario("Pepe",TipoUsuario.ADMIN,"Asd123","qwe");
		usuario.recibirMensaje(new Mensaje("Dando de alta usuario"));
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		List <Comportamiento> comportamientos = new ArrayList<>();
		RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(new CategoriaEntidad (comportamientos,"CatSinComp"));
		
		List<CategoriaEntidad> categoriasDelSistema = RepositorioCategoriasDeEntidades.instance().obtenerCategorias();
		
		Assert.assertEquals(categoriasDelSistema.get(0).descripcion,"CATSINCOMP");
		RepositorioCategoriasDeEntidades.instance().eliminarCategoria("CATSINCOMP");

	}
	*/
	@Test
	public void updateEstadoOperacion() {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
						
		RepositorioCompras.instance().agregarNuevaCompra(operacion1);
				
		Long idDeCompra = operacion1.getId();
		//TODO: validarComprasPendientes tira PersistenObjectException: detached entity passed to persist: model.OperacionDeEgreso
//		RepositorioCompras.instance().validarComprasPendientes();
		
		OperacionDeEgreso operacion2 = em.find(OperacionDeEgreso.class, idDeCompra);
		
		Assert.assertEquals(EstadoOperacion.APROBADA, operacion2.getEstado());	
	}
}
