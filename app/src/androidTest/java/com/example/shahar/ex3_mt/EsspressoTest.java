package com.example.shahar.ex3_mt;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by user on 14/01/2018.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EsspressoTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule(LoginActivity.class);

    @Test
    public void listGoesOverTheFold() {

        onView(withId(R.id.email_sign_in_button)).check(matches(withText("Sign in")));

    }
}
