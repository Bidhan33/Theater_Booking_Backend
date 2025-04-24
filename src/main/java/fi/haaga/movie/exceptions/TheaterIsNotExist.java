package fi.haaga.movie.exceptions;

public class TheaterIsNotExist extends RuntimeException{
    private static final long serialVersionUID = -80039152090012599L;

	public TheaterIsNotExist() {
        super("The theater is not located at this address. Please verify the location or check for an alternative venue nearby");
    }
}
