package usuarios;

import validacionesContrasenias.ValidarTodo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue
	@Column(name = "id_usuario")
	private Long id;
	
	@Column(name = "nombre_usuario")
	private String username;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_usuario")
	private TipoUsuario tipoUser;
	
	@Column(name = "password")
	private String hashedPasswordActual;
	
	@Column(name = "salt")
	private String saltActual;
	
	@ElementCollection
	@CollectionTable(name = "passwords_historicas",
					 joinColumns=@JoinColumn(name = "id_usuario"))
	@Column(name = "password")
	private List<String[]> hashedPasswordUsadasConSalt = new ArrayList<String[]>(); 
	
	@ElementCollection
	@CollectionTable(name = "mensajes_usuarios",
					 joinColumns=@JoinColumn(name = "id_usuario"))
	@Column(name = "mensaje")
	private List<Mensaje> mensajes = new ArrayList<>();
	
	public Usuario() {}

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
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}

	public String getHashedPasswordActual() {
		return hashedPasswordActual;
	}
	public String getSaltActual(){
		return saltActual;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
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

	public Long getId() {
		return id;
	}
}
