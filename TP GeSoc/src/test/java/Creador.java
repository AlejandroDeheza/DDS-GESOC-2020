import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import model.*;

public class Creador {
	
	public List<Item> crearListaDeTresItems(){
		List <Item> ListaItems = new ArrayList<>();
		Item item1 = new Item(new BigDecimal(20), "Item A");
		Item item2 = new Item(new BigDecimal(30), "Item B");
		Item item3 = new Item(new BigDecimal(40), "Item C");

		 ListaItems.add(item1);
		 ListaItems.add(item2);
		 ListaItems.add(item3);
	
		return ListaItems;
	}
	public DocumentoComercial crearDocumentoComercial(int ID) {
		return new DocumentoComercial (ID,TipoDocumentoComercial.FACTURA);
	}
	
	/*
	public OperacionDeEgreso crearOperacionDeEgreso() {
		return new OperacionDeEgreso(items,)
	}*/

}
