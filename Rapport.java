
package evaluationfonciere;

import net.sf.json.JSONObject;

/**
 *
 * @author  Goldlen
 */
public class Rapport {
    public String rapport(){
        return rapportJSONObject().toString();
    }

    public JSONObject rapportJSONObject(double valeurFonciereTotale, double taxeScolaire, double taxeMunicipale){
        calculsRapport();
        JSONObject rapport = new JSONObject();
        rapport.accumulate("valeur_fonciere_totale", formaterDecimal(valeurFonciereTotale) + " $");
        rapport.accumulate("taxe_scolaire", formaterDecimal(taxeScolaire) + " $");
        rapport.accumulate("taxe_ municipale", formaterDecimal(taxeMunicipale) + " $");
        JSONArray lots = creerRapportsLots();
        rapport.accumulate("lotissements", lots);

        return rapport;
    }

    public JSONArray creerRapportsLots(Lotissement[] lotissements) {
        JSONArray lots = new JSONArray();
        for (Lotissement lot : lotissements) {
            JSONObject lotUnique = new JSONObject();
            lotUnique.accumulate("description", lot.getDescription());
            lotUnique.accumulate("valeur_par_lot", formaterDecimal(lot.getValeurTotalLot()) + " $");

            lots.add(lotUnique);
        }
        return lots;
    }

    public void calculsRapport(double valeurFonciereTotale, double taxeScolaire, double taxeMunicipale, Lotissement[] lotissements, double PRIX_FIXE, double TAUX_SCOLAIRE, double TAUX_MUNICIPALE){
        valeurFonciereTotale = PRIX_FIXE;
        for (Lotissement lot : lotissements) {
            lot.calculs();
            valeurFonciereTotale += lot.getValeurTotalLot();
        }
        valeurFonciereTotale = arrondiAu5sousSuperieur(valeurFonciereTotale);
        taxeScolaire = arrondiAu5sousSuperieur(valeurFonciereTotale * TAUX_SCOLAIRE);
        taxeMunicipale = arrondiAu5sousSuperieur(valeurFonciereTotale * TAUX_MUNICIPALE);
    }

    public static void ecrireFichierSortie(String args, String texteEvaluation) throws FormatInvalide {
        try (FileWriter writer = new FileWriter(new File(args))) {
            writer.write(texteEvaluation);
        } catch (IOException e) {
            throw new FormatInvalide("Erreur dans l'ecriture du fichier de sortie");
        }
     }
    
    public static String retournerContenuSortie(JSONObject JSONSource){
        try {
            //Ici des exception peuvent etre lance
            Terrain terrain = new Terrain(JSONSource);
            //Ici non
            //Ici sera l'appel de Rapport.getRapport(terrain);
            return terrain.rapport();
        } catch (FormatInvalide erreur) {
            return "{\"message\": \"" + erreur.getMessage() + ".\"}";
        }
    }

    public static String lireFichierEntree(String[] args) throws FormatInvalide {
        if(args.length != 2){
            throw new FormatInvalide("Fichier d'entree ou de sortie manquant");
        }
        try {
            String texteSource = new String(Files.readAllBytes(Paths.get(args[0])));
            return texteSource;
        } catch (IOException e) {
            throw new FormatInvalide("Erreur dans la lecture du fichier d'entree");
        }
    }
}