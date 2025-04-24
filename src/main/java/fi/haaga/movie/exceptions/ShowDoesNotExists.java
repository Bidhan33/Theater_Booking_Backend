package fi.haaga.movie.exceptions;

public class ShowDoesNotExists extends RuntimeException {

	private static final long serialVersionUID = -4436119261176031165L;

	public ShowDoesNotExists() {
		super("The show you are looking for does not exist or is unavailable. Please verify the details or select a different show");
	}
}
