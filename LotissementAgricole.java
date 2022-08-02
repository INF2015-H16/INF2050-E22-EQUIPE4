package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 * Classe enfant de Lotissement pour initialiser un terrain agricole 
 * et avec ses propres méthodes de calcul de montants.
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
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
