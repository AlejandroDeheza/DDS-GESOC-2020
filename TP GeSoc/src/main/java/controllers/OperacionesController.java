package controllers;

import model.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizacion.Entidad;
import paymentMethods.IDMedioDePago;
import repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Usuario;
import validacionesOperaciones.ValidacionDeOperaciones;
import validacionesOperaciones.ValidarQueLaOperacionContengaTodosLosItems;
import validacionesOperaciones.ValidarQueSeHayaElegidoElPresupuestoMasBarato;
import validacionesOperaciones.ValidarQueTengaLaSuficienteCantidadDePresupuestos;

import java.time.LocalDate;
import java.util.*;

public class OperacionesController implements WithGlobalEntityManager, TransactionalOps {


    public ModelAndView getOperaciones(Request request, Response response) {
        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        Map<String, Object> modelo = new HashMap<>();
        //Obtener del repo las operaciones relacionadas a la entidad actual
        modelo.put("operaciones", RepositorioOperaciones.instance().obtenerOperaciones("entidad = " + request.params(":idEntidad")));
        cargarDatosParaHistorico(request, modelo);

        modelo.put("usuarioLogeado",(new UsuariosController()).getUsuarioLogueado(request));

        return new ModelAndView(modelo, "operaciones.html.hbs");
    }

    private void cargarDatosParaHistorico(Request request, Map<String, Object> modelo) {
        modelo.put("organizacionID",request.params(":idOrg"));
        modelo.put("organizacionNombre",RepositorioOrganizaciones.instance().obtenerOrganizacion(Long.parseLong(request.params(":idOrg"))).getNombre());
        modelo.put("entidadID",request.params(":idEntidad"));
        modelo.put("entidadNombre",RepositorioEntidades.instance().obtenerEntidad(Long.parseLong(request.params(":idEntidad"))).getNombreFicticio());
    }

    public ModelAndView getFormOperaciones(Request request, Response response){
        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }
        Map<String, Object> modelo = new HashMap<>();
        cargarDatosParaHistorico(request, modelo);
        modelo.put("organizacion", request.params(":idOrg"));
        modelo.put("entidad", request.params("idEntidad"));
        modelo.put("tiposDocumentoComercial", Arrays.asList(TipoDocumentoComercial.values()));
        modelo.put("mediosDePago", Arrays.asList(IDMedioDePago.values()));
        modelo.put("proveedores",RepositorioProveedores.instance().obtenerTodosLosProveedores());
        List<EtiquetaOperacion> etiquetas = RepositorioEtiquetas.instance().obtenerTodasLasEtiquetas();
        modelo.put("etiquetas",etiquetas);
        modelo.put("usuarioLogeado",(new UsuariosController()).getUsuarioLogueado(request));
        return new ModelAndView(modelo,"formulario-creacion-operaciones.html.hbs");
    }

    public ModelAndView getOperacion(Request request, Response response) {
        if (!new UsuariosController().estaLogueado(request, response)) {
            response.redirect("/login");
            return null;
        }

        Long idOperacion = Long.parseLong(request.params(":idOperacion"));

        try {
            OperacionDeEgreso compra = RepositorioOperaciones.instance().buscar(idOperacion);
            Usuario usuarioLogeado =
                    RepositorioUsuarios.instance().obtenerUsuario(request.session().attribute("idUsuario"));
            if (compra != null) {
                Map<String, Object> detalleCompra = new HashMap<>();
                cargarDatosParaHistorico(request, detalleCompra);

                detalleCompra.put("compra", compra);
                detalleCompra.put("items", compra.getItems());
                detalleCompra.put("documentoComercial", compra.getDocumentoComercial());
                detalleCompra.put("proveedor", compra.getProveedor());
                detalleCompra.put("presupuestos", compra.getPresupuestos());
                detalleCompra.put("presupuestoElegido", compra.getPresupuestoElegido());
                detalleCompra.put("revisores", compra.getRevisores());
                detalleCompra.put("validaciones", compra.getValidaciones());
                detalleCompra.put("etiquetas", compra.getEtiquetas());
                detalleCompra.put("presupuestosMinimos", compra.getPresupuestosMinimos());
                if(compra.getRevisores().stream().anyMatch(revisor -> revisor.getId()==usuarioLogeado.getId())){
                    detalleCompra.put("usuarioEsRevisor", true);
                }
                else{
                    detalleCompra.put("usuarioEsRevisor", false);
                }

                detalleCompra.put("idOrg", request.params(":idOrg"));
                detalleCompra.put("idEntidad", request.params(":idEntidad"));
                detalleCompra.put("idOperacion", request.params(":idOperacion"));

                detalleCompra.put("usuarioLogeado",(new UsuariosController()).getUsuarioLogueado(request));

                return new ModelAndView(detalleCompra, "operacion.html.hbs");
            }
        } catch (NumberFormatException e) {
            response.status(400);
            System.out.println("El id ingresado (" + idOperacion + ") no es un número");
           // return "Bad Request";
            return null;
        }
        //Putea sin este return
        return null;
    }


    public ModelAndView crearOperacion(Request request, Response response){
        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        LocalDate fecha = LocalDate.parse(request.queryParams("fecha"));
        Proveedor proveedor = RepositorioProveedores.instance().buscar(Long.parseLong(request.queryParams("proveedor")));
        DocumentoComercial docComercial = new DocumentoComercial(TipoDocumentoComercial.valueOf(request.queryParams("documentoComercial")));
        IDMedioDePago medioDePago = IDMedioDePago.valueOf(request.queryParams("medioDePago"));
        int presupuestosMinimos = Integer.parseInt(request.queryParams("presupuestosMinimos"));

        List<ValidacionDeOperaciones> validacionesActivas = new ArrayList<>();
        if(request.queryParams("todosLosItems")!=null && request.queryParams("todosLosItems").equals("seleccionado"))
            validacionesActivas.add(new ValidarQueLaOperacionContengaTodosLosItems());
        if(request.queryParams("presupuestoBarato")!=null && request.queryParams("presupuestoBarato").equals("seleccionado"))
            validacionesActivas.add(new ValidarQueSeHayaElegidoElPresupuestoMasBarato());
        if(request.queryParams("cantidadMinima")!=null && request.queryParams("cantidadMinima").equals("seleccionado"))
            validacionesActivas.add(new ValidarQueTengaLaSuficienteCantidadDePresupuestos());

        //Busco la etiqueta en el repositorio y la agrego a la lista.
        //Gonzalo: No hace falta porque esta embebida, lo unico que importa es el texto que tiene
        /*List<EtiquetaOperacion> etiquetas = new ArrayList<EtiquetaOperacion>();
        etiquetas.add(RepositorioEtiquetas.instance().encontrarEtiqueta(request.queryParams("etiqueta")));*/

        // Me agrego por defecto a mi mismo como revisor.
        List<Usuario> revisores = new ArrayList<Usuario>();
        String agregarRevisor = request.queryParams("revisor");
        if(agregarRevisor!=null && agregarRevisor.equals("seleccionado")) {
            revisores.add(RepositorioUsuarios.instance().obtenerUsuario(request.session().attribute("idUsuario")));
        }

        EtiquetaOperacion etiqueta = new EtiquetaOperacion(request.queryParams("etiqueta"));
        List<EtiquetaOperacion> etiquetas = new ArrayList<>();
        etiquetas.add(etiqueta);

        List<Item> items = new ArrayList<>();

        items = obtenerItems(request,"ARS");

        OperacionDeEgreso nuevaOperacion = new OperacionDeEgreso();
        nuevaOperacion.setDocumentoComercial(docComercial);
        nuevaOperacion.setFechaOperacion(fecha);
        nuevaOperacion.setMedio(medioDePago);
        nuevaOperacion.setProveedor(proveedor);
        nuevaOperacion.setRevisores(revisores);
        nuevaOperacion.setPresupuestosMinimos(presupuestosMinimos);
        nuevaOperacion.setValidacionesVigentes(validacionesActivas);
        nuevaOperacion.setEtiquetas(etiquetas);
        nuevaOperacion.setItems(items);


        Long idEntidad = Long.valueOf(request.params(":idEntidad"));
        Entidad entidad = RepositorioEntidades.instance().obtenerEntidad(idEntidad);
        entidad.agregarOperacionDeEgreso(nuevaOperacion);

        withTransaction(() ->{
            RepositorioOperaciones.instance().agregarCompra(nuevaOperacion);
            RepositorioEntidades.instance().actualizarEntidad(entidad);
        });

        Long operacionCreada = nuevaOperacion.getId();
        response.redirect("/organizaciones/" + request.params(":idOrg") + "/entidades/" + request.params(":idEntidad") + "/operaciones/" + operacionCreada);
        //response.redirect("/organizaciones/" + request.params(":idOrg") + "/entidades/" + request.params(":idEntidad") + "/operaciones");
        return null;
    }

    public List<Item> obtenerItems(Request request,String currency) {
        List<Item> items = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            String descripcion = request.queryParams(("itemD"+i));
            String precio = request.queryParams(("itemN"+i));

            if(descripcion != null && precio != null){
                items.add(new Item(new Moneda(Double.parseDouble(precio),currency)
                                  ,descripcion));
            }
        }
        return items;
    }

    public ModelAndView getFormPresupuestos(Request request, Response response){
        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }
        Map<String, Object> modelo = new HashMap<>();
        cargarDatosParaHistorico(request, modelo);

        modelo.put("organizacion", request.params(":idOrg"));
        modelo.put("entidad", request.params(":idEntidad"));
        modelo.put("operacionID", request.params(":idOperacion"));

        modelo.put("tiposDocumentoComercial", Arrays.asList(TipoDocumentoComercial.values()));
        modelo.put("proveedores",RepositorioProveedores.instance().obtenerTodosLosProveedores());

        return new ModelAndView(modelo,"formulario-creacion-presupuestos.html.hbs");
    }


    public ModelAndView crearPresupuesto(Request request, Response response){
        List<Item> items = obtenerItems(request,"ARS");
        DocumentoComercial docComercial = new DocumentoComercial(TipoDocumentoComercial.valueOf(request.queryParams("documentoComercial")));
        Proveedor proveedor = RepositorioProveedores.instance().buscar(Long.parseLong(request.queryParams("proveedor")));

        Presupuesto presupuesto = new Presupuesto(items,docComercial,proveedor);

        OperacionDeEgreso operacionAsociada = RepositorioOperaciones.instance().buscar(Long.parseLong(request.params(":idOperacion")));
        operacionAsociada.agregarPresupuesto(presupuesto);

        withTransaction(() ->{
            RepositorioOperaciones.instance().actualizarCompra(operacionAsociada);
        });

        response.redirect("/organizaciones/" + request.params(":idOrg") + "/entidades/" + request.params(":idEntidad") + "/operaciones/" + operacionAsociada.getId());
        return null;
    }

    public ModelAndView agregarRevisor(Request request, Response response) {

        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        Long idOperacion = Long.parseLong(request.params(":idOperacion"));

        OperacionDeEgreso operacion = RepositorioOperaciones.instance().buscar(idOperacion);
        Usuario usuario = RepositorioUsuarios.instance().obtenerUsuario(request.session().attribute("idUsuario"));
        operacion.agregarRevisor(usuario);

        withTransaction(() ->{
            RepositorioOperaciones.instance().actualizarCompra(operacion);
        });

        response.redirect("/organizaciones/" + request.params(":idOrg") + "/entidades/" + request.params(":idEntidad") + "/operaciones/" + operacion.getId());
        return null;
    }

    public ModelAndView quitarRevisor(Request request, Response response) {

        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        Long idOperacion = Long.parseLong(request.params(":idOperacion"));

        OperacionDeEgreso operacion = RepositorioOperaciones.instance().buscar(idOperacion);
        Usuario usuario = RepositorioUsuarios.instance().obtenerUsuario(request.session().attribute("idUsuario"));
        operacion.quitarRevisor(usuario);

        withTransaction(() ->{
            RepositorioOperaciones.instance().actualizarCompra(operacion);
        });


        response.redirect("/organizaciones/" + request.params(":idOrg") + "/entidades/" + request.params(":idEntidad") + "/operaciones/" + operacion.getId());
        return null;
    }


}
