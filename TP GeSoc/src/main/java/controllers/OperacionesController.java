package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class OperacionesController {


    public ModelAndView getOperaciones() {
        //Pide al repo las operaciones
        //Las lista? xd

        return new ModelAndView(null, "");
    }

    public ModelAndView getFormOperaciones(){

        return new ModelAndView(null,"crearOperaciones.html.hbs");
    }

    public ModelAndView getOperacion(Request request, Response response) {
        return null;
    }
}
