
package evaluationfonciere;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
public class EvaluationFonciere {

    /**
     * @param fileName
     * @return 
     * @throws java.io.IOException
     */
    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
    
    public static void main(String[] args) {
        String texteSource = "";
        Terrain terrain;
        JSONObject JSONEvaluation;
        String texteEvaluation;
        File fichier = new File(args[1]);
        JSONObject JSONSource;
        
        
        try {
            texteSource = readFileAsString(args[0]);
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
        
        JSONSource = JSONObject.fromObject(texteSource);
        terrain = new Terrain(JSONSource);

        switch(JSONSource.getInt("type_terrain")){
            case 0 -> terrain.agricole();
            case 1 -> terrain.residentiel();
            case 2 -> terrain.commercial();
        }
        
        JSONEvaluation = terrain.rapport();
        texteEvaluation = JSONEvaluation.toString();
        System.out.println(texteEvaluation);

        try (FileWriter writer = new FileWriter(fichier)) {
            writer.write(texteEvaluation);
        } catch (IOException e) {
            System.out.printf("Une erreur s'est produite %s", e.getMessage());
        }
    }
    
}
