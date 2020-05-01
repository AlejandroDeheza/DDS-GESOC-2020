
public class TarjetaDeCredito implements MedioDePago{
	TipoMedio codTipo = TipoMedio.CREDIT_CARD;
	 
	
	int numeroTarjeta;
	String titular;
	int codigoSeguridad;
	int cuotas;
	
	void abonarOperacion(float valor) {
		
	}

}

/*TODO
 * Agregar clases de todos los tipos definidos por mercadoLibre: https://www.mercadopago.com.ar/developers/es/guides/localization/payment-methods/ 
 *  |- Usar herencia.
 *  |- Pensar otra alternativa con composición.
