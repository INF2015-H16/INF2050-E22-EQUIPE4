package com.proudmusketeers;

import static org.junit.Assert.*;
import org.junit.*;

import net.sf.json.JSONObject;

public class LotissementAgricoleTest {
    JSONObject testData;
    LotissementAgricole lotTest;

    @Before
    public void setUp() throws Exception {
        testData = new JSONObject();
        testData.accumulate("description", "valide");
        testData.accumulate("nombre_droits_passage", 10);
        testData.accumulate("date_mesure", "2001-07-30");
        testData.accumulate("nombre_services", 1);
        testData.accumulate("superficie", 500);
    }

    //retourne 0
    @Test
    public void testMontantServicesCinqCentsEtMoins() throws FormatInvalide {
        lotTest = new LotissementAgricole(testData);
        assertEquals(0, lotTest.montantServices(), 0);
    }

    //superficie = 500; prixMin = 1 -> 250
    @Test
    public void testMontantDroitDePassages() throws FormatInvalide {
        double[] MinMax ={1, 5};
        lotTest = new LotissementAgricole(testData);
        lotTest.setPrixMinMax(MinMax);
        assertEquals(250, lotTest.montantDroitDePassages(), 0);
    }

    //superficie = 500; prixMin = 1 -> 500
    @Test
    public void testValeurSuperficie() throws FormatInvalide {
        double[] MinMax ={1, 5};
        lotTest = new LotissementAgricole(testData);
        lotTest.setPrixMinMax(MinMax);
        assertEquals(500, lotTest.valeurSuperficie(), 0);
    }
}
