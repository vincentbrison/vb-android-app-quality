package com.vincentbrison.app.quality;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import vb.android.app.quality.Greeting;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

/**
 * Class to test Android test case.
 */
@RunWith(AndroidJUnit4.class)
public class MyAndroidTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testContextShouldNotBeNull() {
        assertThat(InstrumentationRegistry.getContext()).isNotNull();
    }

    @Test
    public void testTargetContextShouldNotBeNull() {
        assertThat(InstrumentationRegistry.getTargetContext()).isNotNull();
    }

    /**
     * this is a simple example of mocking method of an interface
     */
    @Test
    public void testShouldGenerateMockedGreeting() {
        // given
        Greeting greeting = Mockito.mock(Greeting.class);
        String givenGreeting = "Hello World!";

        // when
        when(greeting.generate()).thenReturn(givenGreeting);

        // then
        assertThat(greeting.generate()).isEqualTo(givenGreeting);
    }
}
