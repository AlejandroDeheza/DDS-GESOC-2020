package db;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.junit.Assert;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import comportamientoEntidad.Comportamiento;
import model.CategoriaEntidad;
import model.EtiquetaOperacion;
import repositorios.RepositorioCategoriasDeEntidades;
import usuarios.Mensaje;
import usuarios.TipoUsuario;
import usuarios.Usuario;

public class PersistenceTests extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		
		
		//Usuario usuario = new Usuario("Pepe",TipoUsuario.ADMIN,"Asd123","qwe");
		//usuario.recibirMensaje(new Mensaje("Dando de alta usuario"));
		
		//em.getTransaction().begin();
		//em.persist(usuario);
		//em.getTransaction().commit();
		//List <Comportamiento> comportamientos = new ArrayList<>();
		//RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(new CategoriaEntidad (comportamientos,"CatSinComp"));
		
		//List<CategoriaEntidad> categoriasDelSistema = RepositorioCategoriasDeEntidades.instance().obtenerCategorias();
		
		//Assert.assertEquals(categoriasDelSistema.get(0).descripcion,"CATSINCOMP");
		//RepositorioCategoriasDeEntidades.instance().eliminarCategoria("CATSINCOMP");

	}


}
