package model;

import exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class validadorPasswords {
	private static final validadorPasswords INSTANCE = new validadorPasswords(); // No se por qu� me insiste con la exception ac�
	private BufferedReader archivoPasswords;
	private List<String> listaPasswords = new ArrayList<String>();

	private validadorPasswords() {
		this.setArchivoPasswords();
		this.leerArchivo(listaPasswords, archivoPasswords);
	}

	static validadorPasswords instance() {
		return INSTANCE;
	}

	void validarPassword(String password, List<String> passwordsUsadas) {
		validarQueTengaLongitudApropiada(password); // Que la contrase�a tenga por lo menos 8 caracteres
		validarQueNoSeHayaUsadoAntes(password, passwordsUsadas); // Que la contrase�a no se haya usado anteriormente
		validarQueNoSeaComun(password); // Que la contrase�a no pertenezca a las 10k mas usadas
		validarQueTengaAlgunaMayuscula(password);
		validarQueTengaAlgunaMinuscula(password);
		validarQueTengaAlgunNumero(password);
	}

	private void validarQueTengaAlgunaMinuscula(String password) {
		if (password.chars().filter(Caracter -> Character.isLowerCase(Caracter)).count() == 0) {
			throw new contraseniaSinMinusculaException();
		}
	}

	private void validarQueTengaAlgunaMayuscula(String password) {
		if (password.chars().filter(Caracter -> Character.isUpperCase(Caracter)).count() == 0) {
			throw new contraseniaSinMayusculaException();
		}
	}

	private void validarQueTengaAlgunNumero(String password) {
		if (password.chars().filter(Caracter -> Character.isDigit(Caracter)).count() == 0) {
			throw new contraseniaSinNumeroException();

		}
	}

	private void validarQueNoSeHayaUsadoAntes(String password, List<String> passwordsUsadas) {
		if (passwordsUsadas == null) {
			return;
		}
		if (passwordsUsadas.stream().anyMatch(unaPassword -> unaPassword.contentEquals(password))) {
			throw new contraseniaUsadaPreviamenteException();
		}
	}

	private void validarQueTengaLongitudApropiada(String password) {
		if (password.length() < 8) {
			throw new longitudDeContraseniaBajaException();
		}
	}

	private void validarQueNoSeaComun(String password) {
		if (listaPasswords.stream().anyMatch(unaPassword -> unaPassword.contentEquals(password))) {
			throw new contraseniaComunException();
		}
	}

	private List<String> leerArchivo(List<String> lista, BufferedReader archivo) {

		try {
			for (int i = 1; i <= 10000; i++) {
				lista.add(archivo.readLine());
			}
			archivo.close();
		} catch (IOException e) {
		}
		return lista;
	}

	private FileReader archivoPasswords() throws FileNotFoundException {
		return new FileReader("./././Assets/10k-most-common.txt");

	}

	private void setArchivoPasswords() {
		try {
			archivoPasswords = new BufferedReader(this.archivoPasswords());
		} catch (FileNotFoundException e) {
			System.out.println("El archivo especificado no se encuentra en la carpeta");
		}
	}
}
