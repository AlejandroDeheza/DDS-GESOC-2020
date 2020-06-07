package model;

import java.util.List;
import exceptions.contraseniaUsadaPreviamenteException;

public class ValidadorDePassUsadas implements Validador {
	Hasher hasher = new Hasher();

	@Override
	public void validar(String password, List<String[]> passwordsUsadas) {
		if (passwordsUsadas == null) {
			return;
		}
		if (passwordsUsadas.stream().anyMatch(
				unaPasswordUsadaConSalt -> hasher.sonCorrespondientes(password, unaPasswordUsadaConSalt))) {
			throw new contraseniaUsadaPreviamenteException();
		}
	}

}
