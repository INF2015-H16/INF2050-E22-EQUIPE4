
package evaluationfonciere;

/**
 *
 * @author 
 */
abstract class Terrain {
    double prixMin;
    double prixMax;
    double valeurFonciereTotale;
    double taxeScolaire;
    double taxeMunicipale;
    double montantServices;
    Lotissement [] lotissements;
    final int nbrServiceBase = 2;
    final double prixFixe = 733.77;
    final double montantBase = 500; //Je l'ai mis ici et non dans chaque sub-class pour eviter la repetition
    
    public terrain(JSONObject JSONSource){        
        this.prixMin = JSONSource.getDouble("prix_min");
        this.prixMax = JSONSource.getDouble("prix_max");
        this.lotissements = formaterLot(JSONSource.getJSONArray("lotissements"));
        //Je n'ai pas l'ordre des calculs en tete
        //Prochaines lignes surement a changer d'ordre
        this.valeurFonciereTotale = this.calculValeurFonciereTotale();
        this.taxeScolaire = this.calculTaxeScolaire();
        this.taxeMunicipale = this.calculTaxeMunicipale();
        this.montantServices = this.calculMontantServices();   
    }
          
    private abstract double calculValeurFonciereTotale();
    private abstract double calculTaxeScolaire();
    private abstract double calculTaxeMunicipale();
    private abstract double calculMontantServices();
    
    private Lotissement [] formaterLot(JSONArray source){
        lotissements = new Lotissement[source.size()];
        for (int i=0; i < source.size(); i++){
            lotissements[i] = new Lotissement(source.getJSONObject(i));
        }
        return lotissements;
    }
    
    public JSONObject rapport{
        //append les lignes voulues avant le lotissement
        //append avec appel de lotissements.rapport()
    }
}