package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import exceptions.*;

public class BuilderOperacionDeEgreso {
	private DocumentoComercial codDocumentoComercial;
	private LocalDateTime fechaOperacion;
	private MedioDePago medio;
	private List<Item> items = new ArrayList<>();
	private Organizacion organizacion;
	private Proveedor proveedor;

	public void setCodDocumentoComercial(DocumentoComercial codDocumentoComercial) {
		this.codDocumentoComercial = codDocumentoComercial;
	}

	public void setFechaOperacion(LocalDateTime fechaOperacion) {
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
