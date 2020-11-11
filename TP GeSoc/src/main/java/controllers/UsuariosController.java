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
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UsuariosController {


    public ModelAndView getFormLogin(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();
        if(!request.params().isEmpty() && request.params(":status").equals("failed")){
            modelo.put("loginFailed", true);
        }
        else
        {
            modelo.put("loginFailed", false);
        }
        return new ModelAndView(modelo,"login.html.hbs");
    }

    public ModelAndView closeSession(Request request, Response response){

        request.session().removeAttribute("idUsuario");
        response.redirect("/");
        return null;
    }

    public ModelAndView handleSession(Request request, Response response){

        String password = request.queryParams("password");
        String username = request.queryParams("username");

        List<Usuario> posibleUsuario = RepositorioUsuarios.instance().obtenerUsuarios("nombre_usuario = '" + username + "'");

        if(posibleUsuario.isEmpty()) {
            //TODO HTTP Error code y redirect a pagina de error
            //Por ahora
            response.redirect("/login/failed");
            return null;
        }

        Usuario usuario = posibleUsuario.stream().findFirst().get();

        String[] passConSalt = new String[2];
        passConSalt[0] = usuario.getHashedPasswordActual();
        passConSalt[1] = usuario.getSaltActual();

        if(!Hasher.sonCorrespondientes(password,passConSalt)) {
            //TODO HTTP Error code y redirect a pagina de error
            //Por ahora
            response.redirect("/login/failed");
            return null;
        }

        request.session().attribute("idUsuario", usuario.getId());
        response.redirect("/");
        return null;
    }

    public ModelAndView getBandejaDeMensajes(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();
        //Hay que filtrar los usuarios segun el que este logeado
        Usuario usuarioLogeado = null;
        if(estaLogueado(request,response)){
            usuarioLogeado = getUsuarioLogueado(request);
        }
        else{
            response.redirect("/login");
        }

        String filtro = request.queryParams("filtro");

        if(filtro!=null){
            modelo.put("mensajes", usuarioLogeado.getMensajes().stream().
                    filter(m -> m.getCuerpo().contains(filtro) || m.getAsunto().contains(filtro)).collect(Collectors.toList()));
        }else{
            modelo.put("mensajes", usuarioLogeado.getMensajes());
        }

        modelo.put("usuarioLogeado",(new UsuariosController()).getUsuarioLogueado(request));

        return new ModelAndView(modelo, "inbox.html.hbs");
    }

    public Usuario getUsuarioLogueado(Request request) {
        Long idUsuario = request.session().attribute("idUsuario");

        Usuario usuario = null;

        if(idUsuario != null){
            usuario = RepositorioUsuarios.instance().obtenerUsuario(idUsuario);
        }

        return usuario;
    }

    public boolean estaLogueado(Request request, Response response) {
        Usuario usuario = getUsuarioLogueado(request);
        return usuario != null;
    }

}
