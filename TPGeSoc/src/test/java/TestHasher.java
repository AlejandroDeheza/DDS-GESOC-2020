import static org.junit.Assert.*;
import org.junit.*;
import usuarios.Hasher;

public class TestHasher {

	private String[] passConSalt;
	
	private String[] generarHashYSalt(String password)
	{
		String salt = Hasher.generarSalt();
		String hashedPassword = Hasher.hashSHA512(password, salt);
		
		passConSalt[0] = hashedPassword;
		passConSalt[1] = salt;
		return passConSalt;
	}

	@Before
	public void init() {
		passConSalt = new String[2];
	}
	
	@Test
	public void elHasherCreaSaltsAleatorios() {
		String unaSalt = Hasher.generarSalt();
		String otraSalt = Hasher.generarSalt();
		assertNotEquals(unaSalt, otraSalt);
	}
	
	@Test
	public void elHasherCreaHashsNoAleatoriosConLaMismaSalt() {
		String salt = Hasher.generarSalt();
		String unHash = Hasher.hashSHA512("contraseña", salt);
		String otroHash = Hasher.hashSHA512("contraseña", salt);
		assertEquals(unHash, otroHash);
	}
	
	@Test
	public void elHasherCreaHashsAleatoriosConDistintasSalt() {
		String unHash = Hasher.hashSHA512("contraseña", Hasher.generarSalt());
		String otroHash = Hasher.hashSHA512("contraseña", Hasher.generarSalt());
		assertNotEquals(unHash, otroHash);
	}
	
	@Test
	public void elHasherComparaCorrectamenteContraseniaCorrecta() {
		String password = "contraseña";
		String[] passConSalt = generarHashYSalt(password);
		assertTrue(Hasher.sonCorrespondientes(password, passConSalt));
	}
	
	@Test
	public void elHasherComparaCorrrectamenteContraseniaIncorrecta() {
		String password = "contraseña";
		String[] passConSalt = generarHashYSalt(password);
		assertFalse(Hasher.sonCorrespondientes("otraContrasenia", passConSalt));
	}

}
