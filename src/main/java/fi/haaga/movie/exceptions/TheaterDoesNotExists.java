package fi.haaga.movie.exceptions;

public class TheaterDoesNotExists extends RuntimeException {
	private static final long serialVersionUID = 2885350098352987873L;

	public TheaterDoesNotExists() {
		super("The theater is not present or does not exist in the specified location. Please check the details or try a different venue");
	}
}
