
package evaluationfoncierev2;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
public class Terrain {
    double prixMin;
    double prixMax;
    
    double valeurFonciereTotale;
    double taxeScolaire;
    double taxeMunicipale;
    Lotissement [] lotissements;
    
    final double prixFixe = 733.77;
    final double montantBase = 500;
    
    public Terrain(JSONObject JSONSource){
        this.prixMin = Double.parseDouble(JSONSource.getString("prix_m2_min").split(" ")[0]);
        this.prixMax = Double.parseDouble(JSONSource.getString("prix_m2_max").split(" ")[0]);
        this.lotissements = formaterLot(JSONSource.getJSONArray("lotissements"));
    }
    
    void residentiel() {
        for(Lotissement lot : lotissements){
            lot.residentiel(prixMax, prixMin);
        }
    }

    void agricole() {
        for(Lotissement lot : lotissements){
            lot.agricole(prixMin);
        }
    }

    void commercial() {
        for(Lotissement lot : lotissements){
            lot.commercial(prixMax);
        }
    }
    
    public JSONObject rapport(){
        valeurFonciereTotale = prixFixe;
        for(Lotissement lot : lotissements){
            valeurFonciereTotale += lot.valeurLot;
        }
        
        taxeScolaire = valeurFonciereTotale * (12/100);
        taxeMunicipale = valeurFonciereTotale * (25/100);
        
        JSONObject rapport = new JSONObject();
        rapport.accumulate("valeur_fonciere_totale", valeurFonciereTotale);
        rapport.accumulate("taxe_scolaire", taxeScolaire);
        rapport.accumulate("taxe_ municipale", taxeMunicipale);
        
        JSONArray lots = new JSONArray();
        for(Lotissement lot : lotissements){
            JSONObject lotUnique = new JSONObject();
            lotUnique.accumulate("description", lot.description);
            lotUnique.accumulate("valeur_par_lot", lot.valeurLot);
            lots.add(lotUnique);
        }
        
        rapport.accumulate("lotissements", lots);
        
        return rapport;
    }
    
    private Lotissement [] formaterLot(JSONArray source){
        lotissements = new Lotissement[source.size()];
        for (int i=0; i < source.size(); i++){
            lotissements[i] = new Lotissement(source.getJSONObject(i));
        }
        return lotissements;
    }
}
