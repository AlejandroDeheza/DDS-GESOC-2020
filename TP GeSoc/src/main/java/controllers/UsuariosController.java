package controllers;

import repositorios.RepositorioOrganizaciones;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Hasher;
import usuarios.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UsuariosController {


    public ModelAndView getFormLogin() {
        return new ModelAndView(null,"login.html.hbs");
    }

    public ModelAndView handleSession(Request request, Response response){

        String password = request.queryParams("password");
        String username = request.queryParams("username");
        Usuario usuario = RepositorioUsuarios.instance().obtenerTodosLosUsuarios().stream()
                .filter(u -> Hasher.sonCorrespondientes(password,u.getHashedPasswordActual()) && u.getUsername().equals(username)).findFirst().get();

        request.session().attribute("idUsuario", usuario.getId());

        response.redirect("/");

        return null;
    }

    public ModelAndView getBandejaDeMensajes(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();
        //Hay que filtrar los usuarios segun el que este logeado
        modelo.put("mensajes", RepositorioUsuarios.instance().obtenerTodosLosUsuarios());

        return new ModelAndView(modelo, "inbox.html.hbs");
    }
}
