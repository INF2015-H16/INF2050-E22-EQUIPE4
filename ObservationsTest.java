package evaluationfonciere;

import static org.junit.Assert.*;
import org.junit.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ObservationsTest {
    static Terrain unTerrain;
    static Observations test;

    //Creation d'un terrain qui devrait soulever toutes les observations
    @BeforeClass
    public static void setUp() throws Exception {
        JSONArray lots = new JSONArray();
        JSONObject unLot = new JSONObject()
            .accumulate("description", "1")
            .accumulate("nombre_droits_passage", 10)
            .accumulate("nombre_services", 1)
            .accumulate("superficie", 5)
            .accumulate("date_mesure", "2001-01-01");
        JSONObject unAutreLot = new JSONObject()
            .accumulate("description", "2")
            .accumulate("nombre_droits_passage", 0)
            .accumulate("nombre_services", 1)
            .accumulate("superficie", 45001) 
            .accumulate("date_mesure", "2001-08-01");
        lots.add(unLot);
        lots.add(unAutreLot);
        JSONObject terrainData = new JSONObject()
            .accumulate("type_terrain", 2)
            .accumulate("prix_m2_min", 3)
            .accumulate("prix_m2_max", 7)
            .accumulate("lotissements", lots);
        unTerrain = new Terrain(terrainData);
        test = new Observations(unTerrain);
    }    

    @Test
    public void testObservationValeurLot(){
        assertTrue(test.observations().contains("La valeur par lot du lot 2 est trop dispendieuse."));
    }

    @Test
    public void testObservationTaxeMunicipale(){
        assertTrue(test.observations().contains("La taxe municipale payable par le proprietaire necessite deux versements."));
    }

    @Test
    public void testObservationEcartDates(){
        assertTrue(test.observations().contains("L'ecart maximal entre les dates de mesure des lots d'un meme terrain devrait etre de moins de 6 mois."));
    }

    @Test
    public void testObservationValeurFonciereTotale(){
        assertTrue(test.observations().contains("La valeur fonciere totale est trop dispendieuse."));
    }

    @Test
    public void testObservationSuperficie(){
        assertTrue(test.observations().contains("La superficie par lot du lot 2 est trop grande."));
    }

    @Test
    public void testObservationPrixMinMax(){
        assertTrue(test.observations().contains("Le prix maximum par metres carres depasse de plus de deux fois le prix minimum par metres carres."));
    }

    @Test
    public void testObservationTaxeScolaire(){
        assertTrue(test.observations().contains("La taxe scolaire payable par le proprietaire necessite deux versements."));
    }
}
