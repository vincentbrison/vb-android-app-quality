package com.vincentbrison.app.quality;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import vb.android.app.quality.Constants;
import vb.android.app.quality.MainActivity;
import vb.android.app.quality.R;

/**
 * Class to test instrumentation testing with the help of robotium.
 */
public class MyRoboTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public MyRoboTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testUI() {
        solo.waitForActivity(MainActivity.class);
        final TextView tv = (TextView) solo.getView(R.id.textView);
        solo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return tv.isShown();
            }
        }, 5000);

        assertTrue("TextView should have been set to the flavor name.", tv.getText().toString().equals(Constants.FLAVOR));
    }
}
