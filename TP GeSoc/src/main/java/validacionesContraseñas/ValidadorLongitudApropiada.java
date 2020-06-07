package validacionesContraseñas;

import exceptions.longitudDeContraseniaBajaException;

public class ValidadorLongitudApropiada implements Validador {

	public void validar(String password) {
		if (password.length() < 8) {
			throw new longitudDeContraseniaBajaException();
		}
	}
}
