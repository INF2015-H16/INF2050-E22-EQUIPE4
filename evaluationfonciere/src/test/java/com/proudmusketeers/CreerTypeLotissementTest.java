package com.proudmusketeers;

import static org.junit.Assert.*;
import org.junit.*;

import net.sf.json.JSONObject;

public class CreerTypeLotissementTest {
    JSONObject testData;
    CreerTypeLotissement lotTest;
    LotissementAgricole lotAgricoleTest;
    LotissementResidentiel lotResidentielTest;
    LotissementCommercial lotCommercialTest;

    @Before
    public void setUp() throws Exception {
        testData = new JSONObject();
        testData.accumulate("description", "valide");
        testData.accumulate("nombre_droits_passage", 10);
        testData.accumulate("date_mesure", "2001-07-30");
        testData.accumulate("nombre_services", 1);
        testData.accumulate("superficie", 500);
        lotTest = new CreerTypeLotissement();
    }

    @Test
    public void testCreerLotissementTypeZero() throws FormatInvalide{
        lotAgricoleTest = new LotissementAgricole(testData);
        assertEquals(lotAgricoleTest.getClass(), lotTest.creerLotissement(0, testData).getClass()); 
    }

    @Test
    public void testCreerLotissementTypeUn() throws FormatInvalide{
        lotResidentielTest = new LotissementResidentiel(testData);
        assertEquals(lotResidentielTest.getClass(), lotTest.creerLotissement(1, testData).getClass()); 
    }

    @Test
    public void testCreerLotissementTypeDeux() throws FormatInvalide{
        lotCommercialTest = new LotissementCommercial(testData);
        assertEquals(lotCommercialTest.getClass(), lotTest.creerLotissement(2, testData).getClass()); 
    }
}
