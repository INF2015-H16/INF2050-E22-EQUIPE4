
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
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
