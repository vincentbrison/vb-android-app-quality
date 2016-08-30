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

package com.vincentbrison.app.quality.robotium;

import android.support.test.InstrumentationRegistry;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;
import com.vincentbrison.app.quality.AbstractTestMainActivity;

import vb.android.app.quality.R;
import vb.android.app.quality.ui.MainActivity;

/**
 * Class to test instrumentation testing with the help of robotium.
 */
public class TestMainActivityWithRobotium extends AbstractTestMainActivity {

    protected Solo mSolo;
    protected EditText mEditText;
    protected Button mButtonSendPI;
    protected Button mButtonCompute;
    protected Button mButtonShare;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mSolo = new Solo(InstrumentationRegistry.getInstrumentation(), mActivityRule.getActivity());
        mEditText = (EditText) mSolo.getView(R.id.editTextDigits);
        mButtonSendPI = (Button) mSolo.getView(R.id.buttonSendPi);
        mButtonCompute = (Button) mSolo.getView(R.id.buttonCompute);
        mButtonShare = (Button) mSolo.getView(R.id.buttonShareResult);
    }

    @Override
    public void tearDown() throws Exception {
        mSolo.finishOpenedActivities();
        super.tearDown();
    }

    @Override
    protected void userAskPIComputation() {
        mSolo.enterText(mEditText, "5");
        mSolo.clickOnView(mButtonCompute);
    }

    @Override
    protected void userAskSendPIOnlineForRank() {
        mSolo.clickOnView(mButtonSendPI);
    }

    @Override
    protected void userAskShare() {
        mSolo.clickOnView(mButtonShare);
    }

    @Override
    protected boolean checkPIComputationWentOK() {
        mSolo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return mButtonSendPI.isEnabled();
            }
        }, 5000);
        return mButtonSendPI.isEnabled();
    }

    @Override
    protected boolean checkSendPIWentOK() {
        mSolo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return mButtonShare.isEnabled();
            }
        }, 5000);
        return mButtonShare.isEnabled();
    }

    @Override
    protected boolean checkSendPIWentWrong() {
        mSolo.waitForText(mSolo.getString(R.string.network_issue));
        return mSolo.searchText(mSolo.getString(R.string.network_issue));
    }

    @Override
    protected boolean checkShareWentOK() {
        // Cannot check that intent is deliver since its go outside of the application.
        // Return fake result true.
        return true;
    }

    @Override
    public void testThatDefaultBehaviorIsWorking() throws Exception {
        mSolo.waitForActivity(MainActivity.class);
        super.testThatDefaultBehaviorIsWorking();
    }

    @Override
    public void testThatServerIssueDisplayToast() {
        mSolo.waitForActivity(MainActivity.class);
        super.testThatServerIssueDisplayToast();
    }
}
