
package com.proudmusketeers;

/**
 * Classe d'exception qui est lancee lorsque le format des valeurs dans 
 * le fichier d'entree ne sont pas valide.
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
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
