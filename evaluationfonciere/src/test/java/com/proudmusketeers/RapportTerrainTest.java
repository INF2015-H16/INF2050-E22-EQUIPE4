package com.proudmusketeers;

import static org.junit.Assert.*;
import org.junit.*;

import net.sf.json.JSONArray;
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
public class RapportTerrainTest {
    JSONObject terrainData; 
    Terrain unTerrain; 
    JSONObject rapportTerrainAttendu; 
    JSONArray lots;
    JSONObject unLot;
    RapportTerrain sortie;

    @Before
    public void setUp() throws Exception {
        lots = new JSONArray();
        unLot = new JSONObject()
            .accumulate("description", "valide")
            .accumulate("nombre_droits_passage", 10)
            .accumulate("nombre_services", 1)
            .accumulate("superficie", 500)
            .accumulate("date_mesure", "2001-07-30");
        lots.add(unLot);
        terrainData = new JSONObject()
            .accumulate("type_terrain", 0)
            .accumulate("prix_m2_min", 1)
            .accumulate("prix_m2_max", 2)
            .accumulate("lotissements", lots);
        unTerrain = new Terrain(terrainData);
        sortie = new RapportTerrain();
    };

    @Test
    public void testRapportTerrain() {
        lots = new JSONArray();
        unLot = new JSONObject()
            .accumulate("description", "valide")
            .accumulate("valeur_par_lot", "750.00 $");
        lots.add(unLot);
        rapportTerrainAttendu = new JSONObject()
            .accumulate("valeur_fonciere_totale", "1483.75 $")
            .accumulate("taxe_scolaire", "17.80 $")
            .accumulate("taxe_municipale", "37.10 $")
            .accumulate("lotissements", lots);

        assertEquals(rapportTerrainAttendu, sortie.rapport(unTerrain));
    }
}