package ubicacion;

import org.json.JSONArray;
import org.json.JSONObject;

public interface InfoDeUbicacionYMoneda{
	public JSONArray obtenerPaises();
	
	public JSONArray obtenerProvincias(String Pais);
	
	public JSONArray obtenerCiudades(String Provincia, String Pais);

	public JSONObject obtenerJSONPais(String Pais);
	
	public JSONObject obtenerJSONProvincia(String Provincia, String Pais);
			
	public JSONObject obtenerJSONCiudad(String Ciudad, String Provincia, String Pais);
	
	public JSONObject obtenerJSONMoneda(String Pais);
	
	public JSONObject obtenerRatioAPesos(String IdMoneda);
}
