package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.CategoriaEntidad;
import model.EstadoOperacion;
import model.OperacionDeEgreso;

public final class RepositorioCompras {
	public List<OperacionDeEgreso> comprasPendientes = new ArrayList<>();
//	public List<OperacionDeEgreso> comprasValidadas = new ArrayList<>();
//	public List<OperacionDeEgreso> comprasAceptadas = new ArrayList<>();
	
	private static final RepositorioCompras INSTANCE = new RepositorioCompras();
	
	public static final RepositorioCompras instance() {
		return INSTANCE;
	}
	
	public void agregarNuevaCompra(OperacionDeEgreso compra) {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		
		em.getTransaction().begin();
		em.persist(compra);
		em.getTransaction().commit();
		
		this.comprasPendientes.add(compra);
	}
	
	public void persistirCompras(List<OperacionDeEgreso> compras){
		
		compras.stream().forEach(compra -> this.agregarNuevaCompra(compra));
	}
	
	public List<OperacionDeEgreso> obtenerOperacionesPendientes(){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<OperacionDeEgreso> compras = session.createQuery("FROM OperacionDeEgreso WHERE estado = 'PENDIENTE'").list();
		
		return compras;
	}
	
	//Extraer a objeto validador de OperacionesDeEgreso.
	public void validarComprasPendientes() {	
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<OperacionDeEgreso> compras = session.createQuery("FROM OperacionDeEgreso WHERE estado = 'PENDIENTE'").list();
		
		compras.stream().forEach(compra -> this.validarCompra(compra));
		
		this.persistirCompras(compras);
	}
	
	public void validarCompra(OperacionDeEgreso compra) {
		if(compra.esValida()) {
			//Aca tenemos que cambiarle el atributo a ACEPTADA.
			compra.setEstado(EstadoOperacion.APROBADA);
			compra.notificarRevisores("La operacion fue validada correctamente");
		}
		else {
			//Aca tenemos que cambiarle el atributo a RECHAZADA.
			compra.setEstado(EstadoOperacion.RECHAZADA);
			
			compra.notificarRevisores("La operacion no es valida");
		}
	}
}
