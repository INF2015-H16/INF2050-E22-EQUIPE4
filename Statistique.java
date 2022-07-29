
package evaluationfonciere;

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
    
    static void traiter(String arg) {
        switch (arg.toUpperCase()) {
            case "-S" -> Statistique.afficher();
            case "-SR" -> Statistique.reinitialiser();
            default -> System.out.println(Statistique.MSG_ERREUR);
        }
    }

    static void afficher() {
        //Si existe pas cree le fichier avec tout a zero
        //Faudrait toruver comment veriifer lexistance dun fichier
        Statistique.creeStatsInitiale();
        //Faire la methode qui lit le fichier et affiche toute les statistique estehiquement
    }

    static void reinitialiser() {
        //Si nexiste pas cree le ficheir avec tout a zero
        //Supprime le fichier et appelle
        Statistique.creeStatsInitiale();
    }

    static void mettreAJour(Terrain terrain) {
        //Si nexiste pas cree le ficheir avec tout a zero
        //Faudrait truover comment veriifer lexistance dun fichier
        Statistique.creeStatsInitiale();
        //Rajouter les donnes dans le fichier de statisqitique
        try {
            String texteSource = new String(Files.readAllBytes(Paths.get("Statistiques.json")));
            JSONObject JSONSource = JSONObject.fromObject(texteSource);
            Statistique stats = new Statistique(JSONSource);
            stats.ajouter(terrain);
        } catch (IOException e) {
            System.out.print("Erreur dans la lecture du fichier de statistiques.");
        }
    }

    private static void creeStatsInitiale() {
        //Cree le fichier vide
    }
    ///Fin des methode static
    
    private int nbrTotalLots = 0;
    private int[] valeursParLot = new int[3]; //[0] moins de 1000, [1] 1000 a 10000, [2] 10000 et plus
    private int nbrLotAgricole = 0;
    private int nbrLotCommercial = 0;
    private int nbrLotResidentiel = 0;
    private List<Integer> superficieMaximale = new ArrayList<>();//liste des superficie maximale a laquell on rajoute a chaque fois
    private List<Integer> valeurMaximale = new ArrayList<>();//liste des valeurs maximale a laquell on rajoute a chaque fois
    
    private Statistique(JSONObject stats) {
        //ici des affaire du genre this.nbrTotalLots = stats.getInt("nbrTotalLots");
    }

    private void ajouter(Terrain terrain) {
        //Ici des affaires du genre this.nbrTotalLots += nbrTotalLots(terrain);
    }
    
    
    
}
