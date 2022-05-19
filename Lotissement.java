
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author 
 */
public class Lotissement {
    //Valeurs recues
    private String description;
    private int nbDroitsPassages;
    private int nbServices;
    private int superficie;
    private String dateMesure; 
    //Valeurs calculees
    private double valeurSuperficie;
    private double montantDroitsPassage;
    private double montantServices;
    private double valeurLot;
    
    public Lotissement(JSONObject source){
        description = source.getString("description");
        nbDroitsPassages = source.getInt("nombre_droits_passage");
        nbServices = source.getInt("nombre_services");
        superficie = source.getInt("superficie");
        dateMesure = source.getString("dateMesure");
    }
    
    //getters des valeurs recues
    public String getDescription(){
        return description;
    }

    public int getNbDroitsPassages(){
        return nbDroitsPassages;
    }

    public int getNbServices(){
        return nbServices;
    }
    
    public int getSuperficie(){
        return superficie;
    }

    public int getDateMesure(){
        return dateMesure;
    }

    //setters des valeurs calculees
    public void setValeurSuperficie(double montant){
        valeurSuperficie = montant;
    }

    public void setMontantDroitsPassage(double montant){
        montantDroitsPassage = montant;
    }

    public void setMontantServices(double montant){
        montantServices = montant;
    }

    public void setValeurLot(){
        valeurLot = valeurSuperficie + montantDroitsPassage + montantServices;
    }


    public JSONObject rapport(){
    //append les lignes demandees
    }
    
}
