package usuarios;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Mensaje {
	
	@Column(name = "mensaje")
	private String cuerpo;

	public Mensaje () {
		
	}
	public Mensaje(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getCuerpo(){
		return cuerpo;
	}
}
