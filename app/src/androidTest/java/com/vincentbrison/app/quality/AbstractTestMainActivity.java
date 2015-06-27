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

package com.vincentbrison.app.quality;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import vb.android.app.quality.R;
import vb.android.app.quality.ui.MainActivity;

/**
 * Test class which describe functional tests for the main activity.
 */
@RunWith(AndroidJUnit4.class)
public abstract class AbstractTestMainActivity extends ActivityInstrumentationTestCase2 {

    protected MockWebServer mMockWebServer;

    public AbstractTestMainActivity() {
        super(MainActivity.class);
    }

    protected abstract void userAskPIComputation();
    protected abstract void userAskSendPIOnlineForRank();
    protected abstract void userAskShare();

    protected abstract boolean checkPIComputationWentOK();
    protected abstract boolean checkSendPIWentOK();
    protected abstract boolean checkSendPIWentWrong();
    protected abstract boolean checkShareWentOK();

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mMockWebServer = new MockWebServer();
    }

    @After
    @Override
    public void tearDown() throws Exception {
        mMockWebServer.shutdown();
        super.tearDown();
    }

    @Test
    public void testThatDefaultBehaviorIsWorking() {
        userAskPIComputation();
        assertTrue("After a Pi computation, user is able to send its result.", checkPIComputationWentOK());
        userAskSendPIOnlineForRank();
        assertTrue("After ranking his result online, the user should be able to share his rank.", checkSendPIWentOK());
        userAskShare();
        assertTrue("After asking for share, the user should be able to choose how he wants to share", checkShareWentOK());
    }

    @Test
    public void testThatServerIssueDisplayToast() {
        userAskPIComputation();
        assertTrue("After a Pi computation, user is able to send its result.", checkPIComputationWentOK());
        userAskSendPIOnlineForRank();
        assertTrue("A Toast message should be displayed.", checkSendPIWentWrong());
    }
}
