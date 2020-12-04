package repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.Entidad;
import organizacion.EntidadBase;
import organizacion.EntidadJuridica;

import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

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


	public List<EntidadJuridica> obtenerTodasLasEntidadesJuridicas(){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<EntidadJuridica> entidadesJuridicas = session.createQuery("FROM EntidadJuridica").list();
		return entidadesJuridicas;
	}

	public List<EntidadBase> obtenerTodasLasEntidadesBase(){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<EntidadBase> entidadesBase = session.createQuery("FROM EntidadBase").list();
		return entidadesBase;
	}

	public List<Entidad> obtenerEntidades(String condicion){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<Entidad> entidades = new ArrayList<>();

		entidades.addAll(session.createQuery("FROM Entidad WHERE " + condicion).list());

		return entidades;
	}

	public void actualizarEntidadBase(EntidadBase entidad) {
		entityManager().merge(entidad);
	}
	public void actualizarEntidadJuridica(EntidadJuridica entidad) {
		entityManager().merge(entidad);
	}
	public void actualizarEntidad(Entidad entidad) {
		entityManager().merge(entidad);
	}

	public Entidad obtenerEntidad(long id){
		entityManager().clear();
		return entityManager().find(Entidad.class,id);
	}

	public EntidadBase obtenerEntidadBase(long id){
		entityManager().clear();
		return entityManager().find(EntidadBase.class,id);
	}

	public EntidadJuridica obtenerEntidadJuridica(long id){
		entityManager().clear();
		return entityManager().find(EntidadJuridica.class,id);
	}

	public void agregarEntidad(Entidad entidad) {
		entityManager().persist(entidad);
	}

	public void eliminarEntidad(Entidad entidad) {
		entityManager().remove(entidad);
	}

}
