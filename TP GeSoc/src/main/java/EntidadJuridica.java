
public class EntidadJuridica {
	String razonSocial;
	String nombreFicticio;
	int cuit;
	String direccionPostal;
	int codInscIGJ; //Opcional
	List<EntidadBase> entidades = new ArrayList<>(); //Puede estar vacia
	CategoriaEntidadJuridica categoriaEntidad; //Opcionalmente se reemplazara por una intefaz
	

}

public enum CategoriaEntidadJuridica{
	OSC, MICRO_EMPRESA, PEQUEÑA_EMPRESA, EMPRESA_MEDIANA_TRAMO_1, EMPRESA_MEDIANA_TRAMO_2;
}