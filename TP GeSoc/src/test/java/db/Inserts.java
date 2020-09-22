package db;

import static org.junit.Assert.*;

import javax.persistence.*;


import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import usuarios.Mensaje;
import usuarios.TipoUsuario;
import usuarios.Usuario;

public class Inserts extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		
		Usuario usuario = new Usuario("Pepe",TipoUsuario.ADMIN,"Asd123","qwe");
		usuario.recibirMensaje(new Mensaje("Dando de alta usuario"));
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
	}

	

}
