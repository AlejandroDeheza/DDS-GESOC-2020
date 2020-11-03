package CodigoAuxiliar;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.*;

import ClasePrincipal.LlenarBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import comportamientoEntidad.Comportamiento;
import model.*;
import paymentMethods.IDMedioDePago;
import repositorios.RepositorioCategoriasDeEntidades;
import repositorios.RepositorioCompras;
import ubicacion.*;
import usuarios.Mensaje;
import usuarios.TipoUsuario;
import usuarios.Usuario;
import validacionesOperaciones.*;


public class Prueba extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Before
	public void init() {
	   
	}

	@Test
	public void updateEstadoOperacion() {

		List<OperacionDeEgreso> oEgreso = new LlenarBase().crearLista3Operaciones();

		RepositorioCompras.instance().agregarCompra(oEgreso.get(0));

		RepositorioCompras.instance().agregarCompra(oEgreso.get(1));
	
		RepositorioCompras.instance().agregarCompra(oEgreso.get(2));
		
		//Usuario usuario = crearUsuario("Jorge");
	    /*	
		EntityManager em = Persistence.createEntityManagerFactory("db").createEntityManager();
		 
		em.getTransaction().begin();
		//em.persist(usuario);
		SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
		Session session = sessionFactory.openSession();
		List<Usuario> usuarios = session.createQuery("FROM Usuario WHERE username = 'Mario'").list();
		Usuario u = usuarios.get(0);
		u.setUsername("Jorge");
		em.merge(u);
		
		em.getTransaction().commit();*/
		
	}
}