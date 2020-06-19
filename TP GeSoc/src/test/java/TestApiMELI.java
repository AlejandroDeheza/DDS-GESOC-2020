import org.junit.*;

import model.*;


public class TestApiMELI {

		private MercadoLibreApi api;
		
		@Before
		public void init() {
			api = new MercadoLibreApi();
		}
		
		@Test
		public void apiMercadoLibreGetPaisTest() {
			Assert.assertEquals("AR",api.obtenerJSONPais("Argentina").getString("id"));
		}
		
		@Test
		public void apiMercadoLibreGetProvinciaTest() {			
			Assert.assertEquals("Rocha",api.obtenerJSONProvincia("Rocha","Uruguay").getString("name"));
		}
		
		@Test
		public void apiMercadoLibreGetCiudadTest() {		
			Assert.assertEquals("Aguas Dulces",api.obtenerJSONCiudad("Aguas Dulces", "Rocha", "Uruguay").getString("name"));
		}
		
		@Test
		public void apiMercadoLibreGetCurrencyTest() {		
			Assert.assertEquals("Peso argentino",api.obtenerJSONMoneda("Argentina").getString("description"));
		}
}
