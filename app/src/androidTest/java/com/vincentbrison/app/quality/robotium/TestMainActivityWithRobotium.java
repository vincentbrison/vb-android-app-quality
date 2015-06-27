package com.vincentbrison.app.quality.robotium;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.vincentbrison.app.quality.AbstractTestMainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import vb.android.app.quality.AssetsHelper;
import vb.android.app.quality.R;
import vb.android.app.quality.ui.MainActivity;

/**
 * Class to test instrumentation testing with the help of robotium.
 */
@RunWith(AndroidJUnit4.class)
public class TestMainActivityWithRobotium extends AbstractTestMainActivity {

    protected Solo mSolo;
    protected EditText mEditText;
    protected Button mButtonSendPI;
    protected Button mButtonCompute;
    protected Button mButtonShare;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mSolo = new Solo(getInstrumentation(), getActivity());
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
        mSolo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return mButtonSendPI.isEnabled();
            }
        }, 5000);
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
        // Very random way to check, may fail often.
        mSolo.waitForText("Share");
        return mSolo.searchText("Share");
    }

    @Override
    public void testThatDefaultBehaviorIsWorking() {
        mMockWebServer.enqueue(new MockResponse().setBody(AssetsHelper.getStringFromAsset("stubs/rank_ok.json")));
        try {
            mMockWebServer.start(Integer.parseInt(getActivity().getString(R.string.port)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mSolo.waitForActivity(MainActivity.class);

        super.testThatDefaultBehaviorIsWorking();
    }

    @Override
    public void testThatServerIssueDisplayToast() {
        mMockWebServer.enqueue(new MockResponse().setResponseCode(500));
        try {
            mMockWebServer.start(Integer.parseInt(getActivity().getString(R.string.port)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mSolo.waitForActivity(MainActivity.class);

        super.testThatServerIssueDisplayToast();
    }
}
