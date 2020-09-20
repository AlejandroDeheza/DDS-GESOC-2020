package db;

import static org.junit.Assert.*;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import usuarios.TipoUsuario;
import usuarios.Usuario;

public class Inserts extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		Usuario usuario1 = new Usuario();
		usuario1.setUsername("Pepe");
		
		entityManager().persist(usuario1);
		entityManager().flush();
		
	}

	

}
