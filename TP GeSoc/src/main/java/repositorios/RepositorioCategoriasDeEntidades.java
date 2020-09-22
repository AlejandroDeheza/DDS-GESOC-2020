package repositorios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import comportamientoEntidad.Comportamiento;
import model.CategoriaEntidad;

public class RepositorioCategoriasDeEntidades {
	private List<CategoriaEntidad> categoriasDelSistema = new ArrayList<>();
	
	private static final RepositorioCategoriasDeEntidades INSTANCE = new RepositorioCategoriasDeEntidades();
	
	public static final RepositorioCategoriasDeEntidades instance() {
		return INSTANCE;
	}
	
	/*
	public void crearNuevaCategoria(List<ValidacionEntidad> validaciones, String texto) {
		categoriasDelSistema.add(new CategoriaEntidad(validaciones,texto));
	}*/
	
	public void agregarNuevaCategoria(CategoriaEntidad nuevaCategoria) {
		categoriasDelSistema.add(nuevaCategoria);
	}
	
	public void modificarCategoria(List<Comportamiento> comportamientos, String descripcion) {
		CategoriaEntidad categoriaAModificar = categoriasDelSistema.stream().filter(categoria -> categoria.descripcion.equals(descripcion.toUpperCase())).collect(Collectors.toList()).get(0);
		categoriaAModificar.setComportamientos(comportamientos);
	}
	
	public void eliminarCategoria(String descripcion) {
		categoriasDelSistema.removeIf(categoria -> categoria.descripcion.equals(descripcion.toUpperCase()));
	}

}
