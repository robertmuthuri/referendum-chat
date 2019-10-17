package com.example.referendum_chat;

import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class OneOnOneActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<OneOOneActivity> activityTestRule = new ActivityTestRule<>(OneOOneActivity.class);

    @Test
    public void listItemClickDisplaysToastWuthCorrectContent() {
        View activityDecorView = activityTestRule.getActivity().getWindow().getDecorView();

        String referendumDefinition = "Definition: a general vote by the electorate on a single political question which has been referred to them for a direct decision.";

        onData(anything())
                .inAdapterView(withId(R.id.listView))
                .atPosition(0)
                .perform(click());

        onView(withText(referendumDefinition)).inRoot(withDecorView(not(activityDecorView)))
                .check(matches(withText(referendumDefinition)));

    }
}
