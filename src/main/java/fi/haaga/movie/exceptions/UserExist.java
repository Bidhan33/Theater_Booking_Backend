package fi.haaga.movie.exceptions;

public class UserExist extends RuntimeException {
    private static final long serialVersionUID = -4666349320340656440L;

	public UserExist() {
        super("User Already Exists with this EmailId");
    }
}
