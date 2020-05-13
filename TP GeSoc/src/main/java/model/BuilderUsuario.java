package model;


public class BuilderUsuario {
	String username;
	String password;
	TipoUsuario tipo;

	public void setUsername(String username) {
		//Validar que no exista previamente (queda a futuro)
		this.username=username;
	}
	public void setPassword(String password) {
		validadorPasswords.instance().validarPassword(password, null);		
		this.password=password;	
	}
	public void setTipo(TipoUsuario tipo) {
		this.tipo=tipo;
	}
	public TipoUsuario getTipo() {
		return tipo;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public Usuario crearUsuario() {
			return new Usuario(username,password,tipo);
	}

}
