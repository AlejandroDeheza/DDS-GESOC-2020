package CodigoAuxiliar;

import java.util.List;

import ClasePrincipal.Bootstrap;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import model.*;
import repositorios.RepositorioCompras;


public class Prueba extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Before
	public void init() {
	   
	}

	@Test
	public void updateEstadoOperacion() {

		List<OperacionDeEgreso> oEgreso = new Bootstrap().crearLista3Operaciones();

		RepositorioCompras.instance().agregarCompra(oEgreso.get(0));

		RepositorioCompras.instance().agregarCompra(oEgreso.get(1));
	
		RepositorioCompras.instance().agregarCompra(oEgreso.get(2));
		
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