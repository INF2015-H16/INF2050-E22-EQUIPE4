package evaluationfonciere;

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
    private Lotissement[] lotissements;
    
    //Constantes
    static final double PRIX_FIXE = 733.77;
    static final double TAUX_SCOLAIRE = 0.012;
    static final double TAUX_MUNICIPALE = 0.025;
    
    //Validateur des valeurs utilise dans les setters
    ValiderValeursTerrain valider;
    
    public Terrain(JSONObject JSONSource) throws FormatInvalide {
        this.valider = new ValiderValeursTerrain(JSONSource);
        
        setLotissements();
    }
    
    private void setLotissements() throws FormatInvalide{
        this.lotissements = valider.lotissements();
    }
    
    public Lotissement[] getLotissements() {
        return lotissements;
    }
    
    public double getValeurFonciereTotale(){
        double resultat = PRIX_FIXE;
        for (Lotissement lot : lotissements) {
            resultat += lot.getValeurTotalLot();
        }
        return arrondiAu5sousSuperieur(resultat);
    }    
    
    public double getTaxeScolaire(){
        return arrondiAu5sousSuperieur(getValeurFonciereTotale() * TAUX_SCOLAIRE);
    }
    
    public double getTaxeMunicipale(){
        return arrondiAu5sousSuperieur(getValeurFonciereTotale() * TAUX_MUNICIPALE);
    }

    private double arrondiAu5sousSuperieur(double montant) {
        return Math.ceil(montant * 20) / 20;
    }
}
