
package com.proudmusketeers;

import java.text.DecimalFormat;
import net.sf.json.JSONObject;

/**
 * Classe qui cherche les variables des Lotissements pour
 * les formatter avant d'etre ajouter au fichier de sortie.
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
 */
public class RapportLotissement {
    public JSONObject rapport(Lotissement lot){
       JSONObject lotUnique = new JSONObject();
       lotUnique.accumulate("description", lot.getDescription());
       lotUnique.accumulate("valeur_par_lot", RapportTerrain.formaterDecimal(lot.getValeurTotalLot()) + " $");
       return lotUnique;
    }
}
