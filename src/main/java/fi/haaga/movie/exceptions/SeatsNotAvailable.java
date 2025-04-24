package fi.haaga.movie.exceptions;

public class SeatsNotAvailable extends RuntimeException {
	private static final long serialVersionUID = 1497113945165128412L;

	public SeatsNotAvailable() {
		super("The seats you requested are currently unavailable. Please try selecting different seats or check for availability at another time");
	}
}
