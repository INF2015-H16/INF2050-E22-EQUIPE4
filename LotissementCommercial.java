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
    protected double montantDroitDePassages(){
        return MONTANT_BASE - (nbDroitsPassages * (0.15) * valeurSuperficie());
    }
    protected double valeurSuperficie(){
        return superficie * prixMax;
    }
}
