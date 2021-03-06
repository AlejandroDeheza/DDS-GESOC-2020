package repositorios;

import model.OperacionDeEgreso;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.Persistence;
import java.util.List;


public final class RepositorioOperaciones implements WithGlobalEntityManager {
	
	private static final RepositorioOperaciones INSTANCE = new RepositorioOperaciones();
	
	public static final RepositorioOperaciones instance() {
		return INSTANCE;
	}
	
	public void agregarCompras(List<OperacionDeEgreso> compras){
		compras.stream().forEach(compra -> this.agregarCompra(compra));
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
	
	public void agregarCompra(OperacionDeEgreso compra) {
		entityManager().persist(compra);
	}
	
	public void actualizarCompra(OperacionDeEgreso compra) {
		entityManager().merge(compra);
	}
	
	public void eliminarCompra(OperacionDeEgreso compra) {
		entityManager().remove(compra);
	}

	public OperacionDeEgreso buscar(long id){
		//Agrego este clear porque nos trajo problemas de consistencia. Ciertas entidades que persistiamos previamente
		//con withTransaction, al usar el find venian con info desactualizada.
		//Aparentemente se soluciona con el after en routes pero no estaria funcionando
		entityManager().clear();
		return entityManager().find(OperacionDeEgreso.class, id);
	}
	
}
