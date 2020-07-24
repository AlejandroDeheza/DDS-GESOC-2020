package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.EtiquetaOperacion;

public class RepositorioEtiquetas {
	public List<EtiquetaOperacion> etiquetasDelSistema = new ArrayList<>();
	private static final RepositorioEtiquetas INSTANCE = new RepositorioEtiquetas();
	
	public static final RepositorioEtiquetas instance() {
		return INSTANCE;
	}
	
	public void crearNuevaEtiqueta(String texto) {
		etiquetasDelSistema.add(new EtiquetaOperacion(texto));
	}
	
	public void agregarNuevaEtiqueta(EtiquetaOperacion nuevaEtiqueta) {
		etiquetasDelSistema.add(nuevaEtiqueta);
	}
	
	public void modificarEtiqueta(String textoOriginal, String textoNuevo) {
		EtiquetaOperacion etiquetaAModificar = etiquetasDelSistema.stream().filter(etiqueta -> etiqueta.texto.equals(textoOriginal.toUpperCase())).collect(Collectors.toList()).get(0);
		etiquetaAModificar.setTexto(textoNuevo);
	}
	
	public void eliminarEtiqueta(String texto) {
		etiquetasDelSistema.removeIf(etiqueta -> etiqueta.texto.equals(texto.toUpperCase()));
	}

}
