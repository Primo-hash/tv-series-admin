package no.abdulhadi.tvseriesadmin.exception;

public class ReportProducerException extends RuntimeException {

    private final String message;

    public ReportProducerException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
