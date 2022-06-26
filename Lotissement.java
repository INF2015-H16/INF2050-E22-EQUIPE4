
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

    public void verifierValeurs() throws FormatInvalide {
        if(nbDroitsPassages > 10){
            throw new FormatInvalide("Le nombre de droits de passage est superieur a 10");
        } else if(nbDroitsPassages < 0){
            throw new FormatInvalide("Le nombre de droits de passage est inferieur a 0");
        }
        if(nbServices > 5){
            throw new FormatInvalide("Le nombre de services est superieur a 5");
        } else if(nbServices > 5){
            throw new FormatInvalide("Le nombre de services est inferieur a 0");
        }
        if(superficie > 50000){
            throw new FormatInvalide("La superficie ne peut pas etre supérieure a 50000 metres carres");
        } else if(superficie < 0){
            throw new FormatInvalide("La superficie ne peut pas etre négative");
        }
    }
}
