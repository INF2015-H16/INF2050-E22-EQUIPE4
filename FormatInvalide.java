
package evaluationfonciere;

/**
 *
 * @author Leonid
 */
public class FormatInvalide extends Exception {

    /**
     * Creates a new instance of <code>FormatInvalide</code> without detail
     * message.
     */
    public FormatInvalide() {
    }

    /**
     * Constructs an instance of <code>FormatInvalide</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FormatInvalide(String msg) {
        super(msg);
    }
}
