package model;

import java.util.ArrayList;
import java.util.List;

public class EtiquetasDeOperaciones {
	public List<EtiquetaOperacion> etiquetasDelSistema = new ArrayList<>();
	private static final EtiquetasDeOperaciones INSTANCE = new EtiquetasDeOperaciones();
	
	public static final EtiquetasDeOperaciones instance() {
		return INSTANCE;
	}
	
	void crearNuevaEtiqueta(String texto) {
		etiquetasDelSistema.add(new EtiquetaOperacion(texto));
	}
	
	void eliminarEtiqueta(String texto) {
		etiquetasDelSistema.removeIf(etiqueta -> etiqueta.texto.equals(texto.toUpperCase()));
	}

}
