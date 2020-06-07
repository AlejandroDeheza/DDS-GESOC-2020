package model;

import exceptions.contraseniaSinMayusculaException;

public class ValidadorDeMayusculas implements Validador{

	public void validar(String password) {
		if (password.chars().filter(Caracter -> Character.isUpperCase(Caracter)).count() == 0) {
			throw new contraseniaSinMayusculaException();
		}
	}

}
