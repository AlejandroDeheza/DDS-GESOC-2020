package validacionesContrasenias;

import exceptions.contraseniaUsadaPreviamenteException;
import usuarios.Hasher;

import java.util.ArrayList;
import java.util.List;

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
