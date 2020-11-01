package controllers;

import repositorios.RepositorioOrganizaciones;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class UsuariosController {


    public ModelAndView getFormLogin() {
        return new ModelAndView(null,"login.html.hbs");
    }

    public ModelAndView handleSesion(){
        return new ModelAndView(null,null);
    }

    public ModelAndView getBandejaDeMensajes(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();
        //Hay que filtrar los usuarios segun el que este logeado
        modelo.put("mensajes", RepositorioUsuarios.instance().obtenerTodosLosUsuarios());

        return new ModelAndView(modelo, "inbox.html.hbs");
    }
}
