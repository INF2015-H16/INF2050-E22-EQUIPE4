package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
public class LotissementAgricole extends Lotissement{
    
    public LotissementAgricole(JSONObject unLot) throws FormatInvalide {
        super(unLot);
    }

    @Override
    protected double montantServices(){
        return 0;
    }
    @Override
    protected double montantDroitDePassages(){
        return MONTANT_BASE - (nbDroitsPassages * (valeurSuperficie() / 20));
    }
    @Override
    protected double valeurSuperficie(){
        return superficie * prixMin;
    }
}
