
package evaluationfonciere;

import java.text.DecimalFormat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
 */
public class Terrain {
    double prixMin;
    double prixMax;

    double valeurFonciereTotale;
    double taxeScolaire;
    double taxeMunicipale;
    Lotissement[] lotissements;

    static final double PRIX_FIXE = 733.77;
    static final double TAUX_SCOLAIRE = 0.012;
    static final double TAUX_MUNICIPALE = 0.025;

    public Terrain(JSONObject JSONSource) {
        this.prixMin = Double.parseDouble(JSONSource.getString("prix_m2_min").split(" ")[0]);
        this.prixMax = Double.parseDouble(JSONSource.getString("prix_m2_max").split(" ")[0]);
        this.lotissements = formaterLot(JSONSource.getJSONArray("lotissements"));

        switch (JSONSource.getInt("type_terrain")) {
            case 0:
                this.agricole();
                break;
            case 1:
                this.residentiel();
                break;
            case 2:
                this.commercial();
                break;
        }
    }

    private void residentiel() {
        for (Lotissement lot : lotissements) {
            lot.residentiel(prixMax, prixMin);
        }
    }

    private void agricole() {
        for (Lotissement lot : lotissements) {
            lot.agricole(prixMin);
        }
    }

    private void commercial() {
        for (Lotissement lot : lotissements) {
            lot.commercial(prixMax);
        }
    }

    public JSONObject rapport() {
        valeurFonciereTotale = PRIX_FIXE;

        String pattern = "#.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        for (Lotissement lot : lotissements) {
            valeurFonciereTotale += lot.getValeurTotalLot();
        }

        // Arrondir ses montants au 5 sous
        valeurFonciereTotale = arrondiAu5sousSuperieur(valeurFonciereTotale);
        taxeScolaire = arrondiAu5sousSuperieur(valeurFonciereTotale * TAUX_SCOLAIRE);
        taxeMunicipale = arrondiAu5sousSuperieur(valeurFonciereTotale * TAUX_MUNICIPALE);

        JSONObject rapport = new JSONObject();
        rapport.accumulate("valeur_fonciere_totale", decimalFormat.format(valeurFonciereTotale) + " $");
        rapport.accumulate("taxe_scolaire", decimalFormat.format(taxeScolaire) + " $");
        rapport.accumulate("taxe_ municipale", decimalFormat.format(taxeMunicipale) + " $");

        JSONArray lots = new JSONArray();
        for (Lotissement lot : lotissements) {
            JSONObject lotUnique = new JSONObject();
            lotUnique.accumulate("description", lot.getDescription());

            // Enoncer ne precise pas qu'il faut arrondir le montant au 5 sous pour chaque
            // lot,
            // mais l'exemple de sortie montre des montants arrondis
            lotUnique.accumulate("valeur_par_lot", decimalFormat.format(lot.getValeurTotalLot()) + " $");

            lots.add(lotUnique);
        }

        rapport.accumulate("lotissements", lots);

        return rapport;
    }

    public double arrondiAu5sousSuperieur(double montant) {
        return Math.ceil(montant * 20) / 20;
    }

    private Lotissement[] formaterLot(JSONArray source) {
        lotissements = new Lotissement[source.size()];
        for (int i = 0; i < source.size(); i++) {
            lotissements[i] = new Lotissement(source.getJSONObject(i));
        }
        return lotissements;
    }
}
