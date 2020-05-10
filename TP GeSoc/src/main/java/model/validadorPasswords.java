package model;
import java.io.BufferedReader;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import exceptions.contraseniaComunException;
import exceptions.contraseniaUsadaPreviamenteException;
import exceptions.longitudDeContraseniaBajaException;

public class validadorPasswords {
	private static final validadorPasswords INSTANCE = new validadorPasswords(); //No se por qu� me insiste con la exception ac�
	private BufferedReader archivoPasswords;
	private List<String> listaPasswords = new ArrayList<String>();
	
	private validadorPasswords() {
		this.setArchivoPasswords();
		this.leerArchivo(listaPasswords,  archivoPasswords);
	}
	

	static validadorPasswords instance() {
		return INSTANCE;
	}
	
	
	/* Valida que las contrase�as no pertenezcan a la lista de las 10k contrase�as m�s comunes */
	void validarPassword(String password, List<String> passwordsUsadas){				
		validarQueNoSeaComun(password); //Que la contrase�a no pertenezca a las 10k mas usadas
	    validarQueTengaLongitudApropiada(password); // Que la contrase�a tenga por lo menos 8 caracteres
	    validarQueNoSeHayaUsadoAntes(password, passwordsUsadas); //Que la contrase�a no se haya usado anteriormente
			   
			   //TODO: Nico | Faltar�a un metodo m�s de control. Deber�amos sacar cada termino del && en un m�todo individual para mayor declaratividad.
	}

	private boolean validarQueNoSeHayaUsadoAntes(String password, List<String> passwordsUsadas) {
		if(!passwordsUsadas.stream().allMatch(unaPassword -> unaPassword != password))
		{
			throw new contraseniaUsadaPreviamenteException();
			
		}
		return true;
	}

	private boolean validarQueTengaLongitudApropiada(String password) {
		if(password.length() < 8)
		{
			throw new longitudDeContraseniaBajaException();
		}
		return true;
	}

	private boolean validarQueNoSeaComun(String password) {
		if(listaPasswords.stream().allMatch(unaPassword -> unaPassword != password)) {
			throw new contraseniaComunException();
		}
		return true;
	}
	
	List<String> leerArchivo(List<String> lista, BufferedReader archivo){
		for(int i = 1; i <= 10000; i++) {
			try {
			lista.add(archivo.readLine());
			}
			catch(IOException e) {
				
			}
		}
		return lista;
	}
	
	private FileReader archivoPasswords() throws FileNotFoundException{
		return new FileReader("./././Assets/10k-most-common.txt");
		
	}
	
	void setArchivoPasswords() {
		try {
			archivoPasswords=new BufferedReader(this.archivoPasswords());
		}
		catch (FileNotFoundException e) {
			System.out.println("El archivo especificado no se encuentra en la carpeta");
		}	
	}
}
