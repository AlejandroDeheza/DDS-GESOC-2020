package model;

import javax.persistence.*;

@Entity
@Table(name = "documentos_comerciales")
public class DocumentoComercial {
	
	@Id
	@GeneratedValue
	@Column(name = "id_documento_comercial")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_documento")
	private TipoDocumentoComercial tipoDoc;
	
	public DocumentoComercial(TipoDocumentoComercial tipoDoc){
		this.tipoDoc=tipoDoc;
	}
	
	public DocumentoComercial(){
		
	}
	
}
