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

public enum TipoUsuario{
	ADMIN, ESTANDAR;
}
