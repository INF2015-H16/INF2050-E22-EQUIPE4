package com.proudmusketeers;

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
public class TraitementTest {
    JSONObject testData;
    Traitement testTraitement;

    @Before
    public void setUp() throws Exception {
        testData = new JSONObject();
        testTraitement = new Traitement();
    }

    @Test
    public void testLireFichierEntreeManquant() {
        try {
            String[] args = {"test1"};
            testTraitement.lireFichierEntree(args);
        } catch (FormatInvalide e) {
            assertEquals("Fichier d'entree ou de sortie manquant.", e.getMessage());
        }
    }
}
