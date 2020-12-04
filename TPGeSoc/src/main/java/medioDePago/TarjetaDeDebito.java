package medioDePago;

public class TarjetaDeDebito implements MedioDePago {
	//TipoMedioDePago codTipoMedioDaPago = TipoMedioDePago.DEBIT_CARD;
	private int numeroTarjeta;
	private String titular;
	private int codigoSeguridad;

	public void abonarOperacion(float valor) {
	}

}
