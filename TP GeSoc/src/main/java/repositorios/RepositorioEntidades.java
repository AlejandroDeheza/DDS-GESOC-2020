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

	public List<Entidad> obtenerTodasLasEntidadesDeLaOrganizacion(Long idOrganizacion){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<Entidad> entidades = new ArrayList<>();
		entidades.addAll(this.obtenerTodasLasEntidadesBaseDe(idOrganizacion));
		entidades.addAll(this.obtenerTodasLasEntidadesJuridicasDe(idOrganizacion));
		return entidades;
	}

	public List<EntidadBase> obtenerTodasLasEntidadesBaseDe(Long idOrganizacion){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<EntidadBase> entidadesBase = session.createQuery("FROM EntidadBase INNER JOIN Entidad ON Entidad.id_entidad = EntidadBase.id_entidad WHERE entidad_organizacion = "
				                                      + idOrganizacion).list();
		return entidadesBase;
	}

	public List<EntidadJuridica> obtenerTodasLasEntidadesJuridicasDe(Long idOrganizacion){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<EntidadJuridica> entidadesJuridicas = session.createQuery("FROM EntidadJuridica INNER JOIN Entidad ON Entidad.id_entidad = EntidadJuridica.id_entidad WHERE entidad_organizacion = "
				                                       + idOrganizacion).list();
		return entidadesJuridicas;
	}

	public List<Entidad> obtenerEntidades(String condicion){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<Entidad> entidades = new ArrayList<>();
		entidades.addAll(session.createQuery("FROM Entidad INNER JOIN EntidadBase ON Entidad.id_entidad = EntidadBase.id_entidad WHERE " + condicion).list());
		entidades.addAll(session.createQuery("FROM Entidad INNER JOIN EntidadBase ON Entidad.id_entidad = EntidadJuridica.id_entidad WHERE " + condicion).list());
		return entidades;
	}
	public void agregarEntidad(Entidad entidad) {
		entityManager().persist(entidad);
	}

	public void eliminarEntidad(Entidad entidad) {
		entityManager().remove(entidad);
	}

}
