package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import comportamientoEntidad.Comportamiento;
import model.CategoriaEntidad;

public class RepositorioCategoriasDeEntidades {
	//private List<CategoriaEntidad> categoriasDelSistema = new ArrayList<>();
	
	private static final RepositorioCategoriasDeEntidades INSTANCE = new RepositorioCategoriasDeEntidades();
	
	public static final RepositorioCategoriasDeEntidades instance() {
		return INSTANCE;
	}
	

	public void agregarNuevaCategoria(CategoriaEntidad nuevaCategoria) {

		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		
		em.getTransaction().begin();
		em.persist(nuevaCategoria);
		em.getTransaction().commit();
		//em.close();
		//categoriasDelSistema.add(nuevaCategoria);
	}
	
	/*public void modificarCategoria(List<Comportamiento> comportamientos, String descripcion) {
		CategoriaEntidad categoriaAModificar = categoriasDelSistema.stream().filter(categoria -> categoria.descripcion.equals(descripcion.toUpperCase())).collect(Collectors.toList()).get(0);
		categoriaAModificar.setComportamientos(comportamientos);
	}*/
	
	public void modificarCategoria(CategoriaEntidad nuevaCategoria) {
		
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		
		em.getTransaction().begin();
		em.merge(nuevaCategoria);
		em.getTransaction().commit();
		//em.close();
	}
	
	public void eliminarCategoria(CategoriaEntidad nuevaCategoria) {
		
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		
		em.getTransaction().begin();
		//em.createQuery("DELETE FROM CategoriaEntidad WHERE descripcion='"+descripcion+"'").executeUpdate();
		em.remove(nuevaCategoria);
		em.getTransaction().commit();
		//em.close();
		
		//categoriasDelSistema.removeIf(categoria -> categoria.descripcion.equals(descripcion.toUpperCase()));
	}
	
	public List<CategoriaEntidad> obtenerTodasLasCategorias() {
		
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<CategoriaEntidad> categoriasDelSistema = session.createQuery("FROM CategoriaEntidad").list();
		//sessionFactory.close();
		//session.close();
		return categoriasDelSistema;
		
	}
	
	public List<CategoriaEntidad> obtenerCategorias(String query) {
		
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<CategoriaEntidad> categoriasDelSistema = session.createQuery("FROM CategoriaEntidad WHERE " + query).list();
		//sessionFactory.close();
		//session.close();
		return categoriasDelSistema;
		
	}

}
