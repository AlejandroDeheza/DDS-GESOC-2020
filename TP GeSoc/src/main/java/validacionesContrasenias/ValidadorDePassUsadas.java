package validacionesContrasenias;

import java.util.List;
import java.util.ArrayList;
import exceptions.contraseniaUsadaPreviamenteException;
import usuarios.Hasher;

public class ValidadorDePassUsadas implements Validador {
	private List<String[]> passwordsUsadas = new ArrayList<>();
	
	public ValidadorDePassUsadas(List<String[]> passwordsUsadas){
		this.passwordsUsadas = passwordsUsadas;
	}

	public void validar(String password) {
		if (this.passwordsUsadas == null) {
			return;
		}
		if (this.passwordsUsadas.stream().anyMatch(
				unaPasswordUsadaConSalt -> Hasher.sonCorrespondientes(password, unaPasswordUsadaConSalt))) {
			throw new contraseniaUsadaPreviamenteException();
		}
	}

}
