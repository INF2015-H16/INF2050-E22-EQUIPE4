
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
public class LotissementCommercial extends Lotissement{

    public LotissementCommercial(JSONObject unLot) throws FormatInvalide {
        super(unLot);
    }

    @Override
    protected double montantServices(){
        double resultat;
        if (superficie <= 500) {
            resultat = 500 * nbServices;
        } else {
            resultat = 1500 * nbServices;
        }
        if (resultat > 5000) {
            resultat = 5000;
        }
        return resultat;
    }
    @Override
    protected double montantDroitDePassages(){
        return MONTANT_BASE - (nbDroitsPassages * (0.15) * valeurSuperficie());
    }
    @Override
    protected double valeurSuperficie(){
        return superficie * prixMax;
    }
}
