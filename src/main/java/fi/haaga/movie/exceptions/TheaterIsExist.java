package fi.haaga.movie.exceptions;

public class TheaterIsExist extends RuntimeException{
    private static final long serialVersionUID = 6386810783666583528L;

	public TheaterIsExist() {
        super("The theater is already established at this address. No further action is required to set up at this location");
    }
}
