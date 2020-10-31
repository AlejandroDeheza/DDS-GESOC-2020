package controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UsuariosController {


    public ModelAndView getFormLogin() {
        return new ModelAndView(null,"login.html.hbs");
    }

    public ModelAndView handleSesion(){
        return new ModelAndView(null,null);
    }

    public ModelAndView getBandejaDeMensajes(Request request, Response response) {
        return null;
    }
}
