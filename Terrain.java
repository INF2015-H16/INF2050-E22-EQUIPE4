package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 * Classe Terrain avec les methodes de calcul pour la valeur totale/les taxes
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
    private double prixMax;
    private double prixMin;

    
    //Constantes
    static final double PRIX_FIXE = 733.77;
    static final double TAUX_SCOLAIRE = 0.012;
    static final double TAUX_MUNICIPALE = 0.025;
    
    //Validateur des valeurs utilise dans les setters
    ValiderValeursTerrain valider;
    
    public Terrain(JSONObject JSONSource) throws FormatInvalide {
        this.valider = new ValiderValeursTerrain(JSONSource);
        
        setLotissements();
        setPrixMax();
        setPrixMin();
        
        mettreAJourStatistiques();
    }
    
    private void mettreAJourStatistiques() {
        Statistique.mettreAJour(this);
    }
    
    private void setLotissements() throws FormatInvalide{
        this.lotissements = valider.lotissements();
    }
    
    private void setPrixMax() throws FormatInvalide{
        this.prixMax = valider.prixMax();
    }
    
    private void setPrixMin() throws FormatInvalide{
        this.prixMin = valider.prixMin();
    }
    
    public Lotissement[] getLotissements() {
        return lotissements;
    }
    
    public double getValeurFonciereTotale(){
        double resultat = PRIX_FIXE;
        for (Lotissement lot : lotissements) {
            resultat += lot.getValeurTotalLot();
        }
        return arrondiAu5sous(resultat);
    }    
    
    public double getTaxeScolaire(){
        return arrondiAu5sous(getValeurFonciereTotale() * TAUX_SCOLAIRE);
    }
    
    public double getTaxeMunicipale(){
        return arrondiAu5sous(getValeurFonciereTotale() * TAUX_MUNICIPALE);
    }

    private double arrondiAu5sous(double montant) {
        return Math.round(montant * 20) / 20;
    }
    
    public double getPrixMax(){
        return prixMax;
    }
    
    public double getPrixMin(){
        return prixMin;
    }
}
