import exceptions.*;
import model.*;

import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestsNuevosUsuarios {

	private Usuario usuario;
	private BuilderUsuario builder;

	@Before
	public void init() {
		builder = new BuilderUsuario();
	}

	@Test
	public void setearUsernameNormalEnBuilder() {
		builder.setUsername("Jorge");
		Assert.assertEquals("Jorge", builder.getUsername());
	}
	
	@Test
	public void setearContraseniaNormalEnBuilder() throws NoSuchAlgorithmException{
		builder.setPassword("qiehgWfiiyrt26");
		Assert.assertNotEquals("qiehgWfiiyrt26", builder.getHashedPassword());
	}

	@Test
	public void setearTipoUsuarioEnBuilder() {
		builder.setTipo(TipoUsuario.ADMIN);
		Assert.assertEquals(TipoUsuario.ADMIN, builder.getTipo());
	}

	@Test(expected = longitudDeContraseniaBajaException.class)
	public void validarContraseniaCorta() {
		ValidadorLongitudApropiada validador = new ValidadorLongitudApropiada();
		
		validador.validar("Holi",null);
	}

	@Test(expected = contraseniaUsadaPreviamenteException.class)
	public void setearContraseniaYaUsada() throws NoSuchAlgorithmException{
		builder.setPassword("qiehgyWfiiyrt2");
		builder.setTipo(TipoUsuario.ADMIN);
		builder.setUsername("Jorge");
		usuario = builder.crearUsuario();
		usuario.cambiarContrasenia("qiehgyWfiiyrt2");
	}

	@Test(expected = contraseniaComunException.class)
	public void setearContraseniaComun() throws NoSuchAlgorithmException{
		builder.setPassword("password");
	}

	@Test(expected = contraseniaSinMayusculaException.class)
	public void setearContraseniaSinMayuscula() throws NoSuchAlgorithmException{
		builder.setPassword("qwrpoqwirpqworiqpwori");
	}

	@Test(expected = contraseniaSinMinusculaException.class)
	public void setearContraseniaSinMinuscula() throws NoSuchAlgorithmException{
		builder.setPassword("GASLKASJGLQPR25126");
	}

	@Test(expected = contraseniaSinNumeroException.class)
	public void setearContraseniaSinNumero() throws NoSuchAlgorithmException{
		builder.setPassword("QWRasdqwRWQsafG");
	}
}
