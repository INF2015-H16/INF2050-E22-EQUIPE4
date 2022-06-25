
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
public abstract class Lotissement{
    //Constantes
    static final int NB_SERVICE_BASE = 2;
    static final int MONTANT_BASE = 500;
    
    //Variable definies dans le constructeur
    String description;
    int nbDroitsPassages;
    int nbServices;
    int superficie;
    String dateMesure;
    
    //Variable obtenues apres calculs()
    double montantServices;
    double montantDroitDePassages;
    double valeurSuperficie;
    double valeurTotalLot;
    
    //Variables defini dans l'appel setPrixMinMax()
    double prixMax;
    double prixMin;

    Lotissement(JSONObject jsonObject) {
        description = (jsonObject.getString("description"));
        nbDroitsPassages = (jsonObject.getInt("nombre_droits_passage"));
        dateMesure = (jsonObject.getString("date_mesure"));
        nbServices = (jsonObject.getInt("nombre_services") + NB_SERVICE_BASE);
        superficie = (jsonObject.getInt("superficie"));
    }
    
    void setPrixMinMax(double[] prixMinMax){
        this.prixMin = prixMinMax[0];
        this.prixMax = prixMinMax[1];
    }
    
    abstract void calculs();

    public String getDescription() {
        return description;
    }

    public double getValeurTotalLot() {
        return valeurTotalLot;
    }
}
