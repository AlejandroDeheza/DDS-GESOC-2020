import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import usuarios.Hasher;
import validacionesContrasenias.*;

public class TestValidacionesDeContrasenia {
	
	private String[] passConSalt;
	private ArrayList<String[]> passUsadas;
	
	private String[] generarHashYSalt(String password)
	{
		String salt = Hasher.generarSalt();
		String hashedPassword = Hasher.hashSHA512(password, salt);
		
		passConSalt[0] = hashedPassword;
		passConSalt[1] = salt;
		return passConSalt;
	}
	
	private void validarContraseniaEstandoUsada(String password, String[] passConSalt) 
	{	
		passUsadas.add(passConSalt);
		ValidadorDePassUsadas validador = new ValidadorDePassUsadas(passUsadas);
		validador.validar(password);
	}

	@Before
	public void init() {
		passConSalt = new String[2];
		passUsadas = new ArrayList<>();
	}
	
	@Test(expected = contraseniaUsadaPreviamenteException.class)
	public void validarContraseniaYaUsada() {
		String password = "contraseña";
		String[] passConSalt = generarHashYSalt(password);
		validarContraseniaEstandoUsada(password, passConSalt);
	}
	
	@Test
	public void validarContraseniaNoUsada() {
		String password = "contraseña";
		String[] passConSalt = generarHashYSalt(password);
		validarContraseniaEstandoUsada("otraContrasenia", passConSalt);
	}
	
	@Test(expected = longitudDeContraseniaBajaException.class)
	public void validarContraseniaCorta() {
		ValidadorLongitudApropiada validador = new ValidadorLongitudApropiada();
		validador.validar("Holi");
	}

	@Test(expected = contraseniaComunException.class)
	public void validarContraseniaComun() {
		ValidadorDePassComun.instance().validar("password");
	}

	@Test(expected = contraseniaSinMayusculaException.class)
	public void validarContraseniaSinMayuscula(){
		ValidadorDeMayusculas validador = new ValidadorDeMayusculas();
		validador.validar("qwrpoqwirpqworiqpwori");
	}

	@Test(expected = contraseniaSinMinusculaException.class)
	public void validarContraseniaSinMinuscula(){
		ValidadorDeMinuscula validador = new ValidadorDeMinuscula();
		validador.validar("GASLKASJGLQPR25126");
	}

	@Test(expected = contraseniaSinNumeroException.class)
	public void validarContraseniaSinNumero() {
		ValidadorDeNumero validador = new ValidadorDeNumero();
		validador.validar("QWRasdqwRWQsafG");
	}
	
	@Test
	public void validarContraseniaCompletamente() {
		ValidarTodo validador = new ValidarTodo(null);
		validador.validar("qiehgyWfiiyrt2");
	}
	
}
