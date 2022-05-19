
package evaluationfonciere;

/**
 *
 * @author 
 */
public class EvaluationFonciere {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String texteSource = cIOUtils.toString(new FileInputStream(args[0]), "UTF-8");
        JSONObject JSONSource = new JSONObject(texteSource);
        
        String texteEvaluation;
        JSONObject JSONEvaluation;
        Terrain terrain;
        File fichier;
        
        switch(JSONSource.getInt("type_terrain")){
            case 0:
                terrain = new TerrainAgricole(JSONSource);
            break;
            case 1:
                terrain = new TerrainResidentiel(JSONSource);
            break;
            case 2:
                terrain = new TerrainCommercial(JSONSource);
            break;
        }
        
        JSONEvaluation = terrain.rapport();
        texteEvaluation = JSONEvaluation.toString();
        fichier = new File(args[1]);
        FileUtils.writeStringToFile(fichier, texteEvaluation, "UTF-8")
        
    }    
    
}
