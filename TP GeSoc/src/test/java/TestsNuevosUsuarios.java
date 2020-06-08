import exceptions.*;
import usuarios.*;
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
		Assert.assertNotEquals("qiehgWfiiyrt26", builder.getHashedPassword());
	}

	@Test
	public void setearTipoUsuarioEnBuilder() {
		builder.setTipo(TipoUsuario.ADMIN);
		Assert.assertEquals(TipoUsuario.ADMIN, builder.getTipo());
	}

	@Test(expected = contraseniaUsadaPreviamenteException.class)
	public void setearContraseniaYaUsada() {
		builder.setPassword("qiehgyWfiiyrt2");
		//no seteamos lo demas porque no nos interesa
		usuario = builder.crearUsuario();
		usuario.cambiarContrasenia("qiehgyWfiiyrt2");
	}

}
