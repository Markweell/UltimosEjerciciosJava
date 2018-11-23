package pgn.negocio;

@SuppressWarnings("serial")
public class CocheNoExistenteException extends Exception {

	CocheNoExistenteException(String mensaje){
		super(mensaje);
	}
}
