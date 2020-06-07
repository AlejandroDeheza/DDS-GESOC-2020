package usuarios;

import java.security.NoSuchAlgorithmException;

import validacionesContraseñas.ValidarTodo;

public class BuilderUsuario {
	private String username;
	private String hashedPassword;
	private String salt;
	private TipoUsuario tipo;

	public void setUsername(String username) {
		// Validar que no exista previamente (queda a futuro)
		// evitar god objects
		this.username = username;
	}

	public void setPassword(String password) throws NoSuchAlgorithmException
	{
		ValidarTodo validador = new ValidarTodo(null);
		
		validador.validar(password);
		this.salt = Hasher.generarSalt();
		this.hashedPassword = Hasher.hashSHA512(password, this.salt);
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public TipoUsuario getTipo() {
		return this.tipo;
	}

	public String getUsername() {
		return this.username;
	}

	public String getHashedPassword() {
		return this.hashedPassword;
	}// este metodo no deberia ser posible. Solo lo usamos para un test

	public Usuario crearUsuario() {
		return new Usuario(username, tipo, hashedPassword, salt);
	}

}
