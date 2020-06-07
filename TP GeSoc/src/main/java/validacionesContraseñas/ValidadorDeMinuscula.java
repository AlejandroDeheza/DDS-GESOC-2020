package validacionesContraseñas;

import exceptions.contraseniaSinMinusculaException;

public class ValidadorDeMinuscula implements Validador{
	
	public void validar(String password) {
		if (password.chars().filter(Caracter -> Character.isLowerCase(Caracter)).count() == 0) {
			throw new contraseniaSinMinusculaException();
		}
	}
}