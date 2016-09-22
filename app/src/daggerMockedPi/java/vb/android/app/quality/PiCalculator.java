package vb.android.app.quality;

import vb.android.app.quality.pi.PiGenerator;

/**
 * Mock class to return static value of PI.
 */
public final class PiCalculator implements PiGenerator {
    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.STATIC;
    }

    @Override
    public double calcPiDigits(int max) {
        return Math.PI;
    }
}
