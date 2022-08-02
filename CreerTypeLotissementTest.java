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
public class CreerTypeLotissementTest {
    static JSONObject testData;
    static CreerTypeLotissement nouveauLot;

    @BeforeClass
    public static void setUp() throws Exception {
        testData = new JSONObject();
        testData.accumulate("description", "valide");
        testData.accumulate("nombre_droits_passage", 10);
        testData.accumulate("date_mesure", "2001-07-30");
        testData.accumulate("nombre_services", 1);
        testData.accumulate("superficie", 500);
        nouveauLot = new CreerTypeLotissement();
    }

    @Test
    public void testCreerLotissementTypeZero() throws FormatInvalide{
        LotissementAgricole lotAgricoleTest = new LotissementAgricole(testData);
        assertEquals(lotAgricoleTest.getClass(), nouveauLot.creerLotissement(0, testData).getClass()); 
    }

    @Test
    public void testCreerLotissementTypeUn() throws FormatInvalide{
        LotissementResidentiel lotResidentielTest = new LotissementResidentiel(testData);
        assertEquals(lotResidentielTest.getClass(), nouveauLot.creerLotissement(1, testData).getClass()); 
    }

    @Test
    public void testCreerLotissementTypeDeux() throws FormatInvalide{
        LotissementCommercial lotCommercialTest = new LotissementCommercial(testData);
        assertEquals(lotCommercialTest.getClass(), nouveauLot.creerLotissement(2, testData).getClass()); 
    }

    @AfterClass
    public static void tearDown(){
        testData = null;
        nouveauLot = null;
    }
}
