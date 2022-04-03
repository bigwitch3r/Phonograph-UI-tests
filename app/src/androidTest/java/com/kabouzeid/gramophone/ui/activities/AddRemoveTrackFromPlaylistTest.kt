package com.kabouzeid.gramophone.ui.activities


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.kabouzeid.gramophone.R
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class AddRemoveTrackFromPlaylistTest {

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
    fun addRemoveTrackFromPlaylistTest() {
        try {
            val appCompatButton = onView(allOf(withText("Get started"), childAtPosition(allOf(withId(R.id.mi_button_cta), childAtPosition(withId(R.id.mi_frame), 4)), 0), isDisplayed()))
            appCompatButton.perform(click())
        } catch (e: NoMatchingViewException) {
        }

        val tabView = onView(
                allOf(
                        withContentDescription("œÀ≈…À»—“€"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                4),
                ))
        tabView.perform(scrollTo(), click())

        val overflowMenuButton = onView(
                allOf(withContentDescription("≈˘∏"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.toolbar),
                                        2),
                                1),
                        isDisplayed()))
        overflowMenuButton.perform(click())

        val appCompatTextView = onView(
                allOf(withId(R.id.title), withText("ÕÓ‚˚È ÔÎÂÈÎËÒÚ"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()))
        appCompatTextView.perform(click())

        val appCompatEditText = onView(
                allOf(withId(android.R.id.input),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.LinearLayout")),
                                        1),
                                0),
                        isDisplayed()))
        appCompatEditText.perform(replaceText("Test"), closeSoftKeyboard())

        val mDButton = onView(
                allOf(withId(R.id.md_buttonDefaultPositive), withText("—ÓÁ‰‡Ú¸"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                4),
                        isDisplayed()))
        mDButton.perform(click())

        val tabView2 = onView(
                allOf(
                        withContentDescription("“–≈ »"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                0),
                ))
        tabView2.perform(scrollTo(), click())

        Thread.sleep(1000)

        val iconImageView = onView(
                firstView(
                allOf(withId(R.id.menu),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.FrameLayout")),
                                        1),
                                2),
                        isDisplayed())))
        iconImageView.perform(click())

        val appCompatTextView2 = onView(
                allOf(withId(android.R.id.title), withText("ƒÓ·‡‚ËÚ¸ ‚ ÔÎÂÈÎËÒÚ..."),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                0),
                        ))
        appCompatTextView2.perform(click())

        val recyclerView = onView(
                allOf(withId(R.id.md_contentRecyclerView),
                        childAtPosition(
                                withId(R.id.md_contentListViewFrame),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(4, click()))
        Thread.sleep(1000)

        val tabView3 = onView(
                allOf(
                        withContentDescription("œÀ≈…À»—“€"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.tabs),
                                        0),
                                4),
                ))
        tabView3.perform(scrollTo(), click())

        Thread.sleep(1000)

        val frameLayout = onView(
                allOf(childAtPosition(
                        allOf(withId(R.id.recycler_view),
                                childAtPosition(
                                        withId(R.id.container),
                                        0)),
                        6),
                        ))
        frameLayout.perform(click())

        Thread.sleep(1000)
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

    private fun <T> firstView(matcher: Matcher<T>): Matcher<T>? {
        return object : BaseMatcher<T>() {
            var isFirst = true
            override fun matches(item: Any): Boolean {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false
                    return true
                }
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("should return first matching item")
            }
        }
    }
}
