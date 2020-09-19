package usuarios;

import javax.persistence.Embeddable;

@Embeddable
public class Mensaje {
	private String cuerpo;

	public Mensaje(String cuerpo) {
		this.cuerpo = cuerpo;
	}
}
