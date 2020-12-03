package repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import organizacion.Organizacion;

import javax.persistence.Persistence;
import java.util.List;

public class RepositorioOrganizaciones implements WithGlobalEntityManager {

    private static final RepositorioOrganizaciones INSTANCE = new RepositorioOrganizaciones();

    public static final RepositorioOrganizaciones instance() {
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
        SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        List<Organizacion> organizaciones = session.createQuery("FROM Organizacion WHERE " + condicion).list();
        return organizaciones;
    }

    public Organizacion obtenerOrganizacion(long id) { return entityManager().find(Organizacion.class,id); }
    public void agregarOrganizacion(Organizacion organizacion) {
        entityManager().persist(organizacion);
    }

    public void actualizarOrganizacion(Organizacion organizacion) {
        entityManager().merge(organizacion);
    }

    public void eliminarOrganizacion(Organizacion organizacion) {
        entityManager().remove(organizacion);
    }

}