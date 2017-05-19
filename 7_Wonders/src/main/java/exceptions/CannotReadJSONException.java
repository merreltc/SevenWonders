package exceptions;

public class CannotReadJSONException extends RuntimeException {
	public CannotReadJSONException(String message) {
		super(message);
	}
}
