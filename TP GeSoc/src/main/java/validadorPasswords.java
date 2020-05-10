import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class validadorPasswords {
	private static final validadorPasswords INSTANCE = new validadorPasswords(); //No se por qu� me insiste con la exception ac�
	private BufferedReader archivoPasswords = new BufferedReader(this.archivoPasswords());
	private List<String> listaPasswords = new ArrayList<String>();
	
	private validadorPasswords() throws Exception {
		this.leerArchivo(listaPasswords,  archivoPasswords);
	}
	
	static validadorPasswords instance() {
		return INSTANCE;
	}
	
	
	/* Valida que las contrase�as no pertenezcan a la lista de las 10k contrase�as m�s comunes */
	boolean validarPassword(String password, List<String> passwordsUsadas) throws Exception{				
		return listaPasswords.stream().allMatch(unaPassword -> unaPassword != password) //Que la contrase�a no pertenezca a las 10k mas usadas
			   && password.length() >= 8 // Que la contrase�a tenga por lo menos 8 caracteres
			   && passwordsUsadas.stream().allMatch(unaPassword -> unaPassword != password); //Que la contrase�a no se haya usado anteriormente
			   
			   //TODO: Nico | Faltar�a un metodo m�s de control. Deber�amos sacar cada termino del && en un m�todo individual para mayor declaratividad.
	}
	
	List<String> leerArchivo(List<String> lista, BufferedReader archivo) throws Exception {
		for(int i = 1; i <= 10000; i++) {
			lista.add(archivo.readLine());
		}
		return lista;
	}
	
	private FileReader archivoPasswords() throws FileNotFoundException {
		return new FileReader("./././Assets/10k-most-common.txt");
	}
}
