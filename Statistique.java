
package evaluationfonciere;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
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
    private final String NOM_FICHIER = "Statistiques.json";
    
    private int nbrTotalLots = 0;
    private List<Integer> valeursParLot = new ArrayList<>(3); //[0] moins de 1000, [1] 1000 a 10000, [2] 10000 et plus
    private int nbrLotAgricole = 0;
    private int nbrLotCommercial = 0;
    private int nbrLotResidentiel = 0;
    private List<Integer> superficiesMaximales = new ArrayList<>();//liste des superficie maximale a laquell on rajoute a chaque fois
    private List<Integer> valeursMaximales = new ArrayList<>();//liste des valeurs maximale a laquell on rajoute a chaque fois

    public Statistique() {
        File f = new File(NOM_FICHIER);
        if(!f.exists() || f.isDirectory()) { 
            reinitialiser();
        }
        JSONObject fichier = getContenueFichier();
        this.nbrTotalLots = fichier.getInt("nbrTotalLots");
        this.valeursParLot = fichier.getJSONArray("valeursParLot");
        this.nbrLotAgricole = fichier.getInt("nbrLotAgricole");
        this.nbrLotCommercial = fichier.getInt("nbrLotCommercial");
        this.nbrLotResidentiel = fichier.getInt("nbrLotResidentiel");
        this.superficiesMaximales = fichier.getJSONArray("superficiesMaximales");
        this.valeursMaximales = fichier.getJSONArray("valeursMaximales");
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
        File f = new File(NOM_FICHIER);
        if(!f.exists() && f.isDirectory()) { 
            reinitialiser();
        }
        JSONObject fichier = getContenueFichier();
        System.out.println("Le nombre total de lots : " + fichier.getInt("nbrTotalLots"));
        System.out.println(fichier.getJSONArray("valeursParLot"));
        System.out.println("Le nombre de lots agricoles : " + fichier.getInt("nbrLotAgricole"));
        System.out.println("Le nombre de lots commerciaux : " + fichier.getInt("nbrLotCommercial"));
        System.out.println("Le nombre de lots residentiels : " + fichier.getInt("nbrLotResidentiel"));
        System.out.println("La plus grande superficie de lot traite : "+ fichier.getJSONArray("superficiesMaximales"));
        System.out.println("La valeur maximale de lot traite : " + fichier.getJSONArray("valeursMaximales"));
    }

    final void reinitialiser() {
        File f = new File(NOM_FICHIER);
        try (FileWriter writer = new FileWriter(f)) {
            String rapportString = contenueInitiale();
            writer.write(rapportString);
        } catch (IOException e) {
            System.out.println("Une erreur dans l'ecriture du fichier Statistique.json est survenue.");
        }
    }

    private String contenueInitiale() {
        JSONObject contenue = new JSONObject();
        contenue.accumulate("nbrTotalLots", 0);
        contenue.accumulate("valeursParLot", new JSONArray());
        contenue.accumulate("nbrLotAgricole", 0);
        contenue.accumulate("nbrLotCommercial", 0);
        contenue.accumulate("nbrLotResidentiel", 0);
        contenue.accumulate("superficiesMaximales", new JSONArray());
        contenue.accumulate("valeursMaximales", new JSONArray());
        
        return contenue.toString();
    }

    void mettreAJour(Terrain terrain) {
        //Seul chose a implementer
    }
}
