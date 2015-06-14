package vb.android.app.quality;

/**
 * Interface for generating greeting
 */
public interface PiGenerator {
    /**
     * Compute maxDigits digits of Pi. This method is very slow and should be used to compute Pi in
     * an efficient way.
     * @param maxDigits is the number of digits of Pi to compute.
     * @return the value of Pi.
     */
    public double calcPiDigits(int maxDigits);
}
