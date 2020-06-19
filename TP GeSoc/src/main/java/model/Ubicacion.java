package model;

public class Ubicacion {
	String pais, provincia, ciudad;
	
	public Ubicacion(String pais, String provincia, String ciudad) {
		this.pais = pais;
		this.provincia = provincia;
		this.ciudad = ciudad;
	}
	
	public String obtenerInfoDePais(InfoDeUbicacion api) {
		return api.obtenerInfoPais(this.pais);
	}
	
	public String obtenerInfoDeProvincia(InfoDeUbicacion api) {
		return api.obtenerInfoProvincia(this.provincia, this.pais);
	}
	
	public String obtenerInfoDeCiudad(InfoDeUbicacion api) {
		return api.obtenerInfoCiudad(this.ciudad, this.provincia, this.pais);
	}
}
