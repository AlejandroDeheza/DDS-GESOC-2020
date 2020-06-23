package medioDePago;

public class TarjetaDeCredito implements MedioDePago {
	//TipoMedioDePago codTipoMedioDePago = TipoMedioDePago.CREDIT_CARD;

	private int numeroTarjeta;
	private String titular;
	private int codigoSeguridad;
	private int cuotas;

	public void abonarOperacion(float valor) {

	}

}

/*
 * TODO Agregar clases de todos los tipos definidos por mercadoLibre:
 * https://www.mercadopago.com.ar/developers/es/guides/localization/payment-methods/ |- Usar herencia. |- Pensar otra alternativa con composicion.
 */