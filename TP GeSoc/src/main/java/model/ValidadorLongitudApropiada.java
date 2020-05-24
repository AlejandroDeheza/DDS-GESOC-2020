package model;

import java.util.List;

import exceptions.longitudDeContraseniaBajaException;

public class ValidadorLongitudApropiada implements Validador {

	@Override
	public void validar(String password, List<String> passwordsUsadas) {
		if (password.length() < 8) {
			throw new longitudDeContraseniaBajaException();
		}
	}
}
