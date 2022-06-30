
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author  Leonid
 *          Goldlen
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

    Lotissement(JSONObject JSONSource) {
        setDescription(JSONSource, description);
        setNbDroitsPassages(JSONSource, nbDroitsPassages);
        setDateMesure(JSONSource, dateMesure);
        setNbServices(JSONSource, nbServices, NB_SERVICE_BASE);
        setSuperficie(JSONSource, superficie);
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
