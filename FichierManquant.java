
package evaluationfonciere;

/**
 *
 * @author Leonid
 */
public class FichierManquant extends Exception {

    /**
     * Creates a new instance of <code>FichierManquant</code> without detail
     * message.
     */
    public FichierManquant() {
    }

    /**
     * Constructs an instance of <code>FichierManquant</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FichierManquant(String msg) {
        super(msg);
    }
}
