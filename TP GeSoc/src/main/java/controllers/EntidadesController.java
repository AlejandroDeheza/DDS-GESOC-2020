package controllers;

import repositorios.RepositorioEntidades;
import repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class EntidadesController {
    public ModelAndView getFormEntidades() {
        return null;
    }

    public ModelAndView getEntidades(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("entidades", RepositorioEntidades.instance().obtenerEntidades("id_organizacion = " + request.params(":idOrg") ));

        return new ModelAndView(modelo, "entidades.html.hbs");
    }

    public ModelAndView getEntidad(Request request, Response response) {
        return null;
    }
}
