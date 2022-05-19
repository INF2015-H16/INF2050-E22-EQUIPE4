
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author 
 */
public class Lotissement {
    //Valeurs recues
    String description;
    int nbServices;
    int nbDroitsPassages;
    int superficie;
    String dateMesure; 
    //Valeurs calculees
    double valeurParSuperficie;
    double valeurDroitPassage;
    double montantServices;
    double valeurParLot;
    
    public Lotissement(JSONObject source){
        
    }
}
