package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import exceptions.*;

public class BuilderOperacionDeEgreso {
	int codDocumentoComercial;
	Date fechaOperacion;
	MedioDePago medio;
	List<Item> items = new ArrayList<>();
	Organizacion organizacion;
	Proveedor proveedor;

	public void setCodDocumentoComercial(int codDocumentoComercial) {
		this.codDocumentoComercial = codDocumentoComercial;
	}

	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public void setMedio(MedioDePago medio) {
		this.medio = medio;
	}

	private void validarListaItemsNoVacia(List<Item> items) {
		if (items.isEmpty())
			throw new listaDeItemsVaciaException();
	}

	public void setItems(List<Item> items) {
		validarListaItemsNoVacia(items);
		this.items = items;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public OperacionDeEgreso registrarOperacion() {
		return new OperacionDeEgreso(codDocumentoComercial, fechaOperacion, medio, items, organizacion, proveedor);
	}

}
