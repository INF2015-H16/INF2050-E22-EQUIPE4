
package evaluationfonciere;

import java.util.stream.Stream;
import net.sf.json.JSONObject;

/**
 * Classe parent des 3 types de Lotissement avec les variables communes
 * des lotissements et les methodes abstraites de calcul. Les parametres 
 * de constructeur sont valides avant d'etre affecte.
 * 
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
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
    
    //Validateur des valeurs utilise dans les setters
    ValiderValeursLot valider;

    Lotissement(JSONObject JSONSource) throws FormatInvalide {
        valider = new ValiderValeursLot(JSONSource);
        
        setDescription();
        setNbDroitsPassages();
        setDateMesure();
        setNbServices();
        setSuperficie();
    }

    private void setDescription() throws FormatInvalide{
        this.description = valider.description();
    }

    private void setNbDroitsPassages() throws FormatInvalide {
        this.nbDroitsPassages = valider.nbDroitsPassages();
    }

    private void setDateMesure() throws FormatInvalide {
        this.dateMesure = valider.dateMesure();
    }

    private void setNbServices() throws FormatInvalide {
        this.nbServices = valider.nbServices() + NB_SERVICE_BASE;
    }

    private void setSuperficie() throws FormatInvalide {
        this.superficie = valider.superficie();
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
    
    public int getSuperficie() {
        return superficie;
    }

    public String getDateMesure() {
        return dateMesure;
    }
}
