package com.vincentbrison.app.quality;

import android.test.AndroidTestCase;

/**
 * Class to test Android test case.
 */
public class MyAndroidTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContext() {
        assertTrue("The context should not be null", getContext() != null);
    }

}
