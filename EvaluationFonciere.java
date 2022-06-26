
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
        String texteEvaluation = "";
        try {
            String texteSource = lireFichierEntree(args);
            JSONObject JSONSource = JSONObject.fromObject(texteSource);
            Terrain terrain = new Terrain(JSONSource);
            texteEvaluation = terrain.rapport();
        } catch (FormatInvalide erreur) {
            System.out.print(erreur.getMessage());
        } finally {
            if(!texteEvaluation.equals("")){
                ecrireFichierSortie(args, texteEvaluation);
            }
        }
    }

    private static void ecrireFichierSortie(String[] args, String texteEvaluation) {
        try (FileWriter writer = new FileWriter(new File(args[1]))) {
            writer.write(texteEvaluation);
        } catch (IOException e) {
            System.out.printf("Erreur dans l'ecriture du fichier de sortie");
        }
    }

    private static String lireFichierEntree(String[] args) throws FormatInvalide {
        if(args.length != 2){
            throw new FormatInvalide("Fichier d'entree ou de sortie manquant");
        }
        String texteSource;
        try {
            texteSource = new String(Files.readAllBytes(Paths.get(args[0])));
        } catch (IOException e) {
            throw new FormatInvalide("Erreur dans la lecture du fichier d'entree");
        }
        return texteSource;
    }
}
