package usuarios;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import validacionesContrasenias.ValidarTodo;

@Entity
@Table(name = "Usuarios")
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	
	@Enumerated(EnumType.STRING)
	private TipoUsuario tipoUser;
	
	private String hashedPasswordActual;
	
	private String saltActual;
	
	@ElementCollection
	private List<String[]> hashedPasswordUsadasConSalt = new ArrayList<String[]>(); 
	
	@ElementCollection
	private List<Mensaje> mensajes = new ArrayList<>();

	public Usuario(String username, TipoUsuario tipoUser, String hashedPassword, String salt) {
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

	public void cambiarContrasenia(String password)
	{
		ValidarTodo validador = new ValidarTodo(this.hashedPasswordUsadasConSalt);
		
		validador.validar(password);
		this.saltActual = Hasher.generarSalt();
		this.hashedPasswordActual = Hasher.hashSHA512(password, this.saltActual);
		this.agregarPasswordUsada();
	}
	
	public void recibirMensaje(Mensaje mensaje) {
		this.mensajes.add(mensaje);
	}
}
