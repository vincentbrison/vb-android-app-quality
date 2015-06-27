package vb.android.app.quality;

import vb.android.app.quality.pi.PiGenerator;

/**
 * Very slow class to compute PI.
 */
public final class PiCalculator implements PiGenerator {

    @Override
    public double calcPiDigits(int maxDigits) {
        return 3.1415;
    }
}