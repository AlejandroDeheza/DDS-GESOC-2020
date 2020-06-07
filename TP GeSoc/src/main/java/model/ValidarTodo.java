package model;

import java.util.ArrayList;
import java.util.List;

public class ValidarTodo implements Validador{
	private List<Validador> validaciones = new ArrayList<Validador>();
	
	public ValidarTodo(List<String[]> passwordsUsadas) {
		this.validaciones.add(new ValidadorLongitudApropiada());
		this.validaciones.add(ValidadorDePassComun.instance());
		this.validaciones.add(new ValidadorDeMinuscula());
		this.validaciones.add(new ValidadorDeMayusculas());
		this.validaciones.add(new ValidadorDeNumero());
		this.validaciones.add(new ValidadorDePassUsadas(passwordsUsadas));
	}
	
	public void validar(String password) {
		this.validaciones.stream().forEach(validacion -> validacion.validar(password));
	}
}
