package evaluationfonciere;

import java.text.DecimalFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 *
 */
public class Terrain {
    //Definies dans le constructeur
    double[] prixMinMax = new double[2];
    int typeDeTerrain;
    Lotissement[] lotissements;
    
    //Constantes
    static final double PRIX_FIXE = 733.77;
    static final double TAUX_SCOLAIRE = 0.012;
    static final double TAUX_MUNICIPALE = 0.025;
    
    //Validateur des valeurs utilise dans les setters
    ValiderValeursTerrain valider;
    
    public Terrain(JSONObject JSONSource) throws FormatInvalide {
        this.valider = new ValiderValeursTerrain(JSONSource);
        
        setPrixMin();
        setPrixMax();
        setTypeTerrain();
        setLotissements();
    }

    private void setPrixMin() throws FormatInvalide {
        this.prixMinMax[0] = valider.prixMin();
    }

    private void setPrixMax() throws FormatInvalide {
        this.prixMinMax[1] = valider.prixMax();
    }
    
    private void setTypeTerrain() throws FormatInvalide {
        this.typeDeTerrain = valider.typeTerrain();
    }
    
    private void setLotissements() throws FormatInvalide{
        this.lotissements = valider.lotissements();
    }

    public String rapport(){
        return rapportJSONObject().toString();
    }

    private JSONObject rapportJSONObject(){
        JSONObject rapport = new JSONObject();
        rapport.accumulate("valeur_fonciere_totale", formaterDecimal(valeurFonciereTotale()) + " $");
        rapport.accumulate("taxe_scolaire", formaterDecimal(taxeScolaire()) + " $");
        rapport.accumulate("taxe_ municipale", formaterDecimal(taxeMunicipale()) + " $");
        JSONArray lots = creerRapportsLots();
        rapport.accumulate("lotissements", lots);

        return rapport;
    }

    private JSONArray creerRapportsLots() {
        JSONArray lots = new JSONArray();
        for (Lotissement lot : lotissements) {
            JSONObject lotUnique = new JSONObject();
            lotUnique.accumulate("description", lot.getDescription());
            lotUnique.accumulate("valeur_par_lot", formaterDecimal(lot.getValeurTotalLot()) + " $");

            lots.add(lotUnique);
        }
        return lots;
    }
    
    private double valeurFonciereTotale(){
        double resultat = PRIX_FIXE;
        for (Lotissement lot : lotissements) {
            resultat += lot.getValeurTotalLot();
        }
        return arrondiAu5sousSuperieur(resultat);
    }    
    
    private double taxeScolaire(){
        return arrondiAu5sousSuperieur(valeurFonciereTotale() * TAUX_SCOLAIRE);
    }
    
    private double taxeMunicipale(){
        return arrondiAu5sousSuperieur(valeurFonciereTotale() * TAUX_MUNICIPALE);
    }

    private String formaterDecimal(double valeur) {
        String pattern = "#.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(valeur);
    }

    public double arrondiAu5sousSuperieur(double montant) {
        return Math.ceil(montant * 20) / 20;
    }
}
