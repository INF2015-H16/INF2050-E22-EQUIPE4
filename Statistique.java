
package evaluationfonciere;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

//    static void mettreAJour(Terrain terrain) {
//        //Si nexiste pas cree le ficheir avec tout a zero
//        //Faudrait truover comment veriifer lexistance dun fichier
//        Statistique.creeStatsInitiale();
//        //Rajouter les donnes dans le fichier de statisqitique
//        try {
//            String texteSource = new String(Files.readAllBytes(Paths.get("Statistiques.json")));
//            JSONObject JSONSource = JSONObject.fromObject(texteSource);
//            Statistique stats = new Statistique(JSONSource);
//            stats.ajouter(terrain);
//        } catch (IOException e) {
//            System.out.print("Erreur dans la lecture du fichier de statistiques.");
//        }
//    }
    ///Fin des methode static
    
//    private void ajouter(Terrain terrain) {
//        //Ici des affaires du genre this.nbrTotalLots += nbrTotalLots(terrain);
//    }
    
    private int nbrTotalLots = 0;
    private List<Integer> valeursParLot = new ArrayList<>(3); //[0] moins de 1000, [1] 1000 a 10000, [2] 10000 et plus
    private int nbrLotAgricole = 0;
    private int nbrLotCommercial = 0;
    private int nbrLotResidentiel = 0;
    private List<Integer> superficiesMaximales = new ArrayList<>();//liste des superficie maximale a laquell on rajoute a chaque fois
    private List<Integer> valeursMaximales = new ArrayList<>();//liste des valeurs maximale a laquell on rajoute a chaque fois

    public Statistique() {
        File f = new File(NOM_FICHIER);
        if(f.exists() && !f.isDirectory()) { 
            JSONObject fichier = getFichier();
            this.nbrTotalLots = fichier.getInt("nbrTotalLots");
            this.valeursParLot = fichier.getJSONArray("valeursParLot");
            this.nbrLotCommercial = fichier.getInt("nbrLotCommercial");
            this.nbrLotResidentiel = fichier.getInt("nbrLotResidentiel");
            this.nbrLotAgricole = fichier.getInt("nbrLotAgricole");
            this.superficiesMaximales = fichier.getJSONArray("superficiesMaximales");
            this.valeursMaximales = fichier.getJSONArray("valeursMaximales");
        } else {
            reinitialiser();
        }
    }

    private JSONObject getFichier() {
        try {
            JSONObject fichier = JSONObject.fromObject(new String(Files.readAllBytes(Paths.get(NOM_FICHIER))));
            return fichier;
        } catch (IOException e) {
            System.out.println("Une erreur dans la lecture du fichier Statistique.json est survenue.");
        }
    }
    
    void afficher() {
        //Si existe pas cree le fichier avec tout a zero
        //Faudrait toruver comment veriifer lexistance dun fichier
        //Faire la methode qui lit le fichier et affiche toute les statistique estehiquement
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
