package repositorios;

import java.util.List;

import javax.persistence.Persistence;

import org.hibernate.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import organizacion.CategoriaEntidad;

public class RepositorioCategoriasDeEntidades implements WithGlobalEntityManager {
	//private List<CategoriaEntidad> categoriasDelSistema = new ArrayList<>();
	
	private static final RepositorioCategoriasDeEntidades INSTANCE = new RepositorioCategoriasDeEntidades();
	
	public static final RepositorioCategoriasDeEntidades instance() {
		return INSTANCE;
	}
	

	public void agregarNuevaCategoria(CategoriaEntidad nuevaCategoria) {
		entityManager().persist(nuevaCategoria);
	}
	
	/*public void modificarCategoria(List<Comportamiento> comportamientos, String descripcion) {
		CategoriaEntidad categoriaAModificar = categoriasDelSistema.stream().filter(categoria -> categoria.descripcion.equals(descripcion.toUpperCase())).collect(Collectors.toList()).get(0);
		categoriaAModificar.setComportamientos(comportamientos);
	}*/
	
	public void modificarCategoria(CategoriaEntidad nuevaCategoria) {
		entityManager().merge(nuevaCategoria);
	}
	
	public void eliminarCategoria(CategoriaEntidad nuevaCategoria) {
		entityManager().remove(nuevaCategoria);
	}
	
	public List<CategoriaEntidad> obtenerTodasLasCategorias() {
		
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<CategoriaEntidad> categoriasDelSistema = session.createQuery("FROM CategoriaEntidad").list();
		return categoriasDelSistema;
		
	}
	
	public List<CategoriaEntidad> obtenerCategorias(String query) {
		
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<CategoriaEntidad> categoriasDelSistema = session.createQuery("FROM CategoriaEntidad WHERE " + query).list();
		return categoriasDelSistema;
		
	}

	public CategoriaEntidad buscar(long id){
		return entityManager().find(CategoriaEntidad.class, id);
	}

}
