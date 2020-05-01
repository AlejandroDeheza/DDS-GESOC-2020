
public class CajeroAutomatico implements MedioDePago{
	TipoMedio codTipo = TipoMedio.ATM;
	
	void abonarOperacion(float valor) {
    }

}


public enum RedBancaria{
    LINK, BANELCO;
}
