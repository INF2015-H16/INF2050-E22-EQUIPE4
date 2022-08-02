package evaluationfonciere;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
 */
class Statistique {
    public static final String MSG_ERREUR = "Erreur: Parametres non reconnus !";
    private final static String NOM_FICHIER = "Statistiques.json";
    
    private int nbrTotalLots = 0;
    private List<Integer> valeursParLot = new ArrayList<>(Collections.nCopies(3, 0)); //[0] moins de 1000, [1] 1000 a 10000, [2] 10000 et plus
    private int nbrLotAgricole = 0;
    private int nbrLotCommercial = 0;
    private int nbrLotResidentiel = 0;
    private int superficieMaximale = 0;
    private double valeurMaximale = 0.0;

    public Statistique() {
        File f = new File(NOM_FICHIER);
        if(!f.exists() || f.isDirectory()) { 
            reinitialiser();
        }
        JSONObject fichier = getContenueFichier();
        setValeurs(fichier);
    }
    
    private void setValeurs(JSONObject fichier) {
        try {
            this.nbrTotalLots = fichier.getInt("nbrTotalLots");
            this.valeursParLot = fichier.getJSONArray("valeursParLot");
            this.nbrLotAgricole = fichier.getInt("nbrLotAgricole");
            this.nbrLotCommercial = fichier.getInt("nbrLotCommercial");
            this.nbrLotResidentiel = fichier.getInt("nbrLotResidentiel");
            this.superficieMaximale = fichier.getInt("superficiesMaximales");
            this.valeurMaximale = fichier.getDouble("valeursMaximales");
        } catch (JSONException e) {
            System.out.println("Une erreur dans l'analyse du fichier Statistique.json "
                    + "est survenue, il est probablement corrompue. "
                    + "Utilser l'option '-SR' pour le reinitialiser.");
            System.exit(0);
        }
    }

    private JSONObject getContenueFichier() {
        JSONObject fichier = null;
        try {
            fichier = JSONObject.fromObject(new String(Files.readAllBytes(Paths.get(NOM_FICHIER))));
        } catch (IOException e) {
            System.out.println("Une erreur dans la lecture du fichier Statistique.json est survenue.");
        }
        return fichier;
    }
    
    void afficher() {
        System.out.println("Le nombre total de lots : " + nbrTotalLots);
        afficherBraquettes(valeursParLot);
        System.out.println("Le nombre de lots agricoles : " + nbrLotAgricole);
        System.out.println("Le nombre de lots commerciaux : " + nbrLotCommercial);
        System.out.println("Le nombre de lots residentiels : " + nbrLotResidentiel);
        System.out.println("La plus grande superficie de lot traite : "+ superficieMaximale);
        System.out.println("La valeur maximale de lot traite : " + valeurMaximale);
    }
    
    private void afficherBraquettes(List<Integer> valeursParLot){
        System.out.println("Le nombre de lots valant moins de 1000$: " + valeursParLot.get(0));
        System.out.println("Le nombre de lots valant entre 1000$ et 10000$: " + valeursParLot.get(1));
        System.out.println("Le nombre de lots valant plus de 10000$: " + valeursParLot.get(2));
    }
    //Ces trois methodes sont static pour la eviter 
    //les erreurs quand on -SR un fichier Statistique.json corrompue
    public static final void reinitialiser(){
        ecrireFichierStats(contenueInitiale());
    }
    
    private static void ecrireFichierStats(String contenue) {
        File f = new File(Statistique.NOM_FICHIER);
        try (FileWriter writer = new FileWriter(f)) {
            String rapportString = contenue;
            writer.write(rapportString);
        } catch (IOException e) {
            System.out.println("Une erreur dans l'ecriture du fichier Statistique.json est survenue.");
        }
    }

    private static String contenueInitiale() {
        JSONObject contenue = new JSONObject();
        contenue.accumulate("nbrTotalLots", 0);
        contenue.accumulate("valeursParLot", new ArrayList<>(Collections.nCopies(3, 0)));
        contenue.accumulate("nbrLotAgricole", 0);
        contenue.accumulate("nbrLotCommercial", 0);
        contenue.accumulate("nbrLotResidentiel", 0);
        contenue.accumulate("superficiesMaximales", 0);
        contenue.accumulate("valeursMaximales", 0);
        
        return contenue.toString();
    }

    void mettreAJour(Terrain terrain) {
        Lotissement[] lotissements = terrain.getLotissements();
        for (int i = 0; i < lotissements.length; i++){
            this.nbrTotalLots++;
            this.nombreDeTypeDeLotsAJour(lotissements[i]);
            this.valeurParBraquette(lotissements[i]);
            this.superficieMaximaleAJour(lotissements[i]);
            this.valeurMaximaleAJour(lotissements[i]);
        }
        mettreAJourFichier();
    }
                
    private void nombreDeTypeDeLotsAJour(Lotissement lot){
        if(lot instanceof LotissementAgricole){
            this.nbrLotAgricole++;
        }else if(lot instanceof LotissementResidentiel){
            this.nbrLotResidentiel++;
        }else if(lot instanceof LotissementCommercial){
            this.nbrLotCommercial++;
        }
    }
    
    private void valeurParBraquette(Lotissement lot){
        if(lot.getValeurTotalLot() < 1000){
            this.valeursParLot.set(0, this.valeursParLot.get(0) + 1);
        }else if(lot.getValeurTotalLot() < 10000){
            this.valeursParLot.set(1, this.valeursParLot.get(1) + 1);
        }else{
            this.valeursParLot.set(2, this.valeursParLot.get(2) + 1);
        }
    }
    
    private void superficieMaximaleAJour(Lotissement lot){
        if(lot.getSuperficie() > this.superficieMaximale){
            this.superficieMaximale = lot.getSuperficie();
        }
    }
    
    private void valeurMaximaleAJour(Lotissement lot){
        if(lot.getValeurTotalLot() > this.valeurMaximale){
            this.valeurMaximale = lot.getValeurTotalLot();
        }
    }
    
    private void mettreAJourFichier(){
        ecrireFichierStats(contenueMisAJour());
    }
    
    private String contenueMisAJour() {
        JSONObject contenue = new JSONObject();
        contenue.accumulate("nbrTotalLots", nbrTotalLots);
        contenue.accumulate("valeursParLot", valeursParLot);
        contenue.accumulate("nbrLotAgricole", nbrLotAgricole);
        contenue.accumulate("nbrLotCommercial", nbrLotCommercial);
        contenue.accumulate("nbrLotResidentiel", nbrLotResidentiel);
        contenue.accumulate("superficiesMaximales", superficieMaximale);
        contenue.accumulate("valeursMaximales", Double.valueOf(RapportTerrain.formaterDecimal(valeurMaximale)));
        
        return contenue.toString();
    }
}
