package model;

import java.util.ArrayList;
import java.util.List;

public class ValidarTodo implements Validador{
	public List<Validador> validaciones = new ArrayList<Validador>();
	
	public void validar(String password, List<String[]> passAnteriores) {
		validaciones.stream().forEach(validacion -> validacion.validar(password, passAnteriores));
	}
	
	ValidarTodo() {
		validaciones.add(new ValidadorLongitudApropiada());
		validaciones.add(ValidadorDePassComun.instance());
		validaciones.add(new ValidadorDeMinuscula());
		validaciones.add(new ValidadorDeMayusculas());
		validaciones.add(new ValidadorDeNumero());
		validaciones.add(new ValidadorDePassUsadas());
	}
}
