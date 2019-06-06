package com.g_draflab.orderit;

import android.support.test.espresso.Espresso;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.g_draflab.orderit.Activities.SignInActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignInTest {


    @Rule
    public ActivityTestRule<SignInActivity> activityRule
            = new ActivityTestRule<>(SignInActivity.class);


    @Test
    public void testSignInViews() {
        onView(withId(R.id.sign_in_button));
        onView(withId(R.id.signin_tool_bar));
        onView(withId(R.id.signin_toolbar_title));
        onView(withId(R.id.sign_in_container));
        onView(withId(R.id.email_edit_Text));
        onView(withId(R.id.password_edit_text));
        onView(withId(R.id.forget_password_btn));
        onView(withId(R.id.center_polygon_signin));
        onView(withId(R.id.sign_in_line_one));
        onView(withId(R.id.facebook_login_button));
        onView(withId(R.id.twitter_login_button));
        onView(withId(R.id.sign_in_line_two));
        onView(withId(R.id.sign_Up_sign_button));
        onView(withId(R.id.sign_in_button)).perform(click());

    }
}
