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
public class RapportLotissementTest {
    JSONObject lotData;
    Lotissement lotTest;

    @Before
    public void setUp() throws Exception {
        lotData = new JSONObject();
        lotData.accumulate("description", "valide");
        lotData.accumulate("nombre_droits_passage", 10);
        lotData.accumulate("date_mesure", "2001-07-30");
        lotData.accumulate("nombre_services", 1);
        lotData.accumulate("superficie", 500);
        lotTest = new LotissementAgricole(lotData);
    }

    @Test
    public void testRapportLotissement() {
        JSONObject lotAttendu = new JSONObject();
        RapportLotissement sortie = new RapportLotissement();
        lotAttendu.accumulate("description", "valide");
        lotAttendu.accumulate("valeur_par_lot", "500.00 $");
        assertEquals(lotAttendu, sortie.rapport(lotTest));
    }
}
