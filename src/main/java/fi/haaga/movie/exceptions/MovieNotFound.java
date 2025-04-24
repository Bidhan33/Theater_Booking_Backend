package fi.haaga.movie.exceptions;

public class MovieNotFound extends RuntimeException {
    private static final long serialVersionUID = 1234567890L;

    public MovieNotFound() {
        super("Movie not found with the provided ID");
    }

    public MovieNotFound(String message) {
        super(message);
    }
}
