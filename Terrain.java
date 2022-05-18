
package evaluationfonciere;

/**
 *
 * @author 
 */
abstract class Terrain {
    double prixMin;
    double prixMax;
    double valeurFonciereTotale;
    double taxeScolaire;
    double taxeMunicipale;
    double montantServices;
    Lotissement[] lotissements;
    final double prixFixe = 733.77;
    double montantBase = 500; //Je l'ai mis ici et non dans chaque sub-class pour eviter la repetition
    
    public Terrain(){
        
    }
    
}
