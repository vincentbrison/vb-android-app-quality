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

package com.vincentbrison.app.quality.espresso;

import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.vincentbrison.app.quality.AbstractTestMainActivity;

import org.junit.runner.RunWith;

import vb.android.app.quality.ui.MainActivity;

/**
 * Class to test instrumentation testing with the help of Espresso.
 */
@RunWith(AndroidJUnit4.class)
public class TestMainActivityWithEspresso extends AbstractTestMainActivity {

    @Override
    protected void userAskPIComputation() {

    }

    @Override
    protected void userAskSendPIOnlineForRank() {

    }

    @Override
    protected void userAskShare() {

    }

    @Override
    protected boolean checkPIComputationWentOK() {
        return false;
    }

    @Override
    protected boolean checkSendPIWentOK() {
        return false;
    }

    @Override
    protected boolean checkSendPIWentWrong() {
        return false;
    }

    @Override
    protected boolean checkShareWentOK() {
        return false;
    }
}
