package model;

//import java.security.SecureRandom;
//import java.util.Random;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Hasher {
	
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/*
	public String generarSalt() {
		final Random r = new SecureRandom();
		byte[] salt = new byte[32];
		r.nextBytes(salt);
		return salt.toString();
	}
	*/
	//lo use para un metodo anterior. Ahora no lo uso

	public String hashBcrypt(String aHashear) {
		return passwordEncoder.encode(aHashear);
	}
	
	public Boolean sonCorrespondientes(String password, String hashedPassword) {
		return passwordEncoder.matches(password, hashedPassword);
	}
}
