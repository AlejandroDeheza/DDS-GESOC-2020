package controllers;

import repositorios.RepositorioEntidades;
import repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class OrganizacionesController {

    public ModelAndView getFormOrganizaciones() {
        return null;
    }

    public ModelAndView getOrganizaciones() {

        Map<String, Object> modelo = new HashMap<>();
        //Obtener del repo las organizaciones
        modelo.put("organizaciones", RepositorioOrganizaciones.instance().obtenerTodasLasOrganizaciones());

        return new ModelAndView(modelo, "organizaciones.html.hbs");
    }

    public ModelAndView getOrganizacion(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();

        modelo.put("organizacion", RepositorioOrganizaciones.instance().obtenerOrganizaciones("id_organizacion = " + request.params(":idOrg")));

        modelo.put("entidades", RepositorioEntidades.instance().obtenerEntidades("entidad_organizacion = " + request.params(":idOrg")));

//        modelo.put("entidades", RepositorioEntidades.instance().obtenerEntidades(request.params(":idOrg")));

        return new ModelAndView(modelo, "organizacion.html.hbs");
    }


}
