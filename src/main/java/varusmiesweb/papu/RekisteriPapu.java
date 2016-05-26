package varusmiesweb.papu;

import VarusmiesDomain.Varusmiesrekisteri;

/**
 * Tämä toimii papuna sovelluksen kontrollereille
 * @author Aleksanteri
 * @version 1.0
 */
public class RekisteriPapu {
    
    private static Varusmiesrekisteri rekisteri = new Varusmiesrekisteri();
    
    /**
     * Palauttaa rekisteriolion
     * @return staattinen varusmiesrekisteriolio
     */
    public static Varusmiesrekisteri annaRekisteri() {
        return rekisteri;
    }
}
