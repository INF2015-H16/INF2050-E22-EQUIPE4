package com.proudmusketeers;

import static org.junit.Assert.*;
import org.junit.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ValiderValeursTerrainTest {
    JSONObject testData;
    ValiderValeursTerrain validateur;
    final int MAX_LIMITE_LOTS = 10;

    @Before
    public void setUp() throws Exception {
        testData = new JSONObject();
        validateur = new ValiderValeursTerrain(testData);
    }

    @Test (expected = FormatInvalide.class)
    public void testPrixMinManquante() throws FormatInvalide{
        validateur.prixMin();
    }

    @Test (expected = FormatInvalide.class)
    public void testPrixMinNegative() throws FormatInvalide{
        testData.accumulate("prix_m2_min", -1);
        validateur.prixMin();
    }

    @Test
    public void testPrixMinValide() throws FormatInvalide {
        testData.accumulate("prix_m2_min", 1);
        assertEquals(1,validateur.prixMin(), 0);
    } 

    @Test (expected = FormatInvalide.class) 
    public void testPrixMaxManquante() throws FormatInvalide{
        validateur.prixMax();
    }

    @Test (expected = FormatInvalide.class)
    public void testPrixMaxNegative() throws FormatInvalide{
        testData.accumulate("prix_m2_max", -1);
        validateur.prixMax();
    }

    @Test
    public void testPrixMaxValide() throws FormatInvalide {
        testData.accumulate("prix_m2_max", 1);
        assertEquals(1,validateur.prixMax(), 0);
    }

    @Test (expected = FormatInvalide.class) 
    public void testTypeTerrainManquante() throws FormatInvalide{
        validateur.typeTerrain();
    }

    @Test (expected = FormatInvalide.class)
    public void testTypeTerrainNegative() throws FormatInvalide{
        testData.accumulate("type_terrain", 3);
        validateur.typeTerrain();
    }

    @Test
    public void testTypeTerrainValide() throws FormatInvalide {
        testData.accumulate("type_terrain", 1);
        assertEquals(1,validateur.typeTerrain(), 0);
    }

    @Test (expected = FormatInvalide.class) 
    public void testLotissementsManquante() throws FormatInvalide{
        validateur.lotissements();
    }

    @Test (expected = FormatInvalide.class)
    public void testLotissementsVide() throws FormatInvalide{
        JSONArray listeVide = new JSONArray();
        testData.accumulate("lotissements", listeVide);
        validateur.lotissements();
    }

    @Test (expected = FormatInvalide.class)
    public void TestLotissementsTaillePlusQueMaxLimite() throws FormatInvalide{
        JSONObject unLot = new JSONObject();
        unLot.put("nom", "lot1");
        JSONArray listeAvecTropLots = new JSONArray();

        for(int i = 0; i <= MAX_LIMITE_LOTS; i++) {
            listeAvecTropLots.add(unLot);
        }

        testData.accumulate("lotissements", listeAvecTropLots);
        validateur.lotissements();
    }
}
