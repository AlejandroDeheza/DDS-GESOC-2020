package ClasePrincipal;

import comportamientoEntidad.Comportamiento;
import comportamientoEntidad.PoderAgregarEgresos;
import comportamientoEntidad.PoderAgregarEntidadesBaseAJuridica;
import model.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizacion.*;
import paymentMethods.IDMedioDePago;
import repositorios.RepositorioCategoriasDeEntidades;
import repositorios.RepositorioOrganizaciones;
import repositorios.RepositorioUsuarios;
import ubicacion.Direccion;
import ubicacion.DireccionPostal;
import ubicacion.Ubicacion;
import usuarios.BuilderUsuario;
import usuarios.Mensaje;
import usuarios.TipoUsuario;
import usuarios.Usuario;
import validacionesOperaciones.ValidacionDeOperaciones;
import validacionesOperaciones.ValidarQueLaOperacionContengaTodosLosItems;
import validacionesOperaciones.ValidarQueSeHayaElegidoElPresupuestoMasBarato;
import validacionesOperaciones.ValidarQueTengaLaSuficienteCantidadDePresupuestos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bootstrap implements WithGlobalEntityManager, TransactionalOps {
    public Item crearItem(String descripcion, Double valor) {
        Moneda moneda = new Moneda(valor,"ARS");
        return new Item(moneda, descripcion);
    }

    public List<Item> crearLista3Items(Item item1, Item item2, Item item3){
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        return items;
    }

    public Proveedor crearProveedor() {
        Direccion direccion = new Direccion("Callao", "1234", "5", "C");
        Ubicacion ubicacion = new Ubicacion("Argentina", "Buenos aires", "CABA");
        DireccionPostal direccionPostal = new DireccionPostal(direccion,ubicacion);
        return new Proveedor("RANDOM PROVIDER S.A.",direccionPostal,12345678);

    }

    public Presupuesto crearPresupuesto(List<Item> items,Proveedor proveedor ){
        DocumentoComercial documento = new DocumentoComercial(Long.valueOf(5),TipoDocumentoComercial.FACTURA);
        return new Presupuesto(items,documento,proveedor);
    }

    public List<Presupuesto> crearLista3Presupuestos(Presupuesto p1, Presupuesto p2, Presupuesto p3){
        List<Presupuesto> presupuestos = new ArrayList<>();
        presupuestos.add(p1);
        presupuestos.add(p2);
        presupuestos.add(p3);
        return presupuestos;
    }

    public Usuario crearUsuario(String username) {
        return new Usuario(username, TipoUsuario.ESTANDAR, "123456",  "asd");
    }

    public List<Usuario> crearLista3Usuarios(String user1, String user2, String user3){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(crearUsuario(user1));
        usuarios.add(crearUsuario(user2));
        usuarios.add(crearUsuario(user3));
        return usuarios;
    }

    public List<ValidacionDeOperaciones> crearListaValidaciones(){
        List<ValidacionDeOperaciones> validaciones = new ArrayList<>();
        validaciones.add(new ValidarQueLaOperacionContengaTodosLosItems());
        validaciones.add(new ValidarQueSeHayaElegidoElPresupuestoMasBarato());
        validaciones.add(new ValidarQueTengaLaSuficienteCantidadDePresupuestos());
        return validaciones;
    }

    public List<EtiquetaOperacion> crearListaEtiquetas() {
        List<EtiquetaOperacion> etiquetas = new ArrayList<>();
        etiquetas.add(new EtiquetaOperacion("A"));
        etiquetas.add(new EtiquetaOperacion("B"));
        etiquetas.add(new EtiquetaOperacion("C"));
        return etiquetas;
    }

    public List<OperacionDeEgreso> crearLista3Operaciones(Proveedor proveedor){
        List<Presupuesto> listaPresupuestos = new ArrayList<>();
        Presupuesto presupuesto = crearPresupuesto(crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)),proveedor);
        listaPresupuestos.add(presupuesto);

        OperacionDeEgreso operacion1 = new OperacionDeEgreso(
                crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)),
                new DocumentoComercial(Long.valueOf(1),TipoDocumentoComercial.FACTURA),
                LocalDate.now(),
                IDMedioDePago.ACCOUNT_MONEY,
                proveedor,
                listaPresupuestos,
                presupuesto,
                crearLista3Usuarios("Pepe","Daniela","Jose"),
                crearListaValidaciones(),
                crearListaEtiquetas(),
                1,
                EstadoOperacion.PENDIENTE
        );

        List<Presupuesto> listaPresupuestos2 = new ArrayList<>();
        Presupuesto presupuesto2 = crearPresupuesto(crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)),proveedor);
        listaPresupuestos2.add(presupuesto2);

        OperacionDeEgreso operacion2 = new OperacionDeEgreso(
                crearLista3Items(crearItem("X",20.0), crearItem("X",30.0), crearItem("Y",40.0)),
                new DocumentoComercial(Long.valueOf(1),TipoDocumentoComercial.FACTURA),
                LocalDate.now(),
                IDMedioDePago.AMEX,
                proveedor,
                listaPresupuestos2,
                presupuesto2,
                crearLista3Usuarios("Pepe","Daniela","Jose"),
                crearListaValidaciones(),
                crearListaEtiquetas(),
                1,
                EstadoOperacion.PENDIENTE
        );


        List<Presupuesto> listaPresupuestos3 = new ArrayList<>();
        Presupuesto presupuesto3 = crearPresupuesto(crearLista3Items(crearItem("Z",30.0), crearItem("X",30.0), crearItem("Y",40.0)),proveedor);
        Presupuesto presupuesto4 = crearPresupuesto(crearLista3Items(crearItem("Z",40.0), crearItem("X",30.0), crearItem("Y",40.0)),proveedor);
        listaPresupuestos3.add(presupuesto3);
        listaPresupuestos3.add(presupuesto4);

        OperacionDeEgreso operacion3 = new OperacionDeEgreso(
                crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)),
                new DocumentoComercial(Long.valueOf(1),TipoDocumentoComercial.FACTURA),
                LocalDate.now(),
                IDMedioDePago.ARGENCARD,
                proveedor,
                listaPresupuestos3,
                presupuesto4,
                crearLista3Usuarios("Pepe","Daniela","Jose"),
                crearListaValidaciones(),
                crearListaEtiquetas(),
                1,
                EstadoOperacion.PENDIENTE
        );

        List<OperacionDeEgreso> oEgreso = new ArrayList<OperacionDeEgreso>();

        oEgreso.add(operacion1);
        oEgreso.add(operacion2);
        oEgreso.add(operacion3);

        return oEgreso;
    }

    public List<EntidadJuridica> crearLista2Entidades(Proveedor proveedor){
        /*----------------------------------Creacion de Entidades Juridicas----------------------------*/
        List<EntidadJuridica> listaEntidades = new ArrayList<EntidadJuridica>();
        List<Comportamiento> comportamientos1 = new ArrayList<Comportamiento>();
        comportamientos1.add(new PoderAgregarEntidadesBaseAJuridica());

        Direccion direccion1 = new Direccion("Mirta", "1234", "5", "C");
        Ubicacion ubicacion1 = new Ubicacion("Argentina", "Buenos aires", "CABA");
        DireccionPostal direccionPostal1 = new DireccionPostal(direccion1,ubicacion1);
        EntidadJuridica entidad1 = new EntidadJuridica("Entidad juridica 1",new CategoriaEntidad(comportamientos1,"Poder agregar entidades base a entidades juridicas"),"Entidad generada a modo de prueba",2020505490,direccionPostal1,1201, CategoriaEntidadJuridica.PEQUENIA_EMPRESA);
        listaEntidades.add(entidad1);

        List<Comportamiento> comportamientos2 = new ArrayList<Comportamiento>();
        comportamientos2.add(new PoderAgregarEgresos(new BigDecimal(2000)));

        Direccion direccion2 = new Direccion("Martines", "1232", "1", "D");
        DireccionPostal direccionPostal2 = new DireccionPostal(direccion2,ubicacion1);
        EntidadJuridica entidad2 = new EntidadJuridica("Entidad juridica 2",new CategoriaEntidad(comportamientos2,"Poder agregar egresos"),"Entidad generada a modo de prueba",2012333119,direccionPostal2,1202, CategoriaEntidadJuridica.EMPRESA_MEDIANA_TRAMO_1);
        listaEntidades.add(entidad2);

        List<OperacionDeEgreso> operacionDeEgreso = this.crearLista3Operaciones(proveedor);
        entidad1.agregarOperacionDeEgreso(operacionDeEgreso.get(0));
        entidad2.agregarOperacionDeEgreso(operacionDeEgreso.get(1));
        entidad2.agregarOperacionDeEgreso(operacionDeEgreso.get(2));

        return listaEntidades;
    }
    public List<Organizacion> crearLista2Organizaciones(Proveedor proveedor){
        List<Organizacion> listaOrganizaciones = new ArrayList<Organizacion>();

        List<EntidadJuridica> entidades = crearLista2Entidades(proveedor);

        /*----------------------------------Creacion de Entidad Base-----------------------------------*/
        EntidadBase entidadBase = new EntidadBase("Entidad base 1",null,"Entidad generada a modo de prueba",null);
        entidades.get(1).asociarEntidadBase(entidadBase);


        List<Entidad> eJuridica = new ArrayList<>();
        eJuridica.add(entidades.get(0));
        List<Entidad> eJuridica2 = new ArrayList<>();
        eJuridica.add(entidades.get(1));
        eJuridica.add(entidadBase);

        listaOrganizaciones.add(new Organizacion("Organización 1","Organización generada a modo de prueba", eJuridica));

        listaOrganizaciones.add(new Organizacion("Organización 2","Organización generada a modo de prueba",eJuridica2));

        return listaOrganizaciones;
    }

    private void agregarUsuarios() {

        BuilderUsuario builderUsuarioRama = new BuilderUsuario();
        builderUsuarioRama.setUsername("Rama");
        builderUsuarioRama.setPassword("Asd123456");
        builderUsuarioRama.setTipo(TipoUsuario.ADMIN);

        BuilderUsuario builderUsuarioNico = new BuilderUsuario();
        builderUsuarioNico.setUsername("Nico");
        builderUsuarioNico.setPassword("Asd123456");
        builderUsuarioNico.setTipo(TipoUsuario.ADMIN);

        BuilderUsuario builderUsuarioEmma = new BuilderUsuario();
        builderUsuarioEmma.setUsername("Emma");
        builderUsuarioEmma.setPassword("Asd123456");
        builderUsuarioEmma.setTipo(TipoUsuario.ADMIN);

        BuilderUsuario builderUsuarioAle = new BuilderUsuario();
        builderUsuarioAle.setUsername("Ale");
        builderUsuarioAle.setPassword("Asd123456");
        builderUsuarioAle.setTipo(TipoUsuario.ADMIN);

        BuilderUsuario builderUsuarioGonza= new BuilderUsuario();
        builderUsuarioGonza.setUsername("Gonza");
        builderUsuarioGonza.setPassword("Asd123456");
        builderUsuarioGonza.setTipo(TipoUsuario.ADMIN);

        Usuario usuarioRama = builderUsuarioRama.crearUsuario();
        Usuario usuarioNico = builderUsuarioNico.crearUsuario();
        Usuario usuarioEmma = builderUsuarioEmma.crearUsuario();
        Usuario usuarioAle = builderUsuarioAle.crearUsuario();
        Usuario usuarioGonza = builderUsuarioGonza.crearUsuario();

        usuarioRama.recibirMensaje(new Mensaje("Bienvenida", "Usuario generado exitosamente", LocalDate.now()));
        usuarioNico.recibirMensaje(new Mensaje("Bienvenida", "Usuario generado exitosamente", LocalDate.now()));
        usuarioEmma.recibirMensaje(new Mensaje("Bienvenida", "Usuario generado exitosamente", LocalDate.now()));
        usuarioAle.recibirMensaje(new Mensaje("Bienvenida", "Usuario generado exitosamente", LocalDate.now()));
        usuarioGonza.recibirMensaje(new Mensaje("Bienvenida", "Usuario generado exitosamente", LocalDate.now()));

        RepositorioUsuarios.instance().agregarUsuario(usuarioRama);
        RepositorioUsuarios.instance().agregarUsuario(usuarioNico);
        RepositorioUsuarios.instance().agregarUsuario(usuarioEmma);
        RepositorioUsuarios.instance().agregarUsuario(usuarioAle);
        RepositorioUsuarios.instance().agregarUsuario(usuarioGonza);
    }

    public void run(){
        Proveedor proveedor = crearProveedor();
        /*withTransaction(() ->{
             List<OperacionDeEgreso> operacionDeEgreso = this.crearLista3Operaciones();
             this.agregarUsuarios();
        });*/

        withTransaction(() -> {
            entityManager().persist(proveedor);

            List<OperacionDeEgreso> operacionDeEgreso = this.crearLista3Operaciones(proveedor);
            this.agregarUsuarios();

            List<Organizacion> organizaciones = crearLista2Organizaciones(proveedor);
            RepositorioOrganizaciones.instance().agregarOrganizaciones(organizaciones);

            //Para probar listado
            CategoriaEntidad cat1 = new CategoriaEntidad();
            CategoriaEntidad cat2 = new CategoriaEntidad();
            CategoriaEntidad cat3 = new CategoriaEntidad();
            cat1.setDescripcion("CAT1");
            cat2.setDescripcion("CAT2");
            cat3.setDescripcion("CAT3");
            RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(cat1);
            RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(cat2);
            RepositorioCategoriasDeEntidades.instance().agregarNuevaCategoria(cat3);
        });
    }
    public static void main(String[] args) {
        (new Bootstrap()).run();
    }
}
