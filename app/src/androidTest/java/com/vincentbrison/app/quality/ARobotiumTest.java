package com.vincentbrison.app.quality;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.robotium.solo.Condition;
import com.robotium.solo.Solo;

import org.mockito.Mockito;

import vb.android.app.quality.ui.MainActivity;
import vb.android.app.quality.pi.PiGenerator;
import vb.android.app.quality.R;

/**
 * Class to test instrumentation testing with the help of robotium.
 */
public class ARobotiumTest extends ActivityInstrumentationTestCase2 {

    private Solo solo;

    public ARobotiumTest() {
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

    public void testThatPIComputationEnableRanking() {
        solo.waitForActivity(MainActivity.class);
        PiGenerator pi = Mockito.mock(PiGenerator.class);
        Mockito.when(pi.calcPiDigits(5)).thenReturn(3.1415);

        final EditText editText = (EditText) solo.getView(R.id.editTextDigits);
        final Button buttonSendPI = (Button) solo.getView(R.id.buttonSendPi);
        solo.enterText(editText, "5");
        solo.clickOnView(solo.getView(R.id.buttonCompute));
        solo.waitForCondition(new Condition() {
            @Override
            public boolean isSatisfied() {
                return buttonSendPI.isEnabled();
            }
        }, 5000);

        assertTrue("After a Pi computation, user is able to send its result", buttonSendPI.isEnabled());
    }
}
