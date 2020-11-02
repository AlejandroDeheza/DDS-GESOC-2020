package repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.*;
import usuarios.Usuario;

import javax.persistence.Persistence;

public class RepositorioUsuarios implements WithGlobalEntityManager {

    private static final RepositorioUsuarios INSTANCE = new RepositorioUsuarios();

    public static final RepositorioUsuarios instance() {
        return INSTANCE;
    }

    public void agregarUsuarios(List<Usuario> usuarios){
        usuarios.stream().forEach(usuario -> this.agregarUsuario(usuario));
    }

    public void agregarUsuario(Usuario usuario) {
        entityManager().getTransaction().begin();
        entityManager().persist(usuario);
        entityManager().getTransaction().commit();
    }

    public List<Usuario> obtenerTodosLosUsuarios(){
        SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        List<Usuario> usuarios = session.createQuery("FROM Usuario").list();
        return usuarios;
    }

    public List<Usuario> obtenerUsuarios(String condicion){
        SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        List<Usuario> usuarios = session.createQuery("FROM Usuario WHERE " + condicion).list();
        return usuarios;
    }
    public void eliminarUsuario(Usuario usuario) {
        entityManager().remove(usuario);
    }

}