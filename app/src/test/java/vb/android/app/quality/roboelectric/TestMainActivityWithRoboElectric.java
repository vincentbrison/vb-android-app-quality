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

package vb.android.app.quality.roboelectric;

import android.widget.Button;
import android.widget.EditText;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;

import java.io.IOException;

import vb.android.app.quality.AssetsHelper;
import vb.android.app.quality.R;
import vb.android.app.quality.ui.MainActivity;

import static junit.framework.Assert.assertTrue;

/**
 * Test class which describe functional tests for the main activity.
 */
@RunWith(RobolectricGradleTestRunner.class)
public class TestMainActivityWithRoboElectric {

    protected MockWebServer mMockWebServer;
    protected MainActivity mMainActivity;
    protected EditText mEditText;
    protected Button mButtonSendPI;
    protected Button mButtonCompute;
    protected Button mButtonShare;

    protected void userAskPIComputation() {

    }

    protected void userAskSendPIOnlineForRank() {

    }

    protected void userAskShare() {

    }

    protected boolean checkPIComputationWentOK() {
        return false;
    }

    protected boolean checkSendPIWentOK() {
        return false;
    }

    protected boolean checkSendPIWentWrong() {
        return false;
    }

    protected boolean checkShareWentOK() {
        return false;
    }

    @Before
    public void setUp() throws Exception {
        mMockWebServer = new MockWebServer();
        try {
            mMockWebServer.start(Integer.parseInt(mActivityRule.getActivity().getString(R.string.port)));
        } catch (IOException e) {
            throw e;
        }
        mMainActivity = Robolectric.setupActivity(MainActivity.class);
        mEditText = (EditText) mMainActivity.findViewById(R.id.editTextDigits);
        mButtonSendPI = (Button) mMainActivity.findViewById(R.id.buttonSendPi);
        mButtonCompute = (Button) mMainActivity.findViewById(R.id.buttonCompute);
        mButtonShare = (Button) mMainActivity.findViewById(R.id.buttonShareResult);
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
        userAskSendPIOnlineForRank();
        assertTrue("After ranking his result online, the user should be able to share his rank.", checkSendPIWentOK());
        userAskShare();
        assertTrue("After asking for share, the user should be able to choose how he wants to share", checkShareWentOK());
    }

    @Test
    public void testThatServerIssueDisplayToast() {
        mMockWebServer.enqueue(new MockResponse().setResponseCode(500));
        userAskPIComputation();
        assertTrue("After a Pi computation, user is able to send its result.", checkPIComputationWentOK());
        userAskSendPIOnlineForRank();
        assertTrue("A Toast message should be displayed.", checkSendPIWentWrong());
    }
}
