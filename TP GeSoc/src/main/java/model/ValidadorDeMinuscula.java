package model;

import java.util.List;

import exceptions.contraseniaSinMinusculaException;

public class ValidadorDeMinuscula implements Validador{
	
	@Override
	public void validar(String password, List<String> passwordsUsadas) {
		if (password.chars().filter(Caracter -> Character.isLowerCase(Caracter)).count() == 0) {
			throw new contraseniaSinMinusculaException();
		}
	}
}