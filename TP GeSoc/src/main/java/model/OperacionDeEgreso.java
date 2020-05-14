package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OperacionDeEgreso {
	int codDocumentoComercial;
	Date fechaOperacion;
	MedioDePago medio;
	List<Item> items = new ArrayList<>();
	Organizacion organizacion;
	Proveedor proveedor;

	public void setCodDocumentoComercial(int codDocumentoComercial) {
		this.codDocumentoComercial = codDocumentoComercial;
	}

	public OperacionDeEgreso(int codDocumentoComercial, Date fechaOperacion, MedioDePago medio, List<Item> items,
			Organizacion organizacion, Proveedor proveedor) {
		this.codDocumentoComercial = codDocumentoComercial;
		this.fechaOperacion = fechaOperacion;
		this.medio = medio;
		this.items = items;
		this.organizacion = organizacion;
		this.proveedor = proveedor;
	}

	public float valorTotal() {
		return (float) this.items.stream().mapToDouble(a -> a.getValor()).sum();
	};

}
