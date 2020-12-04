package validacionesContrasenias;

import exceptions.contraseniaSinNumeroException;

public class ValidadorDeNumero implements Validador {

	public void validar(String password) {
		if (password.chars().filter(Caracter -> Character.isDigit(Caracter)).count() == 0) {
			throw new contraseniaSinNumeroException();

		}
	}

}
