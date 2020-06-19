package model;

public interface InfoDeUbicacion{
	public String obtenerInfoPais(String Pais);
	
	public String obtenerInfoProvincia(String Provincia, String Pais);
			
	public String obtenerInfoCiudad(String Ciudad, String Provincia, String Pais);
	
	public String obtenerInfoMoneda(String Pais);

}
