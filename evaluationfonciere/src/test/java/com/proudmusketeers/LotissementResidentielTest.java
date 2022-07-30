package com.proudmusketeers;

import static org.junit.Assert.*;
import org.junit.*;

import net.sf.json.JSONObject;

public class LotissementResidentielTest {
    JSONObject testData;
    LotissementResidentiel lotResidentielTest;

    @Before
    public void setUp() throws Exception {
        testData = new JSONObject();
        testData.accumulate("description", "valide");
        testData.accumulate("nombre_droits_passage", 10);
        testData.accumulate("date_mesure", "2001-07-30");
        testData.accumulate("nombre_services", 1);
    }

    //superficie <= 500 -> 0
    @Test
    public void testMontantServicesCinqCentsEtMoins() throws FormatInvalide {
        testData.accumulate("superficie", 500);
        lotResidentielTest = new LotissementResidentiel(testData);
        assertEquals(0, lotResidentielTest.montantServices(), 0);
    }

    //500 > superficie <= 10000 -> 1500
    @Test
    public void testMontantServicesEntreCinqCentsEtDixMille() throws FormatInvalide {
        testData.accumulate("superficie", 10000);
        lotResidentielTest = new LotissementResidentiel(testData);
        assertEquals(1500, lotResidentielTest.montantServices(), 0);
    }

    //superficie > 10000 -> 3000
    @Test
    public void testMontantServicesPlusQueDixMille() throws FormatInvalide {
        testData.accumulate("superficie", 20000);
        lotResidentielTest = new LotissementResidentiel(testData);
        assertEquals(3000, lotResidentielTest.montantServices(), 0);
    }

    //superficie = 50000; prixMin = 1; prixMax = 5 -> -149500
    @Test
    public void testMontantDroitDePassages() throws FormatInvalide {
        double[] MinMax ={1, 5};
        testData.accumulate("superficie", 50000);
        lotResidentielTest = new LotissementResidentiel(testData);
        lotResidentielTest.setPrixMinMax(MinMax);
        assertEquals(-149500, lotResidentielTest.montantDroitDePassages(), 0);
    }

    //superficie = 50000; prixMin = 1; prixMax = 5 -> 150000
    @Test
    public void testValeurSuperficie() throws FormatInvalide {
        double[] MinMax ={1, 5};
        testData.accumulate("superficie", 50000);
        lotResidentielTest = new LotissementResidentiel(testData);
        lotResidentielTest.setPrixMinMax(MinMax);
        assertEquals(150000, lotResidentielTest.valeurSuperficie(), 0);
    }
}