package com.daniel.matters.activities;

import android.test.ActivityInstrumentationTestCase2;

import com.daniel.matters.R;

import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

/**
 * Created by dabraham on 1/10/16.
 */
public class MatterDetailsTest extends ActivityInstrumentationTestCase2 {

    public MatterDetailsTest() {
        super(MattersActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }

    @Test
    public void testOpenMatterDetails() throws InterruptedException {
        Thread.sleep(3000);
        onData(anything()).inAdapterView(withId(R.id.matters_list)).atPosition(0).perform(click());
        Thread.sleep(2000);
        onView(allOf(withId(R.id.matter_id))).check(matches(withText("1021016254 - Protective Order")));
        onView(allOf(withId(R.id.client_name))).check(matches(withText("Clio LLC")));
        onView(allOf(withId(R.id.matter_open_date))).check(matches(withText("2013-12-13")));
        onView(allOf(withId(R.id.matter_status))).check(matches(withText("Open")));
    }
}
