package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperacionDeEgreso {
	private DocumentoComercial documentoComercial;
	private LocalDateTime fechaOperacion;
	private MedioDePago medio;
	private List<Item> items = new ArrayList<>();
	private Organizacion organizacion;
	private Proveedor proveedor;

	public void setCodDocumentoComercial(DocumentoComercial codDocumentoComercial) {
		this.documentoComercial = codDocumentoComercial;
	}

	public OperacionDeEgreso(DocumentoComercial codDocumentoComercial, LocalDateTime fechaOperacion, MedioDePago medio, List<Item> items,
			Organizacion organizacion, Proveedor proveedor) {
		this.documentoComercial = codDocumentoComercial;
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
