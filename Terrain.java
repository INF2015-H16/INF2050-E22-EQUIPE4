
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
        //Calculs generaux
        this.valeurFonciereTotale = this.calculValeurFonciereTotale();
        this.taxeScolaire = this.calculTaxeScolaire();
        this.taxeMunicipale = this.calculTaxeMunicipale(); 
    }
    
    //Toutes les methodes abstraites agissent sur this.lotissements
    private abstract double calculTaxeParSuperficie();    
    private abstract double calculDroitPassage();    
    private abstract double calculMontantServices();    
    private abstract double calculValeurParLot();    
    
    private double calculValeurFonciereTotale(){    
    }    
    
    private double calculTaxeScolaire(){
    }
    
    private double calculTaxeMunicipale(){
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
