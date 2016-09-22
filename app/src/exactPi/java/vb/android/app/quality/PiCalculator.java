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

import java.math.BigInteger;

import vb.android.app.quality.pi.PiGenerator;

/**
 * Slow way to compute PI based on digits.
 */
public final class PiCalculator implements PiGenerator {
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final BigInteger THREE = BigInteger.valueOf(3);
    private static final BigInteger FOUR = BigInteger.valueOf(4);
    private static final BigInteger SEVEN = BigInteger.valueOf(7);

    @Override
    public CalculationMethod getCalculationMethod() {
        return CalculationMethod.DIGITS;
    }

    @Override
    public double calcPiDigits(int max) {
        BigInteger q = BigInteger.ONE;
        BigInteger r = BigInteger.ZERO;
        BigInteger t = BigInteger.ONE;
        BigInteger k = BigInteger.ONE;
        BigInteger n = BigInteger.valueOf(3);
        BigInteger l = BigInteger.valueOf(3);
        BigInteger nn;
        BigInteger nr;
        double pi = 0;
        boolean first = true;
        int digits = 0;
        BigInteger divider = BigInteger.ONE;
        while (digits < max) {
            if (FOUR.multiply(q).add(r).subtract(t).compareTo(n.multiply(t)) == -1) {
                pi += n.doubleValue() / divider.doubleValue();
                divider = divider.multiply(BigInteger.TEN);
                if (first) {
                    first = false;
                }
                nr = BigInteger.TEN.multiply(r.subtract(n.multiply(t)));
                n = BigInteger.TEN.multiply(THREE.multiply(q).add(r)).divide(t).subtract(BigInteger.TEN.multiply(n));
                q = q.multiply(BigInteger.TEN);
                r = nr;
                digits++;
            } else {
                nr = TWO.multiply(q).add(r).multiply(l);
                nn = q.multiply((SEVEN.multiply(k))).add(TWO).add(r.multiply(l)).divide(t.multiply(l));
                q = q.multiply(k);
                t = t.multiply(l);
                l = l.add(TWO);
                k = k.add(BigInteger.ONE);
                n = nn;
                r = nr;
            }
        }
        return pi;
    }
}
