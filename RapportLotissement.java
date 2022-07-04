
package evaluationfonciere;

import java.text.DecimalFormat;
import net.sf.json.JSONObject;

/**
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
       lotUnique.accumulate("valeur_par_lot", formaterDecimal(lot.getValeurTotalLot()) + " $");
       return lotUnique;
    }
     
    private String formaterDecimal(double valeur) {
        String pattern = "#.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(valeur);
    }
}
