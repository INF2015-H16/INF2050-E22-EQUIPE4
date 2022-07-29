
package evaluationfonciere;


/**
 * Classe main du programme qui prend en parametre un fichier d'entree pour
 * calculer et creer un fichier de sortie contenant les informations pertinentes 
 * du terrain.
 *
 * @param args[0] fichier d'entree
 * @param args[1] fichier de sortie
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
 */
public class EvaluationFonciere {
    public static void main(String[] args) {
        if (args.length == 1) {
            Statistique.traiter(args[0]);
        } else {
            Traitement.traiter(args);
        }
    }
}
