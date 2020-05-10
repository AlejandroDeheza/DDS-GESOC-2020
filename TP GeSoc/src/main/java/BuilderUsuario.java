public class BuilderUsuario {
	String username;
	String password;
	TipoUsuario tipo;
	
	void setUsername(String username) {
		//Validar que no exista previamente (queda a futuro)
		this.username=username;
	}
	void setPassword(String password) {
		/* TODO
		 * Validar segun criterios de seguridad de la norma OWASP. Bajar archivo del repo de github en la referencia
		 * https://github.com/danielmiessler/SecLists/blob/master/Passwords/Common-Credentials/10k-most-common.txt
		 */
	}
	void setTipo(TipoUsuario tipo) {
		this.tipo=tipo;
	}
	Usuario crearUsuario() {
			return new Usuario(username,password,tipo);
	}

}
