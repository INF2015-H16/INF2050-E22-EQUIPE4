
package evaluationfonciere;
import net.sf.json.JSONObject;

/**
 *
 * @author 
 */
public class Lotissement {
    String description;
    int nbServices;
    int nbDroitsPassages;
    int superficie;
    String dateMesure; //Inutile a ce que je comprend de la sortie attendue
    double valeur;
    
    public Lotissement(JSONObject source){
        description = source.getString("description");
        nbServices = source.getInt("nombre_services");
        nbDroitsPassages = source.getInt("nombre_droits_passage");
        superficie = source.getInt("superficie");
        dateMesure = source.getString("date_mesure");
    }
}
