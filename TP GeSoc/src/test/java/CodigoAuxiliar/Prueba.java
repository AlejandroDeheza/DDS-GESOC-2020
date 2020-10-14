package CodigoAuxiliar;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import model.*;
import paymentMethods.IDMedioDePago;
import repositorios.RepositorioCategoriasDeEntidades;
import repositorios.RepositorioCompras;
import ubicacion.*;
import usuarios.Mensaje;
import usuarios.TipoUsuario;
import usuarios.Usuario;
import validacionesOperaciones.*;


public class Prueba extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	
	
	public Item crearItem(String descripcion, Double valor) {
		Moneda moneda = new Moneda(valor,"ARS");
		return new Item(moneda, descripcion);
	}
	
	public List<Item> crearLista3Items(Item item1, Item item2, Item item3){
		List<Item> items = new ArrayList<>();
		items.add(item1);
		items.add(item2);
		items.add(item3);
		return items;
	}
	
	public Proveedor crearProveedor() {
		Direccion direccion = new Direccion("Callao", "1234", "5", "C");
		Ubicacion ubicacion = new Ubicacion("Argentina", "Buenos aires", "CABA");
		DireccionPostal direccionPostal = new DireccionPostal(direccion,ubicacion);
		return new Proveedor("RANDOM PROVIDER S.A.",direccionPostal,12345678);
		
	}
	public Presupuesto crearPresupuesto(List<Item> items) {
		DocumentoComercial documento = new DocumentoComercial(Long.valueOf(5),TipoDocumentoComercial.FACTURA);
		Proveedor proveedor = crearProveedor();
		return new Presupuesto(items,documento,proveedor);
	}
	
	public List<Presupuesto> crearLista3Presupuestos(Presupuesto p1, Presupuesto p2, Presupuesto p3){
		List<Presupuesto> presupuestos = new ArrayList<>();
		presupuestos.add(p1);
		presupuestos.add(p2);
		presupuestos.add(p3);
		return presupuestos;
	}
	
	public Usuario crearUsuario(String username) {
		return new Usuario(username, TipoUsuario.ESTANDAR, "123456",  "asd");
	}

	public List<Usuario> crearLista3Usuarios(String user1, String user2, String user3){
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(crearUsuario(user1));
		usuarios.add(crearUsuario(user2));
		usuarios.add(crearUsuario(user3));
		return usuarios;
	}
	
	public List<ValidacionDeOperaciones> crearListaValidaciones(){
		List<ValidacionDeOperaciones> validaciones = new ArrayList<>();
		validaciones.add(new ValidarQueLaOperacionContengaTodosLosItems());
		validaciones.add(new ValidarQueSeHayaElegidoElPresupuestoMasBarato());
		validaciones.add(new ValidarQueTengaLaSuficienteCantidadDePresupuestos());
		return validaciones;
	}
	
	public List<EtiquetaOperacion> crearListaEtiquetas() {
		List<EtiquetaOperacion> etiquetas = new ArrayList<>();
		etiquetas.add(new EtiquetaOperacion("A"));
		etiquetas.add(new EtiquetaOperacion("B"));
		etiquetas.add(new EtiquetaOperacion("C"));
		return etiquetas;
	}

	@Before
	public void init() {
	   
	}

	@Test
	public void updateEstadoOperacion() {
		/*public OperacionDeEgreso(List<Item> items,
				 DocumentoComercial documentoComercial,
				 LocalDateTime fechaOperacion, 
				 IDMedioDePago medio,
				 Proveedor proveedor, 
				 List<Presupuesto> presupuestos,
				 Presupuesto presupuestoElegido,
				 List<Usuario> revisores,
				 List<ValidacionDeOperaciones> validacionesVigentes,
				 List<EtiquetaOperacion> etiquetas,
				 int presupuestosMinimos,
				 EstadoOperacion estado) {*/

		List<Presupuesto> listaPresupuestos = new ArrayList<>();
		Presupuesto presupuesto = crearPresupuesto(crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)));
		listaPresupuestos.add(presupuesto);
		
		OperacionDeEgreso operacion1 = new OperacionDeEgreso(
				crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)),
				new DocumentoComercial(Long.valueOf(1),TipoDocumentoComercial.FACTURA),
				LocalDateTime.now(),
				IDMedioDePago.ACCOUNT_MONEY,
				crearProveedor(),
				listaPresupuestos,
				presupuesto,
				crearLista3Usuarios("Pepe","Daniela","Jose"),
				crearListaValidaciones(),
				crearListaEtiquetas(),
				1,
				EstadoOperacion.PENDIENTE
				);
	
		List<Presupuesto> listaPresupuestos2 = new ArrayList<>();
		Presupuesto presupuesto2 = crearPresupuesto(crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)));
		listaPresupuestos2.add(presupuesto2);
		
		OperacionDeEgreso operacion2 = new OperacionDeEgreso(
				crearLista3Items(crearItem("X",20.0), crearItem("X",30.0), crearItem("Y",40.0)),
				new DocumentoComercial(Long.valueOf(1),TipoDocumentoComercial.FACTURA),
				LocalDateTime.now(),
				IDMedioDePago.AMEX,
				crearProveedor(),
				listaPresupuestos2,
				presupuesto2,
				crearLista3Usuarios("Pepe","Daniela","Jose"),
				crearListaValidaciones(),
				crearListaEtiquetas(),
				1,
				EstadoOperacion.PENDIENTE
				);
		
		
		List<Presupuesto> listaPresupuestos3 = new ArrayList<>();
		Presupuesto presupuesto3 = crearPresupuesto(crearLista3Items(crearItem("Z",30.0), crearItem("X",30.0), crearItem("Y",40.0)));
		Presupuesto presupuesto4 = crearPresupuesto(crearLista3Items(crearItem("Z",40.0), crearItem("X",30.0), crearItem("Y",40.0)));
		listaPresupuestos3.add(presupuesto3);
		listaPresupuestos3.add(presupuesto4);
		
		OperacionDeEgreso operacion3 = new OperacionDeEgreso(
				crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)),
				new DocumentoComercial(Long.valueOf(1),TipoDocumentoComercial.FACTURA),
				LocalDateTime.now(),
				IDMedioDePago.ARGENCARD,
				crearProveedor(),
				listaPresupuestos3,
				presupuesto4,
				crearLista3Usuarios("Pepe","Daniela","Jose"),
				crearListaValidaciones(),
				crearListaEtiquetas(),
				1,
				EstadoOperacion.PENDIENTE
				);
		
		RepositorioCompras.instance().agregarCompra(operacion1);

		RepositorioCompras.instance().agregarCompra(operacion2);
	
		RepositorioCompras.instance().agregarCompra(operacion3);
		
		//Usuario usuario = crearUsuario("Jorge");
	    /*	
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		 
		em.getTransaction().begin();
		//em.persist(usuario);
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<Usuario> usuarios = session.createQuery("FROM Usuario WHERE username = 'Mario'").list();
		Usuario u = usuarios.get(0);
		u.setUsername("Jorge");
		em.merge(u);
		
		em.getTransaction().commit();*/
		
		
		
		
	}
}