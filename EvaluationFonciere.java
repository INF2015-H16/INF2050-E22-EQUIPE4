
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
    
     private static void ecrireFichierSortie(String args, String texteEvaluation) throws FormatInvalide {
        try (FileWriter writer = new FileWriter(new File(args))) {
            writer.write(texteEvaluation);
        } catch (IOException e) {
            throw new FormatInvalide("Erreur dans l'ecriture du fichier de sortie");
        }
     }
    
    private static String retournerContenuSortie(JSONObject JSONSource){
        try {
            //Ici des exception peuvent etre lance
            Terrain terrain = new Terrain(JSONSource);
            //Ici non
            //Ici sera l'appel de Rapport.getRapport(terrain);
            return terrain.rapport();
        } catch (FormatInvalide erreur) {
            return "{\"message\": \"" + erreur.getMessage() + ".\"}";
        }
    }

    private static String lireFichierEntree(String[] args) throws FormatInvalide {
        if(args.length != 2){
            throw new FormatInvalide("Fichier d'entree ou de sortie manquant");
        }
        try {
            String texteSource = new String(Files.readAllBytes(Paths.get(args[0])));
            return texteSource;
        } catch (IOException e) {
            throw new FormatInvalide("Erreur dans la lecture du fichier d'entree");
        }
    }
}
