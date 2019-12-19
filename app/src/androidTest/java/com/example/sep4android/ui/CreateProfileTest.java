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
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class CreateProfileTest {

    @Rule
    public ActivityTestRule<LogInActivity> mActivityTestRule = new ActivityTestRule<>(LogInActivity.class);

    @Test
    public void createProfile() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.editText_email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.editText_email),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("naya7777@gmail.com"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.editText_email), withText("naya7777@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.editText_password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.editText_password),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("123"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.editText_password), withText("123"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText6.perform(pressImeActionButton());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.login), withText("LOG IN"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                        5),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_plantProfile), withContentDescription("plant profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.floatingActionButton),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_save), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profileLayout),
                                        10),
                                1)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.editProfileName),
                        childAtPosition(
                                allOf(withId(R.id.profileLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                1)));
        appCompatEditText7.perform(scrollTo(), replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.editTempMin),
                        childAtPosition(
                                allOf(withId(R.id.layouttemp),
                                        childAtPosition(
                                                withId(R.id.profileLayout),
                                                3)),
                                0)));
        appCompatEditText8.perform(scrollTo(), replaceText("0"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.editTempMax),
                        childAtPosition(
                                allOf(withId(R.id.layouttemp),
                                        childAtPosition(
                                                withId(R.id.profileLayout),
                                                3)),
                                1)));
        appCompatEditText9.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.button_clear), withText("Clear"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profileLayout),
                                        10),
                                0)));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.editProfileName),
                        childAtPosition(
                                allOf(withId(R.id.profileLayout),
                                        childAtPosition(
                                                withClassName(is("android.widget.ScrollView")),
                                                0)),
                                1)));
        appCompatEditText10.perform(scrollTo(), replaceText("pt"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.editTempMin),
                        childAtPosition(
                                allOf(withId(R.id.layouttemp),
                                        childAtPosition(
                                                withId(R.id.profileLayout),
                                                3)),
                                0)));
        appCompatEditText11.perform(scrollTo(), replaceText("0"), closeSoftKeyboard());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.editTempMax),
                        childAtPosition(
                                allOf(withId(R.id.layouttemp),
                                        childAtPosition(
                                                withId(R.id.profileLayout),
                                                3)),
                                1)));
        appCompatEditText12.perform(scrollTo(), replaceText("1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.editCo2Max),
                        childAtPosition(
                                allOf(withId(R.id.layoutco2),
                                        childAtPosition(
                                                withId(R.id.profileLayout),
                                                5)),
                                1)));
        appCompatEditText13.perform(scrollTo(), replaceText("3"), closeSoftKeyboard());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.editCo2Min),
                        childAtPosition(
                                allOf(withId(R.id.layoutco2),
                                        childAtPosition(
                                                withId(R.id.profileLayout),
                                                5)),
                                0)));
        appCompatEditText14.perform(scrollTo(), replaceText("2"), closeSoftKeyboard());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.editLightMin),
                        childAtPosition(
                                allOf(withId(R.id.layoutlight),
                                        childAtPosition(
                                                withId(R.id.profileLayout),
                                                7)),
                                0)));
        appCompatEditText15.perform(scrollTo(), replaceText("4"), closeSoftKeyboard());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.editLightMax),
                        childAtPosition(
                                allOf(withId(R.id.layoutlight),
                                        childAtPosition(
                                                withId(R.id.profileLayout),
                                                7)),
                                1)));
        appCompatEditText16.perform(scrollTo(), replaceText("5"), closeSoftKeyboard());

        ViewInteraction appCompatEditText17 = onView(
                allOf(withId(R.id.editHumidityMin),
                        childAtPosition(
                                allOf(withId(R.id.layouthumidity),
                                        childAtPosition(
                                                withId(R.id.profileLayout),
                                                9)),
                                0)));
        appCompatEditText17.perform(scrollTo(), replaceText("6"), closeSoftKeyboard());

        ViewInteraction appCompatEditText18 = onView(
                allOf(withId(R.id.editHumidityMax),
                        childAtPosition(
                                allOf(withId(R.id.layouthumidity),
                                        childAtPosition(
                                                withId(R.id.profileLayout),
                                                9)),
                                1)));
        appCompatEditText18.perform(scrollTo(), replaceText("7"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_save), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.profileLayout),
                                        10),
                                1)));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_plantProfile), withContentDescription("plant profile"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());
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
