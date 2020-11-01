package repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.*;

import javax.persistence.Persistence;

public class RepositorioEntidades implements WithGlobalEntityManager {

	private static final RepositorioEntidades INSTANCE = new RepositorioEntidades();
	
	public static final RepositorioEntidades instance() {
		return INSTANCE;
	}

	public void agregarEntidades(List<Entidad> entidades){
		entidades.stream().forEach(entidad -> this.agregarEntidad(entidad));
	}

	public List<Entidad> obtenerTodasLasEntidades(){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<Entidad> entidades = session.createQuery("FROM Entidad").list();
		return entidades;
	}

	public List<Entidad> obtenerEntidades(String condicion){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<Entidad> entidades = session.createQuery("FROM Entidad WHERE " + condicion).list();
		return entidades;
	}
	public void agregarEntidad(Entidad entidad) {
		entityManager().persist(entidad);
	}

	public void eliminarEntidad(Entidad entidad) {
		entityManager().remove(entidad);
	}

}
