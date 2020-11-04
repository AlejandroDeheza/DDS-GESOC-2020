package repositorios;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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
        entityManager().persist(usuario);
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
//    public Object obtenerUsuarioSegunNombre(String nombre){
//        SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
//        Session session = sessionFactory.openSession();
//        Query query = session.createQuery("FROM Usuario u WHERE u.nombre_usuario=:user").setParameter("user", nombre);
//        Object usuario = query.uniqueResult();
//        return usuario;
//    }

    public void eliminarUsuario(Usuario usuario) {
        entityManager().remove(usuario);
    }

    public Usuario obtenerUsuario(long id){
        return entityManager().find(Usuario.class, id);
    }

}