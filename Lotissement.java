
package evaluationfonciere;

import net.sf.json.JSONException;
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
    
    //Variables dérivées
    protected abstract double montantServices();
    protected abstract double montantDroitDePassages();
    protected abstract double valeurSuperficie();

    //Variables defini dans l'appel setPrixMinMax()
    double prixMax;
    double prixMin;

    Lotissement(JSONObject JSONSource) throws FormatInvalide {
        setDescription(JSONSource);
        setNbDroitsPassages(JSONSource);
        setDateMesure(JSONSource);
        setNbServices(JSONSource);
        setSuperficie(JSONSource);
    }

    private void setDescription(JSONObject JSONSource) throws FormatInvalide{
        try {
            description = JSONSource.getString("description");
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <description> d'un lot est manquante dans le fichier d'entree");
        }
    }

    private void setNbDroitsPassages(JSONObject JSONSource) throws FormatInvalide {
        try {
            nbDroitsPassages = JSONSource.getInt("nombre_droits_passage");
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <nombre_droits_passage> d'un lot est manquante dans le fichier d'entree");
        }
    }

    private void setDateMesure(JSONObject JSONSource) throws FormatInvalide {
        try {
            dateMesure = JSONSource.getString("date_mesure");
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <date_mesure> d'un lot est manquante dans le fichier d'entree");
        }
    }

    private void setNbServices(JSONObject JSONSource) throws FormatInvalide {
        try {
            nbServices = JSONSource.getInt("nombre_services") + NB_SERVICE_BASE;
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <nombre_services> d'un lot est manquante dans le fichier d'entree");
        }
    }

    private void setSuperficie(JSONObject JSONSource) throws FormatInvalide {
        try {
            superficie = JSONSource.getInt("superficie");
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <superficie> d'un lot est manquante dans le fichier d'entree");
        }
    }

    void setPrixMinMax(double[] prixMinMax){
        this.prixMin = prixMinMax[0];
        this.prixMax = prixMinMax[1];
    }

    public String getDescription() {
        return description;
    }

    public double getValeurTotalLot() {
        return valeurSuperficie() + montantDroitDePassages() + montantServices();
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
