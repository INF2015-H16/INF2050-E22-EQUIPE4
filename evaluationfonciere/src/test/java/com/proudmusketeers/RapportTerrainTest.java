package com.proudmusketeers;

import static org.junit.Assert.*;
import org.junit.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class RapportTerrainTest {
    JSONObject testData;
    Terrain lotTest;
    JSONObject rapport;
    JSONArray lots;
    JSONObject fill;
    RapportTerrain rapportTest;

    @Before
    public void setUp() throws Exception {
        testData = new JSONObject();
        lots = new JSONArray();
        fill = new JSONObject();
        fill.accumulate("description", "valide");
        fill.accumulate("nombre_droits_passage", 10);
        fill.accumulate("date_mesure", "2001-07-30");
        fill.accumulate("nombre_services", 1);
        fill.accumulate("superficie", 500);
        fill.accumulate("valeur_par_lot", "750.00 $");
        lots.add(fill);
        testData.accumulate("lotissements", lots);
        testData.accumulate("type_terrain", 0);
        testData.accumulate("prix_m2_min", 1);
        testData.accumulate("prix_m2_max", 5);
        lotTest = new Terrain(testData);
    };

    @Test
    public void testRapportTerrain() {
        rapport = new JSONObject();
        lots = new JSONArray();
        fill = new JSONObject();
        fill.accumulate("description", "valide");
        fill.accumulate("valeur_par_lot", "750.00 $");
        lots.add(fill);
        rapportTest = new RapportTerrain();
        rapport.accumulate("valeur_fonciere_totale", "1483.75 $");
        rapport.accumulate("taxe_scolaire", "17.80 $");
        rapport.accumulate("taxe_municipale", "37.10 $");
        rapport.accumulate("lotissements", lots);
        assertEquals(rapport, rapportTest.rapport(lotTest));
    }
}