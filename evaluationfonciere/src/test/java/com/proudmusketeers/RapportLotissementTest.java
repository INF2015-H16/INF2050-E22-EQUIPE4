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
public class RapportLotissementTest {
    JSONObject testData;
    Lotissement lotTest;
    JSONObject lot;
    RapportLotissement rapportTest;

    @Before
    public void setUp() throws Exception {
        testData = new JSONObject();
        testData.accumulate("description", "valide");
        testData.accumulate("nombre_droits_passage", 10);
        testData.accumulate("date_mesure", "2001-07-30");
        testData.accumulate("nombre_services", 1);
        testData.accumulate("superficie", 500);
        lotTest = new LotissementAgricole(testData);
    }

    @Test
    public void testRapportLotissement() {
        lot = new JSONObject();
        rapportTest = new RapportLotissement();
        lot.accumulate("description", "valide");
        lot.accumulate("valeur_par_lot", "500.00 $");
        assertEquals(lot, rapportTest.rapport(lotTest));
    }
}
