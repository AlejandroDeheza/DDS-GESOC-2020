import exceptions.*;
import model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestsOperacionDeEgreso {

	private BuilderOperacionDeEgreso builder;
	private OperacionDeEgreso operacion;
	private List<Item> items;

	@Before
	public void init() {
		builder = new BuilderOperacionDeEgreso();
		items = new ArrayList<>();
	}

	@Test
	public void valorTotalDevuelveLaSumaDeLosValoresDeItems() {
		items.add(new Item(200, "Pollo"));
		items.add(new Item(400, "Carne"));
		builder.setItems(items);
		// no seteamos los demas atributos porque no nos interesan.
		operacion = builder.registrarOperacion();
		assertEquals(600, operacion.valorTotal(), 0);
	}

	@Test(expected = listaDeItemsVaciaException.class)
	public void noSePuedeCrearOperacionSinItems() {
		builder.setItems(items);
	}

}