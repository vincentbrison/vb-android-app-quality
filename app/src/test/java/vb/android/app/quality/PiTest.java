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

/**
 * Created by Vincent Brison on 14/06/2015.
 */
public class PiTest extends TestCase {
    public void testCalcPiDigits() throws Exception {
        if (Contants.FLAVOR.equals(Flavors.APPROXIMATION_PI)) {
            double pi = new PiCalculator().calcPiDigits(500);
            assertTrue("After 500 interations, computed Pi (" +
                    pi +
                    ") should have at least 2 decimals rights.", pi <= 3.15 && pi >= 3.13);
        } else {
            double pi = new PiCalculator().calcPiDigits(5);
            assertTrue("The 5 first decimals of computed Pi (" + pi + ") should be correct.", pi == 3.1415);
        }
    }
}
