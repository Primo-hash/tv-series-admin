package no.abdulhadi.tvseriesadmin.exception;

public class InjectorException extends RuntimeException {

    private final String message;

    public InjectorException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
