/*
 * Copyright 2015 Vincent Brison.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package vb.android.app.quality.pi;

/**
 * Interface for generating Pi
 */
public interface PiGenerator {
    enum CalculationMethod {
        /**
         * Algorithm based on number of digits
         */
        DIGITS,
        /**
         * Algorithm based on number of iterations
         */
        ITERATIONS,
        /**
         * No calculation, return static Math.PI
         */
        STATIC,
    }

    /**
     * Returns the method of calculation of this generator.
     *
     * @return the method of calculation of this generator
     */
    CalculationMethod getCalculationMethod();

    /**
     * Compute Pi.
     *
     * @param max is used to parameter the algorithm used for computation (max iteration, max digits...).
     * @return the value of Pi.
     */
    double calcPiDigits(int max);
}
