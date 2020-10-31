package ClasePrincipal;
import controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Routes {
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");
		 
		Spark.port(8080);
		Spark.staticFileLocation("/resources/Public");
	     
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
	     
		HomeController homeController = new HomeController();
		UsuariosController usuarioController = new UsuariosController();
		OperacionesController operacionController = new OperacionesController();
		EntidadesController entidadController = new EntidadesController();
		OrganizacionesController organizacionController = new OrganizacionesController();
	     
		Spark.get("/", (request, response) -> homeController.getHome(), engine);
		Spark.get("/login", (request, response) -> usuarioController.getFormLogin(), engine);

		//Consulta sobre objetos
		Spark.get("/organizaciones", (request, response) -> organizacionController.getOrganizaciones(), engine);
		Spark.get("/organizaciones/:id", (request, response) -> organizacionController.getOrganizacion(request, response), engine);
		Spark.get("/organizaciones/:id/entidades", (request, response) -> entidadController.getEntidades(), engine);
		Spark.get("/organizaciones/:id/entidades/:idEntidad", (request, response) -> entidadController.getEntidad(request,response), engine);
		Spark.get("/organizaciones/:id/entidades/:idEntidad/operaciones", (request, response) -> operacionController.getOperaciones(), engine);
		Spark.get("/organizaciones/:id/entidades/:idEntidad/operaciones/:idOperacion", (request, response) -> operacionController.getOperacion(request,response), engine);
		Spark.get("/inbox", (request, response) -> usuarioController.getBandejaDeMensajes(request,response),engine);

		//Cuando quieras crear una entidad se te hace un display de las categorias existentes.
		//Si queres agregar una categoria habria un boton de agregar.


		//Creacion de objetos
		Spark.get("/operaciones/nueva", (request, response) -> operacionController.getFormOperaciones(), engine);
		Spark.get("/entidades/nueva", (request, response) -> entidadController.getFormEntidades(), engine);
		Spark.get("/organizaciones/nueva", (request, response) -> organizacionController.getFormOrganizaciones(), engine);

		//TODO -- Hacer el handleSession cuando Roly suba lo suyo.
		Spark.post("/login", (request, response) -> usuarioController.handleSesion(), engine);
	     
	}
}
