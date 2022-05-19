
package evaluationfonciere;

/**
 *
 * @author 
 */
abstract class Terrain {
    private final int nbrServiceBase = 2;
    private final double prixFixe = 733.77;
    private final double montantBase = 500;    
    private double prixMin;
    private double prixMax;
    private double valeurFonciereTotale;
    private double taxeScolaire;
    private double taxeMunicipale;
    private Lotissement [] lotissements;
    
    public terrain(JSONObject JSONSource){
        //Simple initialisation
        this.prixMin = JSONSource.getDouble("prix_min");
        this.prixMax = JSONSource.getDouble("prix_max");
        this.lotissements = formaterLot(JSONSource.getJSONArray("lotissements"));
        //Calculs specifiques des valeurs dans lotissements
        this.calculTaxeParSuperficie();
        this.calculDroitPassage();
        this.calculMontantServices();
        this.calculValeurParLot();
        //Calculs generaux
        this.calculValeurFonciereTotale();
        this.calculTaxeScolaire();
        this.calculTaxeMunicipale(); 
    }
    
    //Toutes les methodes abstraites agissent sur this.lotissements
    private abstract void calculTaxeParSuperficie();    
    private abstract void calculDroitPassage();    
    private abstract void calculMontantServices();    
    private abstract void calculValeurParLot();    
    
    private void calculValeurFonciereTotale(){
        this.valeurFonciereToTale = 0;
        for (Lotissement lot: this.lotissements){
            this.valeurFonciereTotale += lot.getValeurParLot();
        }
    }    
    
    private void calculTaxeScolaire(){
        this.taxeScolaire = 0.012 * this.valeurFonciereTotale;
    }
    
    private void calculTaxeMunicipale(){
        this.taxeMunicipale = 0.025 * this.valeurFonciereTotale;
    }
    
    private Lotissement [] formaterLot(JSONArray source){
        lotissements = new Lotissement[source.size()];
        for (int i=0; i < source.size(); i++){
            lotissements[i] = new Lotissement(source.getJSONObject(i));
        }
        return lotissements;
    }
    
    public JSONObject rapport{
        //append les lignes voulues avant le lotissement
        //append avec appel de rapport() dans la classe Lotissement
    }
}
