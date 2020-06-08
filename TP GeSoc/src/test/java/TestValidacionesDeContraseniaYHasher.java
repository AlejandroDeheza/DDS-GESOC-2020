import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.*;
import usuarios.Hasher;
import validacionesContraseñas.*;

public class TestValidacionesDeContraseniaYHasher {
	
	private String[] passConSalt;
	private ArrayList<String[]> passUsadas;
	
	private String[] generarHashYSalt(String password) throws NoSuchAlgorithmException
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
	
	
	
	// tests del hasher //
	
	@Test
	public void elHasherCreaSaltsAleatorios() throws NoSuchAlgorithmException{
		String unaSalt = Hasher.generarSalt();
		String otraSalt = Hasher.generarSalt();
		Assert.assertFalse(unaSalt.equals(otraSalt));
	}
	
	@Test
	public void elHasherCreaHashsNoAleatoriosConLaMismaSalt() throws NoSuchAlgorithmException{
		String salt = Hasher.generarSalt();
		String unHash = Hasher.hashSHA512("contraseña", salt);
		String otroHash = Hasher.hashSHA512("contraseña", salt);
		Assert.assertTrue(unHash.equals(otroHash));
	}
	
	@Test
	public void elHasherCreaHashsAleatoriosConDistintasSalt() throws NoSuchAlgorithmException{
		String unHash = Hasher.hashSHA512("contraseña", Hasher.generarSalt());
		String otroHash = Hasher.hashSHA512("contraseña", Hasher.generarSalt());
		Assert.assertFalse(unHash.equals(otroHash));
	}
	
	@Test
	public void elHasherComparaCorrectamenteContraseñaCorrecta() throws NoSuchAlgorithmException{
		String password = "contraseña";
		String[] passConSalt = generarHashYSalt(password);
		Assert.assertTrue(Hasher.sonCorrespondientes(password, passConSalt));
	}
	
	@Test
	public void elHasherComparaCorrrectamenteContraseñaIncorrecta() throws NoSuchAlgorithmException{
		String password = "contraseña";
		String[] passConSalt = generarHashYSalt(password);
		Assert.assertFalse(Hasher.sonCorrespondientes("otracosa", passConSalt));
	}
	
	
	
	// tests de validaciones de contraseñas //
	
	@Test(expected = contraseniaUsadaPreviamenteException.class)
	public void validarContraseniaYaUsada() throws NoSuchAlgorithmException{
		String password = "contraseña";
		String[] passConSalt = generarHashYSalt(password);
		validarContraseniaEstandoUsada(password, passConSalt);
	}
	
	@Test
	public void validarContraseniaNoUsada() throws NoSuchAlgorithmException{
		String password = "contraseña";
		String[] passConSalt = generarHashYSalt(password);
		validarContraseniaEstandoUsada("otraCosa", passConSalt);
	}
	
	@Test(expected = longitudDeContraseniaBajaException.class)
	public void validarContraseniaCorta() {
		ValidadorLongitudApropiada validador = new ValidadorLongitudApropiada();
		validador.validar("Holi");
	}

	@Test(expected = contraseniaComunException.class)
	public void validarContraseniaComun() {
		ValidadorDePassComun validador = ValidadorDePassComun.instance();
		validador.validar("password");
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
	public void validarContraseñaCompletamente() {
		ValidarTodo validador = new ValidarTodo(null);
		validador.validar("qiehgyWfiiyrt2");
	}

}
