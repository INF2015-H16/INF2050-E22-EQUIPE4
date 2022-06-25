/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
public class LotissementCommercial extends Lotissement{

    public LotissementCommercial(JSONObject unLot) {
        super(unLot);
    }
    
    @Override
    public void calculs(){
        valeurSuperficie = superficie * prixMax;
        montantDroitDePassages = MONTANT_BASE - (nbDroitsPassages * (0.15) * valeurSuperficie);
        calculMontantService();

        valeurTotalLot = valeurSuperficie + montantDroitDePassages + montantServices;
    }

    private void calculMontantService() {
        if (superficie <= 500) {
            montantServices = 500 * nbServices;
        } else {
            montantServices = 1500 * nbServices;
        }

        if (montantServices > 5000) {
            montantServices = 5000;
        }
    }
}
