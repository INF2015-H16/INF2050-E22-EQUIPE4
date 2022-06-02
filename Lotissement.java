
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
public class Lotissement{
    private static final int NB_SERVICE_BASE = 2;
    private static final int MONTANT_BASE = 500;

    private int nbDroitsPassages;
    private int nbServices;
    private int superficie;
    
    private double montantServices;
    private double montantDroitDePassages;
    private double valeurLot;

    private String description;
    private String dateMesure;

    public String getDescription() {
        return description;
    }

    public double getValeurLot() {
        return valeurLot;
    }

    /* public String getDateMesure() {
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
    
    public double getMontantServices() {
        return montantServices;
    }
    
    public double getMontantDroitDePassages() {
        return montantDroitDePassages;
    }*/

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
        setDescription(jsonObject.getString("description"));
        setNbDroitsPassages(jsonObject.getInt("nombre_droits_passage"));
        setDateMesure(jsonObject.getString("date_mesure"));
        setNbServices(jsonObject.getInt("nombre_services") + NB_SERVICE_BASE);
        setSuperficie(jsonObject.getInt("superficie"));
    }

    void residentiel(double prixMax, double prixMin) {
        valeurLot = superficie * ((prixMax + prixMin)/2);
        montantDroitDePassages = MONTANT_BASE - (nbDroitsPassages * (valeurLot/10));
        
        if(superficie <= 500) {
            montantServices = 0;
        } else if(superficie <= 10000){
            montantServices = 500 * nbServices;
        } else {
            montantServices = 1000 * nbServices;
        }
    }

    void agricole(double prixMin) {
        valeurLot = superficie * prixMin;
        montantDroitDePassages = MONTANT_BASE - (nbDroitsPassages * (valeurLot/20));
        montantServices = 0;
    }

    void commercial(double prixMax) {
        valeurLot = superficie * prixMax;
        montantDroitDePassages = MONTANT_BASE - (nbDroitsPassages * (0.15) * valeurLot);
        
        if(superficie <= 500) {
            montantServices = 500 * nbServices;
        } else {
            montantServices = 1500 * nbServices;
        }
        
        if(montantServices > 5000){
            montantServices = 5000;
        }
    }
}
