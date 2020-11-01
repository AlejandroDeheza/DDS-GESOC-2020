package controllers;

import repositorios.RepositorioCompras;
import repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class OperacionesController {


    public ModelAndView getOperaciones() {
        Map<String, Object> modelo = new HashMap<>();
        //Obtener del repo las operaciones relacionadas a la entidad actual
        modelo.put("operaciones", RepositorioCompras.instance().obtenerTodasLasOperaciones());

        return new ModelAndView(modelo, "operaciones.html.hbs");
    }

    public ModelAndView getFormOperaciones(){

        return new ModelAndView(null,"crearOperaciones.html.hbs");
    }

    public ModelAndView getOperacion(Request request, Response response) {
        return null;
    }
}
