package fi.haaga.movie.exceptions;

public class TicketDoesNotExist extends RuntimeException {

    public TicketDoesNotExist(String message) {
        super(message);
    }
}
