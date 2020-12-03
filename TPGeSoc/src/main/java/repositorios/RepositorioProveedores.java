package repositorios;

import model.Proveedor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.Persistence;
import java.util.List;

public class RepositorioProveedores implements WithGlobalEntityManager {

    private static final RepositorioProveedores INSTANCE = new RepositorioProveedores();

    public static final RepositorioProveedores instance() {
        return INSTANCE;
    }

    public void agregarProveedores(List<Proveedor> proveedores){
        proveedores.stream().forEach(proveedor -> this.agregarProveedor(proveedor));
    }

    public void agregarProveedor(Proveedor proveedor) {
        entityManager().persist(proveedor);
    }

    public List<Proveedor> obtenerTodosLosProveedores(){
        SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        List<Proveedor> proveedores = session.createQuery("FROM Proveedor").list();
        return proveedores;
    }

    public List<Proveedor> obtenerProveedores(String condicion){
        SessionFactory sessionFactory = Persistence.createEntityManagerFactory("db").unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        List<Proveedor> proveedores = session.createQuery("FROM Proveedor WHERE " + condicion).list();
        return proveedores;
    }

    public Proveedor buscar(Long id){
        return entityManager().find(Proveedor.class,id);
    }

    public void actualizarProveedor(Proveedor proveedor) {
        entityManager().merge(proveedor);
    }

    public void eliminarOrganizacion(Proveedor proveedor) {
        entityManager().remove(proveedor);
    }

}