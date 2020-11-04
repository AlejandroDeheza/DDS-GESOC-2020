package repositorios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.OperacionDeEgreso;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import usuarios.Usuario;


public final class RepositorioCompras implements WithGlobalEntityManager {
	
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

	public OperacionDeEgreso buscar(int id){
		return entityManager().find(OperacionDeEgreso.class, id);
	}
	
}
