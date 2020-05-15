package model;

public class TarjetaDeDebito implements MedioDePago {
	TipoMedioDePago codTipoMedioDaPago = TipoMedioDePago.DEBIT_CARD;
	int numeroTarjeta;
	String titular;
	int codigoSeguridad;

	public void abonarOperacion(float valor) {
	}

}
