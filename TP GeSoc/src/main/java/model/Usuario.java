package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	String username;
	String hashedPassword;
	private List<String> hashedPasswordUsadas = new ArrayList<String>();
	TipoUsuario tipoUser;
	Hasher hasher = new Hasher();

	Usuario(String username, String hashedPassword, TipoUsuario tipoUser) {
		this.username = username;
		this.hashedPassword = hashedPassword;
		agregarPasswordUsada();
		this.tipoUser = tipoUser;
	}
	
	private void agregarPasswordUsada() {
		hashedPasswordUsadas.add(hashedPassword);
	}

	public void cambiarContrasenia(String password) {
		ValidarTodo validador = new ValidarTodo();
		
		validador.validar(password,hashedPasswordUsadas);
		this.hashedPassword = hasher.hashBcrypt(password);
		agregarPasswordUsada();
	}
}
