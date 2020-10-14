package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.EtiquetaOperacion;

public class RepositorioEtiquetas {
	//public List<EtiquetaOperacion> etiquetasDelSistema = new ArrayList<>();
	private static final RepositorioEtiquetas INSTANCE = new RepositorioEtiquetas();
	
	public static final RepositorioEtiquetas instance() {
		return INSTANCE;
	}
	
	public void crearNuevaEtiqueta(String texto) {
		
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		EtiquetaOperacion etiqueta = new EtiquetaOperacion(texto);
	    em.getTransaction().begin();
		em.persist(etiqueta);
		em.getTransaction().commit();
		em.close();
		//etiquetasDelSistema.add(etiqueta);	
	}
	
	public void agregarNuevaEtiqueta(EtiquetaOperacion nuevaEtiqueta) {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		
		em.getTransaction().begin();
		em.persist(nuevaEtiqueta);
		em.getTransaction().commit();
		em.close();
		
		//etiquetasDelSistema.add(nuevaEtiqueta);
	}
	
	/*public void modificarEtiqueta(String textoOriginal, String textoNuevo) {
		EtiquetaOperacion etiquetaAModificar = etiquetasDelSistema.stream().filter(etiqueta -> etiqueta.texto.equals(textoOriginal.toUpperCase())).collect(Collectors.toList()).get(0);
		etiquetaAModificar.setTexto(textoNuevo);
	}*/
	public void modificarEtiqueta(EtiquetaOperacion etiqueta) {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		
		em.getTransaction().begin();
		em.merge(etiqueta);
		em.getTransaction().commit();
		em.close();
	}
	
	public void eliminarEtiqueta(EtiquetaOperacion etiqueta) {
		
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		em.getTransaction().begin();
		//em.createQuery("DELETE FROM EtiquetaOperacion WHERE texto='"+texto+"'").executeUpdate();
		em.remove(etiqueta);
		em.getTransaction().commit();
		em.close();
		//etiquetasDelSistema.removeIf(etiqueta -> etiqueta.texto.equals(texto.toUpperCase()));
		
	}

}
