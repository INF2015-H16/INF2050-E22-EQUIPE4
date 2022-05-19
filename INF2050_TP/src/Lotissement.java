
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
        this.description = source.getString("description");
        this.nbServices = source.getInt("nombre_services");
        this.nbDroitsPassages = source.getInt("nombre_droits_passage");
        this.superficie = source.getInt("superficie");
        this.dateMesure = source.getString("date_mesure");
    }
}
