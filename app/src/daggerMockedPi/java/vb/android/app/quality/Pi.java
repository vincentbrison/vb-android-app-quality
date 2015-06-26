package vb.android.app.quality;

import java.math.BigInteger ;

import vb.android.app.quality.pi.PiGenerator;

/**
 * Very slow class to compute PI.
 */
public final class Pi implements PiGenerator {
    public Pi() {

    }

    @Override
    public double calcPiDigits(int maxDigits){
        return 3.1415;
    }
}