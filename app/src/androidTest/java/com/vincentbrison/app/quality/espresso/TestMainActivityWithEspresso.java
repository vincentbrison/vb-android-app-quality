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

package com.vincentbrison.app.quality.espresso;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;

import com.vincentbrison.app.quality.AbstractTestMainActivity;

import vb.android.app.quality.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Class to test instrumentation testing with the help of Espresso.
 */
public class TestMainActivityWithEspresso extends AbstractTestMainActivity {

    @Override
    protected void userAskPIComputation() {
        onView(withId(R.id.editTextDigits)).perform(typeText("5"));
        onView(withId(R.id.buttonCompute)).perform(click());
    }

    @Override
    protected void userAskSendPIOnlineForRank() {
        onView(withId(R.id.buttonSendPi)).perform(click());
    }

    @Override
    protected void userAskShare() {
        onView(withId(R.id.buttonShareResult)).perform(click());
    }

    @Override
    protected boolean checkPIComputationWentOK() {
        onView(withId(R.id.buttonSendPi)).check(matches(isEnabled()));
        return true;
    }

    @Override
    protected boolean checkSendPIWentOK() {
        onView(withId(R.id.buttonShareResult)).check(matches(isEnabled()));
        return true;
    }

    @Override
    protected boolean checkSendPIWentWrong() {
        onView(withText(R.string.network_issue)).inRoot(withDecorView(
                not((mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
        return true;
    }

    @Override
    protected boolean checkShareWentOK() {
        intended(allOf(
                hasAction(Intent.ACTION_SEND),
                IntentMatchers.hasExtra(Intent.EXTRA_SUBJECT, mActivityRule.getActivity().getString(R.string.share_title))));
        return true;
    }

    @Override
    public void testThatDefaultBehaviorIsWorking() throws Exception {
        Instrumentation.ActivityResult dummyResult = new Instrumentation.ActivityResult(0, null);
        Intents.intending(allOf(
                hasAction(Intent.ACTION_SEND),
                IntentMatchers.hasExtra(Intent.EXTRA_SUBJECT, mActivityRule.getActivity().getString(R.string.share_title))))
                .respondWith(dummyResult);
        super.testThatDefaultBehaviorIsWorking();
    }
}
