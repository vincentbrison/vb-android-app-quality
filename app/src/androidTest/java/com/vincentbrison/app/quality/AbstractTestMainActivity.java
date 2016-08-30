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

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.squareup.spoon.Spoon;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import vb.android.app.quality.AssetsHelper;
import vb.android.app.quality.R;
import vb.android.app.quality.ui.MainActivity;

import static junit.framework.Assert.assertTrue;

/**
 * Test class which describe functional tests for the main activity.
 */
@RunWith(AndroidJUnit4.class)
public abstract class AbstractTestMainActivity {

    protected MockWebServer mMockWebServer;

    protected abstract void userAskPIComputation();

    protected abstract void userAskSendPIOnlineForRank();

    protected abstract void userAskShare();

    protected abstract boolean checkPIComputationWentOK();

    protected abstract boolean checkSendPIWentOK();

    protected abstract boolean checkSendPIWentWrong();

    protected abstract boolean checkShareWentOK();

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mMockWebServer = new MockWebServer();
        try {
            mMockWebServer.start(Integer.parseInt(mActivityRule.getActivity().getString(R.string.port)));
        } catch (IOException e) {
            throw e;
        }
    }

    @After
    public void tearDown() throws Exception {
        mMockWebServer.shutdown();
    }

    @Test
    public void testThatDefaultBehaviorIsWorking() throws Exception {
        mMockWebServer.enqueue(new MockResponse().setBody(AssetsHelper.getStringFromAsset("stubs/rank_ok.json")));
        userAskPIComputation();
        assertTrue("After a Pi computation, user is able to send its result.", checkPIComputationWentOK());
        Spoon.screenshot(mActivityRule.getActivity(), "checkPIComputationWentOK");
        userAskSendPIOnlineForRank();
        assertTrue("After ranking his result online, the user should be able to share his rank.", checkSendPIWentOK());
        Spoon.screenshot(mActivityRule.getActivity(), "checkSendPIWentOK");
        userAskShare();
        assertTrue("After asking for share, the user should be able to choose how he wants to share", checkShareWentOK());
        Spoon.screenshot(mActivityRule.getActivity(), "checkShareWentOK");
    }

    @Test
    public void testThatServerIssueDisplayToast() {
        mMockWebServer.enqueue(new MockResponse().setResponseCode(500));
        userAskPIComputation();
        assertTrue("After a Pi computation, user is able to send its result.", checkPIComputationWentOK());
        Spoon.screenshot(mActivityRule.getActivity(), "checkPIComputationWentOK");
        userAskSendPIOnlineForRank();
        assertTrue("A Toast message should be displayed.", checkSendPIWentWrong());
        Spoon.screenshot(mActivityRule.getActivity(), "checkSendPIWentWrong");
    }
}
