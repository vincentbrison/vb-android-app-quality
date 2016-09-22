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

package vb.android.app.quality;

import junit.framework.TestCase;

import java.util.Locale;

import vb.android.app.quality.pi.PiGenerator;

/**
 * Created by Vincent Brison on 14/06/2015.
 */
public class PiCalculatorTest extends TestCase {
    public void testCalcPiDigits() throws Exception {
        PiCalculator calculator = new PiCalculator();
        PiGenerator.CalculationMethod method = calculator.getCalculationMethod();
        int max = 0;
        double allowedDeviation = 0f;
        double pi;

        if (method == PiGenerator.CalculationMethod.ITERATIONS) {
            max = 500;
            allowedDeviation = 0.005f;
        } else if (method == PiGenerator.CalculationMethod.DIGITS) {
            max = 5;
            allowedDeviation = 0.0001f;
        } else if (method == PiGenerator.CalculationMethod.STATIC) {
            allowedDeviation = 0f;
        } else {
            fail("Unsupported calculation method: " + method);
        }

        pi = calculator.calcPiDigits(max);

        String message = String.format(Locale.US,
                "Using calculation method %s with max of %d", method, max);

        if (allowedDeviation == 0f) {
            assertEquals(message + ", calculated value (" + pi + ") does not match PI", Math.PI, pi);
        } else {
            assertTrue(message + ", calculated value (" + pi + ") not within bounds (" + allowedDeviation + ")",
                    pi >= Math.PI - allowedDeviation && pi <= Math.PI + allowedDeviation);
        }
    }
}
