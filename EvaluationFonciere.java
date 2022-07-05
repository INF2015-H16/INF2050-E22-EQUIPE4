
package evaluationfonciere;

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
        Traitement traitement = new Traitement();
        try {
            String texteSource = traitement.lireFichierEntree(args);
            JSONObject JSONSource = JSONObject.fromObject(texteSource);
            JSONObject contenuSortie = traitement.contenuSortie(JSONSource);
            traitement.ecrireFichierSortie(args[1], contenuSortie);
        } catch (FormatInvalide erreur) {
            System.out.print(erreur.getMessage());
        }
    }
}
