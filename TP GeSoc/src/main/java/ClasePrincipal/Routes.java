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
		Spark.staticFileLocation("/public");

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
	     
		HomeController homeController = new HomeController();
		UsuariosController usuarioController = new UsuariosController();
		OperacionesController operacionController = new OperacionesController();
		EntidadesController entidadController = new EntidadesController();
		OrganizacionesController organizacionController = new OrganizacionesController();
		ErrorController errorController = new ErrorController();

		//GET
		Spark.get("/", (request, response) -> homeController.getHome(request,response), engine);
		Spark.get("/inbox", (request, response) -> usuarioController.getBandejaDeMensajes(request,response),engine);
		Spark.get("/login", (request, response) -> usuarioController.getFormLogin(request,response), engine);
		Spark.get("/login/:status", (request, response) -> usuarioController.getFormLogin(request,response), engine);
		Spark.get("/organizaciones", (request, response) -> organizacionController.getOrganizaciones(request,response), engine);
		Spark.get("/organizaciones/nueva", (request, response) -> organizacionController.getFormOrganizaciones(request, response), engine);
		Spark.get("/organizaciones/:idOrg", (request, response) -> organizacionController.getOrganizacion(request, response), engine);
		Spark.get("/organizaciones/:idOrg/entidades", (request, response) -> entidadController.getEntidades(request,response), engine);
		Spark.get("/organizaciones/:idOrg/entidades/nueva", (request, response) -> entidadController.getFormEntidades(request,response), engine);
		Spark.get("/organizaciones/:idOrg/entidades/:idEntidad", (request, response) -> entidadController.getEntidad(request,response), engine);
		Spark.get("/organizaciones/:idOrg/entidades/:idEntidad/operaciones", (request, response) -> operacionController.getOperaciones(request,response), engine);
		Spark.get("/organizaciones/:idOrg/entidades/:idEntidad/operaciones/nueva", (request, response) -> operacionController.getFormOperaciones(request,response), engine);
		Spark.get("/organizaciones/:idOrg/entidades/:idEntidad/operaciones/:idOperacion", (request, response) -> operacionController.getOperacion(request,response), engine);
		Spark.get("/organizaciones/:idOrg/entidades/:idEntidad/operaciones/:idOperacion/nuevoPresupuesto", (request, response) -> operacionController.getFormPresupuestos(request,response), engine);


		//Cuando quieras crear una entidad se te hace un display de las categorias existentes.
		//Si queres agregar una categoria habria un boton de agregar.

		//POST
		Spark.post("/login", (request, response) -> usuarioController.handleSession(request,response));
		Spark.get("/cerrarSesion", (request, response) -> usuarioController.closeSession(request,response));

		Spark.post("/organizaciones/:idOrg/entidades", (request, response) -> entidadController.crearEntidad(request,response), engine);
		Spark.post("/organizaciones/:idOrg/entidades/:idEntidad/operaciones", (request, response) -> operacionController.crearOperacion(request,response), engine);

		Spark.post("/organizaciones/:idOrg/entidades/:idEntidad/operaciones/:idOperacion/altaRevisor", (request, response) -> operacionController.agregarRevisor(request,response), engine);
		Spark.post("/organizaciones/:idOrg/entidades/:idEntidad/operaciones/:idOperacion/bajaRevisor", (request, response) -> operacionController.quitarRevisor(request,response), engine);
		Spark.post("/organizaciones/:idOrg/entidades/:idEntidad/operaciones/:idOperacion/nuevoPresupuesto", (request, response) -> operacionController.crearPresupuesto(request,response), engine);

		//ERROR
		Spark.get("/error", (request, response) -> errorController.getError(request, response), engine);
	}
}
