package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
public class LotissementAgricole extends Lotissement{
    
    public LotissementAgricole(JSONObject unLot) {
        super(unLot);
    }

    protected double montantServices(){
        return 0;
    }
    protected double montantDroitDePassages(){
        return MONTANT_BASE - (nbDroitsPassages * (valeurSuperficie() / 20));
    }
    protected double valeurSuperficie(){
        return superficie * prixMin;
    }
}
