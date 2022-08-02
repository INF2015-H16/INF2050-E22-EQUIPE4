package evaluationfonciere;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import net.sf.json.JSONObject;

/**
 * Classe de methodes qui lit le fichier d'entree et
 * ecrit le fichier de sortie. (JSON)
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
 */
public class Traitement {
    
    public String lireFichierEntree(String[] args) throws FormatInvalide {
        if(args.length != 2){
            throw new FormatInvalide("Fichier d'entree ou de sortie manquant.");
        }
        try {
            String texteSource = new String(Files.readAllBytes(Paths.get(args[0])));
            return texteSource;
        } catch (IOException e) {
            throw new FormatInvalide("Erreur dans la lecture du fichier d'entree.");
        }
    }
    
    public void ecrireFichierSortie(String args, JSONObject contenuSortie) throws FormatInvalide {
       try (FileWriter writer = new FileWriter(new File(args))) {
           String rapportString = contenuSortie.toString();
           writer.write(rapportString);
       } catch (IOException e) {
           throw new FormatInvalide("Erreur dans l'ecriture du fichier de sortie.");
       }
    }

    public JSONObject contenuSortie(JSONObject JSONSource){
        try {
            //Peut lancer des exceptions :
            Terrain terrain = new Terrain(JSONSource);
            //Ne devrait pas lancer des exceptions :
            JSONObject rapport = new RapportTerrain().rapport(terrain);
            return rapport;
        } catch (FormatInvalide erreur) {
            JSONObject objetErreur = new JSONObject();
            objetErreur.accumulate("message", erreur.getMessage());
            return objetErreur;
        }
    }
}
