package ClasePrincipal;
import comportamientoEntidad.Comportamiento;
import comportamientoEntidad.PoderAgregarEgresos;
import comportamientoEntidad.PoderAgregarEntidadesBaseAJuridica;
import model.*;
import organizacion.*;
import paymentMethods.IDMedioDePago;
import repositorios.RepositorioUsuarios;
import ubicacion.Direccion;
import ubicacion.DireccionPostal;
import ubicacion.Ubicacion;
import usuarios.BuilderUsuario;
import usuarios.TipoUsuario;
import usuarios.Usuario;
import validacionesOperaciones.ValidacionDeOperaciones;
import validacionesOperaciones.ValidarQueLaOperacionContengaTodosLosItems;
import validacionesOperaciones.ValidarQueSeHayaElegidoElPresupuestoMasBarato;
import validacionesOperaciones.ValidarQueTengaLaSuficienteCantidadDePresupuestos;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bootstrap {
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

    public Presupuesto crearPresupuesto(List<Item> items) {
        DocumentoComercial documento = new DocumentoComercial(Long.valueOf(5),TipoDocumentoComercial.FACTURA);
        Proveedor proveedor = crearProveedor();
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

    public List<OperacionDeEgreso> crearLista3Operaciones(){
        List<Presupuesto> listaPresupuestos = new ArrayList<>();
        Presupuesto presupuesto = crearPresupuesto(crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)));
        listaPresupuestos.add(presupuesto);

        OperacionDeEgreso operacion1 = new OperacionDeEgreso(
                crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)),
                new DocumentoComercial(Long.valueOf(1),TipoDocumentoComercial.FACTURA),
                LocalDate.now(),
                IDMedioDePago.ACCOUNT_MONEY,
                crearProveedor(),
                listaPresupuestos,
                presupuesto,
                crearLista3Usuarios("Pepe","Daniela","Jose"),
                crearListaValidaciones(),
                crearListaEtiquetas(),
                1,
                EstadoOperacion.PENDIENTE
        );

        List<Presupuesto> listaPresupuestos2 = new ArrayList<>();
        Presupuesto presupuesto2 = crearPresupuesto(crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)));
        listaPresupuestos2.add(presupuesto2);

        OperacionDeEgreso operacion2 = new OperacionDeEgreso(
                crearLista3Items(crearItem("X",20.0), crearItem("X",30.0), crearItem("Y",40.0)),
                new DocumentoComercial(Long.valueOf(1),TipoDocumentoComercial.FACTURA),
                LocalDate.now(),
                IDMedioDePago.AMEX,
                crearProveedor(),
                listaPresupuestos2,
                presupuesto2,
                crearLista3Usuarios("Pepe","Daniela","Jose"),
                crearListaValidaciones(),
                crearListaEtiquetas(),
                1,
                EstadoOperacion.PENDIENTE
        );


        List<Presupuesto> listaPresupuestos3 = new ArrayList<>();
        Presupuesto presupuesto3 = crearPresupuesto(crearLista3Items(crearItem("Z",30.0), crearItem("X",30.0), crearItem("Y",40.0)));
        Presupuesto presupuesto4 = crearPresupuesto(crearLista3Items(crearItem("Z",40.0), crearItem("X",30.0), crearItem("Y",40.0)));
        listaPresupuestos3.add(presupuesto3);
        listaPresupuestos3.add(presupuesto4);

        OperacionDeEgreso operacion3 = new OperacionDeEgreso(
                crearLista3Items(crearItem("Z",20.0), crearItem("X",30.0), crearItem("Y",40.0)),
                new DocumentoComercial(Long.valueOf(1),TipoDocumentoComercial.FACTURA),
                LocalDate.now(),
                IDMedioDePago.ARGENCARD,
                crearProveedor(),
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

    public List<EntidadJuridica> crearLista2Entidades(){
        /*----------------------------------Creacion de Entidades Juridicas----------------------------*/
        List<EntidadJuridica> listaEntidades = new ArrayList<EntidadJuridica>();
        List<Comportamiento> comportamientos1 = new ArrayList<Comportamiento>();
        comportamientos1.add(new PoderAgregarEntidadesBaseAJuridica());

        Direccion direccion1 = new Direccion("Mirta", "1234", "5", "C");
        Ubicacion ubicacion1 = new Ubicacion("Argentina", "Buenos aires", "CABA");
        DireccionPostal direccionPostal1 = new DireccionPostal(direccion1,ubicacion1);
        EntidadJuridica entidad1 = new EntidadJuridica("Pepito",new CategoriaEntidad(comportamientos1,"Poder agregar EBase a EJuridicas"),"Pepito SRL",2020505490,direccionPostal1,1201, CategoriaEntidadJuridica.PEQUENIA_EMPRESA);
        listaEntidades.add(entidad1);

        List<Comportamiento> comportamientos2 = new ArrayList<Comportamiento>();
        comportamientos2.add(new PoderAgregarEgresos());

        Direccion direccion2 = new Direccion("Martines", "1232", "1", "D");
        DireccionPostal direccionPostal2 = new DireccionPostal(direccion2,ubicacion1);
        EntidadJuridica entidad2 = new EntidadJuridica("Martilleros",new CategoriaEntidad(comportamientos2,"Poder agregar egresos"),"Martilleros SA",2012333119,direccionPostal1,1202, CategoriaEntidadJuridica.EMPRESA_MEDIANA_TRAMO_1);
        listaEntidades.add(entidad2);

        return listaEntidades;
    }
    public List<Organizacion> crearLista2Organizaciones(){
        List<Organizacion> listaOrganizaciones = new ArrayList<Organizacion>();

        List<EntidadJuridica> entidades = crearLista2Entidades();

        /*----------------------------------Creacion de Entidad Base-----------------------------------*/
        EntidadBase entidadBase = new EntidadBase("Jueves",null,"Me estoy muriendo del aburrimiento",null);
        entidades.get(1).asociarEntidadBase(entidadBase);

        List<EntidadBase> eBase = new ArrayList<EntidadBase>();
        eBase.add(entidadBase);
        listaOrganizaciones.add(new Organizacion(entidades.subList(0,0),null));

        listaOrganizaciones.add(new Organizacion(entidades.subList(1,1),eBase));
        return listaOrganizaciones;
    }

    private void agregarUsuarios() {
        BuilderUsuario builderUsuario = new BuilderUsuario();
        builderUsuario.setUsername("Goner");
        builderUsuario.setPassword("LaWeaFome123");
        builderUsuario.setTipo(TipoUsuario.ADMIN);
        Usuario nuevoUsuario = builderUsuario.crearUsuario();
        RepositorioUsuarios.instance().agregarUsuario(nuevoUsuario);
    }

    public void run(){
        List<OperacionDeEgreso> operacionDeEgreso = this.crearLista3Operaciones();
        this.agregarUsuarios();
        //Aca si queremos cargar algo a la base tendriamos q llamar al Repositorio y meter cada cosa ahi.
    }
}
