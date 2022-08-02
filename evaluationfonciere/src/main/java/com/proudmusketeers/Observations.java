
package com.proudmusketeers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
    private ArrayList<String> observations = new ArrayList<>();
    private final int MAX_SUPERFICIE = 45000;
    private final int MAX_VALEUR = 45000;
    
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

    List<String> observations() {
        return this.observations;
    }

    private void valeurLot() {
        double valeurMax = Arrays.stream(this.terrain.getLotissements())
                .mapToDouble(lot -> lot.getValeurTotalLot()).max().getAsDouble();
        
        if(valeurMax > MAX_VALEUR) {
            this.observations.add(String
                    .format("La valeur par lot %s est trop dispendieuse.", this.lotsGrandeValeur()));
        }
    }

    private void taxeMunicipale() {
        if(this.terrain.getTaxeMunicipale() > 1000) {
            this.observations.add("La taxe municipale payable par le proprietaire necessite deux versements.");
        }
    }

    private void ecartDates() {
        LocalDate[] datesLots = Arrays.stream(this.terrain.getLotissements())
                .map(lot -> LocalDate.parse(lot.getDateMesure()))
                .toArray(size -> new LocalDate[size]);
                        
        List<LocalDate> dates = new ArrayList<>(List.of(datesLots));
        dates.sort(Comparator.naturalOrder());
        if(ChronoUnit.MONTHS.between(dates.get(0), dates.get(dates.size() - 1)) > 6){
            this.observations.add("L'ecart maximal entre les dates de mesure des lots d'un meme terrain devrait etre de moins de 6 mois.");
        }
    }

    private void valeurFonciereTot() {
        if(this.terrain.getValeurFonciereTotale() > 300000) {
            this.observations.add("La valeur fonciere totale est trop dispendieuse.");
        }
    }

    private void superficie() {
        double superficie = Arrays.stream(this.terrain.getLotissements())
                .mapToDouble(lot -> lot.getSuperficie()).max().getAsDouble();
        if(superficie > MAX_SUPERFICIE) {
            this.observations.add(String
                    .format("La superficie par lot %s est trop grande.", this.lotsGrandeSuperficies()));
        }
    }

    private void prixMinMax() {
        if((this.terrain.getPrixMin() * 2) < this.terrain.getPrixMax()) {
            this.observations.add("Le prix maximum par metres carres depasse de plus de deux fois le prix minimum par metres carres.");
        }
    }

    private void taxeScolaire() {
        if(this.terrain.getTaxeScolaire() > 500) {
            this.observations.add("La taxe scolaire payable par le proprietaire necessite deux versements.");
        }
    }

    private String lotsGrandeValeur() {
        double[] valeursLots = Arrays.stream(this.terrain.getLotissements())
                .mapToDouble(lot -> lot.getValeurTotalLot()).toArray();
        
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < valeursLots.length; i++) {
            if(valeursLots[i] > MAX_VALEUR){
                indexes.add(i);
            }
        }
        
        return texteIndexLots(indexes);
    }

    private String lotsGrandeSuperficies() {
        int[] superficies = Arrays.stream(this.terrain.getLotissements())
                .mapToInt(lot -> lot.getSuperficie()).toArray();
        
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < superficies.length; i++) {
            if(superficies[i] > MAX_SUPERFICIE){
                indexes.add(i);
            }
        }
        
        return texteIndexLots(indexes);
    }
    
    private String texteIndexLots(List<Integer> indexes){
        String retour = "";
        if(indexes.size() > 1){
            retour += "des lots ";
            for (int i: indexes) {
                retour += String.valueOf(i + 1) + ", ";
            }
            retour = retour.substring(0, retour.lastIndexOf(",", retour.length() - 3));
            retour += " et " + String.valueOf(indexes.get(indexes.size() - 1) + 1);
        } else {
            retour += "du lot ";
            retour += String.valueOf(indexes.get(0) + 1);
        }
        return retour;
    }
}
