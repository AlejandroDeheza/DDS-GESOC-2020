package repositorios;

import model.EtiquetaOperacion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEtiquetas implements WithGlobalEntityManager {
	//public List<EtiquetaOperacion> etiquetasDelSistema = new ArrayList<>();
	private static final RepositorioEtiquetas INSTANCE = new RepositorioEtiquetas();
	
	public static final RepositorioEtiquetas instance() {
		return INSTANCE;
	}
	
	public void crearNuevaEtiqueta(String texto) {
		EtiquetaOperacion etiqueta = new EtiquetaOperacion(texto);
		entityManager().persist(etiqueta);
	}
	
	public void agregarNuevaEtiqueta(EtiquetaOperacion nuevaEtiqueta) {
		entityManager().persist(nuevaEtiqueta);
	}
	
	/*public void modificarEtiqueta(String textoOriginal, String textoNuevo) {
		EtiquetaOperacion etiquetaAModificar = etiquetasDelSistema.stream().filter(etiqueta -> etiqueta.texto.equals(textoOriginal.toUpperCase())).collect(Collectors.toList()).get(0);
		etiquetaAModificar.setTexto(textoNuevo);
	}*/

	public List<EtiquetaOperacion> obtenerTodasLasEtiquetas(){
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List rows = session.createSQLQuery("SELECT DISTINCT etiqueta FROM etiquetas_operaciones").list();
		List<EtiquetaOperacion> etiquetas = new ArrayList<>();
		rows.forEach (
			row -> {
				EtiquetaOperacion etiquetaOperacion = new EtiquetaOperacion();
				etiquetaOperacion.setTexto(row.toString());
				etiquetas.add(etiquetaOperacion);
			}
		);
		return etiquetas;
	}

	public void modificarEtiqueta(EtiquetaOperacion etiqueta) {
		entityManager().merge(etiqueta);
	}
	
	public void eliminarEtiqueta(EtiquetaOperacion etiqueta) {
		entityManager().remove(etiqueta);
	}

	public EtiquetaOperacion encontrarEtiqueta(String texto) {
		return entityManager().find(EtiquetaOperacion.class,texto);
	}

}
