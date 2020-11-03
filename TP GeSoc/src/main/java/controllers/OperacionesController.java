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
        Map<String, Object> modelo = new HashMap<>();
        //Obtener del repo las operaciones relacionadas a la entidad actual
        modelo.put("operaciones", RepositorioCompras.instance().obtenerOperaciones("entidad = " + request.params(":idEntidad")));

        return new ModelAndView(modelo, "operaciones.html.hbs");
    }

    public ModelAndView getFormOperaciones(Request request, Response response){
        if(!estaLogueado(request, response)){
            response.redirect("/login");
        }
        return new ModelAndView(null,"crearOperaciones.html.hbs");
    }

    public Object getOperacion(Request request, Response response, TemplateEngine engine) {

        Usuario usuario = getUsuarioLogueado(request);

        if(usuario != null){
            response.redirect("/login");
        }

        int idOperacion = Integer.parseInt(request.queryParams(":idOperacion"));

        try{
            OperacionDeEgreso compra = RepositorioCompras.instance().buscar(idOperacion);
            return compra != null ?
                    engine.render(new ModelAndView(compra, "detalle-operacion.html.hbs"))
                    : null;
        } catch(NumberFormatException e){
            response.status(400);
            System.out.println("El id ingresado (" + idOperacion +") no es un número");
            return "Bad Request";
        }
    }

    private boolean estaLogueado(Request request, Response response) {
        Usuario usuario = getUsuarioLogueado(request);

        return usuario != null;
    }

    private Usuario getUsuarioLogueado(Request request) {
        Long idUsuario = request.session().attribute("idUsuario");

        Usuario usuario = null;

        if(idUsuario != null){
            usuario = RepositorioUsuarios.instance().obtenerUsuario(idUsuario);
        }

        return usuario;
    }
}
