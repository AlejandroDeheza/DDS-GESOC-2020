package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import model.EtiquetaOperacion;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

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

	public void modificarEtiqueta(EtiquetaOperacion etiqueta) {
		entityManager().merge(etiqueta);
	}
	
	public void eliminarEtiqueta(EtiquetaOperacion etiqueta) {
		entityManager().remove(etiqueta);
	}

}
