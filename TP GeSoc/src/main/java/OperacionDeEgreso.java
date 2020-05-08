import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OperacionDeEgreso {
	int codDocumentoComercial;
	Date fechaOperacion;
	MedioDePago medio;
	List<Item> items=new ArrayList<>();
	Organizacion organizacion;
	
	
	public float valorTotal() {
		return codDocumentoComercial;
		//Se calcula recorriendo la lista de items.
	};
	
	
}


