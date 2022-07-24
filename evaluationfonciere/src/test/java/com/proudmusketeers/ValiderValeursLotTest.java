package com.proudmusketeers;

import static org.junit.Assert.*;
import org.junit.*;

import net.sf.json.JSONObject;

public class ValiderValeursLotTest {

    JSONObject testData;
    ValiderValeursLot validateur;

    @Before
    public void setUp() throws Exception {
        testData = new JSONObject();
        validateur = new ValiderValeursLot(testData);
    }

    @Test (expected = FormatInvalide.class)
    public void testDescriptionManquante() throws FormatInvalide {
        validateur.description();
    }

    @Test (expected = FormatInvalide.class)
    public void testDescriptionVide() throws FormatInvalide {
        testData.accumulate("description","");
        validateur.description();
    }

    @Test
    public void testDescriptionValide() throws FormatInvalide {
        testData.accumulate("description", "valide");
        assertEquals("valide", validateur.description());

    }

    @Test
    public void testDescriptionAvecWhitespacesDebutFin() throws FormatInvalide {
        testData.accumulate("description", " valide ");
        assertEquals("valide", validateur.description());
    }

    @Test (expected = FormatInvalide.class)
    public void testNbDroitsPassageManquante() throws FormatInvalide {
        validateur.nbDroitsPassages();
    }

    @Test (expected = FormatInvalide.class)
    public void testNbDroitsPassagePlusQueDix() throws FormatInvalide {
        testData.accumulate("nombre_droits_passage", 11);
        validateur.nbDroitsPassages();
    }

    @Test (expected = FormatInvalide.class)
    public void testNbDroitsPassageNegatif() throws FormatInvalide {
        testData.accumulate("nombre_droits_passage", -1);
        validateur.nbDroitsPassages();
    }

    @Test
    public void testNbDroitsPassageValide() throws FormatInvalide {
        testData.accumulate("nombre_droits_passage", 10);
        assertEquals(10,validateur.nbDroitsPassages());
    }

    @Test (expected = FormatInvalide.class)
    public void testDateMesureManquante() throws FormatInvalide {
        validateur.dateMesure();
    }

    @Test (expected = FormatInvalide.class)
    public void testDateMesureMauvaiseNotationAnneeJourMois() throws FormatInvalide {
        testData.accumulate("date_mesure", "2001-30-07");
        validateur.dateMesure();
    }

    @Test (expected = FormatInvalide.class)
    public void testDateMesureMauvaiseNotationJourMoisAnnee() throws FormatInvalide {
        testData.accumulate("date_mesure", "30-07-2001");
        validateur.dateMesure();
    }

    @Test (expected = FormatInvalide.class)
    public void testDateMesureMauvaiseNotationMoisJourAnnee() throws FormatInvalide {
        testData.accumulate("date_mesure", "07-30-2001");
        validateur.dateMesure();
    }

    @Test (expected = FormatInvalide.class)
    public void testDateMesureMauvaiseNotationJourAnneeMois() throws FormatInvalide {
        testData.accumulate("date_mesure", "30-2001-07");
        validateur.dateMesure();
    }

    @Test (expected = FormatInvalide.class)
    public void testDateMesureMauvaiseNotationMoisAnneeJour() throws FormatInvalide {
        testData.accumulate("date_mesure", "07-2001-30");
        validateur.dateMesure();
    }

    @Test
    public void testDateMesureValide() throws FormatInvalide {
        testData.accumulate("date_mesure", "2001-07-30");
        validateur.dateMesure();
    }

    @Test (expected = FormatInvalide.class)
    public void testNbServicesManquante() throws FormatInvalide {
        validateur.nbServices();
    }

    @Test (expected = FormatInvalide.class)
    public void testNbServicesPlusQueCinq() throws FormatInvalide {
        testData.accumulate("nb_services", "6");
        validateur.nbServices();
    }

    @Test (expected = FormatInvalide.class)
    public void testNbServicesNegatif() throws FormatInvalide {
        testData.accumulate("nb_services", "-1");
        validateur.nbServices();
    }

    @Test
    public void testNbServiceValide() throws FormatInvalide {
        testData.accumulate("nb_services", "5");
    }

    @Test (expected = FormatInvalide.class)
    public void testSuperficieManquante() throws FormatInvalide {
        validateur.superficie();
    }

    @Test (expected = FormatInvalide.class)
    public void testSuperficiePlusQueCinquanteMilles() throws FormatInvalide {
        testData.accumulate("superficie", 50001);
        validateur.superficie();
    }

    @Test (expected = FormatInvalide.class)
    public void testSuperficieNegative() throws FormatInvalide {
        testData.accumulate("superficie", -1);
        validateur.superficie();
    }

    @Test
    public void testSuperficieValide() throws FormatInvalide{
        testData.accumulate("superficie", 50000);
        assertEquals(50000, validateur.superficie());
    }
}
