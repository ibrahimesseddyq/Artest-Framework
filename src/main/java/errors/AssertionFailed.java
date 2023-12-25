package errors;

public class AssertionFailed extends AssertionError {
    private static final long serialVersionUID = 1L;
    public AssertionFailed() {
    }
    public AssertionFailed(String message) {
        super(defaultString(message));
    }

    private static String defaultString(String message) {
        return message == null ? "" : message;
    }
}
