import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class Creador {
	
	
	public Moneda crearMoneda(Double monto) {
		return new Moneda(monto,"ARS");
	}
	public List<Item> crearListaDeTresItems(){
		List <Item> ListaItems = new ArrayList<>();
		Item item1 = new Item(crearMoneda(20.0), "Item A");
		Item item2 = new Item(crearMoneda(30.0), "Item B");
		Item item3 = new Item(crearMoneda(40.0), "Item C");

		 ListaItems.add(item1);
		 ListaItems.add(item2);
		 ListaItems.add(item3);
	
		return ListaItems;
	}
	public DocumentoComercial crearDocumentoComercial(TipoDocumentoComercial tipo) {
		return new DocumentoComercial (tipo);
	}
	
	/*
	public OperacionDeEgreso crearOperacionDeEgreso() {
		return new OperacionDeEgreso(items,)
	}*/

}
