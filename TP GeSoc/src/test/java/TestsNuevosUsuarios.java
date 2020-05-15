import exceptions.*;
import model.*;
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
	public void setearContraseniaNormalEnBuilder() {
		builder.setPassword("qiehgWfiiyrt26");
		Assert.assertEquals("qiehgWfiiyrt26", builder.getPassword());
	}

	@Test
	public void setearTipoUsuarioEnBuilder() {
		builder.setTipo(TipoUsuario.ADMIN);
		Assert.assertEquals(TipoUsuario.ADMIN, builder.getTipo());
	}

	@Test(expected = longitudDeContraseniaBajaException.class)
	public void setearContraseniaCorta() {
		builder.setPassword("Holi");
	}

	@Test(expected = contraseniaUsadaPreviamenteException.class)
	public void setearContraseniaYaUsada() {
		builder.setPassword("qiehgyWfiiyrt2");
		builder.setTipo(TipoUsuario.ADMIN);
		builder.setUsername("Jorge");
		usuario = builder.crearUsuario();
		usuario.cambiarContrasenia("qiehgyWfiiyrt2");
	}

	@Test(expected = contraseniaComunException.class)
	public void setearContraseniaComun() {
		builder.setPassword("password");
	}

	@Test(expected = contraseniaSinMayusculaException.class)
	public void setearContraseniaSinMayuscula() {
		builder.setPassword("qwrpoqwirpqworiqpwori");
	}

	@Test(expected = contraseniaSinMinusculaException.class)
	public void setearContraseniaSinMinuscula() {
		builder.setPassword("GASLKASJGLQPR25126");
	}

	@Test(expected = contraseniaSinNumeroException.class)
	public void setearContraseniaSinNumero() {
		builder.setPassword("QWRasdqwRWQsafG");
	}
}
