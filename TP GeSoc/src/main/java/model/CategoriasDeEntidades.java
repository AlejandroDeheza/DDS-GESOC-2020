package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoriasDeEntidades {
	private List<CategoriaEntidad> categoriasDelSistema = new ArrayList<>();
	
	private static final EtiquetasDeOperaciones INSTANCE = new EtiquetasDeOperaciones();
	
	public static final EtiquetasDeOperaciones instance() {
		return INSTANCE;
	}
	
	void crearNuevaCategoria(List<ValidacionEntidad> comportamientos, String texto) {
		categoriasDelSistema.add(new CategoriaEntidad(comportamientos,texto));
	}
	
	void modificarCategoria(List<ValidacionEntidad> comportamientos, String texto) {
		CategoriaEntidad entidadAModificar = categoriasDelSistema.stream().filter(categoria -> categoria.texto.equals(texto.toUpperCase())).collect(Collectors.toList()).get(0);
		entidadAModificar.setComportamientos(comportamientos);
	}
	
	void eliminarCategoria(String texto) {
		categoriasDelSistema.removeIf(categoria -> categoria.texto.equals(texto.toUpperCase()));
	}

}
