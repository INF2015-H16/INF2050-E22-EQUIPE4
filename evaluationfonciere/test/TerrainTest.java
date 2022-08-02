package com.proudmusketeers;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * 
 *
 * @author Leonid Glazyrin GLAL77080105
 *         Goldlen Chhun CHHG20069604
 *         Steven Chieng CHIS01069604
 *         Eric Drapeau DRAE21079108
 * 
 */
public class TerrainTest
{
    //10.00 -> 10.00
    @Test
    public void testArrondiAuZeroCentimesDirect(){
        assertEquals(10.00, Terrain.arrondiAu5sous(10.00), 0);
    }

    //10.05 -> 10.05
    @Test
    public void testArrondiAuCinqCentimesDirect(){
        assertEquals(10.05, Terrain.arrondiAu5sous(10.05), 0);
    }

    // 9.98 -> 10.00
    @Test
    public void testArrondiAuZeroCentimeVersLeHaut()
    {
        assertEquals(10.00, Terrain.arrondiAu5sous(9.98),  0);
    }

    // 10.02 -> 10.00
    @Test
    public void testArrondiAuZeroCentimeVersLeBas()
    {
        assertEquals(10.00, Terrain.arrondiAu5sous(10.02), 0);
    }

    // 10.03 -> 10.05
    @Test
    public void testArrondiAuCinqCentimesVersLeHaut()
    {
        assertEquals(10.05, Terrain.arrondiAu5sous(10.03), 0);
    }

    // 10.07 -> 10.05
    @Test
    public void testArrondiAuCinqCentimesVersLeBas()
    {
        assertEquals(10.05, Terrain.arrondiAu5sous(10.07), 0);
    }
}
