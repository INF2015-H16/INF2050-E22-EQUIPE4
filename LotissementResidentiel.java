
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
public class LotissementResidentiel extends Lotissement{
    
    public LotissementResidentiel(JSONObject unLot) {
        super(unLot);
    }
    
    @Override
    public void calculs(){
        valeurSuperficie = superficie * ((prixMax + prixMin) / 2);
        montantDroitDePassages = MONTANT_BASE - 
                (nbDroitsPassages * (valeurSuperficie / 10));
        calculMontantService();

        valeurTotalLot = valeurSuperficie + montantDroitDePassages + montantServices;
    }

    private void calculMontantService() {
        if (superficie <= 500) {
            montantServices = 0;
        } else if (superficie <= 10000) {
            montantServices = 500 * nbServices;
        } else {
            montantServices = 1000 * nbServices;
        }
    }
}
