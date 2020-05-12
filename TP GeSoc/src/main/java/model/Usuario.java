package model;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	String username;
	String password;
	private List<String> passwordsUsadas = new ArrayList<String>();
	TipoUsuario tipoUser;
	
	Usuario(String username, String password, TipoUsuario tipoUser){
		this.username=username;
		this.password=password;
        agregarPasswordUsada(password);
		this.tipoUser=tipoUser;
	}

	void agregarPasswordUsada(String password) {
		this.passwordsUsadas.add(password);
	}
	
	public void cambiarContrasenia(String password) {
		validadorPasswords.instance().validarPassword(password, passwordsUsadas);
		this.password=password;
		this.passwordsUsadas.add(password);
	}
}
