
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
public class Lotissement{
    double montantServices;
    double montantDroitDePassages;
    double valeurLot;
    
    String description;
    int nbServices;
    int nbDroitsPassages;
    int superficie;
    String dateMesure;
    
    final int nbrServiceBase = 2;
    final int montantDeBase = 500;
    
    Lotissement(JSONObject jsonObject) {
        this.description = jsonObject.getString("description");
        this.nbDroitsPassages = jsonObject.getInt("nombre_droits_passage");
        this.dateMesure = jsonObject.getString("date_mesure");
        this.nbServices = jsonObject.getInt("nombre_services") + nbrServiceBase;
        this.superficie = jsonObject.getInt("superficie");
    }

    void residentiel(double prixMax, double prixMin) {
        valeurLot = superficie * ((prixMax + prixMin)/2);
        montantDroitDePassages = montantDeBase - (nbDroitsPassages * (valeurLot/10));
        
        if(superficie <= 500) {
            montantServices = 0;
        } else if(superficie > 500 && superficie <= 10000){
            montantServices = 500 * nbServices;
        } else if(superficie > 10000){
            montantServices = 1000 * nbServices;
        }
    }

    void agricole(double prixMin) {
        valeurLot = superficie * prixMin;
        montantDroitDePassages = montantDeBase - (nbDroitsPassages * (valeurLot/20));
        montantServices = 0;
    }

    void commercial(double prixMax) {
        valeurLot = superficie * prixMax;
        montantDroitDePassages = montantDeBase - (nbDroitsPassages * (15/100) * valeurLot);
        
        if(superficie <= 500) {
            montantServices = 500 * nbServices;
        } else if(superficie > 500){
            montantServices = 1500 * nbServices;
        }
        
        if(montantServices > 5000){
            montantServices = 5000;
        }
    }
}
