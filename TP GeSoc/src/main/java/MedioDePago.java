
public interface MedioDePago {
	void abonarOperacion(float valor);
}


enum TipoMedio {
	CREDIT_CARD, DEBIT_CARD, CASH, ATM, ACCOUNT_MONEY;
}

enum IDMedioPago{
	VISA, MASTER, AMEX; 
	

}