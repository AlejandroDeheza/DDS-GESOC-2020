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

    public ModelAndView getEntidades() {


        Map<String, Object> modelo = new HashMap<>();
        //Obtener del repo las entidades donde el id_organizacion coincida con la organizacion en la que se este
        modelo.put("entidades", RepositorioEntidades.instance().obtenerTodasLasEntidades());

        return new ModelAndView(modelo, "entidades.html.hbs");
    }

    public ModelAndView getEntidad(Request request, Response response) {
        return null;
    }
}
