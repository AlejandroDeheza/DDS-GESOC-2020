package model;

public class DocumentoComercial {
	private int ID;
	private TipoDocumentoComercial tipoDoc;
	
	public DocumentoComercial(int ID, TipoDocumentoComercial tipoDoc){
		this.ID=ID;
		this.tipoDoc=tipoDoc;
	}
}
