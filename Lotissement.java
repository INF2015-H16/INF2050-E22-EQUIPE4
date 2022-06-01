
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
public class Lotissement{
    final int nbrServiceBase = 2;
    final int montantDeBase = 500;

    int nbDroitsPassages;
    int nbServices;
    int superficie;
    
    double montantServices;
    double montantDroitDePassages;
    double valeurLot;

    String description;
    String dateMesure;

    public String getDescription() {
        return description;
    }

    public double getValeurLot() {
        return valeurLot;
    }

    public String getDateMesure() {
        return dateMesure;
    }

    public int getNbDroitsPassages() {
        return nbDroitsPassages;
    }

    public int getNbServices() {
        return nbServices;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateMesure(String dateMesure) {
        this.dateMesure = dateMesure;
    }

    public void setNbDroitsPassages(int nbDroitsPassages) {
        this.nbDroitsPassages = nbDroitsPassages;
    }

    public void setNbServices(int nbServices) {
        this.nbServices = nbServices;
    }
    
    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }
    
    Lotissement(JSONObject jsonObject) {
        jsonObject.setDescription(jsonObject.getString("description"));
        jsonObject.setNbDroitsPassages(jsonObject.getInt("nombre_droits_passage"));
        jsonObject.setDateMesure(jsonObject.getString("date_mesure"));
        jsonObject.setNbServices(jsonObject.getInt("nombre_services") + nbrServiceBase);
        jsonObject.setSuperficie(jsonObject.getInt("superficie"));
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
