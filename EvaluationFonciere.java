
package evaluationfonciere;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import net.sf.json.JSONObject;

/**
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
 */
public class EvaluationFonciere {
    public static void main(String[] args) {
        try {
            String texteSource = lireFichierEntree(args);
            JSONObject JSONSource = JSONObject.fromObject(texteSource);
            String texteEvaluation = retournerContenuSortie(JSONSource);
            ecrireFichierSortie(args[1], texteEvaluation);
        } catch (FormatInvalide erreur) {
            System.out.print(erreur.getMessage());
        }
    }
}
