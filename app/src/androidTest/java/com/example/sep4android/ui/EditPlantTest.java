package com.example.sep4android.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.sep4android.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditPlantTest {

    @Rule
    public ActivityTestRule<LogInActivity> mActivityTestRule = new ActivityTestRule<>(LogInActivity.class);

    @Test
    public void editPlant() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText_email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("naya7777@"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editText_email), withText("naya7777@"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editText_email), withText("naya7777@"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("naya7777@gmail.com.com"));

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editText_email), withText("naya7777@gmail.com.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText4.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.editText_email), withText("naya7777@gmail.com.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText5.perform(pressImeActionButton());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.editText_password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("123"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.editText_password), withText("123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText7.perform(pressImeActionButton());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login), withText("LOG IN"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.editText_email), withText("naya7777@gmail.com.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("naya7777@gmail.com"));

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.editText_email), withText("naya7777@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText9.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.editText_email), withText("naya7777@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText10.perform(pressImeActionButton());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.login), withText("LOG IN"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.parent_layout),
                        childAtPosition(
                                allOf(withId(R.id.rv),
                                        childAtPosition(
                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingActionButton),
                        childAtPosition(
                                allOf(withId(R.id.plantInfo),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                5),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.editPalntName), withText("q"),
                        childAtPosition(
                                allOf(withId(R.id.parentlayout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText11.perform(replaceText("ros"));

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.editPalntName), withText("ros"),
                        childAtPosition(
                                allOf(withId(R.id.parentlayout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText12.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button_clear), withText("Clear"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.parentlayout),
                                        6),
                                0),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.editPalntName),
                        childAtPosition(
                                allOf(withId(R.id.parentlayout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText13.perform(click());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.editPalntName),
                        childAtPosition(
                                allOf(withId(R.id.parentlayout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText14.perform(pressImeActionButton());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.editPalntName),
                        childAtPosition(
                                allOf(withId(R.id.parentlayout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText15.perform(pressImeActionButton());

        pressBack();

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_save), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.parentlayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.editPalntName),
                        childAtPosition(
                                allOf(withId(R.id.parentlayout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText16.perform(replaceText("h"), closeSoftKeyboard());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.editText_sensorId),
                        childAtPosition(
                                allOf(withId(R.id.parentlayout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText17.perform(replaceText("111"), closeSoftKeyboard());

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.editText_sensorId), withText("111"),
                        childAtPosition(
                                allOf(withId(R.id.parentlayout),
                                        childAtPosition(
                                                withId(R.id.frameLayout),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatEditText18.perform(pressImeActionButton());

        pressBack();

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btn_save), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.parentlayout),
                                        6),
                                1),
                        isDisplayed()));
        appCompatButton5.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
