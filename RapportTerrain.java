package evaluationfonciere;

import java.text.DecimalFormat;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * Classe qui cherche les resultats de calcul de la classe Terrain pour
 * les formatter avant d'etre ajouter au fichier de sortie.
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
 */
public class RapportTerrain {
    Terrain terrain;
    
    public JSONObject rapport(Terrain terrain){
        this.terrain = terrain;
        
        JSONObject rapport = new JSONObject();
        rapport.accumulate("valeur_fonciere_totale", formaterDecimal(terrain.getValeurFonciereTotale()) + " $");
        rapport.accumulate("taxe_scolaire", formaterDecimal(terrain.getTaxeScolaire()) + " $");
        rapport.accumulate("taxe_municipale", formaterDecimal(terrain.getTaxeMunicipale()) + " $");
        JSONArray lots = creerRapportsLots();
        rapport.accumulate("lotissements", lots);
        
        List<String> observations = new Observations(terrain).observations();
        if(!observations.isEmpty()){
            rapport.accumulate("observations", observations);
        }
        
        return rapport;
    }

    private JSONArray creerRapportsLots() {
        JSONArray lots = new JSONArray();
        for (Lotissement lot : terrain.getLotissements()) {
            lots.add(new RapportLotissement().rapport(lot));
        }
        return lots;
    }
    
    //Methode pour formater a 2 decimal utilisee dans d'autre classes
    static String formaterDecimal(double valeur) {
        String pattern = "#.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(valeur);
    }
}
