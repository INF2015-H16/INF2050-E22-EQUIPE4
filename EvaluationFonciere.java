
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
        String texteSource = "";
        try {
            texteSource = new String(Files.readAllBytes(Paths.get(args[0])));
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }

        JSONObject JSONSource = JSONObject.fromObject(texteSource);
        Terrain terrain = new Terrain(JSONSource);

        JSONObject JSONEvaluation = terrain.rapport();
        String texteEvaluation = JSONEvaluation.toString();

        try (FileWriter writer = new FileWriter(new File(args[1]))) {
            writer.write(texteEvaluation);
        } catch (IOException e) {
            System.out.printf("Une erreur s'est produite %s", e.getMessage());
        }
    }
}
