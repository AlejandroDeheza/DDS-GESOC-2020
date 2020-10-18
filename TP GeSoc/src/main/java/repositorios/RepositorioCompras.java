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
	
	public void agregarCompras(List<OperacionDeEgreso> compras){
		compras.stream().forEach(compra -> this.agregarCompra(compra));
	}
	
	public List<OperacionDeEgreso> obtenerTodasLasOperaciones(){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<OperacionDeEgreso> compras = session.createQuery("FROM OperacionDeEgreso").list();
		//sessionFactory.close();
		//session.close();
		return compras;
	}
	
	public List<OperacionDeEgreso> obtenerOperaciones(String condicion){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<OperacionDeEgreso> compras = session.createQuery("FROM OperacionDeEgreso WHERE " + condicion).list();
		//sessionFactory.close();
		//session.close();
		return compras;
	}
	

	public void agregarCompra(OperacionDeEgreso compra) {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		
		em.getTransaction().begin();
		em.persist(compra);
		em.getTransaction().commit();
		//em.close();
		
	}
	
	public void actualizarCompra(OperacionDeEgreso compra) {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		 
		em.getTransaction().begin();
		em.merge(compra);
		em.getTransaction().commit();
		em.close();
	}
	
	public void eliminarCompra(OperacionDeEgreso compra) {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		 
		em.getTransaction().begin();
		em.remove(compra);
		em.getTransaction().commit();
		//em.close();
	}
	
}
