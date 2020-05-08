
public class TarjetaDeDebito implements MedioDePago{	
	TipoMedio codTipo = TipoMedio.DEBIT_CARD;
	int numeroTarjeta;
    String titular;
    int codigoSeguridad;
    
    public void abonarOperacion(float valor) {
    }

}
