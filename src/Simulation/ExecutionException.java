package Simulation;

/**
 * The Exception class thrown when an error is detected with execution.
 * 
 * 
 * @author Vincent
 *
 */
public class ExecutionException extends Exception {

    /**
     * Create an exception based on an issue in our code.
     */
    public ExecutionException () {
        super();
    }

    /**
     * Create an exception based on an issue in our code with a given message.
     */
    public ExecutionException (String message) {
        super(message);
    }

    /**
     * Create an exception based on a caught exception with a different message.
     *
     * @param cause
     * @param message
     */
    public ExecutionException (Throwable cause, String message) {
        super(String.format(message), cause);
    }
}
