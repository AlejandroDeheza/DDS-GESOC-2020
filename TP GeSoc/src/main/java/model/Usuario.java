package model;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String username;
	private TipoUsuario tipoUser;
	private String hashedPasswordActual;
	private String saltActual;
	private List<String[]> hashedPasswordUsadasConSalt = new ArrayList<String[]>();
	private Hasher hasher = new Hasher();

	Usuario(String username, TipoUsuario tipoUser, String hashedPassword, String salt) {
		this.username = username;
		this.tipoUser = tipoUser;
		this.hashedPasswordActual = hashedPassword;
		this.saltActual = salt;
		this.agregarPasswordUsada();
	}
	
	private void agregarPasswordUsada() {
		String[] passConSalt = new String[2];
		passConSalt[0] = this.hashedPasswordActual;
		passConSalt[1] = this.saltActual;
		this.hashedPasswordUsadasConSalt.add(passConSalt);
	}

	public void cambiarContrasenia(String password) throws NoSuchAlgorithmException
	{
		ValidarTodo validador = new ValidarTodo();
		
		validador.validar(password, this.hashedPasswordUsadasConSalt);
		this.saltActual = hasher.generarSalt();
		this.hashedPasswordActual = hasher.hashSHA512(password, this.saltActual);
		agregarPasswordUsada();
	}
}
