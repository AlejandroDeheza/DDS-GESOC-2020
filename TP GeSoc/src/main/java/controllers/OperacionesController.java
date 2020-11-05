package controllers;

import model.OperacionDeEgreso;
import repositorios.RepositorioCompras;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateEngine;
import usuarios.Usuario;

import java.util.HashMap;
import java.util.Map;

public class OperacionesController {


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
        return new ModelAndView(null,"crearOperaciones.html.hbs");
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
                return new ModelAndView(detalleCompra, "detalle-operacion.html.hbs");
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
}
