package exceptions;

public class NoMorePlayersException extends RuntimeException {
	public NoMorePlayersException(String message) {
		super(message);
	}
}
