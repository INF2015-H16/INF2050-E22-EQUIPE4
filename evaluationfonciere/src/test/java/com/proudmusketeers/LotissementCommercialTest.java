package com.proudmusketeers;

import static org.junit.Assert.*;
import org.junit.*;

import net.sf.json.JSONObject;

public class LotissementCommercialTest {
    JSONObject testData;
    LotissementCommercial lotTest;

    @Before
    public void setUp() throws Exception {
        testData = new JSONObject();
        testData.accumulate("description", "valide");
        testData.accumulate("nombre_droits_passage", 10);
        testData.accumulate("date_mesure", "2001-07-30");
    }

    //service = 1; superficie = 500 -> 1500
    @Test
    public void testMontantServicesCinqCentsEtMoins() throws FormatInvalide {
        testData.accumulate("nombre_services", 1);
        testData.accumulate("superficie", 500);
        lotTest = new LotissementCommercial(testData);
        assertEquals(1500, lotTest.montantServices(), 0);
    }

    //service = 1; superficie = 501 -> 4500
    @Test
    public void testMontantServicesPlusQueCinqCents() throws FormatInvalide {
        testData.accumulate("nombre_services", 1);
        testData.accumulate("superficie", 501);
        lotTest = new LotissementCommercial(testData);
        assertEquals(4500, lotTest.montantServices(), 0);
    }

    //service = 2; superficie = 501; resultat = 6000 -> 5000
    @Test
    public void testMontantServicesResultatPlusQueCinqMille() throws FormatInvalide {
        testData.accumulate("nombre_services", 2);
        testData.accumulate("superficie", 501);
        lotTest = new LotissementCommercial(testData);
        assertEquals(5000, lotTest.montantServices(), 0);
    }

    //superficie = 500; prixMax = 5 -> -3250
    @Test
    public void testMontantDroitDePassages() throws FormatInvalide {
        double[] MinMax ={1, 5};
        testData.accumulate("nombre_services", 1);
        testData.accumulate("superficie", 500);
        lotTest = new LotissementCommercial(testData);
        lotTest.setPrixMinMax(MinMax);
        assertEquals(-3250, lotTest.montantDroitDePassages(), 0);
    }

    //superficie = 500; prixMax = 5 -> 2500
    @Test
    public void testValeurSuperficie() throws FormatInvalide {
        double[] MinMax ={1, 5};
        testData.accumulate("nombre_services", 1);
        testData.accumulate("superficie", 500);
        lotTest = new LotissementCommercial(testData);
        lotTest.setPrixMinMax(MinMax);
        assertEquals(2500, lotTest.valeurSuperficie(), 0);
    }
}
