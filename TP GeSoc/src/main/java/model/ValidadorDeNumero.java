package model;

import java.util.List;

import exceptions.contraseniaSinNumeroException;

public class ValidadorDeNumero implements Validador {

	@Override
	public void validar(String password, List<String[]> passwordsUsadas) {
		if (password.chars().filter(Caracter -> Character.isDigit(Caracter)).count() == 0) {
			throw new contraseniaSinNumeroException();

		}
	}

}
