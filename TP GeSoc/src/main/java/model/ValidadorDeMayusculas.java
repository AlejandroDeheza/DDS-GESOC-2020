package model;

import java.util.List;

import exceptions.contraseniaSinMayusculaException;

public class ValidadorDeMayusculas implements Validador{

	@Override
	public void validar(String password, List<String[]> passwordsUsadas) {
		if (password.chars().filter(Caracter -> Character.isUpperCase(Caracter)).count() == 0) {
			throw new contraseniaSinMayusculaException();
		}
	}

}
