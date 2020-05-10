import java.util.ArrayList;
import java.util.List;

public class EntidadJuridica {
	String razonSocial;
	String nombreFicticio;
	int cuit;
	String direccionPostal;
	int codInscIGJ; //Opcional
	List<EntidadBase> entidades = new ArrayList<>(); //Puede estar vacia
	CategoriaEntidadJuridica categoriaEntidad; //Opcionalmente se reemplazara por una intefaz
	
}

