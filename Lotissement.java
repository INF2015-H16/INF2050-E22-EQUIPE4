
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author 
 */
public class Lotissement {
    //Valeurs recues
    private String description;
    private int nbServices;
    private int nbDroitsPassages;
    private int superficie;
    private String dateMesure; 
    //Valeurs calculees
    private double valeurParSuperficie;
    private double valeurDroitPassage;
    private double montantServices;
    private double valeurParLot;
    
    public Lotissement(JSONObject source){
    //initiliser seulement les valeurs recues    
    }
    
    //getters des valeurs recues
    
    //setters des valeurs calculees
    
    public JSONObject rapport(){
    //append les lignes demandees
    }
    
}
