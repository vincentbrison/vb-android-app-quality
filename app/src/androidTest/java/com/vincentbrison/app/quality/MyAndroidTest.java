package com.vincentbrison.app.quality;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import vb.android.app.quality.NumberGenerator;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

/**
 * Class to test Android test case.
 */
@RunWith(AndroidJUnit4.class)
public class MyAndroidTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testContext() {
        assertThat(InstrumentationRegistry.getContext()).isNotNull();
    }

    @Test
    public void testTargetContext() {
        assertThat(InstrumentationRegistry.getTargetContext()).isNotNull();
    }

    @Test
    public void testShouldGenerate63() {
        // example of mocking classes
        NumberGenerator mockedNumberGenerator = Mockito.mock(NumberGenerator.class);
        when(mockedNumberGenerator.generateAnyNumber()).thenReturn(63);
        // when it's NOT mocked, it should return 42 instead of 63
        assertThat(mockedNumberGenerator.generateAnyNumber()).isEqualTo(63);
    }

    @Test
    public void testShouldGenerate42() {
        NumberGenerator numberGenerator = new NumberGenerator();
        int generatedNumber = numberGenerator.generateAnyNumber();
        assertThat(generatedNumber).isEqualTo(42);
    }
}
