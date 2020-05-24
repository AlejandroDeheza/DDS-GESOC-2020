package model;

public class BuilderUsuario {
	String username;
	String hashedPassword;
	TipoUsuario tipo;
	Hasher hasher = new Hasher();

	public void setUsername(String username) {
		// Validar que no exista previamente (queda a futuro)
		// evitar god objects
		this.username = username;
	}

	public void setPassword(String password) {
		ValidarTodo validador = new ValidarTodo();
		
		validador.validar(password,null);
		this.hashedPassword = hasher.hashBcrypt(password);
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
		return new Usuario(username, hashedPassword, tipo);
	}

}
