
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
public class CreerTypeLotissement {
    public Lotissement creerLotissement(int typeDeTerrain, JSONObject unLot) throws FormatInvalide{
      
        switch (typeDeTerrain) {
            case 0:
                return new LotissementAgricole(unLot);
            case 1:
                return new LotissementResidentiel(unLot);
            case 2:
                return new LotissementCommercial(unLot);
            default:
                break;
        }
      return null;
   }
}
