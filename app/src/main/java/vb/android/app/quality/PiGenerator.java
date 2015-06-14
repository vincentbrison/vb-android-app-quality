package vb.android.app.quality;

/**
 * Interface for generating greeting
 */
public interface PiGenerator {
    /**
     * Compute Pi.
     * @param max is used to parameter the algorithm used for computation (max iteration, max digits...).
     * @return the value of Pi.
     */
    public double calcPiDigits(int max);
}
