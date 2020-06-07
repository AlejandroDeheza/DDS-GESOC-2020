package model;

import java.security.NoSuchAlgorithmException;

public class BuilderUsuario {
	String username;
	String hashedPassword;
	String salt;
	TipoUsuario tipo;
	Hasher hasher = new Hasher();

	public void setUsername(String username) {
		// Validar que no exista previamente (queda a futuro)
		// evitar god objects
		this.username = username;
	}

	public void setPassword(String password) throws NoSuchAlgorithmException //revisar esta excepcion, TODO
	{
		ValidarTodo validador = new ValidarTodo();
		
		validador.validar(password, null);
		this.salt = hasher.generarSalt();
		this.hashedPassword = hasher.hashSHA512(password, this.salt);
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public String getUsername() {
		return username;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public Usuario crearUsuario() {
		return new Usuario(username, tipo, hashedPassword, salt);
	}

}
