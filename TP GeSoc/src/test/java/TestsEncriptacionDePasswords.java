import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Encrypter;


public class TestsEncriptacionDePasswords {
	

	@Before
	public void init() {
		
	}

	@Test
	public void desencriptaPasswordEncriptadaDevuelveLaOriginal() {
		String passwordOriginal = "HolaComoVa";
		String passwordEncriptada, passwordDesencriptada=null;
		try {
		passwordEncriptada=Encrypter.encriptarString(passwordOriginal, "password", "12345678");
		passwordDesencriptada=Encrypter.desencriptarString(passwordEncriptada, "password", "12345678");
		}catch (Exception e) {
			
		}
		Assert.assertEquals(passwordDesencriptada, "HolaComoVa");
		
	}
	



}
