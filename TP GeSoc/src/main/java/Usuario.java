public class Usuario {
	String username;
	String password;
	TipoUsuario tipoUser;
	Usuario(String username, String password, TipoUsuario tipoUser){
		this.username=username;
		this.password=password;
		this.tipoUser=tipoUser;
	}

}

enum TipoUsuario{
	ADMIN, ESTANDAR;
}
