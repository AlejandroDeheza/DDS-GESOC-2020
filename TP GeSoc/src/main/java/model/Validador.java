package model;

import java.util.List;

public interface Validador {
	public void validar(String password, List<String[]> passwordsUsadasConSalt);
}
