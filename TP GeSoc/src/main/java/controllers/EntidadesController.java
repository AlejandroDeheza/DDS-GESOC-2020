package controllers;

import model.CategoriaEntidad;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizacion.Entidad;
import organizacion.EntidadBase;
import organizacion.EntidadJuridica;
import organizacion.Organizacion;
import repositorios.RepositorioEntidades;
import repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class EntidadesController implements WithGlobalEntityManager, TransactionalOps {

    public ModelAndView getFormEntidades(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("organizacion",request.params(":idOrg") );
        return new ModelAndView(modelo, "formulario-creacion-entidades.html.hbs");
    }

    public ModelAndView getEntidades(Request request, Response response) {

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("entidades", RepositorioEntidades.instance().obtenerEntidades("id_organizacion = " + request.params(":idOrg") ));

        return new ModelAndView(modelo, "entidades.html.hbs");
    }

    public ModelAndView getEntidad(Request request, Response response) {
        Map<String, Object> modelo = new HashMap<>();
        modelo.put("entidad", RepositorioEntidades.instance().obtenerEntidades("id_entidad = " + request.params(":idEntidad")));
        return new ModelAndView(modelo, "entidad.html.hbs");
    }


    public ModelAndView crearEntidad(Request request, Response response){
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

        /*Usuario usuario = getUsuarioLogueado(request);

        if(usuario != null){
            response.redirect("/login");
        }*/

       /* Entidad nueva = new Consultora(nombre,cantidadEmpleados);
        withTransaction(() ->{
            RepositorioConsultoras.instancia.agregar(nueva);
            usuario.agregarConsultora(nueva);
        });*/

        response.redirect("/organizaciones/" + request.params(":idOrg") + "/entidades/" + nuevaEntidad.getId());
        return null;
    }
}
