
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
    
    @Override
    public void calculs(){
        valeurSuperficie = superficie * prixMin;
        montantDroitDePassages = MONTANT_BASE - (nbDroitsPassages * (valeurSuperficie / 20));
        montantServices = 0;

        valeurTotalLot = valeurSuperficie + montantDroitDePassages + montantServices;
    }
}

