package repositorios;

import java.util.ArrayList;
import java.util.List;

import model.EtiquetaOperacion;

public class RepositorioEtiquetas {
	public List<EtiquetaOperacion> etiquetasDelSistema = new ArrayList<>();
	private static final RepositorioEtiquetas INSTANCE = new RepositorioEtiquetas();
	
	public static final RepositorioEtiquetas instance() {
		return INSTANCE;
	}
	
	void crearNuevaEtiqueta(String texto) {
		etiquetasDelSistema.add(new EtiquetaOperacion(texto));
	}
	
	void eliminarEtiqueta(String texto) {
		etiquetasDelSistema.removeIf(etiqueta -> etiqueta.texto.equals(texto.toUpperCase()));
	}

}
