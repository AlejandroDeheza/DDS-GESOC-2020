package controllers;

import repositorios.RepositorioOrganizaciones;
import repositorios.RepositorioUsuarios;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import usuarios.Hasher;
import usuarios.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuariosController {


    public ModelAndView getFormLogin() {
        return new ModelAndView(null,"login.html.hbs");
    }

    public ModelAndView handleSession(Request request, Response response){

        String password = request.queryParams("password");
        String username = request.queryParams("username");

        List<Usuario> posibleUsuario = RepositorioUsuarios.instance().obtenerUsuarios("nombre_usuario = " + username);

        if(posibleUsuario.isEmpty()) {
            //TODO HTTP Error code y redirect a pagina de error
            //Por ahora
            System.out.println("No existe el usuario");
            return null;
        }

        Usuario usuario = posibleUsuario.stream().findFirst().get();

        if(!Hasher.sonCorrespondientes(password, usuario.getHashedPasswordActual())) {
            //TODO HTTP Error code y redirect a pagina de error
            //Por ahora
            System.out.print("password incorrecta");
            return null;
        }

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
