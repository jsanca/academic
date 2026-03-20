package jsanca.download.internal.exception;

public class StrategyNotFoundException extends RuntimeException {

    public StrategyNotFoundException() {
    }

    public StrategyNotFoundException(String message) {
        super(message);
    }

    public StrategyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StrategyNotFoundException(Throwable cause) {
        super(cause);
    }

    public StrategyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
