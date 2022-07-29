
package evaluationfonciere;

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

    static void afficher() {
        //Si existe pas cree le fichier avec tout a zero
        //Faire la methode qui lit le fichier et affiche toute les statistique estehiquement
    }

    static void reinitialiser() {
        //Si nexiste pas cree le ficheir avec tout a zero
        //Faire la methode lit le fichier et reinitialise tout a zero
    }

    static void traiter(String arg) {
        switch (arg.toUpperCase()) {
            case "-S" -> Statistique.afficher();
            case "-SR" -> Statistique.reinitialiser();
            default -> System.out.println(Statistique.MSG_ERREUR);
        }
    }
    
    
    
}
