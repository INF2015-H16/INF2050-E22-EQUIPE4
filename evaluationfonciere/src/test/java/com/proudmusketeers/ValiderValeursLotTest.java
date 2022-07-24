package com.proudmusketeers;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.rules.ExpectedException;

import net.sf.json.JSONObject;

public class ValiderValeursLotTest {

    JSONObject testData = new JSONObject(); 

    @Test (expected = FormatInvalide.class)
    public void testDescriptionPasDansDocument() throws FormatInvalide {
        ValiderValeursLot validateur = new ValiderValeursLot(testData);
        validateur.description();
    }

    @Test (expected = FormatInvalide.class)
    public void testDescriptionVide() throws FormatInvalide {
        testData.accumulate("description","");
        ValiderValeursLot validateur = new ValiderValeursLot(testData);
        validateur.description();
    }

    @Test
    public void testDescriptionValide(){
        testData.accumulate("description", "valide");
        ValiderValeursLot validateur = new ValiderValeursLot(testData);

        try {
            assertEquals("valide", validateur.description());
        } catch (FormatInvalide e) {
            fail("Ne devrait pas avoir de FormatInvalide exception");
        }
    }

    @Test
    public void testDescriptionAvecWhitespacesDebutFin(){
        testData.accumulate("description", " valide ");
        ValiderValeursLot validateur = new ValiderValeursLot(testData);

        try {
            assertEquals("valide", validateur.description());
        } catch (FormatInvalide e) {
            fail("Ne devrait pas avoir de FormatInvalide exception");
        }
    }
}
