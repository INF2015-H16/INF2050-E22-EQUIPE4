
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author  Goldlen
 */
public class Verificateur {
    public void verifierNbDroitsPassages(int nbDroitsPassages) throws FormatInvalide {
        if(nbDroitsPassages > 10){
            throw new FormatInvalide("Le nombre de droits de passage est superieur a 10");
        } else if(nbDroitsPassages < 0){
            throw new FormatInvalide("Le nombre de droits de passage est inferieur a 0");
        }
    }

    public void verifierNbServices(int nbServices) throws FormatInvalide {
        if(nbServices > 5){
            throw new FormatInvalide("Le nombre de services est superieur a 5");
        } else if(nbServices > 5){
            throw new FormatInvalide("Le nombre de services est inferieur a 0");
        }
    }

    public void verifierSuperficie(int superficie) throws FormatInvalide {
        if(superficie > 50000){
            throw new FormatInvalide("La superficie ne peut pas etre supérieure a 50000 metres carres");
        } else if(superficie < 0){
            throw new FormatInvalide("La superficie ne peut pas etre négative");
        }
    }

    public void verifierDescription(JSONObject JSONSource) throws FormatInvalide {
        if (!JSONSource.containskey("description")) {
            throw new FormatInvalide("La propriete <description> d'un lot est manquante dans le fichier d'entree");
        }
    }

    public void descriptionExiste(JSONObject JSONSource, String description) throws FormatInvalide {
        if (JSONSource.containskey("description")) {
            description = JSONSource.getString("description");
        } else {
            throw new FormatInvalide("La propriete <description> d'un lot est manquante dans le fichier d'entree");
        }
    }

    public void nbDroitsPassagesExiste(JSONObject JSONSource, int nbDroitsPassages) throws FormatInvalide {
        if (JSONSource.containskey("nombre_droits_passage")) {
            nbDroitsPassages = JSONSource.getInt("nombre_droits_passage");
        } else {
            throw new FormatInvalide("La propriete <nombre_droits_passage> d'un lot est manquante dans le fichier d'entree");
        }
    }

    public void dateMesureExiste(JSONObject JSONSource, String dateMesure) throws FormatInvalide {
        if (JSONSource.containskey("date_mesure")) {
            dateMesure = JSONSource.getString("date_mesure");
        } else {
            throw new FormatInvalide("La propriete <date_mesure> d'un lot est manquante dans le fichier d'entree");
        }
    }

    public void nbServicesExiste(JSONObject JSONSource, int nbServices, int NB_SERVICE_BASE) throws FormatInvalide {
        if (JSONSource.containskey("nombre_services")) {
            nbServices = JSONSource.getInt("nombre_services") + NB_SERVICE_BASE;
        } else {
            throw new FormatInvalide("La propriete <nombre_services> d'un lot est manquante dans le fichier d'entree");
        }
    }

    public void superficieExiste(JSONObject JSONSource, int superficie) throws FormatInvalide {
        if (JSONSource.containskey("superficie")) {
            superficie = JSONSource.getInt("superficie");
        } else {
            throw new FormatInvalide("La propriete <superficie> d'un lot est manquante dans le fichier d'entree");
        }
    }

    public void prixMinExiste(JSONObject JSONSource, double[] prixMinMax) throws FormatInvalide {
        if (JSONSource.containskey("prix_m2_min")) {
            prixMinMax[0] = stringEnDouble(JSONSource.getString("prix_m2_min"));
        } else {
            throw new FormatInvalide("La propriete <prix_m2_min> est manquante dans le fichier d'entree");
        }
    }

    public void prixMaxExiste(JSONObject JSONSource, double[] prixMinMax) throws FormatInvalide {
        if (JSONSource.containskey("prix_m2_max")) {
            prixMinMax[1] = stringEnDouble(JSONSource.getString("prix_m2_max"));
        } else {
            throw new FormatInvalide("La propriete <prix_m2_max> est manquante dans le fichier d'entree");
        }
    }

    public JSONArray lotsExiste(JSONObject JSONSource) throws FormatInvalide {
        if (JSONSource.containskey("lotissements")) {
            return JSONSource.getJSONArray("lotissements");
        } else {
            throw new FormatInvalide("La propriete <lotissements> est manquante dans le fichier d'entree");
        }
    }

    public int typeTerrainExiste(JSONObject JSONSource) throws FormatInvalide {
        if (JSONSource.containskey("type_terrain")) {
            return JSONSource.getInt("type_terrain");
        } else {
            throw new FormatInvalide("La propriete <type_terrain> est manquante dans le fichier d'entree");
        }
    }

    public void verifierValeursTerrain(int typeDeTerrain, JSONArray lesLots, double[] prixMinMax) throws FormatInvalide {
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
}