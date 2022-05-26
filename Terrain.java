
package evaluationfonciere;

/**
 *
 * @author 
 */
abstract class Terrain {
    //valeurs recues
    private final int nbrServiceBase = 2;
    private final double prixFixe = 733.77;
    private final double montantBase = 500;    
    private double prixMin;
    private double prixMax;
    //valeurs calculees
    private double valeurFonciereTotale;
    private double taxeScolaire;
    private double taxeMunicipale;
    private ArrayList<Lotissement> lotissements;
    
    public Terrain(JSONObject JSONSource){
        //Simple initialisation
        this.prixMin = JSONSource.getDouble("prix_min");
        this.prixMax = JSONSource.getDouble("prix_max");
        this.lotissements = initialiserLot(JSONSource.getJSONArray("lotissements"));
        //Calculs specifiques des valeurs dans lotissements
        this.calculValeurParSuperficie();
        this.calculDroitPassage();
        this.calculMontantServices();
        //Calculs generaux
        this.calculValeurParLot();
        this.calculValeurFonciereTotale();
        this.calculTaxeScolaire();
        this.calculTaxeMunicipale(); 
    }
    
    //Toutes les methodes abstraites agissent sur this.lotissements
    private abstract void calculValeurParSuperficie();    
    private abstract void calculDroitPassage();    
    private abstract void calculMontantServices();    
    private abstract void calculValeurParLot();    
    
    public void calculValeurParLot(){
        for(Lotissement lot: this.lotissements){
            lot.setValeurLot();
        }
    }
    
    private void calculValeurFonciereTotale(){
        this.valeurFonciereToTale = prixFixe;
        for (Lotissement lot: this.lotissements){
            this.valeurFonciereTotale += lot.getValeurParLot();
        }
        this.valeurFonciereTotale = arrondiAu5sousSuperieur(valeurFonciereTotale);
    }    
    
    private void calculTaxeScolaire(){
        this.taxeScolaire = arrondiAu5sousSuperieur(0.012 * this.valeurFonciereTotale);
    }
    
    private void calculTaxeMunicipale(){
        this.taxeMunicipale = arrondiAu5sousSuperieur(0.025 * this.valeurFonciereTotale);
    }

    public static double arrondiAu5sousSuperieur(double montant){
        return (double) (Math.ceil(montant*20)/20);
    }
    
    private ArrayList<Lotissement> initialiserLot(JSONArray source){
        lotissements = new ArrayList<Lotissement>;
        for (JSONObject JSONlot: source){
            Lotissement lot = new Lotissement(JSONlot);            
            lotissements.append(lot);
        }
        return lotissements;
    }
    
    public JSONObject rapport{
        //append les lignes voulues avant le lotissement
        //append avec appel de rapport() dans la classe Lotissement
    }
}
