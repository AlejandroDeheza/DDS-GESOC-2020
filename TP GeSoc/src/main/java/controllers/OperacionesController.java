package controllers;

import medioDePago.MedioDePago;
import model.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizacion.CategoriaEntidadJuridica;
import organizacion.Entidad;
import organizacion.Organizacion;
import paymentMethods.IDMedioDePago;
import repositorios.*;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;
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
        modelo.put("operaciones", RepositorioCompras.instance().obtenerOperaciones("entidad = " + request.params(":idEntidad")));

        return new ModelAndView(modelo, "operaciones.html.hbs");
    }

    public ModelAndView getFormOperaciones(Request request, Response response){
        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("organizacion", request.params(":idOrg"));
        modelo.put("entidad", request.params("idEntidad"));
        modelo.put("tiposDocumentoComercial", Arrays.asList(TipoDocumentoComercial.values()));
        modelo.put("mediosDePago", Arrays.asList(IDMedioDePago.values()));
        modelo.put("proveedores",RepositorioProveedores.instance().obtenerTodosLosProveedores());
        List<EtiquetaOperacion> etiquetas = RepositorioEtiquetas.instance().obtenerTodasLasEtiquetas();
        modelo.put("etiquetas",etiquetas);
        return new ModelAndView(modelo,"formulario-creacion-operaciones.html.hbs");
    }

    public ModelAndView getOperacion(Request request, Response response) {
        if (!new UsuariosController().estaLogueado(request, response)) {
            response.redirect("/login");
            return null;
        }

        Long idOperacion = Long.parseLong(request.params(":idOperacion"));

        try {
            OperacionDeEgreso compra = RepositorioCompras.instance().buscar(idOperacion);
            if (compra != null) {
                Map<String, Object> detalleCompra = new HashMap<>();

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
        Proveedor proveedor = entityManager().find(Proveedor.class, Long.parseLong(request.queryParams("proveedor")));
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
        revisores.add(RepositorioUsuarios.instance().obtenerUsuario(request.session().attribute("idUsuario")));

        EtiquetaOperacion etiqueta = new EtiquetaOperacion(request.queryParams("etiqueta"));
        List<EtiquetaOperacion> etiquetas = new ArrayList<>();
        etiquetas.add(etiqueta);

        List<Item> items = new ArrayList<>();

        instanciarItems(request, response, items);

        /*OperacionDeEgreso nuevaOperacion = new OperacionDeEgreso(null,docComercial,fecha,medioDePago,proveedor,null,
                null,revisores,null,etiquetas,presupuestosMinimos,EstadoOperacion.PENDIENTE);*/
        OperacionDeEgreso nuevaOperacion = new OperacionDeEgreso();
        nuevaOperacion.setDocumentoComercial(docComercial);
        nuevaOperacion.setFechaOperacion(fecha);
        nuevaOperacion.setMedio(medioDePago);
        nuevaOperacion.setProveedor(proveedor);
        nuevaOperacion.setRevisores(revisores);
        nuevaOperacion.setPresupuestosMinimos(presupuestosMinimos);
        nuevaOperacion.setValidacionesVigentes(validacionesActivas);
        nuevaOperacion.setEtiquetas(etiquetas);
        //nuevaOperacion.setEstado(EstadoOperacion.PENDIENTE); Ya se inicializa como PENDIENTE
        nuevaOperacion.setItems(items);


        Long idEntidad = Long.valueOf(request.params(":idEntidad"));
        Entidad entidad = RepositorioEntidades.instance().obtenerEntidad(idEntidad);
        entidad.agregarOperacionDeEgreso(nuevaOperacion);

        withTransaction(() ->{
            RepositorioCompras.instance().agregarCompra(nuevaOperacion);
            RepositorioEntidades.instance().actualizarEntidad(entidad);
        });

        response.redirect("/organizaciones/" + request.params(":idOrg") + "/entidades/" + request.params(":idEntidad") + "/operaciones/" + nuevaOperacion.getId());
        return null;
    }

    private void instanciarItems(Request request, Response response, List<Item> items) {
        for(Integer parametroActual = 0; parametroActual < 10; parametroActual++){
            try { //TODO Ver que onda la moneda
                if (request.queryParams("itemD" + parametroActual).isEmpty() || request.queryParams("itemN" + parametroActual).isEmpty()) {
                    response.status(400); //Bad request
                    // TODO: armar rutas de error.
                    //response.redirect();
                } else {
                    items.add(new Item(new Moneda(Double.parseDouble(request.queryParams("itemN" + parametroActual)), "ARS")
                            , request.queryParams("itemD" + parametroActual)));
                }
            }
            catch(Exception exception){}
        }
    }
}
