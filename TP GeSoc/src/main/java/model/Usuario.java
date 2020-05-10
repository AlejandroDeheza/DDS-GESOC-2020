package model;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class Usuario {
	String username;
	String password;
	private List<String> passwordsUsadas = new ArrayList<String>();
	TipoUsuario tipoUser;
	
	Usuario(String username, String password, TipoUsuario tipoUser){
		this.username=username;
		validadorPasswords.instance().validarPassword(password, passwordsUsadas);
			
		
		this.password=password;
		agregarPasswordUsada(password);
		this.tipoUser=tipoUser;
	}

	void agregarPasswordUsada(String password) {
		this.passwordsUsadas.add(password);
	}
	
}

enum TipoUsuario{
	ADMIN, ESTANDAR;
}
