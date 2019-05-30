package com.g_draflab.orderit;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.g_draflab.orderit.Activities.SignInActivity;
import com.g_draflab.orderit.Activities.SignUpActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SignUpTest {


    @Rule
    public ActivityTestRule<SignUpActivity> activityRule
            = new ActivityTestRule<>(SignUpActivity.class);

    @Test
    public void testSignUpViews() {
        // Type text and then press the button.
        onView(withId(R.id.signup_tool_bar));
        onView(withId(R.id.signup_toolbar_title));
        onView(withId(R.id.sign_up_container));
        onView(withId(R.id.first_name_edit_Text));
        onView(withId(R.id.last_name_edit_text));
        onView(withId(R.id.sign_up_email_edit_Text));
        onView(withId(R.id.sign_up_password_edit_text));
        onView(withId(R.id.sign_up_retype_password_edit_text));
        onView(withId(R.id.month_of_birth));
        onView(withId(R.id.year_of_birth));
        onView(withId(R.id.gender_selector_image));
        onView(withId(R.id.terms_switch));
        onView(withId(R.id.sign_up_button));
        onView(withId(R.id.sign_up_container)).perform(click());

    }
}
