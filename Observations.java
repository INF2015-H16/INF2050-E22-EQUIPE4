
package evaluationfonciere;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.sf.json.JSONArray;

/**
 * 
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
 */
public class Observations {
    private Terrain terrain;
    private JSONArray observations;
    
    public Observations(Terrain terrain) {
        this.terrain = terrain;
        //Methodes qui verifient ces proprietees
        this.valeurLot();
        this.taxeMunicipale();
        this.ecartDates();
        this.taxeScolaire();
        this.valeurFonciereTot();
        this.superficie();
        this.prixMinMax(); 
    }

    JSONArray observations() {
        return this.observations;
    }

    private void valeurLot() {
        double valeurMax = Arrays.stream(this.terrain.getLotissements())
                .mapToDouble(lot -> lot.getValeurTotalLot()).max().getAsDouble();
        
        if(valeurMax > 45000) {
            this.observations.add(String
                    .format("La valeur par lot du lot %d est trop dispendieuse.",this.indexMaxLot()));
        }
    }

    private void taxeMunicipale() {
        if(this.terrain.getTaxeMunicipale() > 1000) {
            this.observations.add("La taxe municipale payable par le propriétaire nécessite deux versements.");
        }
    }

    private void ecartDates() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void valeurFonciereTot() {
        if(this.terrain.getValeurFonciereTotale() > 300000) {
            this.observations.add("La valeur fonciere totale est trop dispendieuse.");
        }
    }

    private void superficie() {
        double superficie = Arrays.stream(this.terrain.getLotissements())
                .mapToDouble(lot -> lot.getSuperficie()).max().getAsDouble();
        if(superficie > 45000) {
            this.observations.add(String
                    .format("La superficie par lot %s est trop grande.", this.superficies(45000)));
        }
    }

    private void prixMinMax() {
        if(this.terrain.getPrixMax() > this.terrain.getPrixMin() * 2) {
            this.observations.add("Le prix maximum par metres carres depasse de plus de deux fois le prix minimum par metres carres.");
        }
    }

    private void taxeScolaire() {
        if(this.terrain.getTaxeScolaire() > 500) {
            this.observations.add("La taxe scolaire payable par le propriétaire nécessite deux versements.");
        }
    }

    private int indexMaxLot() {
        double[] valeursLots = Arrays.stream(this.terrain.getLotissements())
                .mapToDouble(lot -> lot.getValeurTotalLot()).toArray();
        int maxAt = 0;
        for (int i = 0; i < valeursLots.length; i++) {
            maxAt = valeursLots[i] > valeursLots[maxAt] ? i : maxAt;
        }
        return maxAt + 1;
    }

    private String superficies(int maxSuperficie) {
        int[] superficies = Arrays.stream(this.terrain.getLotissements())
                .mapToInt(lot -> lot.getSuperficie()).toArray();
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < superficies.length; i++) {
            if(superficies[i] > maxSuperficie){
                indexes.add(i);
            }
        }
        String retour = "";
        if(indexes.size() > 1){
            retour += "des lots ";
            for (int i: indexes) {
                retour += String.valueOf(i) + ", ";
            }
            retour = retour.substring(0, retour.lastIndexOf(",", retour.length() - 2));
            retour += " et " + String.valueOf(indexes.get(indexes.size() - 1))+ " ";
        } else {
            retour += "du lot ";
            retour += String.valueOf(indexes.get(0));
        }
        return retour;
    }
    
}
