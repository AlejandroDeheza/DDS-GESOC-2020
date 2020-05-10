import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class Usuario {
	String username;
	String password;
	private List<String> passwordsUsadas = new ArrayList<String>();
	TipoUsuario tipoUser;
	
	Usuario(String username, String password, TipoUsuario tipoUser) throws Exception{
		this.username=username;
		if(validadorPasswords.instance().validarPassword(password, passwordsUsadas)) {
			this.password=password;
		}
		else { 
			throw new RuntimeErrorException(null, "Contrasenia inválida"); 
		}
		this.tipoUser=tipoUser;
	}

	void agregarPasswordUsada(String password) {
		this.passwordsUsadas.add(password);
	}
	
}

enum TipoUsuario{
	ADMIN, ESTANDAR;
}
