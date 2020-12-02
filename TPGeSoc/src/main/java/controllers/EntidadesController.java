package controllers;

import model.MercadoLibreApi;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import organizacion.*;
import repositorios.RepositorioCategoriasDeEntidades;
import repositorios.RepositorioEntidades;
import repositorios.RepositorioOrganizaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import ubicacion.Direccion;
import ubicacion.DireccionPostal;
import ubicacion.Ubicacion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntidadesController implements WithGlobalEntityManager, TransactionalOps {

    public ModelAndView getFormEntidades(Request request, Response response) {

        checkearUsuarioLogueado(request, response);

        Map<String, Object> modelo = new HashMap<>();
        Organizacion organizacion = RepositorioOrganizaciones.instance().obtenerOrganizacion(Long.parseLong(request.params(":idOrg")));
        modelo.put("organizacion",organizacion);
        //Aca habria que agregar la parte de cada entidad especifica juridica/base
        modelo.put("categoriasDisponibles", RepositorioCategoriasDeEntidades.instance().obtenerTodasLasCategorias());
        modelo.put("categoriasEntidadJuridicaDisponibles", Arrays.asList(CategoriaEntidadJuridica.values()));
        modelo.put("entidadesJuridicasDisponibles", RepositorioEntidades.instance().obtenerTodasLasEntidadesJuridicas());

        modelo.put("usuarioLogeado",(new UsuariosController()).getUsuarioLogueado(request));
        modelo.put("paises", (new MercadoLibreApi()).obtenerListaPaises());
        //TODO - ¿deberia enviarse la info de la API para generar un listado que permita cargar la ubicacion?
        return new ModelAndView(modelo, "formulario-creacion-entidades.html.hbs");
    }

    public ModelAndView getEntidades(Request request, Response response) {

        checkearUsuarioLogueado(request, response);

        Map<String, Object> modelo = new HashMap<>();
        String categoriaSeleccionada = request.queryParams("categoria");
        if(categoriaSeleccionada!=null && !categoriaSeleccionada.equals("Cualquiera")) {
            List<Entidad> entidadesAMostrar = RepositorioEntidades.instance().obtenerEntidades("entidad_organizacion = " + request.params(":idOrg"))
                    .stream().filter(e -> e.getCategoriaEntidad()!=null && e.getCategoriaEntidad().getId() == Long.parseLong(request.queryParams("categoria"))).collect(Collectors.toList());
            modelo.put("entidades",entidadesAMostrar);
        }
        else {
            modelo.put("entidades", RepositorioEntidades.instance().obtenerEntidades("entidad_organizacion = " + request.params(":idOrg")));
        }

        modelo.put("categoriasDisponibles", RepositorioCategoriasDeEntidades.instance().obtenerTodasLasCategorias());
        modelo.put("organizacion",RepositorioOrganizaciones.instance().obtenerOrganizacion(Long.parseLong(request.params(":idOrg"))));

        modelo.put("usuarioLogeado",(new UsuariosController()).getUsuarioLogueado(request));

        return new ModelAndView(modelo, "entidades.html.hbs");
    }

    public ModelAndView getEntidad(Request request, Response response) {

        checkearUsuarioLogueado(request, response);

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
        //modelo.put("idOrg", request.params(":idOrg"));
        modelo.put("idEnt",request.params(":idEntidad"));
        modelo.put("organizacion",RepositorioOrganizaciones.instance().obtenerOrganizacion(Long.parseLong(request.params(":idOrg"))));
        modelo.put("usuarioLogeado",(new UsuariosController()).getUsuarioLogueado(request));

        return new ModelAndView(modelo, "entidad.html.hbs");
    }


    public ModelAndView crearEntidad(Request request, Response response){
        checkearUsuarioLogueado(request, response);

        try {
            String tipo = request.queryParams("tipo");
            String nombreFicticio = request.queryParams("nombreFicticio");
            Long idCategoria = Long.parseLong(request.queryParams("categoria"));

            if (tipo.equals("Base")) {
                EntidadBase nuevaEntidad = new EntidadBase();
                nuevaEntidad.setNombreFicticio(nombreFicticio);
                nuevaEntidad.setCategoriaEntidad(RepositorioCategoriasDeEntidades.instance().buscar(idCategoria));
                nuevaEntidad.setDescripcion(request.queryParams("descripcion-base"));
                if (!request.queryParams("entidad-juridica-asociada").equals("Ninguna")) {
                    nuevaEntidad.setEntidadJuridica(RepositorioEntidades.instance().obtenerEntidadJuridica(Long.parseLong(request.queryParams("entidad-juridica-asociada"))));
                }

                Long idOrganizacion = Long.valueOf(request.params(":idOrg"));
                Organizacion organizacion = RepositorioOrganizaciones.instance().obtenerOrganizaciones("id_organizacion = " + idOrganizacion).get(0);
                organizacion.agregarEntidad(nuevaEntidad);

                withTransaction(() -> {
                    RepositorioEntidades.instance().agregarEntidad(nuevaEntidad);
                    RepositorioOrganizaciones.instance().actualizarOrganizacion(organizacion);
                });

                response.redirect("/organizaciones/" + request.params(":idOrg") + "/entidades/" + nuevaEntidad.getId());

            } else if (tipo.equals("Juridica")) {
                EntidadJuridica nuevaEntidad = new EntidadJuridica();
                nuevaEntidad.setNombreFicticio(nombreFicticio);
                nuevaEntidad.setCategoriaEntidad(RepositorioCategoriasDeEntidades.instance().buscar(idCategoria));
                nuevaEntidad.setCategoriaEntidadJuridica(CategoriaEntidadJuridica.valueOf(request.queryParams("categoria-entidad-juridica")));
                nuevaEntidad.setCodInscIGJ(Integer.parseInt(request.queryParams("codigo-igj-juridica")));
                nuevaEntidad.setCuit(Integer.parseInt(request.queryParams("cuit-juridica")));
                nuevaEntidad.setRazonSocial(request.queryParams("razon-social-juridica"));

                String pais = request.queryParams("pais");
                String provincia = request.queryParams("provincia");
                String ciudad = request.queryParams("ciudad");
                String calle = request.queryParams("calle");
                String altura = request.queryParams("altura");
                String piso = request.queryParams("piso");
                String departamento = request.queryParams("departamento");
                nuevaEntidad.setDireccionPostal(new DireccionPostal(new Direccion(calle, altura, piso, departamento), new Ubicacion(pais, provincia, ciudad)));
                //TODO - Los datos tendrian que salir de la API, ¿habra que hacer alguna conversion aca?
                //RAMA - Podriamos hacer algo con Js para que haya un dropdown list de lo q tenemos de la api.

                Long idOrganizacion = Long.valueOf(request.params(":idOrg"));
                Organizacion organizacion = RepositorioOrganizaciones.instance().obtenerOrganizaciones("id_organizacion = " + idOrganizacion).get(0);
                organizacion.agregarEntidad(nuevaEntidad);

                withTransaction(() -> {
                    RepositorioEntidades.instance().agregarEntidad(nuevaEntidad);
                    RepositorioOrganizaciones.instance().actualizarOrganizacion(organizacion);
                });
                response.redirect("/organizaciones/" + request.params(":idOrg") + "/entidades/" + nuevaEntidad.getId());
            } else {
                response.status(400);
                response.body("Bad Request");
                System.out.println("El tipo de entidad no es correcto");
                response.redirect("/error");
            }
        }
        catch(Exception e) {
                response.status(400);
                response.body("Bad Request");
                System.out.println("Los campos no fueron completados correctamente");
                response.redirect("/error");
                return null;
        }

        return null;
    }

    private void checkearUsuarioLogueado(Request request, Response response) {
        if (!new UsuariosController().estaLogueado(request, response)) {
            response.redirect("/login");
        }
    }
}
