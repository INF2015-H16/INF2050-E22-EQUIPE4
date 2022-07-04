
package evaluationfonciere;

import java.time.*;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
 */
class ValiderValeursLot {
    private final JSONObject JSONSource;

    public ValiderValeursLot(JSONObject JSONSource) {
        this.JSONSource = JSONSource;
    }
    
    String description() throws FormatInvalide{
        try {
            String description = JSONSource.getString("description");
            if(description.equals("")){
                throw new FormatInvalide("Une ou plusieurs propriétés <description> des lots est vide");
            }
            return description;
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <description> d'un lot est manquante dans le fichier d'entree");
        }
    }

    int nbDroitsPassages() throws FormatInvalide {
        try {
            int nbDroitsPassages = JSONSource.getInt("nombre_droits_passage");
            if(nbDroitsPassages > 10){
                throw new FormatInvalide("Le nombre de droits de passage est superieur a 10");
            } else if(nbDroitsPassages < 0){
                throw new FormatInvalide("Le nombre de droits de passage est inferieur a 0");
            }
            return nbDroitsPassages;
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <nombre_droits_passage> d'un lot est manquante dans le fichier d'entree");
        }
    }

    String dateMesure() throws FormatInvalide {
        try {
            String dateMesure = JSONSource.getString("date_mesure");
            LocalDate.parse(dateMesure); //peut créer un objet LocalDate ssi le format de dateMesure est ISO8601
            return dateMesure;
            
        } catch (DateTimeParseException) {
            throw new FormatInvalide("La date n'est pas dans le format ISO8601 (aaaa-mm-jj)");
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <date_mesure> d'un lot est manquante dans le fichier d'entree");
        }
    }

    int nbServices() throws FormatInvalide {
        try {
            int nbServices = JSONSource.getInt("nombre_services");
            if(nbServices > 5){
                throw new FormatInvalide("Le nombre de services est superieur a 5");
            } else if(nbServices > 5){
                throw new FormatInvalide("Le nombre de services est inferieur a 0");
            }
            return nbServices;
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <nombre_services> d'un lot est manquante dans le fichier d'entree");
        }
    }

    int superficie() throws FormatInvalide {
        try {
            int superficie = JSONSource.getInt("superficie");
            if(superficie > 50000){
                throw new FormatInvalide("La superficie ne peut pas etre superieure a 50000 metres carres");
            } else if(superficie < 0){
                throw new FormatInvalide("La superficie ne peut pas etre negative");
            }
            return superficie;
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <superficie> d'un lot est manquante dans le fichier d'entree");
        }
    }
}
