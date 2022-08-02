package evaluationfonciere;

import static org.junit.Assert.*;
import org.junit.*;

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
public class ValiderValeursLotTest {

    JSONObject testData;
    ValiderValeursLot validateur;

    @Before
    public void setUp() throws Exception {
        testData = new JSONObject();
        validateur = new ValiderValeursLot(testData);
    }

    @Test
    public void testDescriptionManquante() {
        try {
            validateur.description();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <description> d'un ou plusieurs lots est manquante dans le fichier d'entree.", e.getMessage());
        }
    }

    @Test
    public void testDescriptionVide() {
        try {
            testData.accumulate("description","");
            validateur.description();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <description> de chaque lot ne doit pas etre vide.", e.getMessage());
        }
    }

    @Test
    public void testDescriptionValide() throws FormatInvalide {
        testData.accumulate("description", "valide");
        assertEquals("valide", validateur.description());
    }

    @Test
    public void testDescriptionAvecWhitespacesDebutFin() throws FormatInvalide {
        testData.accumulate("description", " valide ");
        assertEquals("valide", validateur.description());
    }

    @Test
    public void testNbDroitsPassageManquante() {
        try{
            validateur.nbDroitsPassages();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <nombre_droits_passage> d'un ou plusieurs lots est manquante dans le fichier d'entree.", e.getMessage());
        }
    }

    @Test
    public void testNbDroitsPassagePlusQueDix() {
        try{
            testData.accumulate("nombre_droits_passage", 11);
            validateur.nbDroitsPassages();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <nombre_droits_passage> de chaque lot ne doit pas depasser 10.", e.getMessage());
        }
    }

    @Test
    public void testNbDroitsPassageNegatif(){
        try{
            testData.accumulate("nombre_droits_passage", -1);
            validateur.nbDroitsPassages();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <nombre_droits_passage> de chaque lot ne doit pas etre inferieure a 0.", e.getMessage());
        }
    }

    @Test
    public void testNbDroitsPassageValide() throws FormatInvalide {
        testData.accumulate("nombre_droits_passage", 10);
        assertEquals(10,validateur.nbDroitsPassages());
    }

    @Test
    public void testDateMesureManquante() {
        try {
            validateur.dateMesure();
        } catch (FormatInvalide e){
            assertEquals("La propriete <date_mesure> d'un ou plusieurs lots est manquante dans le fichier d'entree.", e.getMessage());
        }
    }

    @Test
    public void testDateMesureMauvaiseNotationAnneeJourMois(){
        try {
            testData.accumulate("date_mesure", "2001-30-07");
            validateur.dateMesure();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <date_mesure> de chaque lot doit etre dans le format ISO8601 (aaaa-mm-jj).", e.getMessage());
        }
    }

    @Test
    public void testDateMesureMauvaiseNotationJourMoisAnnee() {
        try {
            testData.accumulate("date_mesure", "30-07-2001");
            validateur.dateMesure();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <date_mesure> de chaque lot doit etre dans le format ISO8601 (aaaa-mm-jj).", e.getMessage());
        }
    }

    @Test
    public void testDateMesureMauvaiseNotationMoisJourAnnee() {
        try {
            testData.accumulate("date_mesure", "07-30-2001");
            validateur.dateMesure();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <date_mesure> de chaque lot doit etre dans le format ISO8601 (aaaa-mm-jj).", e.getMessage());
        }
    }

    @Test
    public void testDateMesureMauvaiseNotationJourAnneeMois() {
        try { 
            testData.accumulate("date_mesure", "30-2001-07");
            validateur.dateMesure();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <date_mesure> de chaque lot doit etre dans le format ISO8601 (aaaa-mm-jj).", e.getMessage());
        }
    }

    @Test
    public void testDateMesureMauvaiseNotationMoisAnneeJour() {
        try {
            testData.accumulate("date_mesure", "07-2001-30");
            validateur.dateMesure();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <date_mesure> de chaque lot doit etre dans le format ISO8601 (aaaa-mm-jj).", e.getMessage());
        }
    }

    @Test
    public void testDateMesureValide() throws FormatInvalide {
        testData.accumulate("date_mesure", "2001-07-30");
        validateur.dateMesure();
    }

    @Test
    public void testNbServicesManquante() {
        try {
            validateur.nbServices();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <nombre_services> d'un lot est manquante dans le fichier d'entree.", e.getMessage());
        }
    }

    @Test
    public void testNbServicesPlusQueCinq() {
        try {
            testData.accumulate("nombre_services", "6");
            validateur.nbServices();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <nombre_services> de chaque lot ne peut pas depasser 5.", e.getMessage());
        }
    }

    @Test
    public void testNbServicesNegatif() throws FormatInvalide {
        try {
            testData.accumulate("nombre_services", "-1");
            validateur.nbServices();
        } catch (FormatInvalide e) {
            assertEquals("Le propriete <nombre_services> de chaque lot ne peut pas etre inferieure a 0.", e.getMessage());
        }
    }

    @Test
    public void testNbServiceValide() throws FormatInvalide {
        testData.accumulate("nb_services", "5");
    }

    @Test 
    public void testSuperficieManquante() {
        try {
            validateur.superficie();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <superficie> d'un lot est manquante dans le fichier d'entree.", e.getMessage());
        }
    }

    @Test
    public void testSuperficiePlusQueCinquanteMilles() {
        try { 
            testData.accumulate("superficie", 50001);
            validateur.superficie();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <superficie> de chaque lot ne doit pas depasser 50000 metres carres.", e.getMessage());
        }
    }

    @Test 
    public void testSuperficieNegative() {
        try{
            testData.accumulate("superficie", -1);
            validateur.superficie();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <superficie> de chaque lot ne doit pas etre negative.", e.getMessage());
        }
    }

    @Test
    public void testSuperficieValide() throws FormatInvalide{
        testData.accumulate("superficie", 50000);
        assertEquals(50000, validateur.superficie());
    }
}
