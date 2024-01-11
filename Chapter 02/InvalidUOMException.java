package chapter2;

public class InvalidUOMException extends RuntimeException {

	private static final long serialVersionUID = -7272497685658403328L;

	public InvalidUOMException() {
		super("The UOM of this entry could not be determined.");
	}
}
