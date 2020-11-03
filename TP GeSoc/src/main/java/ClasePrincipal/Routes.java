package ClasePrincipal;
import controllers.*;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import repositorios.RepositorioUsuarios;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import usuarios.BuilderUsuario;
import usuarios.TipoUsuario;
import usuarios.Usuario;

public class Routes implements WithGlobalEntityManager, TransactionalOps {
	public static void main(String[] args) {
		System.out.println("Iniciando servidor");

		Spark.port(8080);
		Spark.staticFileLocation("/resources/Public");

		//Lleno la base con datos iniciales
//		new Bootstrap().run();

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
		Spark.get("/organizaciones/:idOrg", (request, response) -> organizacionController.getOrganizacion(request, response), engine);
		Spark.get("/organizaciones/:idOrg/entidades", (request, response) -> entidadController.getEntidades(request,response), engine);
		Spark.get("/organizaciones/:idOrg/entidades/nueva", (request, response) -> entidadController.getFormEntidades(request,response), engine);
		Spark.get("/organizaciones/:idOrg/entidades/:idEntidad", (request, response) -> entidadController.getEntidad(request,response), engine);
		Spark.get("/organizaciones/:idOrg/entidades/:idEntidad/operaciones", (request, response) -> operacionController.getOperaciones(request,response), engine);
		Spark.get("/organizaciones/:idOrg/entidades/:idEntidad/operaciones/:idOperacion", (request, response) -> operacionController.getOperacion(request,response), engine);
		Spark.get("/inbox", (request, response) -> usuarioController.getBandejaDeMensajes(request,response),engine);


		//Cuando quieras crear una entidad se te hace un display de las categorias existentes.
		//Si queres agregar una categoria habria un boton de agregar.


		//Creacion de objetos
		Spark.get("/organizaciones/:idOrg/entidades/:idEntidad/operaciones/nueva", (request, response) -> operacionController.getFormOperaciones(), engine);
		Spark.get("/organizaciones/nueva", (request, response) -> organizacionController.getFormOrganizaciones(), engine);

		Spark.post("/organizaciones/:idOrg/entidades", (request, response) -> entidadController.crearEntidad(request,response), engine);
		//TODO -- Hacer el handleSession cuando Roly suba lo suyo.
		Spark.post("/login", (request, response) -> usuarioController.handleSession(request,response), engine);
	     
	}
}
