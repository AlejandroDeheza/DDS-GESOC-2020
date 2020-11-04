package controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {

    public ModelAndView getHome(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();

        modelo.put("anio", LocalDate.now().getYear());
        modelo.put("usuarioLogeado", (new UsuariosController()).estaLogueado(request,response));

        return new ModelAndView(modelo, "home.html.hbs");
    }
}