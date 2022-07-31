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
public class LotissementAgricoleTest {
    static JSONObject testData;
    static LotissementAgricole lotTest;

    @BeforeClass
    public static void setUp() throws Exception {
        testData = new JSONObject();
        testData.accumulate("description", "valide");
        testData.accumulate("nombre_droits_passage", 10);
        testData.accumulate("date_mesure", "2001-07-30");
        testData.accumulate("nombre_services", 1);
        testData.accumulate("superficie", 500);
        lotTest = new LotissementAgricole(testData);
        double[] prixMinMax ={1, 5};
        lotTest.setPrixMinMax(prixMinMax);
    }

    //retourne 0
    @Test
    public void testMontantServicesLotAgricole() throws FormatInvalide {
        assertEquals(0, lotTest.montantServices(), 0);
    }

    //superficie = 500; prixMin = 1 -> 250
    @Test
    public void testMontantDroitDePassagesLotAgricole() throws FormatInvalide {
        assertEquals(250, lotTest.montantDroitDePassages(), 0);
    }

    //superficie = 500; prixMin = 1 -> 500
    @Test
    public void testValeurSuperficieLotAgricole() throws FormatInvalide {
        assertEquals(500, lotTest.valeurSuperficie(), 0);
    }

    @AfterClass
    public static void tearDown() {
        testData = null;
        lotTest = null;
    }
}
