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
public class LotissementResidentiel extends Lotissement{
    
    public LotissementResidentiel(JSONObject unLot) throws FormatInvalide {
        super(unLot);
    }

    @Override
    protected double montantServices(){
        if (superficie <= 500) {
            return 0;
        } else if (superficie <= 10000) {
            return  500 * nbServices;
        } else {
            return  1000 * nbServices;
        }
    }
    @Override
    protected double montantDroitDePassages(){
        return MONTANT_BASE - (nbDroitsPassages * (valeurSuperficie() / 10));
    }
    @Override
    protected double valeurSuperficie(){
        return superficie * ((prixMax + prixMin) / 2);
    }
}
