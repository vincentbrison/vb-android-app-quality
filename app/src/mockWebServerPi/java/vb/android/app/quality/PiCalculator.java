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
