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

    @Test
    public void testPrixMinManquante() {
        try{
            validateur.prixMin();
        } catch (FormatInvalide e){
            assertEquals("La propriete <prix_m2_min> est manquante dans le fichier d'entree.", e.getMessage());
        }
        
    }

    @Test
    public void testPrixMinNegative() {
        try{
            testData.accumulate("prix_m2_min", -1);
            validateur.prixMin();
        } catch (FormatInvalide e){
            assertEquals("La propriete <prix_m2_min> ne peut pas etre negative.", e.getMessage());
        }
    }

    @Test
    public void testPrixMinValide() throws FormatInvalide {
        testData.accumulate("prix_m2_min", 1);
        assertEquals(1,validateur.prixMin(), 0);
    } 

    @Test
    public void testPrixMaxManquante() {
        try {
            validateur.prixMax();
        } catch (FormatInvalide e) {
            assertEquals("La propriete <prix_m2_max> est manquante dans le fichier d'entree.", e.getMessage());
        }
    }

    @Test
    public void testPrixMaxNegative() {
        try{
            testData.accumulate("prix_m2_max", -1);
            validateur.prixMax();
        } catch (FormatInvalide e){
            assertEquals("La propriete <prix_m2_max> ne peut pas etre negative.", e.getMessage());
        }
    }

    @Test
    public void testPrixMaxValide() throws FormatInvalide {
        testData.accumulate("prix_m2_max", 1);
        assertEquals(1,validateur.prixMax(), 0);
    }

    @Test 
    public void testTypeTerrainManquante() {
        try{    
            validateur.typeTerrain();
        } catch (FormatInvalide e){
            assertEquals("La propriete <type_terrain> est manquante dans le fichier d'entree.", e.getMessage());
        }
    }

    @Test
    public void testTypeTerrainNonValide() {
        try{   
            testData.accumulate("type_terrain", 3);
            validateur.typeTerrain();
        } catch (FormatInvalide e){
            assertEquals("La propriete <type_terrain> n'est pas la valeur 0, 1 ou 2.", e.getMessage());
        }
    }

    @Test
    public void testTypeTerrainValide() throws FormatInvalide {
        testData.accumulate("type_terrain", 1);
        assertEquals(1,validateur.typeTerrain(), 0);
    }

    @Test
    public void testLotissementsManquante() {
        try{
            validateur.lotissements();
        } catch (FormatInvalide e){
            assertEquals("La propriete <lotissements> est manquante dans le fichier d'entree.", e.getMessage());
        }
    }

    @Test
    public void testLotissementsVide() {
        JSONArray listeVide = new JSONArray();
        try {
            testData.accumulate("lotissements", listeVide);
            validateur.lotissements();
        } catch (FormatInvalide e){
            assertEquals("La propriete <lotissements> doit comporter au moins un lot.", e.getMessage());
        }
    }

    @Test
    public void TestLotissementsTaillePlusQueMaxLimite() {
        JSONObject unLot = new JSONObject();
        unLot.put("nom", "lot1");
        JSONArray listeAvecTropLots = new JSONArray();
        for(int i = 0; i <= MAX_LIMITE_LOTS; i++) {
            listeAvecTropLots.add(unLot);
        }

        try{
            testData.accumulate("lotissements", listeAvecTropLots);
            validateur.lotissements();
        } catch (FormatInvalide e) {
            assertEquals("Le nombre de lots dans la propriete <lotissements> ne doit pas depasser 10 lots.", e.getMessage());
        }
    }
}
