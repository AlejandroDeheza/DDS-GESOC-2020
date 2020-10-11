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
	
	private static final RepositorioCompras INSTANCE = new RepositorioCompras();
	
	public static final RepositorioCompras instance() {
		return INSTANCE;
	}
	
	public void persistirCompra(OperacionDeEgreso compra) {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		em.persist(compra);
	}
	
	public void agregarCompras(List<OperacionDeEgreso> compras){
		compras.stream().forEach(compra -> this.persistirCompra(compra));
	}
	
	public List<OperacionDeEgreso> obtenerTodasLasOperaciones(){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<OperacionDeEgreso> compras = session.createQuery("FROM OperacionDeEgreso").list();
		return compras;
	}
	
	public List<OperacionDeEgreso> obtenerOperaciones(String condicion){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<OperacionDeEgreso> compras = session.createQuery("FROM OperacionDeEgreso WHERE " + condicion).list();
		return compras;
	}
	
	public void actualizarOperaciones() {
		//TODO - Ver como hacemos esto 
	}
	
	//Extraer a objeto validador de OperacionesDeEgreso.
	/*public void validarComprasPendientes() {	
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
	}*/
}
