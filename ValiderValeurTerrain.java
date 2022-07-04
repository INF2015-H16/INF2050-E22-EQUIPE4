
package evaluationfonciere;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
 * @author Leonid
 */
class ValiderValeurTerrain {

    private final JSONObject JSONSource;

    public ValiderValeurTerrain(JSONObject JSONSource) {
        this.JSONSource = JSONSource;
    }
    
    double prixMin() throws FormatInvalide {
        try {
            double prixMin = stringEnDouble(JSONSource.getString("prix_m2_min"));
            if(prixMin < 0){
                throw new FormatInvalide("Un montant d'argent ne peut pas être négatif (prix_m2_min)");
            }
            return prixMin;
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <prix_m2_min> est manquante dans le fichier d'entree");
        }
    }
    
    double prixMax() throws FormatInvalide {
        try {
            double prixMax = stringEnDouble(JSONSource.getString("prix_m2_max"));
            if(prixMax < 0){
                throw new FormatInvalide("Un montant d'argent ne peut pas être négatif (prix_m2_max)");
            }
            return prixMax;
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <prix_m2_max> est manquante dans le fichier d'entree");
        }
    }
    
    int typeTerrain() throws FormatInvalide {
        try {
            int typeTerrain = JSONSource.getInt("type_terrain");
            if(typeNonValide(typeTerrain)){
                throw new FormatInvalide("Le type de terrain n'est pas la valeur 0, 1 ou 2");
            }
            return typeTerrain;
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <type_terrain> est manquante dans le fichier d'entree");
        }
    }
    
    Lotissement[] lotissements() throws FormatInvalide{
        try {
            JSONArray lots = JSONSource.getJSONArray("lotissements");
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <lotissements> est manquante dans le fichier d'entree");
        }
    }
    
    private void verifierValeursTerrain(int typeDeTerrain, JSONArray lesLots) throws FormatInvalide {
        if(typeNonValide(typeDeTerrain)){
            throw new FormatInvalide("Le type n'est pas la valeur 0, 1 ou 2");
        } else if(lesLots.size() > 10){
            throw new FormatInvalide("Le nombre de lots ne doit jamais depasser 10 lots");
        } else if(lesLots.size() < 1){
            throw new FormatInvalide("Un terrain doit avoir au moins un lot");
        } else if(prixMinMax[0] < 0 || prixMinMax[1] < 0){
            throw new FormatInvalide("Un montant d'argent ne peut pas être négatif;");
        }
    }
     
    private double stringEnDouble(String prixEnString){
       //On le separe du signe $ et on remplace les , par . s'il y en a
       prixEnString = prixEnString.split(" ")[0].replaceAll(",",".");
       return Double.parseDouble(prixEnString);
    }
    
    private boolean typeNonValide(int type) {
        return type != 0 && type != 1 && type != 2;
    }

    
}
