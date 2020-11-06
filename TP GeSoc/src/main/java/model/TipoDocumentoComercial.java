package model;


public enum TipoDocumentoComercial {
	FACTURA, TICKET, RECIBO;

	public String getDisplayName() {
		return this.toString();
	}
}
