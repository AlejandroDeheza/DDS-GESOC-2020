package repositorios;

import model.OperacionDeEgreso;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.Organizacion;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class RepositorioOrganizaciones implements WithGlobalEntityManager {

    private static final RepositorioCompras INSTANCE = new RepositorioCompras();

    public static final RepositorioCompras instance() {
        return INSTANCE;
    }

    public void agregarOrganizaciones(List<Organizacion> organizaciones){
        organizaciones.stream().forEach(organizacion -> this.agregarOrganizacion(organizacion));
    }

    public List<Organizacion> obtenerTodasLasOrganizaciones(){
        SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        List<Organizacion> organizaciones = session.createQuery("FROM Organizacion").list();
        return organizaciones;
    }

    public List<Organizacion> obtenerOrganizaciones(String condicion){
        SessionFactory sessionFactory = entityManager().unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        List<Organizacion> organizaciones = session.createQuery("FROM Organizacion WHERE " + condicion).list();
        return organizaciones;
    }
    public void agregarOrganizacion(Organizacion organizacion) {
        entityManager().persist(organizacion);
    }


    public void eliminarOrganizacion(Organizacion organizacion) {
        entityManager().remove(organizacion);
    }

}