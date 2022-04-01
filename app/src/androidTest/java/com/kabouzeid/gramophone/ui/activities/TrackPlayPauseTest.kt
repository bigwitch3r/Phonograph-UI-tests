package com.kabouzeid.gramophone.ui.activities


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TrackPlayPauseTest {

    @Rule
    @JvmField
    var mActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule: GrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE")

    @Test
    fun trackPlayPauseTest() {
        //val appCompatButton = onView(allOf(withText("Get started"), childAtPosition(allOf(withId(R.id.mi_button_cta), childAtPosition(withId(R.id.mi_frame), 4)), 0), isDisplayed()))
        //appCompatButton.perform(click())
        try {
            val appCompatButton = onView(allOf(withText("Get started"), childAtPosition(allOf(withId(R.id.mi_button_cta), childAtPosition(withId(R.id.mi_frame), 4)), 0), isDisplayed()))
            appCompatButton.perform(click())
        } catch (e: NoMatchingViewException) {

        }

        val frameLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recycler_view),
                                childAtPosition(
                                        withId(R.id.container),
                                        0)),
                        1),
                        isDisplayed()))
        frameLayout.perform(click())

        val iconImageView = onView(
                allOf(withId(R.id.mini_player_play_pause_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.mini_player_fragment),
                                        0),
                                2),
                        isDisplayed()))
        iconImageView.perform(click())

        val iconImageView2 = onView(
                allOf(withId(R.id.mini_player_play_pause_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.mini_player_fragment),
                                        0),
                                2),
                        isDisplayed()))
        iconImageView2.perform(click())
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
