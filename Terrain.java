
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
    double[] prixMinMax = new double[2];

    double valeurFonciereTotale;
    double taxeScolaire;
    double taxeMunicipale;
    Lotissement[] lotissements;

    static final double PRIX_FIXE = 733.77;
    static final double TAUX_SCOLAIRE = 0.012;
    static final double TAUX_MUNICIPALE = 0.025;
   
    public Terrain(JSONObject JSONSource) throws FormatInvalide {
        setPrixMin(JSONSource);
        setPrixMax(JSONSource);
        
        JSONArray lesLots = setLots(JSONSource);
        int typeDeTerrain = setTypeTerrain(JSONSource);
        verifierValeursTerrain(typeDeTerrain, lesLots);
        
        this.lotissements = formaterLot(lesLots, typeDeTerrain);
        verifierValeursLots();
    }

    private void setPrixMin(JSONObject JSONSource) throws FormatInvalide {
        try {
            prixMinMax[0] = stringEnDouble(JSONSource.getString("prix_m2_min"));
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <prix_m2_min> est manquante dans le fichier d'entree");
        }
    }

    private void setPrixMax(JSONObject JSONSource) throws FormatInvalide {
        try {
            prixMinMax[1] = stringEnDouble(JSONSource.getString("prix_m2_max"));
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <prix_m2_max> est manquante dans le fichier d'entree");
        }
    }

    private JSONArray setLots(JSONObject JSONSource) throws FormatInvalide {
        try {
            return JSONSource.getJSONArray("lotissements");
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <lotissements> est manquante dans le fichier d'entree");
        }
    }

    private int setTypeTerrain(JSONObject JSONSource) throws FormatInvalide {
        try {
            return JSONSource.getInt("type_terrain");
        } catch (JSONException e) {
            throw new FormatInvalide("La propriete <type_terrain> est manquante dans le fichier d'entree");
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
    
    private void verifierValeursLots() throws FormatInvalide {
        for(Lotissement lot : lotissements) {
            lot.verifierValeurs();
        }
    }
    
    private double stringEnDouble(String prixEnString){
        //On le separe du signe $ et on remplace les , par . s'il y en a
        prixEnString = prixEnString.split(" ")[0].replaceAll(",",".");
        return Double.parseDouble(prixEnString);
    }
    
    public String rapport(){
        return rapportJSONObject().toString();
    }
    
    private JSONObject rapportJSONObject(){
        calculsRapport();
        JSONObject rapport = new JSONObject();
        rapport.accumulate("valeur_fonciere_totale", formaterDecimal(valeurFonciereTotale) + " $");
        rapport.accumulate("taxe_scolaire", formaterDecimal(taxeScolaire) + " $");
        rapport.accumulate("taxe_ municipale", formaterDecimal(taxeMunicipale) + " $");
        JSONArray lots = creerRapportsLots();
        rapport.accumulate("lotissements", lots);

        return rapport;
    }

    private JSONArray creerRapportsLots() {
        JSONArray lots = new JSONArray();
        for (Lotissement lot : lotissements) {
            JSONObject lotUnique = new JSONObject();
            lotUnique.accumulate("description", lot.getDescription());
            lotUnique.accumulate("valeur_par_lot", formaterDecimal(lot.getValeurTotalLot()) + " $");

            lots.add(lotUnique);
        }
        return lots;
    }
    
    private void calculsRapport(){
        valeurFonciereTotale = PRIX_FIXE;
        for (Lotissement lot : lotissements) {
            lot.calculs();
            valeurFonciereTotale += lot.getValeurTotalLot();
        }
        valeurFonciereTotale = arrondiAu5sousSuperieur(valeurFonciereTotale);
        taxeScolaire = arrondiAu5sousSuperieur(valeurFonciereTotale * TAUX_SCOLAIRE);
        taxeMunicipale = arrondiAu5sousSuperieur(valeurFonciereTotale * TAUX_MUNICIPALE);
    }
    
    private String formaterDecimal(double valeur) {
        String pattern = "#.00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(valeur);
    }

    public double arrondiAu5sousSuperieur(double montant) {
        return Math.ceil(montant * 20) / 20;
    }

    private Lotissement[] formaterLot(JSONArray source, int typeDeTerrain) {
        CreerTypeLotissement createur = new CreerTypeLotissement();
        JSONObject unLot;
        lotissements = new Lotissement[source.size()];
        for (int i = 0; i < source.size(); i++) {
            unLot = source.getJSONObject(i);
            lotissements[i] = createur.creerLotissement(typeDeTerrain, unLot);
            lotissements[i].setPrixMinMax(prixMinMax);
        }
        return lotissements;
    }

    private boolean typeNonValide(int type) {
        return type != 0 && type != 1 && type != 2;
    }
}
