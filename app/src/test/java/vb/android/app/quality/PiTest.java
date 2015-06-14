package vb.android.app.quality;

import junit.framework.TestCase;

/**
 * Created by Vincent Brison on 14/06/2015.
 */
public class PiTest extends TestCase {

    public void testCalcPiDigits() throws Exception {
        assertTrue(new Pi().calcPiDigits(5) == 3.1415);
    }
}