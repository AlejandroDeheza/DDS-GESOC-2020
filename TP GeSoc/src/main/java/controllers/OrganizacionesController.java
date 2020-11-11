package controllers;

import repositorios.RepositorioEntidades;
import repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class OrganizacionesController {

    public ModelAndView getFormOrganizaciones(Request request, Response response) {

        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        return null;
    }

    public ModelAndView getOrganizaciones(Request request, Response response) {

        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        Map<String, Object> modelo = new HashMap<>();
        String filtro = request.queryParams("filtro");
        if(filtro!=null)
        {
            modelo.put("organizaciones", RepositorioOrganizaciones.instance().obtenerTodasLasOrganizaciones()
            .stream().filter(org -> org.getNombre().contains(filtro)).collect(Collectors.toList()));
        }
        else{
            modelo.put("organizaciones", RepositorioOrganizaciones.instance().obtenerTodasLasOrganizaciones());
        }


        return new ModelAndView(modelo, "organizaciones.html.hbs");
    }

    public ModelAndView getOrganizacion(Request request, Response response) {

        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        Map<String, Object> modelo = new HashMap<>();

        modelo.put("organizacion", RepositorioOrganizaciones.instance().obtenerOrganizacion(Long.parseLong(request.params(":idOrg"))));

        modelo.put("entidades", RepositorioEntidades.instance().obtenerEntidades("entidad_organizacion = " + request.params(":idOrg")));

//        modelo.put("entidades", RepositorioEntidades.instance().obtenerEntidades(request.params(":idOrg")));

        return new ModelAndView(modelo, "organizacion.html.hbs");
    }


}
