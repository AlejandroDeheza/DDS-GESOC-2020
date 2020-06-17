import static org.junit.Assert.*;
import org.junit.*;

import com.sun.jersey.impl.ApiMessages;

import model.*;


public class TestApiMELI {

		private MercadoLibreApi api;
		
		@Before
		public void init() {
			api = new MercadoLibreApi();
		}
		
		@Test
		public void apiMercadoLibreGetPaisTest() {
			Assert.assertEquals("AR",api.obtenerInfoPais("Argentina").getString("id"));
		}
		
		@Test
		public void apiMercadoLibreGetProvinciaTest() {			
			Assert.assertEquals("Rocha",api.obtenerInfoProvincia("Rocha","Uruguay").getString("name"));
		}
		
		@Test
		public void apiMercadoLibreGetCiudadTest() {		
			Assert.assertEquals("Aguas Dulces",api.obtenerInfoCiudad("Aguas Dulces", "Rocha", "Uruguay").getString("name"));
		}
		
		@Test
		public void apiMercadoLibreGetCurrencyTest() {		
			Assert.assertEquals("Peso argentino",api.obtenerInfoMoneda("Argentina").getString("description"));
		}
}
