package controllers;

import model.CategoriaEntidad;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizacion.Entidad;
import organizacion.EntidadBase;
import organizacion.EntidadJuridica;
import organizacion.Organizacion;
import repositorios.RepositorioCategoriasDeEntidades;
import repositorios.RepositorioEntidades;
import repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class EntidadesController implements WithGlobalEntityManager, TransactionalOps {

    public ModelAndView getFormEntidades(Request request, Response response) {

        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("organizacion",request.params(":idOrg"));
        modelo.put("categoriasDisponibles", RepositorioCategoriasDeEntidades.instance().obtenerTodasLasCategorias());
        return new ModelAndView(modelo, "formulario-creacion-entidades.html.hbs");
    }

    public ModelAndView getEntidades(Request request, Response response) {

        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("entidades", RepositorioEntidades.instance().obtenerEntidades("entidad_organizacion = " + request.params(":idOrg") ));

        return new ModelAndView(modelo, "entidades.html.hbs");
    }

    public ModelAndView getEntidad(Request request, Response response) {

        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        Map<String, Object> modelo = new HashMap<>();

        //modelo.put("entidad",RepositorioEntidades.instance().obtenerEntidad(Long.parseLong(request.params(":idEntidad"))));

        EntidadBase entidadBase = RepositorioEntidades.instance().obtenerEntidadBase(Long.parseLong(request.params(":idEntidad")));

        if(entidadBase != null){
            modelo.put("entidadBase",entidadBase);
            modelo.put("categoria",entidadBase.categoriaEntidad);
            modelo.put("entidadJuridicaAsociada", entidadBase.getEntidadJuridica());
        }
        else{
            EntidadJuridica entidadJuridica = RepositorioEntidades.instance().obtenerEntidadJuridica(Long.parseLong(request.params(":idEntidad")));
            modelo.put("entidadJuridica",entidadJuridica);
            modelo.put("categoria",entidadJuridica.categoriaEntidad);
            modelo.put("direccion",entidadJuridica.getDireccionPostal().getDireccion());
            modelo.put("ubicacion",entidadJuridica.getDireccionPostal().getUbicacion());
        }

        return new ModelAndView(modelo, "entidad.html.hbs");
    }


    public ModelAndView crearEntidad(Request request, Response response){

        if(!new UsuariosController().estaLogueado(request,response)){
            response.redirect("/login");
        }

        Long idOrganizacion = Long.valueOf(request.params(":idOrg"));
        String nombre = request.queryParams("nombre");
        String tipo = request.queryParams("tipo");
        String categoria  = request.queryParams("categoria");
        Organizacion organizacion = RepositorioOrganizaciones.instance().obtenerOrganizaciones("id_organizacion = " + idOrganizacion).get(0);

        Entidad nuevaEntidad;

        CategoriaEntidad nuevaCategoria = new CategoriaEntidad();
        nuevaCategoria.setDescripcion(categoria);

        if(tipo.equals("Base")){
             nuevaEntidad = new EntidadBase();
        }
        else{
             nuevaEntidad = new EntidadJuridica();
        }

        nuevaEntidad.setNombreFicticio(nombre);
        nuevaEntidad.setCategoriaEntidad(nuevaCategoria);
        organizacion.agregarEntidad(nuevaEntidad);

        withTransaction(() ->{
            RepositorioEntidades.instance().agregarEntidad(nuevaEntidad);
            RepositorioOrganizaciones.instance().actualizarOrganizacion(organizacion);
        });

       /* Usuario usuario = getUsuarioLogueado(request);

        if(usuario != null){
            response.redirect("/login");
        }

       Entidad nueva = new Consultora(nombre,cantidadEmpleados);
        withTransaction(() ->{
            RepositorioConsultoras.instancia.agregar(nueva);
            usuario.agregarConsultora(nueva);
        });*/

        response.redirect("/organizaciones/" + request.params(":idOrg") + "/entidades/" + nuevaEntidad.getId());
        return null;
    }
}
