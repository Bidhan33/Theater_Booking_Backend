package fi.haaga.movie.exceptions;

public class MovieDoesNotExists extends RuntimeException {
	private static final long serialVersionUID = -5385129013790060351L;

	public MovieDoesNotExists() {
		super("The movie you're looking for cannot be found in the database. Please verify the title or try searching for a different movie");
	}
}
