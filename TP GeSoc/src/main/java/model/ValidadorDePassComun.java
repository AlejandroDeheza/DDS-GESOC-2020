package model;

import exceptions.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ValidadorDePassComun implements Validador{
	private static final ValidadorDePassComun INSTANCE = new ValidadorDePassComun(); // No se por qu� me insiste con la exception ac�
	private List<String> listaPasswords = new ArrayList<String>();
	Hasher hasher = new Hasher();
	private BufferedReader archivoPasswords;
	
	private ValidadorDePassComun() {
		this.setArchivoPasswords();
		this.leerArchivo(listaPasswords, archivoPasswords);
	}

	public static ValidadorDePassComun instance() {
		return INSTANCE;
	}

	public void validar(String password) {
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
