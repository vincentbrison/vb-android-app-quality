package vb.android.app.quality;

import junit.framework.TestCase;

/**
 * Created by Vincent Brison on 14/06/2015.
 */
public class PiTest extends TestCase {

    public void testCalcPiDigits() throws Exception {
        if (Contants.FLAVOR.equals(Flavors.APPROXIMATION_PI)) {
            double pi = new Pi().calcPiDigits(500);
            assertTrue("After 500 interations, computed Pi (" +
                    pi +
                    ") should have at least 2 decimals rights.", pi <= 3.15 && pi >= 3.13);
        } else {
            double pi = new Pi().calcPiDigits(5);
            assertTrue("The 5 first decimals of computed Pi (" + pi + ") should be correct.", pi == 3.1415);
        }

    }
}