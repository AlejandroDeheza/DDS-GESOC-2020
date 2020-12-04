package usuarios;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Mensaje {

	private String cuerpo;
	private String asunto;
	private LocalDate fecha;


	public Mensaje () {
		
	}
	public Mensaje(String asunto, String cuerpo, LocalDate fecha) {
		this.asunto = asunto;
		this.cuerpo = cuerpo;
		this.fecha = fecha;
	}

	public String getCuerpo(){
		return cuerpo;
	}

	public String getAsunto() {
		return asunto;
	}
	public LocalDate getFecha() {
		return fecha;
	}
}
